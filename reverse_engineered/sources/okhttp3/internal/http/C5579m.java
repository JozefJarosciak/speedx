package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.internal.C5586l;
import okio.C5492r;
import okio.C5522t;
import okio.C5637c;

/* compiled from: RetryableSink */
/* renamed from: okhttp3.internal.http.m */
public final class C5579m implements C5492r {
    /* renamed from: a */
    private boolean f17976a;
    /* renamed from: b */
    private final int f17977b;
    /* renamed from: c */
    private final C5637c f17978c;

    public C5579m(int i) {
        this.f17978c = new C5637c();
        this.f17977b = i;
    }

    public C5579m() {
        this(-1);
    }

    public void close() throws IOException {
        if (!this.f17976a) {
            this.f17976a = true;
            if (this.f17978c.m20623a() < ((long) this.f17977b)) {
                throw new ProtocolException("content-length promised " + this.f17977b + " bytes, but received " + this.f17978c.m20623a());
            }
        }
    }

    /* renamed from: a */
    public void mo6706a(C5637c c5637c, long j) throws IOException {
        if (this.f17976a) {
            throw new IllegalStateException("closed");
        }
        C5586l.m20326a(c5637c.m20623a(), 0, j);
        if (this.f17977b == -1 || this.f17978c.m20623a() <= ((long) this.f17977b) - j) {
            this.f17978c.mo6706a(c5637c, j);
            return;
        }
        throw new ProtocolException("exceeded content-length limit of " + this.f17977b + " bytes");
    }

    public void flush() throws IOException {
    }

    public C5522t timeout() {
        return C5522t.f17784b;
    }

    /* renamed from: a */
    public long m20274a() throws IOException {
        return this.f17978c.m20623a();
    }

    /* renamed from: a */
    public void m20276a(C5492r c5492r) throws IOException {
        C5637c c5637c = new C5637c();
        this.f17978c.m20636a(c5637c, 0, this.f17978c.m20623a());
        c5492r.mo6706a(c5637c, c5637c.m20623a());
    }
}
