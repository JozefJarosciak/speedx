package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.ar */
public final class ar extends C4077i<aq, ar> {
    /* renamed from: a */
    private int f151a;
    /* renamed from: b */
    private List<ao> f152b = Collections.emptyList();

    private ar() {
    }

    /* renamed from: c */
    private ar m331c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    Object r = ao.m291r();
                    c4072d.a(r, c4075g);
                    m336a(r.m316b());
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
    private ar m333f() {
        return new ar().m337a(m339b());
    }

    /* renamed from: g */
    private void m334g() {
        if ((this.f151a & 1) != 1) {
            this.f152b = new ArrayList(this.f152b);
            this.f151a |= 1;
        }
    }

    /* renamed from: a */
    public final aq m335a() {
        aq b = m339b();
        if (b.m330d()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final ar m336a(ao aoVar) {
        if (aoVar == null) {
            throw new NullPointerException();
        }
        m334g();
        this.f152b.add(aoVar);
        return this;
    }

    /* renamed from: a */
    public final ar m337a(aq aqVar) {
        if (!(aqVar == aq.m322a() || aqVar.f148b.isEmpty())) {
            if (this.f152b.isEmpty()) {
                this.f152b = aqVar.f148b;
                this.f151a &= -2;
            } else {
                m334g();
                this.f152b.addAll(aqVar.f148b);
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m338a(C4072d c4072d, C4075g c4075g) {
        return m331c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final aq m339b() {
        aq aqVar = new aq();
        if ((this.f151a & 1) == 1) {
            this.f152b = Collections.unmodifiableList(this.f152b);
            this.f151a &= -2;
        }
        aqVar.f148b = this.f152b;
        return aqVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m340b(C4072d c4072d, C4075g c4075g) {
        return m331c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m341c() {
        return m333f();
    }

    public final /* synthetic */ Object clone() {
        return m333f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m342d() {
        return m333f();
    }
}
