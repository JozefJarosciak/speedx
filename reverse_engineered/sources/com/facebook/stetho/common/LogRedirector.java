package com.facebook.stetho.common;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class LogRedirector {
    private static volatile Logger sLogger;

    public interface Logger {
        boolean isLoggable(String str, int i);

        void log(int i, String str, String str2);
    }

    public static void setLogger(Logger logger) {
        Util.throwIfNull(logger);
        Util.throwIfNotNull(sLogger);
        sLogger = logger;
    }

    /* renamed from: e */
    public static void m15265e(String str, String str2, Throwable th) {
        m15264e(str, str2 + "\n" + formatThrowable(th));
    }

    /* renamed from: e */
    public static void m15264e(String str, String str2) {
        log(6, str, str2);
    }

    /* renamed from: w */
    public static void m15271w(String str, String str2, Throwable th) {
        m15270w(str, str2 + "\n" + formatThrowable(th));
    }

    /* renamed from: w */
    public static void m15270w(String str, String str2) {
        log(5, str, str2);
    }

    /* renamed from: i */
    public static void m15267i(String str, String str2, Throwable th) {
        m15266i(str, str2 + "\n" + formatThrowable(th));
    }

    /* renamed from: i */
    public static void m15266i(String str, String str2) {
        log(4, str, str2);
    }

    /* renamed from: d */
    public static void m15263d(String str, String str2, Throwable th) {
        m15262d(str, str2 + "\n" + formatThrowable(th));
    }

    /* renamed from: d */
    public static void m15262d(String str, String str2) {
        log(3, str, str2);
    }

    /* renamed from: v */
    public static void m15269v(String str, String str2, Throwable th) {
        m15268v(str, str2 + "\n" + formatThrowable(th));
    }

    /* renamed from: v */
    public static void m15268v(String str, String str2) {
        log(2, str, str2);
    }

    private static String formatThrowable(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace();
        printWriter.flush();
        return stringWriter.toString();
    }

    private static void log(int i, String str, String str2) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.log(i, str, str2);
        } else {
            Log.println(i, str, str2);
        }
    }

    public static boolean isLoggable(String str, int i) {
        Logger logger = sLogger;
        if (logger != null) {
            return logger.isLoggable(str, i);
        }
        return Log.isLoggable(str, i);
    }
}
