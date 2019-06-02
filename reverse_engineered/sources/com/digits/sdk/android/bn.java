package com.digits.sdk.android;

import io.fabric.sdk.android.services.concurrency.AsyncTask;

/* compiled from: PhoneNumberTask */
class bn extends AsyncTask<Void, Void, bi> {
    /* renamed from: a */
    private final C2908a f13289a;
    /* renamed from: d */
    private final bo f13290d;
    /* renamed from: e */
    private final String f13291e;

    /* compiled from: PhoneNumberTask */
    /* renamed from: com.digits.sdk.android.bn$a */
    interface C2908a {
        /* renamed from: a */
        void mo3674a(bi biVar);
    }

    protected bn(bo boVar, C2908a c2908a) {
        if (boVar == null) {
            throw new NullPointerException("phoneNumberUtils can't be null");
        }
        this.f13289a = c2908a;
        this.f13290d = boVar;
        this.f13291e = "";
    }

    protected bn(bo boVar, String str, C2908a c2908a) {
        if (boVar == null) {
            throw new NullPointerException("phoneNumberUtils can't be null");
        }
        this.f13289a = c2908a;
        this.f13290d = boVar;
        this.f13291e = str;
    }

    /* renamed from: a */
    protected bi m14134a(Void... voidArr) {
        return this.f13290d.m14146a(this.f13291e);
    }

    /* renamed from: a */
    protected void m14136a(bi biVar) {
        if (this.f13289a != null) {
            this.f13289a.mo3674a(biVar);
        }
    }
}
