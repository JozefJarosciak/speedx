package cn.jpush.p000a.p001a;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.ac */
public final class ac extends C4077i<ab, ac> {
    /* renamed from: A */
    private as f48A = as.m345a();
    /* renamed from: a */
    private int f49a;
    /* renamed from: b */
    private bl f50b = bl.m608a();
    /* renamed from: c */
    private bn f51c = bn.m641a();
    /* renamed from: d */
    private bi f52d = bi.m582a();
    /* renamed from: e */
    private be f53e = be.m526a();
    /* renamed from: f */
    private C0369d f54f = C0369d.m696a();
    /* renamed from: g */
    private C0373h f55g = C0373h.m753a();
    /* renamed from: h */
    private C0375j f56h = C0375j.m774a();
    /* renamed from: i */
    private C0380o f57i = C0380o.m832a();
    /* renamed from: j */
    private C0384s f58j = C0384s.m897a();
    /* renamed from: k */
    private C0378m f59k = C0378m.m803a();
    /* renamed from: l */
    private C0382q f60l = C0382q.m868a();
    /* renamed from: m */
    private C0386u f61m = C0386u.m918a();
    /* renamed from: n */
    private ba f62n = ba.m448a();
    /* renamed from: o */
    private aq f63o = aq.m322a();
    /* renamed from: p */
    private bc f64p = bc.m496a();
    /* renamed from: q */
    private ay f65q = ay.m416a();
    /* renamed from: r */
    private C0367b f66r = C0367b.m438a();
    /* renamed from: s */
    private C0371f f67s = C0371f.m731a();
    /* renamed from: t */
    private af f68t = af.m191a();
    /* renamed from: u */
    private bp f69u = bp.m662a();
    /* renamed from: v */
    private am f70v = am.m260a();
    /* renamed from: w */
    private aw f71w = aw.m392a();
    /* renamed from: x */
    private ak f72x = ak.m236a();
    /* renamed from: y */
    private au f73y = au.m368a();
    /* renamed from: z */
    private ai f74z = ai.m213a();

    private ac() {
    }

    /* renamed from: c */
    private ac m115c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            Object l;
            switch (a) {
                case 0:
                    break;
                case 10:
                    l = bl.m614l();
                    if (((this.f49a & 1) == 1 ? 1 : null) != null) {
                        l.m631a(this.f50b);
                    }
                    c4072d.a(l, c4075g);
                    m134a(l.m634b());
                    continue;
                case 18:
                    l = bn.m644f();
                    if (((this.f49a & 2) == 2 ? 1 : null) != null) {
                        l.m654a(this.f51c);
                    }
                    c4072d.a(l, c4075g);
                    m135a(l.m657b());
                    continue;
                case 26:
                    l = bi.m585j();
                    if (((this.f49a & 4) == 4 ? 1 : null) != null) {
                        l.m601a(this.f52d);
                    }
                    c4072d.a(l, c4075g);
                    m133a(l.m603b());
                    continue;
                case 34:
                    l = be.m530j();
                    if (((this.f49a & 8) == 8 ? 1 : null) != null) {
                        l.m545a(this.f53e);
                    }
                    c4072d.a(l, c4075g);
                    m132a(l.m548b());
                    continue;
                case 42:
                    l = C0369d.m702n();
                    if (((this.f49a & 16) == 16 ? 1 : null) != null) {
                        l.m722a(this.f54f);
                    }
                    c4072d.a(l, c4075g);
                    m137a(l.m725b());
                    continue;
                case 50:
                    l = C0373h.m755f();
                    if (((this.f49a & 32) == 32 ? 1 : null) != null) {
                        l.m766a(this.f55g);
                    }
                    c4072d.a(l, c4075g);
                    m139a(l.m768b());
                    continue;
                case 58:
                    l = C0375j.m778j();
                    if (((this.f49a & 64) == 64 ? 1 : null) != null) {
                        l.m793a(this.f56h);
                    }
                    c4072d.a(l, c4075g);
                    m140a(l.m796b());
                    continue;
                case 66:
                    l = C0380o.m838n();
                    if (((this.f49a & 128) == 128 ? 1 : null) != null) {
                        l.m857a(this.f57i);
                    }
                    c4072d.a(l, c4075g);
                    m142a(l.m860b());
                    continue;
                case 74:
                    l = C0384s.m899f();
                    if (((this.f49a & 256) == 256 ? 1 : null) != null) {
                        l.m910a(this.f58j);
                    }
                    c4072d.a(l, c4075g);
                    m144a(l.m912b());
                    continue;
                case 82:
                    l = C0378m.m808h();
                    if (((this.f49a & 512) == 512 ? 1 : null) != null) {
                        l.m823a(this.f59k);
                    }
                    c4072d.a(l, c4075g);
                    m141a(l.m826b());
                    continue;
                case 90:
                    l = C0382q.m873h();
                    if (((this.f49a & 1024) == 1024 ? 1 : null) != null) {
                        l.m888a(this.f60l);
                    }
                    c4072d.a(l, c4075g);
                    m143a(l.m891b());
                    continue;
                case 98:
                    l = C0386u.m922j();
                    if (((this.f49a & 2048) == 2048 ? 1 : null) != null) {
                        l.m937a(this.f61m);
                    }
                    c4072d.a(l, c4075g);
                    m145a(l.m940b());
                    continue;
                case 106:
                    l = ba.m459t();
                    if (((this.f49a & 4096) == 4096 ? 1 : null) != null) {
                        l.m486a(this.f62n);
                    }
                    c4072d.a(l, c4075g);
                    m130a(l.m488b());
                    continue;
                case 114:
                    l = aq.m326e();
                    if (((this.f49a & 8192) == 8192 ? 1 : null) != null) {
                        l.m337a(this.f63o);
                    }
                    c4072d.a(l, c4075g);
                    m124a(l.m339b());
                    continue;
                case AVException.INVALID_FILE_NAME /*122*/:
                    l = bc.m502m();
                    if (((this.f49a & 16384) == 16384 ? 1 : null) != null) {
                        l.m519a(this.f64p);
                    }
                    c4072d.a(l, c4075g);
                    m131a(l.m518a());
                    continue;
                case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                    l = ay.m420i();
                    if (((this.f49a & 32768) == 32768 ? 1 : null) != null) {
                        l.m433a(this.f65q);
                    }
                    c4072d.a(l, c4075g);
                    m128a(l.m432a());
                    continue;
                case 146:
                    l = C0367b.m442d();
                    if (((this.f49a & 65536) == 65536 ? 1 : null) != null) {
                        l.m687a(this.f66r);
                    }
                    c4072d.a(l, c4075g);
                    m129a(l.m690b());
                    continue;
                case Opcodes.IFNE /*154*/:
                    l = C0371f.m735d();
                    if (((this.f49a & 131072) == 131072 ? 1 : null) != null) {
                        l.m744a(this.f67s);
                    }
                    c4072d.a(l, c4075g);
                    m138a(l.m747b());
                    continue;
                case 162:
                    l = af.m195g();
                    if (((this.f49a & 262144) == 262144 ? 1 : null) != null) {
                        l.m206a(this.f68t);
                    }
                    c4072d.a(l, c4075g);
                    m120a(l.m205a());
                    continue;
                case 186:
                    l = bp.m665f();
                    if (((this.f49a & 524288) == 524288 ? 1 : null) != null) {
                        l.m675a(this.f69u);
                    }
                    c4072d.a(l, c4075g);
                    m136a(l.m678b());
                    continue;
                case 250:
                    l = am.m263h();
                    if (((this.f49a & 1048576) == 1048576 ? 1 : null) != null) {
                        l.m276a(this.f70v);
                    }
                    c4072d.a(l, c4075g);
                    m123a(l.m278b());
                    continue;
                case 258:
                    l = aw.m395h();
                    if (((this.f49a & 2097152) == 2097152 ? 1 : null) != null) {
                        l.m408a(this.f71w);
                    }
                    c4072d.a(l, c4075g);
                    m127a(l.m410b());
                    continue;
                case 266:
                    l = ak.m239h();
                    if (((this.f49a & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) == AccessibilityEventCompat.TYPE_WINDOWS_CHANGED ? 1 : null) != null) {
                        l.m252a(this.f72x);
                    }
                    c4072d.a(l, c4075g);
                    m122a(l.m254b());
                    continue;
                case 274:
                    l = au.m371h();
                    if (((this.f49a & 8388608) == 8388608 ? 1 : null) != null) {
                        l.m384a(this.f73y);
                    }
                    c4072d.a(l, c4075g);
                    m126a(l.m386b());
                    continue;
                case 282:
                    l = ai.m216h();
                    if (((this.f49a & 16777216) == 16777216 ? 1 : null) != null) {
                        l.m228a(this.f74z);
                    }
                    c4072d.a(l, c4075g);
                    m121a(l.m230b());
                    continue;
                case 290:
                    l = as.m348h();
                    if (((this.f49a & 33554432) == 33554432 ? 1 : null) != null) {
                        l.m360a(this.f48A);
                    }
                    c4072d.a(l, c4075g);
                    m125a(l.m362b());
                    continue;
                default:
                    if (!c4072d.b(a)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    /* renamed from: f */
    private ac m117f() {
        return new ac().m119a(m147b());
    }

    /* renamed from: a */
    public final ab m118a() {
        ab b = m147b();
        if (b.ac()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final ac m119a(ab abVar) {
        if (abVar != ab.m35a()) {
            if (abVar.m90b()) {
                bl d = abVar.m92d();
                if ((this.f49a & 1) != 1 || this.f50b == bl.m608a()) {
                    this.f50b = d;
                } else {
                    this.f50b = bl.m609a(this.f50b).m631a(d).m634b();
                }
                this.f49a |= 1;
            }
            if (abVar.m93e()) {
                bn f = abVar.m94f();
                if ((this.f49a & 2) != 2 || this.f51c == bn.m641a()) {
                    this.f51c = f;
                } else {
                    this.f51c = bn.m642a(this.f51c).m654a(f).m657b();
                }
                this.f49a |= 2;
            }
            if (abVar.m95g()) {
                bi h = abVar.m96h();
                if ((this.f49a & 4) != 4 || this.f52d == bi.m582a()) {
                    this.f52d = h;
                } else {
                    this.f52d = bi.m583a(this.f52d).m601a(h).m603b();
                }
                this.f49a |= 4;
            }
            if (abVar.m97i()) {
                be j = abVar.m98j();
                if ((this.f49a & 8) != 8 || this.f53e == be.m526a()) {
                    this.f53e = j;
                } else {
                    this.f53e = be.m527a(this.f53e).m545a(j).m548b();
                }
                this.f49a |= 8;
            }
            if (abVar.m99k()) {
                C0369d l = abVar.m100l();
                if ((this.f49a & 16) != 16 || this.f54f == C0369d.m696a()) {
                    this.f54f = l;
                } else {
                    this.f54f = C0369d.m697a(this.f54f).m722a(l).m725b();
                }
                this.f49a |= 16;
            }
            if (abVar.m101m()) {
                C0373h n = abVar.m102n();
                if ((this.f49a & 32) != 32 || this.f55g == C0373h.m753a()) {
                    this.f55g = n;
                } else {
                    this.f55g = C0373h.m754a(this.f55g).m766a(n).m768b();
                }
                this.f49a |= 32;
            }
            if (abVar.m103o()) {
                C0375j p = abVar.m104p();
                if ((this.f49a & 64) != 64 || this.f56h == C0375j.m774a()) {
                    this.f56h = p;
                } else {
                    this.f56h = C0375j.m775a(this.f56h).m793a(p).m796b();
                }
                this.f49a |= 64;
            }
            if (abVar.m105q()) {
                C0380o r = abVar.m106r();
                if ((this.f49a & 128) != 128 || this.f57i == C0380o.m832a()) {
                    this.f57i = r;
                } else {
                    this.f57i = C0380o.m833a(this.f57i).m857a(r).m860b();
                }
                this.f49a |= 128;
            }
            if (abVar.m107s()) {
                C0384s t = abVar.m108t();
                if ((this.f49a & 256) != 256 || this.f58j == C0384s.m897a()) {
                    this.f58j = t;
                } else {
                    this.f58j = C0384s.m898a(this.f58j).m910a(t).m912b();
                }
                this.f49a |= 256;
            }
            if (abVar.m109u()) {
                C0378m v = abVar.m110v();
                if ((this.f49a & 512) != 512 || this.f59k == C0378m.m803a()) {
                    this.f59k = v;
                } else {
                    this.f59k = C0378m.m804a(this.f59k).m823a(v).m826b();
                }
                this.f49a |= 512;
            }
            if (abVar.m111w()) {
                C0382q x = abVar.m112x();
                if ((this.f49a & 1024) != 1024 || this.f60l == C0382q.m868a()) {
                    this.f60l = x;
                } else {
                    this.f60l = C0382q.m869a(this.f60l).m888a(x).m891b();
                }
                this.f49a |= 1024;
            }
            if (abVar.m113y()) {
                C0386u z = abVar.m114z();
                if ((this.f49a & 2048) != 2048 || this.f61m == C0386u.m918a()) {
                    this.f61m = z;
                } else {
                    this.f61m = C0386u.m919a(this.f61m).m937a(z).m940b();
                }
                this.f49a |= 2048;
            }
            if (abVar.m63A()) {
                ba B = abVar.m64B();
                if ((this.f49a & 4096) != 4096 || this.f62n == ba.m448a()) {
                    this.f62n = B;
                } else {
                    this.f62n = ba.m449a(this.f62n).m486a(B).m488b();
                }
                this.f49a |= 4096;
            }
            if (abVar.m65C()) {
                aq D = abVar.m66D();
                if ((this.f49a & 8192) != 8192 || this.f63o == aq.m322a()) {
                    this.f63o = D;
                } else {
                    this.f63o = aq.m323a(this.f63o).m337a(D).m339b();
                }
                this.f49a |= 8192;
            }
            if (abVar.m67E()) {
                bc F = abVar.m68F();
                if ((this.f49a & 16384) != 16384 || this.f64p == bc.m496a()) {
                    this.f64p = F;
                } else {
                    this.f64p = bc.m497a(this.f64p).m519a(F).m518a();
                }
                this.f49a |= 16384;
            }
            if (abVar.m69G()) {
                ay H = abVar.m70H();
                if ((this.f49a & 32768) != 32768 || this.f65q == ay.m416a()) {
                    this.f65q = H;
                } else {
                    this.f65q = ay.m417a(this.f65q).m433a(H).m432a();
                }
                this.f49a |= 32768;
            }
            if (abVar.m71I()) {
                C0367b J = abVar.m72J();
                if ((this.f49a & 65536) != 65536 || this.f66r == C0367b.m438a()) {
                    this.f66r = J;
                } else {
                    this.f66r = C0367b.m439a(this.f66r).m687a(J).m690b();
                }
                this.f49a |= 65536;
            }
            if (abVar.m73K()) {
                C0371f L = abVar.m74L();
                if ((this.f49a & 131072) != 131072 || this.f67s == C0371f.m731a()) {
                    this.f67s = L;
                } else {
                    this.f67s = C0371f.m732a(this.f67s).m744a(L).m747b();
                }
                this.f49a |= 131072;
            }
            if (abVar.m75M()) {
                af N = abVar.m76N();
                if ((this.f49a & 262144) != 262144 || this.f68t == af.m191a()) {
                    this.f68t = N;
                } else {
                    this.f68t = af.m192a(this.f68t).m206a(N).m205a();
                }
                this.f49a |= 262144;
            }
            if (abVar.m77O()) {
                bp P = abVar.m78P();
                if ((this.f49a & 524288) != 524288 || this.f69u == bp.m662a()) {
                    this.f69u = P;
                } else {
                    this.f69u = bp.m663a(this.f69u).m675a(P).m678b();
                }
                this.f49a |= 524288;
            }
            if (abVar.m79Q()) {
                am R = abVar.m80R();
                if ((this.f49a & 1048576) != 1048576 || this.f70v == am.m260a()) {
                    this.f70v = R;
                } else {
                    this.f70v = am.m261a(this.f70v).m276a(R).m278b();
                }
                this.f49a |= 1048576;
            }
            if (abVar.m81S()) {
                aw T = abVar.m82T();
                if ((this.f49a & 2097152) != 2097152 || this.f71w == aw.m392a()) {
                    this.f71w = T;
                } else {
                    this.f71w = aw.m393a(this.f71w).m408a(T).m410b();
                }
                this.f49a |= 2097152;
            }
            if (abVar.m83U()) {
                ak V = abVar.m84V();
                if ((this.f49a & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) != AccessibilityEventCompat.TYPE_WINDOWS_CHANGED || this.f72x == ak.m236a()) {
                    this.f72x = V;
                } else {
                    this.f72x = ak.m237a(this.f72x).m252a(V).m254b();
                }
                this.f49a |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
            }
            if (abVar.m85W()) {
                au X = abVar.m86X();
                if ((this.f49a & 8388608) != 8388608 || this.f73y == au.m368a()) {
                    this.f73y = X;
                } else {
                    this.f73y = au.m369a(this.f73y).m384a(X).m386b();
                }
                this.f49a |= 8388608;
            }
            if (abVar.m87Y()) {
                ai Z = abVar.m88Z();
                if ((this.f49a & 16777216) != 16777216 || this.f74z == ai.m213a()) {
                    this.f74z = Z;
                } else {
                    this.f74z = ai.m214a(this.f74z).m228a(Z).m230b();
                }
                this.f49a |= 16777216;
            }
            if (abVar.aa()) {
                as ab = abVar.ab();
                if ((this.f49a & 33554432) != 33554432 || this.f48A == as.m345a()) {
                    this.f48A = ab;
                } else {
                    this.f48A = as.m346a(this.f48A).m360a(ab).m362b();
                }
                this.f49a |= 33554432;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final ac m120a(af afVar) {
        if (afVar == null) {
            throw new NullPointerException();
        }
        this.f68t = afVar;
        this.f49a |= 262144;
        return this;
    }

    /* renamed from: a */
    public final ac m121a(ai aiVar) {
        if (aiVar == null) {
            throw new NullPointerException();
        }
        this.f74z = aiVar;
        this.f49a |= 16777216;
        return this;
    }

    /* renamed from: a */
    public final ac m122a(ak akVar) {
        if (akVar == null) {
            throw new NullPointerException();
        }
        this.f72x = akVar;
        this.f49a |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
        return this;
    }

    /* renamed from: a */
    public final ac m123a(am amVar) {
        if (amVar == null) {
            throw new NullPointerException();
        }
        this.f70v = amVar;
        this.f49a |= 1048576;
        return this;
    }

    /* renamed from: a */
    public final ac m124a(aq aqVar) {
        if (aqVar == null) {
            throw new NullPointerException();
        }
        this.f63o = aqVar;
        this.f49a |= 8192;
        return this;
    }

    /* renamed from: a */
    public final ac m125a(as asVar) {
        if (asVar == null) {
            throw new NullPointerException();
        }
        this.f48A = asVar;
        this.f49a |= 33554432;
        return this;
    }

    /* renamed from: a */
    public final ac m126a(au auVar) {
        if (auVar == null) {
            throw new NullPointerException();
        }
        this.f73y = auVar;
        this.f49a |= 8388608;
        return this;
    }

    /* renamed from: a */
    public final ac m127a(aw awVar) {
        if (awVar == null) {
            throw new NullPointerException();
        }
        this.f71w = awVar;
        this.f49a |= 2097152;
        return this;
    }

    /* renamed from: a */
    public final ac m128a(ay ayVar) {
        if (ayVar == null) {
            throw new NullPointerException();
        }
        this.f65q = ayVar;
        this.f49a |= 32768;
        return this;
    }

    /* renamed from: a */
    public final ac m129a(C0367b c0367b) {
        if (c0367b == null) {
            throw new NullPointerException();
        }
        this.f66r = c0367b;
        this.f49a |= 65536;
        return this;
    }

    /* renamed from: a */
    public final ac m130a(ba baVar) {
        if (baVar == null) {
            throw new NullPointerException();
        }
        this.f62n = baVar;
        this.f49a |= 4096;
        return this;
    }

    /* renamed from: a */
    public final ac m131a(bc bcVar) {
        if (bcVar == null) {
            throw new NullPointerException();
        }
        this.f64p = bcVar;
        this.f49a |= 16384;
        return this;
    }

    /* renamed from: a */
    public final ac m132a(be beVar) {
        if (beVar == null) {
            throw new NullPointerException();
        }
        this.f53e = beVar;
        this.f49a |= 8;
        return this;
    }

    /* renamed from: a */
    public final ac m133a(bi biVar) {
        if (biVar == null) {
            throw new NullPointerException();
        }
        this.f52d = biVar;
        this.f49a |= 4;
        return this;
    }

    /* renamed from: a */
    public final ac m134a(bl blVar) {
        if (blVar == null) {
            throw new NullPointerException();
        }
        this.f50b = blVar;
        this.f49a |= 1;
        return this;
    }

    /* renamed from: a */
    public final ac m135a(bn bnVar) {
        if (bnVar == null) {
            throw new NullPointerException();
        }
        this.f51c = bnVar;
        this.f49a |= 2;
        return this;
    }

    /* renamed from: a */
    public final ac m136a(bp bpVar) {
        if (bpVar == null) {
            throw new NullPointerException();
        }
        this.f69u = bpVar;
        this.f49a |= 524288;
        return this;
    }

    /* renamed from: a */
    public final ac m137a(C0369d c0369d) {
        if (c0369d == null) {
            throw new NullPointerException();
        }
        this.f54f = c0369d;
        this.f49a |= 16;
        return this;
    }

    /* renamed from: a */
    public final ac m138a(C0371f c0371f) {
        if (c0371f == null) {
            throw new NullPointerException();
        }
        this.f67s = c0371f;
        this.f49a |= 131072;
        return this;
    }

    /* renamed from: a */
    public final ac m139a(C0373h c0373h) {
        if (c0373h == null) {
            throw new NullPointerException();
        }
        this.f55g = c0373h;
        this.f49a |= 32;
        return this;
    }

    /* renamed from: a */
    public final ac m140a(C0375j c0375j) {
        if (c0375j == null) {
            throw new NullPointerException();
        }
        this.f56h = c0375j;
        this.f49a |= 64;
        return this;
    }

    /* renamed from: a */
    public final ac m141a(C0378m c0378m) {
        if (c0378m == null) {
            throw new NullPointerException();
        }
        this.f59k = c0378m;
        this.f49a |= 512;
        return this;
    }

    /* renamed from: a */
    public final ac m142a(C0380o c0380o) {
        if (c0380o == null) {
            throw new NullPointerException();
        }
        this.f57i = c0380o;
        this.f49a |= 128;
        return this;
    }

    /* renamed from: a */
    public final ac m143a(C0382q c0382q) {
        if (c0382q == null) {
            throw new NullPointerException();
        }
        this.f60l = c0382q;
        this.f49a |= 1024;
        return this;
    }

    /* renamed from: a */
    public final ac m144a(C0384s c0384s) {
        if (c0384s == null) {
            throw new NullPointerException();
        }
        this.f58j = c0384s;
        this.f49a |= 256;
        return this;
    }

    /* renamed from: a */
    public final ac m145a(C0386u c0386u) {
        if (c0386u == null) {
            throw new NullPointerException();
        }
        this.f61m = c0386u;
        this.f49a |= 2048;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m146a(C4072d c4072d, C4075g c4075g) {
        return m115c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final ab m147b() {
        ab abVar = new ab();
        int i = this.f49a;
        int i2 = 0;
        if ((i & 1) == 1) {
            i2 = 1;
        }
        abVar.f24c = this.f50b;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        abVar.f25d = this.f51c;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        abVar.f26e = this.f52d;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        abVar.f27f = this.f53e;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        abVar.f28g = this.f54f;
        if ((i & 32) == 32) {
            i2 |= 32;
        }
        abVar.f29h = this.f55g;
        if ((i & 64) == 64) {
            i2 |= 64;
        }
        abVar.f30i = this.f56h;
        if ((i & 128) == 128) {
            i2 |= 128;
        }
        abVar.f31j = this.f57i;
        if ((i & 256) == 256) {
            i2 |= 256;
        }
        abVar.f32k = this.f58j;
        if ((i & 512) == 512) {
            i2 |= 512;
        }
        abVar.f33l = this.f59k;
        if ((i & 1024) == 1024) {
            i2 |= 1024;
        }
        abVar.f34m = this.f60l;
        if ((i & 2048) == 2048) {
            i2 |= 2048;
        }
        abVar.f35n = this.f61m;
        if ((i & 4096) == 4096) {
            i2 |= 4096;
        }
        abVar.f36o = this.f62n;
        if ((i & 8192) == 8192) {
            i2 |= 8192;
        }
        abVar.f37p = this.f63o;
        if ((i & 16384) == 16384) {
            i2 |= 16384;
        }
        abVar.f38q = this.f64p;
        if ((i & 32768) == 32768) {
            i2 |= 32768;
        }
        abVar.f39r = this.f65q;
        if ((i & 65536) == 65536) {
            i2 |= 65536;
        }
        abVar.f40s = this.f66r;
        if ((i & 131072) == 131072) {
            i2 |= 131072;
        }
        abVar.f41t = this.f67s;
        if ((i & 262144) == 262144) {
            i2 |= 262144;
        }
        abVar.f42u = this.f68t;
        if ((i & 524288) == 524288) {
            i2 |= 524288;
        }
        abVar.f43v = this.f69u;
        if ((1048576 & i) == 1048576) {
            i2 |= 1048576;
        }
        abVar.f44w = this.f70v;
        if ((2097152 & i) == 2097152) {
            i2 |= 2097152;
        }
        abVar.f45x = this.f71w;
        if ((AccessibilityEventCompat.TYPE_WINDOWS_CHANGED & i) == AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) {
            i2 |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
        }
        abVar.f46y = this.f72x;
        if ((8388608 & i) == 8388608) {
            i2 |= 8388608;
        }
        abVar.f47z = this.f73y;
        if ((16777216 & i) == 16777216) {
            i2 |= 16777216;
        }
        abVar.f19A = this.f74z;
        if ((i & 33554432) == 33554432) {
            i2 |= 33554432;
        }
        abVar.f20B = this.f48A;
        abVar.f23b = i2;
        return abVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m148b(C4072d c4072d, C4075g c4075g) {
        return m115c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m149c() {
        return m117f();
    }

    public final /* synthetic */ Object clone() {
        return m117f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m150d() {
        return m117f();
    }
}
