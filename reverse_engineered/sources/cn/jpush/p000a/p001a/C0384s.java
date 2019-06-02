package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.s */
public final class C0384s extends C4076h {
    /* renamed from: a */
    private static final C0384s f371a;
    /* renamed from: b */
    private int f372b;
    /* renamed from: c */
    private long f373c;
    /* renamed from: d */
    private byte f374d;
    /* renamed from: e */
    private int f375e;

    static {
        C0384s c0384s = new C0384s();
        f371a = c0384s;
        c0384s.f373c = 0;
    }

    private C0384s() {
        this.f374d = (byte) -1;
        this.f375e = -1;
    }

    private C0384s(C0385t c0385t) {
        super((byte) 0);
        this.f374d = (byte) -1;
        this.f375e = -1;
    }

    /* renamed from: a */
    public static C0384s m897a() {
        return f371a;
    }

    /* renamed from: a */
    public static C0385t m898a(C0384s c0384s) {
        return new C0385t().m910a(c0384s);
    }

    /* renamed from: f */
    public static C0385t m899f() {
        return new C0385t();
    }

    /* renamed from: a */
    public final void m900a(C4073e c4073e) {
        m902c();
        if ((this.f372b & 1) == 1) {
            c4073e.a(1, this.f373c);
        }
    }

    /* renamed from: b */
    public final boolean m901b() {
        return (this.f372b & 1) == 1;
    }

    /* renamed from: c */
    public final int m902c() {
        int i = this.f375e;
        if (i == -1) {
            i = 0;
            if ((this.f372b & 1) == 1) {
                i = C4073e.b(1, this.f373c) + 0;
            }
            this.f375e = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m903d() {
        return this.f373c;
    }

    /* renamed from: e */
    public final boolean m904e() {
        byte b = this.f374d;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f374d = (byte) 1;
            return true;
        }
    }
}
