package com.google.protobuf.jpush;

/* renamed from: com.google.protobuf.jpush.a */
public abstract class C4068a implements C4067k {
    /* renamed from: z */
    private static final String[] f14607z;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
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
        r2 = "@\u001b\u001dOKk\u0006Y\u0018Wm\u0006\u001cODwR\u0014\u001aFlR\u001d\u000eQeR\u0018\u001c\u0005a\n\t\nFp\u0017\u001dA";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0056;
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
            case 0: goto L_0x004b;
            case 1: goto L_0x004d;
            case 2: goto L_0x0050;
            case 3: goto L_0x0053;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 37;
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
        r0 = "W\u0017\u000b\u0006Dh\u001b\u0003\u0006KcR\r\u0000\u0005eR\u001b\u0016QaR\u0018\u001dWe\u000bY\u001bMv\u0017\u000eODjR0 `|\u0011\u001c\u001fQm\u001d\u0017O\rw\u001a\u0016\u001aI`R\u0017\nSa\u0000Y\u0007Dt\u0002\u001c\u0001\f*";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14607z = r6;
        return;
    L_0x004b:
        r11 = 4;
        goto L_0x0021;
    L_0x004d:
        r11 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0021;
    L_0x0050:
        r11 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0021;
    L_0x0053:
        r11 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0021;
    L_0x0056:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.a.<clinit>():void");
    }

    public final byte[] ae() {
        try {
            byte[] bArr = new byte[m16412c()];
            C4073e a = C4073e.m16446a(bArr, 0, bArr.length);
            m16411a(a);
            if (a.m16459a() == 0) {
                return bArr;
            }
            throw new IllegalStateException(f14607z[0]);
        } catch (Throwable e) {
            throw new RuntimeException(f14607z[1], e);
        }
    }
}
