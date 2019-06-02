package cn.jpush.android.api;

import java.util.Set;

/* renamed from: cn.jpush.android.api.b */
public final class C0406b {
    /* renamed from: z */
    private static final String[] f506z;
    /* renamed from: a */
    public String f507a;
    /* renamed from: b */
    public Set<String> f508b;
    /* renamed from: c */
    public TagAliasCallback f509c;

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
        r12 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "kE\f\u0004'4_";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000d:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
    L_0x0014:
        r8 = r1;
    L_0x0015:
        r9 = r2;
        r10 = r8;
        r14 = r7;
        r7 = r2;
        r2 = r14;
    L_0x001a:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004d;
            case 1: goto L_0x0050;
            case 2: goto L_0x0052;
            case 3: goto L_0x0055;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = 64;
    L_0x0023:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002f;
    L_0x002b:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x001a;
    L_0x002f:
        r7 = r2;
        r2 = r9;
    L_0x0031:
        if (r7 > r8) goto L_0x0015;
    L_0x0033:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            default: goto L_0x003f;
        };
    L_0x003f:
        r5[r3] = r2;
        r0 = "\u0004\u0004\u0014\t\u0002&\u0006\u00135!5\u0004\u0015\u0016`jE\u0019\t)&\u0016B";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r5[r3] = r2;
        f506z = r6;
        return;
    L_0x004d:
        r11 = 71;
        goto L_0x0023;
    L_0x0050:
        r11 = r12;
        goto L_0x0023;
    L_0x0052:
        r11 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x0023;
    L_0x0055:
        r11 = r12;
        goto L_0x0023;
    L_0x0057:
        r8 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.b.<clinit>():void");
    }

    public C0406b(String str, Set<String> set, TagAliasCallback tagAliasCallback) {
        this.f507a = str;
        this.f508b = set;
        this.f509c = tagAliasCallback;
    }

    public final String toString() {
        return new StringBuilder(f506z[1]).append(this.f507a).append(f506z[0]).append(this.f508b).toString();
    }
}
