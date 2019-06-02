package com.facebook;

import com.alipay.sdk.util.C0880h;

public class FacebookServiceException extends FacebookException {
    private static final long serialVersionUID = 1;
    private final FacebookRequestError error;

    public FacebookServiceException(FacebookRequestError facebookRequestError, String str) {
        super(str);
        this.error = facebookRequestError;
    }

    public final FacebookRequestError getRequestError() {
        return this.error;
    }

    public final String toString() {
        return "{FacebookServiceException: " + "httpResponseCode: " + this.error.m14301a() + ", facebookErrorCode: " + this.error.m14302b() + ", facebookErrorType: " + this.error.m14304d() + ", message: " + this.error.m14305e() + C0880h.f2222d;
    }
}
