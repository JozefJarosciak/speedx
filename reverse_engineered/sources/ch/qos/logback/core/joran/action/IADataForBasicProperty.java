package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.util.AggregationType;

class IADataForBasicProperty {
    final AggregationType aggregationType;
    boolean inError;
    final PropertySetter parentBean;
    final String propertyName;

    IADataForBasicProperty(PropertySetter propertySetter, AggregationType aggregationType, String str) {
        this.parentBean = propertySetter;
        this.aggregationType = aggregationType;
        this.propertyName = str;
    }
}
