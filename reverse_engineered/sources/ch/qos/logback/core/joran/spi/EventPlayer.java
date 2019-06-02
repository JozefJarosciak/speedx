package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.joran.event.BodyEvent;
import ch.qos.logback.core.joran.event.EndEvent;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.event.StartEvent;
import java.util.ArrayList;
import java.util.List;

public class EventPlayer {
    int currentIndex;
    List<SaxEvent> eventList;
    final Interpreter interpreter;

    public EventPlayer(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void addEventsDynamically(List<SaxEvent> list, int i) {
        this.eventList.addAll(this.currentIndex + i, list);
    }

    public List<SaxEvent> getCopyOfPlayerEventList() {
        return new ArrayList(this.eventList);
    }

    public void play(List<SaxEvent> list) {
        this.eventList = list;
        this.currentIndex = 0;
        while (this.currentIndex < this.eventList.size()) {
            SaxEvent saxEvent = (SaxEvent) this.eventList.get(this.currentIndex);
            if (saxEvent instanceof StartEvent) {
                this.interpreter.startElement((StartEvent) saxEvent);
                this.interpreter.getInterpretationContext().fireInPlay(saxEvent);
            }
            if (saxEvent instanceof BodyEvent) {
                this.interpreter.getInterpretationContext().fireInPlay(saxEvent);
                this.interpreter.characters((BodyEvent) saxEvent);
            }
            if (saxEvent instanceof EndEvent) {
                this.interpreter.getInterpretationContext().fireInPlay(saxEvent);
                this.interpreter.endElement((EndEvent) saxEvent);
            }
            this.currentIndex++;
        }
    }
}
