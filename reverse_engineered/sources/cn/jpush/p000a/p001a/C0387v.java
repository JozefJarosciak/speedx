package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.v */
public final class C0387v extends C4077i<C0386u, C0387v> {
    /* renamed from: a */
    private int f385a;
    /* renamed from: b */
    private long f386b;
    /* renamed from: c */
    private C4071c f387c = C4071c.f14609a;
    /* renamed from: d */
    private C4071c f388d = C4071c.f14609a;

    private C0387v() {
    }

    /* renamed from: c */
    private C0387v m932c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f385a |= 1;
                    this.f386b = c4072d.b();
                    continue;
                case 18:
                    this.f385a |= 2;
                    this.f387c = c4072d.d();
                    continue;
                case 26:
                    this.f385a |= 4;
                    this.f388d = c4072d.d();
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
    private C0387v m934f() {
        return new C0387v().m937a(m940b());
    }

    /* renamed from: a */
    public final C0386u m935a() {
        C0386u b = m940b();
        if (b.m931i()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0387v m936a(long j) {
        this.f385a |= 1;
        this.f386b = j;
        return this;
    }

    /* renamed from: a */
    public final C0387v m937a(C0386u c0386u) {
        if (c0386u != C0386u.m918a()) {
            if (c0386u.m924b()) {
                m936a(c0386u.m926d());
            }
            if (c0386u.m927e()) {
                m938a(c0386u.m928f());
            }
            if (c0386u.m929g()) {
                m941b(c0386u.m930h());
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0387v m938a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f385a |= 2;
        this.f387c = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m939a(C4072d c4072d, C4075g c4075g) {
        return m932c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0386u m940b() {
        int i = 1;
        C0386u c0386u = new C0386u();
        int i2 = this.f385a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0386u.f380c = this.f386b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0386u.f381d = this.f387c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        c0386u.f382e = this.f388d;
        c0386u.f379b = i;
        return c0386u;
    }

    /* renamed from: b */
    public final C0387v m941b(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f385a |= 4;
        this.f388d = c4071c;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m942b(C4072d c4072d, C4075g c4075g) {
        return m932c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m943c() {
        return m934f();
    }

    public final /* synthetic */ Object clone() {
        return m934f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m944d() {
        return m934f();
    }
}
