package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.ElementPath;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.NoAutoStartUtil;
import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.AggregationType;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Stack;
import org.xml.sax.Attributes;

public class NestedComplexPropertyIA extends ImplicitAction {
    Stack<IADataForComplexProperty> actionDataStack = new Stack();

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
        IADataForComplexProperty iADataForComplexProperty = (IADataForComplexProperty) this.actionDataStack.peek();
        String subst = interpretationContext.subst(attributes.getValue(Action.CLASS_ATTRIBUTE));
        try {
            Class loadClass = !OptionHelper.isEmpty(subst) ? Loader.loadClass(subst, this.context) : iADataForComplexProperty.parentBean.getClassNameViaImplicitRules(iADataForComplexProperty.getComplexPropertyName(), iADataForComplexProperty.getAggregationType(), interpretationContext.getDefaultNestedComponentRegistry());
            if (loadClass == null) {
                iADataForComplexProperty.inError = true;
                addError("Could not find an appropriate class for property [" + str + "]");
                return;
            }
            if (OptionHelper.isEmpty(subst)) {
                addInfo("Assuming default type [" + loadClass.getName() + "] for [" + str + "] property");
            }
            iADataForComplexProperty.setNestedComplexProperty(loadClass.newInstance());
            if (iADataForComplexProperty.getNestedComplexProperty() instanceof ContextAware) {
                ((ContextAware) iADataForComplexProperty.getNestedComplexProperty()).setContext(this.context);
            }
            interpretationContext.pushObject(iADataForComplexProperty.getNestedComplexProperty());
        } catch (Throwable e) {
            iADataForComplexProperty.inError = true;
            addError("Could not create component [" + str + "] of type [" + subst + "]", e);
        }
    }

    public void end(InterpretationContext interpretationContext, String str) {
        IADataForComplexProperty iADataForComplexProperty = (IADataForComplexProperty) this.actionDataStack.pop();
        if (!iADataForComplexProperty.inError) {
            PropertySetter propertySetter = new PropertySetter(iADataForComplexProperty.getNestedComplexProperty());
            propertySetter.setContext(this.context);
            if (propertySetter.computeAggregationType("parent") == AggregationType.AS_COMPLEX_PROPERTY) {
                propertySetter.setComplexProperty("parent", iADataForComplexProperty.parentBean.getObj());
            }
            Object nestedComplexProperty = iADataForComplexProperty.getNestedComplexProperty();
            if ((nestedComplexProperty instanceof LifeCycle) && NoAutoStartUtil.notMarkedWithNoAutoStart(nestedComplexProperty)) {
                ((LifeCycle) nestedComplexProperty).start();
            }
            if (interpretationContext.peekObject() != iADataForComplexProperty.getNestedComplexProperty()) {
                addError("The object on the top the of the stack is not the component pushed earlier.");
                return;
            }
            interpretationContext.popObject();
            switch (iADataForComplexProperty.aggregationType) {
                case AS_COMPLEX_PROPERTY_COLLECTION:
                    iADataForComplexProperty.parentBean.addComplexProperty(str, iADataForComplexProperty.getNestedComplexProperty());
                    return;
                case AS_COMPLEX_PROPERTY:
                    iADataForComplexProperty.parentBean.setComplexProperty(str, iADataForComplexProperty.getNestedComplexProperty());
                    return;
                default:
                    return;
            }
        }
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
            case AS_BASIC_PROPERTY:
            case AS_BASIC_PROPERTY_COLLECTION:
                return false;
            case AS_COMPLEX_PROPERTY_COLLECTION:
            case AS_COMPLEX_PROPERTY:
                this.actionDataStack.push(new IADataForComplexProperty(propertySetter, computeAggregationType, peekLast));
                return true;
            default:
                addError("PropertySetter.computeAggregationType returned " + computeAggregationType);
                return false;
        }
    }
}
