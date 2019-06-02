package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.an */
public final class an extends C4077i<am, an> {
    /* renamed from: a */
    private int f125a;
    /* renamed from: b */
    private long f126b;
    /* renamed from: c */
    private long f127c;

    private an() {
    }

    /* renamed from: c */
    private an m271c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f125a |= 1;
                    this.f126b = c4072d.b();
                    continue;
                case 16:
                    this.f125a |= 2;
                    this.f127c = c4072d.b();
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
    private an m273f() {
        return new an().m276a(m278b());
    }

    /* renamed from: a */
    public final am m274a() {
        am b = m278b();
        if (b.m270g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final an m275a(long j) {
        this.f125a |= 1;
        this.f126b = j;
        return this;
    }

    /* renamed from: a */
    public final an m276a(am amVar) {
        if (amVar != am.m260a()) {
            if (amVar.m265b()) {
                m275a(amVar.m267d());
            }
            if (amVar.m268e()) {
                long f = amVar.m269f();
                this.f125a |= 2;
                this.f127c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m277a(C4072d c4072d, C4075g c4075g) {
        return m271c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final am m278b() {
        int i = 1;
        am amVar = new am();
        int i2 = this.f125a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        amVar.f121c = this.f126b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        amVar.f122d = this.f127c;
        amVar.f120b = i;
        return amVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m279b(C4072d c4072d, C4075g c4075g) {
        return m271c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m280c() {
        return m273f();
    }

    public final /* synthetic */ Object clone() {
        return m273f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m281d() {
        return m273f();
    }
}
