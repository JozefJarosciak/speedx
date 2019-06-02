package io.fabric.sdk.android.services.concurrency.internal;

/* compiled from: RetryState */
/* renamed from: io.fabric.sdk.android.services.concurrency.internal.f */
public class C4913f {
    /* renamed from: a */
    private final int f17239a;
    /* renamed from: b */
    private final C4908a f17240b;
    /* renamed from: c */
    private final C4909e f17241c;

    public C4913f(C4908a c4908a, C4909e c4909e) {
        this(0, c4908a, c4909e);
    }

    public C4913f(int i, C4908a c4908a, C4909e c4909e) {
        this.f17239a = i;
        this.f17240b = c4908a;
        this.f17241c = c4909e;
    }

    /* renamed from: a */
    public int m19277a() {
        return this.f17239a;
    }

    /* renamed from: b */
    public C4908a m19278b() {
        return this.f17240b;
    }

    /* renamed from: c */
    public C4909e m19279c() {
        return this.f17241c;
    }

    /* renamed from: d */
    public C4913f m19280d() {
        return new C4913f(this.f17239a + 1, this.f17240b, this.f17241c);
    }
}
