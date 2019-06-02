package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.pattern.util.IEscapeUtil;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import ch.qos.logback.core.spi.ScanException;
import com.avos.avoscloud.AVException;
import java.util.ArrayList;
import java.util.List;

class TokenStream {
    final IEscapeUtil escapeUtil;
    final IEscapeUtil optionEscapeUtil;
    final String pattern;
    final int patternLength;
    int pointer;
    TokenizerState state;

    enum TokenizerState {
        LITERAL_STATE,
        FORMAT_MODIFIER_STATE,
        KEYWORD_STATE,
        OPTION_STATE,
        RIGHT_PARENTHESIS_STATE
    }

    TokenStream(String str) {
        this(str, new RegularEscapeUtil());
    }

    TokenStream(String str, IEscapeUtil iEscapeUtil) {
        this.optionEscapeUtil = new RestrictedEscapeUtil();
        this.state = TokenizerState.LITERAL_STATE;
        this.pointer = 0;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("null or empty pattern string not allowed");
        }
        this.pattern = str;
        this.patternLength = str.length();
        this.escapeUtil = iEscapeUtil;
    }

    private void addValuedToken(int i, StringBuffer stringBuffer, List<Token> list) {
        if (stringBuffer.length() > 0) {
            list.add(new Token(i, stringBuffer.toString()));
            stringBuffer.setLength(0);
        }
    }

    private void handleFormatModifierState(char c, List<Token> list, StringBuffer stringBuffer) {
        if (c == CoreConstants.LEFT_PARENTHESIS_CHAR) {
            addValuedToken(1002, stringBuffer, list);
            list.add(Token.BARE_COMPOSITE_KEYWORD_TOKEN);
            this.state = TokenizerState.LITERAL_STATE;
        } else if (Character.isJavaIdentifierStart(c)) {
            addValuedToken(1002, stringBuffer, list);
            this.state = TokenizerState.KEYWORD_STATE;
            stringBuffer.append(c);
        } else {
            stringBuffer.append(c);
        }
    }

    private void handleKeywordState(char c, List<Token> list, StringBuffer stringBuffer) {
        if (Character.isJavaIdentifierPart(c)) {
            stringBuffer.append(c);
        } else if (c == CoreConstants.CURLY_LEFT) {
            addValuedToken(1004, stringBuffer, list);
            this.state = TokenizerState.OPTION_STATE;
        } else if (c == CoreConstants.LEFT_PARENTHESIS_CHAR) {
            addValuedToken(1005, stringBuffer, list);
            this.state = TokenizerState.LITERAL_STATE;
        } else if (c == CoreConstants.PERCENT_CHAR) {
            addValuedToken(1004, stringBuffer, list);
            list.add(Token.PERCENT_TOKEN);
            this.state = TokenizerState.FORMAT_MODIFIER_STATE;
        } else if (c == CoreConstants.RIGHT_PARENTHESIS_CHAR) {
            addValuedToken(1004, stringBuffer, list);
            this.state = TokenizerState.RIGHT_PARENTHESIS_STATE;
        } else {
            addValuedToken(1004, stringBuffer, list);
            if (c != CoreConstants.ESCAPE_CHAR) {
                stringBuffer.append(c);
            } else if (this.pointer < this.patternLength) {
                String str = this.pattern;
                int i = this.pointer;
                this.pointer = i + 1;
                this.escapeUtil.escape("%()", stringBuffer, str.charAt(i), this.pointer);
            }
            this.state = TokenizerState.LITERAL_STATE;
        }
    }

    private void handleLiteralState(char c, List<Token> list, StringBuffer stringBuffer) {
        switch (c) {
            case '%':
                addValuedToken(1000, stringBuffer, list);
                list.add(Token.PERCENT_TOKEN);
                this.state = TokenizerState.FORMAT_MODIFIER_STATE;
                return;
            case ')':
                addValuedToken(1000, stringBuffer, list);
                this.state = TokenizerState.RIGHT_PARENTHESIS_STATE;
                return;
            case '\\':
                escape("%()", stringBuffer);
                return;
            default:
                stringBuffer.append(c);
                return;
        }
    }

    private void handleRightParenthesisState(char c, List<Token> list, StringBuffer stringBuffer) {
        list.add(Token.RIGHT_PARENTHESIS_TOKEN);
        switch (c) {
            case ')':
                return;
            case '\\':
                escape("%{}", stringBuffer);
                this.state = TokenizerState.LITERAL_STATE;
                return;
            case AVException.INVALID_ACL /*123*/:
                this.state = TokenizerState.OPTION_STATE;
                return;
            default:
                stringBuffer.append(c);
                this.state = TokenizerState.LITERAL_STATE;
                return;
        }
    }

    private void processOption(char c, List<Token> list, StringBuffer stringBuffer) throws ScanException {
        new OptionTokenizer(this).tokenize(c, list);
    }

    void escape(String str, StringBuffer stringBuffer) {
        if (this.pointer < this.patternLength) {
            String str2 = this.pattern;
            int i = this.pointer;
            this.pointer = i + 1;
            this.escapeUtil.escape(str, stringBuffer, str2.charAt(i), this.pointer);
        }
    }

    void optionEscape(String str, StringBuffer stringBuffer) {
        if (this.pointer < this.patternLength) {
            String str2 = this.pattern;
            int i = this.pointer;
            this.pointer = i + 1;
            this.optionEscapeUtil.escape(str, stringBuffer, str2.charAt(i), this.pointer);
        }
    }

    List tokenize() throws ScanException {
        List arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        while (this.pointer < this.patternLength) {
            char charAt = this.pattern.charAt(this.pointer);
            this.pointer++;
            switch (this.state) {
                case LITERAL_STATE:
                    handleLiteralState(charAt, arrayList, stringBuffer);
                    break;
                case FORMAT_MODIFIER_STATE:
                    handleFormatModifierState(charAt, arrayList, stringBuffer);
                    break;
                case OPTION_STATE:
                    processOption(charAt, arrayList, stringBuffer);
                    break;
                case KEYWORD_STATE:
                    handleKeywordState(charAt, arrayList, stringBuffer);
                    break;
                case RIGHT_PARENTHESIS_STATE:
                    handleRightParenthesisState(charAt, arrayList, stringBuffer);
                    break;
                default:
                    break;
            }
        }
        switch (this.state) {
            case LITERAL_STATE:
                addValuedToken(1000, stringBuffer, arrayList);
                break;
            case FORMAT_MODIFIER_STATE:
            case OPTION_STATE:
                throw new ScanException("Unexpected end of pattern string");
            case KEYWORD_STATE:
                arrayList.add(new Token(1004, stringBuffer.toString()));
                break;
            case RIGHT_PARENTHESIS_STATE:
                arrayList.add(Token.RIGHT_PARENTHESIS_TOKEN);
                break;
        }
        return arrayList;
    }
}
