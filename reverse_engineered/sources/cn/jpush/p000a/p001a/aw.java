package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.aw */
public final class aw extends C4076h {
    /* renamed from: a */
    private static final aw f171a;
    /* renamed from: b */
    private int f172b;
    /* renamed from: c */
    private long f173c;
    /* renamed from: d */
    private long f174d;
    /* renamed from: e */
    private byte f175e;
    /* renamed from: f */
    private int f176f;

    static {
        aw awVar = new aw();
        f171a = awVar;
        awVar.f173c = 0;
        awVar.f174d = 0;
    }

    private aw() {
        this.f175e = (byte) -1;
        this.f176f = -1;
    }

    private aw(ax axVar) {
        super((byte) 0);
        this.f175e = (byte) -1;
        this.f176f = -1;
    }

    /* renamed from: a */
    public static aw m392a() {
        return f171a;
    }

    /* renamed from: a */
    public static ax m393a(aw awVar) {
        return new ax().m408a(awVar);
    }

    /* renamed from: h */
    public static ax m395h() {
        return new ax();
    }

    /* renamed from: a */
    public final void m396a(C4073e c4073e) {
        m398c();
        if ((this.f172b & 1) == 1) {
            c4073e.a(1, this.f173c);
        }
        if ((this.f172b & 2) == 2) {
            c4073e.a(2, this.f174d);
        }
    }

    /* renamed from: b */
    public final boolean m397b() {
        return (this.f172b & 1) == 1;
    }

    /* renamed from: c */
    public final int m398c() {
        int i = this.f176f;
        if (i == -1) {
            i = 0;
            if ((this.f172b & 1) == 1) {
                i = C4073e.b(1, this.f173c) + 0;
            }
            if ((this.f172b & 2) == 2) {
                i += C4073e.b(2, this.f174d);
            }
            this.f176f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m399d() {
        return this.f173c;
    }

    /* renamed from: e */
    public final boolean m400e() {
        return (this.f172b & 2) == 2;
    }

    /* renamed from: f */
    public final long m401f() {
        return this.f174d;
    }

    /* renamed from: g */
    public final boolean m402g() {
        byte b = this.f175e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f175e = (byte) 1;
            return true;
        }
    }
}
