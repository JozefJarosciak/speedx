package okhttp3.internal.framed;

import android.support.v4.view.ViewCompat;
import io.rong.push.PushConst;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.C5586l;
import okhttp3.internal.framed.C5501a.C5500a;
import okhttp3.internal.framed.C5529f.C5527a;
import okhttp3.internal.framed.C5529f.C5528b;
import okio.ByteString;
import okio.C5520s;
import okio.C5522t;
import okio.C5635d;
import okio.C5636e;
import okio.C5637c;

/* compiled from: Http2 */
/* renamed from: okhttp3.internal.framed.g */
public final class C5535g implements C5534n {
    /* renamed from: a */
    private static final Logger f17845a = Logger.getLogger(C5531b.class.getName());
    /* renamed from: b */
    private static final ByteString f17846b = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* compiled from: Http2 */
    /* renamed from: okhttp3.internal.framed.g$a */
    static final class C5530a implements C5520s {
        /* renamed from: a */
        int f17826a;
        /* renamed from: b */
        byte f17827b;
        /* renamed from: c */
        int f17828c;
        /* renamed from: d */
        int f17829d;
        /* renamed from: e */
        short f17830e;
        /* renamed from: f */
        private final C5636e f17831f;

        public C5530a(C5636e c5636e) {
            this.f17831f = c5636e;
        }

        public long read(C5637c c5637c, long j) throws IOException {
            while (this.f17829d == 0) {
                this.f17831f.mo6822h((long) this.f17830e);
                this.f17830e = (short) 0;
                if ((this.f17827b & 4) != 0) {
                    return -1;
                }
                m20043a();
            }
            long read = this.f17831f.read(c5637c, Math.min(j, (long) this.f17829d));
            if (read == -1) {
                return -1;
            }
            this.f17829d = (int) (((long) this.f17829d) - read);
            return read;
        }

        public C5522t timeout() {
            return this.f17831f.timeout();
        }

        public void close() throws IOException {
        }

        /* renamed from: a */
        private void m20043a() throws IOException {
            int i = this.f17828c;
            int a = C5535g.m20083b(this.f17831f);
            this.f17829d = a;
            this.f17826a = a;
            byte i2 = (byte) (this.f17831f.mo6823i() & 255);
            this.f17827b = (byte) (this.f17831f.mo6823i() & 255);
            if (C5535g.f17845a.isLoggable(Level.FINE)) {
                C5535g.f17845a.fine(C5531b.m20045a(true, this.f17828c, this.f17826a, i2, this.f17827b));
            }
            this.f17828c = this.f17831f.mo6827k() & Integer.MAX_VALUE;
            if (i2 != (byte) 9) {
                throw C5535g.m20088d("%s != TYPE_CONTINUATION", Byte.valueOf(i2));
            } else if (this.f17828c != i) {
                throw C5535g.m20088d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    /* compiled from: Http2 */
    /* renamed from: okhttp3.internal.framed.g$b */
    static final class C5531b {
        /* renamed from: a */
        private static final String[] f17832a = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", PushConst.PING_STRING_EXTRA, "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        /* renamed from: b */
        private static final String[] f17833b = new String[64];
        /* renamed from: c */
        private static final String[] f17834c = new String[256];

        C5531b() {
        }

        /* renamed from: a */
        static String m20045a(boolean z, int i, int i2, byte b, byte b2) {
            String a = b < f17832a.length ? f17832a[b] : C5586l.m20319a("0x%02x", Byte.valueOf(b));
            String a2 = C5531b.m20044a(b, b2);
            String str = "%s 0x%08x %5d %-13s %s";
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = a;
            objArr[4] = a2;
            return C5586l.m20319a(str, objArr);
        }

        /* renamed from: a */
        static String m20044a(byte b, byte b2) {
            if (b2 == (byte) 0) {
                return "";
            }
            switch (b) {
                case (byte) 2:
                case (byte) 3:
                case (byte) 7:
                case (byte) 8:
                    return f17834c[b2];
                case (byte) 4:
                case (byte) 6:
                    return b2 == (byte) 1 ? "ACK" : f17834c[b2];
                default:
                    String str = b2 < f17833b.length ? f17833b[b2] : f17834c[b2];
                    if (b == (byte) 5 && (b2 & 4) != 0) {
                        return str.replace("HEADERS", "PUSH_PROMISE");
                    }
                    if (b != (byte) 0 || (b2 & 32) == 0) {
                        return str;
                    }
                    return str.replace("PRIORITY", "COMPRESSED");
            }
        }

        static {
            int i = 0;
            for (int i2 = 0; i2 < f17834c.length; i2++) {
                f17834c[i2] = C5586l.m20319a("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
            }
            f17833b[0] = "";
            f17833b[1] = "END_STREAM";
            int[] iArr = new int[]{1};
            f17833b[8] = "PADDED";
            for (int i3 : iArr) {
                f17833b[i3 | 8] = f17833b[i3] + "|PADDED";
            }
            f17833b[4] = "END_HEADERS";
            f17833b[32] = "PRIORITY";
            f17833b[36] = "END_HEADERS|PRIORITY";
            for (int i4 : new int[]{4, 32, 36}) {
                for (int i5 : iArr) {
                    f17833b[i5 | i4] = f17833b[i5] + '|' + f17833b[i4];
                    f17833b[(i5 | i4) | 8] = f17833b[i5] + '|' + f17833b[i4] + "|PADDED";
                }
            }
            while (i < f17833b.length) {
                if (f17833b[i] == null) {
                    f17833b[i] = f17834c[i];
                }
                i++;
            }
        }
    }

    /* compiled from: Http2 */
    /* renamed from: okhttp3.internal.framed.g$c */
    static final class C5532c implements C5501a {
        /* renamed from: a */
        final C5527a f17835a;
        /* renamed from: b */
        private final C5636e f17836b;
        /* renamed from: c */
        private final C5530a f17837c = new C5530a(this.f17836b);
        /* renamed from: d */
        private final boolean f17838d;

        C5532c(C5636e c5636e, int i, boolean z) {
            this.f17836b = c5636e;
            this.f17838d = z;
            this.f17835a = new C5527a(i, this.f17837c);
        }

        /* renamed from: a */
        public void mo6726a() throws IOException {
            if (!this.f17838d) {
                ByteString d = this.f17836b.mo6816d((long) C5535g.f17846b.size());
                if (C5535g.f17845a.isLoggable(Level.FINE)) {
                    C5535g.f17845a.fine(C5586l.m20319a("<< CONNECTION %s", d.hex()));
                }
                if (!C5535g.f17846b.equals(d)) {
                    throw C5535g.m20088d("Expected a connection header but was %s", d.utf8());
                }
            }
        }

        /* renamed from: a */
        public boolean mo6727a(C5500a c5500a) throws IOException {
            try {
                this.f17836b.mo6809a(9);
                int a = C5535g.m20083b(this.f17836b);
                if (a < 0 || a > 16384) {
                    throw C5535g.m20088d("FRAME_SIZE_ERROR: %s", Integer.valueOf(a));
                }
                byte i = (byte) (this.f17836b.mo6823i() & 255);
                byte i2 = (byte) (this.f17836b.mo6823i() & 255);
                int k = this.f17836b.mo6827k() & Integer.MAX_VALUE;
                if (C5535g.f17845a.isLoggable(Level.FINE)) {
                    C5535g.f17845a.fine(C5531b.m20045a(true, k, a, i, i2));
                }
                switch (i) {
                    case (byte) 0:
                        m20049b(c5500a, a, i2, k);
                        return true;
                    case (byte) 1:
                        m20048a(c5500a, a, i2, k);
                        return true;
                    case (byte) 2:
                        m20050c(c5500a, a, i2, k);
                        return true;
                    case (byte) 3:
                        m20051d(c5500a, a, i2, k);
                        return true;
                    case (byte) 4:
                        m20052e(c5500a, a, i2, k);
                        return true;
                    case (byte) 5:
                        m20053f(c5500a, a, i2, k);
                        return true;
                    case (byte) 6:
                        m20054g(c5500a, a, i2, k);
                        return true;
                    case (byte) 7:
                        m20055h(c5500a, a, i2, k);
                        return true;
                    case (byte) 8:
                        m20056i(c5500a, a, i2, k);
                        return true;
                    default:
                        this.f17836b.mo6822h((long) a);
                        return true;
                }
            } catch (IOException e) {
                return false;
            }
        }

        /* renamed from: a */
        private void m20048a(C5500a c5500a, int i, byte b, int i2) throws IOException {
            if (i2 == 0) {
                throw C5535g.m20088d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            }
            short i3;
            boolean z = (b & 1) != 0;
            if ((b & 8) != 0) {
                i3 = (short) (this.f17836b.mo6823i() & 255);
            } else {
                i3 = (short) 0;
            }
            if ((b & 32) != 0) {
                m20047a(c5500a, i2);
                i -= 5;
            }
            c5500a.mo6720a(false, z, i2, -1, m20046a(C5535g.m20082b(i, b, i3), i3, b, i2), HeadersMode.HTTP_20_HEADERS);
        }

        /* renamed from: a */
        private List<C5526e> m20046a(int i, short s, byte b, int i2) throws IOException {
            C5530a c5530a = this.f17837c;
            this.f17837c.f17829d = i;
            c5530a.f17826a = i;
            this.f17837c.f17830e = s;
            this.f17837c.f17827b = b;
            this.f17837c.f17828c = i2;
            this.f17835a.m20031a();
            return this.f17835a.m20033b();
        }

        /* renamed from: b */
        private void m20049b(C5500a c5500a, int i, byte b, int i2) throws IOException {
            boolean z;
            short s = (short) 1;
            short s2 = (short) 0;
            if ((b & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((b & 32) == 0) {
                s = (short) 0;
            }
            if (s != (short) 0) {
                throw C5535g.m20088d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            }
            if ((b & 8) != 0) {
                s2 = (short) (this.f17836b.mo6823i() & 255);
            }
            c5500a.mo6718a(z, i2, this.f17836b, C5535g.m20082b(i, b, s2));
            this.f17836b.mo6822h((long) s2);
        }

        /* renamed from: c */
        private void m20050c(C5500a c5500a, int i, byte b, int i2) throws IOException {
            if (i != 5) {
                throw C5535g.m20088d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            } else if (i2 == 0) {
                throw C5535g.m20088d("TYPE_PRIORITY streamId == 0", new Object[0]);
            } else {
                m20047a(c5500a, i2);
            }
        }

        /* renamed from: a */
        private void m20047a(C5500a c5500a, int i) throws IOException {
            int k = this.f17836b.mo6827k();
            c5500a.mo6712a(i, k & Integer.MAX_VALUE, (this.f17836b.mo6823i() & 255) + 1, (Integer.MIN_VALUE & k) != 0);
        }

        /* renamed from: d */
        private void m20051d(C5500a c5500a, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                throw C5535g.m20088d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            } else if (i2 == 0) {
                throw C5535g.m20088d("TYPE_RST_STREAM streamId == 0", new Object[0]);
            } else {
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.f17836b.mo6827k());
                if (fromHttp2 == null) {
                    throw C5535g.m20088d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r0));
                } else {
                    c5500a.mo6715a(i2, fromHttp2);
                }
            }
        }

        /* renamed from: e */
        private void m20052e(C5500a c5500a, int i, byte b, int i2) throws IOException {
            if (i2 != 0) {
                throw C5535g.m20088d("TYPE_SETTINGS streamId != 0", new Object[0]);
            } else if ((b & 1) != 0) {
                if (i != 0) {
                    throw C5535g.m20088d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
                c5500a.mo6711a();
            } else if (i % 6 != 0) {
                throw C5535g.m20088d("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            } else {
                C5545l c5545l = new C5545l();
                for (int i3 = 0; i3 < i; i3 += 6) {
                    int j = this.f17836b.mo6826j();
                    int k = this.f17836b.mo6827k();
                    switch (j) {
                        case 2:
                            if (!(k == 0 || k == 1)) {
                                throw C5535g.m20088d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                            }
                        case 3:
                            j = 4;
                            break;
                        case 4:
                            j = 7;
                            if (k >= 0) {
                                break;
                            }
                            throw C5535g.m20088d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        case 5:
                            if (k >= 16384 && k <= ViewCompat.MEASURED_SIZE_MASK) {
                                break;
                            }
                            throw C5535g.m20088d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(k));
                        default:
                            break;
                    }
                    c5545l.m20115a(j, 0, k);
                }
                c5500a.mo6719a(false, c5545l);
                if (c5545l.m20121c() >= 0) {
                    this.f17835a.m20032a(c5545l.m20121c());
                }
            }
        }

        /* renamed from: f */
        private void m20053f(C5500a c5500a, int i, byte b, int i2) throws IOException {
            short s = (short) 0;
            if (i2 == 0) {
                throw C5535g.m20088d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            }
            if ((b & 8) != 0) {
                s = (short) (this.f17836b.mo6823i() & 255);
            }
            c5500a.mo6713a(i2, this.f17836b.mo6827k() & Integer.MAX_VALUE, m20046a(C5535g.m20082b(i - 4, b, s), s, b, i2));
        }

        /* renamed from: g */
        private void m20054g(C5500a c5500a, int i, byte b, int i2) throws IOException {
            boolean z = true;
            if (i != 8) {
                throw C5535g.m20088d("TYPE_PING length != 8: %s", Integer.valueOf(i));
            } else if (i2 != 0) {
                throw C5535g.m20088d("TYPE_PING streamId != 0", new Object[0]);
            } else {
                int k = this.f17836b.mo6827k();
                int k2 = this.f17836b.mo6827k();
                if ((b & 1) == 0) {
                    z = false;
                }
                c5500a.mo6717a(z, k, k2);
            }
        }

        /* renamed from: h */
        private void m20055h(C5500a c5500a, int i, byte b, int i2) throws IOException {
            if (i < 8) {
                throw C5535g.m20088d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            } else if (i2 != 0) {
                throw C5535g.m20088d("TYPE_GOAWAY streamId != 0", new Object[0]);
            } else {
                int k = this.f17836b.mo6827k();
                int i3 = i - 8;
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.f17836b.mo6827k());
                if (fromHttp2 == null) {
                    throw C5535g.m20088d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r0));
                }
                ByteString byteString = ByteString.EMPTY;
                if (i3 > 0) {
                    byteString = this.f17836b.mo6816d((long) i3);
                }
                c5500a.mo6716a(k, fromHttp2, byteString);
            }
        }

        /* renamed from: i */
        private void m20056i(C5500a c5500a, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                throw C5535g.m20088d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            }
            long k = ((long) this.f17836b.mo6827k()) & 2147483647L;
            if (k == 0) {
                throw C5535g.m20088d("windowSizeIncrement was 0", Long.valueOf(k));
            } else {
                c5500a.mo6714a(i2, k);
            }
        }

        public void close() throws IOException {
            this.f17836b.close();
        }
    }

    /* compiled from: Http2 */
    /* renamed from: okhttp3.internal.framed.g$d */
    static final class C5533d implements C5502b {
        /* renamed from: a */
        private final C5635d f17839a;
        /* renamed from: b */
        private final boolean f17840b;
        /* renamed from: c */
        private final C5637c f17841c = new C5637c();
        /* renamed from: d */
        private final C5528b f17842d = new C5528b(this.f17841c);
        /* renamed from: e */
        private int f17843e = 16384;
        /* renamed from: f */
        private boolean f17844f;

        C5533d(C5635d c5635d, boolean z) {
            this.f17839a = c5635d;
            this.f17840b = z;
        }

        /* renamed from: b */
        public synchronized void mo6737b() throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            }
            this.f17839a.flush();
        }

        /* renamed from: a */
        public synchronized void mo6733a(C5545l c5545l) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            }
            this.f17843e = c5545l.m20124e(this.f17843e);
            m20062a(0, 0, (byte) 4, (byte) 1);
            this.f17839a.flush();
        }

        /* renamed from: a */
        public synchronized void mo6728a() throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            } else if (this.f17840b) {
                if (C5535g.f17845a.isLoggable(Level.FINE)) {
                    C5535g.f17845a.fine(C5586l.m20319a(">> CONNECTION %s", C5535g.f17846b.hex()));
                }
                this.f17839a.mo6814c(C5535g.f17846b.toByteArray());
                this.f17839a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6736a(boolean z, boolean z2, int i, int i2, List<C5526e> list) throws IOException {
            if (z2) {
                throw new UnsupportedOperationException();
            } else if (this.f17844f) {
                throw new IOException("closed");
            } else {
                m20069a(z, i, (List) list);
            }
        }

        /* renamed from: a */
        public synchronized void mo6729a(int i, int i2, List<C5526e> list) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            }
            this.f17842d.m20036a((List) list);
            long a = this.f17841c.m20623a();
            int min = (int) Math.min((long) (this.f17843e - 4), a);
            m20062a(i, min + 4, (byte) 5, a == ((long) min) ? (byte) 4 : (byte) 0);
            this.f17839a.mo6824i(Integer.MAX_VALUE & i2);
            this.f17839a.mo6706a(this.f17841c, (long) min);
            if (a > ((long) min)) {
                m20059b(i, a - ((long) min));
            }
        }

        /* renamed from: a */
        void m20069a(boolean z, int i, List<C5526e> list) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            }
            this.f17842d.m20036a((List) list);
            long a = this.f17841c.m20623a();
            int min = (int) Math.min((long) this.f17843e, a);
            byte b = a == ((long) min) ? (byte) 4 : (byte) 0;
            if (z) {
                b = (byte) (b | 1);
            }
            m20062a(i, min, (byte) 1, b);
            this.f17839a.mo6706a(this.f17841c, (long) min);
            if (a > ((long) min)) {
                m20059b(i, a - ((long) min));
            }
        }

        /* renamed from: b */
        private void m20059b(int i, long j) throws IOException {
            while (j > 0) {
                int min = (int) Math.min((long) this.f17843e, j);
                j -= (long) min;
                m20062a(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
                this.f17839a.mo6706a(this.f17841c, (long) min);
            }
        }

        /* renamed from: a */
        public synchronized void mo6731a(int i, ErrorCode errorCode) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            } else if (errorCode.httpCode == -1) {
                throw new IllegalArgumentException();
            } else {
                m20062a(i, 4, (byte) 3, (byte) 0);
                this.f17839a.mo6824i(errorCode.httpCode);
                this.f17839a.flush();
            }
        }

        /* renamed from: c */
        public int mo6739c() {
            return this.f17843e;
        }

        /* renamed from: a */
        public synchronized void mo6735a(boolean z, int i, C5637c c5637c, int i2) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            }
            byte b = (byte) 0;
            if (z) {
                b = (byte) 1;
            }
            m20061a(i, b, c5637c, i2);
        }

        /* renamed from: a */
        void m20061a(int i, byte b, C5637c c5637c, int i2) throws IOException {
            m20062a(i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.f17839a.mo6706a(c5637c, (long) i2);
            }
        }

        /* renamed from: b */
        public synchronized void mo6738b(C5545l c5545l) throws IOException {
            synchronized (this) {
                if (this.f17844f) {
                    throw new IOException("closed");
                }
                m20062a(0, c5545l.m20119b() * 6, (byte) 4, (byte) 0);
                for (int i = 0; i < 10; i++) {
                    if (c5545l.m20118a(i)) {
                        int i2;
                        if (i == 4) {
                            i2 = 3;
                        } else if (i == 7) {
                            i2 = 4;
                        } else {
                            i2 = i;
                        }
                        this.f17839a.mo6825j(i2);
                        this.f17839a.mo6824i(c5545l.m20120b(i));
                    }
                }
                this.f17839a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6734a(boolean z, int i, int i2) throws IOException {
            byte b = (byte) 0;
            synchronized (this) {
                if (this.f17844f) {
                    throw new IOException("closed");
                }
                if (z) {
                    b = (byte) 1;
                }
                m20062a(0, 8, (byte) 6, b);
                this.f17839a.mo6824i(i);
                this.f17839a.mo6824i(i2);
                this.f17839a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6732a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            } else if (errorCode.httpCode == -1) {
                throw C5535g.m20087c("errorCode.httpCode == -1", new Object[0]);
            } else {
                m20062a(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.f17839a.mo6824i(i);
                this.f17839a.mo6824i(errorCode.httpCode);
                if (bArr.length > 0) {
                    this.f17839a.mo6814c(bArr);
                }
                this.f17839a.flush();
            }
        }

        /* renamed from: a */
        public synchronized void mo6730a(int i, long j) throws IOException {
            if (this.f17844f) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                throw C5535g.m20087c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            } else {
                m20062a(i, 4, (byte) 8, (byte) 0);
                this.f17839a.mo6824i((int) j);
                this.f17839a.flush();
            }
        }

        public synchronized void close() throws IOException {
            this.f17844f = true;
            this.f17839a.close();
        }

        /* renamed from: a */
        void m20062a(int i, int i2, byte b, byte b2) throws IOException {
            if (C5535g.f17845a.isLoggable(Level.FINE)) {
                C5535g.f17845a.fine(C5531b.m20045a(false, i, i2, b, b2));
            }
            if (i2 > this.f17843e) {
                throw C5535g.m20087c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f17843e), Integer.valueOf(i2));
            } else if ((Integer.MIN_VALUE & i) != 0) {
                throw C5535g.m20087c("reserved bit set: %s", Integer.valueOf(i));
            } else {
                C5535g.m20086b(this.f17839a, i2);
                this.f17839a.mo6828k(b & 255);
                this.f17839a.mo6828k(b2 & 255);
                this.f17839a.mo6824i(Integer.MAX_VALUE & i);
            }
        }
    }

    /* renamed from: a */
    public C5501a mo6740a(C5636e c5636e, boolean z) {
        return new C5532c(c5636e, 4096, z);
    }

    /* renamed from: a */
    public C5502b mo6741a(C5635d c5635d, boolean z) {
        return new C5533d(c5635d, z);
    }

    /* renamed from: c */
    private static IllegalArgumentException m20087c(String str, Object... objArr) {
        throw new IllegalArgumentException(C5586l.m20319a(str, objArr));
    }

    /* renamed from: d */
    private static IOException m20088d(String str, Object... objArr) throws IOException {
        throw new IOException(C5586l.m20319a(str, objArr));
    }

    /* renamed from: b */
    private static int m20082b(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            short s2 = i - 1;
        }
        if (s <= s2) {
            return (short) (s2 - s);
        }
        throw C5535g.m20088d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(s2));
    }

    /* renamed from: b */
    private static int m20083b(C5636e c5636e) throws IOException {
        return (((c5636e.mo6823i() & 255) << 16) | ((c5636e.mo6823i() & 255) << 8)) | (c5636e.mo6823i() & 255);
    }

    /* renamed from: b */
    private static void m20086b(C5635d c5635d, int i) throws IOException {
        c5635d.mo6828k((i >>> 16) & 255);
        c5635d.mo6828k((i >>> 8) & 255);
        c5635d.mo6828k(i & 255);
    }
}
