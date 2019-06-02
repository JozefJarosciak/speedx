package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ActionConst;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class LoggerAction extends Action {
    public static final String LEVEL_ATTRIBUTE = "level";
    boolean inError = false;
    Logger logger;

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
        this.inError = false;
        this.logger = null;
        LoggerContext loggerContext = (LoggerContext) this.context;
        String subst = interpretationContext.subst(attributes.getValue("name"));
        if (OptionHelper.isEmpty(subst)) {
            this.inError = true;
            addError("No 'name' attribute in element " + str + ", around " + getLineColStr(interpretationContext));
            return;
        }
        this.logger = loggerContext.getLogger(subst);
        String subst2 = interpretationContext.subst(attributes.getValue("level"));
        if (!OptionHelper.isEmpty(subst2)) {
            if (ActionConst.INHERITED.equalsIgnoreCase(subst2) || ActionConst.NULL.equalsIgnoreCase(subst2)) {
                addInfo("Setting level of logger [" + subst + "] to null, i.e. INHERITED");
                this.logger.setLevel(null);
            } else {
                Level toLevel = Level.toLevel(subst2);
                addInfo("Setting level of logger [" + subst + "] to " + toLevel);
                this.logger.setLevel(toLevel);
            }
        }
        subst2 = interpretationContext.subst(attributes.getValue(ActionConst.ADDITIVITY_ATTRIBUTE));
        if (!OptionHelper.isEmpty(subst2)) {
            boolean booleanValue = Boolean.valueOf(subst2).booleanValue();
            addInfo("Setting additivity of logger [" + subst + "] to " + booleanValue);
            this.logger.setAdditive(booleanValue);
        }
        interpretationContext.pushObject(this.logger);
    }

    public void end(InterpretationContext interpretationContext, String str) {
        if (!this.inError) {
            Logger peekObject = interpretationContext.peekObject();
            if (peekObject != this.logger) {
                addWarn("The object on the top the of the stack is not " + this.logger + " pushed earlier");
                addWarn("It is: " + peekObject);
                return;
            }
            interpretationContext.popObject();
        }
    }

    public void finish(InterpretationContext interpretationContext) {
    }
}
