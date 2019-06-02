package ch.qos.logback.classic.boolex;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

public class OnErrorEvaluator extends EventEvaluatorBase<ILoggingEvent> {
    public boolean evaluate(ILoggingEvent iLoggingEvent) throws NullPointerException, EvaluationException {
        return iLoggingEvent.getLevel().levelInt >= Level.ERROR_INT;
    }
}
