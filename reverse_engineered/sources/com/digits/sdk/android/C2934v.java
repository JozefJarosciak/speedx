package com.digits.sdk.android;

/* compiled from: ContactsScribeService */
/* renamed from: com.digits.sdk.android.v */
class C2934v implements ao {
    /* renamed from: a */
    private final am f13349a;

    C2934v(am amVar) {
        if (amVar == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f13349a = amVar;
    }

    /* renamed from: a */
    public void mo3666a() {
        this.f13349a.mo3648a(DigitsScribeConstants.f13103a.d("contacts").e("").f("impression").a());
    }

    /* renamed from: b */
    public void mo3669b() {
    }

    /* renamed from: a */
    public void mo3668a(Element element) {
        this.f13349a.mo3648a(DigitsScribeConstants.f13103a.d("contacts").e(element.toString()).f("click").a());
    }

    /* renamed from: c */
    public void mo3670c() {
    }

    /* renamed from: a */
    public void mo3667a(DigitsException digitsException) {
        this.f13349a.mo3648a(DigitsScribeConstants.f13103a.d("contacts").e("").f("error").a());
    }
}
