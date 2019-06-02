package ch.qos.logback.classic.net;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

public abstract class ReceiverBase extends ContextAwareBase implements LifeCycle {
    private boolean started;

    protected abstract Runnable getRunnableTask();

    public final boolean isStarted() {
        return this.started;
    }

    protected abstract void onStop();

    protected abstract boolean shouldStart();

    public final void start() {
        if (!isStarted()) {
            if (getContext() == null) {
                throw new IllegalStateException("context not set");
            } else if (shouldStart()) {
                getContext().getExecutorService().execute(getRunnableTask());
                this.started = true;
            }
        }
    }

    public final void stop() {
        if (isStarted()) {
            try {
                onStop();
            } catch (Throwable e) {
                addError("on stop: " + e, e);
            }
            this.started = false;
        }
    }
}
