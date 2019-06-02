package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.ak */
public final class ak extends C4076h {
    /* renamed from: a */
    private static final ak f110a;
    /* renamed from: b */
    private int f111b;
    /* renamed from: c */
    private long f112c;
    /* renamed from: d */
    private long f113d;
    /* renamed from: e */
    private byte f114e;
    /* renamed from: f */
    private int f115f;

    static {
        ak akVar = new ak();
        f110a = akVar;
        akVar.f112c = 0;
        akVar.f113d = 0;
    }

    private ak() {
        this.f114e = (byte) -1;
        this.f115f = -1;
    }

    private ak(al alVar) {
        super((byte) 0);
        this.f114e = (byte) -1;
        this.f115f = -1;
    }

    /* renamed from: a */
    public static ak m236a() {
        return f110a;
    }

    /* renamed from: a */
    public static al m237a(ak akVar) {
        return new al().m252a(akVar);
    }

    /* renamed from: h */
    public static al m239h() {
        return new al();
    }

    /* renamed from: a */
    public final void m240a(C4073e c4073e) {
        m242c();
        if ((this.f111b & 1) == 1) {
            c4073e.a(1, this.f112c);
        }
        if ((this.f111b & 2) == 2) {
            c4073e.a(2, this.f113d);
        }
    }

    /* renamed from: b */
    public final boolean m241b() {
        return (this.f111b & 1) == 1;
    }

    /* renamed from: c */
    public final int m242c() {
        int i = this.f115f;
        if (i == -1) {
            i = 0;
            if ((this.f111b & 1) == 1) {
                i = C4073e.b(1, this.f112c) + 0;
            }
            if ((this.f111b & 2) == 2) {
                i += C4073e.b(2, this.f113d);
            }
            this.f115f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m243d() {
        return this.f112c;
    }

    /* renamed from: e */
    public final boolean m244e() {
        return (this.f111b & 2) == 2;
    }

    /* renamed from: f */
    public final long m245f() {
        return this.f113d;
    }

    /* renamed from: g */
    public final boolean m246g() {
        byte b = this.f114e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f114e = (byte) 1;
            return true;
        }
    }
}
