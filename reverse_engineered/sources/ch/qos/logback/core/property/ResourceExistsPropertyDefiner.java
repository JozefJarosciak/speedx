package ch.qos.logback.core.property;

import ch.qos.logback.core.PropertyDefinerBase;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;

public class ResourceExistsPropertyDefiner extends PropertyDefinerBase {
    String resourceStr;

    public String getPropertyValue() {
        if (OptionHelper.isEmpty(this.resourceStr)) {
            addError("The \"resource\" property must be set.");
            return null;
        }
        return PropertyDefinerBase.booleanAsStr(Loader.getResourceBySelfClassLoader(this.resourceStr) != null);
    }

    public String getResource() {
        return this.resourceStr;
    }

    public void setResource(String str) {
        this.resourceStr = str;
    }
}
