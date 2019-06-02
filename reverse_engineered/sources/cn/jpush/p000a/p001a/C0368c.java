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

/* renamed from: cn.jpush.a.a.c */
public final class C0368c extends C4077i<C0367b, C0368c> {
    /* renamed from: a */
    private int f293a;
    /* renamed from: b */
    private List<Long> f294b = Collections.emptyList();

    private C0368c() {
    }

    /* renamed from: c */
    private C0368c m682c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    m685g();
                    this.f294b.add(Long.valueOf(c4072d.b()));
                    continue;
                case 10:
                    a = c4072d.c(c4072d.f());
                    while (c4072d.g() > 0) {
                        long b = c4072d.b();
                        m685g();
                        this.f294b.add(Long.valueOf(b));
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
    private C0368c m684f() {
        return new C0368c().m687a(m690b());
    }

    /* renamed from: g */
    private void m685g() {
        if ((this.f293a & 1) != 1) {
            this.f294b = new ArrayList(this.f294b);
            this.f293a |= 1;
        }
    }

    /* renamed from: a */
    public final C0367b m686a() {
        C0367b b = m690b();
        if (b.m444b()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0368c m687a(C0367b c0367b) {
        if (!(c0367b == C0367b.m438a() || c0367b.f192b.isEmpty())) {
            if (this.f294b.isEmpty()) {
                this.f294b = c0367b.f192b;
                this.f293a &= -2;
            } else {
                m685g();
                this.f294b.addAll(c0367b.f192b);
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0368c m688a(Iterable<? extends Long> iterable) {
        m685g();
        C4077i.a(iterable, this.f294b);
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m689a(C4072d c4072d, C4075g c4075g) {
        return m682c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0367b m690b() {
        C0367b c0367b = new C0367b();
        if ((this.f293a & 1) == 1) {
            this.f294b = Collections.unmodifiableList(this.f294b);
            this.f293a &= -2;
        }
        c0367b.f192b = this.f294b;
        return c0367b;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m691b(C4072d c4072d, C4075g c4075g) {
        return m682c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m692c() {
        return m684f();
    }

    public final /* synthetic */ Object clone() {
        return m684f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m693d() {
        return m684f();
    }
}
