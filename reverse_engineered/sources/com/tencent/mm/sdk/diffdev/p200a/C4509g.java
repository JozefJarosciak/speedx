package com.tencent.mm.sdk.diffdev.p200a;

import org.apache.http.HttpStatus;

/* renamed from: com.tencent.mm.sdk.diffdev.a.g */
public enum C4509g {
    UUID_EXPIRED(HttpStatus.SC_PAYMENT_REQUIRED),
    UUID_CANCELED(HttpStatus.SC_FORBIDDEN),
    UUID_SCANED(404),
    UUID_CONFIRM(HttpStatus.SC_METHOD_NOT_ALLOWED),
    UUID_KEEP_CONNECT(HttpStatus.SC_REQUEST_TIMEOUT),
    UUID_ERROR(500);
    
    private int code;

    private C4509g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    public final String toString() {
        return "UUIDStatusCode:" + this.code;
    }
}
