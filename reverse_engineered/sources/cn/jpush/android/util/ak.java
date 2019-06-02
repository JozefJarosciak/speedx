package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.jpush.android.C0448e;

public final class ak {
    /* renamed from: a */
    private static SharedPreferences f986a = null;
    /* renamed from: z */
    private static final String[] f987z;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "\u0017/l*B\u00145h>B6\"c;B\u00145b;B74";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0064;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0058;
            case 1: goto L_0x005b;
            case 2: goto L_0x005e;
            case 3: goto L_0x0061;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 39;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "'(c,B<3-1Td)x4Khgd6\u0007\u0017/l*B\u00145h>B6\"c;B\u00145b;B74#1I-3";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "')#2W14evF*#7N ix+B6i}*H\".a=";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f987z = r6;
        r0 = 0;
        f986a = r0;
        return;
    L_0x0058:
        r11 = 68;
        goto L_0x0021;
    L_0x005b:
        r11 = 71;
        goto L_0x0021;
    L_0x005e:
        r11 = 13;
        goto L_0x0021;
    L_0x0061:
        r11 = 88;
        goto L_0x0021;
    L_0x0064:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ak.<clinit>():void");
    }

    /* renamed from: a */
    public static void m1640a(Context context, String str, int i) {
        if (m1644a(context)) {
            m1648b(context);
            Editor edit = f986a.edit();
            edit.putInt(str, i);
            edit.apply();
            return;
        }
        ac.m1586d();
    }

    /* renamed from: a */
    public static void m1641a(Context context, String str, long j) {
        if (m1644a(context)) {
            m1648b(context);
            Editor edit = f986a.edit();
            edit.putLong(str, j);
            edit.apply();
            return;
        }
        ac.m1586d();
    }

    /* renamed from: a */
    public static void m1642a(Context context, String str, String str2) {
        if (m1644a(context)) {
            m1648b(context);
            Editor edit = f986a.edit();
            edit.putString(str, str2);
            edit.apply();
            return;
        }
        ac.m1586d();
    }

    /* renamed from: a */
    public static void m1643a(Context context, String str, boolean z) {
        if (m1644a(context)) {
            m1648b(context);
            Editor edit = f986a.edit();
            edit.putBoolean(str, z);
            edit.apply();
            return;
        }
        ac.m1586d();
    }

    /* renamed from: a */
    private static boolean m1644a(Context context) {
        if (context != null) {
            return true;
        }
        ac.m1587d(f987z[0], f987z[1]);
        return false;
    }

    /* renamed from: b */
    public static int m1645b(Context context, String str, int i) {
        if (m1644a(context)) {
            m1648b(context);
            return f986a.getInt(str, i);
        }
        ac.m1586d();
        return i;
    }

    /* renamed from: b */
    public static long m1646b(Context context, String str, long j) {
        if (m1644a(context)) {
            m1648b(context);
            return f986a.getLong(str, j);
        }
        ac.m1586d();
        return j;
    }

    /* renamed from: b */
    public static String m1647b(Context context, String str, String str2) {
        if (m1644a(context)) {
            m1648b(context);
            return f986a.getString(str, str2);
        }
        ac.m1586d();
        return str2;
    }

    /* renamed from: b */
    private static void m1648b(Context context) {
        if (f986a == null) {
            f986a = context.getSharedPreferences(f987z[2], 4);
        } else if (C0448e.f763o == null && !C0448e.f760l) {
            f986a = context.getSharedPreferences(f987z[2], 4);
        }
    }

    /* renamed from: b */
    public static boolean m1649b(Context context, String str, boolean z) {
        if (m1644a(context)) {
            m1648b(context);
            return f986a.getBoolean(str, z);
        }
        ac.m1586d();
        return z;
    }
}
