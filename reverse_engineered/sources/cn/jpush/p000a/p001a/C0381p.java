package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.p */
public final class C0381p extends C4077i<C0380o, C0381p> {
    /* renamed from: a */
    private int f354a;
    /* renamed from: b */
    private C4071c f355b = C4071c.f14609a;
    /* renamed from: c */
    private C4071c f356c = C4071c.f14609a;
    /* renamed from: d */
    private int f357d;
    /* renamed from: e */
    private int f358e;
    /* renamed from: f */
    private long f359f;

    private C0381p() {
    }

    /* renamed from: c */
    private C0381p m852c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    this.f354a |= 1;
                    this.f355b = c4072d.d();
                    continue;
                case 18:
                    this.f354a |= 2;
                    this.f356c = c4072d.d();
                    continue;
                case 24:
                    this.f354a |= 4;
                    this.f357d = c4072d.c();
                    continue;
                case 32:
                    this.f354a |= 8;
                    this.f358e = c4072d.c();
                    continue;
                case 40:
                    this.f354a |= 16;
                    this.f359f = c4072d.b();
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
    private C0381p m854f() {
        return new C0381p().m857a(m860b());
    }

    /* renamed from: a */
    public final C0380o m855a() {
        C0380o b = m860b();
        if (b.m851m()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0381p m856a(int i) {
        this.f354a |= 4;
        this.f357d = i;
        return this;
    }

    /* renamed from: a */
    public final C0381p m857a(C0380o c0380o) {
        if (c0380o != C0380o.m832a()) {
            if (c0380o.m840b()) {
                m858a(c0380o.m842d());
            }
            if (c0380o.m843e()) {
                m862b(c0380o.m844f());
            }
            if (c0380o.m845g()) {
                m856a(c0380o.m846h());
            }
            if (c0380o.m847i()) {
                m861b(c0380o.m848j());
            }
            if (c0380o.m849k()) {
                long l = c0380o.m850l();
                this.f354a |= 16;
                this.f359f = l;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0381p m858a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f354a |= 1;
        this.f355b = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m859a(C4072d c4072d, C4075g c4075g) {
        return m852c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0380o m860b() {
        int i = 1;
        C0380o c0380o = new C0380o();
        int i2 = this.f354a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0380o.f347c = this.f355b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0380o.f348d = this.f356c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        c0380o.f349e = this.f357d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        c0380o.f350f = this.f358e;
        if ((i2 & 16) == 16) {
            i |= 16;
        }
        c0380o.f351g = this.f359f;
        c0380o.f346b = i;
        return c0380o;
    }

    /* renamed from: b */
    public final C0381p m861b(int i) {
        this.f354a |= 8;
        this.f358e = i;
        return this;
    }

    /* renamed from: b */
    public final C0381p m862b(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f354a |= 2;
        this.f356c = c4071c;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m863b(C4072d c4072d, C4075g c4075g) {
        return m852c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m864c() {
        return m854f();
    }

    public final /* synthetic */ Object clone() {
        return m854f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m865d() {
        return m854f();
    }
}
