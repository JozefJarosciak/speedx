package rx.internal.util.p213a;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.p214b.C5810i;

/* compiled from: AtomicReferenceArrayQueue */
/* renamed from: rx.internal.util.a.a */
abstract class C5798a<E> extends AbstractQueue<E> {
    /* renamed from: a */
    protected final AtomicReferenceArray<E> f18478a;
    /* renamed from: b */
    protected final int f18479b;

    public C5798a(int i) {
        int a = C5810i.m21003a(i);
        this.f18479b = a - 1;
        this.f18478a = new AtomicReferenceArray(a);
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    /* renamed from: a */
    protected final int m20976a(long j, int i) {
        return ((int) j) & i;
    }

    /* renamed from: a */
    protected final int m20975a(long j) {
        return ((int) j) & this.f18479b;
    }

    /* renamed from: a */
    protected final E m20978a(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    /* renamed from: a */
    protected final void m20979a(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.lazySet(i, e);
    }

    /* renamed from: a */
    protected final E m20977a(int i) {
        return m20978a(this.f18478a, i);
    }
}
