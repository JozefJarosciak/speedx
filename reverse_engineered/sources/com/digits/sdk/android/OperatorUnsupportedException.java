package com.digits.sdk.android;

public class OperatorUnsupportedException extends DigitsException {
    public OperatorUnsupportedException(String str, int i, AuthConfig authConfig) {
        super(str, i, authConfig);
    }
}
