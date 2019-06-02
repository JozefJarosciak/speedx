package com.google.gson.jpush.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class ac<T> implements Iterator<T> {
    /* renamed from: b */
    ad<K, V> f14482b;
    /* renamed from: c */
    ad<K, V> f14483c;
    /* renamed from: d */
    int f14484d;
    /* renamed from: e */
    final /* synthetic */ C4038w f14485e;

    private ac(C4038w c4038w) {
        this.f14485e = c4038w;
        this.f14482b = this.f14485e.f14563e.f14490d;
        this.f14483c = null;
        this.f14484d = this.f14485e.f14562d;
    }

    /* renamed from: a */
    final ad<K, V> m16321a() {
        ad<K, V> adVar = this.f14482b;
        if (adVar == this.f14485e.f14563e) {
            throw new NoSuchElementException();
        } else if (this.f14485e.f14562d != this.f14484d) {
            throw new ConcurrentModificationException();
        } else {
            this.f14482b = adVar.f14490d;
            this.f14483c = adVar;
            return adVar;
        }
    }

    public final boolean hasNext() {
        return this.f14482b != this.f14485e.f14563e;
    }

    public final void remove() {
        if (this.f14483c == null) {
            throw new IllegalStateException();
        }
        this.f14485e.m16375a(this.f14483c, true);
        this.f14483c = null;
        this.f14484d = this.f14485e.f14562d;
    }
}
