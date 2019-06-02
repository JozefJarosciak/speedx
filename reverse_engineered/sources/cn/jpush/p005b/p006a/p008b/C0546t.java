package cn.jpush.p005b.p006a.p008b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.jpush.android.C0404a;
import cn.jpush.android.helpers.C0460j;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.p000a.p001a.af;
import cn.jpush.p000a.p001a.ao;
import cn.jpush.p000a.p001a.aq;
import cn.jpush.p000a.p001a.ar;
import cn.jpush.p000a.p001a.ba;
import cn.jpush.p005b.p006a.p007a.C0514i;
import cn.jpush.p005b.p006a.p007a.C0518c;
import cn.jpush.p005b.p006a.p007a.C0519d;

/* renamed from: cn.jpush.b.a.b.t */
public final class C0546t {
    /* renamed from: z */
    private static final String[] f1157z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "a\"\u000e{X 2im`*0jmZ";
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
            case 0: goto L_0x0082;
            case 1: goto L_0x0085;
            case 2: goto L_0x0088;
            case 3: goto L_0x008b;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 8;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0004o9lmF;\u0015~2";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "i,(sgFoq:{M!8_~M!(XiK$|7(Z&8 ";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "A\"\u0003hm[?3t{M";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0004o.sl\u0012";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "K!rpx]<44aEa=tlZ 5~&I,(sgFa\u0015WWz\n\u000fJGf\u001c\u0019";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "i,(sgFoq:`I!8vma\"\u000e{X 2im\bb|sek\"8 ";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "i,(sgFoq:{M!8Y`I;\u0011io{62yJI,7:%\b=5~2";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "k'=nE[(|HmK*5lmLoq:e[(\u0015~2";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        f1157z = r4;
        return;
    L_0x0082:
        r9 = 40;
        goto L_0x0020;
    L_0x0085:
        r9 = 79;
        goto L_0x0020;
    L_0x0088:
        r9 = 92;
        goto L_0x0020;
    L_0x008b:
        r9 = 26;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.b.t.<clinit>():void");
    }

    /* renamed from: a */
    public static void m1893a(Context context, Handler handler, C0514i c0514i, byte[] bArr) {
        C0519d c0519d = (C0519d) c0514i;
        C0543p a = c0519d.mo2234a();
        if (a == null) {
            ac.m1588e();
            return;
        }
        int i = a.f1144a;
        long longValue = c0519d.m1828f().m1848a().longValue();
        ac.m1582b(f1157z[0], new StringBuilder(f1157z[6]).append(i).append(f1157z[4]).append(longValue).toString());
        switch (i) {
            case 1:
                af afVar = a.f1149f;
                if (afVar != null && afVar.m199d() == 0) {
                    ac.m1581b();
                    C0404a.m1070a(true);
                    C0404a.m1133p();
                    break;
                }
                ac.m1581b();
                C0404a.m1070a(false);
                break;
                break;
            case 2:
                if (a.f1149f.m199d() != 0) {
                    ac.m1588e();
                    break;
                }
                ac.m1581b();
                C0404a.m1070a(false);
                C0460j.m1421c(context);
                break;
            case 13:
                ba baVar = (ba) a.f1148e;
                new StringBuilder(f1157z[2]).append(longValue).append(f1157z[1]).append(baVar.m463d());
                ac.m1581b();
                a.f1148e = ba.m459t().m485a(baVar.m463d()).m484a(baVar.m465f()).m489b(baVar.m467h()).m491c(baVar.m469j()).m483a();
                Message.obtain(handler, 7501, new C0518c(longValue, a)).sendToTarget();
                break;
            case 14:
                C0546t.m1894a(handler, longValue, a);
                break;
        }
        C0490b.m1678a(context, f1157z[5], f1157z[3], bArr);
    }

    /* renamed from: a */
    private static void m1894a(Handler handler, long j, C0543p c0543p) {
        new StringBuilder(f1157z[7]).append(j);
        ac.m1581b();
        aq aqVar = (aq) c0543p.f1148e;
        ar e = aq.m326e();
        for (ao aoVar : aqVar.m328b()) {
            new StringBuilder(f1157z[8]).append(aoVar.m299h());
            ac.m1576a();
            e.m336a(ao.m291r().m319c(aoVar.m299h()).m313a(aoVar.m301j()).m314a(aoVar.m295d()).m317b(aoVar.m297f()).m312a());
        }
        c0543p.f1148e = e.m335a();
        Message.obtain(handler, 7502, new C0518c(j, c0543p)).sendToTarget();
    }
}
