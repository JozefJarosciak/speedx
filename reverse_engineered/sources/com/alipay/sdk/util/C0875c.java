package com.alipay.sdk.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/* renamed from: com.alipay.sdk.util.c */
public final class C0875c {
    /* renamed from: a */
    public static final boolean f2189a = false;
    /* renamed from: b */
    public static final String f2190b = "mspstd";

    /* renamed from: a */
    private static void m3426a() {
    }

    /* renamed from: b */
    private static void m3428b() {
    }

    /* renamed from: c */
    private static void m3429c() {
    }

    /* renamed from: d */
    private static void m3430d() {
    }

    /* renamed from: e */
    private static void m3431e() {
    }

    /* renamed from: f */
    private static void m3432f() {
    }

    /* renamed from: g */
    private static void m3433g() {
    }

    /* renamed from: a */
    private static void m3427a(Object obj) {
        if (!(obj instanceof Exception)) {
        }
    }

    /* renamed from: h */
    private static void m3434h() {
    }

    /* renamed from: a */
    private static String m3425a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
