package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4078j;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.aa */
public final class aa extends C4077i<C0391z, aa> {
    /* renamed from: a */
    private int f15a;
    /* renamed from: b */
    private ad f16b = ad.m153a();
    /* renamed from: c */
    private ab f17c = ab.m35a();

    private aa() {
    }

    /* renamed from: a */
    static /* synthetic */ C0391z m22a(aa aaVar) {
        C0391z f = aaVar.m26f();
        if (f.m974g()) {
            return f;
        }
        throw new C4078j(new C4079m().getMessage());
    }

    /* renamed from: c */
    private aa m24c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            Object p;
            switch (a) {
                case 0:
                    break;
                case 10:
                    p = ad.m160p();
                    if (((this.f15a & 1) == 1 ? 1 : null) != null) {
                        p.m182a(this.f16b);
                    }
                    c4072d.a(p, c4075g);
                    m28a(p.m185b());
                    continue;
                case 18:
                    p = ab.ad();
                    if (((this.f15a & 2) == 2 ? 1 : null) != null) {
                        p.m119a(this.f17c);
                    }
                    c4072d.a(p, c4075g);
                    m27a(p.m147b());
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
    private aa m25e() {
        aa aaVar = new aa();
        C0391z f = m26f();
        if (f != C0391z.m965a()) {
            if (f.m969b()) {
                ad d = f.m971d();
                if ((aaVar.f15a & 1) != 1 || aaVar.f16b == ad.m153a()) {
                    aaVar.f16b = d;
                } else {
                    aaVar.f16b = ad.m154a(aaVar.f16b).m182a(d).m185b();
                }
                aaVar.f15a |= 1;
            }
            if (f.m972e()) {
                ab f2 = f.m973f();
                if ((aaVar.f15a & 2) != 2 || aaVar.f17c == ab.m35a()) {
                    aaVar.f17c = f2;
                } else {
                    aaVar.f17c = ab.m36a(aaVar.f17c).m119a(f2).m147b();
                }
                aaVar.f15a |= 2;
            }
        }
        return aaVar;
    }

    /* renamed from: f */
    private C0391z m26f() {
        int i = 1;
        C0391z c0391z = new C0391z();
        int i2 = this.f15a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0391z.f397c = this.f16b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0391z.f398d = this.f17c;
        c0391z.f396b = i;
        return c0391z;
    }

    /* renamed from: a */
    public final aa m27a(ab abVar) {
        if (abVar == null) {
            throw new NullPointerException();
        }
        this.f17c = abVar;
        this.f15a |= 2;
        return this;
    }

    /* renamed from: a */
    public final aa m28a(ad adVar) {
        if (adVar == null) {
            throw new NullPointerException();
        }
        this.f16b = adVar;
        this.f15a |= 1;
        return this;
    }

    /* renamed from: a */
    public final C0391z m29a() {
        C0391z f = m26f();
        if (f.m974g()) {
            return f;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m30a(C4072d c4072d, C4075g c4075g) {
        return m24c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m31b(C4072d c4072d, C4075g c4075g) {
        return m24c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m32c() {
        return m25e();
    }

    public final /* synthetic */ Object clone() {
        return m25e();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m33d() {
        return m25e();
    }
}
