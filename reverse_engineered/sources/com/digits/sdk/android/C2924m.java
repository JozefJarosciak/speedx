package com.digits.sdk.android;

/* compiled from: ConfirmationCodeScribeService */
/* renamed from: com.digits.sdk.android.m */
class C2924m implements ao {
    /* renamed from: a */
    private final am f13339a;

    C2924m(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13339a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13339a.mo3648a(DigitsScribeConstants.f13103a.d("signup").e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
        this.f13339a.mo3648a(DigitsScribeConstants.f13103a.d("signup").e("").f("failure").a());
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
        this.f13339a.mo3648a(DigitsScribeConstants.f13103a.d("signup").e(element.toString()).f("click").a());
    }

    /* renamed from: c */
    public void mo3670c() {
        this.f13339a.mo3648a(DigitsScribeConstants.f13103a.d("signup").e("").f("success").a());
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
        this.f13339a.mo3648a(DigitsScribeConstants.f13103a.d("signup").e("").f("error").a());
    }
}
