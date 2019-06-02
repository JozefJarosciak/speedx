package okhttp3;

import ch.qos.logback.core.CoreConstants;
import com.facebook.stetho.dumpapp.Framer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.internal.C5586l;
import okio.ByteString;
import okio.C5635d;
import okio.C5637c;

/* compiled from: MultipartBody */
/* renamed from: okhttp3.t */
public final class C5611t extends C5602x {
    /* renamed from: a */
    public static final C5608s f18070a = C5608s.m20418a("multipart/mixed");
    /* renamed from: b */
    public static final C5608s f18071b = C5608s.m20418a("multipart/alternative");
    /* renamed from: c */
    public static final C5608s f18072c = C5608s.m20418a("multipart/digest");
    /* renamed from: d */
    public static final C5608s f18073d = C5608s.m20418a("multipart/parallel");
    /* renamed from: e */
    public static final C5608s f18074e = C5608s.m20418a("multipart/form-data");
    /* renamed from: f */
    private static final byte[] f18075f = new byte[]{(byte) 58, (byte) 32};
    /* renamed from: g */
    private static final byte[] f18076g = new byte[]{(byte) 13, (byte) 10};
    /* renamed from: h */
    private static final byte[] f18077h = new byte[]{Framer.STDIN_FRAME_PREFIX, Framer.STDIN_FRAME_PREFIX};
    /* renamed from: i */
    private final ByteString f18078i;
    /* renamed from: j */
    private final C5608s f18079j;
    /* renamed from: k */
    private final C5608s f18080k;
    /* renamed from: l */
    private final List<C5610b> f18081l;
    /* renamed from: m */
    private long f18082m = -1;

    /* compiled from: MultipartBody */
    /* renamed from: okhttp3.t$a */
    public static final class C5609a {
        /* renamed from: a */
        private final ByteString f18065a;
        /* renamed from: b */
        private C5608s f18066b;
        /* renamed from: c */
        private final List<C5610b> f18067c;

        public C5609a() {
            this(UUID.randomUUID().toString());
        }

        public C5609a(String str) {
            this.f18066b = C5611t.f18070a;
            this.f18067c = new ArrayList();
            this.f18065a = ByteString.encodeUtf8(str);
        }

        /* renamed from: a */
        public C5609a m20426a(C5608s c5608s) {
            if (c5608s == null) {
                throw new NullPointerException("type == null");
            } else if (c5608s.m20419a().equals("multipart")) {
                this.f18066b = c5608s;
                return this;
            } else {
                throw new IllegalArgumentException("multipart != " + c5608s);
            }
        }

        /* renamed from: a */
        public C5609a m20425a(C5607q c5607q, C5602x c5602x) {
            return m20427a(C5610b.m20432a(c5607q, c5602x));
        }

        /* renamed from: a */
        public C5609a m20423a(String str, String str2) {
            return m20427a(C5610b.m20430a(str, str2));
        }

        /* renamed from: a */
        public C5609a m20424a(String str, String str2, C5602x c5602x) {
            return m20427a(C5610b.m20431a(str, str2, c5602x));
        }

        /* renamed from: a */
        public C5609a m20427a(C5610b c5610b) {
            if (c5610b == null) {
                throw new NullPointerException("part == null");
            }
            this.f18067c.add(c5610b);
            return this;
        }

        /* renamed from: a */
        public C5611t m20428a() {
            if (!this.f18067c.isEmpty()) {
                return new C5611t(this.f18065a, this.f18066b, this.f18067c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    /* compiled from: MultipartBody */
    /* renamed from: okhttp3.t$b */
    public static final class C5610b {
        /* renamed from: a */
        private final C5607q f18068a;
        /* renamed from: b */
        private final C5602x f18069b;

        /* renamed from: a */
        public static C5610b m20432a(C5607q c5607q, C5602x c5602x) {
            if (c5602x == null) {
                throw new NullPointerException("body == null");
            } else if (c5607q != null && c5607q.m20413a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            } else if (c5607q == null || c5607q.m20413a("Content-Length") == null) {
                return new C5610b(c5607q, c5602x);
            } else {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
        }

        /* renamed from: a */
        public static C5610b m20430a(String str, String str2) {
            return C5610b.m20431a(str, null, C5602x.create(null, str2));
        }

        /* renamed from: a */
        public static C5610b m20431a(String str, String str2, C5602x c5602x) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder stringBuilder = new StringBuilder("form-data; name=");
            C5611t.m20435a(stringBuilder, str);
            if (str2 != null) {
                stringBuilder.append("; filename=");
                C5611t.m20435a(stringBuilder, str2);
            }
            return C5610b.m20432a(C5607q.m20410a("Content-Disposition", stringBuilder.toString()), c5602x);
        }

        private C5610b(C5607q c5607q, C5602x c5602x) {
            this.f18068a = c5607q;
            this.f18069b = c5602x;
        }
    }

    C5611t(ByteString byteString, C5608s c5608s, List<C5610b> list) {
        this.f18078i = byteString;
        this.f18079j = c5608s;
        this.f18080k = C5608s.m20418a(c5608s + "; boundary=" + byteString.utf8());
        this.f18081l = C5586l.m20321a((List) list);
    }

    public C5608s contentType() {
        return this.f18080k;
    }

    public long contentLength() throws IOException {
        long j = this.f18082m;
        if (j != -1) {
            return j;
        }
        j = m20434a(null, true);
        this.f18082m = j;
        return j;
    }

    public void writeTo(C5635d c5635d) throws IOException {
        m20434a(c5635d, false);
    }

    /* renamed from: a */
    private long m20434a(C5635d c5635d, boolean z) throws IOException {
        C5637c c5637c;
        long j = 0;
        if (z) {
            C5637c c5637c2 = new C5637c();
            c5637c = c5637c2;
            c5635d = c5637c2;
        } else {
            c5637c = null;
        }
        int size = this.f18081l.size();
        for (int i = 0; i < size; i++) {
            C5610b c5610b = (C5610b) this.f18081l.get(i);
            C5607q a = c5610b.f18068a;
            C5602x b = c5610b.f18069b;
            c5635d.mo6814c(f18077h);
            c5635d.mo6812b(this.f18078i);
            c5635d.mo6814c(f18076g);
            if (a != null) {
                int a2 = a.m20411a();
                for (int i2 = 0; i2 < a2; i2++) {
                    c5635d.mo6811b(a.m20412a(i2)).mo6814c(f18075f).mo6811b(a.m20414b(i2)).mo6814c(f18076g);
                }
            }
            C5608s contentType = b.contentType();
            if (contentType != null) {
                c5635d.mo6811b("Content-Type: ").mo6811b(contentType.toString()).mo6814c(f18076g);
            }
            long contentLength = b.contentLength();
            if (contentLength != -1) {
                c5635d.mo6811b("Content-Length: ").mo6830l(contentLength).mo6814c(f18076g);
            } else if (z) {
                c5637c.m20685t();
                return -1;
            }
            c5635d.mo6814c(f18076g);
            if (z) {
                j += contentLength;
            } else {
                b.writeTo(c5635d);
            }
            c5635d.mo6814c(f18076g);
        }
        c5635d.mo6814c(f18077h);
        c5635d.mo6812b(this.f18078i);
        c5635d.mo6814c(f18077h);
        c5635d.mo6814c(f18076g);
        if (!z) {
            return j;
        }
        j += c5637c.m20623a();
        c5637c.m20685t();
        return j;
    }

    /* renamed from: a */
    static StringBuilder m20435a(StringBuilder stringBuilder, String str) {
        stringBuilder.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\n':
                    stringBuilder.append("%0A");
                    break;
                case '\r':
                    stringBuilder.append("%0D");
                    break;
                case '\"':
                    stringBuilder.append("%22");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        stringBuilder.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        return stringBuilder;
    }
}
