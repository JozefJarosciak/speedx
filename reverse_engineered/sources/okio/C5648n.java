package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* compiled from: RealBufferedSink */
/* renamed from: okio.n */
final class C5648n implements C5635d {
    /* renamed from: a */
    public final C5637c f18236a = new C5637c();
    /* renamed from: b */
    public final C5492r f18237b;
    /* renamed from: c */
    boolean f18238c;

    C5648n(C5492r c5492r) {
        if (c5492r == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.f18237b = c5492r;
    }

    /* renamed from: b */
    public C5637c mo6810b() {
        return this.f18236a;
    }

    /* renamed from: a */
    public void mo6706a(C5637c c5637c, long j) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.mo6706a(c5637c, j);
        mo6836w();
    }

    /* renamed from: b */
    public C5635d mo6812b(ByteString byteString) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20635a(byteString);
        return mo6836w();
    }

    /* renamed from: b */
    public C5635d mo6811b(String str) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20631a(str);
        return mo6836w();
    }

    /* renamed from: c */
    public C5635d mo6814c(byte[] bArr) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20642b(bArr);
        return mo6836w();
    }

    /* renamed from: c */
    public C5635d mo6815c(byte[] bArr, int i, int i2) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20643b(bArr, i, i2);
        return mo6836w();
    }

    /* renamed from: a */
    public long mo6808a(C5520s c5520s) throws IOException {
        if (c5520s == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = c5520s.read(this.f18236a, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
            mo6836w();
        }
    }

    /* renamed from: k */
    public C5635d mo6828k(int i) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20641b(i);
        return mo6836w();
    }

    /* renamed from: j */
    public C5635d mo6825j(int i) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20649c(i);
        return mo6836w();
    }

    /* renamed from: i */
    public C5635d mo6824i(int i) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20654d(i);
        return mo6836w();
    }

    /* renamed from: h */
    public C5635d mo6821h(int i) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20656e(i);
        return mo6836w();
    }

    /* renamed from: l */
    public C5635d mo6830l(long j) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20668i(j);
        return mo6836w();
    }

    /* renamed from: k */
    public C5635d mo6829k(long j) throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        this.f18236a.m20670j(j);
        return mo6836w();
    }

    /* renamed from: w */
    public C5635d mo6836w() throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        long h = this.f18236a.m20664h();
        if (h > 0) {
            this.f18237b.mo6706a(this.f18236a, h);
        }
        return this;
    }

    /* renamed from: e */
    public C5635d mo6817e() throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        long a = this.f18236a.m20623a();
        if (a > 0) {
            this.f18237b.mo6706a(this.f18236a, a);
        }
        return this;
    }

    public void flush() throws IOException {
        if (this.f18238c) {
            throw new IllegalStateException("closed");
        }
        if (this.f18236a.f18210b > 0) {
            this.f18237b.mo6706a(this.f18236a, this.f18236a.f18210b);
        }
        this.f18237b.flush();
    }

    public void close() throws IOException {
        if (!this.f18238c) {
            Throwable th = null;
            try {
                if (this.f18236a.f18210b > 0) {
                    this.f18237b.mo6706a(this.f18236a, this.f18236a.f18210b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f18237b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.f18238c = true;
            if (th != null) {
                C5654u.m20770a(th);
            }
        }
    }

    public C5522t timeout() {
        return this.f18237b.timeout();
    }

    public String toString() {
        return "buffer(" + this.f18237b + ")";
    }
}
