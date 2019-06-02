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

/* renamed from: cn.jpush.a.a.n */
public final class C0379n extends C4077i<C0378m, C0379n> {
    /* renamed from: a */
    private int f341a;
    /* renamed from: b */
    private long f342b;
    /* renamed from: c */
    private int f343c;
    /* renamed from: d */
    private List<Long> f344d = Collections.emptyList();

    private C0379n() {
    }

    /* renamed from: c */
    private C0379n m816c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f341a |= 1;
                    this.f342b = c4072d.b();
                    continue;
                case 16:
                    this.f341a |= 2;
                    this.f343c = c4072d.c();
                    continue;
                case 24:
                    m819g();
                    this.f344d.add(Long.valueOf(c4072d.b()));
                    continue;
                case 26:
                    a = c4072d.c(c4072d.f());
                    while (c4072d.g() > 0) {
                        long b = c4072d.b();
                        m819g();
                        this.f344d.add(Long.valueOf(b));
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
    private C0379n m818f() {
        return new C0379n().m823a(m826b());
    }

    /* renamed from: g */
    private void m819g() {
        if ((this.f341a & 4) != 4) {
            this.f344d = new ArrayList(this.f344d);
            this.f341a |= 4;
        }
    }

    /* renamed from: a */
    public final C0378m m820a() {
        C0378m b = m826b();
        if (b.m815g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0379n m821a(int i) {
        this.f341a |= 2;
        this.f343c = i;
        return this;
    }

    /* renamed from: a */
    public final C0379n m822a(long j) {
        this.f341a |= 1;
        this.f342b = j;
        return this;
    }

    /* renamed from: a */
    public final C0379n m823a(C0378m c0378m) {
        if (c0378m != C0378m.m803a()) {
            if (c0378m.m810b()) {
                m822a(c0378m.m812d());
            }
            if (c0378m.m813e()) {
                m821a(c0378m.m814f());
            }
            if (!c0378m.f338e.isEmpty()) {
                if (this.f344d.isEmpty()) {
                    this.f344d = c0378m.f338e;
                    this.f341a &= -5;
                } else {
                    m819g();
                    this.f344d.addAll(c0378m.f338e);
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0379n m824a(Iterable<? extends Long> iterable) {
        m819g();
        C4077i.a(iterable, this.f344d);
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m825a(C4072d c4072d, C4075g c4075g) {
        return m816c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0378m m826b() {
        int i = 1;
        C0378m c0378m = new C0378m();
        int i2 = this.f341a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0378m.f336c = this.f342b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0378m.f337d = this.f343c;
        if ((this.f341a & 4) == 4) {
            this.f344d = Collections.unmodifiableList(this.f344d);
            this.f341a &= -5;
        }
        c0378m.f338e = this.f344d;
        c0378m.f335b = i;
        return c0378m;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m827b(C4072d c4072d, C4075g c4075g) {
        return m816c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m828c() {
        return m818f();
    }

    public final /* synthetic */ Object clone() {
        return m818f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m829d() {
        return m818f();
    }
}
