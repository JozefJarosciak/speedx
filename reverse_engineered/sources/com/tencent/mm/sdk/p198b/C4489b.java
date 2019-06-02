package com.tencent.mm.sdk.p198b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;

/* renamed from: com.tencent.mm.sdk.b.b */
public final class C4489b {
    private static C4488a aG;
    private static C4488a aH;
    private static final String aI;
    private static int level = 6;

    /* renamed from: com.tencent.mm.sdk.b.b$a */
    public interface C4488a {
        /* renamed from: f */
        void mo6078f(String str, String str2);

        /* renamed from: g */
        void mo6079g(String str, String str2);

        int getLogLevel();

        /* renamed from: h */
        void mo6081h(String str, String str2);

        /* renamed from: i */
        void mo6082i(String str, String str2);
    }

    static {
        C4488a c4490c = new C4490c();
        aG = c4490c;
        aH = c4490c;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("VERSION.RELEASE:[" + VERSION.RELEASE);
            stringBuilder.append("] VERSION.CODENAME:[" + VERSION.CODENAME);
            stringBuilder.append("] VERSION.INCREMENTAL:[" + VERSION.INCREMENTAL);
            stringBuilder.append("] BOARD:[" + Build.BOARD);
            stringBuilder.append("] DEVICE:[" + Build.DEVICE);
            stringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
            stringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
            stringBuilder.append("] HOST:[" + Build.HOST);
            stringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
            stringBuilder.append("] MODEL:[" + Build.MODEL);
            stringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
            stringBuilder.append("] TAGS:[" + Build.TAGS);
            stringBuilder.append("] TYPE:[" + Build.TYPE);
            stringBuilder.append("] USER:[" + Build.USER + "]");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        aI = stringBuilder.toString();
    }

    /* renamed from: a */
    public static void m17826a(String str, String str2, Object... objArr) {
        if (aH != null && aH.getLogLevel() <= 4) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            C4488a c4488a = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4488a.mo6082i(str, format);
        }
    }

    /* renamed from: b */
    public static void m17827b(String str, String str2) {
        C4489b.m17826a(str, str2, null);
    }

    /* renamed from: c */
    public static void m17828c(String str, String str2) {
        if (aH != null && aH.getLogLevel() <= 3) {
            C4488a c4488a = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4488a.mo6081h(str, str2);
        }
    }

    /* renamed from: d */
    public static void m17829d(String str, String str2) {
        if (aH != null && aH.getLogLevel() <= 2) {
            C4488a c4488a = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4488a.mo6078f(str, str2);
        }
    }

    /* renamed from: e */
    public static void m17830e(String str, String str2) {
        if (aH != null && aH.getLogLevel() <= 1) {
            if (str2 == null) {
                str2 = "";
            }
            C4488a c4488a = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            c4488a.mo6079g(str, str2);
        }
    }
}
