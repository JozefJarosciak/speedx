package com.google.gson.jpush;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.gson.jpush.t */
public final class C4052t extends C3975w implements Iterable<C3975w> {
    /* renamed from: a */
    private final List<C3975w> f14600a = new ArrayList();

    /* renamed from: a */
    public final int m16398a() {
        return this.f14600a.size();
    }

    /* renamed from: a */
    public final C3975w m16399a(int i) {
        return (C3975w) this.f14600a.get(2);
    }

    /* renamed from: a */
    public final void m16400a(C3975w c3975w) {
        Object obj;
        if (c3975w == null) {
            obj = C1483y.f7007a;
        }
        this.f14600a.add(obj);
    }

    /* renamed from: b */
    public final Number mo5666b() {
        if (this.f14600a.size() == 1) {
            return ((C3975w) this.f14600a.get(0)).mo5666b();
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    public final String mo5667c() {
        if (this.f14600a.size() == 1) {
            return ((C3975w) this.f14600a.get(0)).mo5667c();
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public final double mo5668d() {
        if (this.f14600a.size() == 1) {
            return ((C3975w) this.f14600a.get(0)).mo5668d();
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    public final long mo5669e() {
        if (this.f14600a.size() == 1) {
            return ((C3975w) this.f14600a.get(0)).mo5669e();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof C4052t) && ((C4052t) obj).f14600a.equals(this.f14600a));
    }

    /* renamed from: f */
    public final int mo5670f() {
        if (this.f14600a.size() == 1) {
            return ((C3975w) this.f14600a.get(0)).mo5670f();
        }
        throw new IllegalStateException();
    }

    /* renamed from: g */
    public final boolean mo5671g() {
        if (this.f14600a.size() == 1) {
            return ((C3975w) this.f14600a.get(0)).mo5671g();
        }
        throw new IllegalStateException();
    }

    public final int hashCode() {
        return this.f14600a.hashCode();
    }

    public final Iterator<C3975w> iterator() {
        return this.f14600a.iterator();
    }
}
