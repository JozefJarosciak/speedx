package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.bl */
public final class bl extends C4076h {
    /* renamed from: a */
    private static final bl f266a;
    /* renamed from: b */
    private int f267b;
    /* renamed from: c */
    private C4071c f268c;
    /* renamed from: d */
    private C4071c f269d;
    /* renamed from: e */
    private int f270e;
    /* renamed from: f */
    private C4071c f271f;
    /* renamed from: g */
    private byte f272g;
    /* renamed from: h */
    private int f273h;

    static {
        bl blVar = new bl();
        f266a = blVar;
        blVar.f268c = C4071c.f14609a;
        blVar.f269d = C4071c.f14609a;
        blVar.f270e = 0;
        blVar.f271f = C4071c.f14609a;
    }

    private bl() {
        this.f272g = (byte) -1;
        this.f273h = -1;
    }

    private bl(bm bmVar) {
        super((byte) 0);
        this.f272g = (byte) -1;
        this.f273h = -1;
    }

    /* renamed from: a */
    public static bl m608a() {
        return f266a;
    }

    /* renamed from: a */
    public static bm m609a(bl blVar) {
        return new bm().m631a(blVar);
    }

    /* renamed from: l */
    public static bm m614l() {
        return new bm();
    }

    /* renamed from: a */
    public final void m615a(C4073e c4073e) {
        m617c();
        if ((this.f267b & 1) == 1) {
            c4073e.a(1, this.f268c);
        }
        if ((this.f267b & 2) == 2) {
            c4073e.a(2, this.f269d);
        }
        if ((this.f267b & 4) == 4) {
            c4073e.a(3, this.f270e);
        }
        if ((this.f267b & 8) == 8) {
            c4073e.a(4, this.f271f);
        }
    }

    /* renamed from: b */
    public final boolean m616b() {
        return (this.f267b & 1) == 1;
    }

    /* renamed from: c */
    public final int m617c() {
        int i = this.f273h;
        if (i == -1) {
            i = 0;
            if ((this.f267b & 1) == 1) {
                i = C4073e.b(1, this.f268c) + 0;
            }
            if ((this.f267b & 2) == 2) {
                i += C4073e.b(2, this.f269d);
            }
            if ((this.f267b & 4) == 4) {
                i += C4073e.c(3, this.f270e);
            }
            if ((this.f267b & 8) == 8) {
                i += C4073e.b(4, this.f271f);
            }
            this.f273h = i;
        }
        return i;
    }

    /* renamed from: d */
    public final C4071c m618d() {
        return this.f268c;
    }

    /* renamed from: e */
    public final boolean m619e() {
        return (this.f267b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m620f() {
        return this.f269d;
    }

    /* renamed from: g */
    public final boolean m621g() {
        return (this.f267b & 4) == 4;
    }

    /* renamed from: h */
    public final int m622h() {
        return this.f270e;
    }

    /* renamed from: i */
    public final boolean m623i() {
        return (this.f267b & 8) == 8;
    }

    /* renamed from: j */
    public final C4071c m624j() {
        return this.f271f;
    }

    /* renamed from: k */
    public final boolean m625k() {
        byte b = this.f272g;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f272g = (byte) 1;
            return true;
        }
    }
}
