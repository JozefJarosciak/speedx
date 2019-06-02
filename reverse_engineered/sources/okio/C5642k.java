package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* compiled from: GzipSource */
/* renamed from: okio.k */
public final class C5642k implements C5520s {
    /* renamed from: a */
    private int f18221a = 0;
    /* renamed from: b */
    private final C5636e f18222b;
    /* renamed from: c */
    private final Inflater f18223c;
    /* renamed from: d */
    private final C5643l f18224d;
    /* renamed from: e */
    private final CRC32 f18225e = new CRC32();

    public C5642k(C5520s c5520s) {
        if (c5520s == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f18223c = new Inflater(true);
        this.f18222b = C5647m.m20714a(c5520s);
        this.f18224d = new C5643l(this.f18222b, this.f18223c);
    }

    public long read(C5637c c5637c, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            if (this.f18221a == 0) {
                m20704a();
                this.f18221a = 1;
            }
            if (this.f18221a == 1) {
                long j2 = c5637c.f18210b;
                long read = this.f18224d.read(c5637c, j);
                if (read != -1) {
                    m20706a(c5637c, j2, read);
                    return read;
                }
                this.f18221a = 2;
            }
            if (this.f18221a == 2) {
                m20707b();
                this.f18221a = 3;
                if (!this.f18222b.mo6818f()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    /* renamed from: a */
    private void m20704a() throws IOException {
        Object obj;
        long a;
        this.f18222b.mo6809a(10);
        byte c = this.f18222b.mo6810b().m20647c(3);
        if (((c >> 1) & 1) == 1) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            m20706a(this.f18222b.mo6810b(), 0, 10);
        }
        m20705a("ID1ID2", 8075, this.f18222b.mo6826j());
        this.f18222b.mo6822h(8);
        if (((c >> 2) & 1) == 1) {
            this.f18222b.mo6809a(2);
            if (obj != null) {
                m20706a(this.f18222b.mo6810b(), 0, 2);
            }
            short l = this.f18222b.mo6810b().mo6831l();
            this.f18222b.mo6809a((long) l);
            if (obj != null) {
                m20706a(this.f18222b.mo6810b(), 0, (long) l);
            }
            this.f18222b.mo6822h((long) l);
        }
        if (((c >> 3) & 1) == 1) {
            a = this.f18222b.mo6806a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                m20706a(this.f18222b.mo6810b(), 0, 1 + a);
            }
            this.f18222b.mo6822h(1 + a);
        }
        if (((c >> 4) & 1) == 1) {
            a = this.f18222b.mo6806a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                m20706a(this.f18222b.mo6810b(), 0, 1 + a);
            }
            this.f18222b.mo6822h(1 + a);
        }
        if (obj != null) {
            m20705a("FHCRC", this.f18222b.mo6831l(), (short) ((int) this.f18225e.getValue()));
            this.f18225e.reset();
        }
    }

    /* renamed from: b */
    private void m20707b() throws IOException {
        m20705a("CRC", this.f18222b.mo6832m(), (int) this.f18225e.getValue());
        m20705a("ISIZE", this.f18222b.mo6832m(), this.f18223c.getTotalOut());
    }

    public C5522t timeout() {
        return this.f18222b.timeout();
    }

    public void close() throws IOException {
        this.f18224d.close();
    }

    /* renamed from: a */
    private void m20706a(C5637c c5637c, long j, long j2) {
        C5651p c5651p = c5637c.f18209a;
        while (j >= ((long) (c5651p.f18245c - c5651p.f18244b))) {
            j -= (long) (c5651p.f18245c - c5651p.f18244b);
            c5651p = c5651p.f18248f;
        }
        while (j2 > 0) {
            int i = (int) (((long) c5651p.f18244b) + j);
            int min = (int) Math.min((long) (c5651p.f18245c - i), j2);
            this.f18225e.update(c5651p.f18243a, i, min);
            j2 -= (long) min;
            c5651p = c5651p.f18248f;
            j = 0;
        }
    }

    /* renamed from: a */
    private void m20705a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
