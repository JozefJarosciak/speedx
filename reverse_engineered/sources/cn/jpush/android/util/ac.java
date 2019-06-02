package cn.jpush.android.util;

import cn.jpush.android.C0448e;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public final class ac {
    /* renamed from: a */
    private static C0488t f942a = new C0501n();
    /* renamed from: b */
    private static final SimpleDateFormat f943b = new SimpleDateFormat(f947z[2]);
    /* renamed from: c */
    private static ArrayList<String> f944c = new ArrayList();
    /* renamed from: d */
    private static boolean f945d = false;
    /* renamed from: e */
    private static boolean f946e = false;
    /* renamed from: z */
    private static final String[] f947z;

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
        r5 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "B1";
        r0 = -1;
        r6 = r4;
        r7 = r4;
        r4 = r1;
    L_0x000c:
        r3 = r3.toCharArray();
        r8 = r3.length;
        if (r8 > r2) goto L_0x007e;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r3;
        r11 = r9;
        r14 = r8;
        r8 = r3;
        r3 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x0072;
            case 1: goto L_0x0075;
            case 2: goto L_0x0078;
            case 3: goto L_0x007b;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 71;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r3 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r3;
        goto L_0x0019;
    L_0x002e:
        r8 = r3;
        r3 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r3);
        r3 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r4] = r3;
        r0 = "UA\u001f]/";
        r3 = r0;
        r4 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r4] = r3;
        r0 = "R\\DJ#@Y\"\u0014*r+\u0019]\u0018LB9";
        r3 = r0;
        r4 = r5;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r4] = r3;
        f947z = r7;
        r0 = new cn.jpush.android.util.n;
        r0.<init>();
        f942a = r0;
        r0 = new java.text.SimpleDateFormat;
        r2 = f947z;
        r2 = r2[r5];
        r0.<init>(r2);
        f943b = r0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        f944c = r0;
        f945d = r1;
        f946e = r1;
        return;
    L_0x0072:
        r12 = 31;
        goto L_0x0022;
    L_0x0075:
        r12 = 17;
        goto L_0x0022;
    L_0x0078:
        r12 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x0022;
    L_0x007b:
        r12 = 46;
        goto L_0x0022;
    L_0x007e:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ac.<clinit>():void");
    }

    /* renamed from: a */
    public static void m1576a() {
    }

    /* renamed from: a */
    static void m1577a(C0488t c0488t) {
        f942a = c0488t;
    }

    /* renamed from: a */
    public static void m1578a(String str, String str2) {
        if (C0448e.f749a && m1580a(2)) {
            f942a.mo2228v(f947z[1], "[" + str + f947z[0] + str2);
        }
    }

    /* renamed from: a */
    public static void m1579a(String str, String str2, Throwable th) {
        if (C0448e.f749a && m1580a(5)) {
            f942a.mo2230w(f947z[1], "[" + str + f947z[0] + str2, th);
        }
    }

    /* renamed from: a */
    private static boolean m1580a(int i) {
        return i >= 3;
    }

    /* renamed from: b */
    public static void m1581b() {
    }

    /* renamed from: b */
    public static void m1582b(String str, String str2) {
        if (C0448e.f749a && m1580a(3)) {
            f942a.mo2224d(f947z[1], "[" + str + f947z[0] + str2);
        }
    }

    /* renamed from: b */
    public static void m1583b(String str, String str2, Throwable th) {
        if (m1580a(6)) {
            f942a.mo2226e(f947z[1], "[" + str + f947z[0] + str2, th);
        }
    }

    /* renamed from: c */
    public static void m1584c() {
    }

    /* renamed from: c */
    public static void m1585c(String str, String str2) {
        if (C0448e.f749a && m1580a(4)) {
            f942a.mo2227i(f947z[1], "[" + str + f947z[0] + str2);
        }
    }

    /* renamed from: d */
    public static void m1586d() {
    }

    /* renamed from: d */
    public static void m1587d(String str, String str2) {
        if (m1580a(5)) {
            f942a.mo2229w(f947z[1], "[" + str + f947z[0] + str2);
        }
    }

    /* renamed from: e */
    public static void m1588e() {
    }

    /* renamed from: e */
    public static void m1589e(String str, String str2) {
        if (m1580a(6)) {
            f942a.mo2225e(f947z[1], "[" + str + f947z[0] + str2);
        }
    }

    /* renamed from: f */
    public static void m1590f() {
    }

    /* renamed from: g */
    public static void m1591g() {
    }

    /* renamed from: h */
    public static void m1592h() {
    }

    /* renamed from: i */
    public static void m1593i() {
    }
}
