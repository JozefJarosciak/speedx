package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.z */
public final class C0391z extends C4076h {
    /* renamed from: a */
    private static final C0391z f395a;
    /* renamed from: b */
    private int f396b;
    /* renamed from: c */
    private ad f397c;
    /* renamed from: d */
    private ab f398d;
    /* renamed from: e */
    private byte f399e;
    /* renamed from: f */
    private int f400f;

    static {
        C0391z c0391z = new C0391z();
        f395a = c0391z;
        c0391z.f397c = ad.m153a();
        c0391z.f398d = ab.m35a();
    }

    private C0391z() {
        this.f399e = (byte) -1;
        this.f400f = -1;
    }

    private C0391z(aa aaVar) {
        super((byte) 0);
        this.f399e = (byte) -1;
        this.f400f = -1;
    }

    /* renamed from: a */
    public static C0391z m965a() {
        return f395a;
    }

    /* renamed from: a */
    public static C0391z m966a(byte[] bArr) {
        return aa.m22a((aa) new aa().a(bArr, 0, bArr.length));
    }

    /* renamed from: h */
    public static aa m967h() {
        return new aa();
    }

    /* renamed from: a */
    public final void m968a(C4073e c4073e) {
        m970c();
        if ((this.f396b & 1) == 1) {
            c4073e.a(1, this.f397c);
        }
        if ((this.f396b & 2) == 2) {
            c4073e.a(2, this.f398d);
        }
    }

    /* renamed from: b */
    public final boolean m969b() {
        return (this.f396b & 1) == 1;
    }

    /* renamed from: c */
    public final int m970c() {
        int i = this.f400f;
        if (i == -1) {
            i = 0;
            if ((this.f396b & 1) == 1) {
                i = C4073e.b(1, this.f397c) + 0;
            }
            if ((this.f396b & 2) == 2) {
                i += C4073e.b(2, this.f398d);
            }
            this.f400f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final ad m971d() {
        return this.f397c;
    }

    /* renamed from: e */
    public final boolean m972e() {
        return (this.f396b & 2) == 2;
    }

    /* renamed from: f */
    public final ab m973f() {
        return this.f398d;
    }

    /* renamed from: g */
    public final boolean m974g() {
        byte b = this.f399e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f399e = (byte) 1;
            return true;
        }
    }
}
