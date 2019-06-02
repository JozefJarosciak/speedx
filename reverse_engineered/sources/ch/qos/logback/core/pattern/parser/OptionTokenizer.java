package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.pattern.util.AsIsEscapeUtil;
import ch.qos.logback.core.pattern.util.IEscapeUtil;
import ch.qos.logback.core.spi.ScanException;
import com.avos.avoscloud.AVException;
import java.util.ArrayList;
import java.util.List;

public class OptionTokenizer {
    private static final int EXPECTING_STATE = 0;
    private static final int QUOTED_COLLECTING_STATE = 2;
    private static final int RAW_COLLECTING_STATE = 1;
    final IEscapeUtil escapeUtil;
    final String pattern;
    final int patternLength;
    char quoteChar;
    int state;
    final TokenStream tokenStream;

    OptionTokenizer(TokenStream tokenStream) {
        this(tokenStream, new AsIsEscapeUtil());
    }

    OptionTokenizer(TokenStream tokenStream, IEscapeUtil iEscapeUtil) {
        this.state = 0;
        this.tokenStream = tokenStream;
        this.pattern = tokenStream.pattern;
        this.patternLength = tokenStream.patternLength;
        this.escapeUtil = iEscapeUtil;
    }

    void emitOptionToken(List<Token> list, List<String> list2) {
        list.add(new Token(1006, list2));
        this.tokenStream.state = TokenizerState.LITERAL_STATE;
    }

    void escape(String str, StringBuffer stringBuffer) {
        if (this.tokenStream.pointer < this.patternLength) {
            String str2 = this.pattern;
            TokenStream tokenStream = this.tokenStream;
            int i = tokenStream.pointer;
            tokenStream.pointer = i + 1;
            this.escapeUtil.escape(str, stringBuffer, str2.charAt(i), this.tokenStream.pointer);
        }
    }

    void tokenize(char c, List<Token> list) throws ScanException {
        StringBuffer stringBuffer = new StringBuffer();
        List arrayList = new ArrayList();
        while (this.tokenStream.pointer < this.patternLength) {
            switch (this.state) {
                case 0:
                    switch (c) {
                        case '\t':
                        case '\n':
                        case '\r':
                        case ' ':
                        case ',':
                            break;
                        case '\"':
                        case '\'':
                            this.state = 2;
                            this.quoteChar = c;
                            break;
                        case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                            emitOptionToken(list, arrayList);
                            return;
                        default:
                            stringBuffer.append(c);
                            this.state = 1;
                            break;
                    }
                case 1:
                    switch (c) {
                        case ',':
                            arrayList.add(stringBuffer.toString().trim());
                            stringBuffer.setLength(0);
                            this.state = 0;
                            break;
                        case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                            arrayList.add(stringBuffer.toString().trim());
                            emitOptionToken(list, arrayList);
                            return;
                        default:
                            stringBuffer.append(c);
                            break;
                    }
                case 2:
                    if (c != this.quoteChar) {
                        if (c != CoreConstants.ESCAPE_CHAR) {
                            stringBuffer.append(c);
                            break;
                        } else {
                            escape(String.valueOf(this.quoteChar), stringBuffer);
                            break;
                        }
                    }
                    arrayList.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                    this.state = 0;
                    break;
                default:
                    break;
            }
            c = this.pattern.charAt(this.tokenStream.pointer);
            TokenStream tokenStream = this.tokenStream;
            tokenStream.pointer++;
        }
        if (c != CoreConstants.CURLY_RIGHT) {
            throw new ScanException("Unexpected end of pattern string in OptionTokenizer");
        } else if (this.state == 0) {
            emitOptionToken(list, arrayList);
        } else if (this.state == 1) {
            arrayList.add(stringBuffer.toString().trim());
            emitOptionToken(list, arrayList);
        } else {
            throw new ScanException("Unexpected end of pattern string in OptionTokenizer");
        }
    }
}
