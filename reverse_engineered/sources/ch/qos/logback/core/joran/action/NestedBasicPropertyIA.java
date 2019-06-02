package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.ElementPath;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.util.AggregationType;
import java.util.Stack;
import org.xml.sax.Attributes;

public class NestedBasicPropertyIA extends ImplicitAction {
    Stack<IADataForBasicProperty> actionDataStack = new Stack();

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
    }

    public void body(InterpretationContext interpretationContext, String str) {
        String subst = interpretationContext.subst(str);
        IADataForBasicProperty iADataForBasicProperty = (IADataForBasicProperty) this.actionDataStack.peek();
        switch (iADataForBasicProperty.aggregationType) {
            case AS_BASIC_PROPERTY:
                iADataForBasicProperty.parentBean.setProperty(iADataForBasicProperty.propertyName, subst);
                return;
            case AS_BASIC_PROPERTY_COLLECTION:
                iADataForBasicProperty.parentBean.addBasicProperty(iADataForBasicProperty.propertyName, subst);
                return;
            default:
                return;
        }
    }

    public void end(InterpretationContext interpretationContext, String str) {
        this.actionDataStack.pop();
    }

    public boolean isApplicable(ElementPath elementPath, Attributes attributes, InterpretationContext interpretationContext) {
        String peekLast = elementPath.peekLast();
        if (interpretationContext.isEmpty()) {
            return false;
        }
        PropertySetter propertySetter = new PropertySetter(interpretationContext.peekObject());
        propertySetter.setContext(this.context);
        AggregationType computeAggregationType = propertySetter.computeAggregationType(peekLast);
        switch (computeAggregationType) {
            case NOT_FOUND:
            case AS_COMPLEX_PROPERTY:
            case AS_COMPLEX_PROPERTY_COLLECTION:
                return false;
            case AS_BASIC_PROPERTY:
            case AS_BASIC_PROPERTY_COLLECTION:
                this.actionDataStack.push(new IADataForBasicProperty(propertySetter, computeAggregationType, peekLast));
                return true;
            default:
                addError("PropertySetter.canContainComponent returned " + computeAggregationType);
                return false;
        }
    }
}
