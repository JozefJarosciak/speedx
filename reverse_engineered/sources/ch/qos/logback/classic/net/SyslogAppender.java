package ch.qos.logback.classic.net;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.pattern.SyslogStartConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.util.LevelToSyslogSeverity;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.net.SyslogAppenderBase;
import java.io.IOException;
import java.io.OutputStream;

public class SyslogAppender extends SyslogAppenderBase<ILoggingEvent> {
    public static final String DEFAULT_STACKTRACE_PATTERN = "\t";
    public static final String DEFAULT_SUFFIX_PATTERN = "[%thread] %logger %msg";
    PatternLayout stackTraceLayout = new PatternLayout();
    String stackTracePattern = DEFAULT_STACKTRACE_PATTERN;
    boolean throwableExcluded = false;

    private void handleThrowableFirstLine(OutputStream outputStream, IThrowableProxy iThrowableProxy, String str, boolean z) throws IOException {
        StringBuilder append = new StringBuilder().append(str);
        if (!z) {
            append.append(CoreConstants.CAUSED_BY);
        }
        append.append(iThrowableProxy.getClassName()).append(": ").append(iThrowableProxy.getMessage());
        outputStream.write(append.toString().getBytes());
        outputStream.flush();
    }

    private void setupStackTraceLayout() {
        this.stackTraceLayout.getInstanceConverterMap().put("syslogStart", SyslogStartConverter.class.getName());
        this.stackTraceLayout.setPattern(getPrefixPattern() + this.stackTracePattern);
        this.stackTraceLayout.setContext(getContext());
        this.stackTraceLayout.start();
    }

    public Layout<ILoggingEvent> buildLayout() {
        Layout patternLayout = new PatternLayout();
        patternLayout.getInstanceConverterMap().put("syslogStart", SyslogStartConverter.class.getName());
        if (this.suffixPattern == null) {
            this.suffixPattern = DEFAULT_SUFFIX_PATTERN;
        }
        patternLayout.setPattern(getPrefixPattern() + this.suffixPattern);
        patternLayout.setContext(getContext());
        patternLayout.start();
        return patternLayout;
    }

    String getPrefixPattern() {
        return "%syslogStart{" + getFacility() + "}%nopex{}";
    }

    public int getSeverityForEvent(Object obj) {
        return LevelToSyslogSeverity.convert((ILoggingEvent) obj);
    }

    public String getStackTracePattern() {
        return this.stackTracePattern;
    }

    public boolean isThrowableExcluded() {
        return this.throwableExcluded;
    }

    protected void postProcess(Object obj, OutputStream outputStream) {
        if (!this.throwableExcluded) {
            ILoggingEvent iLoggingEvent = (ILoggingEvent) obj;
            IThrowableProxy throwableProxy = iLoggingEvent.getThrowableProxy();
            if (throwableProxy != null) {
                String doLayout = this.stackTraceLayout.doLayout(iLoggingEvent);
                boolean z = true;
                while (throwableProxy != null) {
                    StackTraceElementProxy[] stackTraceElementProxyArray = throwableProxy.getStackTraceElementProxyArray();
                    try {
                        handleThrowableFirstLine(outputStream, throwableProxy, doLayout, z);
                        for (Object obj2 : stackTraceElementProxyArray) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(doLayout).append(obj2);
                            outputStream.write(stringBuilder.toString().getBytes());
                            outputStream.flush();
                        }
                        throwableProxy = throwableProxy.getCause();
                        z = false;
                    } catch (IOException e) {
                        return;
                    }
                }
            }
        }
    }

    public void setStackTracePattern(String str) {
        this.stackTracePattern = str;
    }

    public void setThrowableExcluded(boolean z) {
        this.throwableExcluded = z;
    }

    boolean stackTraceHeaderLine(StringBuilder stringBuilder, boolean z) {
        return false;
    }

    public void start() {
        super.start();
        setupStackTraceLayout();
    }
}
