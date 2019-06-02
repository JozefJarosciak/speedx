package okhttp3.internal;

import com.baidu.mapapi.UIMsg.m_AppUI;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import okhttp3.internal.p206a.C5477a;
import okio.C5492r;
import okio.C5522t;
import okio.C5635d;
import okio.C5637c;

/* compiled from: DiskLruCache */
/* renamed from: okhttp3.internal.c */
public final class C5496c implements Closeable, Flushable {
    /* renamed from: a */
    static final Pattern f17686a = Pattern.compile("[a-z0-9_-]{1,120}");
    /* renamed from: b */
    static final /* synthetic */ boolean f17687b = (!C5496c.class.desiredAssertionStatus());
    /* renamed from: p */
    private static final C5492r f17688p = new C54931();
    /* renamed from: c */
    private final C5477a f17689c;
    /* renamed from: d */
    private long f17690d;
    /* renamed from: e */
    private final int f17691e;
    /* renamed from: f */
    private long f17692f;
    /* renamed from: g */
    private C5635d f17693g;
    /* renamed from: h */
    private final LinkedHashMap<String, C5495b> f17694h;
    /* renamed from: i */
    private int f17695i;
    /* renamed from: j */
    private boolean f17696j;
    /* renamed from: k */
    private boolean f17697k;
    /* renamed from: l */
    private boolean f17698l;
    /* renamed from: m */
    private long f17699m;
    /* renamed from: n */
    private final Executor f17700n;
    /* renamed from: o */
    private final Runnable f17701o;

    /* compiled from: DiskLruCache */
    /* renamed from: okhttp3.internal.c$1 */
    static class C54931 implements C5492r {
        C54931() {
        }

        /* renamed from: a */
        public void mo6706a(C5637c c5637c, long j) throws IOException {
            c5637c.mo6822h(j);
        }

        public void flush() throws IOException {
        }

        public C5522t timeout() {
            return C5522t.f17784b;
        }

        public void close() throws IOException {
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: okhttp3.internal.c$a */
    public final class C5494a {
        /* renamed from: a */
        final /* synthetic */ C5496c f17675a;
        /* renamed from: b */
        private final C5495b f17676b;
        /* renamed from: c */
        private final boolean[] f17677c;
        /* renamed from: d */
        private boolean f17678d;

        /* renamed from: a */
        void m19815a() {
            if (this.f17676b.f17684f == this) {
                for (int i = 0; i < this.f17675a.f17691e; i++) {
                    try {
                        this.f17675a.f17689c.mo6690a(this.f17676b.f17682d[i]);
                    } catch (IOException e) {
                    }
                }
                this.f17676b.f17684f = null;
            }
        }

        /* renamed from: b */
        public void m19816b() throws IOException {
            synchronized (this.f17675a) {
                if (this.f17678d) {
                    throw new IllegalStateException();
                }
                if (this.f17676b.f17684f == this) {
                    this.f17675a.m19828a(this, false);
                }
                this.f17678d = true;
            }
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: okhttp3.internal.c$b */
    private final class C5495b {
        /* renamed from: a */
        private final String f17679a;
        /* renamed from: b */
        private final long[] f17680b;
        /* renamed from: c */
        private final File[] f17681c;
        /* renamed from: d */
        private final File[] f17682d;
        /* renamed from: e */
        private boolean f17683e;
        /* renamed from: f */
        private C5494a f17684f;
        /* renamed from: g */
        private long f17685g;

        /* renamed from: a */
        void m19826a(C5635d c5635d) throws IOException {
            for (long l : this.f17680b) {
                c5635d.mo6828k(32).mo6830l(l);
            }
        }
    }

    /* renamed from: a */
    private synchronized void m19828a(C5494a c5494a, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            C5495b a = c5494a.f17676b;
            if (a.f17684f != c5494a) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f17683e) {
                    int i2 = 0;
                    while (i2 < this.f17691e) {
                        if (!c5494a.f17677c[i2]) {
                            c5494a.m19816b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!this.f17689c.mo6692b(a.f17682d[i2])) {
                            c5494a.m19816b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.f17691e) {
                File file = a.f17682d[i];
                if (!z) {
                    this.f17689c.mo6690a(file);
                } else if (this.f17689c.mo6692b(file)) {
                    File file2 = a.f17681c[i];
                    this.f17689c.mo6691a(file, file2);
                    long j = a.f17680b[i];
                    long c = this.f17689c.mo6693c(file2);
                    a.f17680b[i] = c;
                    this.f17692f = (this.f17692f - j) + c;
                }
                i++;
            }
            this.f17695i++;
            a.f17684f = null;
            if ((a.f17683e | z) != 0) {
                a.f17683e = true;
                this.f17693g.mo6811b("CLEAN").mo6828k(32);
                this.f17693g.mo6811b(a.f17679a);
                a.m19826a(this.f17693g);
                this.f17693g.mo6828k(10);
                if (z) {
                    long j2 = this.f17699m;
                    this.f17699m = 1 + j2;
                    a.f17685g = j2;
                }
            } else {
                this.f17694h.remove(a.f17679a);
                this.f17693g.mo6811b("REMOVE").mo6828k(32);
                this.f17693g.mo6811b(a.f17679a);
                this.f17693g.mo6828k(10);
            }
            this.f17693g.flush();
            if (this.f17692f > this.f17690d || m19832b()) {
                this.f17700n.execute(this.f17701o);
            }
        }
    }

    /* renamed from: b */
    private boolean m19832b() {
        return this.f17695i >= m_AppUI.MSG_APP_DATA_OK && this.f17695i >= this.f17694h.size();
    }

    /* renamed from: a */
    private boolean m19830a(C5495b c5495b) throws IOException {
        if (c5495b.f17684f != null) {
            c5495b.f17684f.m19815a();
        }
        for (int i = 0; i < this.f17691e; i++) {
            this.f17689c.mo6690a(c5495b.f17681c[i]);
            this.f17692f -= c5495b.f17680b[i];
            c5495b.f17680b[i] = 0;
        }
        this.f17695i++;
        this.f17693g.mo6811b("REMOVE").mo6828k(32).mo6811b(c5495b.f17679a).mo6828k(10);
        this.f17694h.remove(c5495b.f17679a);
        if (m19832b()) {
            this.f17700n.execute(this.f17701o);
        }
        return true;
    }

    /* renamed from: a */
    public synchronized boolean m19835a() {
        return this.f17697k;
    }

    /* renamed from: c */
    private synchronized void m19833c() {
        if (m19835a()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.f17696j) {
            m19833c();
            m19834d();
            this.f17693g.flush();
        }
    }

    public synchronized void close() throws IOException {
        if (!this.f17696j || this.f17697k) {
            this.f17697k = true;
        } else {
            for (C5495b c5495b : (C5495b[]) this.f17694h.values().toArray(new C5495b[this.f17694h.size()])) {
                if (c5495b.f17684f != null) {
                    c5495b.f17684f.m19816b();
                }
            }
            m19834d();
            this.f17693g.close();
            this.f17693g = null;
            this.f17697k = true;
        }
    }

    /* renamed from: d */
    private void m19834d() throws IOException {
        while (this.f17692f > this.f17690d) {
            m19830a((C5495b) this.f17694h.values().iterator().next());
        }
        this.f17698l = false;
    }
}
