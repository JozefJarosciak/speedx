package cn.jpush.p005b.p006a.p007a;

import cn.jpush.p005b.p006a.p009c.C0552b;
import cn.jpush.p005b.p006a.p009c.C0553c;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.e */
public final class C0520e {
    /* renamed from: z */
    private static final String[] f1087z;

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
        r12 = 53;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "LZ\bT|\u0018W\u0005AuK\u0015Q\u0015";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000d:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0014:
        r8 = r1;
    L_0x0015:
        r9 = r3;
        r10 = r8;
        r14 = r7;
        r7 = r3;
        r3 = r14;
    L_0x001a:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0057;
            case 1: goto L_0x005a;
            case 2: goto L_0x005c;
            case 3: goto L_0x005f;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = 16;
    L_0x0023:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002f;
    L_0x002b:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x001a;
    L_0x002f:
        r7 = r3;
        r3 = r9;
    L_0x0031:
        if (r7 > r8) goto L_0x0015;
    L_0x0033:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0052;
            default: goto L_0x003f;
        };
    L_0x003f:
        r5[r4] = r3;
        r0 = "m[\u0017[O[\\VUX\u001d[t\u0018S\u0013G0HT\u000eFyVR\\\\~ZZ\t[t\u0016";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r5[r4] = r3;
        r3 = 2;
        r0 = "HT\u000eFu\\\u0015\u0014Pq\\\u0015Q\u0015";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000d;
    L_0x0052:
        r5[r4] = r3;
        f1087z = r6;
        return;
    L_0x0057:
        r11 = 56;
        goto L_0x0023;
    L_0x005a:
        r11 = r12;
        goto L_0x0023;
    L_0x005c:
        r11 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        goto L_0x0023;
    L_0x005f:
        r11 = r12;
        goto L_0x0023;
    L_0x0061:
        r8 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.e.<clinit>():void");
    }

    /* renamed from: a */
    public static C0514i m1847a(byte[] bArr) {
        new StringBuilder(f1087z[0]).append(C0553c.m1908a(bArr));
        int length = bArr.length - 20;
        Object obj = new byte[20];
        System.arraycopy(bArr, 0, obj, 0, 20);
        Object obj2 = new byte[length];
        System.arraycopy(bArr, 20, obj2, 0, length);
        ByteBuffer wrap = ByteBuffer.wrap(obj2);
        C0521f c0521f = new C0521f(false, obj);
        new StringBuilder(f1087z[2]).append(c0521f.toString());
        switch (c0521f.f1091c) {
            case 0:
                return new C0524l(c0521f, wrap);
            case 1:
                return new C0522j(c0521f, wrap);
            case 3:
                return new C0523k(c0521f, wrap);
            case 10:
                return new C0526n(c0521f, wrap);
            case 19:
                return new C0515a(c0521f, wrap);
            case 25:
                return new C0516b(c0521f, wrap);
            case 100:
                return new C0519d(c0521f, wrap);
            default:
                C0552b.m1906a(f1087z[1]);
                return null;
        }
    }
}
