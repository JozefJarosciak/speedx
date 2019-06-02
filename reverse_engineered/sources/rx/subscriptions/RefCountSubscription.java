package rx.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.C5720f;

public final class RefCountSubscription implements C5720f {
    static final State EMPTY_STATE = new State(false, 0);
    private final C5720f actual;
    final AtomicReference<State> state = new AtomicReference(EMPTY_STATE);

    private static final class InnerSubscription extends AtomicInteger implements C5720f {
        private static final long serialVersionUID = 7005765588239987643L;
        final RefCountSubscription parent;

        public InnerSubscription(RefCountSubscription refCountSubscription) {
            this.parent = refCountSubscription;
        }

        public void unsubscribe() {
            if (compareAndSet(0, 1)) {
                this.parent.unsubscribeAChild();
            }
        }

        public boolean isUnsubscribed() {
            return get() != 0;
        }
    }

    private static final class State {
        final int children;
        final boolean isUnsubscribed;

        State(boolean z, int i) {
            this.isUnsubscribed = z;
            this.children = i;
        }

        State addChild() {
            return new State(this.isUnsubscribed, this.children + 1);
        }

        State removeChild() {
            return new State(this.isUnsubscribed, this.children - 1);
        }

        State unsubscribe() {
            return new State(true, this.children);
        }
    }

    public RefCountSubscription(C5720f c5720f) {
        if (c5720f == null) {
            throw new IllegalArgumentException("s");
        }
        this.actual = c5720f;
    }

    public C5720f get() {
        AtomicReference atomicReference = this.state;
        State state;
        do {
            state = (State) atomicReference.get();
            if (state.isUnsubscribed) {
                return Subscriptions.unsubscribed();
            }
        } while (!atomicReference.compareAndSet(state, state.addChild()));
        return new InnerSubscription(this);
    }

    public boolean isUnsubscribed() {
        return ((State) this.state.get()).isUnsubscribed;
    }

    public void unsubscribe() {
        State unsubscribe;
        AtomicReference atomicReference = this.state;
        State state;
        do {
            state = (State) atomicReference.get();
            if (!state.isUnsubscribed) {
                unsubscribe = state.unsubscribe();
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(state, unsubscribe));
        unsubscribeActualIfApplicable(unsubscribe);
    }

    private void unsubscribeActualIfApplicable(State state) {
        if (state.isUnsubscribed && state.children == 0) {
            this.actual.unsubscribe();
        }
    }

    void unsubscribeAChild() {
        State removeChild;
        AtomicReference atomicReference = this.state;
        State state;
        do {
            state = (State) atomicReference.get();
            removeChild = state.removeChild();
        } while (!atomicReference.compareAndSet(state, removeChild));
        unsubscribeActualIfApplicable(removeChild);
    }
}
