package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.al */
public final class al extends C4077i<ak, al> {
    /* renamed from: a */
    private int f116a;
    /* renamed from: b */
    private long f117b;
    /* renamed from: c */
    private long f118c;

    private al() {
    }

    /* renamed from: c */
    private al m247c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f116a |= 1;
                    this.f117b = c4072d.b();
                    continue;
                case 16:
                    this.f116a |= 2;
                    this.f118c = c4072d.b();
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
    private al m249f() {
        return new al().m252a(m254b());
    }

    /* renamed from: a */
    public final ak m250a() {
        ak b = m254b();
        if (b.m246g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final al m251a(long j) {
        this.f116a |= 1;
        this.f117b = j;
        return this;
    }

    /* renamed from: a */
    public final al m252a(ak akVar) {
        if (akVar != ak.m236a()) {
            if (akVar.m241b()) {
                m251a(akVar.m243d());
            }
            if (akVar.m244e()) {
                long f = akVar.m245f();
                this.f116a |= 2;
                this.f118c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m253a(C4072d c4072d, C4075g c4075g) {
        return m247c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final ak m254b() {
        int i = 1;
        ak akVar = new ak();
        int i2 = this.f116a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        akVar.f112c = this.f117b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        akVar.f113d = this.f118c;
        akVar.f111b = i;
        return akVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m255b(C4072d c4072d, C4075g c4075g) {
        return m247c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m256c() {
        return m249f();
    }

    public final /* synthetic */ Object clone() {
        return m249f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m257d() {
        return m249f();
    }
}
