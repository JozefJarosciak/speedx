package com.digits.sdk.android;

import com.alipay.sdk.app.statistic.C0825c;

/* compiled from: PhoneNumberScribeService */
class bm implements ao {
    /* renamed from: a */
    private final am f13288a;

    bm(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13288a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13288a.mo3648a(DigitsScribeConstants.f13103a.d(C0825c.f1954d).e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
        this.f13288a.mo3648a(DigitsScribeConstants.f13103a.d(C0825c.f1954d).e("").f("failure").a());
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
        this.f13288a.mo3648a(DigitsScribeConstants.f13103a.d(C0825c.f1954d).e(element.getElement()).f("click").a());
    }

    /* renamed from: c */
    public void mo3670c() {
        this.f13288a.mo3648a(DigitsScribeConstants.f13103a.d(C0825c.f1954d).e("").f("success").a());
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
        this.f13288a.mo3648a(DigitsScribeConstants.f13103a.d(C0825c.f1954d).e("").f("error").a());
    }
}
