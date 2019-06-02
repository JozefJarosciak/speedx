package com.umeng.onlineconfig;

import android.util.Log;

public class OnlineConfigLog {
    public static boolean LOG = false;

    /* renamed from: i */
    public static void m18732i(String str, String str2) {
        if (LOG) {
            Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m18733i(String str, String str2, Exception exception) {
        if (LOG) {
            Log.i(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    /* renamed from: e */
    public static void m18730e(String str, String str2) {
        if (LOG) {
            Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m18731e(String str, String str2, Exception exception) {
        if (LOG) {
            Log.e(str, exception.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                Log.e(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }

    /* renamed from: d */
    public static void m18728d(String str, String str2) {
        if (LOG) {
            Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m18729d(String str, String str2, Exception exception) {
        if (LOG) {
            Log.d(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    /* renamed from: v */
    public static void m18734v(String str, String str2) {
        if (LOG) {
            Log.v(str, str2);
        }
    }

    /* renamed from: v */
    public static void m18735v(String str, String str2, Exception exception) {
        if (LOG) {
            Log.v(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    /* renamed from: w */
    public static void m18736w(String str, String str2) {
        if (LOG) {
            Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m18737w(String str, String str2, Exception exception) {
        if (LOG) {
            Log.w(str, exception.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                Log.w(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }
}
