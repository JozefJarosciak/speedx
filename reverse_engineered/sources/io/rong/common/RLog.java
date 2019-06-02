package io.rong.common;

import android.util.Log;

public class RLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    static final String TAG = "RongLog";
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int level = 2;

    public static void setLevel(int i) {
        level = i;
    }

    /* renamed from: v */
    public static int m19423v(String str, String str2) {
        if (level > 2) {
            return 0;
        }
        RFLog.write(TAG, "[ " + str + " ] " + str2, 2);
        return Log.v(TAG, "[ " + str + " ] " + str2);
    }

    /* renamed from: d */
    public static int m19419d(String str, String str2) {
        if (level > 3) {
            return 0;
        }
        RFLog.write(TAG, "[ " + str + " ] " + str2, 3);
        return Log.d(TAG, "[ " + str + " ] " + str2);
    }

    /* renamed from: i */
    public static int m19422i(String str, String str2) {
        if (level > 4) {
            return 0;
        }
        RFLog.write(TAG, "[ " + str + " ] " + str2, 4);
        return Log.i(TAG, "[ " + str + " ] " + str2);
    }

    /* renamed from: w */
    public static int m19424w(String str, String str2) {
        if (level > 5) {
            return 0;
        }
        RFLog.write(TAG, "[ " + str + " ] " + str2, 5);
        return Log.w(TAG, "[ " + str + " ] " + str2);
    }

    /* renamed from: e */
    public static int m19420e(String str, String str2) {
        if (level > 6) {
            return 0;
        }
        RFLog.write(TAG, "[ " + str + " ] " + str2, 6);
        return Log.e(TAG, "[ " + str + " ] " + str2);
    }

    /* renamed from: e */
    public static int m19421e(String str, String str2, Throwable th) {
        if (level > 6) {
            return 0;
        }
        RFLog.write(TAG, "[ " + str + " ] " + str2, 6);
        return Log.e(TAG, "[ " + str + " ] " + str2, th);
    }
}
