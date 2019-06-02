package io.fabric.sdk.android.services.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PriorityTask */
/* renamed from: io.fabric.sdk.android.services.concurrency.f */
public class C4905f implements C4842a<C4844h>, C4843e, C4844h {
    /* renamed from: a */
    private final List<C4844h> f17224a = new ArrayList();
    /* renamed from: b */
    private final AtomicBoolean f17225b = new AtomicBoolean(false);
    /* renamed from: c */
    private final AtomicReference<Throwable> f17226c = new AtomicReference(null);

    /* renamed from: c */
    public /* synthetic */ void mo6230c(Object obj) {
        m19248a((C4844h) obj);
    }

    /* renamed from: a */
    public synchronized Collection<C4844h> m19247a() {
        return Collections.unmodifiableCollection(this.f17224a);
    }

    /* renamed from: a */
    public synchronized void m19248a(C4844h c4844h) {
        this.f17224a.add(c4844h);
    }

    /* renamed from: c */
    public boolean mo6231c() {
        for (C4844h f : m19247a()) {
            if (!f.mo6232f()) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public synchronized void mo6229b(boolean z) {
        this.f17225b.set(z);
    }

    /* renamed from: f */
    public boolean mo6232f() {
        return this.f17225b.get();
    }

    /* renamed from: b */
    public Priority mo6228b() {
        return Priority.NORMAL;
    }

    /* renamed from: a */
    public void mo6227a(Throwable th) {
        this.f17226c.set(th);
    }

    public int compareTo(Object obj) {
        return Priority.m19234a(this, obj);
    }

    /* renamed from: a */
    public static boolean m19246a(Object obj) {
        try {
            C4842a c4842a = (C4842a) obj;
            C4844h c4844h = (C4844h) obj;
            C4843e c4843e = (C4843e) obj;
            if (c4842a == null || c4844h == null || c4843e == null) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
