package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import cn.jpush.p005b.p006a.p009c.C0551a;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.b */
public final class C0516b extends C0514i {
    /* renamed from: z */
    private static final String[] f1078z;
    /* renamed from: a */
    long f1079a;
    /* renamed from: b */
    String f1080b;

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
        r3 = "\u0002:\u001cK\bmu\u001fL\n@nK";
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
        r11 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
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
        r0 = "\u000e7Q";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "uY\u0005J\u0003|\u0002H\u0000@i\u0014eO\u0003:\u0002]\u0001J\u0003q\u000b\u0014";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f1078z = r6;
        return;
    L_0x0055:
        r11 = 46;
        goto L_0x0021;
    L_0x0058:
        r11 = 26;
        goto L_0x0021;
    L_0x005b:
        r11 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0021;
    L_0x005e:
        r11 = 56;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.b.<clinit>():void");
    }

    public C0516b(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: a */
    public final long mo2234a() {
        return this.f1079a;
    }

    /* renamed from: b */
    public final void mo2231b() {
        super.mo2231b();
        m1820a(this.f1079a);
        m1821a(this.f1080b);
    }

    /* renamed from: c */
    public final void mo2232c() {
        super.mo2232c();
        ByteBuffer byteBuffer = this.f;
        this.f1079a = C0497j.m1758d(byteBuffer, this);
        this.f1080b = C0551a.m1899a(byteBuffer, this);
    }

    /* renamed from: d */
    public final String m1838d() {
        return this.f1080b;
    }

    public final String toString() {
        return new StringBuilder(f1078z[2]).append(this.f1079a).append(f1078z[0]).append(this.f1080b).append(f1078z[1]).append(super.toString()).toString();
    }
}
