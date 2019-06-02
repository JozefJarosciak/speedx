package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.w */
public final class C4475w {
    /* renamed from: a */
    public static String f15774a = "CrashReport";
    /* renamed from: b */
    public static boolean f15775b = false;
    /* renamed from: c */
    private static String f15776c = "CrashReportInfo";

    /* renamed from: a */
    private static boolean m17745a(int i, String str, Object... objArr) {
        if (!f15775b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        switch (i) {
            case 0:
                Log.i(f15774a, str);
                return true;
            case 1:
                Log.d(f15774a, str);
                return true;
            case 2:
                Log.w(f15774a, str);
                return true;
            case 3:
                Log.e(f15774a, str);
                return true;
            case 5:
                Log.i(f15776c, str);
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public static boolean m17747a(String str, Object... objArr) {
        return C4475w.m17745a(0, str, objArr);
    }

    /* renamed from: b */
    public static boolean m17749b(String str, Object... objArr) {
        return C4475w.m17745a(5, str, objArr);
    }

    /* renamed from: c */
    public static boolean m17751c(String str, Object... objArr) {
        return C4475w.m17745a(1, str, objArr);
    }

    /* renamed from: a */
    public static boolean m17746a(Class cls, String str, Object... objArr) {
        return C4475w.m17745a(1, String.format(Locale.US, "[%s] %s", new Object[]{cls.getSimpleName(), str}), objArr);
    }

    /* renamed from: d */
    public static boolean m17752d(String str, Object... objArr) {
        return C4475w.m17745a(2, str, objArr);
    }

    /* renamed from: a */
    public static boolean m17748a(Throwable th) {
        return !f15775b ? false : C4475w.m17745a(2, C4479y.m17781a(th), new Object[0]);
    }

    /* renamed from: e */
    public static boolean m17753e(String str, Object... objArr) {
        return C4475w.m17745a(3, str, objArr);
    }

    /* renamed from: b */
    public static boolean m17750b(Throwable th) {
        return !f15775b ? false : C4475w.m17745a(3, C4479y.m17781a(th), new Object[0]);
    }
}
