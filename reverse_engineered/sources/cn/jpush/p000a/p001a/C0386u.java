package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.u */
public final class C0386u extends C4076h {
    /* renamed from: a */
    private static final C0386u f378a;
    /* renamed from: b */
    private int f379b;
    /* renamed from: c */
    private long f380c;
    /* renamed from: d */
    private C4071c f381d;
    /* renamed from: e */
    private C4071c f382e;
    /* renamed from: f */
    private byte f383f;
    /* renamed from: g */
    private int f384g;

    static {
        C0386u c0386u = new C0386u();
        f378a = c0386u;
        c0386u.f380c = 0;
        c0386u.f381d = C4071c.f14609a;
        c0386u.f382e = C4071c.f14609a;
    }

    private C0386u() {
        this.f383f = (byte) -1;
        this.f384g = -1;
    }

    private C0386u(C0387v c0387v) {
        super((byte) 0);
        this.f383f = (byte) -1;
        this.f384g = -1;
    }

    /* renamed from: a */
    public static C0386u m918a() {
        return f378a;
    }

    /* renamed from: a */
    public static C0387v m919a(C0386u c0386u) {
        return new C0387v().m937a(c0386u);
    }

    /* renamed from: j */
    public static C0387v m922j() {
        return new C0387v();
    }

    /* renamed from: a */
    public final void m923a(C4073e c4073e) {
        m925c();
        if ((this.f379b & 1) == 1) {
            c4073e.a(1, this.f380c);
        }
        if ((this.f379b & 2) == 2) {
            c4073e.a(2, this.f381d);
        }
        if ((this.f379b & 4) == 4) {
            c4073e.a(3, this.f382e);
        }
    }

    /* renamed from: b */
    public final boolean m924b() {
        return (this.f379b & 1) == 1;
    }

    /* renamed from: c */
    public final int m925c() {
        int i = this.f384g;
        if (i == -1) {
            i = 0;
            if ((this.f379b & 1) == 1) {
                i = C4073e.b(1, this.f380c) + 0;
            }
            if ((this.f379b & 2) == 2) {
                i += C4073e.b(2, this.f381d);
            }
            if ((this.f379b & 4) == 4) {
                i += C4073e.b(3, this.f382e);
            }
            this.f384g = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m926d() {
        return this.f380c;
    }

    /* renamed from: e */
    public final boolean m927e() {
        return (this.f379b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m928f() {
        return this.f381d;
    }

    /* renamed from: g */
    public final boolean m929g() {
        return (this.f379b & 4) == 4;
    }

    /* renamed from: h */
    public final C4071c m930h() {
        return this.f382e;
    }

    /* renamed from: i */
    public final boolean m931i() {
        byte b = this.f383f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f383f = (byte) 1;
            return true;
        }
    }
}
