package rx.internal.util.p214b;

/* compiled from: SpmcArrayQueue */
/* renamed from: rx.internal.util.b.p */
abstract class C5812p<E> extends C5811l<E> {
    /* renamed from: e */
    protected static final long f18499e = C5826y.m21014a(C5812p.class, "producerIndex");
    private volatile long producerIndex;

    /* renamed from: b */
    protected final long m21004b() {
        return this.producerIndex;
    }

    /* renamed from: d */
    protected final void m21005d(long j) {
        C5826y.f18506a.putOrderedLong(this, f18499e, j);
    }

    public C5812p(int i) {
        super(i);
    }
}
