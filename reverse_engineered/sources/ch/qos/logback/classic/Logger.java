package ch.qos.logback.classic;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.util.LoggerNameUtil;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.AppenderAttachable;
import ch.qos.logback.core.spi.AppenderAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

public final class Logger implements AppenderAttachable<ILoggingEvent>, Serializable, org.slf4j.Logger, LocationAwareLogger {
    private static final int DEFAULT_CHILD_ARRAY_SIZE = 5;
    public static final String FQCN = Logger.class.getName();
    private static final long serialVersionUID = 5454405123156820674L;
    private transient AppenderAttachableImpl<ILoggingEvent> aai;
    private transient boolean additive = true;
    private transient List<Logger> childrenList;
    private transient int effectiveLevelInt;
    private transient Level level;
    final transient LoggerContext loggerContext;
    private String name;
    private transient Logger parent;

    Logger(String str, Logger logger, LoggerContext loggerContext) {
        this.name = str;
        this.parent = logger;
        this.loggerContext = loggerContext;
    }

    private int appendLoopOnAppenders(ILoggingEvent iLoggingEvent) {
        return this.aai != null ? this.aai.appendLoopOnAppenders(iLoggingEvent) : 0;
    }

    private void buildLoggingEventAndAppend(String str, Marker marker, Level level, String str2, Object[] objArr, Throwable th) {
        ILoggingEvent loggingEvent = new LoggingEvent(str, this, level, str2, th, objArr);
        loggingEvent.setMarker(marker);
        callAppenders(loggingEvent);
    }

    private FilterReply callTurboFilters(Marker marker, Level level) {
        return this.loggerContext.getTurboFilterChainDecision_0_3OrMore(marker, this, level, null, null, null);
    }

    private void filterAndLog_0_Or3Plus(String str, Marker marker, Level level, String str2, Object[] objArr, Throwable th) {
        FilterReply turboFilterChainDecision_0_3OrMore = this.loggerContext.getTurboFilterChainDecision_0_3OrMore(marker, this, level, str2, objArr, th);
        if (turboFilterChainDecision_0_3OrMore == FilterReply.NEUTRAL) {
            if (this.effectiveLevelInt > level.levelInt) {
                return;
            }
        } else if (turboFilterChainDecision_0_3OrMore == FilterReply.DENY) {
            return;
        }
        buildLoggingEventAndAppend(str, marker, level, str2, objArr, th);
    }

    private void filterAndLog_1(String str, Marker marker, Level level, String str2, Object obj, Throwable th) {
        FilterReply turboFilterChainDecision_1 = this.loggerContext.getTurboFilterChainDecision_1(marker, this, level, str2, obj, th);
        if (turboFilterChainDecision_1 == FilterReply.NEUTRAL) {
            if (this.effectiveLevelInt > level.levelInt) {
                return;
            }
        } else if (turboFilterChainDecision_1 == FilterReply.DENY) {
            return;
        }
        buildLoggingEventAndAppend(str, marker, level, str2, new Object[]{obj}, th);
    }

    private void filterAndLog_2(String str, Marker marker, Level level, String str2, Object obj, Object obj2, Throwable th) {
        FilterReply turboFilterChainDecision_2 = this.loggerContext.getTurboFilterChainDecision_2(marker, this, level, str2, obj, obj2, th);
        if (turboFilterChainDecision_2 == FilterReply.NEUTRAL) {
            if (this.effectiveLevelInt > level.levelInt) {
                return;
            }
        } else if (turboFilterChainDecision_2 == FilterReply.DENY) {
            return;
        }
        buildLoggingEventAndAppend(str, marker, level, str2, new Object[]{obj, obj2}, th);
    }

    private synchronized void handleParentLevelChange(int i) {
        if (this.level == null) {
            this.effectiveLevelInt = i;
            if (this.childrenList != null) {
                int size = this.childrenList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((Logger) this.childrenList.get(i2)).handleParentLevelChange(i);
                }
            }
        }
    }

    private boolean isRootLogger() {
        return this.parent == null;
    }

    private void localLevelReset() {
        this.effectiveLevelInt = 10000;
        if (isRootLogger()) {
            this.level = Level.DEBUG;
        } else {
            this.level = null;
        }
    }

    public synchronized void addAppender(Appender<ILoggingEvent> appender) {
        if (this.aai == null) {
            this.aai = new AppenderAttachableImpl();
        }
        this.aai.addAppender(appender);
    }

    public void callAppenders(ILoggingEvent iLoggingEvent) {
        int i = 0;
        for (Logger logger = this; logger != null; logger = logger.parent) {
            i += logger.appendLoopOnAppenders(iLoggingEvent);
            if (!logger.additive) {
                break;
            }
        }
        if (i == 0) {
            this.loggerContext.noAppenderDefinedWarning(this);
        }
    }

    Logger createChildByLastNamePart(String str) {
        if (LoggerNameUtil.getFirstSeparatorIndexOf(str) != -1) {
            throw new IllegalArgumentException("Child name [" + str + " passed as parameter, may not include [" + CoreConstants.DOT + "]");
        }
        if (this.childrenList == null) {
            this.childrenList = new ArrayList();
        }
        Logger logger = isRootLogger() ? new Logger(str, this, this.loggerContext) : new Logger(this.name + CoreConstants.DOT + str, this, this.loggerContext);
        this.childrenList.add(logger);
        logger.effectiveLevelInt = this.effectiveLevelInt;
        return logger;
    }

    Logger createChildByName(String str) {
        if (LoggerNameUtil.getSeparatorIndexOf(str, this.name.length() + 1) != -1) {
            throw new IllegalArgumentException("For logger [" + this.name + "] child name [" + str + " passed as parameter, may not include '.' after index" + (this.name.length() + 1));
        }
        if (this.childrenList == null) {
            this.childrenList = new ArrayList(5);
        }
        Logger logger = new Logger(str, this, this.loggerContext);
        this.childrenList.add(logger);
        logger.effectiveLevelInt = this.effectiveLevelInt;
        return logger;
    }

    public void debug(String str) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, str, null, null);
    }

    public void debug(String str, Object obj) {
        filterAndLog_1(FQCN, null, Level.DEBUG, str, obj, null);
    }

    public void debug(String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, null, Level.DEBUG, str, obj, obj2, null);
    }

    public void debug(String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, str, null, th);
    }

    public void debug(String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, str, objArr, null);
    }

    public void debug(Marker marker, String str) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, str, null, null);
    }

    public void debug(Marker marker, String str, Object obj) {
        filterAndLog_1(FQCN, marker, Level.DEBUG, str, obj, null);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, marker, Level.DEBUG, str, obj, obj2, null);
    }

    public void debug(Marker marker, String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, str, null, th);
    }

    public void debug(Marker marker, String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, str, objArr, null);
    }

    public void detachAndStopAllAppenders() {
        if (this.aai != null) {
            this.aai.detachAndStopAllAppenders();
        }
    }

    public boolean detachAppender(Appender<ILoggingEvent> appender) {
        return this.aai == null ? false : this.aai.detachAppender((Appender) appender);
    }

    public boolean detachAppender(String str) {
        return this.aai == null ? false : this.aai.detachAppender(str);
    }

    public void error(String str) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, str, null, null);
    }

    public void error(String str, Object obj) {
        filterAndLog_1(FQCN, null, Level.ERROR, str, obj, null);
    }

    public void error(String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, null, Level.ERROR, str, obj, obj2, null);
    }

    public void error(String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, str, null, th);
    }

    public void error(String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, str, objArr, null);
    }

    public void error(Marker marker, String str) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, str, null, null);
    }

    public void error(Marker marker, String str, Object obj) {
        filterAndLog_1(FQCN, marker, Level.ERROR, str, obj, null);
    }

    public void error(Marker marker, String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, marker, Level.ERROR, str, obj, obj2, null);
    }

    public void error(Marker marker, String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, str, null, th);
    }

    public void error(Marker marker, String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, str, objArr, null);
    }

    public Appender<ILoggingEvent> getAppender(String str) {
        return this.aai == null ? null : this.aai.getAppender(str);
    }

    Logger getChildByName(String str) {
        if (this.childrenList == null) {
            return null;
        }
        int size = this.childrenList.size();
        for (int i = 0; i < size; i++) {
            Logger logger = (Logger) this.childrenList.get(i);
            if (str.equals(logger.getName())) {
                return logger;
            }
        }
        return null;
    }

    public Level getEffectiveLevel() {
        return Level.toLevel(this.effectiveLevelInt);
    }

    int getEffectiveLevelInt() {
        return this.effectiveLevelInt;
    }

    public Level getLevel() {
        return this.level;
    }

    public LoggerContext getLoggerContext() {
        return this.loggerContext;
    }

    public String getName() {
        return this.name;
    }

    public void info(String str) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, str, null, null);
    }

    public void info(String str, Object obj) {
        filterAndLog_1(FQCN, null, Level.INFO, str, obj, null);
    }

    public void info(String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, null, Level.INFO, str, obj, obj2, null);
    }

    public void info(String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, str, null, th);
    }

    public void info(String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, str, objArr, null);
    }

    public void info(Marker marker, String str) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, str, null, null);
    }

    public void info(Marker marker, String str, Object obj) {
        filterAndLog_1(FQCN, marker, Level.INFO, str, obj, null);
    }

    public void info(Marker marker, String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, marker, Level.INFO, str, obj, obj2, null);
    }

    public void info(Marker marker, String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, str, null, th);
    }

    public void info(Marker marker, String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, str, objArr, null);
    }

    public boolean isAdditive() {
        return this.additive;
    }

    public boolean isAttached(Appender<ILoggingEvent> appender) {
        return this.aai == null ? false : this.aai.isAttached(appender);
    }

    public boolean isDebugEnabled() {
        return isDebugEnabled(null);
    }

    public boolean isDebugEnabled(Marker marker) {
        FilterReply callTurboFilters = callTurboFilters(marker, Level.DEBUG);
        if (callTurboFilters == FilterReply.NEUTRAL) {
            return this.effectiveLevelInt <= 10000;
        } else {
            if (callTurboFilters == FilterReply.DENY) {
                return false;
            }
            if (callTurboFilters == FilterReply.ACCEPT) {
                return true;
            }
            throw new IllegalStateException("Unknown FilterReply value: " + callTurboFilters);
        }
    }

    public boolean isEnabledFor(Level level) {
        return isEnabledFor(null, level);
    }

    public boolean isEnabledFor(Marker marker, Level level) {
        FilterReply callTurboFilters = callTurboFilters(marker, level);
        if (callTurboFilters == FilterReply.NEUTRAL) {
            return this.effectiveLevelInt <= level.levelInt;
        } else {
            if (callTurboFilters == FilterReply.DENY) {
                return false;
            }
            if (callTurboFilters == FilterReply.ACCEPT) {
                return true;
            }
            throw new IllegalStateException("Unknown FilterReply value: " + callTurboFilters);
        }
    }

    public boolean isErrorEnabled() {
        return isErrorEnabled(null);
    }

    public boolean isErrorEnabled(Marker marker) {
        FilterReply callTurboFilters = callTurboFilters(marker, Level.ERROR);
        if (callTurboFilters == FilterReply.NEUTRAL) {
            return this.effectiveLevelInt <= Level.ERROR_INT;
        } else {
            if (callTurboFilters == FilterReply.DENY) {
                return false;
            }
            if (callTurboFilters == FilterReply.ACCEPT) {
                return true;
            }
            throw new IllegalStateException("Unknown FilterReply value: " + callTurboFilters);
        }
    }

    public boolean isInfoEnabled() {
        return isInfoEnabled(null);
    }

    public boolean isInfoEnabled(Marker marker) {
        FilterReply callTurboFilters = callTurboFilters(marker, Level.INFO);
        if (callTurboFilters == FilterReply.NEUTRAL) {
            return this.effectiveLevelInt <= 20000;
        } else {
            if (callTurboFilters == FilterReply.DENY) {
                return false;
            }
            if (callTurboFilters == FilterReply.ACCEPT) {
                return true;
            }
            throw new IllegalStateException("Unknown FilterReply value: " + callTurboFilters);
        }
    }

    public boolean isTraceEnabled() {
        return isTraceEnabled(null);
    }

    public boolean isTraceEnabled(Marker marker) {
        FilterReply callTurboFilters = callTurboFilters(marker, Level.TRACE);
        if (callTurboFilters == FilterReply.NEUTRAL) {
            return this.effectiveLevelInt <= 5000;
        } else {
            if (callTurboFilters == FilterReply.DENY) {
                return false;
            }
            if (callTurboFilters == FilterReply.ACCEPT) {
                return true;
            }
            throw new IllegalStateException("Unknown FilterReply value: " + callTurboFilters);
        }
    }

    public boolean isWarnEnabled() {
        return isWarnEnabled(null);
    }

    public boolean isWarnEnabled(Marker marker) {
        FilterReply callTurboFilters = callTurboFilters(marker, Level.WARN);
        if (callTurboFilters == FilterReply.NEUTRAL) {
            return this.effectiveLevelInt <= 30000;
        } else {
            if (callTurboFilters == FilterReply.DENY) {
                return false;
            }
            if (callTurboFilters == FilterReply.ACCEPT) {
                return true;
            }
            throw new IllegalStateException("Unknown FilterReply value: " + callTurboFilters);
        }
    }

    public Iterator<Appender<ILoggingEvent>> iteratorForAppenders() {
        return this.aai == null ? Collections.EMPTY_LIST.iterator() : this.aai.iteratorForAppenders();
    }

    public void log(Marker marker, String str, int i, String str2, Object[] objArr, Throwable th) {
        filterAndLog_0_Or3Plus(str, marker, Level.fromLocationAwareLoggerInteger(i), str2, objArr, th);
    }

    protected Object readResolve() throws ObjectStreamException {
        return LoggerFactory.getLogger(getName());
    }

    void recursiveReset() {
        detachAndStopAllAppenders();
        localLevelReset();
        this.additive = true;
        if (this.childrenList != null) {
            for (Logger recursiveReset : this.childrenList) {
                recursiveReset.recursiveReset();
            }
        }
    }

    public void setAdditive(boolean z) {
        this.additive = z;
    }

    public synchronized void setLevel(Level level) {
        if (this.level != level) {
            if (level == null) {
                if (isRootLogger()) {
                    throw new IllegalArgumentException("The level of the root logger cannot be set to null");
                }
            }
            this.level = level;
            if (level == null) {
                this.effectiveLevelInt = this.parent.effectiveLevelInt;
                level = this.parent.getEffectiveLevel();
            } else {
                this.effectiveLevelInt = level.levelInt;
            }
            if (this.childrenList != null) {
                int size = this.childrenList.size();
                for (int i = 0; i < size; i++) {
                    ((Logger) this.childrenList.get(i)).handleParentLevelChange(this.effectiveLevelInt);
                }
            }
            this.loggerContext.fireOnLevelChange(this, level);
        }
    }

    public String toString() {
        return "Logger[" + this.name + "]";
    }

    public void trace(String str) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, str, null, null);
    }

    public void trace(String str, Object obj) {
        filterAndLog_1(FQCN, null, Level.TRACE, str, obj, null);
    }

    public void trace(String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, null, Level.TRACE, str, obj, obj2, null);
    }

    public void trace(String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, str, null, th);
    }

    public void trace(String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, str, objArr, null);
    }

    public void trace(Marker marker, String str) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, str, null, null);
    }

    public void trace(Marker marker, String str, Object obj) {
        filterAndLog_1(FQCN, marker, Level.TRACE, str, obj, null);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, marker, Level.TRACE, str, obj, obj2, null);
    }

    public void trace(Marker marker, String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, str, null, th);
    }

    public void trace(Marker marker, String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, str, objArr, null);
    }

    public void warn(String str) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, str, null, null);
    }

    public void warn(String str, Object obj) {
        filterAndLog_1(FQCN, null, Level.WARN, str, obj, null);
    }

    public void warn(String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, null, Level.WARN, str, obj, obj2, null);
    }

    public void warn(String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, str, null, th);
    }

    public void warn(String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, str, objArr, null);
    }

    public void warn(Marker marker, String str) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, str, null, null);
    }

    public void warn(Marker marker, String str, Object obj) {
        filterAndLog_1(FQCN, marker, Level.WARN, str, obj, null);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2) {
        filterAndLog_2(FQCN, marker, Level.WARN, str, obj, obj2, null);
    }

    public void warn(Marker marker, String str, Throwable th) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, str, null, th);
    }

    public void warn(Marker marker, String str, Object[] objArr) {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, str, objArr, null);
    }
}
