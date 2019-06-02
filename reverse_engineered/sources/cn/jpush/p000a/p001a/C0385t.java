package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.t */
public final class C0385t extends C4077i<C0384s, C0385t> {
    /* renamed from: a */
    private int f376a;
    /* renamed from: b */
    private long f377b;

    private C0385t() {
    }

    /* renamed from: c */
    private C0385t m905c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f376a |= 1;
                    this.f377b = c4072d.b();
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
    private C0385t m907f() {
        return new C0385t().m910a(m912b());
    }

    /* renamed from: a */
    public final C0384s m908a() {
        C0384s b = m912b();
        if (b.m904e()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0385t m909a(long j) {
        this.f376a |= 1;
        this.f377b = j;
        return this;
    }

    /* renamed from: a */
    public final C0385t m910a(C0384s c0384s) {
        if (c0384s != C0384s.m897a() && c0384s.m901b()) {
            m909a(c0384s.m903d());
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m911a(C4072d c4072d, C4075g c4075g) {
        return m905c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0384s m912b() {
        int i = 1;
        C0384s c0384s = new C0384s();
        if ((this.f376a & 1) != 1) {
            i = 0;
        }
        c0384s.f373c = this.f377b;
        c0384s.f372b = i;
        return c0384s;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m913b(C4072d c4072d, C4075g c4075g) {
        return m905c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m914c() {
        return m907f();
    }

    public final /* synthetic */ Object clone() {
        return m907f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m915d() {
        return m907f();
    }
}
