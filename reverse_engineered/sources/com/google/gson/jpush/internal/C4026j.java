package com.google.gson.jpush.internal;

import java.lang.reflect.Type;

/* renamed from: com.google.gson.jpush.internal.j */
final class C4026j implements ae<T> {
    /* renamed from: z */
    private static final String[] f14523z;
    /* renamed from: a */
    final /* synthetic */ Class f14524a;
    /* renamed from: b */
    final /* synthetic */ Type f14525b;
    /* renamed from: c */
    final /* synthetic */ C4022f f14526c;
    /* renamed from: d */
    private final UnsafeAllocator f14527d = UnsafeAllocator.create();

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
        r2 = "~e=@\u0011N+(M]Be*M\u0016N+2MPJy;Q]Hd2Q\tY~?V\u0012Y+:M\u000f\u000b";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
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
            case 1: goto L_0x004e;
            case 2: goto L_0x0051;
            case 3: goto L_0x0054;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
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
        r0 = "\u0005+\u000eG\u001aBx(G\u000f\u000bj2\u00024Ex(C\u0013Hn\u001fP\u0018J3P]\\b(J]lx3L]Md.\u0002\tCb/\u0002\tR{9\u0002\u0010Jr|D\u0014S+(J\u0014X+,P\u0012Ig9OS";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14523z = r6;
        return;
    L_0x004b:
        r11 = 43;
        goto L_0x0021;
    L_0x004e:
        r11 = 11;
        goto L_0x0021;
    L_0x0051:
        r11 = 92;
        goto L_0x0021;
    L_0x0054:
        r11 = 34;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.j.<clinit>():void");
    }

    C4026j(C4022f c4022f, Class cls, Type type) {
        this.f14526c = c4022f;
        this.f14524a = cls;
        this.f14525b = type;
    }

    /* renamed from: a */
    public final T mo5711a() {
        try {
            return this.f14527d.newInstance(this.f14524a);
        } catch (Throwable e) {
            throw new RuntimeException(new StringBuilder(f14523z[0]).append(this.f14525b).append(f14523z[1]).toString(), e);
        }
    }
}
