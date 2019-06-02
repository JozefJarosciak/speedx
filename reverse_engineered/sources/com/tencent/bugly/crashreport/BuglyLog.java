package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.C4402b;
import com.tencent.bugly.proguard.C4478x;

/* compiled from: BUGLY */
public class BuglyLog {
    /* renamed from: v */
    public static void m17256v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C4402b.f15204c) {
            Log.v(str, str2);
        }
        C4478x.m17764a("V", str, str2);
    }

    /* renamed from: d */
    public static void m17252d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C4402b.f15204c) {
            Log.d(str, str2);
        }
        C4478x.m17764a("D", str, str2);
    }

    /* renamed from: i */
    public static void m17255i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C4402b.f15204c) {
            Log.i(str, str2);
        }
        C4478x.m17764a("I", str, str2);
    }

    /* renamed from: w */
    public static void m17257w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C4402b.f15204c) {
            Log.w(str, str2);
        }
        C4478x.m17764a("W", str, str2);
    }

    /* renamed from: e */
    public static void m17253e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C4402b.f15204c) {
            Log.e(str, str2);
        }
        C4478x.m17764a("E", str, str2);
    }

    /* renamed from: e */
    public static void m17254e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C4402b.f15204c) {
            Log.e(str, str2, th);
        }
        C4478x.m17765a("E", str, th);
    }

    public static void setCache(int i) {
        C4478x.m17762a(i);
    }
}
