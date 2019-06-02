package rx.internal.util.p214b;

/* compiled from: SpmcArrayQueue */
/* renamed from: rx.internal.util.b.k */
abstract class C5814k<E> extends C5813m<E> {
    /* renamed from: d */
    protected static final long f18500d = C5826y.m21014a(C5814k.class, "consumerIndex");
    private volatile long consumerIndex;

    public C5814k(int i) {
        super(i);
    }

    /* renamed from: a */
    protected final long m21006a() {
        return this.consumerIndex;
    }

    /* renamed from: b */
    protected final boolean m21007b(long j, long j2) {
        return C5826y.f18506a.compareAndSwapLong(this, f18500d, j, j2);
    }
}
