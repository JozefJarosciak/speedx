package ch.qos.logback.core.encoder;

import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.IOException;
import java.io.OutputStream;

public abstract class EncoderBase<E> extends ContextAwareBase implements Encoder<E> {
    protected OutputStream outputStream;
    protected boolean started;

    public void init(OutputStream outputStream) throws IOException {
        this.outputStream = outputStream;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }
}
