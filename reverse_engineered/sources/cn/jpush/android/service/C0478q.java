package cn.jpush.android.service;

/* renamed from: cn.jpush.android.service.q */
final class C0478q {
    /* renamed from: z */
    private static final String[] f892z;
    /* renamed from: a */
    String f893a;
    /* renamed from: b */
    int f894b;

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
        r4 = "\" ?dU\u0002*iuV\u0019:i(\u0019[";
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
            case 3: goto L_0x0068;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 57;
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
        r0 = ";!;q\u0019\u0002=ik\\\u000e*,a\u0019\r!;%XK8(iP\u000fn(a]\u0019+:v\u0015K=9iP\u001fn+|\u0019Q";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "\" ?dU\u0002*iuV\u0019:i(\u0019";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "\" ?dU\u0002*ilIKci";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f892z = r7;
        return;
    L_0x005f:
        r12 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0022;
    L_0x0062:
        r12 = 78;
        goto L_0x0022;
    L_0x0065:
        r12 = 73;
        goto L_0x0022;
    L_0x0068:
        r12 = 5;
        goto L_0x0022;
    L_0x006a:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.q.<clinit>():void");
    }

    public C0478q(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new Exception(f892z[1]);
        }
        this.f893a = str.substring(0, indexOf);
        if (SisInfo.isValidIPV4(this.f893a)) {
            String substring = str.substring(indexOf + 1);
            try {
                this.f894b = Integer.parseInt(substring);
                if (this.f894b == 0) {
                    throw new Exception(f892z[0]);
                }
                return;
            } catch (Exception e) {
                throw new Exception(new StringBuilder(f892z[2]).append(substring).toString());
            }
        }
        throw new Exception(new StringBuilder(f892z[3]).append(this.f893a).toString());
    }

    public final String toString() {
        return this.f893a + ":" + this.f894b;
    }
}
