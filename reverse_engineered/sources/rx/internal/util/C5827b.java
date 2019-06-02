package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.schedulers.C5766b;
import rx.internal.util.p214b.C5809d;
import rx.internal.util.p214b.C5826y;

/* compiled from: ObjectPool */
/* renamed from: rx.internal.util.b */
public abstract class C5827b<T> {
    /* renamed from: a */
    Queue<T> f18508a;
    /* renamed from: b */
    final int f18509b;
    /* renamed from: c */
    final int f18510c;
    /* renamed from: d */
    private final long f18511d;
    /* renamed from: e */
    private final AtomicReference<Future<?>> f18512e;

    /* compiled from: ObjectPool */
    /* renamed from: rx.internal.util.b$1 */
    class C58011 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C5827b f18488a;

        C58011(C5827b c5827b) {
            this.f18488a = c5827b;
        }

        public void run() {
            int i = 0;
            int size = this.f18488a.f18508a.size();
            if (size < this.f18488a.f18509b) {
                size = this.f18488a.f18510c - size;
                while (i < size) {
                    this.f18488a.f18508a.add(this.f18488a.mo7170b());
                    i++;
                }
            } else if (size > this.f18488a.f18510c) {
                size -= this.f18488a.f18510c;
                while (i < size) {
                    this.f18488a.f18508a.poll();
                    i++;
                }
            }
        }
    }

    /* renamed from: b */
    protected abstract T mo7170b();

    public C5827b() {
        this(0, 0, 67);
    }

    private C5827b(int i, int i2, long j) {
        this.f18509b = i;
        this.f18510c = i2;
        this.f18511d = j;
        this.f18512e = new AtomicReference();
        m21016a(i);
        m21017a();
    }

    /* renamed from: a */
    public void m21018a(T t) {
        if (t != null) {
            this.f18508a.offer(t);
        }
    }

    /* renamed from: a */
    public void m21017a() {
        while (this.f18512e.get() == null) {
            try {
                Future scheduleAtFixedRate = C5766b.m20948b().scheduleAtFixedRate(new C58011(this), this.f18511d, this.f18511d, TimeUnit.SECONDS);
                if (!this.f18512e.compareAndSet(null, scheduleAtFixedRate)) {
                    scheduleAtFixedRate.cancel(false);
                } else {
                    return;
                }
            } catch (Throwable e) {
                C5831e.m21032a(e);
                return;
            }
        }
    }

    /* renamed from: a */
    private void m21016a(int i) {
        if (C5826y.m21015a()) {
            this.f18508a = new C5809d(Math.max(this.f18510c, 1024));
        } else {
            this.f18508a = new ConcurrentLinkedQueue();
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.f18508a.add(mo7170b());
        }
    }
}
