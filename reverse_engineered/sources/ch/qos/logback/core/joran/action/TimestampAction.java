package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.action.ActionUtil.Scope;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.CachingDateFormatter;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class TimestampAction extends Action {
    static String CONTEXT_BIRTH = "contextBirth";
    static String DATE_PATTERN_ATTRIBUTE = "datePattern";
    static String TIME_REFERENCE_ATTRIBUTE = "timeReference";
    boolean inError = false;

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        long birthTime;
        String value = attributes.getValue(Action.KEY_ATTRIBUTE);
        if (OptionHelper.isEmpty(value)) {
            addError("Attribute named [key] cannot be empty");
            this.inError = true;
        }
        String value2 = attributes.getValue(DATE_PATTERN_ATTRIBUTE);
        if (OptionHelper.isEmpty(value2)) {
            addError("Attribute named [" + DATE_PATTERN_ATTRIBUTE + "] cannot be empty");
            this.inError = true;
        }
        if (CONTEXT_BIRTH.equalsIgnoreCase(attributes.getValue(TIME_REFERENCE_ATTRIBUTE))) {
            addInfo("Using context birth as time reference.");
            birthTime = this.context.getBirthTime();
        } else {
            birthTime = System.currentTimeMillis();
            addInfo("Using current interpretation time, i.e. now, as time reference.");
        }
        if (!this.inError) {
            Scope stringToScope = ActionUtil.stringToScope(attributes.getValue(Action.SCOPE_ATTRIBUTE));
            String format = new CachingDateFormatter(value2).format(birthTime);
            addInfo("Adding property to the context with key=\"" + value + "\" and value=\"" + format + "\" to the " + stringToScope + " scope");
            ActionUtil.setProperty(interpretationContext, value, format, stringToScope);
        }
    }

    public void end(InterpretationContext interpretationContext, String str) throws ActionException {
    }
}
