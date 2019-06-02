package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.bf */
public final class bf extends C4077i<be, bf> {
    /* renamed from: a */
    private int f240a;
    /* renamed from: b */
    private long f241b;
    /* renamed from: c */
    private bg f242c = bg.m553a();
    /* renamed from: d */
    private long f243d;

    private bf() {
    }

    /* renamed from: c */
    private bf m540c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f240a |= 1;
                    this.f241b = c4072d.b();
                    continue;
                case 18:
                    Object j = bg.m558j();
                    if (((this.f240a & 2) == 2 ? 1 : null) != null) {
                        j.m572a(this.f242c);
                    }
                    c4072d.a(j, c4075g);
                    m546a(j.m575b());
                    continue;
                case 24:
                    this.f240a |= 4;
                    this.f243d = c4072d.b();
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
    private bf m542f() {
        return new bf().m545a(m548b());
    }

    /* renamed from: a */
    public final be m543a() {
        be b = m548b();
        if (b.m539i()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bf m544a(long j) {
        this.f240a |= 1;
        this.f241b = j;
        return this;
    }

    /* renamed from: a */
    public final bf m545a(be beVar) {
        if (beVar != be.m526a()) {
            if (beVar.m532b()) {
                m544a(beVar.m534d());
            }
            if (beVar.m535e()) {
                bg f = beVar.m536f();
                if ((this.f240a & 2) != 2 || this.f242c == bg.m553a()) {
                    this.f242c = f;
                } else {
                    this.f242c = bg.m554a(this.f242c).m572a(f).m575b();
                }
                this.f240a |= 2;
            }
            if (beVar.m537g()) {
                long h = beVar.m538h();
                this.f240a |= 4;
                this.f243d = h;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final bf m546a(bg bgVar) {
        if (bgVar == null) {
            throw new NullPointerException();
        }
        this.f242c = bgVar;
        this.f240a |= 2;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m547a(C4072d c4072d, C4075g c4075g) {
        return m540c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final be m548b() {
        int i = 1;
        be beVar = new be();
        int i2 = this.f240a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        beVar.f235c = this.f241b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        beVar.f236d = this.f242c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        beVar.f237e = this.f243d;
        beVar.f234b = i;
        return beVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m549b(C4072d c4072d, C4075g c4075g) {
        return m540c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m550c() {
        return m542f();
    }

    public final /* synthetic */ Object clone() {
        return m542f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m551d() {
        return m542f();
    }
}
