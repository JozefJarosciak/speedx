package ch.qos.logback.classic.joran.action;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

public class ContextNameAction extends Action {
    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
    }

    public void body(InterpretationContext interpretationContext, String str) {
        String subst = interpretationContext.subst(str);
        addInfo("Setting logger context name as [" + subst + "]");
        try {
            this.context.setName(subst);
        } catch (Throwable e) {
            addError("Failed to rename context [" + this.context.getName() + "] as [" + subst + "]", e);
        }
    }

    public void end(InterpretationContext interpretationContext, String str) {
    }
}
