package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.helpers.NOPAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import ch.qos.logback.core.spi.ContextAwareImpl;

public class AppenderTracker<E> extends AbstractComponentTracker<Appender<E>> {
    final AppenderFactory<E> appenderFactory;
    final Context context;
    final ContextAwareImpl contextAware;
    int nopaWarningCount = 0;

    public AppenderTracker(Context context, AppenderFactory<E> appenderFactory) {
        this.context = context;
        this.appenderFactory = appenderFactory;
        this.contextAware = new ContextAwareImpl(context, this);
    }

    private NOPAppender<E> buildNOPAppender(String str) {
        if (this.nopaWarningCount < 4) {
            this.nopaWarningCount++;
            this.contextAware.addError("Building NOPAppender for discriminating value [" + str + "]");
        }
        NOPAppender<E> nOPAppender = new NOPAppender();
        nOPAppender.setContext(this.context);
        nOPAppender.start();
        return nOPAppender;
    }

    protected Appender<E> buildComponent(String str) {
        Appender<E> appender = null;
        try {
            appender = this.appenderFactory.buildAppender(this.context, str);
        } catch (JoranException e) {
            this.contextAware.addError("Error while building appender with discriminating value [" + str + "]");
        }
        return appender == null ? buildNOPAppender(str) : appender;
    }

    protected boolean isComponentStale(Appender<E> appender) {
        return !appender.isStarted();
    }

    protected void processPriorToRemoval(Appender<E> appender) {
        appender.stop();
    }
}
