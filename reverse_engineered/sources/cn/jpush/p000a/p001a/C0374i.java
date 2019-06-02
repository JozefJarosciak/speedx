package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.i */
public final class C0374i extends C4077i<C0373h, C0374i> {
    /* renamed from: a */
    private int f321a;
    /* renamed from: b */
    private long f322b;

    private C0374i() {
    }

    /* renamed from: c */
    private C0374i m761c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f321a |= 1;
                    this.f322b = c4072d.b();
                    continue;
                default:
                    if (!c4072d.b(a)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    /* renamed from: f */
    private C0374i m763f() {
        return new C0374i().m766a(m768b());
    }

    /* renamed from: a */
    public final C0373h m764a() {
        C0373h b = m768b();
        if (b.m760e()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0374i m765a(long j) {
        this.f321a |= 1;
        this.f322b = j;
        return this;
    }

    /* renamed from: a */
    public final C0374i m766a(C0373h c0373h) {
        if (c0373h != C0373h.m753a() && c0373h.m757b()) {
            m765a(c0373h.m759d());
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m767a(C4072d c4072d, C4075g c4075g) {
        return m761c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0373h m768b() {
        int i = 1;
        C0373h c0373h = new C0373h();
        if ((this.f321a & 1) != 1) {
            i = 0;
        }
        c0373h.f318c = this.f322b;
        c0373h.f317b = i;
        return c0373h;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m769b(C4072d c4072d, C4075g c4075g) {
        return m761c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m770c() {
        return m763f();
    }

    public final /* synthetic */ Object clone() {
        return m763f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m771d() {
        return m763f();
    }
}
