package okhttp3.internal.framed;

import java.util.concurrent.CountDownLatch;

/* compiled from: Ping */
/* renamed from: okhttp3.internal.framed.j */
public final class C5542j {
    /* renamed from: a */
    private final CountDownLatch f17859a = new CountDownLatch(1);
    /* renamed from: b */
    private long f17860b = -1;
    /* renamed from: c */
    private long f17861c = -1;

    C5542j() {
    }

    /* renamed from: a */
    void m20104a() {
        if (this.f17860b != -1) {
            throw new IllegalStateException();
        }
        this.f17860b = System.nanoTime();
    }

    /* renamed from: b */
    void m20105b() {
        if (this.f17861c != -1 || this.f17860b == -1) {
            throw new IllegalStateException();
        }
        this.f17861c = System.nanoTime();
        this.f17859a.countDown();
    }

    /* renamed from: c */
    void m20106c() {
        if (this.f17861c != -1 || this.f17860b == -1) {
            throw new IllegalStateException();
        }
        this.f17861c = this.f17860b - 1;
        this.f17859a.countDown();
    }
}
