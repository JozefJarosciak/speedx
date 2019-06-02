package com.digits.sdk.android;

/* compiled from: PinCodeScribeService */
class br implements ao {
    /* renamed from: a */
    private final am f13307a;

    br(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13307a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13307a.mo3648a(DigitsScribeConstants.f13103a.d("pin").e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
        this.f13307a.mo3648a(DigitsScribeConstants.f13103a.d("pin").e("").f("failure").a());
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
        this.f13307a.mo3648a(DigitsScribeConstants.f13103a.d("pin").e(element.toString()).f("click").a());
    }

    /* renamed from: c */
    public void mo3670c() {
        this.f13307a.mo3648a(DigitsScribeConstants.f13103a.d("pin").e("").f("success").a());
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
        this.f13307a.mo3648a(DigitsScribeConstants.f13103a.d("pin").e("").f("error").a());
    }
}
