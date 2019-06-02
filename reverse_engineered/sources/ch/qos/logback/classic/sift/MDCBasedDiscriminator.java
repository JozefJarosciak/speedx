package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.AbstractDiscriminator;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Map;

public class MDCBasedDiscriminator extends AbstractDiscriminator<ILoggingEvent> {
    private String defaultValue;
    private String key;

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getDiscriminatingValue(ILoggingEvent iLoggingEvent) {
        Map mDCPropertyMap = iLoggingEvent.getMDCPropertyMap();
        if (mDCPropertyMap == null) {
            return this.defaultValue;
        }
        String str = (String) mDCPropertyMap.get(this.key);
        return str == null ? this.defaultValue : str;
    }

    public String getKey() {
        return this.key;
    }

    public void setDefaultValue(String str) {
        this.defaultValue = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void start() {
        int i = 0;
        if (OptionHelper.isEmpty(this.key)) {
            addError("The \"Key\" property must be set");
            i = 1;
        }
        if (OptionHelper.isEmpty(this.defaultValue)) {
            i++;
            addError("The \"DefaultValue\" property must be set");
        }
        if (i == 0) {
            this.started = true;
        }
    }
}
