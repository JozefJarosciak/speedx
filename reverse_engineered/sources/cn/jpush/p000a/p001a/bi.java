package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.bi */
public final class bi extends C4076h {
    /* renamed from: a */
    private static final bi f255a;
    /* renamed from: b */
    private int f256b;
    /* renamed from: c */
    private long f257c;
    /* renamed from: d */
    private bg f258d;
    /* renamed from: e */
    private long f259e;
    /* renamed from: f */
    private byte f260f;
    /* renamed from: g */
    private int f261g;

    static {
        bi biVar = new bi();
        f255a = biVar;
        biVar.f257c = 0;
        biVar.f258d = bg.m553a();
        biVar.f259e = 0;
    }

    private bi() {
        this.f260f = (byte) -1;
        this.f261g = -1;
    }

    private bi(bj bjVar) {
        super((byte) 0);
        this.f260f = (byte) -1;
        this.f261g = -1;
    }

    /* renamed from: a */
    public static bi m582a() {
        return f255a;
    }

    /* renamed from: a */
    public static bj m583a(bi biVar) {
        return new bj().m601a(biVar);
    }

    /* renamed from: j */
    public static bj m585j() {
        return new bj();
    }

    /* renamed from: a */
    public final void m586a(C4073e c4073e) {
        m588c();
        if ((this.f256b & 1) == 1) {
            c4073e.a(1, this.f257c);
        }
        if ((this.f256b & 2) == 2) {
            c4073e.a(2, this.f258d);
        }
        if ((this.f256b & 4) == 4) {
            c4073e.a(3, this.f259e);
        }
    }

    /* renamed from: b */
    public final boolean m587b() {
        return (this.f256b & 1) == 1;
    }

    /* renamed from: c */
    public final int m588c() {
        int i = this.f261g;
        if (i == -1) {
            i = 0;
            if ((this.f256b & 1) == 1) {
                i = C4073e.b(1, this.f257c) + 0;
            }
            if ((this.f256b & 2) == 2) {
                i += C4073e.b(2, this.f258d);
            }
            if ((this.f256b & 4) == 4) {
                i += C4073e.b(3, this.f259e);
            }
            this.f261g = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m589d() {
        return this.f257c;
    }

    /* renamed from: e */
    public final boolean m590e() {
        return (this.f256b & 2) == 2;
    }

    /* renamed from: f */
    public final bg m591f() {
        return this.f258d;
    }

    /* renamed from: g */
    public final boolean m592g() {
        return (this.f256b & 4) == 4;
    }

    /* renamed from: h */
    public final long m593h() {
        return this.f259e;
    }

    /* renamed from: i */
    public final boolean m594i() {
        byte b = this.f260f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f260f = (byte) 1;
            return true;
        }
    }
}
