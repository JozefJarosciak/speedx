package com.baidu.lbsapi.auth;

import android.util.Log;
import com.alipay.sdk.util.C0880h;

/* renamed from: com.baidu.lbsapi.auth.a */
class C1017a {
    /* renamed from: a */
    public static boolean f2273a = false;
    /* renamed from: b */
    private static String f2274b = "BaiduApiAuth";

    /* renamed from: a */
    public static String m3588a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    /* renamed from: a */
    public static void m3589a(String str) {
        if (f2273a && Thread.currentThread().getStackTrace().length != 0) {
            Log.d(f2274b, C1017a.m3588a() + C0880h.f2220b + str);
        }
    }

    /* renamed from: b */
    public static void m3590b(String str) {
        if (Thread.currentThread().getStackTrace().length != 0) {
            Log.i(f2274b, str);
        }
    }

    /* renamed from: c */
    public static void m3591c(String str) {
        if (f2273a && Thread.currentThread().getStackTrace().length != 0) {
            Log.e(f2274b, C1017a.m3588a() + C0880h.f2220b + str);
        }
    }
}
