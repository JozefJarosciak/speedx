package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;

public final class PropertyConverter extends ClassicConverter {
    String key;

    public String convert(ILoggingEvent iLoggingEvent) {
        if (this.key == null) {
            return "Property_HAS_NO_KEY";
        }
        String str = (String) iLoggingEvent.getLoggerContextVO().getPropertyMap().get(this.key);
        return str == null ? System.getProperty(this.key) : str;
    }

    public void start() {
        String firstOption = getFirstOption();
        if (firstOption != null) {
            this.key = firstOption;
            super.start();
        }
    }
}
