package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.m */
public final class C0378m extends C4076h {
    /* renamed from: a */
    private static final C0378m f334a;
    /* renamed from: b */
    private int f335b;
    /* renamed from: c */
    private long f336c;
    /* renamed from: d */
    private int f337d;
    /* renamed from: e */
    private List<Long> f338e;
    /* renamed from: f */
    private byte f339f;
    /* renamed from: g */
    private int f340g;

    static {
        C0378m c0378m = new C0378m();
        f334a = c0378m;
        c0378m.f336c = 0;
        c0378m.f337d = 0;
        c0378m.f338e = Collections.emptyList();
    }

    private C0378m() {
        this.f339f = (byte) -1;
        this.f340g = -1;
    }

    private C0378m(C0379n c0379n) {
        super((byte) 0);
        this.f339f = (byte) -1;
        this.f340g = -1;
    }

    /* renamed from: a */
    public static C0378m m803a() {
        return f334a;
    }

    /* renamed from: a */
    public static C0379n m804a(C0378m c0378m) {
        return new C0379n().m823a(c0378m);
    }

    /* renamed from: h */
    public static C0379n m808h() {
        return new C0379n();
    }

    /* renamed from: a */
    public final void m809a(C4073e c4073e) {
        m811c();
        if ((this.f335b & 1) == 1) {
            c4073e.a(1, this.f336c);
        }
        if ((this.f335b & 2) == 2) {
            c4073e.a(2, this.f337d);
        }
        for (int i = 0; i < this.f338e.size(); i++) {
            c4073e.a(3, ((Long) this.f338e.get(i)).longValue());
        }
    }

    /* renamed from: b */
    public final boolean m810b() {
        return (this.f335b & 1) == 1;
    }

    /* renamed from: c */
    public final int m811c() {
        int i = 0;
        int i2 = this.f340g;
        if (i2 != -1) {
            return i2;
        }
        i2 = (this.f335b & 1) == 1 ? C4073e.b(1, this.f336c) + 0 : 0;
        int c = (this.f335b & 2) == 2 ? i2 + C4073e.c(2, this.f337d) : i2;
        int i3 = 0;
        while (i < this.f338e.size()) {
            i++;
            i3 = C4073e.a(((Long) this.f338e.get(i)).longValue()) + i3;
        }
        i2 = (c + i3) + (this.f338e.size() * 1);
        this.f340g = i2;
        return i2;
    }

    /* renamed from: d */
    public final long m812d() {
        return this.f336c;
    }

    /* renamed from: e */
    public final boolean m813e() {
        return (this.f335b & 2) == 2;
    }

    /* renamed from: f */
    public final int m814f() {
        return this.f337d;
    }

    /* renamed from: g */
    public final boolean m815g() {
        byte b = this.f339f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f339f = (byte) 1;
            return true;
        }
    }
}
