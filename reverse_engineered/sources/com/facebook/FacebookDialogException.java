package com.facebook;

import com.alipay.sdk.util.C0880h;

public class FacebookDialogException extends FacebookException {
    private int errorCode;
    private String failingUrl;

    public FacebookDialogException(String str, int i, String str2) {
        super(str);
        this.errorCode = i;
        this.failingUrl = str2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getFailingUrl() {
        return this.failingUrl;
    }

    public final String toString() {
        return "{FacebookDialogException: " + "errorCode: " + getErrorCode() + ", message: " + getMessage() + ", url: " + getFailingUrl() + C0880h.f2222d;
    }
}
