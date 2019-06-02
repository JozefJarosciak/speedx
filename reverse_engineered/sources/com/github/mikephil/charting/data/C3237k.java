package com.github.mikephil.charting.data;

import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3227b;
import com.github.mikephil.charting.p181d.C3213d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CombinedData */
/* renamed from: com.github.mikephil.charting.data.k */
public class C3237k extends C3225c<C3227b<? extends Entry>> {
    /* renamed from: j */
    private C3238l f14058j;
    /* renamed from: k */
    private C3226a f14059k;
    /* renamed from: l */
    private C3243q f14060l;
    /* renamed from: m */
    private C3232h f14061m;
    /* renamed from: n */
    private C3231g f14062n;

    /* renamed from: b */
    public /* synthetic */ boolean mo3986b(C3220e c3220e) {
        return m15665a((C3227b) c3220e);
    }

    /* renamed from: a */
    public void m15664a(C3238l c3238l) {
        this.f14058j = c3238l;
        mo3985b();
    }

    /* renamed from: a */
    public void m15663a(C3232h c3232h) {
        this.f14061m = c3232h;
        mo3985b();
    }

    /* renamed from: c */
    public void mo3987c() {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.clear();
        this.a = -3.4028235E38f;
        this.b = Float.MAX_VALUE;
        this.c = -3.4028235E38f;
        this.d = Float.MAX_VALUE;
        this.e = -3.4028235E38f;
        this.f = Float.MAX_VALUE;
        this.g = -3.4028235E38f;
        this.h = Float.MAX_VALUE;
        for (C3224j c3224j : m15673q()) {
            c3224j.mo3987c();
            this.i.addAll(c3224j.m15595i());
            if (c3224j.m15592f() > this.a) {
                this.a = c3224j.m15592f();
            }
            if (c3224j.m15591e() < this.b) {
                this.b = c3224j.m15591e();
            }
            if (c3224j.m15594h() > this.c) {
                this.c = c3224j.m15594h();
            }
            if (c3224j.m15593g() < this.d) {
                this.d = c3224j.m15593g();
            }
            if (c3224j.f14030e > this.e) {
                this.e = c3224j.f14030e;
            }
            if (c3224j.f14031f < this.f) {
                this.f = c3224j.f14031f;
            }
            if (c3224j.f14032g > this.g) {
                this.g = c3224j.f14032g;
            }
            if (c3224j.f14033h < this.h) {
                this.h = c3224j.f14033h;
            }
        }
    }

    /* renamed from: a */
    public C3231g m15662a() {
        return this.f14062n;
    }

    /* renamed from: m */
    public C3238l m15669m() {
        return this.f14058j;
    }

    /* renamed from: n */
    public C3226a m15670n() {
        return this.f14059k;
    }

    /* renamed from: o */
    public C3243q m15671o() {
        return this.f14060l;
    }

    /* renamed from: p */
    public C3232h m15672p() {
        return this.f14061m;
    }

    /* renamed from: q */
    public List<C3225c> m15673q() {
        List<C3225c> arrayList = new ArrayList();
        if (this.f14058j != null) {
            arrayList.add(this.f14058j);
        }
        if (this.f14059k != null) {
            arrayList.add(this.f14059k);
        }
        if (this.f14060l != null) {
            arrayList.add(this.f14060l);
        }
        if (this.f14061m != null) {
            arrayList.add(this.f14061m);
        }
        if (this.f14062n != null) {
            arrayList.add(this.f14062n);
        }
        return arrayList;
    }

    /* renamed from: b */
    public void mo3985b() {
        if (this.f14058j != null) {
            this.f14058j.mo3985b();
        }
        if (this.f14059k != null) {
            this.f14059k.mo3985b();
        }
        if (this.f14061m != null) {
            this.f14061m.mo3985b();
        }
        if (this.f14060l != null) {
            this.f14060l.mo3985b();
        }
        if (this.f14062n != null) {
            this.f14062n.mo3985b();
        }
        mo3987c();
    }

    /* renamed from: a */
    public Entry mo3984a(C3213d c3213d) {
        List q = m15673q();
        if (c3213d.m15430e() >= q.size()) {
            return null;
        }
        C3224j c3224j = (C3224j) q.get(c3213d.m15430e());
        if (c3213d.m15431f() >= c3224j.m15590d()) {
            return null;
        }
        for (Entry entry : c3224j.mo3993a(c3213d.m15431f()).mo3947d(c3213d.m15423a())) {
            if (entry.mo3912b() == c3213d.m15427b()) {
                return entry;
            }
            if (Float.isNaN(c3213d.m15427b())) {
                return entry;
            }
        }
        return null;
    }

    /* renamed from: a */
    public boolean m15665a(C3227b<? extends Entry> c3227b) {
        boolean z = false;
        for (C3224j b : m15673q()) {
            z = b.mo3986b((C3220e) c3227b);
            if (z) {
                break;
            }
        }
        return z;
    }
}
