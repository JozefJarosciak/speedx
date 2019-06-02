package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.util.Duration;

public abstract class SiftingAppenderBase<E> extends AppenderBase<E> {
    AppenderFactory<E> appenderFactory;
    protected AppenderTracker<E> appenderTracker;
    Discriminator<E> discriminator;
    int maxAppenderCount = Integer.MAX_VALUE;
    Duration timeout = new Duration(1800000);

    protected void append(E e) {
        if (isStarted()) {
            String discriminatingValue = this.discriminator.getDiscriminatingValue(e);
            long timestamp = getTimestamp(e);
            Appender appender = (Appender) this.appenderTracker.getOrCreate(discriminatingValue, timestamp);
            if (eventMarksEndOfLife(e)) {
                this.appenderTracker.endOfLife(discriminatingValue);
            }
            this.appenderTracker.removeStaleComponents(timestamp);
            appender.doAppend(e);
        }
    }

    protected abstract boolean eventMarksEndOfLife(E e);

    public AppenderTracker<E> getAppenderTracker() {
        return this.appenderTracker;
    }

    public Discriminator<E> getDiscriminator() {
        return this.discriminator;
    }

    public String getDiscriminatorKey() {
        return this.discriminator != null ? this.discriminator.getKey() : null;
    }

    public int getMaxAppenderCount() {
        return this.maxAppenderCount;
    }

    public Duration getTimeout() {
        return this.timeout;
    }

    protected abstract long getTimestamp(E e);

    public void setAppenderFactory(AppenderFactory<E> appenderFactory) {
        this.appenderFactory = appenderFactory;
    }

    public void setDiscriminator(Discriminator<E> discriminator) {
        this.discriminator = discriminator;
    }

    public void setMaxAppenderCount(int i) {
        this.maxAppenderCount = i;
    }

    public void setTimeout(Duration duration) {
        this.timeout = duration;
    }

    public void start() {
        int i = 0;
        if (this.discriminator == null) {
            addError("Missing discriminator. Aborting");
            i = 1;
        }
        if (!this.discriminator.isStarted()) {
            addError("Discriminator has not started successfully. Aborting");
            i++;
        }
        if (this.appenderFactory == null) {
            addError("AppenderFactory has not been set. Aborting");
            i++;
        } else {
            this.appenderTracker = new AppenderTracker(this.context, this.appenderFactory);
            this.appenderTracker.setMaxComponents(this.maxAppenderCount);
            this.appenderTracker.setTimeout(this.timeout.getMilliseconds());
        }
        if (i == 0) {
            super.start();
        }
    }

    public void stop() {
        for (Appender stop : this.appenderTracker.allComponents()) {
            stop.stop();
        }
    }
}
