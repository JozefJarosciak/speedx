package io.rong.eventbus.util;

public interface HasExecutionScope {
    Object getExecutionScope();

    void setExecutionScope(Object obj);
}
