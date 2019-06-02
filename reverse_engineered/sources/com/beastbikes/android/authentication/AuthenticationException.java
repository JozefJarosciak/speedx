package com.beastbikes.android.authentication;

public class AuthenticationException extends Exception {
    private static final long serialVersionUID = -3821984500386448955L;
    private final int errorNumber;

    public AuthenticationException(int i) {
        this.errorNumber = i;
    }

    public AuthenticationException(int i, String str, Throwable th) {
        super(str, th);
        this.errorNumber = i;
    }

    public AuthenticationException(int i, String str) {
        super(str);
        this.errorNumber = i;
    }

    public AuthenticationException(int i, Throwable th) {
        super(th);
        this.errorNumber = i;
    }

    public int getErrorNumber() {
        return this.errorNumber;
    }
}
