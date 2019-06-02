package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.ai */
public final class ai extends C4076h {
    /* renamed from: a */
    private static final ai f101a;
    /* renamed from: b */
    private int f102b;
    /* renamed from: c */
    private int f103c;
    /* renamed from: d */
    private long f104d;
    /* renamed from: e */
    private byte f105e;
    /* renamed from: f */
    private int f106f;

    static {
        ai aiVar = new ai();
        f101a = aiVar;
        aiVar.f103c = 0;
        aiVar.f104d = 0;
    }

    private ai() {
        this.f105e = (byte) -1;
        this.f106f = -1;
    }

    private ai(aj ajVar) {
        super((byte) 0);
        this.f105e = (byte) -1;
        this.f106f = -1;
    }

    /* renamed from: a */
    public static ai m213a() {
        return f101a;
    }

    /* renamed from: a */
    public static aj m214a(ai aiVar) {
        return new aj().m228a(aiVar);
    }

    /* renamed from: h */
    public static aj m216h() {
        return new aj();
    }

    /* renamed from: a */
    public final void m217a(C4073e c4073e) {
        m219c();
        if ((this.f102b & 1) == 1) {
            c4073e.a(1, this.f103c);
        }
        if ((this.f102b & 2) == 2) {
            c4073e.a(2, this.f104d);
        }
    }

    /* renamed from: b */
    public final boolean m218b() {
        return (this.f102b & 1) == 1;
    }

    /* renamed from: c */
    public final int m219c() {
        int i = this.f106f;
        if (i == -1) {
            i = 0;
            if ((this.f102b & 1) == 1) {
                i = C4073e.c(1, this.f103c) + 0;
            }
            if ((this.f102b & 2) == 2) {
                i += C4073e.b(2, this.f104d);
            }
            this.f106f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final int m220d() {
        return this.f103c;
    }

    /* renamed from: e */
    public final boolean m221e() {
        return (this.f102b & 2) == 2;
    }

    /* renamed from: f */
    public final long m222f() {
        return this.f104d;
    }

    /* renamed from: g */
    public final boolean m223g() {
        byte b = this.f105e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f105e = (byte) 1;
            return true;
        }
    }
}
