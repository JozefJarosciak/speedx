package com.google.gson.jpush.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

/* renamed from: com.google.gson.jpush.internal.y */
final class C4040y extends AbstractSet<Entry<K, V>> {
    /* renamed from: a */
    final /* synthetic */ C4038w f14566a;

    C4040y(C4038w c4038w) {
        this.f14566a = c4038w;
    }

    public final void clear() {
        this.f14566a.clear();
    }

    public final boolean contains(Object obj) {
        return (obj instanceof Entry) && this.f14566a.m16374a((Entry) obj) != null;
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new C4041z(this);
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        ad a = this.f14566a.m16374a((Entry) obj);
        if (a == null) {
            return false;
        }
        this.f14566a.m16375a(a, true);
        return true;
    }

    public final int size() {
        return this.f14566a.f14561c;
    }
}
