package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.as */
public final class as extends C4076h {
    /* renamed from: a */
    private static final as f153a;
    /* renamed from: b */
    private int f154b;
    /* renamed from: c */
    private int f155c;
    /* renamed from: d */
    private long f156d;
    /* renamed from: e */
    private byte f157e;
    /* renamed from: f */
    private int f158f;

    static {
        as asVar = new as();
        f153a = asVar;
        asVar.f155c = 0;
        asVar.f156d = 0;
    }

    private as() {
        this.f157e = (byte) -1;
        this.f158f = -1;
    }

    private as(at atVar) {
        super((byte) 0);
        this.f157e = (byte) -1;
        this.f158f = -1;
    }

    /* renamed from: a */
    public static as m345a() {
        return f153a;
    }

    /* renamed from: a */
    public static at m346a(as asVar) {
        return new at().m360a(asVar);
    }

    /* renamed from: h */
    public static at m348h() {
        return new at();
    }

    /* renamed from: a */
    public final void m349a(C4073e c4073e) {
        m351c();
        if ((this.f154b & 1) == 1) {
            c4073e.a(1, this.f155c);
        }
        if ((this.f154b & 2) == 2) {
            c4073e.a(2, this.f156d);
        }
    }

    /* renamed from: b */
    public final boolean m350b() {
        return (this.f154b & 1) == 1;
    }

    /* renamed from: c */
    public final int m351c() {
        int i = this.f158f;
        if (i == -1) {
            i = 0;
            if ((this.f154b & 1) == 1) {
                i = C4073e.c(1, this.f155c) + 0;
            }
            if ((this.f154b & 2) == 2) {
                i += C4073e.b(2, this.f156d);
            }
            this.f158f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final int m352d() {
        return this.f155c;
    }

    /* renamed from: e */
    public final boolean m353e() {
        return (this.f154b & 2) == 2;
    }

    /* renamed from: f */
    public final long m354f() {
        return this.f156d;
    }

    /* renamed from: g */
    public final boolean m355g() {
        byte b = this.f157e;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f157e = (byte) 1;
            return true;
        }
    }
}
