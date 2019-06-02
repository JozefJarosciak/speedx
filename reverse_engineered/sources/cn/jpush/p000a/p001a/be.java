package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.be */
public final class be extends C4076h {
    /* renamed from: a */
    private static final be f233a;
    /* renamed from: b */
    private int f234b;
    /* renamed from: c */
    private long f235c;
    /* renamed from: d */
    private bg f236d;
    /* renamed from: e */
    private long f237e;
    /* renamed from: f */
    private byte f238f;
    /* renamed from: g */
    private int f239g;

    static {
        be beVar = new be();
        f233a = beVar;
        beVar.f235c = 0;
        beVar.f236d = bg.m553a();
        beVar.f237e = 0;
    }

    private be() {
        this.f238f = (byte) -1;
        this.f239g = -1;
    }

    private be(bf bfVar) {
        super((byte) 0);
        this.f238f = (byte) -1;
        this.f239g = -1;
    }

    /* renamed from: a */
    public static be m526a() {
        return f233a;
    }

    /* renamed from: a */
    public static bf m527a(be beVar) {
        return new bf().m545a(beVar);
    }

    /* renamed from: j */
    public static bf m530j() {
        return new bf();
    }

    /* renamed from: a */
    public final void m531a(C4073e c4073e) {
        m533c();
        if ((this.f234b & 1) == 1) {
            c4073e.a(1, this.f235c);
        }
        if ((this.f234b & 2) == 2) {
            c4073e.a(2, this.f236d);
        }
        if ((this.f234b & 4) == 4) {
            c4073e.a(3, this.f237e);
        }
    }

    /* renamed from: b */
    public final boolean m532b() {
        return (this.f234b & 1) == 1;
    }

    /* renamed from: c */
    public final int m533c() {
        int i = this.f239g;
        if (i == -1) {
            i = 0;
            if ((this.f234b & 1) == 1) {
                i = C4073e.b(1, this.f235c) + 0;
            }
            if ((this.f234b & 2) == 2) {
                i += C4073e.b(2, this.f236d);
            }
            if ((this.f234b & 4) == 4) {
                i += C4073e.b(3, this.f237e);
            }
            this.f239g = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m534d() {
        return this.f235c;
    }

    /* renamed from: e */
    public final boolean m535e() {
        return (this.f234b & 2) == 2;
    }

    /* renamed from: f */
    public final bg m536f() {
        return this.f236d;
    }

    /* renamed from: g */
    public final boolean m537g() {
        return (this.f234b & 4) == 4;
    }

    /* renamed from: h */
    public final long m538h() {
        return this.f237e;
    }

    /* renamed from: i */
    public final boolean m539i() {
        byte b = this.f238f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f238f = (byte) 1;
            return true;
        }
    }
}
