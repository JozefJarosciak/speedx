package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.C0427c;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ak;

/* renamed from: cn.jpush.android.helpers.d */
public final class C0454d extends C0427c {
    /* renamed from: z */
    private static final String[] f773z;
    /* renamed from: a */
    private Context f774a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 13;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "Eq\tD|b}\u001cYsdp,X~{]B|m{\u000f\rxr>\u001cD~g2]Cuw\u001bDyjj\u0014BtBz]\u0017:";
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
            case 0: goto L_0x00ad;
            case 1: goto L_0x00b1;
            case 2: goto L_0x00b5;
            case 3: goto L_0x00b9;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 26;
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
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "l{\t\rsej\u0018Jy>\u001fT:jw\u0019A6+u\u0018T ";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "'>\u000bLv~{G";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "Eq\tD|b}\u001cYsdp,X~{]Nuej\u001cDtx>\u001fT:jw\u0019A6+p\u0012Ysmw\u001eLnbq\u0013d~+$]";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "l{\t\rxdq\u0011H{e>\u001fT:jw\u0019A6+u\u0018T ";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "hq\u0010@s>\u0014Cnny\u0018_:ig]LsorQ\rqngG";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "l{\t\rvdp\u001a\rxr>\u001cD~g2]Fr$";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "hq\u0010@s>\u001fBug{\u001cC:ig]LsorQ\rqngG";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "l{\t\rIl\u0014C}&z\u001cY{+|\u0004\r{bz\u0011\u0001:`{\u0004\u0017";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "hq\u0010@s>.Yhbp\u001a\rxr>\u001cD~g2]Fr$";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "hq\u0010@s>\u0011Btl>\u001fT:jw\u0019A6+u\u0018T ";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "Eq\tD|b}\u001cYsdp,X~{]J>\u000eD`n>\u001fT:jw\u0019A6+m\u0014W+$]";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "Eq\tD|b}\u001cYsdp,X~{]]ugr]Oc+\u0014Iv'>\u0013Bnbx\u0014N{w\u0012CSo>G\r";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        f773z = r4;
        return;
    L_0x00ad:
        r9 = 11;
        goto L_0x0020;
    L_0x00b1:
        r9 = 30;
        goto L_0x0020;
    L_0x00b5:
        r9 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0020;
    L_0x00b9:
        r9 = 45;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.d.<clinit>():void");
    }

    public C0454d(Context context) {
        this.f774a = context;
    }

    /* renamed from: a */
    public final int mo2206a() {
        int a = C0455e.m1393a();
        new StringBuilder(f773z[12]).append(a);
        ac.m1576a();
        return a;
    }

    /* renamed from: a */
    public final int mo2207a(String str, int i) {
        int b = ak.m1645b(this.f774a, str, i);
        new StringBuilder(f773z[1]).append(str).append(f773z[2]).append(b);
        ac.m1576a();
        return b;
    }

    /* renamed from: a */
    public final long mo2208a(String str, long j) {
        long b = ak.m1646b(this.f774a, str, j);
        new StringBuilder(f773z[6]).append(str).append(f773z[2]).append(b);
        ac.m1576a();
        return b;
    }

    /* renamed from: a */
    public final String mo2209a(String str, String str2) {
        String b = ak.m1647b(this.f774a, str, str2);
        new StringBuilder(f773z[8]).append(str).append(f773z[2]).append(b);
        ac.m1576a();
        return b;
    }

    /* renamed from: a */
    public final void mo2210a(int i, long j, boolean z, float f, double d, String str) {
    }

    /* renamed from: a */
    public final boolean mo2211a(int i) {
        new StringBuilder(f773z[0]).append(i);
        ac.m1576a();
        return C0455e.m1394a(i);
    }

    /* renamed from: a */
    public final boolean mo2212a(String str, boolean z) {
        boolean b = ak.m1649b(this.f774a, str, z);
        new StringBuilder(f773z[4]).append(str).append(f773z[2]).append(b);
        ac.m1576a();
        return b;
    }

    /* renamed from: b */
    public final int mo2213b() {
        int b = C0455e.m1395b();
        new StringBuilder(f773z[11]).append(b);
        ac.m1576a();
        return b;
    }

    /* renamed from: b */
    public final void mo2214b(String str, int i) {
        new StringBuilder(f773z[5]).append(str).append(f773z[2]).append(i);
        ac.m1576a();
        ak.m1640a(this.f774a, str, i);
    }

    /* renamed from: b */
    public final void mo2215b(String str, long j) {
        new StringBuilder(f773z[10]).append(str).append(f773z[2]).append(j);
        ac.m1576a();
        ak.m1641a(this.f774a, str, j);
    }

    /* renamed from: b */
    public final void mo2216b(String str, String str2) {
        new StringBuilder(f773z[9]).append(str).append(f773z[2]).append(str2);
        ac.m1576a();
        ak.m1642a(this.f774a, str, str2);
    }

    /* renamed from: b */
    public final void mo2217b(String str, boolean z) {
        new StringBuilder(f773z[7]).append(str).append(f773z[2]).append(z);
        ac.m1576a();
        ak.m1643a(this.f774a, str, z);
    }

    /* renamed from: b */
    public final boolean mo2218b(int i) {
        new StringBuilder(f773z[3]).append(i);
        ac.m1576a();
        return C0455e.m1396b(i);
    }
}
