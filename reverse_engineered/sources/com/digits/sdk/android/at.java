package com.digits.sdk.android;

import android.os.Build.VERSION;

/* compiled from: DigitsUserAgent */
class at {
    /* renamed from: a */
    private final String f13197a;
    /* renamed from: b */
    private final String f13198b;
    /* renamed from: c */
    private final String f13199c;

    at() {
        this(aa.a().c(), VERSION.RELEASE, aa.a().q().getApplicationContext().getApplicationInfo().loadLabel(aa.a().q().getApplicationContext().getPackageManager()).toString());
    }

    at(String str, String str2, String str3) {
        this.f13197a = str;
        this.f13199c = str3;
        this.f13198b = str2;
    }

    public String toString() {
        return "Digits/" + this.f13197a + " ( " + this.f13199c + "; Android " + this.f13198b + ")";
    }
}
