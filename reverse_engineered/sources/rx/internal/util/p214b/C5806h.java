package rx.internal.util.p214b;

/* compiled from: MpmcArrayQueue */
/* renamed from: rx.internal.util.b.h */
abstract class C5806h<E> extends C5805f<E> {
    /* renamed from: e */
    private static final long f18497e = C5826y.m21014a(C5806h.class, "producerIndex");
    private volatile long producerIndex;

    public C5806h(int i) {
        super(i);
    }

    /* renamed from: b */
    protected final long m20999b() {
        return this.producerIndex;
    }

    /* renamed from: c */
    protected final boolean m21000c(long j, long j2) {
        return C5826y.f18506a.compareAndSwapLong(this, f18497e, j, j2);
    }
}
