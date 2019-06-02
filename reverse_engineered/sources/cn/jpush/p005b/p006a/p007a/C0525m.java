package cn.jpush.p005b.p006a.p007a;

import cn.jpush.p005b.p006a.p009c.C0551a;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.m */
public final class C0525m extends C0517h {
    /* renamed from: z */
    private static final String[] f1111z;
    /* renamed from: a */
    String f1112a;
    /* renamed from: b */
    String f1113b;

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
        r12 = 88;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = ";l5;,~#:b";
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
            case 0: goto L_0x0056;
            case 1: goto L_0x0059;
            case 2: goto L_0x005c;
            case 3: goto L_0x005f;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = r12;
    L_0x0022:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002e;
    L_0x002a:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x001a;
    L_0x002e:
        r7 = r3;
        r3 = r9;
    L_0x0030:
        if (r7 > r8) goto L_0x0015;
    L_0x0032:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0051;
            default: goto L_0x003e;
        };
    L_0x003e:
        r5[r4] = r3;
        r0 = "7at";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0047:
        r5[r4] = r3;
        r3 = 2;
        r0 = "L\u00185?9{%5+\nr=!=+c\u0011tuxv<$\u0013=nv";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r5[r4] = r3;
        f1111z = r6;
        return;
    L_0x0056:
        r11 = 23;
        goto L_0x0022;
    L_0x0059:
        r11 = 76;
        goto L_0x0022;
    L_0x005c:
        r11 = 84;
        goto L_0x0022;
    L_0x005f:
        r11 = r12;
        goto L_0x0022;
    L_0x0061:
        r8 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.m.<clinit>():void");
    }

    public C0525m(long j, String str, String str2) {
        super(2, 10, j);
        this.f1112a = str;
        this.f1113b = str2;
    }

    /* renamed from: a */
    public final String mo2234a() {
        return this.f1113b;
    }

    /* renamed from: b */
    public final void mo2231b() {
        m1821a(this.f1112a);
        m1821a(this.f1113b);
    }

    /* renamed from: c */
    public final void mo2232c() {
        ByteBuffer byteBuffer = this.f;
        this.f1112a = C0551a.m1905c(byteBuffer);
        this.f1113b = C0551a.m1905c(byteBuffer);
    }

    public final String toString() {
        return new StringBuilder(f1111z[2]).append(this.f1112a).append(f1111z[0]).append(this.f1113b).append(f1111z[1]).append(super.toString()).toString();
    }
}
