package ch.qos.logback.classic.log4j;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.helpers.Transform;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class XMLLayout extends LayoutBase<ILoggingEvent> {
    private static final int DEFAULT_SIZE = 256;
    private static final int UPPER_LIMIT = 2048;
    private StringBuilder buf = new StringBuilder(256);
    private boolean locationInfo = false;
    private boolean properties = false;

    public String doLayout(ILoggingEvent iLoggingEvent) {
        if (this.buf.capacity() > 2048) {
            this.buf = new StringBuilder(256);
        } else {
            this.buf.setLength(0);
        }
        this.buf.append("<log4j:event logger=\"");
        this.buf.append(iLoggingEvent.getLoggerName());
        this.buf.append("\"\r\n");
        this.buf.append("             timestamp=\"");
        this.buf.append(iLoggingEvent.getTimeStamp());
        this.buf.append("\" level=\"");
        this.buf.append(iLoggingEvent.getLevel());
        this.buf.append("\" thread=\"");
        this.buf.append(iLoggingEvent.getThreadName());
        this.buf.append("\">\r\n");
        this.buf.append("  <log4j:message><![CDATA[");
        Transform.appendEscapingCDATA(this.buf, iLoggingEvent.getFormattedMessage());
        this.buf.append("]]></log4j:message>\r\n");
        IThrowableProxy throwableProxy = iLoggingEvent.getThrowableProxy();
        if (throwableProxy != null) {
            StackTraceElementProxy[] stackTraceElementProxyArray = throwableProxy.getStackTraceElementProxyArray();
            this.buf.append("  <log4j:throwable><![CDATA[");
            for (StackTraceElementProxy stackTraceElementProxy : stackTraceElementProxyArray) {
                this.buf.append('\t');
                this.buf.append(stackTraceElementProxy.toString());
                this.buf.append("\r\n");
            }
            this.buf.append("]]></log4j:throwable>\r\n");
        }
        if (this.locationInfo) {
            StackTraceElement[] callerData = iLoggingEvent.getCallerData();
            if (callerData != null && callerData.length > 0) {
                StackTraceElement stackTraceElement = callerData[0];
                this.buf.append("  <log4j:locationInfo class=\"");
                this.buf.append(stackTraceElement.getClassName());
                this.buf.append("\"\r\n");
                this.buf.append("                      method=\"");
                this.buf.append(Transform.escapeTags(stackTraceElement.getMethodName()));
                this.buf.append("\" file=\"");
                this.buf.append(stackTraceElement.getFileName());
                this.buf.append("\" line=\"");
                this.buf.append(stackTraceElement.getLineNumber());
                this.buf.append("\"/>\r\n");
            }
        }
        if (getProperties()) {
            Map mDCPropertyMap = iLoggingEvent.getMDCPropertyMap();
            if (!(mDCPropertyMap == null || mDCPropertyMap.size() == 0)) {
                Set<Entry> entrySet = mDCPropertyMap.entrySet();
                this.buf.append("  <log4j:properties>");
                for (Entry entry : entrySet) {
                    this.buf.append("\r\n    <log4j:data");
                    this.buf.append(" name='" + Transform.escapeTags((String) entry.getKey()) + "'");
                    this.buf.append(" value='" + Transform.escapeTags((String) entry.getValue()) + "'");
                    this.buf.append(" />");
                }
                this.buf.append("\r\n  </log4j:properties>");
            }
        }
        this.buf.append("\r\n</log4j:event>\r\n\r\n");
        return this.buf.toString();
    }

    public String getContentType() {
        return "text/xml";
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public boolean getProperties() {
        return this.properties;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public void setProperties(boolean z) {
        this.properties = z;
    }

    public void start() {
        super.start();
    }
}
