package cn.jpush.p005b.p006a.p007a;

import cn.jpush.p005b.p006a.p008b.C0543p;
import cn.jpush.p005b.p006a.p009c.C0551a;
import com.google.protobuf.jpush.C4078j;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.d */
public final class C0519d extends C0514i {
    /* renamed from: z */
    private static final String[] f1084z;
    /* renamed from: a */
    int f1085a;
    /* renamed from: b */
    C0543p f1086b;

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
        r4 = ":k\"";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006a;
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
            case 3: goto L_0x0067;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 81;
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
        r0 = "A\u000fO 4i6m\u001c\"\u001b\"_qj4m\u0006>y)nH";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "T\u0013N>qU$h\u00172n";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "S\u000b\"0>~?\">4t!v\u001aq7f";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f1084z = r7;
        return;
    L_0x005f:
        r12 = 26;
        goto L_0x0022;
    L_0x0062:
        r12 = 70;
        goto L_0x0022;
    L_0x0065:
        r12 = r3;
        goto L_0x0022;
    L_0x0067:
        r12 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0022;
    L_0x006a:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.d.<clinit>():void");
    }

    public C0519d(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: a */
    public final C0543p mo2234a() {
        return this.f1086b;
    }

    /* renamed from: b */
    public final void mo2231b() {
        super.mo2231b();
        if (this.f1086b != null) {
            byte[] ae = this.f1086b.m1889a().ae();
            this.f1085a = ae.length;
            new StringBuilder(f1084z[3]).append(this.f1085a);
            m1824b(this.f1085a);
            m1822a(ae);
        }
    }

    /* renamed from: c */
    public final void mo2232c() {
        super.mo2232c();
        this.f1085a = this.f.getShort();
        new StringBuilder(f1084z[3]).append(this.f1085a);
        try {
            this.f1086b = new C0543p(C0551a.m1903a(this.f));
        } catch (C4078j e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        return new StringBuilder(f1084z[1]).append(this.f1086b == null ? f1084z[2] : this.f1086b.toString()).append(f1084z[0]).append(super.toString()).toString();
    }
}
