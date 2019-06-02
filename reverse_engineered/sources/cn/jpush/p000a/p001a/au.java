package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.au */
public final class au extends C4076h {
    /* renamed from: a */
    private static final au f162a;
    /* renamed from: b */
    private int f163b;
    /* renamed from: c */
    private long f164c;
    /* renamed from: d */
    private long f165d;
    /* renamed from: e */
    private byte f166e;
    /* renamed from: f */
    private int f167f;

    static {
        au auVar = new au();
        f162a = auVar;
        auVar.f164c = 0;
        auVar.f165d = 0;
    }

    private au() {
        this.f166e = (byte) -1;
        this.f167f = -1;
    }

    private au(av avVar) {
        super((byte) 0);
        this.f166e = (byte) -1;
        this.f167f = -1;
    }

    /* renamed from: a */
    public static au m368a() {
        return f162a;
    }

    /* renamed from: a */
    public static av m369a(au auVar) {
        return new av().m384a(auVar);
    }

    /* renamed from: h */
    public static av m371h() {
        return new av();
    }

    /* renamed from: a */
    public final void m372a(C4073e c4073e) {
        m374c();
        if ((this.f163b & 1) == 1) {
            c4073e.a(1, this.f164c);
        }
        if ((this.f163b & 2) == 2) {
            c4073e.a(2, this.f165d);
        }
    }

    /* renamed from: b */
    public final boolean m373b() {
        return (this.f163b & 1) == 1;
    }

    /* renamed from: c */
    public final int m374c() {
        int i = this.f167f;
        if (i == -1) {
            i = 0;
            if ((this.f163b & 1) == 1) {
                i = C4073e.b(1, this.f164c) + 0;
            }
            if ((this.f163b & 2) == 2) {
                i += C4073e.b(2, this.f165d);
            }
            this.f167f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m375d() {
        return this.f164c;
    }

    /* renamed from: e */
    public final boolean m376e() {
        return (this.f163b & 2) == 2;
    }

    /* renamed from: f */
    public final long m377f() {
        return this.f165d;
    }

    /* renamed from: g */
    public final boolean m378g() {
        byte b = this.f166e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f166e = (byte) 1;
            return true;
        }
    }
}
