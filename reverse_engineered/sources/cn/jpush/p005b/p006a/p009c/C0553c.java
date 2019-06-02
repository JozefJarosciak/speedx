package cn.jpush.p005b.p006a.p009c;

/* renamed from: cn.jpush.b.a.c.c */
public final class C0553c {
    /* renamed from: z */
    private static final String f1170z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r6 = 77;
        r0 = "9&Sy<!zXtHU\u000e$\bO";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0028;
    L_0x000d:
        r3 = r0;
        r4 = r2;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0012:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0036;
            case 1: goto L_0x0039;
            case 2: goto L_0x003c;
            case 3: goto L_0x003e;
            default: goto L_0x0019;
        };
    L_0x0019:
        r5 = r6;
    L_0x001a:
        r5 = r5 ^ r7;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0026;
    L_0x0022:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0012;
    L_0x0026:
        r1 = r0;
        r0 = r3;
    L_0x0028:
        if (r1 > r2) goto L_0x000d;
    L_0x002a:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f1170z = r0;
        return;
    L_0x0036:
        r5 = 9;
        goto L_0x001a;
    L_0x0039:
        r5 = 23;
        goto L_0x001a;
    L_0x003c:
        r5 = r6;
        goto L_0x001a;
    L_0x003e:
        r5 = 96;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.c.c.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1908a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append(f1170z.charAt((b >> 4) & 15)).append(f1170z.charAt(b & 15));
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }
}
