package rx.subscriptions;

import java.util.concurrent.Future;
import rx.C5720f;
import rx.p208a.C5711a;

public final class Subscriptions {
    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();

    private static final class FutureSubscription implements C5720f {
        /* renamed from: f */
        final Future<?> f18552f;

        public FutureSubscription(Future<?> future) {
            this.f18552f = future;
        }

        public void unsubscribe() {
            this.f18552f.cancel(true);
        }

        public boolean isUnsubscribed() {
            return this.f18552f.isCancelled();
        }
    }

    static final class Unsubscribed implements C5720f {
        Unsubscribed() {
        }

        public void unsubscribe() {
        }

        public boolean isUnsubscribed() {
            return true;
        }
    }

    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static C5720f empty() {
        return BooleanSubscription.create();
    }

    public static C5720f unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static C5720f create(C5711a c5711a) {
        return BooleanSubscription.create(c5711a);
    }

    public static C5720f from(Future<?> future) {
        return new FutureSubscription(future);
    }

    public static CompositeSubscription from(C5720f... c5720fArr) {
        return new CompositeSubscription(c5720fArr);
    }
}
