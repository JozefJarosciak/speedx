package ch.qos.logback.core.subst;

import ch.qos.logback.core.CoreConstants;

public class Token {
    public static final Token CURLY_LEFT_TOKEN = new Token(Type.CURLY_LEFT, null);
    public static final Token CURLY_RIGHT_TOKEN = new Token(Type.CURLY_RIGHT, null);
    public static final Token DEFAULT_SEP_TOKEN = new Token(Type.DEFAULT, null);
    public static final Token START_TOKEN = new Token(Type.START, null);
    String payload;
    Type type;

    public enum Type {
        LITERAL,
        START,
        CURLY_LEFT,
        CURLY_RIGHT,
        DEFAULT
    }

    public Token(Type type, String str) {
        this.type = type;
        this.payload = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Token token = (Token) obj;
        if (this.type != token.type) {
            return false;
        }
        if (this.payload != null) {
            if (this.payload.equals(token.payload)) {
                return true;
            }
        } else if (token.payload == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.type != null ? this.type.hashCode() : 0) * 31;
        if (this.payload != null) {
            i = this.payload.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        String str = "Token{type=" + this.type;
        if (this.payload != null) {
            str = str + ", payload='" + this.payload + CoreConstants.SINGLE_QUOTE_CHAR;
        }
        return str + CoreConstants.CURLY_RIGHT;
    }
}
