package com.qiniu.android.dns.p190a;

import java.util.HashMap;
import java.util.LinkedList;

/* compiled from: LruCache */
/* renamed from: com.qiniu.android.dns.a.b */
public final class C4344b<K, V> {
    /* renamed from: a */
    private LinkedList<K> f15111a;
    /* renamed from: b */
    private HashMap<K, V> f15112b;
    /* renamed from: c */
    private int f15113c;

    public C4344b() {
        this(256);
    }

    public C4344b(int i) {
        this.f15111a = new LinkedList();
        this.f15112b = new HashMap();
        this.f15113c = i;
    }

    /* renamed from: a */
    public C4344b m17137a(K k, V v) {
        if (this.f15111a.size() == this.f15113c) {
            this.f15112b.remove(this.f15111a.pollLast());
        }
        this.f15112b.put(k, v);
        this.f15111a.push(k);
        return this;
    }

    /* renamed from: a */
    public V m17138a(K k) {
        V v = this.f15112b.get(k);
        this.f15111a.remove(k);
        this.f15111a.push(k);
        return v;
    }

    /* renamed from: a */
    public void m17139a() {
        this.f15111a.clear();
        this.f15112b.clear();
    }
}
