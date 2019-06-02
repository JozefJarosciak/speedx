package ch.qos.logback.core.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.status.WarnStatus;

public class ContextAwareImpl implements ContextAware {
    protected Context context;
    private int noContextWarning = 0;
    final Object origin;

    public ContextAwareImpl(Context context, Object obj) {
        this.context = context;
        this.origin = obj;
    }

    public void addError(String str) {
        addStatus(new ErrorStatus(str, getOrigin()));
    }

    public void addError(String str, Throwable th) {
        addStatus(new ErrorStatus(str, getOrigin(), th));
    }

    public void addInfo(String str) {
        addStatus(new InfoStatus(str, getOrigin()));
    }

    public void addInfo(String str, Throwable th) {
        addStatus(new InfoStatus(str, getOrigin(), th));
    }

    public void addStatus(Status status) {
        if (this.context == null) {
            int i = this.noContextWarning;
            this.noContextWarning = i + 1;
            if (i == 0) {
                System.out.println("LOGBACK: No context given for " + this);
                return;
            }
            return;
        }
        StatusManager statusManager = this.context.getStatusManager();
        if (statusManager != null) {
            statusManager.add(status);
        }
    }

    public void addWarn(String str) {
        addStatus(new WarnStatus(str, getOrigin()));
    }

    public void addWarn(String str, Throwable th) {
        addStatus(new WarnStatus(str, getOrigin(), th));
    }

    public Context getContext() {
        return this.context;
    }

    protected Object getOrigin() {
        return this.origin;
    }

    public StatusManager getStatusManager() {
        return this.context == null ? null : this.context.getStatusManager();
    }

    public void setContext(Context context) {
        if (this.context == null) {
            this.context = context;
        } else if (this.context != context) {
            throw new IllegalStateException("Context has been already set");
        }
    }
}
