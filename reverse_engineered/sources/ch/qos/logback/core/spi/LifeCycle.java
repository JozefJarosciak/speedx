package ch.qos.logback.core.spi;

public interface LifeCycle {
    boolean isStarted();

    void start();

    void stop();
}
