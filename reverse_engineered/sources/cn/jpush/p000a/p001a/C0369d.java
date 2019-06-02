package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.d */
public final class C0369d extends C4076h {
    /* renamed from: a */
    private static final C0369d f295a;
    /* renamed from: b */
    private int f296b;
    /* renamed from: c */
    private long f297c;
    /* renamed from: d */
    private C4071c f298d;
    /* renamed from: e */
    private C4071c f299e;
    /* renamed from: f */
    private int f300f;
    /* renamed from: g */
    private C4071c f301g;
    /* renamed from: h */
    private byte f302h;
    /* renamed from: i */
    private int f303i;

    static {
        C0369d c0369d = new C0369d();
        f295a = c0369d;
        c0369d.f297c = 0;
        c0369d.f298d = C4071c.f14609a;
        c0369d.f299e = C4071c.f14609a;
        c0369d.f300f = 0;
        c0369d.f301g = C4071c.f14609a;
    }

    private C0369d() {
        this.f302h = (byte) -1;
        this.f303i = -1;
    }

    private C0369d(C0370e c0370e) {
        super((byte) 0);
        this.f302h = (byte) -1;
        this.f303i = -1;
    }

    /* renamed from: a */
    public static C0369d m696a() {
        return f295a;
    }

    /* renamed from: a */
    public static C0370e m697a(C0369d c0369d) {
        return new C0370e().m722a(c0369d);
    }

    /* renamed from: n */
    public static C0370e m702n() {
        return new C0370e();
    }

    /* renamed from: a */
    public final void m703a(C4073e c4073e) {
        m705c();
        if ((this.f296b & 1) == 1) {
            c4073e.a(1, this.f297c);
        }
        if ((this.f296b & 2) == 2) {
            c4073e.a(2, this.f298d);
        }
        if ((this.f296b & 4) == 4) {
            c4073e.a(3, this.f299e);
        }
        if ((this.f296b & 8) == 8) {
            c4073e.a(4, this.f300f);
        }
        if ((this.f296b & 16) == 16) {
            c4073e.a(5, this.f301g);
        }
    }

    /* renamed from: b */
    public final boolean m704b() {
        return (this.f296b & 1) == 1;
    }

    /* renamed from: c */
    public final int m705c() {
        int i = this.f303i;
        if (i == -1) {
            i = 0;
            if ((this.f296b & 1) == 1) {
                i = C4073e.b(1, this.f297c) + 0;
            }
            if ((this.f296b & 2) == 2) {
                i += C4073e.b(2, this.f298d);
            }
            if ((this.f296b & 4) == 4) {
                i += C4073e.b(3, this.f299e);
            }
            if ((this.f296b & 8) == 8) {
                i += C4073e.c(4, this.f300f);
            }
            if ((this.f296b & 16) == 16) {
                i += C4073e.b(5, this.f301g);
            }
            this.f303i = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m706d() {
        return this.f297c;
    }

    /* renamed from: e */
    public final boolean m707e() {
        return (this.f296b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m708f() {
        return this.f298d;
    }

    /* renamed from: g */
    public final boolean m709g() {
        return (this.f296b & 4) == 4;
    }

    /* renamed from: h */
    public final C4071c m710h() {
        return this.f299e;
    }

    /* renamed from: i */
    public final boolean m711i() {
        return (this.f296b & 8) == 8;
    }

    /* renamed from: j */
    public final int m712j() {
        return this.f300f;
    }

    /* renamed from: k */
    public final boolean m713k() {
        return (this.f296b & 16) == 16;
    }

    /* renamed from: l */
    public final C4071c m714l() {
        return this.f301g;
    }

    /* renamed from: m */
    public final boolean m715m() {
        byte b = this.f302h;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f302h = (byte) 1;
            return true;
        }
    }
}
