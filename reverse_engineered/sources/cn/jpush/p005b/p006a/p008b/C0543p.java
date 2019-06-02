package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.C0367b;
import cn.jpush.p000a.p001a.C0369d;
import cn.jpush.p000a.p001a.C0371f;
import cn.jpush.p000a.p001a.C0373h;
import cn.jpush.p000a.p001a.C0375j;
import cn.jpush.p000a.p001a.C0378m;
import cn.jpush.p000a.p001a.C0380o;
import cn.jpush.p000a.p001a.C0382q;
import cn.jpush.p000a.p001a.C0384s;
import cn.jpush.p000a.p001a.C0386u;
import cn.jpush.p000a.p001a.C0391z;
import cn.jpush.p000a.p001a.aa;
import cn.jpush.p000a.p001a.ab;
import cn.jpush.p000a.p001a.ac;
import cn.jpush.p000a.p001a.ad;
import cn.jpush.p000a.p001a.ae;
import cn.jpush.p000a.p001a.af;
import cn.jpush.p000a.p001a.ai;
import cn.jpush.p000a.p001a.ak;
import cn.jpush.p000a.p001a.am;
import cn.jpush.p000a.p001a.aq;
import cn.jpush.p000a.p001a.as;
import cn.jpush.p000a.p001a.au;
import cn.jpush.p000a.p001a.aw;
import cn.jpush.p000a.p001a.ay;
import cn.jpush.p000a.p001a.ba;
import cn.jpush.p000a.p001a.bc;
import cn.jpush.p000a.p001a.be;
import cn.jpush.p000a.p001a.bi;
import cn.jpush.p000a.p001a.bl;
import cn.jpush.p000a.p001a.bn;
import cn.jpush.p000a.p001a.bp;
import cn.jpush.p005b.p006a.p009c.C0552b;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.p */
public final class C0543p {
    /* renamed from: z */
    private static final String[] f1143z;
    /* renamed from: a */
    int f1144a;
    /* renamed from: b */
    int f1145b;
    /* renamed from: c */
    long f1146c;
    /* renamed from: d */
    String f1147d;
    /* renamed from: e */
    Object f1148e;
    /* renamed from: f */
    af f1149f;
    /* renamed from: g */
    int f1150g = -1;
    /* renamed from: h */
    String f1151h;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 10;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001cXi-F-Zd(\b*[el\u0005i";
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
            case 3: goto L_0x0094;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 40;
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
        r1 = "\u0012L\u001cZ&Bn/G%k!a\b*Yl!I'R;";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "e\u0016w)Z:_n\"\u0012";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "e\u0016d\"\\ Bxv";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "e\u0016t%Ls";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "e\u0016s)[9Yo?M\u0004Sr?I.S;";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "e\u0016s)[9Yo?M\nYe)\u0012";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "e\u0016`<X\"Sxv";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u001cXi-F-Zd(\b\u0000{!/E-\u0016x)\\i\u001b!";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0012|Q9[!k!a\b";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        f1143z = r4;
        return;
    L_0x008c:
        r9 = 73;
        goto L_0x0020;
    L_0x008f:
        r9 = 54;
        goto L_0x0020;
    L_0x0092:
        r9 = 1;
        goto L_0x0020;
    L_0x0094:
        r9 = 76;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.b.p.<clinit>():void");
    }

    public C0543p(int i, int i2, long j, String str, Object obj) {
        this.f1144a = i;
        this.f1145b = 1;
        this.f1146c = j;
        this.f1147d = str;
        this.f1148e = obj;
    }

    public C0543p(byte[] bArr) {
        C0391z a = C0391z.m966a(bArr);
        ad d = a.m971d();
        ab f = a.m973f();
        this.f1144a = d.m164d();
        this.f1145b = d.m166f();
        this.f1146c = d.m168h();
        if (d.m170j() != null) {
            this.f1147d = d.m170j().b();
        }
        this.f1149f = f.m76N();
        switch (this.f1144a) {
            case 1:
                this.f1148e = f.m92d();
                return;
            case 2:
                this.f1148e = f.m94f();
                return;
            case 3:
                this.f1148e = f.m96h();
                return;
            case 4:
                this.f1148e = f.m98j();
                return;
            case 5:
                this.f1148e = f.m100l();
                return;
            case 6:
                this.f1148e = f.m102n();
                return;
            case 7:
                this.f1148e = f.m104p();
                return;
            case 8:
                this.f1148e = f.m106r();
                return;
            case 9:
                this.f1148e = f.m108t();
                return;
            case 10:
                this.f1148e = f.m110v();
                return;
            case 11:
                this.f1148e = f.m112x();
                return;
            case 12:
                this.f1148e = f.m114z();
                return;
            case 13:
                this.f1148e = f.m64B();
                return;
            case 14:
                this.f1148e = f.m66D();
                return;
            case 15:
                this.f1148e = f.m68F();
                return;
            case 16:
                this.f1148e = f.m70H();
                return;
            case 18:
                this.f1148e = f.m72J();
                return;
            case 19:
                this.f1148e = f.m74L();
                return;
            case 23:
                this.f1148e = f.m78P();
                return;
            case 31:
                this.f1148e = f.m80R();
                return;
            case 32:
                this.f1148e = f.m82T();
                return;
            case 33:
                this.f1148e = f.m84V();
                return;
            case 34:
                this.f1148e = f.m86X();
                return;
            case 35:
                this.f1148e = f.m88Z();
                return;
            case 36:
                this.f1148e = f.ab();
                return;
            default:
                System.out.println(new StringBuilder(f1143z[9]).append(new StringBuilder(f1143z[8]).append(this.f1144a).toString()).toString());
                this.f1148e = null;
                return;
        }
    }

    /* renamed from: a */
    public final C0391z m1889a() {
        ae a = ad.m160p().m180a(this.f1144a).m186b(this.f1145b).m181a(this.f1146c);
        if (!(this.f1147d == null || "".equalsIgnoreCase(this.f1147d.trim()))) {
            a.m183a(C4071c.a(this.f1147d));
        }
        aa a2 = C0391z.m967h().m28a(a.m179a());
        int i = this.f1144a;
        Object obj = this.f1148e;
        af afVar = this.f1149f;
        ac ad = ab.ad();
        if (afVar != null) {
            ad.m120a(afVar);
        }
        if (obj != null) {
            switch (i) {
                case 1:
                    ad.m134a((bl) obj);
                    break;
                case 2:
                    ad.m135a((bn) obj);
                    break;
                case 3:
                    ad.m133a((bi) obj);
                    break;
                case 4:
                    ad.m132a((be) obj);
                    break;
                case 5:
                    ad.m137a((C0369d) obj);
                    break;
                case 6:
                    ad.m139a((C0373h) obj);
                    break;
                case 7:
                    ad.m140a((C0375j) obj);
                    break;
                case 8:
                    ad.m142a((C0380o) obj);
                    break;
                case 9:
                    ad.m144a((C0384s) obj);
                    break;
                case 10:
                    ad.m141a((C0378m) obj);
                    break;
                case 11:
                    ad.m143a((C0382q) obj);
                    break;
                case 12:
                    ad.m145a((C0386u) obj);
                    break;
                case 13:
                    ad.m130a((ba) obj);
                    break;
                case 14:
                    ad.m124a((aq) obj);
                    break;
                case 15:
                    ad.m131a((bc) obj);
                    break;
                case 16:
                    ad.m128a((ay) obj);
                    break;
                case 18:
                    ad.m129a((C0367b) obj);
                    break;
                case 19:
                    ad.m138a((C0371f) obj);
                    break;
                case 23:
                    ad.m136a((bp) obj);
                    break;
                case 31:
                    ad.m123a((am) obj);
                    break;
                case 32:
                    ad.m127a((aw) obj);
                    break;
                case 33:
                    ad.m122a((ak) obj);
                    break;
                case 34:
                    ad.m126a((au) obj);
                    break;
                case 35:
                    ad.m121a((ai) obj);
                    break;
                case 36:
                    ad.m125a((as) obj);
                    break;
                default:
                    C0552b.m1906a(new StringBuilder(f1143z[0]).append(i).toString());
                    break;
            }
        }
        return a2.m27a(ad.m118a()).m29a();
    }

    /* renamed from: b */
    public final int m1890b() {
        return this.f1144a;
    }

    public final String toString() {
        StringBuilder append = new StringBuilder(f1143z[1]).append(this.f1144a).append(f1143z[2]).append(this.f1145b).append(f1143z[4]).append(this.f1146c).append(f1143z[7]).append(this.f1147d).append(this.f1150g >= 0 ? new StringBuilder(f1143z[6]).append(this.f1150g).toString() : "");
        String stringBuilder = (this.f1150g < 0 || this.f1151h == null) ? "" : new StringBuilder(f1143z[5]).append(this.f1151h).toString();
        return append.append(stringBuilder).append(this.f1148e == null ? "" : new StringBuilder(f1143z[3]).append(this.f1148e.toString()).toString()).toString();
    }
}
