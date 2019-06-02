package okio;

import java.io.Closeable;
import java.io.IOException;

/* compiled from: Source */
/* renamed from: okio.s */
public interface C5520s extends Closeable {
    void close() throws IOException;

    long read(C5637c c5637c, long j) throws IOException;

    C5522t timeout();
}
