package ch.qos.logback.core;

import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.spi.PropertyContainer;
import ch.qos.logback.core.status.StatusManager;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public interface Context extends PropertyContainer {
    long getBirthTime();

    Object getConfigurationLock();

    Map<String, String> getCopyOfPropertyMap();

    ExecutorService getExecutorService();

    String getName();

    Object getObject(String str);

    String getProperty(String str);

    StatusManager getStatusManager();

    void putObject(String str, Object obj);

    void putProperty(String str, String str2);

    void register(LifeCycle lifeCycle);

    void setName(String str);
}
