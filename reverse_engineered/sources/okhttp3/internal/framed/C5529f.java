package okhttp3.internal.framed;

import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.cons.C0846c;
import com.alipay.sdk.packet.C0861d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.ByteString;
import okio.C5520s;
import okio.C5636e;
import okio.C5637c;
import okio.C5647m;
import org.apache.http.HttpHost;

/* compiled from: Hpack */
/* renamed from: okhttp3.internal.framed.f */
final class C5529f {
    /* renamed from: a */
    private static final C5526e[] f17824a = new C5526e[]{new C5526e(C5526e.f17809e, ""), new C5526e(C5526e.f17806b, "GET"), new C5526e(C5526e.f17806b, "POST"), new C5526e(C5526e.f17807c, "/"), new C5526e(C5526e.f17807c, "/index.html"), new C5526e(C5526e.f17808d, HttpHost.DEFAULT_SCHEME_NAME), new C5526e(C5526e.f17808d, C0845b.f2060a), new C5526e(C5526e.f17805a, "200"), new C5526e(C5526e.f17805a, "204"), new C5526e(C5526e.f17805a, "206"), new C5526e(C5526e.f17805a, "304"), new C5526e(C5526e.f17805a, "400"), new C5526e(C5526e.f17805a, "404"), new C5526e(C5526e.f17805a, "500"), new C5526e("accept-charset", ""), new C5526e("accept-encoding", "gzip, deflate"), new C5526e("accept-language", ""), new C5526e("accept-ranges", ""), new C5526e("accept", ""), new C5526e("access-control-allow-origin", ""), new C5526e("age", ""), new C5526e("allow", ""), new C5526e("authorization", ""), new C5526e("cache-control", ""), new C5526e("content-disposition", ""), new C5526e("content-encoding", ""), new C5526e("content-language", ""), new C5526e("content-length", ""), new C5526e("content-location", ""), new C5526e("content-range", ""), new C5526e(C0861d.f2132d, ""), new C5526e("cookie", ""), new C5526e("date", ""), new C5526e("etag", ""), new C5526e("expect", ""), new C5526e("expires", ""), new C5526e("from", ""), new C5526e(C0846c.f2075f, ""), new C5526e("if-match", ""), new C5526e("if-modified-since", ""), new C5526e("if-none-match", ""), new C5526e("if-range", ""), new C5526e("if-unmodified-since", ""), new C5526e("last-modified", ""), new C5526e("link", ""), new C5526e(MapboxEvent.TYPE_LOCATION, ""), new C5526e("max-forwards", ""), new C5526e("proxy-authenticate", ""), new C5526e("proxy-authorization", ""), new C5526e("range", ""), new C5526e("referer", ""), new C5526e("refresh", ""), new C5526e("retry-after", ""), new C5526e("server", ""), new C5526e("set-cookie", ""), new C5526e("strict-transport-security", ""), new C5526e("transfer-encoding", ""), new C5526e("user-agent", ""), new C5526e("vary", ""), new C5526e("via", ""), new C5526e("www-authenticate", "")};
    /* renamed from: b */
    private static final Map<ByteString, Integer> f17825b = C5529f.m20042c();

    /* compiled from: Hpack */
    /* renamed from: okhttp3.internal.framed.f$a */
    static final class C5527a {
        /* renamed from: a */
        C5526e[] f17815a = new C5526e[8];
        /* renamed from: b */
        int f17816b = (this.f17815a.length - 1);
        /* renamed from: c */
        int f17817c = 0;
        /* renamed from: d */
        int f17818d = 0;
        /* renamed from: e */
        private final List<C5526e> f17819e = new ArrayList();
        /* renamed from: f */
        private final C5636e f17820f;
        /* renamed from: g */
        private int f17821g;
        /* renamed from: h */
        private int f17822h;

        C5527a(int i, C5520s c5520s) {
            this.f17821g = i;
            this.f17822h = i;
            this.f17820f = C5647m.m20714a(c5520s);
        }

        /* renamed from: a */
        void m20032a(int i) {
            this.f17821g = i;
            this.f17822h = i;
            m20021d();
        }

        /* renamed from: d */
        private void m20021d() {
            if (this.f17822h >= this.f17818d) {
                return;
            }
            if (this.f17822h == 0) {
                m20022e();
            } else {
                m20018b(this.f17818d - this.f17822h);
            }
        }

        /* renamed from: e */
        private void m20022e() {
            this.f17819e.clear();
            Arrays.fill(this.f17815a, null);
            this.f17816b = this.f17815a.length - 1;
            this.f17817c = 0;
            this.f17818d = 0;
        }

        /* renamed from: b */
        private int m20018b(int i) {
            int i2 = 0;
            if (i > 0) {
                for (int length = this.f17815a.length - 1; length >= this.f17816b && i > 0; length--) {
                    i -= this.f17815a[length].f17814j;
                    this.f17818d -= this.f17815a[length].f17814j;
                    this.f17817c--;
                    i2++;
                }
                System.arraycopy(this.f17815a, this.f17816b + 1, this.f17815a, (this.f17816b + 1) + i2, this.f17817c);
                this.f17816b += i2;
            }
            return i2;
        }

        /* renamed from: a */
        void m20031a() throws IOException {
            while (!this.f17820f.mo6818f()) {
                int i = this.f17820f.mo6823i() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                } else if ((i & 128) == 128) {
                    m20019c(m20030a(i, 127) - 1);
                } else if (i == 64) {
                    m20027g();
                } else if ((i & 64) == 64) {
                    m20025f(m20030a(i, 63) - 1);
                } else if ((i & 32) == 32) {
                    this.f17822h = m20030a(i, 31);
                    if (this.f17822h < 0 || this.f17822h > this.f17821g) {
                        throw new IOException("Invalid dynamic table size update " + this.f17822h);
                    }
                    m20021d();
                } else if (i == 16 || i == 0) {
                    m20024f();
                } else {
                    m20023e(m20030a(i, 15) - 1);
                }
            }
        }

        /* renamed from: b */
        public List<C5526e> m20033b() {
            List arrayList = new ArrayList(this.f17819e);
            this.f17819e.clear();
            return arrayList;
        }

        /* renamed from: c */
        private void m20019c(int i) throws IOException {
            if (m20029h(i)) {
                this.f17819e.add(C5529f.f17824a[i]);
                return;
            }
            int d = m20020d(i - C5529f.f17824a.length);
            if (d < 0 || d > this.f17815a.length - 1) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.f17819e.add(this.f17815a[d]);
        }

        /* renamed from: d */
        private int m20020d(int i) {
            return (this.f17816b + 1) + i;
        }

        /* renamed from: e */
        private void m20023e(int i) throws IOException {
            this.f17819e.add(new C5526e(m20026g(i), m20034c()));
        }

        /* renamed from: f */
        private void m20024f() throws IOException {
            this.f17819e.add(new C5526e(C5529f.m20041b(m20034c()), m20034c()));
        }

        /* renamed from: f */
        private void m20025f(int i) throws IOException {
            m20017a(-1, new C5526e(m20026g(i), m20034c()));
        }

        /* renamed from: g */
        private void m20027g() throws IOException {
            m20017a(-1, new C5526e(C5529f.m20041b(m20034c()), m20034c()));
        }

        /* renamed from: g */
        private ByteString m20026g(int i) {
            if (m20029h(i)) {
                return C5529f.f17824a[i].f17812h;
            }
            return this.f17815a[m20020d(i - C5529f.f17824a.length)].f17812h;
        }

        /* renamed from: h */
        private boolean m20029h(int i) {
            return i >= 0 && i <= C5529f.f17824a.length - 1;
        }

        /* renamed from: a */
        private void m20017a(int i, C5526e c5526e) {
            this.f17819e.add(c5526e);
            int i2 = c5526e.f17814j;
            if (i != -1) {
                i2 -= this.f17815a[m20020d(i)].f17814j;
            }
            if (i2 > this.f17822h) {
                m20022e();
                return;
            }
            int b = m20018b((this.f17818d + i2) - this.f17822h);
            if (i == -1) {
                if (this.f17817c + 1 > this.f17815a.length) {
                    Object obj = new C5526e[(this.f17815a.length * 2)];
                    System.arraycopy(this.f17815a, 0, obj, this.f17815a.length, this.f17815a.length);
                    this.f17816b = this.f17815a.length - 1;
                    this.f17815a = obj;
                }
                b = this.f17816b;
                this.f17816b = b - 1;
                this.f17815a[b] = c5526e;
                this.f17817c++;
            } else {
                this.f17815a[(b + m20020d(i)) + i] = c5526e;
            }
            this.f17818d = i2 + this.f17818d;
        }

        /* renamed from: h */
        private int m20028h() throws IOException {
            return this.f17820f.mo6823i() & 255;
        }

        /* renamed from: a */
        int m20030a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            i3 = 0;
            while (true) {
                int h = m20028h();
                if ((h & 128) == 0) {
                    return (h << i3) + i2;
                }
                i2 += (h & 127) << i3;
                i3 += 7;
            }
        }

        /* renamed from: c */
        ByteString m20034c() throws IOException {
            int h = m20028h();
            Object obj = (h & 128) == 128 ? 1 : null;
            h = m20030a(h, 127);
            if (obj != null) {
                return ByteString.of(C5537h.m20094a().m20097a(this.f17820f.mo6820g((long) h)));
            }
            return this.f17820f.mo6816d((long) h);
        }
    }

    /* compiled from: Hpack */
    /* renamed from: okhttp3.internal.framed.f$b */
    static final class C5528b {
        /* renamed from: a */
        private final C5637c f17823a;

        C5528b(C5637c c5637c) {
            this.f17823a = c5637c;
        }

        /* renamed from: a */
        void m20036a(List<C5526e> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ByteString toAsciiLowercase = ((C5526e) list.get(i)).f17812h.toAsciiLowercase();
                Integer num = (Integer) C5529f.f17825b.get(toAsciiLowercase);
                if (num != null) {
                    m20035a(num.intValue() + 1, 15, 0);
                    m20037a(((C5526e) list.get(i)).f17813i);
                } else {
                    this.f17823a.m20641b(0);
                    m20037a(toAsciiLowercase);
                    m20037a(((C5526e) list.get(i)).f17813i);
                }
            }
        }

        /* renamed from: a */
        void m20035a(int i, int i2, int i3) throws IOException {
            if (i < i2) {
                this.f17823a.m20641b(i3 | i);
                return;
            }
            this.f17823a.m20641b(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f17823a.m20641b((i4 & 127) | 128);
                i4 >>>= 7;
            }
            this.f17823a.m20641b(i4);
        }

        /* renamed from: a */
        void m20037a(ByteString byteString) throws IOException {
            m20035a(byteString.size(), 127, 0);
            this.f17823a.m20635a(byteString);
        }
    }

    /* renamed from: c */
    private static Map<ByteString, Integer> m20042c() {
        Map linkedHashMap = new LinkedHashMap(f17824a.length);
        for (int i = 0; i < f17824a.length; i++) {
            if (!linkedHashMap.containsKey(f17824a[i].f17812h)) {
                linkedHashMap.put(f17824a[i].f17812h, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* renamed from: b */
    private static ByteString m20041b(ByteString byteString) throws IOException {
        int i = 0;
        int size = byteString.size();
        while (i < size) {
            byte b = byteString.getByte(i);
            if (b < (byte) 65 || b > (byte) 90) {
                i++;
            } else {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }
}
