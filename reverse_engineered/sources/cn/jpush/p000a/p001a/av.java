package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.av */
public final class av extends C4077i<au, av> {
    /* renamed from: a */
    private int f168a;
    /* renamed from: b */
    private long f169b;
    /* renamed from: c */
    private long f170c;

    private av() {
    }

    /* renamed from: c */
    private av m379c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f168a |= 1;
                    this.f169b = c4072d.b();
                    continue;
                case 16:
                    this.f168a |= 2;
                    this.f170c = c4072d.b();
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
    private av m381f() {
        return new av().m384a(m386b());
    }

    /* renamed from: a */
    public final au m382a() {
        au b = m386b();
        if (b.m378g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final av m383a(long j) {
        this.f168a |= 1;
        this.f169b = j;
        return this;
    }

    /* renamed from: a */
    public final av m384a(au auVar) {
        if (auVar != au.m368a()) {
            if (auVar.m373b()) {
                m383a(auVar.m375d());
            }
            if (auVar.m376e()) {
                long f = auVar.m377f();
                this.f168a |= 2;
                this.f170c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m385a(C4072d c4072d, C4075g c4075g) {
        return m379c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final au m386b() {
        int i = 1;
        au auVar = new au();
        int i2 = this.f168a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        auVar.f164c = this.f169b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        auVar.f165d = this.f170c;
        auVar.f163b = i;
        return auVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m387b(C4072d c4072d, C4075g c4075g) {
        return m379c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m388c() {
        return m381f();
    }

    public final /* synthetic */ Object clone() {
        return m381f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m389d() {
        return m381f();
    }
}
