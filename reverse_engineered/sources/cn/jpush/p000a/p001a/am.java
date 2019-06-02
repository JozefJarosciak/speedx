package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.am */
public final class am extends C4076h {
    /* renamed from: a */
    private static final am f119a;
    /* renamed from: b */
    private int f120b;
    /* renamed from: c */
    private long f121c;
    /* renamed from: d */
    private long f122d;
    /* renamed from: e */
    private byte f123e;
    /* renamed from: f */
    private int f124f;

    static {
        am amVar = new am();
        f119a = amVar;
        amVar.f121c = 0;
        amVar.f122d = 0;
    }

    private am() {
        this.f123e = (byte) -1;
        this.f124f = -1;
    }

    private am(an anVar) {
        super((byte) 0);
        this.f123e = (byte) -1;
        this.f124f = -1;
    }

    /* renamed from: a */
    public static am m260a() {
        return f119a;
    }

    /* renamed from: a */
    public static an m261a(am amVar) {
        return new an().m276a(amVar);
    }

    /* renamed from: h */
    public static an m263h() {
        return new an();
    }

    /* renamed from: a */
    public final void m264a(C4073e c4073e) {
        m266c();
        if ((this.f120b & 1) == 1) {
            c4073e.a(1, this.f121c);
        }
        if ((this.f120b & 2) == 2) {
            c4073e.a(2, this.f122d);
        }
    }

    /* renamed from: b */
    public final boolean m265b() {
        return (this.f120b & 1) == 1;
    }

    /* renamed from: c */
    public final int m266c() {
        int i = this.f124f;
        if (i == -1) {
            i = 0;
            if ((this.f120b & 1) == 1) {
                i = C4073e.b(1, this.f121c) + 0;
            }
            if ((this.f120b & 2) == 2) {
                i += C4073e.b(2, this.f122d);
            }
            this.f124f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m267d() {
        return this.f121c;
    }

    /* renamed from: e */
    public final boolean m268e() {
        return (this.f120b & 2) == 2;
    }

    /* renamed from: f */
    public final long m269f() {
        return this.f122d;
    }

    /* renamed from: g */
    public final boolean m270g() {
        byte b = this.f123e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f123e = (byte) 1;
            return true;
        }
    }
}
