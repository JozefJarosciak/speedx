package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.e */
public final class C0370e extends C4077i<C0369d, C0370e> {
    /* renamed from: a */
    private int f304a;
    /* renamed from: b */
    private long f305b;
    /* renamed from: c */
    private C4071c f306c = C4071c.f14609a;
    /* renamed from: d */
    private C4071c f307d = C4071c.f14609a;
    /* renamed from: e */
    private int f308e;
    /* renamed from: f */
    private C4071c f309f = C4071c.f14609a;

    private C0370e() {
    }

    /* renamed from: c */
    private C0370e m716c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f304a |= 1;
                    this.f305b = c4072d.b();
                    continue;
                case 18:
                    this.f304a |= 2;
                    this.f306c = c4072d.d();
                    continue;
                case 26:
                    this.f304a |= 4;
                    this.f307d = c4072d.d();
                    continue;
                case 32:
                    this.f304a |= 8;
                    this.f308e = c4072d.c();
                    continue;
                case 42:
                    this.f304a |= 16;
                    this.f309f = c4072d.d();
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
    private C0370e m718f() {
        return new C0370e().m722a(m725b());
    }

    /* renamed from: a */
    public final C0369d m719a() {
        C0369d b = m725b();
        if (b.m715m()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0370e m720a(int i) {
        this.f304a |= 8;
        this.f308e = i;
        return this;
    }

    /* renamed from: a */
    public final C0370e m721a(long j) {
        this.f304a |= 1;
        this.f305b = j;
        return this;
    }

    /* renamed from: a */
    public final C0370e m722a(C0369d c0369d) {
        if (c0369d != C0369d.m696a()) {
            if (c0369d.m704b()) {
                m721a(c0369d.m706d());
            }
            if (c0369d.m707e()) {
                m723a(c0369d.m708f());
            }
            if (c0369d.m709g()) {
                m726b(c0369d.m710h());
            }
            if (c0369d.m711i()) {
                m720a(c0369d.m712j());
            }
            if (c0369d.m713k()) {
                m728c(c0369d.m714l());
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0370e m723a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f304a |= 2;
        this.f306c = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m724a(C4072d c4072d, C4075g c4075g) {
        return m716c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0369d m725b() {
        int i = 1;
        C0369d c0369d = new C0369d();
        int i2 = this.f304a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0369d.f297c = this.f305b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0369d.f298d = this.f306c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        c0369d.f299e = this.f307d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        c0369d.f300f = this.f308e;
        if ((i2 & 16) == 16) {
            i |= 16;
        }
        c0369d.f301g = this.f309f;
        c0369d.f296b = i;
        return c0369d;
    }

    /* renamed from: b */
    public final C0370e m726b(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f304a |= 4;
        this.f307d = c4071c;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m727b(C4072d c4072d, C4075g c4075g) {
        return m716c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final C0370e m728c(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f304a |= 16;
        this.f309f = c4071c;
        return this;
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m729c() {
        return m718f();
    }

    public final /* synthetic */ Object clone() {
        return m718f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m730d() {
        return m718f();
    }
}
