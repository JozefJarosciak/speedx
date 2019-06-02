package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ActionConst;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

@Deprecated
public class LevelAction extends Action {
    boolean inError = false;

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
        Object peekObject = interpretationContext.peekObject();
        if (peekObject instanceof Logger) {
            Logger logger = (Logger) peekObject;
            String name = logger.getName();
            String subst = interpretationContext.subst(attributes.getValue("value"));
            if (ActionConst.INHERITED.equalsIgnoreCase(subst) || ActionConst.NULL.equalsIgnoreCase(subst)) {
                logger.setLevel(null);
            } else {
                logger.setLevel(Level.toLevel(subst, Level.DEBUG));
            }
            addInfo(name + " level set to " + logger.getLevel());
            return;
        }
        this.inError = true;
        addError("For element <level>, could not find a logger at the top of execution stack.");
    }

    public void end(InterpretationContext interpretationContext, String str) {
    }

    public void finish(InterpretationContext interpretationContext) {
    }
}
