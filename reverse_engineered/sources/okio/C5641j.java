package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* compiled from: GzipSink */
/* renamed from: okio.j */
public final class C5641j implements C5492r {
    /* renamed from: a */
    private final C5635d f18216a;
    /* renamed from: b */
    private final Deflater f18217b;
    /* renamed from: c */
    private final C5638f f18218c;
    /* renamed from: d */
    private boolean f18219d;
    /* renamed from: e */
    private final CRC32 f18220e = new CRC32();

    public C5641j(C5492r c5492r) {
        if (c5492r == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.f18217b = new Deflater(-1, true);
        this.f18216a = C5647m.m20713a(c5492r);
        this.f18218c = new C5638f(this.f18216a, this.f18217b);
        m20700a();
    }

    /* renamed from: a */
    public void mo6706a(C5637c c5637c, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j != 0) {
            m20702b(c5637c, j);
            this.f18218c.mo6706a(c5637c, j);
        }
    }

    public void flush() throws IOException {
        this.f18218c.flush();
    }

    public C5522t timeout() {
        return this.f18216a.timeout();
    }

    public void close() throws IOException {
        Throwable th;
        if (!this.f18219d) {
            Throwable th2 = null;
            try {
                this.f18218c.m20690a();
                m20701b();
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.f18217b.end();
                th3 = th2;
            } catch (Throwable th4) {
                th3 = th4;
                if (th2 != null) {
                    th3 = th2;
                }
            }
            try {
                this.f18216a.close();
            } catch (Throwable th22) {
                if (th3 == null) {
                    th3 = th22;
                }
            }
            this.f18219d = true;
            if (th3 != null) {
                C5654u.m20770a(th3);
            }
        }
    }

    /* renamed from: a */
    private void m20700a() {
        C5637c b = this.f18216a.mo6810b();
        b.m20649c(8075);
        b.m20641b(8);
        b.m20641b(0);
        b.m20654d(0);
        b.m20641b(0);
        b.m20641b(0);
    }

    /* renamed from: b */
    private void m20701b() throws IOException {
        this.f18216a.mo6821h((int) this.f18220e.getValue());
        this.f18216a.mo6821h(this.f18217b.getTotalIn());
    }

    /* renamed from: b */
    private void m20702b(C5637c c5637c, long j) {
        C5651p c5651p = c5637c.f18209a;
        while (j > 0) {
            int min = (int) Math.min(j, (long) (c5651p.f18245c - c5651p.f18244b));
            this.f18220e.update(c5651p.f18243a, c5651p.f18244b, min);
            j -= (long) min;
            c5651p = c5651p.f18248f;
        }
    }
}
