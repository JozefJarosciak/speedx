package ch.qos.logback.core.spi;

import java.util.Map;

public interface PropertyContainer {
    Map<String, String> getCopyOfPropertyMap();

    String getProperty(String str);
}
