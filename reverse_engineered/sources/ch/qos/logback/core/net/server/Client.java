package ch.qos.logback.core.net.server;

import java.io.Closeable;

public interface Client extends Closeable, Runnable {
    void close();
}
