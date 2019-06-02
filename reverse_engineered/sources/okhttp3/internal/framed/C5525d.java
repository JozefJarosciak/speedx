package okhttp3.internal.framed;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alipay.sdk.data.C0847a;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.C5492r;
import okio.C5520s;
import okio.C5522t;
import okio.C5523a;
import okio.C5636e;
import okio.C5637c;

/* compiled from: FramedStream */
/* renamed from: okhttp3.internal.framed.d */
public final class C5525d {
    /* renamed from: d */
    static final /* synthetic */ boolean f17793d = (!C5525d.class.desiredAssertionStatus());
    /* renamed from: a */
    long f17794a = 0;
    /* renamed from: b */
    long f17795b;
    /* renamed from: c */
    final C5519a f17796c;
    /* renamed from: e */
    private final int f17797e;
    /* renamed from: f */
    private final C5517c f17798f;
    /* renamed from: g */
    private final List<C5526e> f17799g;
    /* renamed from: h */
    private List<C5526e> f17800h;
    /* renamed from: i */
    private final C5521b f17801i;
    /* renamed from: j */
    private final C5524c f17802j = new C5524c(this);
    /* renamed from: k */
    private final C5524c f17803k = new C5524c(this);
    /* renamed from: l */
    private ErrorCode f17804l = null;

    /* compiled from: FramedStream */
    /* renamed from: okhttp3.internal.framed.d$a */
    final class C5519a implements C5492r {
        /* renamed from: a */
        static final /* synthetic */ boolean f17772a = (!C5525d.class.desiredAssertionStatus());
        /* renamed from: b */
        final /* synthetic */ C5525d f17773b;
        /* renamed from: c */
        private final C5637c f17774c = new C5637c();
        /* renamed from: d */
        private boolean f17775d;
        /* renamed from: e */
        private boolean f17776e;

        C5519a(C5525d c5525d) {
            this.f17773b = c5525d;
        }

        /* renamed from: a */
        public void mo6706a(C5637c c5637c, long j) throws IOException {
            if (f17772a || !Thread.holdsLock(this.f17773b)) {
                this.f17774c.mo6706a(c5637c, j);
                while (this.f17774c.m20623a() >= PlaybackStateCompat.ACTION_PREPARE) {
                    m19960a(false);
                }
                return;
            }
            throw new AssertionError();
        }

        /* renamed from: a */
        private void m19960a(boolean z) throws IOException {
            synchronized (this.f17773b) {
                this.f17773b.f17803k.m19986c();
                while (this.f17773b.f17795b <= 0 && !this.f17776e && !this.f17775d && this.f17773b.f17804l == null) {
                    try {
                        this.f17773b.m20001l();
                    } catch (Throwable th) {
                        this.f17773b.f17803k.m19989b();
                    }
                }
                this.f17773b.f17803k.m19989b();
                this.f17773b.m20000k();
                long min = Math.min(this.f17773b.f17795b, this.f17774c.m20623a());
                C5525d c5525d = this.f17773b;
                c5525d.f17795b -= min;
            }
            this.f17773b.f17803k.m19986c();
            try {
                C5517c a = this.f17773b.f17798f;
                int b = this.f17773b.f17797e;
                boolean z2 = z && min == this.f17774c.m20623a();
                a.m19951a(b, z2, this.f17774c, min);
            } finally {
                this.f17773b.f17803k.m19989b();
            }
        }

        public void flush() throws IOException {
            if (f17772a || !Thread.holdsLock(this.f17773b)) {
                synchronized (this.f17773b) {
                    this.f17773b.m20000k();
                }
                while (this.f17774c.m20623a() > 0) {
                    m19960a(false);
                    this.f17773b.f17798f.m19958c();
                }
                return;
            }
            throw new AssertionError();
        }

        public C5522t timeout() {
            return this.f17773b.f17803k;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
            r6 = this;
            r4 = 0;
            r2 = 1;
            r0 = f17772a;
            if (r0 != 0) goto L_0x0015;
        L_0x0007:
            r0 = r6.f17773b;
            r0 = java.lang.Thread.holdsLock(r0);
            if (r0 == 0) goto L_0x0015;
        L_0x000f:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
        L_0x0015:
            r1 = r6.f17773b;
            monitor-enter(r1);
            r0 = r6.f17775d;	 Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x001e;
        L_0x001c:
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
        L_0x001d:
            return;
        L_0x001e:
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
            r0 = r6.f17773b;
            r0 = r0.f17796c;
            r0 = r0.f17776e;
            if (r0 != 0) goto L_0x0052;
        L_0x0027:
            r0 = r6.f17774c;
            r0 = r0.m20623a();
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0042;
        L_0x0031:
            r0 = r6.f17774c;
            r0 = r0.m20623a();
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0052;
        L_0x003b:
            r6.m19960a(r2);
            goto L_0x0031;
        L_0x003f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
            throw r0;
        L_0x0042:
            r0 = r6.f17773b;
            r0 = r0.f17798f;
            r1 = r6.f17773b;
            r1 = r1.f17797e;
            r3 = 0;
            r0.m19951a(r1, r2, r3, r4);
        L_0x0052:
            r1 = r6.f17773b;
            monitor-enter(r1);
            r0 = 1;
            r6.f17775d = r0;	 Catch:{ all -> 0x0068 }
            monitor-exit(r1);	 Catch:{ all -> 0x0068 }
            r0 = r6.f17773b;
            r0 = r0.f17798f;
            r0.m19958c();
            r0 = r6.f17773b;
            r0.m19999j();
            goto L_0x001d;
        L_0x0068:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0068 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.framed.d.a.close():void");
        }
    }

    /* compiled from: FramedStream */
    /* renamed from: okhttp3.internal.framed.d$b */
    private final class C5521b implements C5520s {
        /* renamed from: a */
        static final /* synthetic */ boolean f17777a = (!C5525d.class.desiredAssertionStatus());
        /* renamed from: b */
        final /* synthetic */ C5525d f17778b;
        /* renamed from: c */
        private final C5637c f17779c;
        /* renamed from: d */
        private final C5637c f17780d;
        /* renamed from: e */
        private final long f17781e;
        /* renamed from: f */
        private boolean f17782f;
        /* renamed from: g */
        private boolean f17783g;

        private C5521b(C5525d c5525d, long j) {
            this.f17778b = c5525d;
            this.f17779c = new C5637c();
            this.f17780d = new C5637c();
            this.f17781e = j;
        }

        public long read(C5637c c5637c, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            long j2;
            synchronized (this.f17778b) {
                m19965a();
                m19968b();
                if (this.f17780d.m20623a() == 0) {
                    j2 = -1;
                } else {
                    j2 = this.f17780d.read(c5637c, Math.min(j, this.f17780d.m20623a()));
                    C5525d c5525d = this.f17778b;
                    c5525d.f17794a += j2;
                    if (this.f17778b.f17794a >= ((long) (this.f17778b.f17798f.f17753e.m20125f(65536) / 2))) {
                        this.f17778b.f17798f.m19949a(this.f17778b.f17797e, this.f17778b.f17794a);
                        this.f17778b.f17794a = 0;
                    }
                    synchronized (this.f17778b.f17798f) {
                        C5517c a = this.f17778b.f17798f;
                        a.f17751c += j2;
                        if (this.f17778b.f17798f.f17751c >= ((long) (this.f17778b.f17798f.f17753e.m20125f(65536) / 2))) {
                            this.f17778b.f17798f.m19949a(0, this.f17778b.f17798f.f17751c);
                            this.f17778b.f17798f.f17751c = 0;
                        }
                    }
                }
            }
            return j2;
        }

        /* renamed from: a */
        private void m19965a() throws IOException {
            this.f17778b.f17802j.m19986c();
            while (this.f17780d.m20623a() == 0 && !this.f17783g && !this.f17782f && this.f17778b.f17804l == null) {
                try {
                    this.f17778b.m20001l();
                } catch (Throwable th) {
                    this.f17778b.f17802j.m19989b();
                }
            }
            this.f17778b.f17802j.m19989b();
        }

        /* renamed from: a */
        void m19970a(C5636e c5636e, long j) throws IOException {
            if (f17777a || !Thread.holdsLock(this.f17778b)) {
                while (j > 0) {
                    boolean z;
                    Object obj;
                    synchronized (this.f17778b) {
                        z = this.f17783g;
                        obj = this.f17780d.m20623a() + j > this.f17781e ? 1 : null;
                    }
                    if (obj != null) {
                        c5636e.mo6822h(j);
                        this.f17778b.m20007b(ErrorCode.FLOW_CONTROL_ERROR);
                        return;
                    } else if (z) {
                        c5636e.mo6822h(j);
                        return;
                    } else {
                        long read = c5636e.read(this.f17779c, j);
                        if (read == -1) {
                            throw new EOFException();
                        }
                        j -= read;
                        synchronized (this.f17778b) {
                            if (this.f17780d.m20623a() == 0) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            this.f17780d.mo6808a(this.f17779c);
                            if (obj != null) {
                                this.f17778b.notifyAll();
                            }
                        }
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        public C5522t timeout() {
            return this.f17778b.f17802j;
        }

        public void close() throws IOException {
            synchronized (this.f17778b) {
                this.f17782f = true;
                this.f17780d.m20685t();
                this.f17778b.notifyAll();
            }
            this.f17778b.m19999j();
        }

        /* renamed from: b */
        private void m19968b() throws IOException {
            if (this.f17782f) {
                throw new IOException("stream closed");
            } else if (this.f17778b.f17804l != null) {
                throw new StreamResetException(this.f17778b.f17804l);
            }
        }
    }

    /* compiled from: FramedStream */
    /* renamed from: okhttp3.internal.framed.d$c */
    class C5524c extends C5523a {
        /* renamed from: a */
        final /* synthetic */ C5525d f17792a;

        C5524c(C5525d c5525d) {
            this.f17792a = c5525d;
        }

        /* renamed from: a */
        protected void mo6725a() {
            this.f17792a.m20007b(ErrorCode.CANCEL);
        }

        /* renamed from: a */
        protected IOException mo6724a(IOException iOException) {
            IOException socketTimeoutException = new SocketTimeoutException(C0847a.f2089f);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        /* renamed from: b */
        public void m19989b() throws IOException {
            if (i_()) {
                throw mo6724a(null);
            }
        }
    }

    C5525d(int i, C5517c c5517c, boolean z, boolean z2, List<C5526e> list) {
        if (c5517c == null) {
            throw new NullPointerException("connection == null");
        } else if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        } else {
            this.f17797e = i;
            this.f17798f = c5517c;
            this.f17795b = (long) c5517c.f17754f.m20125f(65536);
            this.f17801i = new C5521b((long) c5517c.f17753e.m20125f(65536));
            this.f17796c = new C5519a(this);
            this.f17801i.f17783g = z2;
            this.f17796c.f17776e = z;
            this.f17799g = list;
        }
    }

    /* renamed from: a */
    public int m20002a() {
        return this.f17797e;
    }

    /* renamed from: b */
    public synchronized boolean m20008b() {
        boolean z = false;
        synchronized (this) {
            if (this.f17804l == null) {
                if (!(this.f17801i.f17783g || this.f17801i.f17782f) || (!(this.f17796c.f17776e || this.f17796c.f17775d) || this.f17800h == null)) {
                    z = true;
                }
            }
        }
        return z;
    }

    /* renamed from: c */
    public boolean m20010c() {
        boolean z;
        if ((this.f17797e & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        return this.f17798f.f17750b == z;
    }

    /* renamed from: d */
    public synchronized List<C5526e> m20011d() throws IOException {
        this.f17802j.m19986c();
        while (this.f17800h == null && this.f17804l == null) {
            try {
                m20001l();
            } catch (Throwable th) {
                this.f17802j.m19989b();
            }
        }
        this.f17802j.m19989b();
        if (this.f17800h != null) {
        } else {
            throw new StreamResetException(this.f17804l);
        }
        return this.f17800h;
    }

    /* renamed from: e */
    public C5522t m20012e() {
        return this.f17802j;
    }

    /* renamed from: f */
    public C5522t m20013f() {
        return this.f17803k;
    }

    /* renamed from: g */
    public C5520s m20014g() {
        return this.f17801i;
    }

    /* renamed from: h */
    public C5492r m20015h() {
        synchronized (this) {
            if (this.f17800h != null || m20010c()) {
            } else {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f17796c;
    }

    /* renamed from: a */
    public void m20005a(ErrorCode errorCode) throws IOException {
        if (m19994d(errorCode)) {
            this.f17798f.m19957b(this.f17797e, errorCode);
        }
    }

    /* renamed from: b */
    public void m20007b(ErrorCode errorCode) {
        if (m19994d(errorCode)) {
            this.f17798f.m19950a(this.f17797e, errorCode);
        }
    }

    /* renamed from: d */
    private boolean m19994d(ErrorCode errorCode) {
        if (f17793d || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.f17804l != null) {
                    return false;
                } else if (this.f17801i.f17783g && this.f17796c.f17776e) {
                    return false;
                } else {
                    this.f17804l = errorCode;
                    notifyAll();
                    this.f17798f.m19956b(this.f17797e);
                    return true;
                }
            }
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    void m20004a(List<C5526e> list, HeadersMode headersMode) {
        if (f17793d || !Thread.holdsLock(this)) {
            ErrorCode errorCode = null;
            boolean z = true;
            synchronized (this) {
                if (this.f17800h == null) {
                    if (headersMode.failIfHeadersAbsent()) {
                        errorCode = ErrorCode.PROTOCOL_ERROR;
                    } else {
                        this.f17800h = list;
                        z = m20008b();
                        notifyAll();
                    }
                } else if (headersMode.failIfHeadersPresent()) {
                    errorCode = ErrorCode.STREAM_IN_USE;
                } else {
                    List arrayList = new ArrayList();
                    arrayList.addAll(this.f17800h);
                    arrayList.addAll(list);
                    this.f17800h = arrayList;
                }
            }
            if (errorCode != null) {
                m20007b(errorCode);
                return;
            } else if (!z) {
                this.f17798f.m19956b(this.f17797e);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    void m20006a(C5636e c5636e, int i) throws IOException {
        if (f17793d || !Thread.holdsLock(this)) {
            this.f17801i.m19970a(c5636e, (long) i);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: i */
    void m20016i() {
        if (f17793d || !Thread.holdsLock(this)) {
            boolean b;
            synchronized (this) {
                this.f17801i.f17783g = true;
                b = m20008b();
                notifyAll();
            }
            if (!b) {
                this.f17798f.m19956b(this.f17797e);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    synchronized void m20009c(ErrorCode errorCode) {
        if (this.f17804l == null) {
            this.f17804l = errorCode;
            notifyAll();
        }
    }

    /* renamed from: j */
    private void m19999j() throws IOException {
        if (f17793d || !Thread.holdsLock(this)) {
            Object obj;
            boolean b;
            synchronized (this) {
                obj = (!this.f17801i.f17783g && this.f17801i.f17782f && (this.f17796c.f17776e || this.f17796c.f17775d)) ? 1 : null;
                b = m20008b();
            }
            if (obj != null) {
                m20005a(ErrorCode.CANCEL);
                return;
            } else if (!b) {
                this.f17798f.m19956b(this.f17797e);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    void m20003a(long j) {
        this.f17795b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* renamed from: k */
    private void m20000k() throws IOException {
        if (this.f17796c.f17775d) {
            throw new IOException("stream closed");
        } else if (this.f17796c.f17776e) {
            throw new IOException("stream finished");
        } else if (this.f17804l != null) {
            throw new StreamResetException(this.f17804l);
        }
    }

    /* renamed from: l */
    private void m20001l() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }
}
