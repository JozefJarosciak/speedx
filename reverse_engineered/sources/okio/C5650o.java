package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: RealBufferedSource */
/* renamed from: okio.o */
final class C5650o implements C5636e {
    /* renamed from: a */
    public final C5637c f18240a = new C5637c();
    /* renamed from: b */
    public final C5520s f18241b;
    /* renamed from: c */
    boolean f18242c;

    /* compiled from: RealBufferedSource */
    /* renamed from: okio.o$1 */
    class C56491 extends InputStream {
        /* renamed from: a */
        final /* synthetic */ C5650o f18239a;

        C56491(C5650o c5650o) {
            this.f18239a = c5650o;
        }

        public int read() throws IOException {
            if (this.f18239a.f18242c) {
                throw new IOException("closed");
            } else if (this.f18239a.f18240a.f18210b == 0 && this.f18239a.f18241b.read(this.f18239a.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            } else {
                return this.f18239a.f18240a.mo6823i() & 255;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.f18239a.f18242c) {
                throw new IOException("closed");
            }
            C5654u.m20769a((long) bArr.length, (long) i, (long) i2);
            if (this.f18239a.f18240a.f18210b == 0 && this.f18239a.f18241b.read(this.f18239a.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            return this.f18239a.f18240a.m20622a(bArr, i, i2);
        }

        public int available() throws IOException {
            if (!this.f18239a.f18242c) {
                return (int) Math.min(this.f18239a.f18240a.f18210b, 2147483647L);
            }
            throw new IOException("closed");
        }

        public void close() throws IOException {
            this.f18239a.close();
        }

        public String toString() {
            return this.f18239a + ".inputStream()";
        }
    }

    C5650o(C5520s c5520s) {
        if (c5520s == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f18241b = c5520s;
    }

    /* renamed from: b */
    public C5637c mo6810b() {
        return this.f18240a;
    }

    public long read(C5637c c5637c, long j) throws IOException {
        if (c5637c == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f18242c) {
            throw new IllegalStateException("closed");
        } else if (this.f18240a.f18210b == 0 && this.f18241b.read(this.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.f18240a.read(c5637c, Math.min(j, this.f18240a.f18210b));
        }
    }

    /* renamed from: f */
    public boolean mo6818f() throws IOException {
        if (!this.f18242c) {
            return this.f18240a.mo6818f() && this.f18241b.read(this.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    /* renamed from: a */
    public void mo6809a(long j) throws IOException {
        if (!mo6813b(j)) {
            throw new EOFException();
        }
    }

    /* renamed from: b */
    public boolean mo6813b(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f18242c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.f18240a.f18210b < j) {
                if (this.f18241b.read(this.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    /* renamed from: i */
    public byte mo6823i() throws IOException {
        mo6809a(1);
        return this.f18240a.mo6823i();
    }

    /* renamed from: d */
    public ByteString mo6816d(long j) throws IOException {
        mo6809a(j);
        return this.f18240a.mo6816d(j);
    }

    /* renamed from: s */
    public byte[] mo6835s() throws IOException {
        this.f18240a.mo6808a(this.f18241b);
        return this.f18240a.mo6835s();
    }

    /* renamed from: g */
    public byte[] mo6820g(long j) throws IOException {
        mo6809a(j);
        return this.f18240a.mo6820g(j);
    }

    /* renamed from: a */
    public long mo6807a(C5492r c5492r) throws IOException {
        if (c5492r == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.f18241b.read(this.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long h = this.f18240a.m20664h();
            if (h > 0) {
                j += h;
                c5492r.mo6706a(this.f18240a, h);
            }
        }
        if (this.f18240a.m20623a() <= 0) {
            return j;
        }
        j += this.f18240a.m20623a();
        c5492r.mo6706a(this.f18240a, this.f18240a.m20623a());
        return j;
    }

    /* renamed from: q */
    public String mo6834q() throws IOException {
        long a = mo6806a((byte) 10);
        if (a != -1) {
            return this.f18240a.m20658f(a);
        }
        C5637c c5637c = new C5637c();
        this.f18240a.m20636a(c5637c, 0, Math.min(32, this.f18240a.m20623a()));
        throw new EOFException("\\n not found: size=" + this.f18240a.m20623a() + " content=" + c5637c.m20680o().hex() + "…");
    }

    /* renamed from: j */
    public short mo6826j() throws IOException {
        mo6809a(2);
        return this.f18240a.mo6826j();
    }

    /* renamed from: l */
    public short mo6831l() throws IOException {
        mo6809a(2);
        return this.f18240a.mo6831l();
    }

    /* renamed from: k */
    public int mo6827k() throws IOException {
        mo6809a(4);
        return this.f18240a.mo6827k();
    }

    /* renamed from: m */
    public int mo6832m() throws IOException {
        mo6809a(4);
        return this.f18240a.mo6832m();
    }

    /* renamed from: n */
    public long mo6833n() throws IOException {
        mo6809a(1);
        for (int i = 0; mo6813b((long) (i + 1)); i++) {
            byte c = this.f18240a.m20647c((long) i);
            if ((c < (byte) 48 || c > (byte) 57) && ((c < (byte) 97 || c > (byte) 102) && (c < (byte) 65 || c > (byte) 70))) {
                if (i == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(c)}));
                }
                return this.f18240a.mo6833n();
            }
        }
        return this.f18240a.mo6833n();
    }

    /* renamed from: h */
    public void mo6822h(long j) throws IOException {
        if (this.f18242c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.f18240a.f18210b == 0 && this.f18241b.read(this.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.f18240a.m20623a());
            this.f18240a.mo6822h(min);
            j -= min;
        }
    }

    /* renamed from: a */
    public long mo6806a(byte b) throws IOException {
        return m20739a(b, 0);
    }

    /* renamed from: a */
    public long m20739a(byte b, long j) throws IOException {
        if (this.f18242c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a = this.f18240a.m20625a(b, j);
            if (a != -1) {
                return a;
            }
            a = this.f18240a.f18210b;
            if (this.f18241b.read(this.f18240a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            j = Math.max(j, a);
        }
    }

    /* renamed from: g */
    public InputStream mo6819g() {
        return new C56491(this);
    }

    public void close() throws IOException {
        if (!this.f18242c) {
            this.f18242c = true;
            this.f18241b.close();
            this.f18240a.m20685t();
        }
    }

    public C5522t timeout() {
        return this.f18241b.timeout();
    }

    public String toString() {
        return "buffer(" + this.f18241b + ")";
    }
}
