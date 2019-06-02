package cn.jpush.p000a.p001a;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.ab */
public final class ab extends C4076h {
    /* renamed from: a */
    private static final ab f18a;
    /* renamed from: A */
    private ai f19A;
    /* renamed from: B */
    private as f20B;
    /* renamed from: C */
    private byte f21C;
    /* renamed from: D */
    private int f22D;
    /* renamed from: b */
    private int f23b;
    /* renamed from: c */
    private bl f24c;
    /* renamed from: d */
    private bn f25d;
    /* renamed from: e */
    private bi f26e;
    /* renamed from: f */
    private be f27f;
    /* renamed from: g */
    private C0369d f28g;
    /* renamed from: h */
    private C0373h f29h;
    /* renamed from: i */
    private C0375j f30i;
    /* renamed from: j */
    private C0380o f31j;
    /* renamed from: k */
    private C0384s f32k;
    /* renamed from: l */
    private C0378m f33l;
    /* renamed from: m */
    private C0382q f34m;
    /* renamed from: n */
    private C0386u f35n;
    /* renamed from: o */
    private ba f36o;
    /* renamed from: p */
    private aq f37p;
    /* renamed from: q */
    private bc f38q;
    /* renamed from: r */
    private ay f39r;
    /* renamed from: s */
    private C0367b f40s;
    /* renamed from: t */
    private C0371f f41t;
    /* renamed from: u */
    private af f42u;
    /* renamed from: v */
    private bp f43v;
    /* renamed from: w */
    private am f44w;
    /* renamed from: x */
    private aw f45x;
    /* renamed from: y */
    private ak f46y;
    /* renamed from: z */
    private au f47z;

    static {
        ab abVar = new ab();
        f18a = abVar;
        abVar.f24c = bl.m608a();
        abVar.f25d = bn.m641a();
        abVar.f26e = bi.m582a();
        abVar.f27f = be.m526a();
        abVar.f28g = C0369d.m696a();
        abVar.f29h = C0373h.m753a();
        abVar.f30i = C0375j.m774a();
        abVar.f31j = C0380o.m832a();
        abVar.f32k = C0384s.m897a();
        abVar.f33l = C0378m.m803a();
        abVar.f34m = C0382q.m868a();
        abVar.f35n = C0386u.m918a();
        abVar.f36o = ba.m448a();
        abVar.f37p = aq.m322a();
        abVar.f38q = bc.m496a();
        abVar.f39r = ay.m416a();
        abVar.f40s = C0367b.m438a();
        abVar.f41t = C0371f.m731a();
        abVar.f42u = af.m191a();
        abVar.f43v = bp.m662a();
        abVar.f44w = am.m260a();
        abVar.f45x = aw.m392a();
        abVar.f46y = ak.m236a();
        abVar.f47z = au.m368a();
        abVar.f19A = ai.m213a();
        abVar.f20B = as.m345a();
    }

    private ab() {
        this.f21C = (byte) -1;
        this.f22D = -1;
    }

    private ab(ac acVar) {
        super((byte) 0);
        this.f21C = (byte) -1;
        this.f22D = -1;
    }

    /* renamed from: a */
    public static ab m35a() {
        return f18a;
    }

    /* renamed from: a */
    public static ac m36a(ab abVar) {
        return new ac().m119a(abVar);
    }

    public static ac ad() {
        return new ac();
    }

    /* renamed from: A */
    public final boolean m63A() {
        return (this.f23b & 4096) == 4096;
    }

    /* renamed from: B */
    public final ba m64B() {
        return this.f36o;
    }

    /* renamed from: C */
    public final boolean m65C() {
        return (this.f23b & 8192) == 8192;
    }

    /* renamed from: D */
    public final aq m66D() {
        return this.f37p;
    }

    /* renamed from: E */
    public final boolean m67E() {
        return (this.f23b & 16384) == 16384;
    }

    /* renamed from: F */
    public final bc m68F() {
        return this.f38q;
    }

    /* renamed from: G */
    public final boolean m69G() {
        return (this.f23b & 32768) == 32768;
    }

    /* renamed from: H */
    public final ay m70H() {
        return this.f39r;
    }

    /* renamed from: I */
    public final boolean m71I() {
        return (this.f23b & 65536) == 65536;
    }

    /* renamed from: J */
    public final C0367b m72J() {
        return this.f40s;
    }

    /* renamed from: K */
    public final boolean m73K() {
        return (this.f23b & 131072) == 131072;
    }

    /* renamed from: L */
    public final C0371f m74L() {
        return this.f41t;
    }

    /* renamed from: M */
    public final boolean m75M() {
        return (this.f23b & 262144) == 262144;
    }

    /* renamed from: N */
    public final af m76N() {
        return this.f42u;
    }

    /* renamed from: O */
    public final boolean m77O() {
        return (this.f23b & 524288) == 524288;
    }

    /* renamed from: P */
    public final bp m78P() {
        return this.f43v;
    }

    /* renamed from: Q */
    public final boolean m79Q() {
        return (this.f23b & 1048576) == 1048576;
    }

    /* renamed from: R */
    public final am m80R() {
        return this.f44w;
    }

    /* renamed from: S */
    public final boolean m81S() {
        return (this.f23b & 2097152) == 2097152;
    }

    /* renamed from: T */
    public final aw m82T() {
        return this.f45x;
    }

    /* renamed from: U */
    public final boolean m83U() {
        return (this.f23b & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) == AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
    }

    /* renamed from: V */
    public final ak m84V() {
        return this.f46y;
    }

    /* renamed from: W */
    public final boolean m85W() {
        return (this.f23b & 8388608) == 8388608;
    }

    /* renamed from: X */
    public final au m86X() {
        return this.f47z;
    }

    /* renamed from: Y */
    public final boolean m87Y() {
        return (this.f23b & 16777216) == 16777216;
    }

    /* renamed from: Z */
    public final ai m88Z() {
        return this.f19A;
    }

    /* renamed from: a */
    public final void m89a(C4073e c4073e) {
        m91c();
        if ((this.f23b & 1) == 1) {
            c4073e.a(1, this.f24c);
        }
        if ((this.f23b & 2) == 2) {
            c4073e.a(2, this.f25d);
        }
        if ((this.f23b & 4) == 4) {
            c4073e.a(3, this.f26e);
        }
        if ((this.f23b & 8) == 8) {
            c4073e.a(4, this.f27f);
        }
        if ((this.f23b & 16) == 16) {
            c4073e.a(5, this.f28g);
        }
        if ((this.f23b & 32) == 32) {
            c4073e.a(6, this.f29h);
        }
        if ((this.f23b & 64) == 64) {
            c4073e.a(7, this.f30i);
        }
        if ((this.f23b & 128) == 128) {
            c4073e.a(8, this.f31j);
        }
        if ((this.f23b & 256) == 256) {
            c4073e.a(9, this.f32k);
        }
        if ((this.f23b & 512) == 512) {
            c4073e.a(10, this.f33l);
        }
        if ((this.f23b & 1024) == 1024) {
            c4073e.a(11, this.f34m);
        }
        if ((this.f23b & 2048) == 2048) {
            c4073e.a(12, this.f35n);
        }
        if ((this.f23b & 4096) == 4096) {
            c4073e.a(13, this.f36o);
        }
        if ((this.f23b & 8192) == 8192) {
            c4073e.a(14, this.f37p);
        }
        if ((this.f23b & 16384) == 16384) {
            c4073e.a(15, this.f38q);
        }
        if ((this.f23b & 32768) == 32768) {
            c4073e.a(16, this.f39r);
        }
        if ((this.f23b & 65536) == 65536) {
            c4073e.a(18, this.f40s);
        }
        if ((this.f23b & 131072) == 131072) {
            c4073e.a(19, this.f41t);
        }
        if ((this.f23b & 262144) == 262144) {
            c4073e.a(20, this.f42u);
        }
        if ((this.f23b & 524288) == 524288) {
            c4073e.a(23, this.f43v);
        }
        if ((this.f23b & 1048576) == 1048576) {
            c4073e.a(31, this.f44w);
        }
        if ((this.f23b & 2097152) == 2097152) {
            c4073e.a(32, this.f45x);
        }
        if ((this.f23b & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) == AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) {
            c4073e.a(33, this.f46y);
        }
        if ((this.f23b & 8388608) == 8388608) {
            c4073e.a(34, this.f47z);
        }
        if ((this.f23b & 16777216) == 16777216) {
            c4073e.a(35, this.f19A);
        }
        if ((this.f23b & 33554432) == 33554432) {
            c4073e.a(36, this.f20B);
        }
    }

    public final boolean aa() {
        return (this.f23b & 33554432) == 33554432;
    }

    public final as ab() {
        return this.f20B;
    }

    public final boolean ac() {
        byte b = this.f21C;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f21C = (byte) 1;
            return true;
        }
    }

    /* renamed from: b */
    public final boolean m90b() {
        return (this.f23b & 1) == 1;
    }

    /* renamed from: c */
    public final int m91c() {
        int i = this.f22D;
        if (i == -1) {
            i = 0;
            if ((this.f23b & 1) == 1) {
                i = C4073e.b(1, this.f24c) + 0;
            }
            if ((this.f23b & 2) == 2) {
                i += C4073e.b(2, this.f25d);
            }
            if ((this.f23b & 4) == 4) {
                i += C4073e.b(3, this.f26e);
            }
            if ((this.f23b & 8) == 8) {
                i += C4073e.b(4, this.f27f);
            }
            if ((this.f23b & 16) == 16) {
                i += C4073e.b(5, this.f28g);
            }
            if ((this.f23b & 32) == 32) {
                i += C4073e.b(6, this.f29h);
            }
            if ((this.f23b & 64) == 64) {
                i += C4073e.b(7, this.f30i);
            }
            if ((this.f23b & 128) == 128) {
                i += C4073e.b(8, this.f31j);
            }
            if ((this.f23b & 256) == 256) {
                i += C4073e.b(9, this.f32k);
            }
            if ((this.f23b & 512) == 512) {
                i += C4073e.b(10, this.f33l);
            }
            if ((this.f23b & 1024) == 1024) {
                i += C4073e.b(11, this.f34m);
            }
            if ((this.f23b & 2048) == 2048) {
                i += C4073e.b(12, this.f35n);
            }
            if ((this.f23b & 4096) == 4096) {
                i += C4073e.b(13, this.f36o);
            }
            if ((this.f23b & 8192) == 8192) {
                i += C4073e.b(14, this.f37p);
            }
            if ((this.f23b & 16384) == 16384) {
                i += C4073e.b(15, this.f38q);
            }
            if ((this.f23b & 32768) == 32768) {
                i += C4073e.b(16, this.f39r);
            }
            if ((this.f23b & 65536) == 65536) {
                i += C4073e.b(18, this.f40s);
            }
            if ((this.f23b & 131072) == 131072) {
                i += C4073e.b(19, this.f41t);
            }
            if ((this.f23b & 262144) == 262144) {
                i += C4073e.b(20, this.f42u);
            }
            if ((this.f23b & 524288) == 524288) {
                i += C4073e.b(23, this.f43v);
            }
            if ((this.f23b & 1048576) == 1048576) {
                i += C4073e.b(31, this.f44w);
            }
            if ((this.f23b & 2097152) == 2097152) {
                i += C4073e.b(32, this.f45x);
            }
            if ((this.f23b & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) == AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) {
                i += C4073e.b(33, this.f46y);
            }
            if ((this.f23b & 8388608) == 8388608) {
                i += C4073e.b(34, this.f47z);
            }
            if ((this.f23b & 16777216) == 16777216) {
                i += C4073e.b(35, this.f19A);
            }
            if ((this.f23b & 33554432) == 33554432) {
                i += C4073e.b(36, this.f20B);
            }
            this.f22D = i;
        }
        return i;
    }

    /* renamed from: d */
    public final bl m92d() {
        return this.f24c;
    }

    /* renamed from: e */
    public final boolean m93e() {
        return (this.f23b & 2) == 2;
    }

    /* renamed from: f */
    public final bn m94f() {
        return this.f25d;
    }

    /* renamed from: g */
    public final boolean m95g() {
        return (this.f23b & 4) == 4;
    }

    /* renamed from: h */
    public final bi m96h() {
        return this.f26e;
    }

    /* renamed from: i */
    public final boolean m97i() {
        return (this.f23b & 8) == 8;
    }

    /* renamed from: j */
    public final be m98j() {
        return this.f27f;
    }

    /* renamed from: k */
    public final boolean m99k() {
        return (this.f23b & 16) == 16;
    }

    /* renamed from: l */
    public final C0369d m100l() {
        return this.f28g;
    }

    /* renamed from: m */
    public final boolean m101m() {
        return (this.f23b & 32) == 32;
    }

    /* renamed from: n */
    public final C0373h m102n() {
        return this.f29h;
    }

    /* renamed from: o */
    public final boolean m103o() {
        return (this.f23b & 64) == 64;
    }

    /* renamed from: p */
    public final C0375j m104p() {
        return this.f30i;
    }

    /* renamed from: q */
    public final boolean m105q() {
        return (this.f23b & 128) == 128;
    }

    /* renamed from: r */
    public final C0380o m106r() {
        return this.f31j;
    }

    /* renamed from: s */
    public final boolean m107s() {
        return (this.f23b & 256) == 256;
    }

    /* renamed from: t */
    public final C0384s m108t() {
        return this.f32k;
    }

    /* renamed from: u */
    public final boolean m109u() {
        return (this.f23b & 512) == 512;
    }

    /* renamed from: v */
    public final C0378m m110v() {
        return this.f33l;
    }

    /* renamed from: w */
    public final boolean m111w() {
        return (this.f23b & 1024) == 1024;
    }

    /* renamed from: x */
    public final C0382q m112x() {
        return this.f34m;
    }

    /* renamed from: y */
    public final boolean m113y() {
        return (this.f23b & 2048) == 2048;
    }

    /* renamed from: z */
    public final C0386u m114z() {
        return this.f35n;
    }
}
