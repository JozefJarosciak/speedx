package com.alipay.sdk.app;

import com.alipay.sdk.util.C0880h;

/* renamed from: com.alipay.sdk.app.h */
public final class C0821h {
    /* renamed from: a */
    public static String f1929a;

    /* renamed from: a */
    private static void m3173a(String str) {
        f1929a = str;
    }

    /* renamed from: b */
    private static String m3174b() {
        return f1929a;
    }

    /* renamed from: a */
    public static String m3171a() {
        C0822i a = C0822i.m3178a(C0822i.CANCELED.f1938h);
        return C0821h.m3172a(a.f1938h, a.f1939i, "");
    }

    /* renamed from: c */
    private static String m3175c() {
        C0822i a = C0822i.m3178a(C0822i.DOUBLE_REQUEST.f1938h);
        return C0821h.m3172a(a.f1938h, a.f1939i, "");
    }

    /* renamed from: d */
    private static String m3176d() {
        C0822i a = C0822i.m3178a(C0822i.PARAMS_ERROR.f1938h);
        return C0821h.m3172a(a.f1938h, a.f1939i, "");
    }

    /* renamed from: a */
    public static String m3172a(int i, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resultStatus={").append(i).append("};memo={").append(str).append("};result={").append(str2).append(C0880h.f2222d);
        return stringBuilder.toString();
    }
}
