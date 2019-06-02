package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* compiled from: DeflaterSink */
/* renamed from: okio.f */
public final class C5638f implements C5492r {
    /* renamed from: a */
    private final C5635d f18211a;
    /* renamed from: b */
    private final Deflater f18212b;
    /* renamed from: c */
    private boolean f18213c;

    public C5638f(C5492r c5492r, Deflater deflater) {
        this(C5647m.m20713a(c5492r), deflater);
    }

    C5638f(C5635d c5635d, Deflater deflater) {
        if (c5635d == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.f18211a = c5635d;
            this.f18212b = deflater;
        }
    }

    /* renamed from: a */
    public void mo6706a(C5637c c5637c, long j) throws IOException {
        C5654u.m20769a(c5637c.f18210b, 0, j);
        while (j > 0) {
            C5651p c5651p = c5637c.f18209a;
            int min = (int) Math.min(j, (long) (c5651p.f18245c - c5651p.f18244b));
            this.f18212b.setInput(c5651p.f18243a, c5651p.f18244b, min);
            m20689a(false);
            c5637c.f18210b -= (long) min;
            c5651p.f18244b += min;
            if (c5651p.f18244b == c5651p.f18245c) {
                c5637c.f18209a = c5651p.m20757a();
                C5652q.m20763a(c5651p);
            }
            j -= (long) min;
        }
    }

    @IgnoreJRERequirement
    /* renamed from: a */
    private void m20689a(boolean z) throws IOException {
        C5637c b = this.f18211a.mo6810b();
        while (true) {
            int deflate;
            C5651p f = b.m20659f(1);
            if (z) {
                deflate = this.f18212b.deflate(f.f18243a, f.f18245c, 8192 - f.f18245c, 2);
            } else {
                deflate = this.f18212b.deflate(f.f18243a, f.f18245c, 8192 - f.f18245c);
            }
            if (deflate > 0) {
                f.f18245c += deflate;
                b.f18210b += (long) deflate;
                this.f18211a.mo6836w();
            } else if (this.f18212b.needsInput()) {
                break;
            }
        }
        if (f.f18244b == f.f18245c) {
            b.f18209a = f.m20757a();
            C5652q.m20763a(f);
        }
    }

    public void flush() throws IOException {
        m20689a(true);
        this.f18211a.flush();
    }

    /* renamed from: a */
    void m20690a() throws IOException {
        this.f18212b.finish();
        m20689a(false);
    }

    public void close() throws IOException {
        if (!this.f18213c) {
            Throwable th;
            Throwable th2 = null;
            try {
                m20690a();
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.f18212b.end();
                th = th2;
            } catch (Throwable th4) {
                th = th4;
                if (th2 != null) {
                    th = th2;
                }
            }
            try {
                this.f18211a.close();
            } catch (Throwable th22) {
                if (th == null) {
                    th = th22;
                }
            }
            this.f18213c = true;
            if (th != null) {
                C5654u.m20770a(th);
            }
        }
    }

    public C5522t timeout() {
        return this.f18211a.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.f18211a + ")";
    }
}
