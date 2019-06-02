package ch.qos.logback.classic.net;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.net.AbstractSSLSocketAppender;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.net.InetAddress;

public class SSLSocketAppender extends AbstractSSLSocketAppender<ILoggingEvent> {
    private boolean includeCallerData;
    private final PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();

    @Deprecated
    public SSLSocketAppender(String str, int i) {
        super(str, i);
    }

    @Deprecated
    public SSLSocketAppender(InetAddress inetAddress, int i) {
        super(inetAddress.getHostAddress(), i);
    }

    public PreSerializationTransformer<ILoggingEvent> getPST() {
        return this.pst;
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
