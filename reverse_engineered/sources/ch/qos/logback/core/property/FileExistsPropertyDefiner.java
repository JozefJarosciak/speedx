package ch.qos.logback.core.property;

import ch.qos.logback.core.PropertyDefinerBase;
import ch.qos.logback.core.util.OptionHelper;
import java.io.File;

public class FileExistsPropertyDefiner extends PropertyDefinerBase {
    String path;

    public String getPath() {
        return this.path;
    }

    public String getPropertyValue() {
        if (!OptionHelper.isEmpty(this.path)) {
            return PropertyDefinerBase.booleanAsStr(new File(this.path).exists());
        }
        addError("The \"path\" property must be set.");
        return null;
    }

    public void setPath(String str) {
        this.path = str;
    }
}
