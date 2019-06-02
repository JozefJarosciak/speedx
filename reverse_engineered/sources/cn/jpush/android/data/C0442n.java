package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0503p;
import cn.jpush.android.util.C0505r;
import cn.jpush.android.util.C0506s;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.data.n */
final class C0442n extends Thread {
    /* renamed from: z */
    private static final String[] f717z;
    /* renamed from: a */
    final /* synthetic */ C0441m f718a;
    /* renamed from: b */
    final /* synthetic */ Context f719b;
    /* renamed from: c */
    final /* synthetic */ C0441m f720c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 10;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\\S:YEl^+DQ3\u001d*O^gR(O\u0015zU0V\u0015)P0EP)\u0010";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x008c;
            case 1: goto L_0x008f;
            case 2: goto L_0x0092;
            case 3: goto L_0x0095;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 53;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "'U+LY";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "%\u001d-HVai&QP)\u0000";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "oT3D\u000f&\u0012";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "`P8\u0001F{^b\u0003";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "hS;SZ`YqQP{P6RF`R1\u000fb[t\u000bdjLe\u000bdgG|\u0013~f]r\r`rL";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "%\u001d5TXyp0EP)\u0000";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "[T<I\u0018yH,I\u0015gX:EF)I7D\u0015yX-L\\zN6N[)R9\u0001b[t\u000bdjLe\u000bdgG|\u0013~f]r\r`rL\u0011QYl\\,D\u0015{X.TPzIHA'";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "zU0VpgI6UL)M-NVlN,\u0001F}\\-U\u0015{H1\u0000\u0015zU0VxfY:\u0001\b)";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "ZU0VpgI6UL";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        f717z = r4;
        return;
    L_0x008c:
        r9 = 9;
        goto L_0x0020;
    L_0x008f:
        r9 = 61;
        goto L_0x0020;
    L_0x0092:
        r9 = 95;
        goto L_0x0020;
    L_0x0095:
        r9 = 33;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.n.<clinit>():void");
    }

    C0442n(C0441m c0441m, C0441m c0441m2, Context context) {
        this.f720c = c0441m;
        this.f718a = c0441m2;
        this.f719b = context;
    }

    public final void run() {
        new StringBuilder(f717z[8]).append(this.f720c.f711H).append(f717z[2]).append(this.f720c.f710G).append(f717z[6]).append(this.f720c.f709F);
        ac.m1576a();
        if (this.f720c.f711H != 0) {
            new StringBuilder(f717z[0]).append(this.f720c.f711H);
            ac.m1581b();
            return;
        }
        String str = this.f718a.c;
        String str2 = this.f718a.f716a;
        if (this.f718a.f710G == 0) {
            C0459i.m1415a(str, 995, this.f719b);
            C0417m.m1214a(this.f719b, this.f718a);
        } else if (4 == this.f718a.f710G) {
            this.f718a.f715L = str2;
            C0459i.m1415a(str, 995, this.f719b);
            C0417m.m1214a(this.f719b, this.f718a);
        } else if (C0490b.m1697c(this.f719b, f717z[5])) {
            String str3;
            int i;
            String str4 = null;
            for (int i2 = 0; i2 < 4; i2++) {
                str4 = C0506s.m1797a(str2, 5, 5000);
                if (!C0506s.m1800a(str4)) {
                    str3 = str4;
                    i = 1;
                    break;
                }
            }
            str3 = str4;
            i = 0;
            String b = C0503p.m1783b(this.f719b, str);
            if (i != 0) {
                str4 = b + str + f717z[1];
                String substring = str2.substring(0, str2.lastIndexOf("/") + 1);
                if (this.f718a.f712I.isEmpty()) {
                    this.f718a.f715L = this.f718a.f716a;
                    C0417m.m1214a(this.f719b, this.f718a);
                    return;
                } else if (!C0429c.m1271a(this.f718a.f712I, this.f719b, substring, str, this.f718a.m1279e())) {
                    ac.m1581b();
                    C0459i.m1415a(str, 1014, this.f719b);
                    return;
                } else if (C0505r.m1792a(str4, str3.replaceAll(new StringBuilder(f717z[4]).append(substring).toString(), new StringBuilder(f717z[4]).append(b).toString()), this.f719b)) {
                    this.f718a.f715L = new StringBuilder(f717z[3]).append(str4).toString();
                    C0459i.m1415a(str, 995, this.f719b);
                    C0417m.m1214a(this.f719b, this.f718a);
                    return;
                } else {
                    C0459i.m1415a(str, 1014, this.f719b);
                    return;
                }
            }
            ac.m1586d();
            C0459i.m1415a(str, 1014, this.f719b);
            C0459i.m1416a(str, 1021, C0490b.m1685b(this.f719b, str2), this.f719b);
        } else {
            ac.m1589e(f717z[9], f717z[7]);
            C0459i.m1415a(str, 1014, this.f719b);
        }
    }
}
