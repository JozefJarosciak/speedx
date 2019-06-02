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

/* renamed from: cn.jpush.a.a.r */
public final class C0383r extends C4077i<C0382q, C0383r> {
    /* renamed from: a */
    private int f367a;
    /* renamed from: b */
    private long f368b;
    /* renamed from: c */
    private int f369c;
    /* renamed from: d */
    private List<Long> f370d = Collections.emptyList();

    private C0383r() {
    }

    /* renamed from: c */
    private C0383r m881c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f367a |= 1;
                    this.f368b = c4072d.b();
                    continue;
                case 16:
                    this.f367a |= 2;
                    this.f369c = c4072d.c();
                    continue;
                case 24:
                    m884g();
                    this.f370d.add(Long.valueOf(c4072d.b()));
                    continue;
                case 26:
                    a = c4072d.c(c4072d.f());
                    while (c4072d.g() > 0) {
                        long b = c4072d.b();
                        m884g();
                        this.f370d.add(Long.valueOf(b));
                    }
                    c4072d.d(a);
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
    private C0383r m883f() {
        return new C0383r().m888a(m891b());
    }

    /* renamed from: g */
    private void m884g() {
        if ((this.f367a & 4) != 4) {
            this.f370d = new ArrayList(this.f370d);
            this.f367a |= 4;
        }
    }

    /* renamed from: a */
    public final C0382q m885a() {
        C0382q b = m891b();
        if (b.m880g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final C0383r m886a(int i) {
        this.f367a |= 2;
        this.f369c = i;
        return this;
    }

    /* renamed from: a */
    public final C0383r m887a(long j) {
        this.f367a |= 1;
        this.f368b = j;
        return this;
    }

    /* renamed from: a */
    public final C0383r m888a(C0382q c0382q) {
        if (c0382q != C0382q.m868a()) {
            if (c0382q.m875b()) {
                m887a(c0382q.m877d());
            }
            if (c0382q.m878e()) {
                m886a(c0382q.m879f());
            }
            if (!c0382q.f364e.isEmpty()) {
                if (this.f370d.isEmpty()) {
                    this.f370d = c0382q.f364e;
                    this.f367a &= -5;
                } else {
                    m884g();
                    this.f370d.addAll(c0382q.f364e);
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0383r m889a(Iterable<? extends Long> iterable) {
        m884g();
        C4077i.a(iterable, this.f370d);
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m890a(C4072d c4072d, C4075g c4075g) {
        return m881c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final C0382q m891b() {
        int i = 1;
        C0382q c0382q = new C0382q();
        int i2 = this.f367a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c0382q.f362c = this.f368b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c0382q.f363d = this.f369c;
        if ((this.f367a & 4) == 4) {
            this.f370d = Collections.unmodifiableList(this.f370d);
            this.f367a &= -5;
        }
        c0382q.f364e = this.f370d;
        c0382q.f361b = i;
        return c0382q;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m892b(C4072d c4072d, C4075g c4075g) {
        return m881c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m893c() {
        return m883f();
    }

    public final /* synthetic */ Object clone() {
        return m883f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m894d() {
        return m883f();
    }
}
