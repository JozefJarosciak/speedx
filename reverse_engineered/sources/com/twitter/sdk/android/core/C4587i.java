package com.twitter.sdk.android.core;

import io.fabric.sdk.android.services.p094c.C4574e;
import io.fabric.sdk.android.services.p094c.C4862b;
import io.fabric.sdk.android.services.p094c.C4863d;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PersistedSessionManager */
/* renamed from: com.twitter.sdk.android.core.i */
public class C4587i<T extends C1469k> implements C4586l<T> {
    /* renamed from: a */
    private final C4862b f16215a;
    /* renamed from: b */
    private final C4574e<T> f16216b;
    /* renamed from: c */
    private final ConcurrentHashMap<Long, T> f16217c;
    /* renamed from: d */
    private final ConcurrentHashMap<Long, C4863d<T>> f16218d;
    /* renamed from: e */
    private final C4863d<T> f16219e;
    /* renamed from: f */
    private final AtomicReference<T> f16220f;
    /* renamed from: g */
    private final String f16221g;
    /* renamed from: h */
    private volatile boolean f16222h;

    public C4587i(C4862b c4862b, C4574e<T> c4574e, String str, String str2) {
        this(c4862b, c4574e, new ConcurrentHashMap(1), new ConcurrentHashMap(1), new C4863d(c4862b, c4574e, str), str2);
    }

    C4587i(C4862b c4862b, C4574e<T> c4574e, ConcurrentHashMap<Long, T> concurrentHashMap, ConcurrentHashMap<Long, C4863d<T>> concurrentHashMap2, C4863d<T> c4863d, String str) {
        this.f16222h = true;
        this.f16215a = c4862b;
        this.f16216b = c4574e;
        this.f16217c = concurrentHashMap;
        this.f16218d = concurrentHashMap2;
        this.f16219e = c4863d;
        this.f16220f = new AtomicReference();
        this.f16221g = str;
    }

    /* renamed from: a */
    void m18168a() {
        if (this.f16222h) {
            m18164d();
        }
    }

    /* renamed from: d */
    private synchronized void m18164d() {
        if (this.f16222h) {
            m18166f();
            m18165e();
            this.f16222h = false;
        }
    }

    /* renamed from: e */
    private void m18165e() {
        for (Entry entry : this.f16215a.m19107a().getAll().entrySet()) {
            if (m18171a((String) entry.getKey())) {
                C1469k c1469k = (C1469k) this.f16216b.mo6122b((String) entry.getValue());
                if (c1469k != null) {
                    m18163a(c1469k.e(), c1469k, false);
                }
            }
        }
    }

    /* renamed from: f */
    private void m18166f() {
        C1469k c1469k = (C1469k) this.f16219e.m19110a();
        if (c1469k != null) {
            m18163a(c1469k.e(), c1469k, false);
        }
    }

    /* renamed from: a */
    boolean m18171a(String str) {
        return str.startsWith(this.f16221g);
    }

    /* renamed from: b */
    public T mo6132b() {
        m18168a();
        return (C1469k) this.f16220f.get();
    }

    /* renamed from: a */
    public void mo6131a(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Session must not be null!");
        }
        m18168a();
        m18163a(t.e(), t, true);
    }

    /* renamed from: a */
    public T mo6129a(long j) {
        m18168a();
        return (C1469k) this.f16217c.get(Long.valueOf(j));
    }

    /* renamed from: a */
    public void mo6130a(long j, T t) {
        if (t == null) {
            throw new IllegalArgumentException("Session must not be null!");
        }
        m18168a();
        m18163a(j, t, false);
    }

    /* renamed from: c */
    public Map<Long, T> mo6133c() {
        m18168a();
        return Collections.unmodifiableMap(this.f16217c);
    }

    /* renamed from: a */
    private void m18163a(long j, T t, boolean z) {
        this.f16217c.put(Long.valueOf(j), t);
        C4863d c4863d = (C4863d) this.f16218d.get(Long.valueOf(j));
        if (c4863d == null) {
            c4863d = new C4863d(this.f16215a, this.f16216b, m18173b(j));
            this.f16218d.putIfAbsent(Long.valueOf(j), c4863d);
        }
        c4863d.m19111a(t);
        C1469k c1469k = (C1469k) this.f16220f.get();
        if (c1469k == null || c1469k.e() == j || z) {
            synchronized (this) {
                this.f16220f.compareAndSet(c1469k, t);
                this.f16219e.m19111a(t);
            }
        }
    }

    /* renamed from: b */
    String m18173b(long j) {
        return this.f16221g + "_" + j;
    }
}
