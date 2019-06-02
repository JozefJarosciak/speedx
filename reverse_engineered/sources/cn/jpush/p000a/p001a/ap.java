package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.ap */
public final class ap extends C4077i<ao, ap> {
    /* renamed from: a */
    private int f139a;
    /* renamed from: b */
    private long f140b;
    /* renamed from: c */
    private long f141c;
    /* renamed from: d */
    private long f142d;
    /* renamed from: e */
    private int f143e;
    /* renamed from: f */
    private bg f144f = bg.m553a();
    /* renamed from: g */
    private int f145g;
    /* renamed from: h */
    private int f146h;

    private ap() {
    }

    /* renamed from: c */
    private ap m309c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f139a |= 1;
                    this.f140b = c4072d.b();
                    continue;
                case 16:
                    this.f139a |= 2;
                    this.f141c = c4072d.b();
                    continue;
                case 24:
                    this.f139a |= 4;
                    this.f142d = c4072d.b();
                    continue;
                case 32:
                    this.f139a |= 8;
                    this.f143e = c4072d.c();
                    continue;
                case 42:
                    Object j = bg.m558j();
                    if (((this.f139a & 16) == 16 ? 1 : null) != null) {
                        j.m572a(this.f144f);
                    }
                    c4072d.a(j, c4075g);
                    bg b = j.m575b();
                    if (b == null) {
                        throw new NullPointerException();
                    }
                    this.f144f = b;
                    this.f139a |= 16;
                    continue;
                case 48:
                    this.f139a |= 32;
                    this.f145g = c4072d.e();
                    continue;
                case 56:
                    this.f139a |= 64;
                    this.f146h = c4072d.c();
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
    private ap m311f() {
        ap apVar = new ap();
        ao b = m316b();
        if (b != ao.m284a()) {
            if (b.m293b()) {
                apVar.m314a(b.m295d());
            }
            if (b.m296e()) {
                apVar.m317b(b.m297f());
            }
            if (b.m298g()) {
                apVar.m319c(b.m299h());
            }
            if (b.m300i()) {
                apVar.m313a(b.m301j());
            }
            if (b.m302k()) {
                bg l = b.m303l();
                if ((apVar.f139a & 16) != 16 || apVar.f144f == bg.m553a()) {
                    apVar.f144f = l;
                } else {
                    apVar.f144f = bg.m554a(apVar.f144f).m572a(l).m575b();
                }
                apVar.f139a |= 16;
            }
            if (b.m304m()) {
                int n = b.m305n();
                apVar.f139a |= 32;
                apVar.f145g = n;
            }
            if (b.m306o()) {
                int p = b.m307p();
                apVar.f139a |= 64;
                apVar.f146h = p;
            }
        }
        return apVar;
    }

    /* renamed from: a */
    public final ao m312a() {
        ao b = m316b();
        if (b.m308q()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final ap m313a(int i) {
        this.f139a |= 8;
        this.f143e = i;
        return this;
    }

    /* renamed from: a */
    public final ap m314a(long j) {
        this.f139a |= 1;
        this.f140b = j;
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m315a(C4072d c4072d, C4075g c4075g) {
        return m309c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final ao m316b() {
        int i = 1;
        ao aoVar = new ao();
        int i2 = this.f139a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        aoVar.f130c = this.f140b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        aoVar.f131d = this.f141c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        aoVar.f132e = this.f142d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        aoVar.f133f = this.f143e;
        if ((i2 & 16) == 16) {
            i |= 16;
        }
        aoVar.f134g = this.f144f;
        if ((i2 & 32) == 32) {
            i |= 32;
        }
        aoVar.f135h = this.f145g;
        if ((i2 & 64) == 64) {
            i |= 64;
        }
        aoVar.f136i = this.f146h;
        aoVar.f129b = i;
        return aoVar;
    }

    /* renamed from: b */
    public final ap m317b(long j) {
        this.f139a |= 2;
        this.f141c = j;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m318b(C4072d c4072d, C4075g c4075g) {
        return m309c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final ap m319c(long j) {
        this.f139a |= 4;
        this.f142d = j;
        return this;
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m320c() {
        return m311f();
    }

    public final /* synthetic */ Object clone() {
        return m311f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m321d() {
        return m311f();
    }
}
