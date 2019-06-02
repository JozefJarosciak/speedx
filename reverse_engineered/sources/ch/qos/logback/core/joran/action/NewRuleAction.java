package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class NewRuleAction extends Action {
    boolean inError = false;

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
        this.inError = false;
        String value = attributes.getValue("pattern");
        String value2 = attributes.getValue("actionClass");
        if (OptionHelper.isEmpty(value)) {
            this.inError = true;
            addError("No 'pattern' attribute in <newRule>");
        } else if (OptionHelper.isEmpty(value2)) {
            this.inError = true;
            addError("No 'actionClass' attribute in <newRule>");
        } else {
            try {
                addInfo("About to add new Joran parsing rule [" + value + "," + value2 + "].");
                interpretationContext.getJoranInterpreter().getRuleStore().addRule(new ElementSelector(value), value2);
            } catch (Exception e) {
                this.inError = true;
                addError("Could not add new Joran parsing rule [" + value + "," + value2 + "]");
            }
        }
    }

    public void end(InterpretationContext interpretationContext, String str) {
    }

    public void finish(InterpretationContext interpretationContext) {
    }
}
