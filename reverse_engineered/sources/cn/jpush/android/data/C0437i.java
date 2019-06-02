package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0499l;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.i */
public class C0437i extends C0429c {
    /* renamed from: R */
    private static final String[] f666R;
    /* renamed from: E */
    public String f667E;
    /* renamed from: F */
    public boolean f668F;
    /* renamed from: G */
    public boolean f669G;
    /* renamed from: H */
    public int f670H;
    /* renamed from: I */
    public boolean f671I;
    /* renamed from: J */
    public int f672J;
    /* renamed from: K */
    public String f673K;
    /* renamed from: L */
    public C0440l f674L;
    /* renamed from: M */
    public String f675M;
    /* renamed from: N */
    public boolean f676N;
    /* renamed from: O */
    public boolean f677O;
    /* renamed from: P */
    public String f678P;
    /* renamed from: Q */
    public boolean f679Q;
    /* renamed from: a */
    public String f680a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 28;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0007gOg(=^Cr'";
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
            case 0: goto L_0x0152;
            case 1: goto L_0x0156;
            case 2: goto L_0x015a;
            case 3: goto L_0x015e;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 79;
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
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "9QPc<";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "9~IY*;aL";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "9QTc=";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "9~IY\"<;";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "9QVo;4k";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0007gAi!\boVn";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "9QQe *k";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "9~IY<0aUY)1`Ku'=j}h ,g";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "9QKe 6QWt#";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "9Q@t +yGt";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "9{Vi\u00106";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "9{Vi\u00105";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "9~IY:*b";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "9QKk.?k}s=4";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "9~IY.-zMY&6}Vg#4";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "9QGt*+";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "9QKh)7";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "9{Vi\u0010*m";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0007yGd\u001f9iGV.,f";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "9~IY!";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "9QGs=4";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "9QQo5=";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "9{Vi\u0010*";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "9~IY<0aU";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "9QV?=";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "9~IY:";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\r`G~?=mVc+b.Wh$6aUho(e\u0002u'7y\u0002k <k\u0002+o";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        f666R = r4;
        return;
    L_0x0152:
        r9 = 88;
        goto L_0x0020;
    L_0x0156:
        r9 = 14;
        goto L_0x0020;
    L_0x015a:
        r9 = 34;
        goto L_0x0020;
    L_0x015e:
        r9 = 6;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.i.<clinit>():void");
    }

    public C0437i() {
        this.f677O = true;
        this.o = 1;
        this.f674L = new C0440l(this);
    }

    /* renamed from: a */
    static /* synthetic */ void m1312a(C0437i c0437i, C0437i c0437i2, Context context) {
        if (C0456f.m1403a(c0437i2.f669G, c0437i2.f670H, context)) {
            if (C0490b.m1691b(context)) {
                ServiceInterface.m1461a(context, (C0429c) c0437i2);
                return;
            }
            c0437i2.f669G = false;
        }
        C0417m.m1214a(context, (C0429c) c0437i2);
    }

    /* renamed from: a */
    public void mo2219a(Context context) {
        ac.m1576a();
        boolean f = C0490b.m1710f(context, this.f680a);
        int i = 995;
        if (this.f668F || !f) {
            if (this.f668F && f) {
                ac.m1581b();
                i = 998;
            }
            if (this.f672J == 1) {
                new C0438j(this, this, context, i).start();
                return;
            } else if (this.f672J == 0) {
                new C0439k(this, context, i, this).start();
                return;
            } else {
                new StringBuilder(f666R[27]).append(this.f672J);
                ac.m1586d();
                return;
            }
        }
        ac.m1581b();
        C0459i.m1415a(this.c, 997, context);
    }

    /* renamed from: a */
    public boolean mo2220a(Context context, JSONObject jSONObject) {
        ac.m1576a();
        this.f680a = jSONObject.optString(f666R[20], "");
        this.f668F = jSONObject.optInt(f666R[26], 0) > 0;
        this.f669G = jSONObject.optInt(f666R[12], 0) > 0;
        this.f670H = jSONObject.optInt(f666R[11], 0);
        this.f671I = jSONObject.optInt(f666R[23], 0) > 0;
        this.f672J = jSONObject.optInt(f666R[24], 1);
        this.f673K = jSONObject.optString(f666R[13], "").trim();
        this.f675M = jSONObject.optString(f666R[4], "");
        this.f667E = jSONObject.optString(f666R[18], "");
        this.f676N = jSONObject.optInt(f666R[8], 0) > 0;
        this.f677O = jSONObject.optInt(f666R[15], 1) == 1;
        if (this.o == 1) {
            JSONObject a = C0456f.m1399a(context, this.c, jSONObject, f666R[2]);
            if (a == null) {
                return false;
            }
            C0440l c0440l = this.f674L;
            ac.m1576a();
            c0440l.f692a = a.optString(f666R[5], "");
            c0440l.f693b = a.optString(f666R[9], "").trim();
            c0440l.f694c = a.optString(f666R[3], "");
            c0440l.f695d = a.optString(f666R[25], "");
            c0440l.f696e = a.optInt(f666R[7], 0) == 0;
            c0440l.f697f = a.optString(f666R[22], "");
            c0440l.f698g = a.optString(f666R[17], "");
            c0440l.f699h = a.optString(f666R[14], "").trim();
            c0440l.f701j = a.optString(f666R[21], "").trim();
            c0440l.f706o.y = a.optInt(f666R[10], 0) == 1;
            c0440l.f700i = a.optInt(f666R[1], 0) == 0;
            if (c0440l.f700i) {
                c0440l.f702k = C0499l.m1762a(a.optJSONArray(f666R[16]));
            }
            if (an.m1657a(c0440l.f704m)) {
                c0440l.f704m = a.optString(f666R[0], "").trim();
            }
            if (an.m1657a(c0440l.f703l)) {
                c0440l.f703l = a.optString(f666R[6], "").trim();
            }
            if (an.m1657a(c0440l.f703l)) {
                c0440l.f703l = a.optString(f666R[19], "").trim();
            }
        }
        return true;
    }
}
