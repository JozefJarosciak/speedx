package okio;

import java.io.IOException;

/* compiled from: ForwardingSource */
/* renamed from: okio.h */
public abstract class C5538h implements C5520s {
    private final C5520s delegate;

    public C5538h(C5520s c5520s) {
        if (c5520s == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = c5520s;
    }

    public final C5520s delegate() {
        return this.delegate;
    }

    public long read(C5637c c5637c, long j) throws IOException {
        return this.delegate.read(c5637c, j);
    }

    public C5522t timeout() {
        return this.delegate.timeout();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
