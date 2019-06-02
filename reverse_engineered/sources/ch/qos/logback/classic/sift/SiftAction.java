package ch.qos.logback.classic.sift;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.event.InPlayListener;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

public class SiftAction extends Action implements InPlayListener {
    List<SaxEvent> seList;

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        this.seList = new ArrayList();
        interpretationContext.addInPlayListener(this);
    }

    public void end(InterpretationContext interpretationContext, String str) throws ActionException {
        interpretationContext.removeInPlayListener(this);
        Object peekObject = interpretationContext.peekObject();
        if (peekObject instanceof SiftingAppender) {
            SiftingAppender siftingAppender = (SiftingAppender) peekObject;
            siftingAppender.setAppenderFactory(new AppenderFactoryUsingJoran(this.seList, siftingAppender.getDiscriminatorKey(), interpretationContext.getCopyOfPropertyMap()));
        }
    }

    public List<SaxEvent> getSeList() {
        return this.seList;
    }

    public void inPlay(SaxEvent saxEvent) {
        this.seList.add(saxEvent);
    }
}
