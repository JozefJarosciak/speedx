package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.g */
public final class C0372g extends C4077i<C0371f, C0372g> {
    /* renamed from: a */
    private int f314a;
    /* renamed from: b */
    private List<Long> f315b = Collections.emptyList();

    private C0372g() {
    }

    /* renamed from: c */
    private C0372g m739c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    m742g();
                    this.f315b.add(Long.valueOf(c4072d.b()));
                    continue;
                case 10:
                    a = c4072d.c(c4072d.f());
                    while (c4072d.g() > 0) {
                        long b = c4072d.b();
                        m742g();
                        this.f315b.add(Long.valueOf(b));
                    }
                    c4072d.d(a);
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
    private C0372g m741f() {
        return new C0372g().m744a(m747b());
    }

    /* renamed from: g */
    private void m742g() {
        if ((this.f314a & 1) != 1) {
            this.f315b = new ArrayList(this.f315b);
            this.f314a |= 1;
        }
    }

    /* renamed from: a */
    public final C0371f m743a() {
        C0371f b = m747b();
        if (b.m737b()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0372g m744a(C0371f c0371f) {
        if (!(c0371f == C0371f.m731a() || c0371f.f311b.isEmpty())) {
            if (this.f315b.isEmpty()) {
                this.f315b = c0371f.f311b;
                this.f314a &= -2;
            } else {
                m742g();
                this.f315b.addAll(c0371f.f311b);
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0372g m745a(Iterable<? extends Long> iterable) {
        m742g();
        C4077i.a(iterable, this.f315b);
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m746a(C4072d c4072d, C4075g c4075g) {
        return m739c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0371f m747b() {
        C0371f c0371f = new C0371f();
        if ((this.f314a & 1) == 1) {
            this.f315b = Collections.unmodifiableList(this.f315b);
            this.f314a &= -2;
        }
        c0371f.f311b = this.f315b;
        return c0371f;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m748b(C4072d c4072d, C4075g c4075g) {
        return m739c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m749c() {
        return m741f();
    }

    public final /* synthetic */ Object clone() {
        return m741f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m750d() {
        return m741f();
    }
}
