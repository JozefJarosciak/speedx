package org.slf4j.impl;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import ch.qos.logback.core.status.StatusUtil;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder {
    private static Object KEY = new Object();
    static final String NULL_CS_URL = "http://logback.qos.ch/codes.html#null_CS";
    public static String REQUESTED_API_VERSION = "1.6";
    private static StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    private final ContextSelectorStaticBinder contextSelectorBinder = ContextSelectorStaticBinder.getSingleton();
    private LoggerContext defaultLoggerContext = new LoggerContext();
    private boolean initialized = false;

    static {
        SINGLETON.init();
    }

    private StaticLoggerBinder() {
        this.defaultLoggerContext.setName("default");
    }

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    static void reset() {
        SINGLETON = new StaticLoggerBinder();
        SINGLETON.init();
    }

    public ILoggerFactory getLoggerFactory() {
        if (!this.initialized) {
            return this.defaultLoggerContext;
        }
        if (this.contextSelectorBinder.getContextSelector() != null) {
            return this.contextSelectorBinder.getContextSelector().getLoggerContext();
        }
        throw new IllegalStateException("contextSelector cannot be null. See also http://logback.qos.ch/codes.html#null_CS");
    }

    public String getLoggerFactoryClassStr() {
        return this.contextSelectorBinder.getClass().getName();
    }

    void init() {
        try {
            new ContextInitializer(this.defaultLoggerContext).autoConfig();
        } catch (Throwable e) {
            Util.report("Failed to auto configure default logger context", e);
        } catch (Throwable e2) {
            Util.report("Failed to instantiate [" + LoggerContext.class.getName() + "]", e2);
            return;
        }
        if (!StatusUtil.contextHasStatusListener(this.defaultLoggerContext)) {
            StatusPrinter.printInCaseOfErrorsOrWarnings(this.defaultLoggerContext);
        }
        this.contextSelectorBinder.init(this.defaultLoggerContext, KEY);
        this.initialized = true;
    }
}
