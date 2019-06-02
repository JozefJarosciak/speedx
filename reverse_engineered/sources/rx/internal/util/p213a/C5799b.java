package rx.internal.util.p213a;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: SpscAtomicArrayQueue */
/* renamed from: rx.internal.util.a.b */
public final class C5799b<E> extends C5798a<E> {
    /* renamed from: g */
    private static final Integer f18480g = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    /* renamed from: c */
    final AtomicLong f18481c = new AtomicLong();
    /* renamed from: d */
    protected long f18482d;
    /* renamed from: e */
    final AtomicLong f18483e = new AtomicLong();
    /* renamed from: f */
    final int f18484f;

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public C5799b(int i) {
        super(i);
        this.f18484f = Math.min(i / 4, f18480g.intValue());
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray atomicReferenceArray = this.a;
        int i = this.b;
        long j = this.f18481c.get();
        int a = m20976a(j, i);
        if (j >= this.f18482d) {
            int i2 = this.f18484f;
            if (m20978a(atomicReferenceArray, m20976a(((long) i2) + j, i)) == null) {
                this.f18482d = ((long) i2) + j;
            } else if (m20978a(atomicReferenceArray, a) != null) {
                return false;
            }
        }
        m20979a(atomicReferenceArray, a, e);
        m20982b(1 + j);
        return true;
    }

    public E poll() {
        long j = this.f18483e.get();
        int a = m20975a(j);
        AtomicReferenceArray atomicReferenceArray = this.a;
        E a2 = m20978a(atomicReferenceArray, a);
        if (a2 == null) {
            return null;
        }
        m20979a(atomicReferenceArray, a, null);
        m20983c(j + 1);
        return a2;
    }

    public E peek() {
        return m20977a(m20975a(this.f18483e.get()));
    }

    public int size() {
        long a = m20980a();
        while (true) {
            long b = m20981b();
            long a2 = m20980a();
            if (a == a2) {
                return (int) (b - a2);
            }
            a = a2;
        }
    }

    public boolean isEmpty() {
        return m20981b() == m20980a();
    }

    /* renamed from: b */
    private void m20982b(long j) {
        this.f18481c.lazySet(j);
    }

    /* renamed from: c */
    private void m20983c(long j) {
        this.f18483e.lazySet(j);
    }

    /* renamed from: a */
    private long m20980a() {
        return this.f18483e.get();
    }

    /* renamed from: b */
    private long m20981b() {
        return this.f18481c.get();
    }
}
