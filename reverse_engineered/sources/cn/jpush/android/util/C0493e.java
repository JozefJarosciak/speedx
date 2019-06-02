package cn.jpush.android.util;

import java.io.UnsupportedEncodingException;

/* renamed from: cn.jpush.android.util.e */
public class C0493e {
    /* renamed from: a */
    static final /* synthetic */ boolean f1019a;
    /* renamed from: z */
    private static final String[] f1020z;

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
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "\u0013!Jyj\u00103Kt>E";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0063;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 8;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "$\u0013\u0003\u0018[2\tg";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f1020z = r6;
        r0 = cn.jpush.android.util.C0493e.class;
        r0 = r0.desiredAssertionStatus();
        if (r0 != 0) goto L_0x0061;
    L_0x0052:
        f1019a = r4;
        return;
    L_0x0055:
        r11 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0021;
    L_0x0058:
        r11 = 64;
        goto L_0x0021;
    L_0x005b:
        r11 = 46;
        goto L_0x0021;
    L_0x005e:
        r11 = 89;
        goto L_0x0021;
    L_0x0061:
        r4 = r1;
        goto L_0x0052;
    L_0x0063:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.e.<clinit>():void");
    }

    private C0493e() {
    }

    /* renamed from: a */
    public static String m1749a(byte[] bArr, int i) {
        int i2 = 1;
        try {
            int length = bArr.length;
            C0496h c0496h = new C0496h(i, null);
            int i3 = (length / 3) * 4;
            if (!c0496h.f1032d) {
                switch (length % 3) {
                    case 0:
                        break;
                    case 1:
                        i3 += 2;
                        break;
                    case 2:
                        i3 += 3;
                        break;
                    default:
                        break;
                }
            } else if (length % 3 > 0) {
                i3 += 4;
            }
            if (!c0496h.f1033e || length <= 0) {
                i2 = i3;
            } else {
                int i4 = ((length - 1) / 57) + 1;
                if (c0496h.f1034f) {
                    i2 = 2;
                }
                i2 = (i2 * i4) + i3;
            }
            c0496h.a = new byte[i2];
            c0496h.m1752a(bArr, 0, length, true);
            if (f1019a || c0496h.b == i2) {
                return new String(c0496h.a, f1020z[1]);
            }
            throw new AssertionError();
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public static byte[] m1750a(String str, int i) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        C0495g c0495g = new C0495g(2, new byte[((length * 3) / 4)]);
        if (!c0495g.m1751a(bytes, 0, length, true)) {
            throw new IllegalArgumentException(f1020z[0]);
        } else if (c0495g.b == c0495g.a.length) {
            return c0495g.a;
        } else {
            Object obj = new byte[c0495g.b];
            System.arraycopy(c0495g.a, 0, obj, 0, c0495g.b);
            return obj;
        }
    }
}
