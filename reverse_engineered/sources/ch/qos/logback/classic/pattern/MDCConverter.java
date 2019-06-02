package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Map;
import java.util.Map.Entry;

public class MDCConverter extends ClassicConverter {
    private String defaultValue = "";
    private String key;

    private String outputMDCForAllKeys(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append(", ");
                obj2 = obj;
            }
            stringBuilder.append((String) entry.getKey()).append('=').append((String) entry.getValue());
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public String convert(ILoggingEvent iLoggingEvent) {
        Map mDCPropertyMap = iLoggingEvent.getMDCPropertyMap();
        if (mDCPropertyMap == null) {
            return this.defaultValue;
        }
        if (this.key == null) {
            return outputMDCForAllKeys(mDCPropertyMap);
        }
        String str = (String) iLoggingEvent.getMDCPropertyMap().get(this.key);
        return str == null ? this.defaultValue : str;
    }

    public void start() {
        String[] extractDefaultReplacement = OptionHelper.extractDefaultReplacement(getFirstOption());
        this.key = extractDefaultReplacement[0];
        if (extractDefaultReplacement[1] != null) {
            this.defaultValue = extractDefaultReplacement[1];
        }
        super.start();
    }

    public void stop() {
        this.key = null;
        super.stop();
    }
}
