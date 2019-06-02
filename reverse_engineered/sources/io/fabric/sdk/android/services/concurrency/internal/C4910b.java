package io.fabric.sdk.android.services.concurrency.internal;

/* compiled from: DefaultRetryPolicy */
/* renamed from: io.fabric.sdk.android.services.concurrency.internal.b */
public class C4910b implements C4909e {
    /* renamed from: a */
    private final int f17232a;

    public C4910b() {
        this(1);
    }

    public C4910b(int i) {
        this.f17232a = i;
    }

    /* renamed from: a */
    public boolean mo6253a(int i, Throwable th) {
        return i < this.f17232a;
    }
}
