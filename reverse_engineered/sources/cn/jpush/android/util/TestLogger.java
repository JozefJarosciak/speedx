package cn.jpush.android.util;

public class TestLogger implements C0488t {
    /* renamed from: z */
    private static final String[] f934z;

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
        r2 = "ATd(";
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
        r11 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
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
        r0 = "<Yi";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f934z = r6;
        return;
    L_0x004b:
        r11 = 28;
        goto L_0x0021;
    L_0x004e:
        r11 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0021;
    L_0x0051:
        r11 = 73;
        goto L_0x0021;
    L_0x0054:
        r11 = 8;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.TestLogger.<clinit>():void");
    }

    public static void setTestLogger() {
        ac.m1577a(new TestLogger());
    }

    /* renamed from: d */
    public void mo2224d(String str, String str2) {
        System.out.println("[" + str + f934z[0] + str2);
    }

    /* renamed from: d */
    public void m1566d(String str, String str2, Throwable th) {
        System.out.println("[" + str + f934z[0] + str2 + f934z[1] + th.getMessage());
    }

    /* renamed from: e */
    public void mo2225e(String str, String str2) {
        System.out.println("[" + str + f934z[0] + str2);
    }

    /* renamed from: e */
    public void mo2226e(String str, String str2, Throwable th) {
        System.out.println("[" + str + f934z[0] + str2 + f934z[1] + th.getMessage());
    }

    /* renamed from: i */
    public void mo2227i(String str, String str2) {
        System.out.println("[" + str + f934z[0] + str2);
    }

    /* renamed from: i */
    public void m1570i(String str, String str2, Throwable th) {
        System.out.println("[" + str + f934z[0] + str2 + f934z[1] + th.getMessage());
    }

    /* renamed from: v */
    public void mo2228v(String str, String str2) {
        System.out.println("[" + str + f934z[0] + str2);
    }

    /* renamed from: v */
    public void m1572v(String str, String str2, Throwable th) {
        System.out.println("[" + str + f934z[0] + str2 + f934z[1] + th.getMessage());
    }

    /* renamed from: w */
    public void mo2229w(String str, String str2) {
        System.out.println("[" + str + f934z[0] + str2);
    }

    /* renamed from: w */
    public void mo2230w(String str, String str2, Throwable th) {
        System.out.println("[" + str + f934z[0] + str2 + f934z[1] + th.getMessage());
    }
}
