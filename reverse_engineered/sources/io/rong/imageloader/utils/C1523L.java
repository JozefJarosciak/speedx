package io.rong.imageloader.utils;

import android.util.Log;
import io.rong.imageloader.core.ImageLoader;

/* renamed from: io.rong.imageloader.utils.L */
public final class C1523L {
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    private static volatile boolean writeDebugLogs = false;
    private static volatile boolean writeLogs = true;

    private C1523L() {
    }

    @Deprecated
    public static void enableLogging() {
        C1523L.writeLogs(true);
    }

    @Deprecated
    public static void disableLogging() {
        C1523L.writeLogs(false);
    }

    public static void writeDebugLogs(boolean z) {
        writeDebugLogs = z;
    }

    public static void writeLogs(boolean z) {
        writeLogs = z;
    }

    /* renamed from: d */
    public static void m8374d(String str, Object... objArr) {
        if (writeDebugLogs) {
            C1523L.log(3, null, str, objArr);
        }
    }

    /* renamed from: i */
    public static void m8378i(String str, Object... objArr) {
        C1523L.log(4, null, str, objArr);
    }

    /* renamed from: w */
    public static void m8379w(String str, Object... objArr) {
        C1523L.log(5, null, str, objArr);
    }

    /* renamed from: e */
    public static void m8376e(Throwable th) {
        C1523L.log(6, th, null, new Object[0]);
    }

    /* renamed from: e */
    public static void m8375e(String str, Object... objArr) {
        C1523L.log(6, null, str, objArr);
    }

    /* renamed from: e */
    public static void m8377e(Throwable th, String str, Object... objArr) {
        C1523L.log(6, th, str, objArr);
    }

    private static void log(int i, Throwable th, String str, Object... objArr) {
        if (writeLogs) {
            String format;
            if (objArr.length > 0) {
                format = String.format(str, objArr);
            } else {
                format = str;
            }
            if (th != null) {
                if (format == null) {
                    format = th.getMessage();
                }
                String stackTraceString = Log.getStackTraceString(th);
                format = String.format(LOG_FORMAT, new Object[]{format, stackTraceString});
            }
            Log.println(i, ImageLoader.TAG, format);
        }
    }
}
