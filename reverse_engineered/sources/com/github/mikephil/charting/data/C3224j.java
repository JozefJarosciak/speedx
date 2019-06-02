package com.github.mikephil.charting.data;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p181d.C3213d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ChartData */
/* renamed from: com.github.mikephil.charting.data.j */
public abstract class C3224j<T extends C3220e<? extends Entry>> {
    /* renamed from: a */
    protected float f14026a;
    /* renamed from: b */
    protected float f14027b;
    /* renamed from: c */
    protected float f14028c;
    /* renamed from: d */
    protected float f14029d;
    /* renamed from: e */
    protected float f14030e;
    /* renamed from: f */
    protected float f14031f;
    /* renamed from: g */
    protected float f14032g;
    /* renamed from: h */
    protected float f14033h;
    /* renamed from: i */
    protected List<T> f14034i;

    public C3224j() {
        this.f14026a = -3.4028235E38f;
        this.f14027b = Float.MAX_VALUE;
        this.f14028c = -3.4028235E38f;
        this.f14029d = Float.MAX_VALUE;
        this.f14030e = -3.4028235E38f;
        this.f14031f = Float.MAX_VALUE;
        this.f14032g = -3.4028235E38f;
        this.f14033h = Float.MAX_VALUE;
        this.f14034i = new ArrayList();
    }

    public C3224j(T... tArr) {
        this.f14026a = -3.4028235E38f;
        this.f14027b = Float.MAX_VALUE;
        this.f14028c = -3.4028235E38f;
        this.f14029d = Float.MAX_VALUE;
        this.f14030e = -3.4028235E38f;
        this.f14031f = Float.MAX_VALUE;
        this.f14032g = -3.4028235E38f;
        this.f14033h = Float.MAX_VALUE;
        this.f14034i = m15573a((C3220e[]) tArr);
        mo3985b();
    }

    /* renamed from: a */
    private List<T> m15573a(T[] tArr) {
        List<T> arrayList = new ArrayList();
        for (Object add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public C3224j(List<T> list) {
        this.f14026a = -3.4028235E38f;
        this.f14027b = Float.MAX_VALUE;
        this.f14028c = -3.4028235E38f;
        this.f14029d = Float.MAX_VALUE;
        this.f14030e = -3.4028235E38f;
        this.f14031f = Float.MAX_VALUE;
        this.f14032g = -3.4028235E38f;
        this.f14033h = Float.MAX_VALUE;
        this.f14034i = list;
        mo3985b();
    }

    /* renamed from: b */
    public void mo3985b() {
        mo3987c();
    }

    /* renamed from: a */
    public void m15578a(float f, float f2) {
        for (C3220e a : this.f14034i) {
            a.mo3944a(f, f2);
        }
        mo3987c();
    }

    /* renamed from: c */
    protected void mo3987c() {
        if (this.f14034i != null) {
            this.f14026a = -3.4028235E38f;
            this.f14027b = Float.MAX_VALUE;
            this.f14028c = -3.4028235E38f;
            this.f14029d = Float.MAX_VALUE;
            for (C3220e c : this.f14034i) {
                m15589c(c);
            }
            this.f14030e = -3.4028235E38f;
            this.f14031f = Float.MAX_VALUE;
            this.f14032g = -3.4028235E38f;
            this.f14033h = Float.MAX_VALUE;
            C3220e c2 = m15577a(this.f14034i);
            if (c2 != null) {
                this.f14030e = c2.mo3940I();
                this.f14031f = c2.mo3939H();
                for (C3220e c22 : this.f14034i) {
                    if (c22.mo3913A() == AxisDependency.LEFT) {
                        if (c22.mo3939H() < this.f14031f) {
                            this.f14031f = c22.mo3939H();
                        }
                        if (c22.mo3940I() > this.f14030e) {
                            this.f14030e = c22.mo3940I();
                        }
                    }
                }
            }
            c22 = m15582b(this.f14034i);
            if (c22 != null) {
                this.f14032g = c22.mo3940I();
                this.f14033h = c22.mo3939H();
                for (C3220e c222 : this.f14034i) {
                    if (c222.mo3913A() == AxisDependency.RIGHT) {
                        if (c222.mo3939H() < this.f14033h) {
                            this.f14033h = c222.mo3939H();
                        }
                        if (c222.mo3940I() > this.f14032g) {
                            this.f14032g = c222.mo3940I();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: d */
    public int m15590d() {
        if (this.f14034i == null) {
            return 0;
        }
        return this.f14034i.size();
    }

    /* renamed from: e */
    public float m15591e() {
        return this.f14027b;
    }

    /* renamed from: a */
    public float m15574a(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            if (this.f14031f == Float.MAX_VALUE) {
                return this.f14033h;
            }
            return this.f14031f;
        } else if (this.f14033h == Float.MAX_VALUE) {
            return this.f14031f;
        } else {
            return this.f14033h;
        }
    }

    /* renamed from: f */
    public float m15592f() {
        return this.f14026a;
    }

    /* renamed from: b */
    public float m15581b(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            if (this.f14030e == -3.4028235E38f) {
                return this.f14032g;
            }
            return this.f14030e;
        } else if (this.f14032g == -3.4028235E38f) {
            return this.f14030e;
        } else {
            return this.f14032g;
        }
    }

    /* renamed from: g */
    public float m15593g() {
        return this.f14029d;
    }

    /* renamed from: h */
    public float m15594h() {
        return this.f14028c;
    }

    /* renamed from: i */
    public List<T> m15595i() {
        return this.f14034i;
    }

    /* renamed from: a */
    public Entry mo3984a(C3213d c3213d) {
        if (c3213d.m15431f() >= this.f14034i.size()) {
            return null;
        }
        return ((C3220e) this.f14034i.get(c3213d.m15431f())).mo3945b(c3213d.m15423a(), c3213d.m15427b());
    }

    /* renamed from: a */
    public T mo3993a(int i) {
        if (this.f14034i == null || i < 0 || i >= this.f14034i.size()) {
            return null;
        }
        return (C3220e) this.f14034i.get(i);
    }

    /* renamed from: a */
    public void m15579a(T t) {
        if (t != null) {
            m15589c(t);
            this.f14034i.add(t);
        }
    }

    /* renamed from: b */
    public boolean mo3986b(T t) {
        if (t == null) {
            return false;
        }
        boolean remove = this.f14034i.remove(t);
        if (!remove) {
            return remove;
        }
        mo3987c();
        return remove;
    }

    /* renamed from: c */
    protected void m15589c(T t) {
        if (this.f14026a < t.mo3940I()) {
            this.f14026a = t.mo3940I();
        }
        if (this.f14027b > t.mo3939H()) {
            this.f14027b = t.mo3939H();
        }
        if (this.f14028c < t.mo3942K()) {
            this.f14028c = t.mo3942K();
        }
        if (this.f14029d > t.mo3941J()) {
            this.f14029d = t.mo3941J();
        }
        if (t.mo3913A() == AxisDependency.LEFT) {
            if (this.f14030e < t.mo3940I()) {
                this.f14030e = t.mo3940I();
            }
            if (this.f14031f > t.mo3939H()) {
                this.f14031f = t.mo3939H();
                return;
            }
            return;
        }
        if (this.f14032g < t.mo3940I()) {
            this.f14032g = t.mo3940I();
        }
        if (this.f14033h > t.mo3939H()) {
            this.f14033h = t.mo3939H();
        }
    }

    /* renamed from: a */
    protected T m15577a(List<T> list) {
        for (T t : list) {
            if (t.mo3913A() == AxisDependency.LEFT) {
                return t;
            }
        }
        return null;
    }

    /* renamed from: b */
    public T m15582b(List<T> list) {
        for (T t : list) {
            if (t.mo3913A() == AxisDependency.RIGHT) {
                return t;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void m15585b(int i) {
        for (C3220e d : this.f14034i) {
            d.mo3919d(i);
        }
    }

    /* renamed from: b */
    public void m15584b(float f) {
        for (C3220e a : this.f14034i) {
            a.mo3914a(f);
        }
    }

    /* renamed from: a */
    public void m15580a(boolean z) {
        for (C3220e b : this.f14034i) {
            b.mo3918b(z);
        }
    }

    /* renamed from: b */
    public void m15586b(boolean z) {
        for (C3220e a : this.f14034i) {
            a.mo3916a(z);
        }
    }

    /* renamed from: j */
    public void m15596j() {
        if (this.f14034i != null) {
            this.f14034i.clear();
        }
        mo3985b();
    }

    /* renamed from: k */
    public int m15597k() {
        int i = 0;
        for (C3220e F : this.f14034i) {
            i = F.mo3938F() + i;
        }
        return i;
    }

    /* renamed from: l */
    public T m15598l() {
        if (this.f14034i == null || this.f14034i.isEmpty()) {
            return null;
        }
        T t = (C3220e) this.f14034i.get(0);
        T t2 = t;
        for (T t3 : this.f14034i) {
            if (t3.mo3938F() <= t2.mo3938F()) {
                t3 = t2;
            }
            t2 = t3;
        }
        return t2;
    }
}
