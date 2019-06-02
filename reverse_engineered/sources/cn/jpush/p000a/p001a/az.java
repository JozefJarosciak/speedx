package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;

/* renamed from: cn.jpush.a.a.az */
public final class az extends C4077i<ay, az> {
    /* renamed from: a */
    private int f187a;
    /* renamed from: b */
    private long f188b;
    /* renamed from: c */
    private int f189c;
    /* renamed from: d */
    private int f190d;

    private az() {
    }

    /* renamed from: c */
    private az m430c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f187a |= 1;
                    this.f188b = c4072d.b();
                    continue;
                case 16:
                    this.f187a |= 2;
                    this.f189c = c4072d.c();
                    continue;
                case 24:
                    this.f187a |= 4;
                    this.f190d = c4072d.c();
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

    /* renamed from: e */
    private az m431e() {
        return new az().m433a(m432a());
    }

    /* renamed from: a */
    public final ay m432a() {
        int i = 1;
        ay ayVar = new ay();
        int i2 = this.f187a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        ayVar.f182c = this.f188b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        ayVar.f183d = this.f189c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        ayVar.f184e = this.f190d;
        ayVar.f181b = i;
        return ayVar;
    }

    /* renamed from: a */
    public final az m433a(ay ayVar) {
        if (ayVar != ay.m416a()) {
            int f;
            if (ayVar.m422b()) {
                long d = ayVar.m424d();
                this.f187a |= 1;
                this.f188b = d;
            }
            if (ayVar.m425e()) {
                f = ayVar.m426f();
                this.f187a |= 2;
                this.f189c = f;
            }
            if (ayVar.m427g()) {
                f = ayVar.m428h();
                this.f187a |= 4;
                this.f190d = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m434a(C4072d c4072d, C4075g c4075g) {
        return m430c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m435b(C4072d c4072d, C4075g c4075g) {
        return m430c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m436c() {
        return m431e();
    }

    public final /* synthetic */ Object clone() {
        return m431e();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m437d() {
        return m431e();
    }
}
