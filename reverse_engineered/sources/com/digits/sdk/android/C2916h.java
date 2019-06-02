package com.digits.sdk.android;

/* compiled from: AuthScribeService */
/* renamed from: com.digits.sdk.android.h */
class C2916h implements ao {
    /* renamed from: a */
    private final am f13313a;

    C2916h(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13313a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13313a.mo3648a(DigitsScribeConstants.f13103a.d("").e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
        this.f13313a.mo3648a(DigitsScribeConstants.f13103a.d("").e("").f("failure").a());
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
    }

    /* renamed from: c */
    public void mo3670c() {
        this.f13313a.mo3648a(DigitsScribeConstants.f13103a.d("").e("").f("logged_in").a());
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
    }
}
