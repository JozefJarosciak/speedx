package io.rong.push.common;

public class RongException extends Exception {
    private int errorCode;
    private Exception innerException;

    public RongException(String str) {
        super(str);
    }

    public RongException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public RongException(String str, Exception exception) {
        super(str);
        this.innerException = exception;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public Exception getInnerException() {
        return this.innerException;
    }
}
