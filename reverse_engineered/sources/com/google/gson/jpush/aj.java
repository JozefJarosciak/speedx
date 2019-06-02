package com.google.gson.jpush;

import com.google.gson.jpush.internal.ag;
import com.google.gson.jpush.p184a.C3972a;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;

final class aj<T> extends al<T> {
    /* renamed from: a */
    private final ae<T> f14290a;
    /* renamed from: b */
    private final C3973v<T> f14291b;
    /* renamed from: c */
    private final C4042k f14292c;
    /* renamed from: d */
    private final C3972a<T> f14293d;
    /* renamed from: e */
    private final am f14294e;
    /* renamed from: f */
    private al<T> f14295f;

    private aj(ae<T> aeVar, C3973v<T> c3973v, C4042k c4042k, C3972a<T> c3972a, am amVar) {
        this.f14290a = aeVar;
        this.f14291b = c3973v;
        this.f14292c = c4042k;
        this.f14293d = c3972a;
        this.f14294e = amVar;
    }

    /* renamed from: a */
    private al<T> m16091a() {
        al<T> alVar = this.f14295f;
        if (alVar != null) {
            return alVar;
        }
        alVar = this.f14292c.m16381a(this.f14294e, this.f14293d);
        this.f14295f = alVar;
        return alVar;
    }

    /* renamed from: a */
    public static am m16092a(C3972a<?> c3972a, Object obj) {
        return new ak(obj, c3972a);
    }

    /* renamed from: a */
    public final T mo5672a(C3976a c3976a) {
        if (this.f14291b == null) {
            return m16091a().mo5672a(c3976a);
        }
        C3975w a = ag.m16326a(c3976a);
        return a instanceof C1483y ? null : this.f14291b.mo5665a(a, this.f14293d.m16058b(), this.f14292c.f14569a);
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, T t) {
        if (this.f14290a == null) {
            m16091a().mo5673a(c3980d, t);
        } else if (t == null) {
            c3980d.mo5705f();
        } else {
            ag.m16328a(this.f14290a.mo5664a(t, this.f14293d.m16058b(), this.f14292c.f14570b), c3980d);
        }
    }
}
