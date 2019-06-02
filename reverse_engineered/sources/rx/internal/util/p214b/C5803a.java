package rx.internal.util.p214b;

import java.util.Iterator;

/* compiled from: ConcurrentCircularArrayQueue */
/* renamed from: rx.internal.util.b.a */
public abstract class C5803a<E> extends C5802b<E> {
    /* renamed from: a */
    protected static final int f18489a = Integer.getInteger("sparse.shift", 0).intValue();
    /* renamed from: d */
    private static final long f18490d = ((long) (C5826y.f18506a.arrayBaseOffset(Object[].class) + (32 << (f18491e - f18489a))));
    /* renamed from: e */
    private static final int f18491e;
    /* renamed from: b */
    protected final long f18492b;
    /* renamed from: c */
    protected final E[] f18493c;

    static {
        int arrayIndexScale = C5826y.f18506a.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            f18491e = f18489a + 2;
        } else if (8 == arrayIndexScale) {
            f18491e = f18489a + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
    }

    public C5803a(int i) {
        int a = C5810i.m21003a(i);
        this.f18492b = (long) (a - 1);
        this.f18493c = new Object[((a << f18489a) + 64)];
    }

    /* renamed from: a */
    protected final long m20987a(long j) {
        return m20988a(j, this.f18492b);
    }

    /* renamed from: a */
    protected final long m20988a(long j, long j2) {
        return f18490d + ((j & j2) << f18491e);
    }

    /* renamed from: a */
    protected final void m20990a(long j, E e) {
        m20991a(this.f18493c, j, e);
    }

    /* renamed from: a */
    protected final void m20991a(E[] eArr, long j, E e) {
        C5826y.f18506a.putObject(eArr, j, e);
    }

    /* renamed from: b */
    protected final void m20994b(E[] eArr, long j, E e) {
        C5826y.f18506a.putOrderedObject(eArr, j, e);
    }

    /* renamed from: b */
    protected final E m20992b(long j) {
        return m20989a(this.f18493c, j);
    }

    /* renamed from: a */
    protected final E m20989a(E[] eArr, long j) {
        return C5826y.f18506a.getObject(eArr, j);
    }

    /* renamed from: c */
    protected final E m20995c(long j) {
        return m20993b(this.f18493c, j);
    }

    /* renamed from: b */
    protected final E m20993b(E[] eArr, long j) {
        return C5826y.f18506a.getObjectVolatile(eArr, j);
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
}
