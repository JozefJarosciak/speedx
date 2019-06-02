package rx.internal.producers;

import java.util.concurrent.atomic.AtomicInteger;
import rx.C5721e;
import rx.C5725c;
import rx.exceptions.C5736a;

public final class SingleDelayedProducer<T> extends AtomicInteger implements C5725c {
    private static final long serialVersionUID = -2873467947112093874L;
    /* renamed from: a */
    final C5721e<? super T> f18389a;
    /* renamed from: b */
    T f18390b;

    public SingleDelayedProducer(C5721e<? super T> c5721e) {
        this.f18389a = c5721e;
    }

    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0) {
            int i;
            while (true) {
                i = get();
                if (i != 0) {
                    break;
                } else if (compareAndSet(0, 2)) {
                    return;
                }
            }
            if (i == 1 && compareAndSet(1, 3)) {
                emit(this.f18389a, this.f18390b);
            }
        }
    }

    public void setValue(T t) {
        do {
            int i = get();
            if (i == 0) {
                this.f18390b = t;
            } else if (i == 2 && compareAndSet(2, 3)) {
                emit(this.f18389a, t);
                return;
            } else {
                return;
            }
        } while (!compareAndSet(0, 1));
    }

    private static <T> void emit(C5721e<? super T> c5721e, T t) {
        if (!c5721e.isUnsubscribed()) {
            try {
                c5721e.mo7150a((Object) t);
                if (!c5721e.isUnsubscribed()) {
                    c5721e.mo7149a();
                }
            } catch (Throwable th) {
                C5736a.m20861a(th, c5721e, t);
            }
        }
    }
}
