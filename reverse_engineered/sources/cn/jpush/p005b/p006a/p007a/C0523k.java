package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import cn.jpush.p005b.p006a.p009c.C0551a;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.k */
public final class C0523k extends C0514i {
    /* renamed from: z */
    private static final String[] f1101z;
    /* renamed from: a */
    int f1102a;
    /* renamed from: b */
    long f1103b;
    /* renamed from: c */
    String f1104c;

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
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "RS(";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006b;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r4;
        r11 = r9;
        r14 = r8;
        r8 = r4;
        r4 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x005f;
            case 1: goto L_0x0062;
            case 2: goto L_0x0065;
            case 3: goto L_0x0068;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x0019;
    L_0x002e:
        r8 = r4;
        r4 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            case 2: goto L_0x005a;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r5] = r4;
        r0 = "^^e\u0015\u000b1\u0011f\u0012\t\u001c\n2";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = ")3m\u0015\u001f\u0013\u0019m6\u0019\u0001\u0016UFAR\u0013{\u00018\u000b\u000em\\";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "^^e\u0015\u000b;\u001a2";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f1101z = r7;
        return;
    L_0x005f:
        r12 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0022;
    L_0x0062:
        r12 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x0022;
    L_0x0065:
        r12 = 8;
        goto L_0x0022;
    L_0x0068:
        r12 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x0022;
    L_0x006b:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.k.<clinit>():void");
    }

    public C0523k(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: a */
    public final int mo2234a() {
        return this.f1102a;
    }

    /* renamed from: b */
    public final void mo2231b() {
        super.mo2231b();
        m1819a(this.f1102a);
        m1820a(this.f1103b);
        m1821a(this.f1104c);
    }

    /* renamed from: c */
    public final void mo2232c() {
        super.mo2232c();
        ByteBuffer byteBuffer = this.f;
        this.f1102a = C0497j.m1757c(byteBuffer, this).byteValue();
        this.f1103b = C0497j.m1758d(byteBuffer, this);
        this.f1104c = C0551a.m1899a(byteBuffer, this);
    }

    /* renamed from: d */
    public final long m1858d() {
        return this.f1103b;
    }

    /* renamed from: h */
    public final String m1859h() {
        return this.f1104c;
    }

    public final String toString() {
        return new StringBuilder(f1101z[2]).append(this.f1102a).append(f1101z[3]).append(this.f1103b).append(f1101z[1]).append(this.f1104c).append(f1101z[0]).append(super.toString()).toString();
    }
}
