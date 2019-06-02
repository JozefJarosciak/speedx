package com.digits.sdk.android;

class CouldNotAuthenticateException extends DigitsException {
    public CouldNotAuthenticateException(String str) {
        super(str);
    }

    public CouldNotAuthenticateException(String str, int i, AuthConfig authConfig) {
        super(str, i, authConfig);
    }
}
