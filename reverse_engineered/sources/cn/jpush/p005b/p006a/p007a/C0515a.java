package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.a */
public final class C0515a extends C0514i {
    /* renamed from: z */
    private static final String[] f1073z;
    /* renamed from: a */
    int f1074a;
    /* renamed from: b */
    int f1075b;
    /* renamed from: c */
    int f1076c;
    /* renamed from: d */
    long f1077d;

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
        r4 = 3;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 5;
        r6 = new java.lang.String[r0];
        r5 = "\u00023;\u0018 ^)";
        r0 = -1;
        r7 = r6;
        r8 = r6;
        r6 = r1;
    L_0x000d:
        r5 = r5.toCharArray();
        r9 = r5.length;
        if (r9 > r2) goto L_0x0075;
    L_0x0014:
        r10 = r1;
    L_0x0015:
        r11 = r5;
        r12 = r10;
        r15 = r9;
        r9 = r5;
        r5 = r15;
    L_0x001a:
        r14 = r9[r10];
        r13 = r12 % 5;
        switch(r13) {
            case 0: goto L_0x0069;
            case 1: goto L_0x006c;
            case 2: goto L_0x006f;
            case 3: goto L_0x0072;
            default: goto L_0x0021;
        };
    L_0x0021:
        r13 = 69;
    L_0x0023:
        r13 = r13 ^ r14;
        r13 = (char) r13;
        r9[r10] = r13;
        r10 = r12 + 1;
        if (r5 != 0) goto L_0x002f;
    L_0x002b:
        r9 = r11;
        r12 = r10;
        r10 = r5;
        goto L_0x001a;
    L_0x002f:
        r9 = r5;
        r5 = r11;
    L_0x0031:
        if (r9 > r10) goto L_0x0015;
    L_0x0033:
        r9 = new java.lang.String;
        r9.<init>(r5);
        r5 = r9.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0051;
            case 2: goto L_0x005a;
            case 3: goto L_0x0064;
            default: goto L_0x003f;
        };
    L_0x003f:
        r7[r6] = r5;
        r0 = "\u00023;\u0018$Zf;V";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "\u000e>h";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "\u00023;\u0018,Cvr";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "uR+\u0007\u000bAa%\r)s3eL7Kb=\t6ZP'\u0001(O},V";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f1073z = r8;
        return;
    L_0x0069:
        r13 = 46;
        goto L_0x0023;
    L_0x006c:
        r13 = 19;
        goto L_0x0023;
    L_0x006f:
        r13 = 72;
        goto L_0x0023;
    L_0x0072:
        r13 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0023;
    L_0x0075:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.a.<clinit>():void");
    }

    public C0515a(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: a */
    public final int mo2234a() {
        return this.f1074a;
    }

    /* renamed from: b */
    public final void mo2231b() {
        super.mo2231b();
        m1819a(this.f1074a);
        m1819a(this.f1075b);
        m1819a(this.f1076c);
        m1820a(this.f1077d);
    }

    /* renamed from: c */
    public final void mo2232c() {
        super.mo2232c();
        ByteBuffer byteBuffer = this.f;
        this.f1074a = C0497j.m1757c(byteBuffer, this).byteValue();
        this.f1075b = C0497j.m1757c(byteBuffer, this).byteValue();
        this.f1076c = C0497j.m1757c(byteBuffer, this).byteValue();
        this.f1077d = C0497j.m1758d(byteBuffer, this);
    }

    public final String toString() {
        return new StringBuilder(f1073z[4]).append(this.f1074a).append(f1073z[0]).append(this.f1075b).append(f1073z[1]).append(this.f1076c).append(f1073z[3]).append(this.f1077d).append(f1073z[2]).append(super.toString()).toString();
    }
}
