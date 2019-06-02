package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.bm */
public final class bm extends C4077i<bl, bm> {
    /* renamed from: a */
    private int f274a;
    /* renamed from: b */
    private C4071c f275b = C4071c.f14609a;
    /* renamed from: c */
    private C4071c f276c = C4071c.f14609a;
    /* renamed from: d */
    private int f277d;
    /* renamed from: e */
    private C4071c f278e = C4071c.f14609a;

    private bm() {
    }

    /* renamed from: c */
    private bm m626c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    this.f274a |= 1;
                    this.f275b = c4072d.d();
                    continue;
                case 18:
                    this.f274a |= 2;
                    this.f276c = c4072d.d();
                    continue;
                case 24:
                    this.f274a |= 4;
                    this.f277d = c4072d.c();
                    continue;
                case 34:
                    this.f274a |= 8;
                    this.f278e = c4072d.d();
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
    private bm m628f() {
        return new bm().m631a(m634b());
    }

    /* renamed from: a */
    public final bl m629a() {
        bl b = m634b();
        if (b.m625k()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bm m630a(int i) {
        this.f274a |= 4;
        this.f277d = i;
        return this;
    }

    /* renamed from: a */
    public final bm m631a(bl blVar) {
        if (blVar != bl.m608a()) {
            if (blVar.m616b()) {
                m632a(blVar.m618d());
            }
            if (blVar.m619e()) {
                m635b(blVar.m620f());
            }
            if (blVar.m621g()) {
                m630a(blVar.m622h());
            }
            if (blVar.m623i()) {
                m637c(blVar.m624j());
            }
        }
        return this;
    }

    /* renamed from: a */
    public final bm m632a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f274a |= 1;
        this.f275b = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m633a(C4072d c4072d, C4075g c4075g) {
        return m626c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final bl m634b() {
        int i = 1;
        bl blVar = new bl();
        int i2 = this.f274a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        blVar.f268c = this.f275b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        blVar.f269d = this.f276c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        blVar.f270e = this.f277d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        blVar.f271f = this.f278e;
        blVar.f267b = i;
        return blVar;
    }

    /* renamed from: b */
    public final bm m635b(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f274a |= 2;
        this.f276c = c4071c;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m636b(C4072d c4072d, C4075g c4075g) {
        return m626c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final bm m637c(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f274a |= 8;
        this.f278e = c4071c;
        return this;
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m638c() {
        return m628f();
    }

    public final /* synthetic */ Object clone() {
        return m628f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m639d() {
        return m628f();
    }
}
