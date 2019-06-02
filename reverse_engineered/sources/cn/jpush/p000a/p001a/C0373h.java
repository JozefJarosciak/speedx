package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.h */
public final class C0373h extends C4076h {
    /* renamed from: a */
    private static final C0373h f316a;
    /* renamed from: b */
    private int f317b;
    /* renamed from: c */
    private long f318c;
    /* renamed from: d */
    private byte f319d;
    /* renamed from: e */
    private int f320e;

    static {
        C0373h c0373h = new C0373h();
        f316a = c0373h;
        c0373h.f318c = 0;
    }

    private C0373h() {
        this.f319d = (byte) -1;
        this.f320e = -1;
    }

    private C0373h(C0374i c0374i) {
        super((byte) 0);
        this.f319d = (byte) -1;
        this.f320e = -1;
    }

    /* renamed from: a */
    public static C0373h m753a() {
        return f316a;
    }

    /* renamed from: a */
    public static C0374i m754a(C0373h c0373h) {
        return new C0374i().m766a(c0373h);
    }

    /* renamed from: f */
    public static C0374i m755f() {
        return new C0374i();
    }

    /* renamed from: a */
    public final void m756a(C4073e c4073e) {
        m758c();
        if ((this.f317b & 1) == 1) {
            c4073e.a(1, this.f318c);
        }
    }

    /* renamed from: b */
    public final boolean m757b() {
        return (this.f317b & 1) == 1;
    }

    /* renamed from: c */
    public final int m758c() {
        int i = this.f320e;
        if (i == -1) {
            i = 0;
            if ((this.f317b & 1) == 1) {
                i = C4073e.b(1, this.f318c) + 0;
            }
            this.f320e = i;
        }
        return i;
    }

    /* renamed from: d */
    public final long m759d() {
        return this.f318c;
    }

    /* renamed from: e */
    public final boolean m760e() {
        byte b = this.f319d;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f319d = (byte) 1;
            return true;
        }
    }
}
