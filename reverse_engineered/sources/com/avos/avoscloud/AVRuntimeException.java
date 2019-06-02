package com.avos.avoscloud;

public class AVRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public AVRuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public AVRuntimeException(String str) {
        super(str);
    }

    public AVRuntimeException(Throwable th) {
        super(th);
    }
}
