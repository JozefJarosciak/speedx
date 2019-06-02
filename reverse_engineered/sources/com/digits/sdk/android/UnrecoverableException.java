package com.digits.sdk.android;

public class UnrecoverableException extends DigitsException {
    public UnrecoverableException(String str) {
        super(str);
    }

    public UnrecoverableException(String str, int i, AuthConfig authConfig) {
        super(str, i, authConfig);
    }
}
