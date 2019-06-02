package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import cn.jpush.p005b.p006a.p009c.C0551a;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.i */
public abstract class C0514i extends C0513g {
    /* renamed from: z */
    private static final String[] f1070z;
    /* renamed from: g */
    public int f1071g;
    /* renamed from: h */
    public String f1072h;

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
        r3 = "T|\b";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
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
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 68;
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
        r0 = "XqM%6\u001b#\u0012";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "/\u001bz27\u0004>F$!)q\u0005w'\u001b5Mm";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f1070z = r6;
        return;
    L_0x0055:
        r11 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0021;
    L_0x0058:
        r11 = 81;
        goto L_0x0021;
    L_0x005b:
        r11 = 40;
        goto L_0x0021;
    L_0x005e:
        r11 = 87;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.i.<clinit>():void");
    }

    public C0514i(int i, int i2, long j, long j2, int i3, String str) {
        super(false, 2, 10, j, -1, j2);
        this.f1071g = i3;
        this.f1072h = str;
    }

    public C0514i(C0521f c0521f, ByteBuffer byteBuffer) {
        super(false, c0521f, byteBuffer);
    }

    /* renamed from: b */
    protected void mo2231b() {
        if (this.f1071g >= 0) {
            m1824b(this.f1071g);
            if (this.f1071g > 0) {
                m1821a(this.f1072h);
            }
        }
    }

    /* renamed from: c */
    protected void mo2232c() {
        int e = m1827e();
        if (e != 19 && e != 3 && e != 100 && e != 25) {
            this.f1071g = C0497j.m1756b(this.f, this);
            if (this.f1071g > 0) {
                this.f1072h = C0551a.m1899a(this.f, this);
            }
        }
    }

    public String toString() {
        return new StringBuilder(f1070z[2]).append(this.f1071g).append(this.f1072h == null ? "" : new StringBuilder(f1070z[1]).append(this.f1072h).toString()).append(f1070z[0]).append(super.toString()).toString();
    }
}
