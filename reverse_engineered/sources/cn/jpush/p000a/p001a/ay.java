package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.ay */
public final class ay extends C4076h {
    /* renamed from: a */
    private static final ay f180a;
    /* renamed from: b */
    private int f181b;
    /* renamed from: c */
    private long f182c;
    /* renamed from: d */
    private int f183d;
    /* renamed from: e */
    private int f184e;
    /* renamed from: f */
    private byte f185f;
    /* renamed from: g */
    private int f186g;

    static {
        ay ayVar = new ay();
        f180a = ayVar;
        ayVar.f182c = 0;
        ayVar.f183d = 0;
        ayVar.f184e = 0;
    }

    private ay() {
        this.f185f = (byte) -1;
        this.f186g = -1;
    }

    private ay(az azVar) {
        super((byte) 0);
        this.f185f = (byte) -1;
        this.f186g = -1;
    }

    /* renamed from: a */
    public static ay m416a() {
        return f180a;
    }

    /* renamed from: a */
    public static az m417a(ay ayVar) {
        return new az().m433a(ayVar);
    }

    /* renamed from: i */
    public static az m420i() {
        return new az();
    }

    /* renamed from: a */
    public final void m421a(C4073e c4073e) {
        m423c();
        if ((this.f181b & 1) == 1) {
            c4073e.a(1, this.f182c);
        }
        if ((this.f181b & 2) == 2) {
            c4073e.a(2, this.f183d);
        }
        if ((this.f181b & 4) == 4) {
            c4073e.a(3, this.f184e);
        }
    }

    /* renamed from: b */
    public final boolean m422b() {
        return (this.f181b & 1) == 1;
    }

    /* renamed from: c */
    public final int m423c() {
        int i = this.f186g;
        if (i == -1) {
            i = 0;
            if ((this.f181b & 1) == 1) {
                i = C4073e.b(1, this.f182c) + 0;
            }
            if ((this.f181b & 2) == 2) {
                i += C4073e.c(2, this.f183d);
            }
            if ((this.f181b & 4) == 4) {
                i += C4073e.c(3, this.f184e);
            }
            this.f186g = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m424d() {
        return this.f182c;
    }

    /* renamed from: e */
    public final boolean m425e() {
        return (this.f181b & 2) == 2;
    }

    /* renamed from: f */
    public final int m426f() {
        return this.f183d;
    }

    /* renamed from: g */
    public final boolean m427g() {
        return (this.f181b & 4) == 4;
    }

    /* renamed from: h */
    public final int m428h() {
        return this.f184e;
    }
}
