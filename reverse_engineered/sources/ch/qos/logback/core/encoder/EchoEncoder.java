package ch.qos.logback.core.encoder;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.io.OutputStream;

public class EchoEncoder<E> extends EncoderBase<E> {
    public void close() throws IOException {
    }

    public void doEncode(E e) throws IOException {
        this.outputStream.write((e + CoreConstants.LINE_SEPARATOR).getBytes());
        this.outputStream.flush();
    }

    public void init(OutputStream outputStream) throws IOException {
        super.init(outputStream);
    }
}
