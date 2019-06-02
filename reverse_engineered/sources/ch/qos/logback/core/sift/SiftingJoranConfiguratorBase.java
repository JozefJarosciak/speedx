package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.GenericConfigurator;
import ch.qos.logback.core.joran.action.DefinePropertyAction;
import ch.qos.logback.core.joran.action.ImplicitAction;
import ch.qos.logback.core.joran.action.NestedBasicPropertyIA;
import ch.qos.logback.core.joran.action.NestedComplexPropertyIA;
import ch.qos.logback.core.joran.action.PropertyAction;
import ch.qos.logback.core.joran.action.TimestampAction;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.Interpreter;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.spi.RuleStore;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.List;
import java.util.Map;

public abstract class SiftingJoranConfiguratorBase<E> extends GenericConfigurator {
    static final String ONE_AND_ONLY_ONE_URL = "http://logback.qos.ch/codes.html#1andOnly1";
    int errorEmmissionCount = 0;
    protected final String key;
    protected final Map<String, String> parentPropertyMap;
    protected final String value;

    protected SiftingJoranConfiguratorBase(String str, String str2, Map<String, String> map) {
        this.key = str;
        this.value = str2;
        this.parentPropertyMap = map;
    }

    protected void addImplicitRules(Interpreter interpreter) {
        ImplicitAction nestedComplexPropertyIA = new NestedComplexPropertyIA();
        nestedComplexPropertyIA.setContext(this.context);
        interpreter.addImplicitAction(nestedComplexPropertyIA);
        nestedComplexPropertyIA = new NestedBasicPropertyIA();
        nestedComplexPropertyIA.setContext(this.context);
        interpreter.addImplicitAction(nestedComplexPropertyIA);
    }

    protected void addInstanceRules(RuleStore ruleStore) {
        ruleStore.addRule(new ElementSelector("configuration/property"), new PropertyAction());
        ruleStore.addRule(new ElementSelector("configuration/timestamp"), new TimestampAction());
        ruleStore.addRule(new ElementSelector("configuration/define"), new DefinePropertyAction());
    }

    public void doConfigure(List<SaxEvent> list) throws JoranException {
        super.doConfigure((List) list);
    }

    public abstract Appender<E> getAppender();

    protected void oneAndOnlyOneCheck(Map<?, ?> map) {
        String str = null;
        if (map.size() == 0) {
            this.errorEmmissionCount++;
            str = "No nested appenders found within the <sift> element in SiftingAppender.";
        } else if (map.size() > 1) {
            this.errorEmmissionCount++;
            str = "Only and only one appender can be nested the <sift> element in SiftingAppender. See also http://logback.qos.ch/codes.html#1andOnly1";
        }
        if (str != null && this.errorEmmissionCount < 4) {
            addError(str);
        }
    }

    public String toString() {
        return getClass().getName() + "{" + this.key + SimpleComparison.EQUAL_TO_OPERATION + this.value + CoreConstants.CURLY_RIGHT;
    }
}
