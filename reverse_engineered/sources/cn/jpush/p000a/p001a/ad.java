package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.ad */
public final class ad extends C4076h {
    /* renamed from: a */
    private static final ad f75a;
    /* renamed from: b */
    private int f76b;
    /* renamed from: c */
    private int f77c;
    /* renamed from: d */
    private int f78d;
    /* renamed from: e */
    private long f79e;
    /* renamed from: f */
    private C4071c f80f;
    /* renamed from: g */
    private C0389x f81g;
    /* renamed from: h */
    private int f82h;
    /* renamed from: i */
    private byte f83i;
    /* renamed from: j */
    private int f84j;

    static {
        ad adVar = new ad();
        f75a = adVar;
        adVar.f77c = 0;
        adVar.f78d = 0;
        adVar.f79e = 0;
        adVar.f80f = C4071c.f14609a;
        adVar.f81g = C0389x.m945a();
        adVar.f82h = 0;
    }

    private ad() {
        this.f83i = (byte) -1;
        this.f84j = -1;
    }

    private ad(ae aeVar) {
        super((byte) 0);
        this.f83i = (byte) -1;
        this.f84j = -1;
    }

    /* renamed from: a */
    public static ad m153a() {
        return f75a;
    }

    /* renamed from: a */
    public static ae m154a(ad adVar) {
        return new ae().m182a(adVar);
    }

    /* renamed from: p */
    public static ae m160p() {
        return new ae();
    }

    /* renamed from: a */
    public final void m161a(C4073e c4073e) {
        m163c();
        if ((this.f76b & 1) == 1) {
            c4073e.a(1, this.f77c);
        }
        if ((this.f76b & 2) == 2) {
            c4073e.a(2, this.f78d);
        }
        if ((this.f76b & 4) == 4) {
            c4073e.a(3, this.f79e);
        }
        if ((this.f76b & 8) == 8) {
            c4073e.a(4, this.f80f);
        }
        if ((this.f76b & 16) == 16) {
            c4073e.a(5, this.f81g);
        }
        if ((this.f76b & 32) == 32) {
            c4073e.a(6, this.f82h);
        }
    }

    /* renamed from: b */
    public final boolean m162b() {
        return (this.f76b & 1) == 1;
    }

    /* renamed from: c */
    public final int m163c() {
        int i = this.f84j;
        if (i == -1) {
            i = 0;
            if ((this.f76b & 1) == 1) {
                i = C4073e.c(1, this.f77c) + 0;
            }
            if ((this.f76b & 2) == 2) {
                i += C4073e.c(2, this.f78d);
            }
            if ((this.f76b & 4) == 4) {
                i += C4073e.b(3, this.f79e);
            }
            if ((this.f76b & 8) == 8) {
                i += C4073e.b(4, this.f80f);
            }
            if ((this.f76b & 16) == 16) {
                i += C4073e.b(5, this.f81g);
            }
            if ((this.f76b & 32) == 32) {
                i += C4073e.c(6, this.f82h);
            }
            this.f84j = i;
        }
        return i;
    }

    /* renamed from: d */
    public final int m164d() {
        return this.f77c;
    }

    /* renamed from: e */
    public final boolean m165e() {
        return (this.f76b & 2) == 2;
    }

    /* renamed from: f */
    public final int m166f() {
        return this.f78d;
    }

    /* renamed from: g */
    public final boolean m167g() {
        return (this.f76b & 4) == 4;
    }

    /* renamed from: h */
    public final long m168h() {
        return this.f79e;
    }

    /* renamed from: i */
    public final boolean m169i() {
        return (this.f76b & 8) == 8;
    }

    /* renamed from: j */
    public final C4071c m170j() {
        return this.f80f;
    }

    /* renamed from: k */
    public final boolean m171k() {
        return (this.f76b & 16) == 16;
    }

    /* renamed from: l */
    public final C0389x m172l() {
        return this.f81g;
    }

    /* renamed from: m */
    public final boolean m173m() {
        return (this.f76b & 32) == 32;
    }

    /* renamed from: n */
    public final int m174n() {
        return this.f82h;
    }

    /* renamed from: o */
    public final boolean m175o() {
        byte b = this.f83i;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f83i = (byte) 1;
            return true;
        }
    }
}
