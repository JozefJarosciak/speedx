package rx.internal.util.p214b;

/* compiled from: ConcurrentSequencedCircularArrayQueue */
/* renamed from: rx.internal.util.b.c */
public abstract class C5804c<E> extends C5803a<E> {
    /* renamed from: e */
    private static final long f18494e = ((long) (C5826y.f18506a.arrayBaseOffset(long[].class) + (32 << (f18495f - a))));
    /* renamed from: f */
    private static final int f18495f = (a + 3);
    /* renamed from: d */
    protected final long[] f18496d;

    static {
        if (8 == C5826y.f18506a.arrayIndexScale(long[].class)) {
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public C5804c(int i) {
        super(i);
        int i2 = (int) (this.b + 1);
        this.f18496d = new long[((i2 << a) + 64)];
        for (long j = 0; j < ((long) i2); j++) {
            m20997a(this.f18496d, m20998d(j), j);
        }
    }

    /* renamed from: d */
    protected final long m20998d(long j) {
        return f18494e + ((this.b & j) << f18495f);
    }

    /* renamed from: a */
    protected final void m20997a(long[] jArr, long j, long j2) {
        C5826y.f18506a.putOrderedLong(jArr, j, j2);
    }

    /* renamed from: a */
    protected final long m20996a(long[] jArr, long j) {
        return C5826y.f18506a.getLongVolatile(jArr, j);
    }
}
