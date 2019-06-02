package com.google.gson.jpush.internal;

import java.util.AbstractSet;
import java.util.Iterator;

final class aa extends AbstractSet<K> {
    /* renamed from: a */
    final /* synthetic */ C4038w f14481a;

    aa(C4038w c4038w) {
        this.f14481a = c4038w;
    }

    public final void clear() {
        this.f14481a.clear();
    }

    public final boolean contains(Object obj) {
        return this.f14481a.containsKey(obj);
    }

    public final Iterator<K> iterator() {
        return new ab(this);
    }

    public final boolean remove(Object obj) {
        return this.f14481a.m16373a(obj) != null;
    }

    public final int size() {
        return this.f14481a.f14561c;
    }
}
