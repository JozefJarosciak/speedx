package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.bb */
public final class bb extends C4077i<ba, bb> {
    /* renamed from: a */
    private int f208a;
    /* renamed from: b */
    private long f209b;
    /* renamed from: c */
    private int f210c;
    /* renamed from: d */
    private long f211d;
    /* renamed from: e */
    private long f212e;
    /* renamed from: f */
    private List<Long> f213f = Collections.emptyList();
    /* renamed from: g */
    private C4071c f214g = C4071c.f14609a;
    /* renamed from: h */
    private int f215h;
    /* renamed from: i */
    private int f216i;
    /* renamed from: j */
    private int f217j;

    private bb() {
    }

    /* renamed from: c */
    private bb m479c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f208a |= 1;
                    this.f209b = c4072d.b();
                    continue;
                case 16:
                    this.f208a |= 2;
                    this.f210c = c4072d.c();
                    continue;
                case 24:
                    this.f208a |= 4;
                    this.f211d = c4072d.b();
                    continue;
                case 32:
                    this.f208a |= 8;
                    this.f212e = c4072d.b();
                    continue;
                case 40:
                    m482g();
                    this.f213f.add(Long.valueOf(c4072d.b()));
                    continue;
                case 42:
                    a = c4072d.c(c4072d.f());
                    while (c4072d.g() > 0) {
                        long b = c4072d.b();
                        m482g();
                        this.f213f.add(Long.valueOf(b));
                    }
                    c4072d.d(a);
                    continue;
                case 50:
                    this.f208a |= 32;
                    this.f214g = c4072d.d();
                    continue;
                case 56:
                    this.f208a |= 64;
                    this.f215h = c4072d.e();
                    continue;
                case 64:
                    this.f208a |= 128;
                    this.f216i = c4072d.c();
                    continue;
                case 72:
                    this.f208a |= 256;
                    this.f217j = c4072d.c();
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
    private bb m481f() {
        return new bb().m486a(m488b());
    }

    /* renamed from: g */
    private void m482g() {
        if ((this.f208a & 16) != 16) {
            this.f213f = new ArrayList(this.f213f);
            this.f208a |= 16;
        }
    }

    /* renamed from: a */
    public final ba m483a() {
        ba b = m488b();
        if (b.m478s()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final bb m484a(int i) {
        this.f208a |= 2;
        this.f210c = i;
        return this;
    }

    /* renamed from: a */
    public final bb m485a(long j) {
        this.f208a |= 1;
        this.f209b = j;
        return this;
    }

    /* renamed from: a */
    public final bb m486a(ba baVar) {
        if (baVar != ba.m448a()) {
            int n;
            if (baVar.m461b()) {
                m485a(baVar.m463d());
            }
            if (baVar.m464e()) {
                m484a(baVar.m465f());
            }
            if (baVar.m466g()) {
                m489b(baVar.m467h());
            }
            if (baVar.m468i()) {
                m491c(baVar.m469j());
            }
            if (!baVar.f201g.isEmpty()) {
                if (this.f213f.isEmpty()) {
                    this.f213f = baVar.f201g;
                    this.f208a &= -17;
                } else {
                    m482g();
                    this.f213f.addAll(baVar.f201g);
                }
            }
            if (baVar.m470k()) {
                C4071c l = baVar.m471l();
                if (l == null) {
                    throw new NullPointerException();
                }
                this.f208a |= 32;
                this.f214g = l;
            }
            if (baVar.m472m()) {
                n = baVar.m473n();
                this.f208a |= 64;
                this.f215h = n;
            }
            if (baVar.m474o()) {
                n = baVar.m475p();
                this.f208a |= 128;
                this.f216i = n;
            }
            if (baVar.m476q()) {
                n = baVar.m477r();
                this.f208a |= 256;
                this.f217j = n;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m487a(C4072d c4072d, C4075g c4075g) {
        return m479c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final ba m488b() {
        int i = 1;
        ba baVar = new ba();
        int i2 = this.f208a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        baVar.f197c = this.f209b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        baVar.f198d = this.f210c;
        if ((i2 & 4) == 4) {
            i |= 4;
        }
        baVar.f199e = this.f211d;
        if ((i2 & 8) == 8) {
            i |= 8;
        }
        baVar.f200f = this.f212e;
        if ((this.f208a & 16) == 16) {
            this.f213f = Collections.unmodifiableList(this.f213f);
            this.f208a &= -17;
        }
        baVar.f201g = this.f213f;
        if ((i2 & 32) == 32) {
            i |= 16;
        }
        baVar.f202h = this.f214g;
        if ((i2 & 64) == 64) {
            i |= 32;
        }
        baVar.f203i = this.f215h;
        if ((i2 & 128) == 128) {
            i |= 64;
        }
        baVar.f204j = this.f216i;
        if ((i2 & 256) == 256) {
            i |= 128;
        }
        baVar.f205k = this.f217j;
        baVar.f196b = i;
        return baVar;
    }

    /* renamed from: b */
    public final bb m489b(long j) {
        this.f208a |= 4;
        this.f211d = j;
        return this;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m490b(C4072d c4072d, C4075g c4075g) {
        return m479c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final bb m491c(long j) {
        this.f208a |= 8;
        this.f212e = j;
        return this;
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m492c() {
        return m481f();
    }

    public final /* synthetic */ Object clone() {
        return m481f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m493d() {
        return m481f();
    }
}
