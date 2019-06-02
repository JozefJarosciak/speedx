package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.bc */
public final class bc extends C4076h {
    /* renamed from: a */
    private static final bc f218a;
    /* renamed from: b */
    private int f219b;
    /* renamed from: c */
    private long f220c;
    /* renamed from: d */
    private int f221d;
    /* renamed from: e */
    private long f222e;
    /* renamed from: f */
    private long f223f;
    /* renamed from: g */
    private C4071c f224g;
    /* renamed from: h */
    private byte f225h;
    /* renamed from: i */
    private int f226i;

    static {
        bc bcVar = new bc();
        f218a = bcVar;
        bcVar.f220c = 0;
        bcVar.f221d = 0;
        bcVar.f222e = 0;
        bcVar.f223f = 0;
        bcVar.f224g = C4071c.f14609a;
    }

    private bc() {
        this.f225h = (byte) -1;
        this.f226i = -1;
    }

    private bc(bd bdVar) {
        super((byte) 0);
        this.f225h = (byte) -1;
        this.f226i = -1;
    }

    /* renamed from: a */
    public static bc m496a() {
        return f218a;
    }

    /* renamed from: a */
    public static bd m497a(bc bcVar) {
        return new bd().m519a(bcVar);
    }

    /* renamed from: m */
    public static bd m502m() {
        return new bd();
    }

    /* renamed from: a */
    public final void m503a(C4073e c4073e) {
        m505c();
        if ((this.f219b & 1) == 1) {
            c4073e.a(1, this.f220c);
        }
        if ((this.f219b & 2) == 2) {
            c4073e.a(2, this.f221d);
        }
        if ((this.f219b & 4) == 4) {
            c4073e.a(3, this.f222e);
        }
        if ((this.f219b & 8) == 8) {
            c4073e.a(4, this.f223f);
        }
        if ((this.f219b & 16) == 16) {
            c4073e.a(5, this.f224g);
        }
    }

    /* renamed from: b */
    public final boolean m504b() {
        return (this.f219b & 1) == 1;
    }

    /* renamed from: c */
    public final int m505c() {
        int i = this.f226i;
        if (i == -1) {
            i = 0;
            if ((this.f219b & 1) == 1) {
                i = C4073e.b(1, this.f220c) + 0;
            }
            if ((this.f219b & 2) == 2) {
                i += C4073e.c(2, this.f221d);
            }
            if ((this.f219b & 4) == 4) {
                i += C4073e.b(3, this.f222e);
            }
            if ((this.f219b & 8) == 8) {
                i += C4073e.b(4, this.f223f);
            }
            if ((this.f219b & 16) == 16) {
                i += C4073e.b(5, this.f224g);
            }
            this.f226i = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m506d() {
        return this.f220c;
    }

    /* renamed from: e */
    public final boolean m507e() {
        return (this.f219b & 2) == 2;
    }

    /* renamed from: f */
    public final int m508f() {
        return this.f221d;
    }

    /* renamed from: g */
    public final boolean m509g() {
        return (this.f219b & 4) == 4;
    }

    /* renamed from: h */
    public final long m510h() {
        return this.f222e;
    }

    /* renamed from: i */
    public final boolean m511i() {
        return (this.f219b & 8) == 8;
    }

    /* renamed from: j */
    public final long m512j() {
        return this.f223f;
    }

    /* renamed from: k */
    public final boolean m513k() {
        return (this.f219b & 16) == 16;
    }

    /* renamed from: l */
    public final C4071c m514l() {
        return this.f224g;
    }
}
