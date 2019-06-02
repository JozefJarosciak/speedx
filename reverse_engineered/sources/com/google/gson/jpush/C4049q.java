package com.google.gson.jpush;

import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.q */
final class C4049q<T> extends al<T> {
    /* renamed from: a */
    private al<T> f14584a;

    C4049q() {
    }

    /* renamed from: a */
    public final T mo5672a(C3976a c3976a) {
        if (this.f14584a != null) {
            return this.f14584a.mo5672a(c3976a);
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    public final void m16393a(al<T> alVar) {
        if (this.f14584a != null) {
            throw new AssertionError();
        }
        this.f14584a = alVar;
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, T t) {
        if (this.f14584a == null) {
            throw new IllegalStateException();
        }
        this.f14584a.mo5673a(c3980d, t);
    }
}
