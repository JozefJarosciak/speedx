package com.pingplusplus.android;

import android.util.Log;

public class PingppLog {
    public static boolean DEBUG = false;
    /* renamed from: a */
    private static String f14947a = "PING++";
    /* renamed from: b */
    private static boolean f14948b = false;

    /* renamed from: a */
    public static void m16960a(Exception exception) {
        if (f14948b) {
            if (exception != null) {
                exception.printStackTrace();
            }
        } else if (DEBUG && exception != null && exception.getMessage() != null) {
            Log.d(f14947a, exception.getMessage());
        }
    }

    /* renamed from: a */
    public static void m16961a(String str) {
        if (f14948b) {
            String str2 = "pingpp_debug";
            if (str == null) {
                str = "null";
            }
            Log.d(str2, str);
        }
    }

    /* renamed from: d */
    public static void m16962d(String str) {
        if (DEBUG) {
            String str2 = f14947a;
            if (str == null) {
                str = "null";
            }
            Log.d(str2, str);
        }
    }
}
