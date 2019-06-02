package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.C5586l;
import okio.C5636e;
import okio.C5637c;

/* compiled from: ResponseBody */
/* renamed from: okhttp3.z */
public abstract class C5569z implements Closeable {
    private Reader reader;

    public abstract long contentLength();

    public abstract C5608s contentType();

    public abstract C5636e source();

    public final InputStream byteStream() {
        return source().mo6819g();
    }

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        Closeable source = source();
        try {
            byte[] s = source.mo6835s();
            if (contentLength == -1 || contentLength == ((long) s.length)) {
                return s;
            }
            throw new IOException("Content-Length and stream length disagree");
        } finally {
            C5586l.m20327a(source);
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        reader = new InputStreamReader(byteStream(), charset());
        this.reader = reader;
        return reader;
    }

    public final String string() throws IOException {
        return new String(bytes(), charset().name());
    }

    private Charset charset() {
        C5608s contentType = contentType();
        return contentType != null ? contentType.m20420a(C5586l.f18008c) : C5586l.f18008c;
    }

    public void close() {
        C5586l.m20327a(source());
    }

    public static C5569z create(C5608s c5608s, String str) {
        Charset charset = C5586l.f18008c;
        if (c5608s != null) {
            charset = c5608s.m20422c();
            if (charset == null) {
                charset = C5586l.f18008c;
                c5608s = C5608s.m20418a(c5608s + "; charset=utf-8");
            }
        }
        C5636e a = new C5637c().m20634a(str, charset);
        return C5569z.create(c5608s, a.m20623a(), a);
    }

    public static C5569z create(C5608s c5608s, byte[] bArr) {
        return C5569z.create(c5608s, (long) bArr.length, new C5637c().m20642b(bArr));
    }

    public static C5569z create(final C5608s c5608s, final long j, final C5636e c5636e) {
        if (c5636e != null) {
            return new C5569z() {
                public C5608s contentType() {
                    return c5608s;
                }

                public long contentLength() {
                    return j;
                }

                public C5636e source() {
                    return c5636e;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
