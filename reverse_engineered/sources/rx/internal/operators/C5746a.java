package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;

/* compiled from: BackpressureUtils */
/* renamed from: rx.internal.operators.a */
public final class C5746a {
    /* renamed from: a */
    public static long m20906a(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, C5746a.m20905a(j2, j)));
        return j2;
    }

    /* renamed from: a */
    public static long m20905a(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    /* renamed from: b */
    public static long m20907b(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j3 = atomicLong.get();
            if (j3 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j2 = j3 - j;
            if (j2 < 0) {
                throw new IllegalStateException("More produced than requested: " + j2);
            }
        } while (!atomicLong.compareAndSet(j3, j2));
        return j2;
    }
}
