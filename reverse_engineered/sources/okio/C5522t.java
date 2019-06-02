package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* compiled from: Timeout */
/* renamed from: okio.t */
public class C5522t {
    /* renamed from: b */
    public static final C5522t f17784b = new C56531();
    /* renamed from: a */
    private boolean f17785a;
    /* renamed from: c */
    private long f17786c;
    /* renamed from: d */
    private long f17787d;

    /* compiled from: Timeout */
    /* renamed from: okio.t$1 */
    static class C56531 extends C5522t {
        C56531() {
        }

        /* renamed from: a */
        public C5522t mo6838a(long j, TimeUnit timeUnit) {
            return this;
        }

        /* renamed from: a */
        public C5522t mo6837a(long j) {
            return this;
        }

        /* renamed from: g */
        public void mo6841g() throws IOException {
        }
    }

    /* renamed from: a */
    public C5522t mo6838a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.f17787d = timeUnit.toNanos(j);
            return this;
        }
    }

    public long j_() {
        return this.f17787d;
    }

    public boolean k_() {
        return this.f17785a;
    }

    /* renamed from: d */
    public long mo6839d() {
        if (this.f17785a) {
            return this.f17786c;
        }
        throw new IllegalStateException("No deadline");
    }

    /* renamed from: a */
    public C5522t mo6837a(long j) {
        this.f17785a = true;
        this.f17786c = j;
        return this;
    }

    public C5522t l_() {
        this.f17787d = 0;
        return this;
    }

    /* renamed from: f */
    public C5522t mo6840f() {
        this.f17785a = false;
        return this;
    }

    /* renamed from: g */
    public void mo6841g() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.f17785a && this.f17786c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
