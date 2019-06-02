package rx.internal.util.p214b;

/* compiled from: SpscArrayQueue */
/* renamed from: rx.internal.util.b.s */
abstract class C5819s<E> extends C5803a<E> {
    /* renamed from: e */
    private static final Integer f18502e = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    /* renamed from: d */
    protected final int f18503d;

    public C5819s(int i) {
        super(i);
        this.f18503d = Math.min(i / 4, f18502e.intValue());
    }
}
