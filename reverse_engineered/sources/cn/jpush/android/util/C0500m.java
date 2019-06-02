package cn.jpush.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: cn.jpush.android.util.m */
public final class C0500m {
    /* renamed from: a */
    public static String f1040a;
    /* renamed from: z */
    private static final String[] f1041z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "k?3\u0013\u0013_\".\"\u0016+9\u0019";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x006c;
            case 1: goto L_0x006f;
            case 2: goto L_0x0072;
            case 3: goto L_0x0075;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 94;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "k?3\u0013s_\u000bg\u000e:M\u000e\u0002P3|9\u0019";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        f1041z = r4;
        r0 = "k?3\u0013\u0013_\".5\u0016Z+'";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0086;
    L_0x0052:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0057:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0078;
            case 1: goto L_0x007b;
            case 2: goto L_0x007e;
            case 3: goto L_0x0081;
            default: goto L_0x005e;
        };
    L_0x005e:
        r5 = 94;
    L_0x0060:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0084;
    L_0x0068:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0057;
    L_0x006c:
        r9 = 18;
        goto L_0x001f;
    L_0x006f:
        r9 = 70;
        goto L_0x001f;
    L_0x0072:
        r9 = 74;
        goto L_0x001f;
    L_0x0075:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x001f;
    L_0x0078:
        r5 = 18;
        goto L_0x0060;
    L_0x007b:
        r5 = 70;
        goto L_0x0060;
    L_0x007e:
        r5 = 74;
        goto L_0x0060;
    L_0x0081:
        r5 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x0060;
    L_0x0084:
        r1 = r0;
        r0 = r3;
    L_0x0086:
        if (r1 > r2) goto L_0x0052;
    L_0x0088:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f1040a = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.m.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1764a() {
        return new SimpleDateFormat(f1041z[1]).format(new Date());
    }

    /* renamed from: b */
    public static String m1765b() {
        return new SimpleDateFormat(f1041z[0]).format(new Date());
    }
}
