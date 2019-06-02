package ch.qos.logback.core.encoder;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import java.io.IOException;
import java.io.OutputStream;

public interface Encoder<E> extends ContextAware, LifeCycle {
    void close() throws IOException;

    void doEncode(E e) throws IOException;

    void init(OutputStream outputStream) throws IOException;
}
