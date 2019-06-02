package okhttp3.logging;

import ch.qos.logback.core.CoreConstants;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import okhttp3.C5474h;
import okhttp3.C5569z;
import okhttp3.C5596r;
import okhttp3.C5596r.C5572a;
import okhttp3.C5602x;
import okhttp3.C5607q;
import okhttp3.C5608s;
import okhttp3.C5621w;
import okhttp3.C5627y;
import okhttp3.Protocol;
import okhttp3.internal.C5481j;
import okhttp3.internal.http.C5574g;
import okio.C5636e;
import okio.C5637c;
import org.apache.http.protocol.HTTP;

public final class HttpLoggingInterceptor implements C5596r {
    /* renamed from: a */
    private static final Charset f18038a = Charset.forName("UTF-8");
    /* renamed from: b */
    private final C5594a f18039b;
    /* renamed from: c */
    private volatile Level f18040c;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* renamed from: okhttp3.logging.HttpLoggingInterceptor$a */
    public interface C5594a {
        /* renamed from: a */
        public static final C5594a f18037a = new C55951();

        /* renamed from: okhttp3.logging.HttpLoggingInterceptor$a$1 */
        static class C55951 implements C5594a {
            C55951() {
            }

            /* renamed from: a */
            public void mo6763a(String str) {
                C5481j.m19770c().mo6700a(4, str, null);
            }
        }

        /* renamed from: a */
        void mo6763a(String str);
    }

    public HttpLoggingInterceptor() {
        this(C5594a.f18037a);
    }

    public HttpLoggingInterceptor(C5594a c5594a) {
        this.f18040c = Level.NONE;
        this.f18039b = c5594a;
    }

    /* renamed from: a */
    public HttpLoggingInterceptor m20384a(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.f18040c = level;
        return this;
    }

    public C5627y intercept(C5572a c5572a) throws IOException {
        Level level = this.f18040c;
        C5621w a = c5572a.mo6758a();
        if (level == Level.NONE) {
            return c5572a.mo6759a(a);
        }
        Object obj = level == Level.BODY ? 1 : null;
        Object obj2 = (obj != null || level == Level.HEADERS) ? 1 : null;
        C5602x d = a.m20522d();
        Object obj3 = d != null ? 1 : null;
        C5474h b = c5572a.mo6760b();
        String str = "--> " + a.m20520b() + ' ' + a.m20519a() + ' ' + (b != null ? b.mo6698c() : Protocol.HTTP_1_1);
        if (obj2 == null && obj3 != null) {
            str = str + " (" + d.contentLength() + "-byte body)";
        }
        this.f18039b.mo6763a(str);
        if (obj2 != null) {
            if (obj3 != null) {
                if (d.contentType() != null) {
                    this.f18039b.mo6763a("Content-Type: " + d.contentType());
                }
                if (d.contentLength() != -1) {
                    this.f18039b.mo6763a("Content-Length: " + d.contentLength());
                }
            }
            C5607q c = a.m20521c();
            int a2 = c.m20411a();
            for (int i = 0; i < a2; i++) {
                String a3 = c.m20412a(i);
                if (!("Content-Type".equalsIgnoreCase(a3) || "Content-Length".equalsIgnoreCase(a3))) {
                    this.f18039b.mo6763a(a3 + ": " + c.m20414b(i));
                }
            }
            if (obj == null || obj3 == null) {
                this.f18039b.mo6763a("--> END " + a.m20520b());
            } else if (m20382a(a.m20521c())) {
                this.f18039b.mo6763a("--> END " + a.m20520b() + " (encoded body omitted)");
            } else {
                C5637c c5637c = new C5637c();
                d.writeTo(c5637c);
                Charset charset = f18038a;
                C5608s contentType = d.contentType();
                if (contentType != null) {
                    charset = contentType.m20420a(f18038a);
                }
                this.f18039b.mo6763a("");
                if (m20383a(c5637c)) {
                    this.f18039b.mo6763a(c5637c.m20629a(charset));
                    this.f18039b.mo6763a("--> END " + a.m20520b() + " (" + d.contentLength() + "-byte body)");
                } else {
                    this.f18039b.mo6763a("--> END " + a.m20520b() + " (binary " + d.contentLength() + "-byte body omitted)");
                }
            }
        }
        long nanoTime = System.nanoTime();
        try {
            C5627y a4 = c5572a.mo6759a(a);
            long toMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            C5569z g = a4.m20575g();
            long contentLength = g.contentLength();
            this.f18039b.mo6763a("<-- " + a4.m20570b() + ' ' + a4.m20572d() + ' ' + a4.m20569a().m20519a() + " (" + toMillis + "ms" + (obj2 == null ? ", " + (contentLength != -1 ? contentLength + "-byte" : "unknown-length") + " body" : "") + CoreConstants.RIGHT_PARENTHESIS_CHAR);
            if (obj2 != null) {
                C5607q f = a4.m20574f();
                int a5 = f.m20411a();
                for (int i2 = 0; i2 < a5; i2++) {
                    this.f18039b.mo6763a(f.m20412a(i2) + ": " + f.m20414b(i2));
                }
                if (obj == null || !C5574g.m20239a(a4)) {
                    this.f18039b.mo6763a("<-- END HTTP");
                } else if (m20382a(a4.m20574f())) {
                    this.f18039b.mo6763a("<-- END HTTP (encoded body omitted)");
                } else {
                    C5636e source = g.source();
                    source.mo6813b(Long.MAX_VALUE);
                    C5637c b2 = source.mo6810b();
                    Charset charset2 = f18038a;
                    C5608s contentType2 = g.contentType();
                    if (contentType2 != null) {
                        try {
                            charset2 = contentType2.m20420a(f18038a);
                        } catch (UnsupportedCharsetException e) {
                            this.f18039b.mo6763a("");
                            this.f18039b.mo6763a("Couldn't decode the response body; charset is likely malformed.");
                            this.f18039b.mo6763a("<-- END HTTP");
                            return a4;
                        }
                    }
                    if (m20383a(b2)) {
                        if (contentLength != 0) {
                            this.f18039b.mo6763a("");
                            this.f18039b.mo6763a(b2.m20686u().m20629a(charset2));
                        }
                        this.f18039b.mo6763a("<-- END HTTP (" + b2.m20623a() + "-byte body)");
                    } else {
                        this.f18039b.mo6763a("");
                        this.f18039b.mo6763a("<-- END HTTP (binary " + b2.m20623a() + "-byte body omitted)");
                        return a4;
                    }
                }
            }
            return a4;
        } catch (Exception e2) {
            this.f18039b.mo6763a("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    /* renamed from: a */
    static boolean m20383a(C5637c c5637c) throws EOFException {
        long j = 64;
        try {
            C5637c c5637c2 = new C5637c();
            if (c5637c.m20623a() < 64) {
                j = c5637c.m20623a();
            }
            c5637c.m20636a(c5637c2, 0, j);
            for (int i = 0; i < 16 && !c5637c2.mo6818f(); i++) {
                int r = c5637c2.m20683r();
                if (Character.isISOControl(r) && !Character.isWhitespace(r)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }

    /* renamed from: a */
    private boolean m20382a(C5607q c5607q) {
        String a = c5607q.m20413a("Content-Encoding");
        return (a == null || a.equalsIgnoreCase(HTTP.IDENTITY_CODING)) ? false : true;
    }
}
