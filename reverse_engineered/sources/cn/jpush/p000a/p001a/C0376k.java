package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.k */
public final class C0376k extends C4077i<C0375j, C0376k> {
    /* renamed from: a */
    private int f330a;
    /* renamed from: b */
    private long f331b;
    /* renamed from: c */
    private C4071c f332c = C4071c.f14609a;
    /* renamed from: d */
    private C4071c f333d = C4071c.f14609a;

    private C0376k() {
    }

    /* renamed from: c */
    private C0376k m788c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f330a |= 1;
                    this.f331b = c4072d.b();
                    continue;
                case 18:
                    this.f330a |= 2;
                    this.f332c = c4072d.d();
                    continue;
                case 26:
                    this.f330a |= 4;
                    this.f333d = c4072d.d();
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
    private C0376k m790f() {
        return new C0376k().m793a(m796b());
    }

    /* renamed from: a */
    public final C0375j m791a() {
        C0375j b = m796b();
        if (b.m787i()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0376k m792a(long j) {
        this.f330a |= 1;
        this.f331b = j;
        return this;
    }

    /* renamed from: a */
    public final C0376k m793a(C0375j c0375j) {
        if (c0375j != C0375j.m774a()) {
            if (c0375j.m780b()) {
                m792a(c0375j.m782d());
            }
            if (c0375j.m783e()) {
                m794a(c0375j.m784f());
            }
            if (c0375j.m785g()) {
                m797b(c0375j.m786h());
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0376k m794a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f330a |= 2;
        this.f332c = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m795a(C4072d c4072d, C4075g c4075g) {
        return m788c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0375j m796b() {
        int i = 1;
        C0375j c0375j = new C0375j();
        int i2 = this.f330a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0375j.f325c = this.f331b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0375j.f326d = this.f332c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        c0375j.f327e = this.f333d;
        c0375j.f324b = i;
        return c0375j;
    }

    /* renamed from: b */
    public final C0376k m797b(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f330a |= 4;
        this.f333d = c4071c;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m798b(C4072d c4072d, C4075g c4075g) {
        return m788c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m799c() {
        return m790f();
    }

    public final /* synthetic */ Object clone() {
        return m790f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m800d() {
        return m790f();
    }
}
