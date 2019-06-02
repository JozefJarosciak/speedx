package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* compiled from: Sink */
/* renamed from: okio.r */
public interface C5492r extends Closeable, Flushable {
    /* renamed from: a */
    void mo6706a(C5637c c5637c, long j) throws IOException;

    void close() throws IOException;

    void flush() throws IOException;

    C5522t timeout();
}
