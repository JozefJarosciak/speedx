package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.bn */
public final class bn extends C4076h {
    /* renamed from: a */
    private static final bn f279a;
    /* renamed from: b */
    private int f280b;
    /* renamed from: c */
    private C4071c f281c;
    /* renamed from: d */
    private byte f282d;
    /* renamed from: e */
    private int f283e;

    static {
        bn bnVar = new bn();
        f279a = bnVar;
        bnVar.f281c = C4071c.f14609a;
    }

    private bn() {
        this.f282d = (byte) -1;
        this.f283e = -1;
    }

    private bn(bo boVar) {
        super((byte) 0);
        this.f282d = (byte) -1;
        this.f283e = -1;
    }

    /* renamed from: a */
    public static bn m641a() {
        return f279a;
    }

    /* renamed from: a */
    public static bo m642a(bn bnVar) {
        return new bo().m654a(bnVar);
    }

    /* renamed from: f */
    public static bo m644f() {
        return new bo();
    }

    /* renamed from: a */
    public final void m645a(C4073e c4073e) {
        m647c();
        if ((this.f280b & 1) == 1) {
            c4073e.a(1, this.f281c);
        }
    }

    /* renamed from: b */
    public final boolean m646b() {
        return (this.f280b & 1) == 1;
    }

    /* renamed from: c */
    public final int m647c() {
        int i = this.f283e;
        if (i == -1) {
            i = 0;
            if ((this.f280b & 1) == 1) {
                i = C4073e.b(1, this.f281c) + 0;
            }
            this.f283e = i;
        }
        return i;
    }

    /* renamed from: d */
    public final C4071c m648d() {
        return this.f281c;
    }

    /* renamed from: e */
    public final boolean m649e() {
        byte b = this.f282d;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f282d = (byte) 1;
            return true;
        }
    }
}
