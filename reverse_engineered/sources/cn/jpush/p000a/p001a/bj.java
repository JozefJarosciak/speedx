package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.bj */
public final class bj extends C4077i<bi, bj> {
    /* renamed from: a */
    private int f262a;
    /* renamed from: b */
    private long f263b;
    /* renamed from: c */
    private bg f264c = bg.m553a();
    /* renamed from: d */
    private long f265d;

    private bj() {
    }

    /* renamed from: c */
    private bj m595c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f262a |= 1;
                    this.f263b = c4072d.b();
                    continue;
                case 18:
                    Object j = bg.m558j();
                    if (((this.f262a & 2) == 2 ? 1 : null) != null) {
                        j.m572a(this.f264c);
                    }
                    c4072d.a(j, c4075g);
                    m600a(j.m575b());
                    continue;
                case 24:
                    this.f262a |= 4;
                    this.f265d = c4072d.b();
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
    private bj m597f() {
        return new bj().m601a(m603b());
    }

    /* renamed from: a */
    public final bi m598a() {
        bi b = m603b();
        if (b.m594i()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bj m599a(long j) {
        this.f262a |= 1;
        this.f263b = j;
        return this;
    }

    /* renamed from: a */
    public final bj m600a(bg bgVar) {
        if (bgVar == null) {
            throw new NullPointerException();
        }
        this.f264c = bgVar;
        this.f262a |= 2;
        return this;
    }

    /* renamed from: a */
    public final bj m601a(bi biVar) {
        if (biVar != bi.m582a()) {
            if (biVar.m587b()) {
                m599a(biVar.m589d());
            }
            if (biVar.m590e()) {
                bg f = biVar.m591f();
                if ((this.f262a & 2) != 2 || this.f264c == bg.m553a()) {
                    this.f264c = f;
                } else {
                    this.f264c = bg.m554a(this.f264c).m572a(f).m575b();
                }
                this.f262a |= 2;
            }
            if (biVar.m592g()) {
                long h = biVar.m593h();
                this.f262a |= 4;
                this.f265d = h;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m602a(C4072d c4072d, C4075g c4075g) {
        return m595c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final bi m603b() {
        int i = 1;
        bi biVar = new bi();
        int i2 = this.f262a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        biVar.f257c = this.f263b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        biVar.f258d = this.f264c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        biVar.f259e = this.f265d;
        biVar.f256b = i;
        return biVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m604b(C4072d c4072d, C4075g c4075g) {
        return m595c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m605c() {
        return m597f();
    }

    public final /* synthetic */ Object clone() {
        return m597f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m606d() {
        return m597f();
    }
}
