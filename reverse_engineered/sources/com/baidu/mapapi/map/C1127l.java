package com.baidu.mapapi.map;

import android.graphics.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.baidu.mapapi.map.l */
class C1127l<T extends C1112a> {
    /* renamed from: a */
    private final C1121f f3256a;
    /* renamed from: b */
    private final int f3257b;
    /* renamed from: c */
    private List<T> f3258c;
    /* renamed from: d */
    private List<C1127l<T>> f3259d;

    /* renamed from: com.baidu.mapapi.map.l$a */
    static abstract class C1112a {
        C1112a() {
        }

        /* renamed from: a */
        abstract Point mo2622a();
    }

    private C1127l(double d, double d2, double d3, double d4, int i) {
        this(new C1121f(d, d2, d3, d4), i);
    }

    public C1127l(C1121f c1121f) {
        this(c1121f, 0);
    }

    private C1127l(C1121f c1121f, int i) {
        this.f3259d = null;
        this.f3256a = c1121f;
        this.f3257b = i;
    }

    /* renamed from: a */
    private void m4310a() {
        this.f3259d = new ArrayList(4);
        this.f3259d.add(new C1127l(this.f3256a.f3243a, this.f3256a.f3247e, this.f3256a.f3244b, this.f3256a.f3248f, this.f3257b + 1));
        this.f3259d.add(new C1127l(this.f3256a.f3247e, this.f3256a.f3245c, this.f3256a.f3244b, this.f3256a.f3248f, this.f3257b + 1));
        this.f3259d.add(new C1127l(this.f3256a.f3243a, this.f3256a.f3247e, this.f3256a.f3248f, this.f3256a.f3246d, this.f3257b + 1));
        this.f3259d.add(new C1127l(this.f3256a.f3247e, this.f3256a.f3245c, this.f3256a.f3248f, this.f3256a.f3246d, this.f3257b + 1));
        List<C1112a> list = this.f3258c;
        this.f3258c = null;
        for (C1112a c1112a : list) {
            m4311a((double) c1112a.mo2622a().x, (double) c1112a.mo2622a().y, c1112a);
        }
    }

    /* renamed from: a */
    private void m4311a(double d, double d2, T t) {
        if (this.f3259d == null) {
            if (this.f3258c == null) {
                this.f3258c = new ArrayList();
            }
            this.f3258c.add(t);
            if (this.f3258c.size() > 40 && this.f3257b < 40) {
                m4310a();
            }
        } else if (d2 < this.f3256a.f3248f) {
            if (d < this.f3256a.f3247e) {
                ((C1127l) this.f3259d.get(0)).m4311a(d, d2, t);
            } else {
                ((C1127l) this.f3259d.get(1)).m4311a(d, d2, t);
            }
        } else if (d < this.f3256a.f3247e) {
            ((C1127l) this.f3259d.get(2)).m4311a(d, d2, t);
        } else {
            ((C1127l) this.f3259d.get(3)).m4311a(d, d2, t);
        }
    }

    /* renamed from: a */
    private void m4312a(C1121f c1121f, Collection<T> collection) {
        if (!this.f3256a.m4286a(c1121f)) {
            return;
        }
        if (this.f3259d != null) {
            for (C1127l a : this.f3259d) {
                a.m4312a(c1121f, collection);
            }
        } else if (this.f3258c == null) {
        } else {
            if (c1121f.m4287b(this.f3256a)) {
                collection.addAll(this.f3258c);
                return;
            }
            for (C1112a c1112a : this.f3258c) {
                if (c1121f.m4285a(c1112a.mo2622a())) {
                    collection.add(c1112a);
                }
            }
        }
    }

    /* renamed from: a */
    public Collection<T> m4313a(C1121f c1121f) {
        Collection<T> arrayList = new ArrayList();
        m4312a(c1121f, arrayList);
        return arrayList;
    }

    /* renamed from: a */
    public void m4314a(T t) {
        Point a = t.mo2622a();
        if (this.f3256a.m4283a((double) a.x, (double) a.y)) {
            m4311a((double) a.x, (double) a.y, t);
        }
    }
}
