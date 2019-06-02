package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.o */
public final class C0380o extends C4076h {
    /* renamed from: a */
    private static final C0380o f345a;
    /* renamed from: b */
    private int f346b;
    /* renamed from: c */
    private C4071c f347c;
    /* renamed from: d */
    private C4071c f348d;
    /* renamed from: e */
    private int f349e;
    /* renamed from: f */
    private int f350f;
    /* renamed from: g */
    private long f351g;
    /* renamed from: h */
    private byte f352h;
    /* renamed from: i */
    private int f353i;

    static {
        C0380o c0380o = new C0380o();
        f345a = c0380o;
        c0380o.f347c = C4071c.f14609a;
        c0380o.f348d = C4071c.f14609a;
        c0380o.f349e = 0;
        c0380o.f350f = 0;
        c0380o.f351g = 0;
    }

    private C0380o() {
        this.f352h = (byte) -1;
        this.f353i = -1;
    }

    private C0380o(C0381p c0381p) {
        super((byte) 0);
        this.f352h = (byte) -1;
        this.f353i = -1;
    }

    /* renamed from: a */
    public static C0380o m832a() {
        return f345a;
    }

    /* renamed from: a */
    public static C0381p m833a(C0380o c0380o) {
        return new C0381p().m857a(c0380o);
    }

    /* renamed from: n */
    public static C0381p m838n() {
        return new C0381p();
    }

    /* renamed from: a */
    public final void m839a(C4073e c4073e) {
        m841c();
        if ((this.f346b & 1) == 1) {
            c4073e.a(1, this.f347c);
        }
        if ((this.f346b & 2) == 2) {
            c4073e.a(2, this.f348d);
        }
        if ((this.f346b & 4) == 4) {
            c4073e.a(3, this.f349e);
        }
        if ((this.f346b & 8) == 8) {
            c4073e.a(4, this.f350f);
        }
        if ((this.f346b & 16) == 16) {
            c4073e.a(5, this.f351g);
        }
    }

    /* renamed from: b */
    public final boolean m840b() {
        return (this.f346b & 1) == 1;
    }

    /* renamed from: c */
    public final int m841c() {
        int i = this.f353i;
        if (i == -1) {
            i = 0;
            if ((this.f346b & 1) == 1) {
                i = C4073e.b(1, this.f347c) + 0;
            }
            if ((this.f346b & 2) == 2) {
                i += C4073e.b(2, this.f348d);
            }
            if ((this.f346b & 4) == 4) {
                i += C4073e.c(3, this.f349e);
            }
            if ((this.f346b & 8) == 8) {
                i += C4073e.c(4, this.f350f);
            }
            if ((this.f346b & 16) == 16) {
                i += C4073e.b(5, this.f351g);
            }
            this.f353i = i;
        }
        return i;
    }

    /* renamed from: d */
    public final C4071c m842d() {
        return this.f347c;
    }

    /* renamed from: e */
    public final boolean m843e() {
        return (this.f346b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m844f() {
        return this.f348d;
    }

    /* renamed from: g */
    public final boolean m845g() {
        return (this.f346b & 4) == 4;
    }

    /* renamed from: h */
    public final int m846h() {
        return this.f349e;
    }

    /* renamed from: i */
    public final boolean m847i() {
        return (this.f346b & 8) == 8;
    }

    /* renamed from: j */
    public final int m848j() {
        return this.f350f;
    }

    /* renamed from: k */
    public final boolean m849k() {
        return (this.f346b & 16) == 16;
    }

    /* renamed from: l */
    public final long m850l() {
        return this.f351g;
    }

    /* renamed from: m */
    public final boolean m851m() {
        byte b = this.f352h;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f352h = (byte) 1;
            return true;
        }
    }
}
