package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.j */
public final class C0375j extends C4076h {
    /* renamed from: a */
    private static final C0375j f323a;
    /* renamed from: b */
    private int f324b;
    /* renamed from: c */
    private long f325c;
    /* renamed from: d */
    private C4071c f326d;
    /* renamed from: e */
    private C4071c f327e;
    /* renamed from: f */
    private byte f328f;
    /* renamed from: g */
    private int f329g;

    static {
        C0375j c0375j = new C0375j();
        f323a = c0375j;
        c0375j.f325c = 0;
        c0375j.f326d = C4071c.f14609a;
        c0375j.f327e = C4071c.f14609a;
    }

    private C0375j() {
        this.f328f = (byte) -1;
        this.f329g = -1;
    }

    private C0375j(C0376k c0376k) {
        super((byte) 0);
        this.f328f = (byte) -1;
        this.f329g = -1;
    }

    /* renamed from: a */
    public static C0375j m774a() {
        return f323a;
    }

    /* renamed from: a */
    public static C0376k m775a(C0375j c0375j) {
        return new C0376k().m793a(c0375j);
    }

    /* renamed from: j */
    public static C0376k m778j() {
        return new C0376k();
    }

    /* renamed from: a */
    public final void m779a(C4073e c4073e) {
        m781c();
        if ((this.f324b & 1) == 1) {
            c4073e.a(1, this.f325c);
        }
        if ((this.f324b & 2) == 2) {
            c4073e.a(2, this.f326d);
        }
        if ((this.f324b & 4) == 4) {
            c4073e.a(3, this.f327e);
        }
    }

    /* renamed from: b */
    public final boolean m780b() {
        return (this.f324b & 1) == 1;
    }

    /* renamed from: c */
    public final int m781c() {
        int i = this.f329g;
        if (i == -1) {
            i = 0;
            if ((this.f324b & 1) == 1) {
                i = C4073e.b(1, this.f325c) + 0;
            }
            if ((this.f324b & 2) == 2) {
                i += C4073e.b(2, this.f326d);
            }
            if ((this.f324b & 4) == 4) {
                i += C4073e.b(3, this.f327e);
            }
            this.f329g = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m782d() {
        return this.f325c;
    }

    /* renamed from: e */
    public final boolean m783e() {
        return (this.f324b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m784f() {
        return this.f326d;
    }

    /* renamed from: g */
    public final boolean m785g() {
        return (this.f324b & 4) == 4;
    }

    /* renamed from: h */
    public final C4071c m786h() {
        return this.f327e;
    }

    /* renamed from: i */
    public final boolean m787i() {
        byte b = this.f328f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f328f = (byte) 1;
            return true;
        }
    }
}
