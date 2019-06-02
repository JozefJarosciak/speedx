package com.digits.sdk.android;

import java.lang.ref.WeakReference;

/* compiled from: WeakAuthCallback */
class ca implements C2914f {
    /* renamed from: a */
    private final WeakReference<C2914f> f13311a;
    /* renamed from: b */
    private final ao f13312b;

    /* renamed from: a */
    public void mo3684a(ap apVar, String str) {
        C2914f c2914f = (C2914f) this.f13311a.get();
        if (c2914f != null) {
            this.f13312b.mo3670c();
            c2914f.mo3684a(apVar, str);
        }
    }

    /* renamed from: a */
    public void mo3683a(DigitsException digitsException) {
        C2914f c2914f = (C2914f) this.f13311a.get();
        if (c2914f != null) {
            this.f13312b.mo3669b();
            c2914f.mo3683a(digitsException);
        }
    }
}
