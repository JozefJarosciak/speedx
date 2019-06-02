package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.C5720f;
import rx.p208a.C5711a;

public final class BooleanSubscription implements C5720f {
    static final C5711a EMPTY_ACTION = new C58441();
    final AtomicReference<C5711a> actionRef;

    /* renamed from: rx.subscriptions.BooleanSubscription$1 */
    static class C58441 implements C5711a {
        C58441() {
        }

        public void call() {
        }
    }

    public BooleanSubscription() {
        this.actionRef = new AtomicReference();
    }

    private BooleanSubscription(C5711a c5711a) {
        this.actionRef = new AtomicReference(c5711a);
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(C5711a c5711a) {
        return new BooleanSubscription(c5711a);
    }

    public boolean isUnsubscribed() {
        return this.actionRef.get() == EMPTY_ACTION;
    }

    public final void unsubscribe() {
        if (((C5711a) this.actionRef.get()) != EMPTY_ACTION) {
            C5711a c5711a = (C5711a) this.actionRef.getAndSet(EMPTY_ACTION);
            if (c5711a != null && c5711a != EMPTY_ACTION) {
                c5711a.call();
            }
        }
    }
}
