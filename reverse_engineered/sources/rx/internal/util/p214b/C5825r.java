package rx.internal.util.p214b;

/* compiled from: SpscArrayQueue */
/* renamed from: rx.internal.util.b.r */
public final class C5825r<E> extends C5824w<E> {
    public C5825r(int i) {
        super(i);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        Object[] objArr = this.c;
        long j = this.producerIndex;
        long a = m20987a(j);
        if (m20993b(objArr, a) != null) {
            return false;
        }
        m20994b(objArr, a, e);
        m21012d(1 + j);
        return true;
    }

    public E poll() {
        long j = this.consumerIndex;
        long a = m20987a(j);
        Object[] objArr = this.c;
        E b = m20993b(objArr, a);
        if (b == null) {
            return null;
        }
        m20994b(objArr, a, null);
        m21013e(j + 1);
        return b;
    }

    public E peek() {
        return m20995c(m20987a(this.consumerIndex));
    }

    public int size() {
        long b = m21011b();
        while (true) {
            long a = m21010a();
            long b2 = m21011b();
            if (b == b2) {
                return (int) (a - b2);
            }
            b = b2;
        }
    }

    public boolean isEmpty() {
        return m21010a() == m21011b();
    }

    /* renamed from: d */
    private void m21012d(long j) {
        C5826y.f18506a.putOrderedLong(this, f, j);
    }

    /* renamed from: e */
    private void m21013e(long j) {
        C5826y.f18506a.putOrderedLong(this, e, j);
    }

    /* renamed from: a */
    private long m21010a() {
        return C5826y.f18506a.getLongVolatile(this, f);
    }

    /* renamed from: b */
    private long m21011b() {
        return C5826y.f18506a.getLongVolatile(this, e);
    }
}
