package ch.qos.logback.core;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.WarnStatus;
import java.util.List;

public abstract class AppenderBase<E> extends ContextAwareBase implements Appender<E> {
    static final int ALLOWED_REPEATS = 5;
    private int exceptionCount = 0;
    private FilterAttachableImpl<E> fai = new FilterAttachableImpl();
    private boolean guard = false;
    protected String name;
    protected boolean started = false;
    private int statusRepeatCount = 0;

    public void addFilter(Filter<E> filter) {
        this.fai.addFilter(filter);
    }

    protected abstract void append(E e);

    public void clearAllFilters() {
        this.fai.clearAllFilters();
    }

    public synchronized void doAppend(E e) {
        if (!this.guard) {
            try {
                this.guard = true;
                if (!this.started) {
                    int i = this.statusRepeatCount;
                    this.statusRepeatCount = i + 1;
                    if (i < 5) {
                        addStatus(new WarnStatus("Attempted to append to non started appender [" + this.name + "].", this));
                    }
                    this.guard = false;
                } else if (getFilterChainDecision(e) == FilterReply.DENY) {
                    this.guard = false;
                } else {
                    append(e);
                    this.guard = false;
                }
            } catch (Throwable e2) {
                int i2 = this.exceptionCount;
                this.exceptionCount = i2 + 1;
                if (i2 < 5) {
                    addError("Appender [" + this.name + "] failed to append.", e2);
                }
                this.guard = false;
            } catch (Throwable th) {
                this.guard = false;
            }
        }
    }

    public List<Filter<E>> getCopyOfAttachedFiltersList() {
        return this.fai.getCopyOfAttachedFiltersList();
    }

    public FilterReply getFilterChainDecision(E e) {
        return this.fai.getFilterChainDecision(e);
    }

    public String getName() {
        return this.name;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }

    public String toString() {
        return getClass().getName() + "[" + this.name + "]";
    }
}
