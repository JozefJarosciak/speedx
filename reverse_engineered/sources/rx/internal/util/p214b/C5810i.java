package rx.internal.util.p214b;

/* compiled from: Pow2 */
/* renamed from: rx.internal.util.b.i */
public final class C5810i {
    /* renamed from: a */
    public static int m21003a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
