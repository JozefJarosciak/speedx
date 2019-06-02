package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Monitor.Guard;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public abstract class AbstractService implements Service {
    private static final Callback<Listener> RUNNING_CALLBACK = new Callback<Listener>("running()") {
        void call(Listener listener) {
            listener.running();
        }
    };
    private static final Callback<Listener> STARTING_CALLBACK = new Callback<Listener>("starting()") {
        void call(Listener listener) {
            listener.starting();
        }
    };
    private static final Callback<Listener> STOPPING_FROM_RUNNING_CALLBACK = stoppingCallback(State.RUNNING);
    private static final Callback<Listener> STOPPING_FROM_STARTING_CALLBACK = stoppingCallback(State.STARTING);
    private static final Callback<Listener> TERMINATED_FROM_NEW_CALLBACK = terminatedCallback(State.NEW);
    private static final Callback<Listener> TERMINATED_FROM_RUNNING_CALLBACK = terminatedCallback(State.RUNNING);
    private static final Callback<Listener> TERMINATED_FROM_STOPPING_CALLBACK = terminatedCallback(State.STOPPING);
    private final Guard hasReachedRunning = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(State.RUNNING) >= 0;
        }
    };
    private final Guard isStartable = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state() == State.NEW;
        }
    };
    private final Guard isStoppable = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(State.RUNNING) <= 0;
        }
    };
    private final Guard isStopped = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    };
    private final List<ListenerCallQueue<Listener>> listeners = Collections.synchronizedList(new ArrayList());
    private final Monitor monitor = new Monitor();
    private volatile StateSnapshot snapshot = new StateSnapshot(State.NEW);

    private static final class StateSnapshot {
        final Throwable failure;
        final boolean shutdownWhenStartupFinishes;
        final State state;

        StateSnapshot(State state) {
            this(state, false, null);
        }

        StateSnapshot(State state, boolean z, Throwable th) {
            int i;
            boolean z2 = !z || state == State.STARTING;
            Preconditions.checkArgument(z2, "shudownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", state);
            if (th != null) {
                i = 1;
            } else {
                i = 0;
            }
            Preconditions.checkArgument((i ^ (state == State.FAILED ? 1 : 0)) == 0, "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", state, th);
            this.state = state;
            this.shutdownWhenStartupFinishes = z;
            this.failure = th;
        }

        State externalState() {
            if (this.shutdownWhenStartupFinishes && this.state == State.STARTING) {
                return State.STOPPING;
            }
            return this.state;
        }

        Throwable failureCause() {
            boolean z;
            if (this.state == State.FAILED) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z, "failureCause() is only valid if the service has failed, service is %s", this.state);
            return this.failure;
        }
    }

    protected abstract void doStart();

    protected abstract void doStop();

    private static Callback<Listener> terminatedCallback(final State state) {
        String valueOf = String.valueOf(String.valueOf(state));
        return new Callback<Listener>(new StringBuilder(valueOf.length() + 21).append("terminated({from = ").append(valueOf).append("})").toString()) {
            void call(Listener listener) {
                listener.terminated(state);
            }
        };
    }

    private static Callback<Listener> stoppingCallback(final State state) {
        String valueOf = String.valueOf(String.valueOf(state));
        return new Callback<Listener>(new StringBuilder(valueOf.length() + 19).append("stopping({from = ").append(valueOf).append("})").toString()) {
            void call(Listener listener) {
                listener.stopping(state);
            }
        };
    }

    protected AbstractService() {
    }

    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(State.STARTING);
                starting();
                doStart();
            } catch (Throwable th) {
                notifyFailed(th);
            } finally {
                this.monitor.leave();
                executeListeners();
            }
            return this;
        }
        String valueOf = String.valueOf(String.valueOf(this));
        throw new IllegalStateException(new StringBuilder(valueOf.length() + 33).append("Service ").append(valueOf).append(" has already been started").toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.common.util.concurrent.Service stopAsync() {
        /*
        r4 = this;
        r0 = r4.monitor;
        r1 = r4.isStoppable;
        r0 = r0.enterIf(r1);
        if (r0 == 0) goto L_0x004c;
    L_0x000a:
        r0 = r4.state();	 Catch:{ Throwable -> 0x0040 }
        r1 = com.google.common.util.concurrent.AbstractService.AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State;	 Catch:{ Throwable -> 0x0040 }
        r2 = r0.ordinal();	 Catch:{ Throwable -> 0x0040 }
        r1 = r1[r2];	 Catch:{ Throwable -> 0x0040 }
        switch(r1) {
            case 1: goto L_0x004d;
            case 2: goto L_0x0064;
            case 3: goto L_0x007f;
            case 4: goto L_0x0091;
            case 5: goto L_0x0091;
            case 6: goto L_0x0091;
            default: goto L_0x0019;
        };	 Catch:{ Throwable -> 0x0040 }
    L_0x0019:
        r1 = new java.lang.AssertionError;	 Catch:{ Throwable -> 0x0040 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0040 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0040 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0040 }
        r3 = r0.length();	 Catch:{ Throwable -> 0x0040 }
        r3 = r3 + 18;
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0040 }
        r3 = "Unexpected state: ";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0040 }
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x0040 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0040 }
        r1.<init>(r0);	 Catch:{ Throwable -> 0x0040 }
        throw r1;	 Catch:{ Throwable -> 0x0040 }
    L_0x0040:
        r0 = move-exception;
        r4.notifyFailed(r0);	 Catch:{ all -> 0x0075 }
        r0 = r4.monitor;
        r0.leave();
        r4.executeListeners();
    L_0x004c:
        return r4;
    L_0x004d:
        r0 = new com.google.common.util.concurrent.AbstractService$StateSnapshot;	 Catch:{ Throwable -> 0x0040 }
        r1 = com.google.common.util.concurrent.Service.State.TERMINATED;	 Catch:{ Throwable -> 0x0040 }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0040 }
        r4.snapshot = r0;	 Catch:{ Throwable -> 0x0040 }
        r0 = com.google.common.util.concurrent.Service.State.NEW;	 Catch:{ Throwable -> 0x0040 }
        r4.terminated(r0);	 Catch:{ Throwable -> 0x0040 }
    L_0x005b:
        r0 = r4.monitor;
        r0.leave();
        r4.executeListeners();
        goto L_0x004c;
    L_0x0064:
        r0 = new com.google.common.util.concurrent.AbstractService$StateSnapshot;	 Catch:{ Throwable -> 0x0040 }
        r1 = com.google.common.util.concurrent.Service.State.STARTING;	 Catch:{ Throwable -> 0x0040 }
        r2 = 1;
        r3 = 0;
        r0.<init>(r1, r2, r3);	 Catch:{ Throwable -> 0x0040 }
        r4.snapshot = r0;	 Catch:{ Throwable -> 0x0040 }
        r0 = com.google.common.util.concurrent.Service.State.STARTING;	 Catch:{ Throwable -> 0x0040 }
        r4.stopping(r0);	 Catch:{ Throwable -> 0x0040 }
        goto L_0x005b;
    L_0x0075:
        r0 = move-exception;
        r1 = r4.monitor;
        r1.leave();
        r4.executeListeners();
        throw r0;
    L_0x007f:
        r0 = new com.google.common.util.concurrent.AbstractService$StateSnapshot;	 Catch:{ Throwable -> 0x0040 }
        r1 = com.google.common.util.concurrent.Service.State.STOPPING;	 Catch:{ Throwable -> 0x0040 }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0040 }
        r4.snapshot = r0;	 Catch:{ Throwable -> 0x0040 }
        r0 = com.google.common.util.concurrent.Service.State.RUNNING;	 Catch:{ Throwable -> 0x0040 }
        r4.stopping(r0);	 Catch:{ Throwable -> 0x0040 }
        r4.doStop();	 Catch:{ Throwable -> 0x0040 }
        goto L_0x005b;
    L_0x0091:
        r1 = new java.lang.AssertionError;	 Catch:{ Throwable -> 0x0040 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0040 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0040 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0040 }
        r3 = r0.length();	 Catch:{ Throwable -> 0x0040 }
        r3 = r3 + 45;
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0040 }
        r3 = "isStoppable is incorrectly implemented, saw: ";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0040 }
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x0040 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0040 }
        r1.<init>(r0);	 Catch:{ Throwable -> 0x0040 }
        throw r1;	 Catch:{ Throwable -> 0x0040 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractService.stopAsync():com.google.common.util.concurrent.Service");
    }

    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, j, timeUnit)) {
            try {
                checkCurrentState(State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            throw new TimeoutException(new StringBuilder((valueOf.length() + 66) + valueOf2.length()).append("Timed out waiting for ").append(valueOf).append(" to reach the RUNNING state. ").append("Current state: ").append(valueOf2).toString());
        }
    }

    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, j, timeUnit)) {
            try {
                checkCurrentState(State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            throw new TimeoutException(new StringBuilder((valueOf.length() + 65) + valueOf2.length()).append("Timed out waiting for ").append(valueOf).append(" to reach a terminal state. ").append("Current state: ").append(valueOf2).toString());
        }
    }

    private void checkCurrentState(State state) {
        State state2 = state();
        if (state2 == state) {
            return;
        }
        if (state2 == State.FAILED) {
            String valueOf = String.valueOf(String.valueOf(state));
            throw new IllegalStateException(new StringBuilder(valueOf.length() + 55).append("Expected the service to be ").append(valueOf).append(", but the service has FAILED").toString(), failureCause());
        }
        String valueOf2 = String.valueOf(String.valueOf(state));
        String valueOf3 = String.valueOf(String.valueOf(state2));
        throw new IllegalStateException(new StringBuilder((valueOf2.length() + 37) + valueOf3.length()).append("Expected the service to be ").append(valueOf2).append(", but was ").append(valueOf3).toString());
    }

    protected final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state != State.STARTING) {
                String valueOf = String.valueOf(String.valueOf(this.snapshot.state));
                Throwable illegalStateException = new IllegalStateException(new StringBuilder(valueOf.length() + 43).append("Cannot notifyStarted() when the service is ").append(valueOf).toString());
                notifyFailed(illegalStateException);
                throw illegalStateException;
            }
            if (this.snapshot.shutdownWhenStartupFinishes) {
                this.snapshot = new StateSnapshot(State.STOPPING);
                doStop();
            } else {
                this.snapshot = new StateSnapshot(State.RUNNING);
                running();
            }
            this.monitor.leave();
            executeListeners();
        } catch (Throwable th) {
            this.monitor.leave();
            executeListeners();
        }
    }

    protected final void notifyStopped() {
        this.monitor.enter();
        try {
            State state = this.snapshot.state;
            if (state == State.STOPPING || state == State.RUNNING) {
                this.snapshot = new StateSnapshot(State.TERMINATED);
                terminated(state);
                return;
            }
            String valueOf = String.valueOf(String.valueOf(state));
            Throwable illegalStateException = new IllegalStateException(new StringBuilder(valueOf.length() + 43).append("Cannot notifyStopped() when the service is ").append(valueOf).toString());
            notifyFailed(illegalStateException);
            throw illegalStateException;
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    protected final void notifyFailed(Throwable th) {
        Preconditions.checkNotNull(th);
        this.monitor.enter();
        try {
            State state = state();
            String valueOf;
            switch (state) {
                case NEW:
                case TERMINATED:
                    valueOf = String.valueOf(String.valueOf(state));
                    throw new IllegalStateException(new StringBuilder(valueOf.length() + 22).append("Failed while in state:").append(valueOf).toString(), th);
                case STARTING:
                case RUNNING:
                case STOPPING:
                    this.snapshot = new StateSnapshot(State.FAILED, false, th);
                    failed(state, th);
                    break;
                case FAILED:
                    break;
                default:
                    valueOf = String.valueOf(String.valueOf(state));
                    throw new AssertionError(new StringBuilder(valueOf.length() + 18).append("Unexpected state: ").append(valueOf).toString());
            }
            this.monitor.leave();
            executeListeners();
        } catch (Throwable th2) {
            this.monitor.leave();
            executeListeners();
        }
    }

    public final boolean isRunning() {
        return state() == State.RUNNING;
    }

    public final State state() {
        return this.snapshot.externalState();
    }

    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    public final void addListener(Listener listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.monitor.enter();
        try {
            if (!state().isTerminal()) {
                this.listeners.add(new ListenerCallQueue(listener, executor));
            }
            this.monitor.leave();
        } catch (Throwable th) {
            this.monitor.leave();
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getClass().getSimpleName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        return new StringBuilder((valueOf.length() + 3) + valueOf2.length()).append(valueOf).append(" [").append(valueOf2).append("]").toString();
    }

    private void executeListeners() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            for (int i = 0; i < this.listeners.size(); i++) {
                ((ListenerCallQueue) this.listeners.get(i)).execute();
            }
        }
    }

    private void starting() {
        STARTING_CALLBACK.enqueueOn(this.listeners);
    }

    private void running() {
        RUNNING_CALLBACK.enqueueOn(this.listeners);
    }

    private void stopping(State state) {
        if (state == State.STARTING) {
            STOPPING_FROM_STARTING_CALLBACK.enqueueOn(this.listeners);
        } else if (state == State.RUNNING) {
            STOPPING_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    private void terminated(State state) {
        switch (state) {
            case NEW:
                TERMINATED_FROM_NEW_CALLBACK.enqueueOn(this.listeners);
                return;
            case RUNNING:
                TERMINATED_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
                return;
            case STOPPING:
                TERMINATED_FROM_STOPPING_CALLBACK.enqueueOn(this.listeners);
                return;
            default:
                throw new AssertionError();
        }
    }

    private void failed(final State state, final Throwable th) {
        String valueOf = String.valueOf(String.valueOf(state));
        String valueOf2 = String.valueOf(String.valueOf(th));
        new Callback<Listener>(new StringBuilder((valueOf.length() + 27) + valueOf2.length()).append("failed({from = ").append(valueOf).append(", cause = ").append(valueOf2).append("})").toString()) {
            void call(Listener listener) {
                listener.failed(state, th);
            }
        }.enqueueOn(this.listeners);
    }
}
