package ch.qos.logback.core.status;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.PrintStream;

abstract class OnPrintStreamStatusListenerBase extends ContextAwareBase implements LifeCycle, StatusListener {
    static final long DEFAULT_RETROSPECTIVE = 300;
    boolean isStarted = false;
    long retrospective = DEFAULT_RETROSPECTIVE;

    OnPrintStreamStatusListenerBase() {
    }

    private void print(Status status) {
        StringBuilder stringBuilder = new StringBuilder();
        StatusPrinter.buildStr(stringBuilder, "", status);
        getPrintStream().print(stringBuilder);
    }

    private void retrospectivePrint() {
        if (this.context != null) {
            long currentTimeMillis = System.currentTimeMillis();
            for (Status status : this.context.getStatusManager().getCopyOfStatusList()) {
                if (currentTimeMillis - status.getDate().longValue() < this.retrospective) {
                    print(status);
                }
            }
        }
    }

    public void addStatusEvent(Status status) {
        if (this.isStarted) {
            print(status);
        }
    }

    protected abstract PrintStream getPrintStream();

    public long getRetrospective() {
        return this.retrospective;
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public void setRetrospective(long j) {
        this.retrospective = j;
    }

    public void start() {
        this.isStarted = true;
        if (this.retrospective > 0) {
            retrospectivePrint();
        }
    }

    public void stop() {
        this.isStarted = false;
    }
}
