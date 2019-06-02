package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.bh */
public final class bh extends C4077i<bg, bh> {
    /* renamed from: a */
    private int f251a;
    /* renamed from: b */
    private C4071c f252b = C4071c.f14609a;
    /* renamed from: c */
    private C4071c f253c = C4071c.f14609a;
    /* renamed from: d */
    private C4071c f254d = C4071c.f14609a;

    private bh() {
    }

    /* renamed from: c */
    private bh m568c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    this.f251a |= 1;
                    this.f252b = c4072d.d();
                    continue;
                case 18:
                    this.f251a |= 2;
                    this.f253c = c4072d.d();
                    continue;
                case 26:
                    this.f251a |= 4;
                    this.f254d = c4072d.d();
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
    private bh m570f() {
        return new bh().m572a(m575b());
    }

    /* renamed from: a */
    public final bg m571a() {
        bg b = m575b();
        if (b.m567i()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bh m572a(bg bgVar) {
        if (bgVar != bg.m553a()) {
            C4071c f;
            if (bgVar.m560b()) {
                m573a(bgVar.m562d());
            }
            if (bgVar.m563e()) {
                f = bgVar.m564f();
                if (f == null) {
                    throw new NullPointerException();
                }
                this.f251a |= 2;
                this.f253c = f;
            }
            if (bgVar.m565g()) {
                f = bgVar.m566h();
                if (f == null) {
                    throw new NullPointerException();
                }
                this.f251a |= 4;
                this.f254d = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final bh m573a(C4071c c4071c) {
        if (c4071c == null) {
            throw new NullPointerException();
        }
        this.f251a |= 1;
        this.f252b = c4071c;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m574a(C4072d c4072d, C4075g c4075g) {
        return m568c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final bg m575b() {
        int i = 1;
        bg bgVar = new bg();
        int i2 = this.f251a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        bgVar.f246c = this.f252b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        bgVar.f247d = this.f253c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        bgVar.f248e = this.f254d;
        bgVar.f245b = i;
        return bgVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m576b(C4072d c4072d, C4075g c4075g) {
        return m568c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m577c() {
        return m570f();
    }

    public final /* synthetic */ Object clone() {
        return m570f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m578d() {
        return m570f();
    }
}
