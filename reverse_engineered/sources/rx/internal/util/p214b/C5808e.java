package rx.internal.util.p214b;

/* compiled from: MpmcArrayQueue */
/* renamed from: rx.internal.util.b.e */
abstract class C5808e<E> extends C5807g<E> {
    /* renamed from: e */
    private static final long f18498e = C5826y.m21014a(C5808e.class, "consumerIndex");
    private volatile long consumerIndex;

    public C5808e(int i) {
        super(i);
    }

    /* renamed from: a */
    protected final long m21001a() {
        return this.consumerIndex;
    }

    /* renamed from: b */
    protected final boolean m21002b(long j, long j2) {
        return C5826y.f18506a.compareAndSwapLong(this, f18498e, j, j2);
    }
}
