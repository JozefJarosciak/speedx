package com.facebook.stetho.common;

import java.util.Locale;

public class LogUtil {
    private static final String TAG = "stetho";

    /* renamed from: e */
    public static void m15277e(String str, Object... objArr) {
        m15276e(format(str, objArr));
    }

    /* renamed from: e */
    public static void m15279e(Throwable th, String str, Object... objArr) {
        m15278e(th, format(str, objArr));
    }

    /* renamed from: e */
    public static void m15276e(String str) {
        if (isLoggable(6)) {
            LogRedirector.m15264e(TAG, str);
        }
    }

    /* renamed from: e */
    public static void m15278e(Throwable th, String str) {
        if (isLoggable(6)) {
            LogRedirector.m15265e(TAG, str, th);
        }
    }

    /* renamed from: w */
    public static void m15289w(String str, Object... objArr) {
        m15288w(format(str, objArr));
    }

    /* renamed from: w */
    public static void m15291w(Throwable th, String str, Object... objArr) {
        m15290w(th, format(str, objArr));
    }

    /* renamed from: w */
    public static void m15288w(String str) {
        if (isLoggable(5)) {
            LogRedirector.m15270w(TAG, str);
        }
    }

    /* renamed from: w */
    public static void m15290w(Throwable th, String str) {
        if (isLoggable(5)) {
            LogRedirector.m15271w(TAG, str, th);
        }
    }

    /* renamed from: i */
    public static void m15281i(String str, Object... objArr) {
        m15280i(format(str, objArr));
    }

    /* renamed from: i */
    public static void m15283i(Throwable th, String str, Object... objArr) {
        m15282i(th, format(str, objArr));
    }

    /* renamed from: i */
    public static void m15280i(String str) {
        if (isLoggable(4)) {
            LogRedirector.m15266i(TAG, str);
        }
    }

    /* renamed from: i */
    public static void m15282i(Throwable th, String str) {
        if (isLoggable(4)) {
            LogRedirector.m15267i(TAG, str, th);
        }
    }

    /* renamed from: d */
    public static void m15273d(String str, Object... objArr) {
        m15272d(format(str, objArr));
    }

    /* renamed from: d */
    public static void m15275d(Throwable th, String str, Object... objArr) {
        m15274d(th, format(str, objArr));
    }

    /* renamed from: d */
    public static void m15272d(String str) {
        if (isLoggable(3)) {
            LogRedirector.m15262d(TAG, str);
        }
    }

    /* renamed from: d */
    public static void m15274d(Throwable th, String str) {
        if (isLoggable(3)) {
            LogRedirector.m15263d(TAG, str, th);
        }
    }

    /* renamed from: v */
    public static void m15285v(String str, Object... objArr) {
        m15284v(format(str, objArr));
    }

    /* renamed from: v */
    public static void m15287v(Throwable th, String str, Object... objArr) {
        m15286v(th, format(str, objArr));
    }

    /* renamed from: v */
    public static void m15284v(String str) {
        if (isLoggable(2)) {
            LogRedirector.m15268v(TAG, str);
        }
    }

    /* renamed from: v */
    public static void m15286v(Throwable th, String str) {
        if (isLoggable(2)) {
            LogRedirector.m15269v(TAG, str, th);
        }
    }

    private static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static boolean isLoggable(int i) {
        switch (i) {
            case 5:
            case 6:
                return true;
            default:
                return LogRedirector.isLoggable(TAG, i);
        }
    }
}
