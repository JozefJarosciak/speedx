package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.ae */
public final class ae extends C4077i<ad, ae> {
    /* renamed from: a */
    private int f85a;
    /* renamed from: b */
    private int f86b;
    /* renamed from: c */
    private int f87c;
    /* renamed from: d */
    private long f88d;
    /* renamed from: e */
    private C4071c f89e = C4071c.f14609a;
    /* renamed from: f */
    private C0389x f90f = C0389x.m945a();
    /* renamed from: g */
    private int f91g;

    private ae() {
    }

    /* renamed from: c */
    private ae m176c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f85a |= 1;
                    this.f86b = c4072d.c();
                    continue;
                case 16:
                    this.f85a |= 2;
                    this.f87c = c4072d.c();
                    continue;
                case 24:
                    this.f85a |= 4;
                    this.f88d = c4072d.b();
                    continue;
                case 34:
                    this.f85a |= 8;
                    this.f89e = c4072d.d();
                    continue;
                case 42:
                    Object b = C0389x.m948b();
                    if (((this.f85a & 16) == 16 ? 1 : null) != null) {
                        b.m957a(this.f90f);
                    }
                    c4072d.a(b, c4075g);
                    C0389x a2 = b.m956a();
                    if (a2 == null) {
                        throw new NullPointerException();
                    }
                    this.f90f = a2;
                    this.f85a |= 16;
                    continue;
                case 48:
                    this.f85a |= 32;
                    this.f91g = c4072d.c();
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
    private ae m178f() {
        return new ae().m182a(m185b());
    }

    /* renamed from: a */
    public final ad m179a() {
        ad b = m185b();
        if (b.m175o()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final ae m180a(int i) {
        this.f85a |= 1;
        this.f86b = i;
        return this;
    }

    /* renamed from: a */
    public final ae m181a(long j) {
        this.f85a |= 4;
        this.f88d = j;
        return this;
    }

    /* renamed from: a */
    public final ae m182a(ad adVar) {
        if (adVar != ad.m153a()) {
            if (adVar.m162b()) {
                m180a(adVar.m164d());
            }
            if (adVar.m165e()) {
                m186b(adVar.m166f());
            }
            if (adVar.m167g()) {
                m181a(adVar.m168h());
            }
            if (adVar.m169i()) {
                m183a(adVar.m170j());
            }
            if (adVar.m171k()) {
                C0389x l = adVar.m172l();
                if ((this.f85a & 16) != 16 || this.f90f == C0389x.m945a()) {
                    this.f90f = l;
                } else {
                    this.f90f = C0389x.m946a(this.f90f).m957a(l).m956a();
                }
                this.f85a |= 16;
            }
            if (adVar.m173m()) {
                int n = adVar.m174n();
                this.f85a |= 32;
                this.f91g = n;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final ae m183a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f85a |= 8;
        this.f89e = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m184a(C4072d c4072d, C4075g c4075g) {
        return m176c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final ad m185b() {
        int i = 1;
        ad adVar = new ad();
        int i2 = this.f85a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        adVar.f77c = this.f86b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        adVar.f78d = this.f87c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        adVar.f79e = this.f88d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        adVar.f80f = this.f89e;
        if ((i2 & 16) == 16) {
            i |= 16;
        }
        adVar.f81g = this.f90f;
        if ((i2 & 32) == 32) {
            i |= 32;
        }
        adVar.f82h = this.f91g;
        adVar.f76b = i;
        return adVar;
    }

    /* renamed from: b */
    public final ae m186b(int i) {
        this.f85a |= 2;
        this.f87c = i;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m187b(C4072d c4072d, C4075g c4075g) {
        return m176c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m188c() {
        return m178f();
    }

    public final /* synthetic */ Object clone() {
        return m178f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m189d() {
        return m178f();
    }
}
