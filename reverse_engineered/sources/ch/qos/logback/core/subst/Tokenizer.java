package ch.qos.logback.core.subst;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.subst.Token.Type;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    final String pattern;
    final int patternLength;
    int pointer = 0;
    TokenizerState state = TokenizerState.LITERAL_STATE;

    enum TokenizerState {
        LITERAL_STATE,
        START_STATE,
        DEFAULT_VAL_STATE
    }

    public Tokenizer(String str) {
        this.pattern = str;
        this.patternLength = str.length();
    }

    private void addLiteralToken(List<Token> list, StringBuilder stringBuilder) {
        if (stringBuilder.length() != 0) {
            list.add(new Token(Type.LITERAL, stringBuilder.toString()));
        }
    }

    private void handleDefaultValueState(char c, List<Token> list, StringBuilder stringBuilder) {
        switch (c) {
            case '$':
                stringBuilder.append(CoreConstants.COLON_CHAR);
                addLiteralToken(list, stringBuilder);
                stringBuilder.setLength(0);
                this.state = TokenizerState.START_STATE;
                return;
            case '-':
                list.add(Token.DEFAULT_SEP_TOKEN);
                this.state = TokenizerState.LITERAL_STATE;
                return;
            default:
                stringBuilder.append(CoreConstants.COLON_CHAR).append(c);
                this.state = TokenizerState.LITERAL_STATE;
                return;
        }
    }

    private void handleLiteralState(char c, List<Token> list, StringBuilder stringBuilder) {
        if (c == CoreConstants.DOLLAR) {
            addLiteralToken(list, stringBuilder);
            stringBuilder.setLength(0);
            this.state = TokenizerState.START_STATE;
        } else if (c == CoreConstants.COLON_CHAR) {
            addLiteralToken(list, stringBuilder);
            stringBuilder.setLength(0);
            this.state = TokenizerState.DEFAULT_VAL_STATE;
        } else if (c == CoreConstants.CURLY_LEFT) {
            addLiteralToken(list, stringBuilder);
            list.add(Token.CURLY_LEFT_TOKEN);
            stringBuilder.setLength(0);
        } else if (c == CoreConstants.CURLY_RIGHT) {
            addLiteralToken(list, stringBuilder);
            list.add(Token.CURLY_RIGHT_TOKEN);
            stringBuilder.setLength(0);
        } else {
            stringBuilder.append(c);
        }
    }

    private void handleStartState(char c, List<Token> list, StringBuilder stringBuilder) {
        if (c == CoreConstants.CURLY_LEFT) {
            list.add(Token.START_TOKEN);
        } else {
            stringBuilder.append(CoreConstants.DOLLAR).append(c);
        }
        this.state = TokenizerState.LITERAL_STATE;
    }

    List<Token> tokenize() throws ScanException {
        List<Token> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        while (this.pointer < this.patternLength) {
            char charAt = this.pattern.charAt(this.pointer);
            this.pointer++;
            switch (this.state) {
                case LITERAL_STATE:
                    handleLiteralState(charAt, arrayList, stringBuilder);
                    break;
                case START_STATE:
                    handleStartState(charAt, arrayList, stringBuilder);
                    break;
                case DEFAULT_VAL_STATE:
                    handleDefaultValueState(charAt, arrayList, stringBuilder);
                    break;
                default:
                    break;
            }
        }
        switch (this.state) {
            case LITERAL_STATE:
                addLiteralToken(arrayList, stringBuilder);
                break;
            case START_STATE:
                throw new ScanException("Unexpected end of pattern string");
        }
        return arrayList;
    }
}
