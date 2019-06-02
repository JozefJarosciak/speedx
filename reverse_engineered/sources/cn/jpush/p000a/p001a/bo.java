package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.bo */
public final class bo extends C4077i<bn, bo> {
    /* renamed from: a */
    private int f284a;
    /* renamed from: b */
    private C4071c f285b = C4071c.f14609a;

    private bo() {
    }

    /* renamed from: c */
    private bo m650c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    this.f284a |= 1;
                    this.f285b = c4072d.d();
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
    private bo m652f() {
        return new bo().m654a(m657b());
    }

    /* renamed from: a */
    public final bn m653a() {
        bn b = m657b();
        if (b.m649e()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bo m654a(bn bnVar) {
        if (bnVar != bn.m641a() && bnVar.m646b()) {
            m655a(bnVar.m648d());
        }
        return this;
    }

    /* renamed from: a */
    public final bo m655a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f284a |= 1;
        this.f285b = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m656a(C4072d c4072d, C4075g c4075g) {
        return m650c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final bn m657b() {
        int i = 1;
        bn bnVar = new bn();
        if ((this.f284a & 1) != 1) {
            i = 0;
        }
        bnVar.f281c = this.f285b;
        bnVar.f280b = i;
        return bnVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m658b(C4072d c4072d, C4075g c4075g) {
        return m650c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m659c() {
        return m652f();
    }

    public final /* synthetic */ Object clone() {
        return m652f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m660d() {
        return m652f();
    }
}
