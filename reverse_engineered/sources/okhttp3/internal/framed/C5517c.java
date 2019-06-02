package okhttp3.internal.framed;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.C5503h;
import okhttp3.internal.C5586l;
import okhttp3.internal.framed.C5501a.C5500a;
import okio.ByteString;
import okio.C5635d;
import okio.C5636e;
import okio.C5637c;

/* compiled from: FramedConnection */
/* renamed from: okhttp3.internal.framed.c */
public final class C5517c implements Closeable {
    /* renamed from: k */
    static final /* synthetic */ boolean f17747k;
    /* renamed from: l */
    private static final ExecutorService f17748l = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), C5586l.m20324a("OkHttp FramedConnection", true));
    /* renamed from: a */
    final Protocol f17749a;
    /* renamed from: b */
    final boolean f17750b;
    /* renamed from: c */
    long f17751c;
    /* renamed from: d */
    long f17752d;
    /* renamed from: e */
    C5545l f17753e;
    /* renamed from: f */
    final C5545l f17754f;
    /* renamed from: g */
    final C5534n f17755g;
    /* renamed from: h */
    final Socket f17756h;
    /* renamed from: i */
    final C5502b f17757i;
    /* renamed from: j */
    final C5516c f17758j;
    /* renamed from: m */
    private final C5479b f17759m;
    /* renamed from: n */
    private final Map<Integer, C5525d> f17760n;
    /* renamed from: o */
    private final String f17761o;
    /* renamed from: p */
    private int f17762p;
    /* renamed from: q */
    private int f17763q;
    /* renamed from: r */
    private boolean f17764r;
    /* renamed from: s */
    private long f17765s;
    /* renamed from: t */
    private final ExecutorService f17766t;
    /* renamed from: u */
    private Map<Integer, C5542j> f17767u;
    /* renamed from: v */
    private final C5543k f17768v;
    /* renamed from: w */
    private int f17769w;
    /* renamed from: x */
    private boolean f17770x;
    /* renamed from: y */
    private final Set<Integer> f17771y;

    /* compiled from: FramedConnection */
    /* renamed from: okhttp3.internal.framed.c$b */
    public static abstract class C5479b {
        /* renamed from: a */
        public static final C5479b f17636a = new C55121();

        /* compiled from: FramedConnection */
        /* renamed from: okhttp3.internal.framed.c$b$1 */
        static class C55121 extends C5479b {
            C55121() {
            }

            /* renamed from: a */
            public void mo6696a(C5525d c5525d) throws IOException {
                c5525d.m20005a(ErrorCode.REFUSED_STREAM);
            }
        }

        /* renamed from: a */
        public abstract void mo6696a(C5525d c5525d) throws IOException;

        /* renamed from: a */
        public void mo6695a(C5517c c5517c) {
        }
    }

    /* compiled from: FramedConnection */
    /* renamed from: okhttp3.internal.framed.c$a */
    public static class C5511a {
        /* renamed from: a */
        private Socket f17732a;
        /* renamed from: b */
        private String f17733b;
        /* renamed from: c */
        private C5636e f17734c;
        /* renamed from: d */
        private C5635d f17735d;
        /* renamed from: e */
        private C5479b f17736e = C5479b.f17636a;
        /* renamed from: f */
        private Protocol f17737f = Protocol.SPDY_3;
        /* renamed from: g */
        private C5543k f17738g = C5543k.f17862a;
        /* renamed from: h */
        private boolean f17739h;

        public C5511a(boolean z) throws IOException {
            this.f17739h = z;
        }

        /* renamed from: a */
        public C5511a m19893a(Socket socket, String str, C5636e c5636e, C5635d c5635d) {
            this.f17732a = socket;
            this.f17733b = str;
            this.f17734c = c5636e;
            this.f17735d = c5635d;
            return this;
        }

        /* renamed from: a */
        public C5511a m19895a(C5479b c5479b) {
            this.f17736e = c5479b;
            return this;
        }

        /* renamed from: a */
        public C5511a m19894a(Protocol protocol) {
            this.f17737f = protocol;
            return this;
        }

        /* renamed from: a */
        public C5517c m19896a() throws IOException {
            return new C5517c();
        }
    }

    /* compiled from: FramedConnection */
    /* renamed from: okhttp3.internal.framed.c$c */
    class C5516c extends C5503h implements C5500a {
        /* renamed from: a */
        final C5501a f17745a;
        /* renamed from: c */
        final /* synthetic */ C5517c f17746c;

        private C5516c(C5517c c5517c, C5501a c5501a) {
            this.f17746c = c5517c;
            super("OkHttp %s", c5517c.f17761o);
            this.f17745a = c5501a;
        }

        /* renamed from: b */
        protected void mo6710b() {
            ErrorCode errorCode;
            Throwable th;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            try {
                if (!this.f17746c.f17750b) {
                    this.f17745a.mo6726a();
                }
                do {
                } while (this.f17745a.mo6727a(this));
                try {
                    this.f17746c.m19918a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
                } catch (IOException e) {
                }
                C5586l.m20327a(this.f17745a);
            } catch (IOException e2) {
                errorCode = ErrorCode.PROTOCOL_ERROR;
                try {
                    this.f17746c.m19918a(errorCode, ErrorCode.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
                C5586l.m20327a(this.f17745a);
            } catch (Throwable th2) {
                th = th2;
                this.f17746c.m19918a(errorCode, errorCode3);
                C5586l.m20327a(this.f17745a);
                throw th;
            }
        }

        /* renamed from: a */
        public void mo6718a(boolean z, int i, C5636e c5636e, int i2) throws IOException {
            if (this.f17746c.m19939d(i)) {
                this.f17746c.m19917a(i, c5636e, i2, z);
                return;
            }
            C5525d a = this.f17746c.m19947a(i);
            if (a == null) {
                this.f17746c.m19950a(i, ErrorCode.INVALID_STREAM);
                c5636e.mo6822h((long) i2);
                return;
            }
            a.m20006a(c5636e, i2);
            if (z) {
                a.m20016i();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: a */
        public void mo6720a(boolean r9, boolean r10, int r11, int r12, java.util.List<okhttp3.internal.framed.C5526e> r13, okhttp3.internal.framed.HeadersMode r14) {
            /*
            r8 = this;
            r0 = r8.f17746c;
            r0 = r0.m19939d(r11);
            if (r0 == 0) goto L_0x000e;
        L_0x0008:
            r0 = r8.f17746c;
            r0.m19916a(r11, r13, r10);
        L_0x000d:
            return;
        L_0x000e:
            r6 = r8.f17746c;
            monitor-enter(r6);
            r0 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r0 = r0.f17764r;	 Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x001e;
        L_0x0019:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x001b:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            throw r0;
        L_0x001e:
            r0 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r0 = r0.m19947a(r11);	 Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x008d;
        L_0x0026:
            r0 = r14.failIfStreamAbsent();	 Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0035;
        L_0x002c:
            r0 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r1 = okhttp3.internal.framed.ErrorCode.INVALID_STREAM;	 Catch:{ all -> 0x001b }
            r0.m19950a(r11, r1);	 Catch:{ all -> 0x001b }
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x0035:
            r0 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r0 = r0.f17762p;	 Catch:{ all -> 0x001b }
            if (r11 > r0) goto L_0x003f;
        L_0x003d:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x003f:
            r0 = r11 % 2;
            r1 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r1 = r1.f17763q;	 Catch:{ all -> 0x001b }
            r1 = r1 % 2;
            if (r0 != r1) goto L_0x004d;
        L_0x004b:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x004d:
            r0 = new okhttp3.internal.framed.d;	 Catch:{ all -> 0x001b }
            r2 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r1 = r11;
            r3 = r9;
            r4 = r10;
            r5 = r13;
            r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x001b }
            r1 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r1.f17762p = r11;	 Catch:{ all -> 0x001b }
            r1 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r1 = r1.f17760n;	 Catch:{ all -> 0x001b }
            r2 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x001b }
            r1.put(r2, r0);	 Catch:{ all -> 0x001b }
            r1 = okhttp3.internal.framed.C5517c.f17748l;	 Catch:{ all -> 0x001b }
            r2 = new okhttp3.internal.framed.c$c$1;	 Catch:{ all -> 0x001b }
            r3 = "OkHttp %s stream %d";
            r4 = 2;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x001b }
            r5 = 0;
            r7 = r8.f17746c;	 Catch:{ all -> 0x001b }
            r7 = r7.f17761o;	 Catch:{ all -> 0x001b }
            r4[r5] = r7;	 Catch:{ all -> 0x001b }
            r5 = 1;
            r7 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x001b }
            r4[r5] = r7;	 Catch:{ all -> 0x001b }
            r2.<init>(r8, r3, r4, r0);	 Catch:{ all -> 0x001b }
            r1.execute(r2);	 Catch:{ all -> 0x001b }
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x008d:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            r1 = r14.failIfStreamPresent();
            if (r1 == 0) goto L_0x00a0;
        L_0x0094:
            r1 = okhttp3.internal.framed.ErrorCode.PROTOCOL_ERROR;
            r0.m20007b(r1);
            r0 = r8.f17746c;
            r0.m19956b(r11);
            goto L_0x000d;
        L_0x00a0:
            r0.m20004a(r13, r14);
            if (r10 == 0) goto L_0x000d;
        L_0x00a5:
            r0.m20016i();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.framed.c.c.a(boolean, boolean, int, int, java.util.List, okhttp3.internal.framed.HeadersMode):void");
        }

        /* renamed from: a */
        public void mo6715a(int i, ErrorCode errorCode) {
            if (this.f17746c.m19939d(i)) {
                this.f17746c.m19937c(i, errorCode);
                return;
            }
            C5525d b = this.f17746c.m19956b(i);
            if (b != null) {
                b.m20009c(errorCode);
            }
        }

        /* renamed from: a */
        public void mo6719a(boolean z, C5545l c5545l) {
            C5525d[] c5525dArr;
            long j;
            synchronized (this.f17746c) {
                int f = this.f17746c.f17754f.m20125f(65536);
                if (z) {
                    this.f17746c.f17754f.m20116a();
                }
                this.f17746c.f17754f.m20117a(c5545l);
                if (this.f17746c.m19946a() == Protocol.HTTP_2) {
                    m19901a(c5545l);
                }
                int f2 = this.f17746c.f17754f.m20125f(65536);
                if (f2 == -1 || f2 == f) {
                    c5525dArr = null;
                    j = 0;
                } else {
                    long j2 = (long) (f2 - f);
                    if (!this.f17746c.f17770x) {
                        this.f17746c.m19952a(j2);
                        this.f17746c.f17770x = true;
                    }
                    if (this.f17746c.f17760n.isEmpty()) {
                        j = j2;
                        c5525dArr = null;
                    } else {
                        j = j2;
                        c5525dArr = (C5525d[]) this.f17746c.f17760n.values().toArray(new C5525d[this.f17746c.f17760n.size()]);
                    }
                }
                C5517c.f17748l.execute(new C5503h(this, "OkHttp %s settings", this.f17746c.f17761o) {
                    /* renamed from: a */
                    final /* synthetic */ C5516c f17742a;

                    /* renamed from: b */
                    public void mo6710b() {
                        this.f17742a.f17746c.f17759m.mo6695a(this.f17742a.f17746c);
                    }
                });
            }
            if (c5525dArr != null && j != 0) {
                for (C5525d c5525d : c5525dArr) {
                    synchronized (c5525d) {
                        c5525d.m20003a(j);
                    }
                }
            }
        }

        /* renamed from: a */
        private void m19901a(final C5545l c5545l) {
            C5517c.f17748l.execute(new C5503h(this, "OkHttp %s ACK Settings", new Object[]{this.f17746c.f17761o}) {
                /* renamed from: c */
                final /* synthetic */ C5516c f17744c;

                /* renamed from: b */
                public void mo6710b() {
                    try {
                        this.f17744c.f17746c.f17757i.mo6733a(c5545l);
                    } catch (IOException e) {
                    }
                }
            });
        }

        /* renamed from: a */
        public void mo6711a() {
        }

        /* renamed from: a */
        public void mo6717a(boolean z, int i, int i2) {
            if (z) {
                C5542j c = this.f17746c.m19935c(i);
                if (c != null) {
                    c.m20105b();
                    return;
                }
                return;
            }
            this.f17746c.m19925a(true, i, i2, null);
        }

        /* renamed from: a */
        public void mo6716a(int i, ErrorCode errorCode, ByteString byteString) {
            if (byteString.size() > 0) {
            }
            synchronized (this.f17746c) {
                C5525d[] c5525dArr = (C5525d[]) this.f17746c.f17760n.values().toArray(new C5525d[this.f17746c.f17760n.size()]);
                this.f17746c.f17764r = true;
            }
            for (C5525d c5525d : c5525dArr) {
                if (c5525d.m20002a() > i && c5525d.m20010c()) {
                    c5525d.m20009c(ErrorCode.REFUSED_STREAM);
                    this.f17746c.m19956b(c5525d.m20002a());
                }
            }
        }

        /* renamed from: a */
        public void mo6714a(int i, long j) {
            if (i == 0) {
                synchronized (this.f17746c) {
                    C5517c c5517c = this.f17746c;
                    c5517c.f17752d += j;
                    this.f17746c.notifyAll();
                }
                return;
            }
            C5525d a = this.f17746c.m19947a(i);
            if (a != null) {
                synchronized (a) {
                    a.m20003a(j);
                }
            }
        }

        /* renamed from: a */
        public void mo6712a(int i, int i2, int i3, boolean z) {
        }

        /* renamed from: a */
        public void mo6713a(int i, int i2, List<C5526e> list) {
            this.f17746c.m19915a(i2, (List) list);
        }
    }

    static {
        boolean z;
        if (C5517c.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        f17747k = z;
    }

    private C5517c(C5511a c5511a) throws IOException {
        int i = 2;
        this.f17760n = new HashMap();
        this.f17765s = System.nanoTime();
        this.f17751c = 0;
        this.f17753e = new C5545l();
        this.f17754f = new C5545l();
        this.f17770x = false;
        this.f17771y = new LinkedHashSet();
        this.f17749a = c5511a.f17737f;
        this.f17768v = c5511a.f17738g;
        this.f17750b = c5511a.f17739h;
        this.f17759m = c5511a.f17736e;
        this.f17763q = c5511a.f17739h ? 1 : 2;
        if (c5511a.f17739h && this.f17749a == Protocol.HTTP_2) {
            this.f17763q += 2;
        }
        if (c5511a.f17739h) {
            i = 1;
        }
        this.f17769w = i;
        if (c5511a.f17739h) {
            this.f17753e.m20115a(7, 0, 16777216);
        }
        this.f17761o = c5511a.f17733b;
        if (this.f17749a == Protocol.HTTP_2) {
            this.f17755g = new C5535g();
            this.f17766t = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), C5586l.m20324a(C5586l.m20319a("OkHttp %s Push Observer", this.f17761o), true));
            this.f17754f.m20115a(7, 0, 65535);
            this.f17754f.m20115a(5, 0, 16384);
        } else if (this.f17749a == Protocol.SPDY_3) {
            this.f17755g = new C5548m();
            this.f17766t = null;
        } else {
            throw new AssertionError(this.f17749a);
        }
        this.f17752d = (long) this.f17754f.m20125f(65536);
        this.f17756h = c5511a.f17732a;
        this.f17757i = this.f17755g.mo6741a(c5511a.f17735d, this.f17750b);
        this.f17758j = new C5516c(this.f17755g.mo6740a(c5511a.f17734c, this.f17750b));
    }

    /* renamed from: a */
    public Protocol m19946a() {
        return this.f17749a;
    }

    /* renamed from: a */
    synchronized C5525d m19947a(int i) {
        return (C5525d) this.f17760n.get(Integer.valueOf(i));
    }

    /* renamed from: b */
    synchronized C5525d m19956b(int i) {
        C5525d c5525d;
        c5525d = (C5525d) this.f17760n.remove(Integer.valueOf(i));
        if (c5525d != null && this.f17760n.isEmpty()) {
            m19930b(true);
        }
        notifyAll();
        return c5525d;
    }

    /* renamed from: b */
    private synchronized void m19930b(boolean z) {
        this.f17765s = z ? System.nanoTime() : Long.MAX_VALUE;
    }

    /* renamed from: b */
    public synchronized int m19955b() {
        return this.f17754f.m20123d(Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public C5525d m19948a(List<C5526e> list, boolean z, boolean z2) throws IOException {
        return m19914a(0, (List) list, z, z2);
    }

    /* renamed from: a */
    private C5525d m19914a(int i, List<C5526e> list, boolean z, boolean z2) throws IOException {
        C5525d c5525d;
        boolean z3 = !z;
        boolean z4 = !z2;
        synchronized (this.f17757i) {
            synchronized (this) {
                if (this.f17764r) {
                    throw new IOException("shutdown");
                }
                int i2 = this.f17763q;
                this.f17763q += 2;
                c5525d = new C5525d(i2, this, z3, z4, list);
                Object obj = (!z || this.f17752d == 0 || c5525d.f17795b == 0) ? 1 : null;
                if (c5525d.m20008b()) {
                    this.f17760n.put(Integer.valueOf(i2), c5525d);
                    m19930b(false);
                }
            }
            if (i == 0) {
                this.f17757i.mo6736a(z3, z4, i2, i, list);
            } else if (this.f17750b) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.f17757i.mo6729a(i, i2, (List) list);
            }
        }
        if (obj != null) {
            this.f17757i.mo6737b();
        }
        return c5525d;
    }

    /* renamed from: a */
    public void m19951a(int i, boolean z, C5637c c5637c, long j) throws IOException {
        if (j == 0) {
            this.f17757i.mo6735a(z, i, c5637c, 0);
            return;
        }
        while (j > 0) {
            int min;
            boolean z2;
            synchronized (this) {
                while (this.f17752d <= 0) {
                    try {
                        if (this.f17760n.containsKey(Integer.valueOf(i))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.f17752d), this.f17757i.mo6739c());
                this.f17752d -= (long) min;
            }
            j -= (long) min;
            C5502b c5502b = this.f17757i;
            if (z && j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            c5502b.mo6735a(z2, i, c5637c, min);
        }
    }

    /* renamed from: a */
    void m19952a(long j) {
        this.f17752d += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* renamed from: a */
    void m19950a(int i, ErrorCode errorCode) {
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        f17748l.submit(new C5503h(this, "OkHttp %s stream %d", new Object[]{this.f17761o, Integer.valueOf(i)}) {
            /* renamed from: d */
            final /* synthetic */ C5517c f17708d;

            /* renamed from: b */
            public void mo6710b() {
                try {
                    this.f17708d.m19957b(i2, errorCode2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* renamed from: b */
    void m19957b(int i, ErrorCode errorCode) throws IOException {
        this.f17757i.mo6731a(i, errorCode);
    }

    /* renamed from: a */
    void m19949a(int i, long j) {
        final int i2 = i;
        final long j2 = j;
        f17748l.execute(new C5503h(this, "OkHttp Window Update %s stream %d", new Object[]{this.f17761o, Integer.valueOf(i)}) {
            /* renamed from: d */
            final /* synthetic */ C5517c f17711d;

            /* renamed from: b */
            public void mo6710b() {
                try {
                    this.f17711d.f17757i.mo6730a(i2, j2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* renamed from: a */
    private void m19925a(boolean z, int i, int i2, C5542j c5542j) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final C5542j c5542j2 = c5542j;
        f17748l.execute(new C5503h(this, "OkHttp %s ping %08x%08x", new Object[]{this.f17761o, Integer.valueOf(i), Integer.valueOf(i2)}) {
            /* renamed from: f */
            final /* synthetic */ C5517c f17716f;

            /* renamed from: b */
            public void mo6710b() {
                try {
                    this.f17716f.m19931b(z2, i3, i4, c5542j2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* renamed from: b */
    private void m19931b(boolean z, int i, int i2, C5542j c5542j) throws IOException {
        synchronized (this.f17757i) {
            if (c5542j != null) {
                c5542j.m20104a();
            }
            this.f17757i.mo6734a(z, i, i2);
        }
    }

    /* renamed from: c */
    private synchronized C5542j m19935c(int i) {
        return this.f17767u != null ? (C5542j) this.f17767u.remove(Integer.valueOf(i)) : null;
    }

    /* renamed from: c */
    public void m19958c() throws IOException {
        this.f17757i.mo6737b();
    }

    /* renamed from: a */
    public void m19953a(ErrorCode errorCode) throws IOException {
        synchronized (this.f17757i) {
            synchronized (this) {
                if (this.f17764r) {
                    return;
                }
                this.f17764r = true;
                int i = this.f17762p;
                this.f17757i.mo6732a(i, errorCode, C5586l.f18006a);
            }
        }
    }

    public void close() throws IOException {
        m19918a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* renamed from: a */
    private void m19918a(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        IOException iOException;
        if (f17747k || !Thread.holdsLock(this)) {
            IOException e;
            C5525d[] c5525dArr;
            C5542j[] c5542jArr;
            try {
                m19953a(errorCode);
                iOException = null;
            } catch (IOException e2) {
                iOException = e2;
            }
            synchronized (this) {
                if (this.f17760n.isEmpty()) {
                    c5525dArr = null;
                } else {
                    C5525d[] c5525dArr2 = (C5525d[]) this.f17760n.values().toArray(new C5525d[this.f17760n.size()]);
                    this.f17760n.clear();
                    m19930b(false);
                    c5525dArr = c5525dArr2;
                }
                if (this.f17767u != null) {
                    C5542j[] c5542jArr2 = (C5542j[]) this.f17767u.values().toArray(new C5542j[this.f17767u.size()]);
                    this.f17767u = null;
                    c5542jArr = c5542jArr2;
                } else {
                    c5542jArr = null;
                }
            }
            if (c5525dArr != null) {
                e2 = iOException;
                for (C5525d a : c5525dArr) {
                    try {
                        a.m20005a(errorCode2);
                    } catch (IOException iOException2) {
                        if (e2 != null) {
                            e2 = iOException2;
                        }
                    }
                }
                iOException2 = e2;
            }
            if (c5542jArr != null) {
                for (C5542j c : c5542jArr) {
                    c.m20106c();
                }
            }
            try {
                this.f17757i.close();
                e2 = iOException2;
            } catch (IOException e3) {
                e2 = e3;
                if (iOException2 != null) {
                    e2 = iOException2;
                }
            }
            try {
                this.f17756h.close();
            } catch (IOException e4) {
                e2 = e4;
            }
            if (e2 != null) {
                throw e2;
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: d */
    public void m19959d() throws IOException {
        m19954a(true);
    }

    /* renamed from: a */
    void m19954a(boolean z) throws IOException {
        if (z) {
            this.f17757i.mo6728a();
            this.f17757i.mo6738b(this.f17753e);
            int f = this.f17753e.m20125f(65536);
            if (f != 65536) {
                this.f17757i.mo6730a(0, (long) (f - 65536));
            }
        }
        new Thread(this.f17758j).start();
    }

    /* renamed from: d */
    private boolean m19939d(int i) {
        return this.f17749a == Protocol.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    /* renamed from: a */
    private void m19915a(int i, List<C5526e> list) {
        synchronized (this) {
            if (this.f17771y.contains(Integer.valueOf(i))) {
                m19950a(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.f17771y.add(Integer.valueOf(i));
            final int i2 = i;
            final List<C5526e> list2 = list;
            this.f17766t.execute(new C5503h(this, "OkHttp %s Push Request[%s]", new Object[]{this.f17761o, Integer.valueOf(i)}) {
                /* renamed from: d */
                final /* synthetic */ C5517c f17719d;

                /* renamed from: b */
                public void mo6710b() {
                    if (this.f17719d.f17768v.mo6743a(i2, list2)) {
                        try {
                            this.f17719d.f17757i.mo6731a(i2, ErrorCode.CANCEL);
                            synchronized (this.f17719d) {
                                this.f17719d.f17771y.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private void m19916a(int i, List<C5526e> list, boolean z) {
        final int i2 = i;
        final List<C5526e> list2 = list;
        final boolean z2 = z;
        this.f17766t.execute(new C5503h(this, "OkHttp %s Push Headers[%s]", new Object[]{this.f17761o, Integer.valueOf(i)}) {
            /* renamed from: e */
            final /* synthetic */ C5517c f17723e;

            /* renamed from: b */
            public void mo6710b() {
                boolean a = this.f17723e.f17768v.mo6744a(i2, list2, z2);
                if (a) {
                    try {
                        this.f17723e.f17757i.mo6731a(i2, ErrorCode.CANCEL);
                    } catch (IOException e) {
                        return;
                    }
                }
                if (a || z2) {
                    synchronized (this.f17723e) {
                        this.f17723e.f17771y.remove(Integer.valueOf(i2));
                    }
                }
            }
        });
    }

    /* renamed from: a */
    private void m19917a(int i, C5636e c5636e, int i2, boolean z) throws IOException {
        final C5637c c5637c = new C5637c();
        c5636e.mo6809a((long) i2);
        c5636e.read(c5637c, (long) i2);
        if (c5637c.m20623a() != ((long) i2)) {
            throw new IOException(c5637c.m20623a() + " != " + i2);
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        this.f17766t.execute(new C5503h(this, "OkHttp %s Push Data[%s]", new Object[]{this.f17761o, Integer.valueOf(i)}) {
            /* renamed from: f */
            final /* synthetic */ C5517c f17728f;

            /* renamed from: b */
            public void mo6710b() {
                try {
                    boolean a = this.f17728f.f17768v.mo6745a(i3, c5637c, i4, z2);
                    if (a) {
                        this.f17728f.f17757i.mo6731a(i3, ErrorCode.CANCEL);
                    }
                    if (a || z2) {
                        synchronized (this.f17728f) {
                            this.f17728f.f17771y.remove(Integer.valueOf(i3));
                        }
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    /* renamed from: c */
    private void m19937c(int i, ErrorCode errorCode) {
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        this.f17766t.execute(new C5503h(this, "OkHttp %s Push Reset[%s]", new Object[]{this.f17761o, Integer.valueOf(i)}) {
            /* renamed from: d */
            final /* synthetic */ C5517c f17731d;

            /* renamed from: b */
            public void mo6710b() {
                this.f17731d.f17768v.mo6742a(i2, errorCode2);
                synchronized (this.f17731d) {
                    this.f17731d.f17771y.remove(Integer.valueOf(i2));
                }
            }
        });
    }
}
