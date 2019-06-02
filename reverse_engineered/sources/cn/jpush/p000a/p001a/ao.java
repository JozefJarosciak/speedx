package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.ao */
public final class ao extends C4076h {
    /* renamed from: a */
    private static final ao f128a;
    /* renamed from: b */
    private int f129b;
    /* renamed from: c */
    private long f130c;
    /* renamed from: d */
    private long f131d;
    /* renamed from: e */
    private long f132e;
    /* renamed from: f */
    private int f133f;
    /* renamed from: g */
    private bg f134g;
    /* renamed from: h */
    private int f135h;
    /* renamed from: i */
    private int f136i;
    /* renamed from: j */
    private byte f137j;
    /* renamed from: k */
    private int f138k;

    static {
        ao aoVar = new ao();
        f128a = aoVar;
        aoVar.f130c = 0;
        aoVar.f131d = 0;
        aoVar.f132e = 0;
        aoVar.f133f = 0;
        aoVar.f134g = bg.m553a();
        aoVar.f135h = 0;
        aoVar.f136i = 0;
    }

    private ao() {
        this.f137j = (byte) -1;
        this.f138k = -1;
    }

    private ao(ap apVar) {
        super((byte) 0);
        this.f137j = (byte) -1;
        this.f138k = -1;
    }

    /* renamed from: a */
    public static ao m284a() {
        return f128a;
    }

    /* renamed from: r */
    public static ap m291r() {
        return new ap();
    }

    /* renamed from: a */
    public final void m292a(C4073e c4073e) {
        m294c();
        if ((this.f129b & 1) == 1) {
            c4073e.a(1, this.f130c);
        }
        if ((this.f129b & 2) == 2) {
            c4073e.a(2, this.f131d);
        }
        if ((this.f129b & 4) == 4) {
            c4073e.a(3, this.f132e);
        }
        if ((this.f129b & 8) == 8) {
            c4073e.a(4, this.f133f);
        }
        if ((this.f129b & 16) == 16) {
            c4073e.a(5, this.f134g);
        }
        if ((this.f129b & 32) == 32) {
            c4073e.b(6, this.f135h);
        }
        if ((this.f129b & 64) == 64) {
            c4073e.a(7, this.f136i);
        }
    }

    /* renamed from: b */
    public final boolean m293b() {
        return (this.f129b & 1) == 1;
    }

    /* renamed from: c */
    public final int m294c() {
        int i = this.f138k;
        if (i == -1) {
            i = 0;
            if ((this.f129b & 1) == 1) {
                i = C4073e.b(1, this.f130c) + 0;
            }
            if ((this.f129b & 2) == 2) {
                i += C4073e.b(2, this.f131d);
            }
            if ((this.f129b & 4) == 4) {
                i += C4073e.b(3, this.f132e);
            }
            if ((this.f129b & 8) == 8) {
                i += C4073e.c(4, this.f133f);
            }
            if ((this.f129b & 16) == 16) {
                i += C4073e.b(5, this.f134g);
            }
            if ((this.f129b & 32) == 32) {
                i += C4073e.d(6, this.f135h);
            }
            if ((this.f129b & 64) == 64) {
                i += C4073e.c(7, this.f136i);
            }
            this.f138k = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m295d() {
        return this.f130c;
    }

    /* renamed from: e */
    public final boolean m296e() {
        return (this.f129b & 2) == 2;
    }

    /* renamed from: f */
    public final long m297f() {
        return this.f131d;
    }

    /* renamed from: g */
    public final boolean m298g() {
        return (this.f129b & 4) == 4;
    }

    /* renamed from: h */
    public final long m299h() {
        return this.f132e;
    }

    /* renamed from: i */
    public final boolean m300i() {
        return (this.f129b & 8) == 8;
    }

    /* renamed from: j */
    public final int m301j() {
        return this.f133f;
    }

    /* renamed from: k */
    public final boolean m302k() {
        return (this.f129b & 16) == 16;
    }

    /* renamed from: l */
    public final bg m303l() {
        return this.f134g;
    }

    /* renamed from: m */
    public final boolean m304m() {
        return (this.f129b & 32) == 32;
    }

    /* renamed from: n */
    public final int m305n() {
        return this.f135h;
    }

    /* renamed from: o */
    public final boolean m306o() {
        return (this.f129b & 64) == 64;
    }

    /* renamed from: p */
    public final int m307p() {
        return this.f136i;
    }

    /* renamed from: q */
    public final boolean m308q() {
        byte b = this.f137j;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f137j = (byte) 1;
            return true;
        }
    }
}
