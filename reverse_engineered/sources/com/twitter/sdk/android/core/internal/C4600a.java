package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AuthRequestQueue */
/* renamed from: com.twitter.sdk.android.core.internal.a */
public class C4600a {
    /* renamed from: a */
    private final C4609d f16247a;
    /* renamed from: b */
    final Queue<C4580e<C1469k>> f16248b = new ConcurrentLinkedQueue();
    /* renamed from: c */
    final AtomicBoolean f16249c = new AtomicBoolean(true);

    /* compiled from: AuthRequestQueue */
    /* renamed from: com.twitter.sdk.android.core.internal.a$1 */
    class C45991 extends C4580e<C1469k> {
        /* renamed from: a */
        final /* synthetic */ C4600a f16246a;

        C45991(C4600a c4600a) {
            this.f16246a = c4600a;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<C1469k> c4645j) {
            this.f16246a.m18224b((C1469k) c4645j.f16364a);
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            this.f16246a.m18221a(twitterException);
        }
    }

    public C4600a(C4609d c4609d) {
        this.f16247a = c4609d;
    }

    /* renamed from: b */
    public synchronized boolean m18225b(C4580e<C1469k> c4580e) {
        boolean z = true;
        synchronized (this) {
            if (c4580e == null) {
                z = false;
            } else if (this.f16249c.get()) {
                this.f16248b.add(c4580e);
            } else {
                C1469k b = m18223b();
                if (b != null) {
                    c4580e.mo6128a(new C4645j(b, null));
                } else {
                    this.f16248b.add(c4580e);
                    this.f16249c.set(true);
                    m18220a();
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized void m18222a(C1469k c1469k) {
        if (c1469k != null) {
            m18224b(c1469k);
        } else if (this.f16248b.size() > 0) {
            m18220a();
        } else {
            this.f16249c.set(false);
        }
    }

    /* renamed from: a */
    void m18220a() {
        this.f16247a.mo6175a(new C45991(this));
    }

    /* renamed from: b */
    synchronized void m18224b(C1469k c1469k) {
        this.f16249c.set(false);
        while (!this.f16248b.isEmpty()) {
            ((C4580e) this.f16248b.poll()).mo6128a(new C4645j(c1469k, null));
        }
    }

    /* renamed from: a */
    synchronized void m18221a(TwitterException twitterException) {
        this.f16249c.set(false);
        while (!this.f16248b.isEmpty()) {
            ((C4580e) this.f16248b.poll()).mo6127a(twitterException);
        }
    }

    /* renamed from: b */
    C1469k m18223b() {
        C1469k a = this.f16247a.mo6174a();
        return (a == null || a.d() == null || a.d().a()) ? null : a;
    }
}
