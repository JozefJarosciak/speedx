package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.C5720f;

public final class SerialSubscription implements C5720f {
    final AtomicReference<State> state = new AtomicReference(new State(false, Subscriptions.empty()));

    private static final class State {
        final boolean isUnsubscribed;
        final C5720f subscription;

        State(boolean z, C5720f c5720f) {
            this.isUnsubscribed = z;
            this.subscription = c5720f;
        }

        State unsubscribe() {
            return new State(true, this.subscription);
        }

        State set(C5720f c5720f) {
            return new State(this.isUnsubscribed, c5720f);
        }
    }

    public boolean isUnsubscribed() {
        return ((State) this.state.get()).isUnsubscribed;
    }

    public void unsubscribe() {
        State state;
        AtomicReference atomicReference = this.state;
        do {
            state = (State) atomicReference.get();
            if (state.isUnsubscribed) {
                return;
            }
        } while (!atomicReference.compareAndSet(state, state.unsubscribe()));
        state.subscription.unsubscribe();
    }

    public void set(C5720f c5720f) {
        if (c5720f == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        State state;
        AtomicReference atomicReference = this.state;
        do {
            state = (State) atomicReference.get();
            if (state.isUnsubscribed) {
                c5720f.unsubscribe();
                return;
            }
        } while (!atomicReference.compareAndSet(state, state.set(c5720f)));
        state.subscription.unsubscribe();
    }

    public C5720f get() {
        return ((State) this.state.get()).subscription;
    }
}
