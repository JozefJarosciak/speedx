package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.s */
public final class C0447s extends C0429c {
    /* renamed from: J */
    private static final String[] f742J;
    /* renamed from: E */
    public String f743E;
    /* renamed from: F */
    public String f744F;
    /* renamed from: G */
    public String f745G;
    /* renamed from: H */
    public String f746H;
    /* renamed from: I */
    public String f747I;
    /* renamed from: a */
    public int f748a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "_u7`\u0014";
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
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 33;
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
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "_u?qSE";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "_u3jGF";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "_u.}QL";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "_u/vM";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "|D1jN^DzrHMO5$UPZ?$\f\t";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f742J = r4;
        return;
    L_0x0068:
        r9 = 41;
        goto L_0x001f;
    L_0x006b:
        r9 = 42;
        goto L_0x001f;
    L_0x006e:
        r9 = 90;
        goto L_0x001f;
    L_0x0071:
        r9 = 4;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.s.<clinit>():void");
    }

    public C0447s() {
        this.o = 2;
    }

    /* renamed from: a */
    public final void mo2219a(Context context) {
        ac.m1576a();
        C0459i.m1415a(this.c, 995, context);
        if (this.f748a == 0) {
            if (C0490b.m1691b(context)) {
                ServiceInterface.m1461a(context, (C0429c) this);
            }
        } else if (this.f748a == 1) {
            C0417m.m1214a(context, (C0429c) this);
        } else {
            new StringBuilder(f742J[5]).append(this.f748a);
            ac.m1581b();
        }
    }

    /* renamed from: a */
    public final boolean mo2220a(Context context, JSONObject jSONObject) {
        ac.m1576a();
        this.f748a = jSONObject.optInt(f742J[3], 0);
        this.f743E = jSONObject.optString(f742J[4], "");
        this.f744F = jSONObject.optString(f742J[1], "");
        this.f746H = jSONObject.optString(f742J[0], "");
        this.f745G = jSONObject.optString(f742J[2], "");
        return true;
    }
}
