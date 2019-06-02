package okio;

import ch.qos.logback.classic.spi.CallerData;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.Inflater;

/* compiled from: InflaterSource */
/* renamed from: okio.l */
public final class C5643l implements C5520s {
    /* renamed from: a */
    private final C5636e f18226a;
    /* renamed from: b */
    private final Inflater f18227b;
    /* renamed from: c */
    private int f18228c;
    /* renamed from: d */
    private boolean f18229d;

    public C5643l(C5520s c5520s, Inflater inflater) {
        this(C5647m.m20714a(c5520s), inflater);
    }

    C5643l(C5636e c5636e, Inflater inflater) {
        if (c5636e == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.f18226a = c5636e;
            this.f18227b = inflater;
        }
    }

    public long read(C5637c c5637c, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f18229d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            boolean a;
            do {
                a = m20709a();
                try {
                    C5651p f = c5637c.m20659f(1);
                    int inflate = this.f18227b.inflate(f.f18243a, f.f18245c, 8192 - f.f18245c);
                    if (inflate > 0) {
                        f.f18245c += inflate;
                        c5637c.f18210b += (long) inflate;
                        return (long) inflate;
                    } else if (this.f18227b.finished() || this.f18227b.needsDictionary()) {
                        m20708b();
                        if (f.f18244b == f.f18245c) {
                            c5637c.f18209a = f.m20757a();
                            C5652q.m20763a(f);
                        }
                        return -1;
                    }
                } catch (Throwable e) {
                    throw new IOException(e);
                }
            } while (!a);
            throw new EOFException("source exhausted prematurely");
        }
    }

    /* renamed from: a */
    public boolean m20709a() throws IOException {
        if (!this.f18227b.needsInput()) {
            return false;
        }
        m20708b();
        if (this.f18227b.getRemaining() != 0) {
            throw new IllegalStateException(CallerData.NA);
        } else if (this.f18226a.mo6818f()) {
            return true;
        } else {
            C5651p c5651p = this.f18226a.mo6810b().f18209a;
            this.f18228c = c5651p.f18245c - c5651p.f18244b;
            this.f18227b.setInput(c5651p.f18243a, c5651p.f18244b, this.f18228c);
            return false;
        }
    }

    /* renamed from: b */
    private void m20708b() throws IOException {
        if (this.f18228c != 0) {
            int remaining = this.f18228c - this.f18227b.getRemaining();
            this.f18228c -= remaining;
            this.f18226a.mo6822h((long) remaining);
        }
    }

    public C5522t timeout() {
        return this.f18226a.timeout();
    }

    public void close() throws IOException {
        if (!this.f18229d) {
            this.f18227b.end();
            this.f18229d = true;
            this.f18226a.close();
        }
    }
}
