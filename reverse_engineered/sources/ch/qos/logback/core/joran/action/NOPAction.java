package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

public class NOPAction extends Action {
    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
    }

    public void end(InterpretationContext interpretationContext, String str) {
    }
}
