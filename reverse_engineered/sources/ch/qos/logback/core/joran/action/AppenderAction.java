package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.OptionHelper;
import java.util.HashMap;
import org.xml.sax.Attributes;

public class AppenderAction<E> extends Action {
    Appender<E> appender;
    private boolean inError = false;

    private void warnDeprecated(String str) {
        if (str.equals("ch.qos.logback.core.ConsoleAppender")) {
            addWarn("ConsoleAppender is deprecated for LogcatAppender");
        }
    }

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        this.appender = null;
        this.inError = false;
        String value = attributes.getValue(Action.CLASS_ATTRIBUTE);
        if (OptionHelper.isEmpty(value)) {
            addError("Missing class name for appender. Near [" + str + "] line " + getLineNumber(interpretationContext));
            this.inError = true;
            return;
        }
        try {
            addInfo("About to instantiate appender of type [" + value + "]");
            warnDeprecated(value);
            this.appender = (Appender) OptionHelper.instantiateByClassName(value, Appender.class, this.context);
            this.appender.setContext(this.context);
            String subst = interpretationContext.subst(attributes.getValue("name"));
            if (OptionHelper.isEmpty(subst)) {
                addWarn("No appender name given for appender of type " + value + "].");
            } else {
                this.appender.setName(subst);
                addInfo("Naming appender as [" + subst + "]");
            }
            ((HashMap) interpretationContext.getObjectMap().get(ActionConst.APPENDER_BAG)).put(subst, this.appender);
            interpretationContext.pushObject(this.appender);
        } catch (Throwable e) {
            this.inError = true;
            addError("Could not create an Appender of type [" + value + "].", e);
            throw new ActionException(e);
        }
    }

    public void end(InterpretationContext interpretationContext, String str) {
        if (!this.inError) {
            if (this.appender instanceof LifeCycle) {
                this.appender.start();
            }
            if (interpretationContext.peekObject() != this.appender) {
                addWarn("The object at the of the stack is not the appender named [" + this.appender.getName() + "] pushed earlier.");
            } else {
                interpretationContext.popObject();
            }
        }
    }
}
