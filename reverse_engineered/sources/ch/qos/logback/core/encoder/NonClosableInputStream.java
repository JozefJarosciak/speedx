package ch.qos.logback.core.encoder;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NonClosableInputStream extends FilterInputStream {
    NonClosableInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public void close() {
    }

    public void realClose() throws IOException {
        super.close();
    }
}
