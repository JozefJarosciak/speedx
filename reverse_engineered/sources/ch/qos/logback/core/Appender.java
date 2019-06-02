package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.FilterAttachable;
import ch.qos.logback.core.spi.LifeCycle;

public interface Appender<E> extends ContextAware, FilterAttachable<E>, LifeCycle {
    void doAppend(E e) throws LogbackException;

    String getName();

    void setName(String str);
}
