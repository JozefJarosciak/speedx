package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.af */
public final class af extends C4076h {
    /* renamed from: a */
    private static final af f92a;
    /* renamed from: b */
    private int f93b;
    /* renamed from: c */
    private int f94c;
    /* renamed from: d */
    private C4071c f95d;
    /* renamed from: e */
    private byte f96e;
    /* renamed from: f */
    private int f97f;

    static {
        af afVar = new af();
        f92a = afVar;
        afVar.f94c = 0;
        afVar.f95d = C4071c.f14609a;
    }

    private af() {
        this.f96e = (byte) -1;
        this.f97f = -1;
    }

    private af(ag agVar) {
        super((byte) 0);
        this.f96e = (byte) -1;
        this.f97f = -1;
    }

    /* renamed from: a */
    public static af m191a() {
        return f92a;
    }

    /* renamed from: a */
    public static ag m192a(af afVar) {
        return new ag().m206a(afVar);
    }

    /* renamed from: g */
    public static ag m195g() {
        return new ag();
    }

    /* renamed from: a */
    public final void m196a(C4073e c4073e) {
        m198c();
        if ((this.f93b & 1) == 1) {
            c4073e.a(1, this.f94c);
        }
        if ((this.f93b & 2) == 2) {
            c4073e.a(2, this.f95d);
        }
    }

    /* renamed from: b */
    public final boolean m197b() {
        return (this.f93b & 1) == 1;
    }

    /* renamed from: c */
    public final int m198c() {
        int i = this.f97f;
        if (i == -1) {
            i = 0;
            if ((this.f93b & 1) == 1) {
                i = C4073e.c(1, this.f94c) + 0;
            }
            if ((this.f93b & 2) == 2) {
                i += C4073e.b(2, this.f95d);
            }
            this.f97f = i;
        }
        return i;
    }

    /* renamed from: d */
    public final int m199d() {
        return this.f94c;
    }

    /* renamed from: e */
    public final boolean m200e() {
        return (this.f93b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m201f() {
        return this.f95d;
    }
}
