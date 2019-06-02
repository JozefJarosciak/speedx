package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;

/* renamed from: cn.jpush.a.a.ag */
public final class ag extends C4077i<af, ag> {
    /* renamed from: a */
    private int f98a;
    /* renamed from: b */
    private int f99b;
    /* renamed from: c */
    private C4071c f100c = C4071c.f14609a;

    private ag() {
    }

    /* renamed from: c */
    private ag m203c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f98a |= 1;
                    this.f99b = c4072d.c();
                    continue;
                case 18:
                    this.f98a |= 2;
                    this.f100c = c4072d.d();
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
    private ag m204e() {
        return new ag().m206a(m205a());
    }

    /* renamed from: a */
    public final af m205a() {
        int i = 1;
        af afVar = new af();
        int i2 = this.f98a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        afVar.f94c = this.f99b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        afVar.f95d = this.f100c;
        afVar.f93b = i;
        return afVar;
    }

    /* renamed from: a */
    public final ag m206a(af afVar) {
        if (afVar != af.m191a()) {
            if (afVar.m197b()) {
                int d = afVar.m199d();
                this.f98a |= 1;
                this.f99b = d;
            }
            if (afVar.m200e()) {
                C4071c f = afVar.m201f();
                if (f == null) {
                    throw new NullPointerException();
                }
                this.f98a |= 2;
                this.f100c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m207a(C4072d c4072d, C4075g c4075g) {
        return m203c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m208b(C4072d c4072d, C4075g c4075g) {
        return m203c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m209c() {
        return m204e();
    }

    public final /* synthetic */ Object clone() {
        return m204e();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m210d() {
        return m204e();
    }
}
