package com.digits.sdk.android;

/* compiled from: LoginCodeScribeService */
class bg implements ao {
    /* renamed from: a */
    private final am f13254a;

    bg(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13254a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13254a.mo3648a(DigitsScribeConstants.f13103a.d("login").e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
        this.f13254a.mo3648a(DigitsScribeConstants.f13103a.d("login").e("").f("failure").a());
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
        this.f13254a.mo3648a(DigitsScribeConstants.f13103a.d("login").e(element.toString()).f("click").a());
    }

    /* renamed from: c */
    public void mo3670c() {
        this.f13254a.mo3648a(DigitsScribeConstants.f13103a.d("login").e("").f("success").a());
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
        this.f13254a.mo3648a(DigitsScribeConstants.f13103a.d("login").e("").f("error").a());
    }
}
