package ch.qos.logback.core.status;

import ch.qos.logback.core.Context;
import java.io.PrintStream;

public class OnConsoleStatusListener extends OnPrintStreamStatusListenerBase {
    public static void addNewInstanceToContext(Context context) {
        Object onConsoleStatusListener = new OnConsoleStatusListener();
        onConsoleStatusListener.setContext(context);
        if (context.getStatusManager().addUniquely(onConsoleStatusListener, context)) {
            onConsoleStatusListener.start();
        }
    }

    public /* bridge */ /* synthetic */ void addStatusEvent(Status status) {
        super.addStatusEvent(status);
    }

    protected PrintStream getPrintStream() {
        return System.out;
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
