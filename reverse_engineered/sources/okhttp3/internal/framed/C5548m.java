package okhttp3.internal.framed;

import android.support.v4.view.ViewCompat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.List;
import java.util.zip.Deflater;
import okhttp3.internal.C5586l;
import okhttp3.internal.framed.C5501a.C5500a;
import okio.ByteString;
import okio.C5635d;
import okio.C5636e;
import okio.C5637c;
import okio.C5638f;
import okio.C5647m;

/* compiled from: Spdy3 */
/* renamed from: okhttp3.internal.framed.m */
public final class C5548m implements C5534n {
    /* renamed from: a */
    static final byte[] f17875a;

    /* compiled from: Spdy3 */
    /* renamed from: okhttp3.internal.framed.m$a */
    static final class C5546a implements C5501a {
        /* renamed from: a */
        private final C5636e f17867a;
        /* renamed from: b */
        private final boolean f17868b;
        /* renamed from: c */
        private final C5541i f17869c = new C5541i(this.f17867a);

        C5546a(C5636e c5636e, boolean z) {
            this.f17867a = c5636e;
            this.f17868b = z;
        }

        /* renamed from: a */
        public void mo6726a() {
        }

        /* renamed from: a */
        public boolean mo6727a(C5500a c5500a) throws IOException {
            boolean z = false;
            try {
                int k = this.f17867a.mo6827k();
                int k2 = this.f17867a.mo6827k();
                int i = (ViewCompat.MEASURED_STATE_MASK & k2) >>> 24;
                k2 &= ViewCompat.MEASURED_SIZE_MASK;
                int i2;
                if ((Integer.MIN_VALUE & k) != 0) {
                    int i3 = (2147418112 & k) >>> 16;
                    i2 = 65535 & k;
                    if (i3 != 3) {
                        throw new ProtocolException("version != 3: " + i3);
                    }
                    switch (i2) {
                        case 1:
                            m20129a(c5500a, i, k2);
                            return true;
                        case 2:
                            m20130b(c5500a, i, k2);
                            return true;
                        case 3:
                            m20131c(c5500a, i, k2);
                            return true;
                        case 4:
                            m20136h(c5500a, i, k2);
                            return true;
                        case 6:
                            m20134f(c5500a, i, k2);
                            return true;
                        case 7:
                            m20135g(c5500a, i, k2);
                            return true;
                        case 8:
                            m20132d(c5500a, i, k2);
                            return true;
                        case 9:
                            m20133e(c5500a, i, k2);
                            return true;
                        default:
                            this.f17867a.mo6822h((long) k2);
                            return true;
                    }
                }
                i2 = Integer.MAX_VALUE & k;
                if ((i & 1) != 0) {
                    z = true;
                }
                c5500a.mo6718a(z, i2, this.f17867a, k2);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        /* renamed from: a */
        private void m20129a(C5500a c5500a, int i, int i2) throws IOException {
            boolean z;
            boolean z2 = true;
            int k = this.f17867a.mo6827k();
            int i3 = k & Integer.MAX_VALUE;
            int k2 = this.f17867a.mo6827k() & Integer.MAX_VALUE;
            this.f17867a.mo6826j();
            List a = this.f17869c.m20102a(i2 - 10);
            if ((i & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((i & 2) == 0) {
                z2 = false;
            }
            c5500a.mo6720a(z2, z, i3, k2, a, HeadersMode.SPDY_SYN_STREAM);
        }

        /* renamed from: b */
        private void m20130b(C5500a c5500a, int i, int i2) throws IOException {
            boolean z;
            int k = this.f17867a.mo6827k() & Integer.MAX_VALUE;
            List a = this.f17869c.m20102a(i2 - 4);
            if ((i & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            c5500a.mo6720a(false, z, k, -1, a, HeadersMode.SPDY_REPLY);
        }

        /* renamed from: c */
        private void m20131c(C5500a c5500a, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw C5546a.m20128a("TYPE_RST_STREAM length: %d != 8", Integer.valueOf(i2));
            }
            int k = this.f17867a.mo6827k() & Integer.MAX_VALUE;
            ErrorCode fromSpdy3Rst = ErrorCode.fromSpdy3Rst(this.f17867a.mo6827k());
            if (fromSpdy3Rst == null) {
                throw C5546a.m20128a("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r1));
            } else {
                c5500a.mo6715a(k, fromSpdy3Rst);
            }
        }

        /* renamed from: d */
        private void m20132d(C5500a c5500a, int i, int i2) throws IOException {
            c5500a.mo6720a(false, false, this.f17867a.mo6827k() & Integer.MAX_VALUE, -1, this.f17869c.m20102a(i2 - 4), HeadersMode.SPDY_HEADERS);
        }

        /* renamed from: e */
        private void m20133e(C5500a c5500a, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw C5546a.m20128a("TYPE_WINDOW_UPDATE length: %d != 8", Integer.valueOf(i2));
            }
            int k = this.f17867a.mo6827k() & Integer.MAX_VALUE;
            long k2 = (long) (this.f17867a.mo6827k() & Integer.MAX_VALUE);
            if (k2 == 0) {
                throw C5546a.m20128a("windowSizeIncrement was 0", Long.valueOf(k2));
            } else {
                c5500a.mo6714a(k, k2);
            }
        }

        /* renamed from: f */
        private void m20134f(C5500a c5500a, int i, int i2) throws IOException {
            boolean z = true;
            if (i2 != 4) {
                throw C5546a.m20128a("TYPE_PING length: %d != 4", Integer.valueOf(i2));
            }
            boolean z2;
            int k = this.f17867a.mo6827k();
            boolean z3 = this.f17868b;
            if ((k & 1) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z3 != z2) {
                z = false;
            }
            c5500a.mo6717a(z, k, 0);
        }

        /* renamed from: g */
        private void m20135g(C5500a c5500a, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw C5546a.m20128a("TYPE_GOAWAY length: %d != 8", Integer.valueOf(i2));
            }
            int k = this.f17867a.mo6827k() & Integer.MAX_VALUE;
            ErrorCode fromSpdyGoAway = ErrorCode.fromSpdyGoAway(this.f17867a.mo6827k());
            if (fromSpdyGoAway == null) {
                throw C5546a.m20128a("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r1));
            } else {
                c5500a.mo6716a(k, fromSpdyGoAway, ByteString.EMPTY);
            }
        }

        /* renamed from: h */
        private void m20136h(C5500a c5500a, int i, int i2) throws IOException {
            boolean z = true;
            int k = this.f17867a.mo6827k();
            if (i2 != (k * 8) + 4) {
                throw C5546a.m20128a("TYPE_SETTINGS length: %d != 4 + 8 * %d", Integer.valueOf(i2), Integer.valueOf(k));
            }
            C5545l c5545l = new C5545l();
            for (int i3 = 0; i3 < k; i3++) {
                int k2 = this.f17867a.mo6827k();
                int i4 = (ViewCompat.MEASURED_STATE_MASK & k2) >>> 24;
                c5545l.m20115a(k2 & ViewCompat.MEASURED_SIZE_MASK, i4, this.f17867a.mo6827k());
            }
            if ((i & 1) == 0) {
                z = false;
            }
            c5500a.mo6719a(z, c5545l);
        }

        /* renamed from: a */
        private static IOException m20128a(String str, Object... objArr) throws IOException {
            throw new IOException(C5586l.m20319a(str, objArr));
        }

        public void close() throws IOException {
            this.f17869c.m20103a();
        }
    }

    /* compiled from: Spdy3 */
    /* renamed from: okhttp3.internal.framed.m$b */
    static final class C5547b implements C5502b {
        /* renamed from: a */
        private final C5635d f17870a;
        /* renamed from: b */
        private final C5637c f17871b = new C5637c();
        /* renamed from: c */
        private final C5635d f17872c;
        /* renamed from: d */
        private final boolean f17873d;
        /* renamed from: e */
        private boolean f17874e;

        C5547b(C5635d c5635d, boolean z) {
            this.f17870a = c5635d;
            this.f17873d = z;
            Deflater deflater = new Deflater();
            deflater.setDictionary(C5548m.f17875a);
            this.f17872c = C5647m.m20713a(new C5638f(this.f17871b, deflater));
        }

        /* renamed from: a */
        public void mo6733a(C5545l c5545l) {
        }

        /* renamed from: a */
        public void mo6729a(int i, int i2, List<C5526e> list) throws IOException {
        }

        /* renamed from: a */
        public synchronized void mo6728a() {
        }

        /* renamed from: b */
        public synchronized void mo6737b() throws IOException {
            if (this.f17874e) {
                throw new IOException("closed");
            }
            this.f17870a.flush();
        }

        /* renamed from: a */
        public synchronized void mo6736a(boolean z, boolean z2, int i, int i2, List<C5526e> list) throws IOException {
            int i3 = 0;
            synchronized (this) {
                if (this.f17874e) {
                    throw new IOException("closed");
                }
                m20139a((List) list);
                int a = (int) (10 + this.f17871b.m20623a());
                int i4 = z ? 1 : 0;
                if (z2) {
                    i3 = 2;
                }
                i3 |= i4;
                this.f17870a.mo6824i(-2147287039);
                this.f17870a.mo6824i(((i3 & 255) << 24) | (a & ViewCompat.MEASURED_SIZE_MASK));
                this.f17870a.mo6824i(i & Integer.MAX_VALUE);
                this.f17870a.mo6824i(i2 & Integer.MAX_VALUE);
                this.f17870a.mo6825j(0);
                this.f17870a.mo6808a(this.f17871b);
                this.f17870a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6731a(int i, ErrorCode errorCode) throws IOException {
            if (this.f17874e) {
                throw new IOException("closed");
            } else if (errorCode.spdyRstCode == -1) {
                throw new IllegalArgumentException();
            } else {
                this.f17870a.mo6824i(-2147287037);
                this.f17870a.mo6824i(8);
                this.f17870a.mo6824i(Integer.MAX_VALUE & i);
                this.f17870a.mo6824i(errorCode.spdyRstCode);
                this.f17870a.flush();
            }
        }

        /* renamed from: c */
        public int mo6739c() {
            return 16383;
        }

        /* renamed from: a */
        public synchronized void mo6735a(boolean z, int i, C5637c c5637c, int i2) throws IOException {
            m20142a(i, z ? 1 : 0, c5637c, i2);
        }

        /* renamed from: a */
        void m20142a(int i, int i2, C5637c c5637c, int i3) throws IOException {
            if (this.f17874e) {
                throw new IOException("closed");
            } else if (((long) i3) > 16777215) {
                throw new IllegalArgumentException("FRAME_TOO_LARGE max size is 16Mib: " + i3);
            } else {
                this.f17870a.mo6824i(Integer.MAX_VALUE & i);
                this.f17870a.mo6824i(((i2 & 255) << 24) | (ViewCompat.MEASURED_SIZE_MASK & i3));
                if (i3 > 0) {
                    this.f17870a.mo6706a(c5637c, (long) i3);
                }
            }
        }

        /* renamed from: a */
        private void m20139a(List<C5526e> list) throws IOException {
            this.f17872c.mo6824i(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ByteString byteString = ((C5526e) list.get(i)).f17812h;
                this.f17872c.mo6824i(byteString.size());
                this.f17872c.mo6812b(byteString);
                byteString = ((C5526e) list.get(i)).f17813i;
                this.f17872c.mo6824i(byteString.size());
                this.f17872c.mo6812b(byteString);
            }
            this.f17872c.flush();
        }

        /* renamed from: b */
        public synchronized void mo6738b(C5545l c5545l) throws IOException {
            if (this.f17874e) {
                throw new IOException("closed");
            }
            int b = c5545l.m20119b();
            int i = (b * 8) + 4;
            this.f17870a.mo6824i(-2147287036);
            this.f17870a.mo6824i((i & ViewCompat.MEASURED_SIZE_MASK) | 0);
            this.f17870a.mo6824i(b);
            for (b = 0; b <= 10; b++) {
                if (c5545l.m20118a(b)) {
                    this.f17870a.mo6824i(((c5545l.m20122c(b) & 255) << 24) | (b & ViewCompat.MEASURED_SIZE_MASK));
                    this.f17870a.mo6824i(c5545l.m20120b(b));
                }
            }
            this.f17870a.flush();
        }

        /* renamed from: a */
        public synchronized void mo6734a(boolean z, int i, int i2) throws IOException {
            boolean z2 = true;
            synchronized (this) {
                if (this.f17874e) {
                    throw new IOException("closed");
                }
                boolean z3;
                boolean z4 = this.f17873d;
                if ((i & 1) == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z4 == z3) {
                    z2 = false;
                }
                if (z != z2) {
                    throw new IllegalArgumentException("payload != reply");
                }
                this.f17870a.mo6824i(-2147287034);
                this.f17870a.mo6824i(4);
                this.f17870a.mo6824i(i);
                this.f17870a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6732a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
            if (this.f17874e) {
                throw new IOException("closed");
            } else if (errorCode.spdyGoAwayCode == -1) {
                throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
            } else {
                this.f17870a.mo6824i(-2147287033);
                this.f17870a.mo6824i(8);
                this.f17870a.mo6824i(i);
                this.f17870a.mo6824i(errorCode.spdyGoAwayCode);
                this.f17870a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6730a(int i, long j) throws IOException {
            if (this.f17874e) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                throw new IllegalArgumentException("windowSizeIncrement must be between 1 and 0x7fffffff: " + j);
            } else {
                this.f17870a.mo6824i(-2147287031);
                this.f17870a.mo6824i(8);
                this.f17870a.mo6824i(i);
                this.f17870a.mo6824i((int) j);
                this.f17870a.flush();
            }
        }

        public synchronized void close() throws IOException {
            this.f17874e = true;
            C5586l.m20328a(this.f17870a, this.f17872c);
        }
    }

    static {
        try {
            f17875a = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(C5586l.f18008c.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public C5501a mo6740a(C5636e c5636e, boolean z) {
        return new C5546a(c5636e, z);
    }

    /* renamed from: a */
    public C5502b mo6741a(C5635d c5635d, boolean z) {
        return new C5547b(c5635d, z);
    }
}
