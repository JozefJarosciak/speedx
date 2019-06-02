package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* compiled from: ForwardingTimeout */
/* renamed from: okio.i */
public class C5640i extends C5522t {
    /* renamed from: a */
    private C5522t f18215a;

    public C5640i(C5522t c5522t) {
        if (c5522t == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f18215a = c5522t;
    }

    /* renamed from: a */
    public final C5522t m20694a() {
        return this.f18215a;
    }

    /* renamed from: a */
    public final C5640i m20693a(C5522t c5522t) {
        if (c5522t == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f18215a = c5522t;
        return this;
    }

    /* renamed from: a */
    public C5522t mo6838a(long j, TimeUnit timeUnit) {
        return this.f18215a.mo6838a(j, timeUnit);
    }

    public long j_() {
        return this.f18215a.j_();
    }

    public boolean k_() {
        return this.f18215a.k_();
    }

    /* renamed from: d */
    public long mo6839d() {
        return this.f18215a.mo6839d();
    }

    /* renamed from: a */
    public C5522t mo6837a(long j) {
        return this.f18215a.mo6837a(j);
    }

    public C5522t l_() {
        return this.f18215a.l_();
    }

    /* renamed from: f */
    public C5522t mo6840f() {
        return this.f18215a.mo6840f();
    }

    /* renamed from: g */
    public void mo6841g() throws IOException {
        this.f18215a.mo6841g();
    }
}
