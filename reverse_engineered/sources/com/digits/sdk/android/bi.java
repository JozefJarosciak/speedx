package com.digits.sdk.android;

import android.text.TextUtils;

/* compiled from: PhoneNumber */
public class bi {
    /* renamed from: a */
    private static final bi f13266a = new bi("", "", "");
    /* renamed from: b */
    private final String f13267b;
    /* renamed from: c */
    private final String f13268c;
    /* renamed from: d */
    private final String f13269d;

    public bi(String str, String str2, String str3) {
        this.f13267b = str;
        this.f13268c = str2;
        this.f13269d = str3;
    }

    /* renamed from: a */
    public static bi m14098a() {
        return f13266a;
    }

    /* renamed from: b */
    public String m14101b() {
        return this.f13269d;
    }

    /* renamed from: c */
    public String m14102c() {
        return this.f13267b;
    }

    /* renamed from: d */
    public String m14103d() {
        return this.f13268c;
    }

    /* renamed from: a */
    protected static boolean m14099a(bi biVar) {
        return (biVar == null || f13266a.equals(biVar) || TextUtils.isEmpty(biVar.m14102c()) || TextUtils.isEmpty(biVar.m14101b()) || TextUtils.isEmpty(biVar.m14103d())) ? false : true;
    }

    /* renamed from: b */
    protected static boolean m14100b(bi biVar) {
        return (biVar == null || f13266a.equals(biVar) || TextUtils.isEmpty(biVar.m14101b()) || TextUtils.isEmpty(biVar.m14103d())) ? false : true;
    }
}
