package io.fabric.sdk.android.services.concurrency.internal;

/* compiled from: ExponentialBackoff */
/* renamed from: io.fabric.sdk.android.services.concurrency.internal.c */
public class C4911c implements C4908a {
    /* renamed from: a */
    private final long f17233a;
    /* renamed from: b */
    private final int f17234b;

    public C4911c(long j) {
        this(j, 2);
    }

    public C4911c(long j, int i) {
        this.f17233a = j;
        this.f17234b = i;
    }

    /* renamed from: a */
    public long mo6254a(int i) {
        return (long) (((double) this.f17233a) * Math.pow((double) this.f17234b, (double) i));
    }
}
