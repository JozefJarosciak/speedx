package cn.jpush.android.util;

import android.content.Context;
import android.util.Log;
import cn.jpush.android.C0448e;
import cn.jpush.android.data.C0433e;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JLogger {
    /* renamed from: a */
    private static final SimpleDateFormat f924a = new SimpleDateFormat(f926z);
    /* renamed from: b */
    private static C0509w f925b = new C0509w();
    /* renamed from: z */
    private static final String f926z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r6 = 11;
        r0 = "r\u0001\u0013\u000e&F5G\u0013o+0\"MffB\u0019\u0004";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0028;
    L_0x000d:
        r3 = r0;
        r4 = r2;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0012:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0048;
            case 2: goto L_0x004b;
            case 3: goto L_0x004e;
            default: goto L_0x0019;
        };
    L_0x0019:
        r5 = r6;
    L_0x001a:
        r5 = r5 ^ r7;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0026;
    L_0x0022:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0012;
    L_0x0026:
        r1 = r0;
        r0 = r3;
    L_0x0028:
        if (r1 > r2) goto L_0x000d;
    L_0x002a:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f926z = r0;
        r0 = new java.text.SimpleDateFormat;
        r1 = f926z;
        r0.<init>(r1);
        f924a = r0;
        r0 = new cn.jpush.android.util.w;
        r0.<init>();
        f925b = r0;
        return;
    L_0x0046:
        r5 = r6;
        goto L_0x001a;
    L_0x0048:
        r5 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x001a;
    L_0x004b:
        r5 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x001a;
    L_0x004e:
        r5 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.JLogger.<clinit>():void");
    }

    /* renamed from: a */
    private static void m1546a(int i, String str, String str2) {
        String str3 = "V";
        switch (i) {
            case 1:
                if (C0448e.f749a) {
                    Log.v(str, str2);
                }
                str3 = "V";
                break;
            case 2:
                if (C0448e.f749a) {
                    Log.d(str, str2);
                }
                str3 = "D";
                break;
            case 4:
                if (C0448e.f749a) {
                    Log.i(str, str2);
                }
                str3 = "I";
                break;
            case 8:
                if (C0448e.f749a) {
                    Log.w(str, str2);
                }
                str3 = "W";
                break;
            case 16:
                if (C0448e.f749a) {
                    Log.e(str, str2);
                }
                str3 = "E";
                break;
        }
        if (f925b != null && f925b.f1053b && (f925b.f1052a & i) != 0) {
            C0433e c0433e = new C0433e(i, str3, str, str2, f924a.format(new Date()));
            if (f925b != null) {
                f925b.m1817a(c0433e);
            }
        }
    }

    /* renamed from: d */
    public static void m1547d(String str, String str2) {
        m1546a(2, str, str2);
    }

    /* renamed from: e */
    public static void m1548e(String str, String str2) {
        m1546a(16, str, str2);
    }

    /* renamed from: i */
    public static void m1549i(String str, String str2) {
        m1546a(4, str, str2);
    }

    public static void parseModalJson(String str, Context context) {
        if (context != null && f925b != null) {
            f925b.m1816a(context, str);
        }
    }

    public static void reportByHeartbeats() {
        if (f925b != null) {
            f925b.m1815a();
        }
    }

    /* renamed from: v */
    public static void m1550v(String str, String str2) {
        m1546a(1, str, str2);
    }

    /* renamed from: w */
    public static void m1551w(String str, String str2) {
        m1546a(8, str, str2);
    }
}
