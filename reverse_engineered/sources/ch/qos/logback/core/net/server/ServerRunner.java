package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAware;
import java.io.IOException;

public interface ServerRunner<T extends Client> extends ContextAware, Runnable {
    void accept(ClientVisitor<T> clientVisitor);

    boolean isRunning();

    void stop() throws IOException;
}
