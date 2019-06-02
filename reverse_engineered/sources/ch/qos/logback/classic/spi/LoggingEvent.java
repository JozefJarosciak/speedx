package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.MDCAdapter;

public class LoggingEvent implements ILoggingEvent {
    private static final Map<String, String> CACHED_NULL_MAP = new HashMap();
    private transient Object[] argumentArray;
    private StackTraceElement[] callerDataArray;
    transient String formattedMessage;
    transient String fqnOfLoggerClass;
    private transient Level level;
    private LoggerContext loggerContext;
    private LoggerContextVO loggerContextVO;
    private String loggerName;
    private Marker marker;
    private Map<String, String> mdcPropertyMap;
    private String message;
    private String threadName;
    private ThrowableProxy throwableProxy;
    private long timeStamp;

    public LoggingEvent(String str, Logger logger, Level level, String str2, Throwable th, Object[] objArr) {
        this.fqnOfLoggerClass = str;
        this.loggerName = logger.getName();
        this.loggerContext = logger.getLoggerContext();
        this.loggerContextVO = this.loggerContext.getLoggerContextRemoteView();
        this.level = level;
        this.message = str2;
        this.argumentArray = objArr;
        if (th == null) {
            th = extractThrowableAnRearrangeArguments(objArr);
        }
        if (th != null) {
            this.throwableProxy = new ThrowableProxy(th);
            if (logger.getLoggerContext().isPackagingDataEnabled()) {
                this.throwableProxy.calculatePackagingData();
            }
        }
        this.timeStamp = System.currentTimeMillis();
    }

    private Throwable extractThrowableAnRearrangeArguments(Object[] objArr) {
        Throwable extractThrowable = EventArgUtil.extractThrowable(objArr);
        if (EventArgUtil.successfulExtraction(extractThrowable)) {
            this.argumentArray = EventArgUtil.trimmedCopy(objArr);
        }
        return extractThrowable;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        throw new UnsupportedOperationException(getClass() + " does not support serialization. " + "Use LoggerEventVO instance instead. See also LoggerEventVO.build method.");
    }

    public Object[] getArgumentArray() {
        return this.argumentArray;
    }

    public StackTraceElement[] getCallerData() {
        if (this.callerDataArray == null) {
            this.callerDataArray = CallerData.extract(new Throwable(), this.fqnOfLoggerClass, this.loggerContext.getMaxCallerDataDepth(), this.loggerContext.getFrameworkPackages());
        }
        return this.callerDataArray;
    }

    public long getContextBirthTime() {
        return this.loggerContextVO.getBirthTime();
    }

    public String getFormattedMessage() {
        if (this.formattedMessage != null) {
            return this.formattedMessage;
        }
        if (this.argumentArray != null) {
            this.formattedMessage = MessageFormatter.arrayFormat(this.message, this.argumentArray).getMessage();
        } else {
            this.formattedMessage = this.message;
        }
        return this.formattedMessage;
    }

    public Level getLevel() {
        return this.level;
    }

    public LoggerContextVO getLoggerContextVO() {
        return this.loggerContextVO;
    }

    public String getLoggerName() {
        return this.loggerName;
    }

    public Map<String, String> getMDCPropertyMap() {
        if (this.mdcPropertyMap == null) {
            MDCAdapter mDCAdapter = MDC.getMDCAdapter();
            if (mDCAdapter instanceof LogbackMDCAdapter) {
                this.mdcPropertyMap = ((LogbackMDCAdapter) mDCAdapter).getPropertyMap();
            } else {
                this.mdcPropertyMap = mDCAdapter.getCopyOfContextMap();
            }
        }
        if (this.mdcPropertyMap == null) {
            this.mdcPropertyMap = CACHED_NULL_MAP;
        }
        return this.mdcPropertyMap;
    }

    public Marker getMarker() {
        return this.marker;
    }

    public Map<String, String> getMdc() {
        return getMDCPropertyMap();
    }

    public String getMessage() {
        return this.message;
    }

    public String getThreadName() {
        if (this.threadName == null) {
            this.threadName = Thread.currentThread().getName();
        }
        return this.threadName;
    }

    public IThrowableProxy getThrowableProxy() {
        return this.throwableProxy;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public boolean hasCallerData() {
        return this.callerDataArray != null;
    }

    public void prepareForDeferredProcessing() {
        getFormattedMessage();
        getThreadName();
        getMDCPropertyMap();
    }

    public void setArgumentArray(Object[] objArr) {
        if (this.argumentArray != null) {
            throw new IllegalStateException("argArray has been already set");
        }
        this.argumentArray = objArr;
    }

    public void setCallerData(StackTraceElement[] stackTraceElementArr) {
        this.callerDataArray = stackTraceElementArr;
    }

    public void setLevel(Level level) {
        if (this.level != null) {
            throw new IllegalStateException("The level has been already set for this event.");
        }
        this.level = level;
    }

    public void setLoggerContextRemoteView(LoggerContextVO loggerContextVO) {
        this.loggerContextVO = loggerContextVO;
    }

    public void setLoggerName(String str) {
        this.loggerName = str;
    }

    public void setMDCPropertyMap(Map<String, String> map) {
        if (this.mdcPropertyMap != null) {
            throw new IllegalStateException("The MDCPropertyMap has been already set for this event.");
        }
        this.mdcPropertyMap = map;
    }

    public void setMarker(Marker marker) {
        if (this.marker != null) {
            throw new IllegalStateException("The marker has been already set for this event.");
        }
        this.marker = marker;
    }

    public void setMessage(String str) {
        if (this.message != null) {
            throw new IllegalStateException("The message for this event has been set already.");
        }
        this.message = str;
    }

    public void setThreadName(String str) throws IllegalStateException {
        if (this.threadName != null) {
            throw new IllegalStateException("threadName has been already set");
        }
        this.threadName = str;
    }

    public void setThrowableProxy(ThrowableProxy throwableProxy) {
        if (this.throwableProxy != null) {
            throw new IllegalStateException("ThrowableProxy has been already set.");
        }
        this.throwableProxy = throwableProxy;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        stringBuilder.append(this.level).append("] ");
        stringBuilder.append(getFormattedMessage());
        return stringBuilder.toString();
    }
}
