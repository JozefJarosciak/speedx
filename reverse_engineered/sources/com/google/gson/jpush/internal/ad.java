package com.google.gson.jpush.internal;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.Map.Entry;

final class ad<K, V> implements Entry<K, V> {
    /* renamed from: a */
    ad<K, V> f14487a;
    /* renamed from: b */
    ad<K, V> f14488b;
    /* renamed from: c */
    ad<K, V> f14489c;
    /* renamed from: d */
    ad<K, V> f14490d;
    /* renamed from: e */
    ad<K, V> f14491e;
    /* renamed from: f */
    final K f14492f;
    /* renamed from: g */
    V f14493g;
    /* renamed from: h */
    int f14494h;

    ad() {
        this.f14492f = null;
        this.f14491e = this;
        this.f14490d = this;
    }

    ad(ad<K, V> adVar, K k, ad<K, V> adVar2, ad<K, V> adVar3) {
        this.f14487a = adVar;
        this.f14492f = k;
        this.f14494h = 1;
        this.f14490d = adVar2;
        this.f14491e = adVar3;
        adVar3.f14490d = this;
        adVar2.f14491e = this;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (this.f14492f == null) {
            if (entry.getKey() != null) {
                return false;
            }
        } else if (!this.f14492f.equals(entry.getKey())) {
            return false;
        }
        if (this.f14493g == null) {
            if (entry.getValue() != null) {
                return false;
            }
        } else if (!this.f14493g.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    public final K getKey() {
        return this.f14492f;
    }

    public final V getValue() {
        return this.f14493g;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.f14492f == null ? 0 : this.f14492f.hashCode();
        if (this.f14493g != null) {
            i = this.f14493g.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        V v2 = this.f14493g;
        this.f14493g = v;
        return v2;
    }

    public final String toString() {
        return this.f14492f + SimpleComparison.EQUAL_TO_OPERATION + this.f14493g;
    }
}
