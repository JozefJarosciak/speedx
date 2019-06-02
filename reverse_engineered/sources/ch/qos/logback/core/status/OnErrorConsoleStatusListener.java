package ch.qos.logback.core.status;

import java.io.PrintStream;

public class OnErrorConsoleStatusListener extends OnPrintStreamStatusListenerBase {
    public /* bridge */ /* synthetic */ void addStatusEvent(Status status) {
        super.addStatusEvent(status);
    }

    protected PrintStream getPrintStream() {
        return System.err;
    }

    public /* bridge */ /* synthetic */ long getRetrospective() {
        return super.getRetrospective();
    }

    public /* bridge */ /* synthetic */ boolean isStarted() {
        return super.isStarted();
    }

    public /* bridge */ /* synthetic */ void setRetrospective(long j) {
        super.setRetrospective(j);
    }

    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
