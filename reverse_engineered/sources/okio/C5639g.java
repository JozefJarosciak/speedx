package okio;

import java.io.IOException;

/* compiled from: ForwardingSink */
/* renamed from: okio.g */
public abstract class C5639g implements C5492r {
    /* renamed from: a */
    private final C5492r f18214a;

    public C5639g(C5492r c5492r) {
        if (c5492r == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f18214a = c5492r;
    }

    /* renamed from: a */
    public void mo6706a(C5637c c5637c, long j) throws IOException {
        this.f18214a.mo6706a(c5637c, j);
    }

    public void flush() throws IOException {
        this.f18214a.flush();
    }

    public C5522t timeout() {
        return this.f18214a.timeout();
    }

    public void close() throws IOException {
        this.f18214a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f18214a.toString() + ")";
    }
}
