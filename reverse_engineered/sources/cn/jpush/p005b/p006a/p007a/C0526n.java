package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import cn.jpush.p005b.p006a.p009c.C0552b;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.n */
public final class C0526n extends C0514i {
    /* renamed from: z */
    private static final String[] f1114z;
    /* renamed from: a */
    int f1115a;

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
        r4 = "0\u0003%\u0016\u0007B\u00031%\u000eX\u0012-\u0019\u0018N?bZKX\u00073\u0002\u000eE\u0001'M";
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
        r12 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
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
        r0 = "K\u0006B";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "G\u000b\u000f'\u0004\u0018J\u0005'M";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "9N\u00112\u0018\u0005X\u0007b\u0012\u0019Y\r0WF\u000b\u0001-\u0013\u000e\u0011";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f1114z = r7;
        return;
    L_0x005f:
        r12 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0022;
    L_0x0062:
        r12 = 43;
        goto L_0x0022;
    L_0x0065:
        r12 = 98;
        goto L_0x0022;
    L_0x0068:
        r12 = 66;
        goto L_0x0022;
    L_0x006b:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.n.<clinit>():void");
    }

    public C0526n(long j, long j2, int i, String str, int i2) {
        super(2, 10, j, j2, 0, null);
        this.f1115a = 0;
    }

    public C0526n(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: b */
    public final void mo2231b() {
        super.mo2231b();
        m1826c(this.f1115a);
    }

    /* renamed from: c */
    public final void mo2232c() {
        super.mo2232c();
        if (this.g > 0) {
            C0552b.m1907b(new StringBuilder(f1114z[3]).append(this.g).append(f1114z[2]).append(this.h).toString());
        } else {
            this.f1115a = C0497j.m1756b(this.f, this);
        }
    }

    public final String toString() {
        return new StringBuilder(f1114z[0]).append(this.f1115a).append(f1114z[1]).append(super.toString()).toString();
    }
}
