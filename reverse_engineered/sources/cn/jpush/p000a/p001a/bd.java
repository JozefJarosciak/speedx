package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;

/* renamed from: cn.jpush.a.a.bd */
public final class bd extends C4077i<bc, bd> {
    /* renamed from: a */
    private int f227a;
    /* renamed from: b */
    private long f228b;
    /* renamed from: c */
    private int f229c;
    /* renamed from: d */
    private long f230d;
    /* renamed from: e */
    private long f231e;
    /* renamed from: f */
    private C4071c f232f = C4071c.f14609a;

    private bd() {
    }

    /* renamed from: c */
    private bd m516c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f227a |= 1;
                    this.f228b = c4072d.b();
                    continue;
                case 16:
                    this.f227a |= 2;
                    this.f229c = c4072d.c();
                    continue;
                case 24:
                    this.f227a |= 4;
                    this.f230d = c4072d.b();
                    continue;
                case 32:
                    this.f227a |= 8;
                    this.f231e = c4072d.b();
                    continue;
                case 42:
                    this.f227a |= 16;
                    this.f232f = c4072d.d();
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
    private bd m517e() {
        return new bd().m519a(m518a());
    }

    /* renamed from: a */
    public final bc m518a() {
        int i = 1;
        bc bcVar = new bc();
        int i2 = this.f227a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        bcVar.f220c = this.f228b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        bcVar.f221d = this.f229c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        bcVar.f222e = this.f230d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        bcVar.f223f = this.f231e;
        if ((i2 & 16) == 16) {
            i |= 16;
        }
        bcVar.f224g = this.f232f;
        bcVar.f219b = i;
        return bcVar;
    }

    /* renamed from: a */
    public final bd m519a(bc bcVar) {
        if (bcVar != bc.m496a()) {
            long d;
            if (bcVar.m504b()) {
                d = bcVar.m506d();
                this.f227a |= 1;
                this.f228b = d;
            }
            if (bcVar.m507e()) {
                int f = bcVar.m508f();
                this.f227a |= 2;
                this.f229c = f;
            }
            if (bcVar.m509g()) {
                d = bcVar.m510h();
                this.f227a |= 4;
                this.f230d = d;
            }
            if (bcVar.m511i()) {
                d = bcVar.m512j();
                this.f227a |= 8;
                this.f231e = d;
            }
            if (bcVar.m513k()) {
                C4071c l = bcVar.m514l();
                if (l == null) {
                    throw new NullPointerException();
                }
                this.f227a |= 16;
                this.f232f = l;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m520a(C4072d c4072d, C4075g c4075g) {
        return m516c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m521b(C4072d c4072d, C4075g c4075g) {
        return m516c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m522c() {
        return m517e();
    }

    public final /* synthetic */ Object clone() {
        return m517e();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m523d() {
        return m517e();
    }
}
