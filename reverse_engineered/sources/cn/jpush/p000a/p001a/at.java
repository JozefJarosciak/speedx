package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.at */
public final class at extends C4077i<as, at> {
    /* renamed from: a */
    private int f159a;
    /* renamed from: b */
    private int f160b;
    /* renamed from: c */
    private long f161c;

    private at() {
    }

    /* renamed from: c */
    private at m356c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f159a |= 1;
                    this.f160b = c4072d.c();
                    continue;
                case 16:
                    this.f159a |= 2;
                    this.f161c = c4072d.b();
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
    private at m358f() {
        return new at().m360a(m362b());
    }

    /* renamed from: a */
    public final as m359a() {
        as b = m362b();
        if (b.m355g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final at m360a(as asVar) {
        if (asVar != as.m345a()) {
            if (asVar.m350b()) {
                int d = asVar.m352d();
                this.f159a |= 1;
                this.f160b = d;
            }
            if (asVar.m353e()) {
                long f = asVar.m354f();
                this.f159a |= 2;
                this.f161c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m361a(C4072d c4072d, C4075g c4075g) {
        return m356c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final as m362b() {
        int i = 1;
        as asVar = new as();
        int i2 = this.f159a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        asVar.f155c = this.f160b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        asVar.f156d = this.f161c;
        asVar.f154b = i;
        return asVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m363b(C4072d c4072d, C4075g c4075g) {
        return m356c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m364c() {
        return m358f();
    }

    public final /* synthetic */ Object clone() {
        return m358f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m365d() {
        return m358f();
    }
}
