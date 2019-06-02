package ch.qos.logback.classic.net;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.net.AbstractSocketAppender;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.net.InetAddress;

public class SocketAppender extends AbstractSocketAppender<ILoggingEvent> {
    private static final PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();
    private boolean includeCallerData = false;

    @Deprecated
    public SocketAppender(String str, int i) {
        super(str, i);
    }

    @Deprecated
    public SocketAppender(InetAddress inetAddress, int i) {
        super(inetAddress.getHostAddress(), i);
    }

    public PreSerializationTransformer<ILoggingEvent> getPST() {
        return pst;
    }

    protected void postProcessEvent(ILoggingEvent iLoggingEvent) {
        if (this.includeCallerData) {
            iLoggingEvent.getCallerData();
        }
    }

    public void setIncludeCallerData(boolean z) {
        this.includeCallerData = z;
    }
}
