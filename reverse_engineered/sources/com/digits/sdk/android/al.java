package com.digits.sdk.android;

import retrofit.RequestInterceptor;
import retrofit.RequestInterceptor.RequestFacade;

/* compiled from: DigitsRequestInterceptor */
class al implements RequestInterceptor {
    /* renamed from: a */
    private final at f13191a;

    public al(at atVar) {
        this.f13191a = atVar;
    }

    public void intercept(RequestFacade requestFacade) {
        requestFacade.addHeader("User-Agent", this.f13191a.toString());
    }
}
