package com.digits.sdk.android;

/* compiled from: FailureScribeService */
class bd implements ao {
    /* renamed from: a */
    private final am f13225a;

    bd(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13225a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13225a.mo3648a(DigitsScribeConstants.f13103a.d("fallback").e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
        this.f13225a.mo3648a(DigitsScribeConstants.f13103a.d("fallback").e(element.toString()).f("click").a());
    }

    /* renamed from: c */
    public void mo3670c() {
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
    }
}
