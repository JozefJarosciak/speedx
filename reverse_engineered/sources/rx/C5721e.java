package rx;

import rx.internal.util.C5835g;

/* compiled from: Subscriber */
/* renamed from: rx.e */
public abstract class C5721e<T> implements C5719b<T>, C5720f {
    /* renamed from: a */
    private final C5835g f18272a;
    /* renamed from: b */
    private final C5721e<?> f18273b;
    /* renamed from: c */
    private C5725c f18274c;
    /* renamed from: d */
    private long f18275d;

    protected C5721e() {
        this(null, false);
    }

    protected C5721e(C5721e<?> c5721e) {
        this(c5721e, true);
    }

    protected C5721e(C5721e<?> c5721e, boolean z) {
        this.f18275d = Long.MIN_VALUE;
        this.f18273b = c5721e;
        C5835g c5835g = (!z || c5721e == null) ? new C5835g() : c5721e.f18272a;
        this.f18272a = c5835g;
    }

    /* renamed from: a */
    public final void m20818a(C5720f c5720f) {
        this.f18272a.m21040a(c5720f);
    }

    public final void unsubscribe() {
        this.f18272a.unsubscribe();
    }

    public final boolean isUnsubscribed() {
        return this.f18272a.isUnsubscribed();
    }

    /* renamed from: b */
    public void m20819b() {
    }

    /* renamed from: a */
    protected final void m20816a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.f18274c != null) {
                C5725c c5725c = this.f18274c;
                c5725c.request(j);
                return;
            }
            m20815b(j);
        }
    }

    /* renamed from: b */
    private void m20815b(long j) {
        if (this.f18275d == Long.MIN_VALUE) {
            this.f18275d = j;
            return;
        }
        long j2 = this.f18275d + j;
        if (j2 < 0) {
            this.f18275d = Long.MAX_VALUE;
        } else {
            this.f18275d = j2;
        }
    }

    /* renamed from: a */
    public void mo7164a(C5725c c5725c) {
        Object obj = null;
        synchronized (this) {
            long j = this.f18275d;
            this.f18274c = c5725c;
            if (this.f18273b != null && j == Long.MIN_VALUE) {
                obj = 1;
            }
        }
        if (obj != null) {
            this.f18273b.mo7164a(this.f18274c);
        } else if (j == Long.MIN_VALUE) {
            this.f18274c.request(Long.MAX_VALUE);
        } else {
            this.f18274c.request(j);
        }
    }
}
