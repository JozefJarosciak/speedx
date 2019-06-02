package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.bq */
public final class bq extends C4077i<bp, bq> {
    /* renamed from: a */
    private int f291a;
    /* renamed from: b */
    private C4071c f292b = C4071c.f14609a;

    private bq() {
    }

    /* renamed from: c */
    private bq m671c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    this.f291a |= 1;
                    this.f292b = c4072d.d();
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
    private bq m673f() {
        return new bq().m675a(m678b());
    }

    /* renamed from: a */
    public final bp m674a() {
        bp b = m678b();
        if (b.m670e()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bq m675a(bp bpVar) {
        if (bpVar != bp.m662a() && bpVar.m667b()) {
            m676a(bpVar.m669d());
        }
        return this;
    }

    /* renamed from: a */
    public final bq m676a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f291a |= 1;
        this.f292b = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m677a(C4072d c4072d, C4075g c4075g) {
        return m671c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final bp m678b() {
        int i = 1;
        bp bpVar = new bp();
        if ((this.f291a & 1) != 1) {
            i = 0;
        }
        bpVar.f288c = this.f292b;
        bpVar.f287b = i;
        return bpVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m679b(C4072d c4072d, C4075g c4075g) {
        return m671c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m680c() {
        return m673f();
    }

    public final /* synthetic */ Object clone() {
        return m673f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m681d() {
        return m673f();
    }
}
