package cn.jpush.android.data;

import cn.jpush.android.util.an;

/* renamed from: cn.jpush.android.data.d */
public final class C0432d {
    /* renamed from: z */
    private static final String[] f642z;
    /* renamed from: a */
    public String f643a;
    /* renamed from: b */
    public String f644b;
    /* renamed from: c */
    final /* synthetic */ C0429c f645c;

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
        r2 = "]LYfC\u0014\u001e\u000b`Q\u00143\u0014zR.\u0005\u001d)\bQ";
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
        r11 = 53;
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
        r0 = "\u001c\u001f\u001eV\\\u0015LD)";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f642z = r6;
        return;
    L_0x004b:
        r11 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0021;
    L_0x004e:
        r11 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0021;
    L_0x0051:
        r11 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0021;
    L_0x0054:
        r11 = 9;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.d.<clinit>():void");
    }

    public C0432d(C0429c c0429c, C0429c c0429c2) {
        this.f645c = c0429c;
        this.f643a = c0429c2.f613c;
        this.f644b = c0429c2.f614d;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0432d)) {
            return false;
        }
        C0432d c0432d = (C0432d) obj;
        return (an.m1657a(this.f643a) || an.m1657a(c0432d.f643a) || !an.m1658a(this.f643a, c0432d.f643a)) ? false : (an.m1657a(this.f644b) && an.m1657a(c0432d.f644b)) ? true : (an.m1657a(this.f644b) || an.m1657a(c0432d.f644b) || !an.m1658a(this.f644b, c0432d.f644b)) ? false : true;
    }

    public final String toString() {
        return new StringBuilder(f642z[1]).append(this.f643a).append(f642z[0]).append(this.f644b).toString();
    }
}
