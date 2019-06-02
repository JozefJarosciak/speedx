package ch.qos.logback.classic;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AsyncAppenderBase;

public class AsyncAppender extends AsyncAppenderBase<ILoggingEvent> {
    boolean includeCallerData = false;

    protected boolean isDiscardable(ILoggingEvent iLoggingEvent) {
        return iLoggingEvent.getLevel().toInt() <= 20000;
    }

    public boolean isIncludeCallerData() {
        return this.includeCallerData;
    }

    protected void preprocess(ILoggingEvent iLoggingEvent) {
        iLoggingEvent.prepareForDeferredProcessing();
        if (this.includeCallerData) {
            iLoggingEvent.getCallerData();
        }
    }

    public void setIncludeCallerData(boolean z) {
        this.includeCallerData = z;
    }
}
