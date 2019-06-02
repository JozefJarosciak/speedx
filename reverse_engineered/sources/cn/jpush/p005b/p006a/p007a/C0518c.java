package cn.jpush.p005b.p006a.p007a;

import cn.jpush.p005b.p006a.p008b.C0543p;
import cn.jpush.p005b.p006a.p009c.C0551a;
import cn.jpush.p005b.p006a.p009c.C0553c;
import com.google.protobuf.jpush.C4078j;

/* renamed from: cn.jpush.b.a.a.c */
public final class C0518c extends C0517h {
    /* renamed from: z */
    private static final String[] f1081z;
    /* renamed from: a */
    int f1082a;
    /* renamed from: b */
    C0543p f1083b;

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
        r4 = "\\N6m\u0016mG7mS(";
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
        r12 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
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
        r0 = "Aks\u000f\u0011l_s\u0001\u001bfA'%^%\u0006";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "So\u001e\u001f\u001byS6>\nU\u0006~m\u000ezI'\"\u001dgJi";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "(\u000bs";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f1081z = r7;
        return;
    L_0x005f:
        r12 = 8;
        goto L_0x0022;
    L_0x0062:
        r12 = 38;
        goto L_0x0022;
    L_0x0065:
        r12 = 83;
        goto L_0x0022;
    L_0x0068:
        r12 = 77;
        goto L_0x0022;
    L_0x006b:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.c.<clinit>():void");
    }

    public C0518c(long j, C0543p c0543p) {
        super(1, 100, j);
        this.f1083b = c0543p;
    }

    /* renamed from: a */
    public final C0543p mo2234a() {
        return this.f1083b;
    }

    /* renamed from: b */
    public final void mo2231b() {
        if (this.f1083b != null) {
            byte[] ae = this.f1083b.m1889a().ae();
            this.f1082a = ae.length;
            m1824b(this.f1082a);
            m1822a(ae);
        }
    }

    /* renamed from: c */
    public final void mo2232c() {
        new StringBuilder(f1081z[0]).append(C0553c.m1908a(this.e.m1850c()));
        this.f1082a = this.f.getShort();
        new StringBuilder(f1081z[1]).append(this.f1082a);
        try {
            this.f1083b = new C0543p(C0551a.m1903a(this.f));
        } catch (C4078j e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        return new StringBuilder(f1081z[2]).append(this.f1083b == null ? "" : this.f1083b.toString()).append(f1081z[3]).append(super.toString()).toString();
    }
}
