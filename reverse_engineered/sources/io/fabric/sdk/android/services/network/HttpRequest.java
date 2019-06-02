package io.fabric.sdk.android.services.network;

import ch.qos.logback.core.CoreConstants;
import com.facebook.stetho.dumpapp.Framer;
import com.loopj.android.http.AsyncHttpClient;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.slf4j.Marker;

public class HttpRequest {
    /* renamed from: b */
    private static final String[] f17252b = new String[0];
    /* renamed from: c */
    private static C4919c f17253c = C4919c.f17250a;
    /* renamed from: a */
    public final URL f17254a;
    /* renamed from: d */
    private HttpURLConnection f17255d = null;
    /* renamed from: e */
    private final String f17256e;
    /* renamed from: f */
    private C4921e f17257f;
    /* renamed from: g */
    private boolean f17258g;
    /* renamed from: h */
    private boolean f17259h = true;
    /* renamed from: i */
    private boolean f17260i = false;
    /* renamed from: j */
    private int f17261j = 8192;
    /* renamed from: k */
    private String f17262k;
    /* renamed from: l */
    private int f17263l;

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$d */
    protected static abstract class C4915d<V> implements Callable<V> {
        /* renamed from: b */
        protected abstract V mo6257b() throws HttpRequestException, IOException;

        /* renamed from: c */
        protected abstract void mo6256c() throws IOException;

        protected C4915d() {
        }

        public V call() throws HttpRequestException {
            Throwable th;
            Object obj = 1;
            try {
                V b = mo6257b();
                try {
                    mo6256c();
                    return b;
                } catch (IOException e) {
                    throw new HttpRequestException(e);
                }
            } catch (HttpRequestException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new HttpRequestException(e3);
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                mo6256c();
            } catch (IOException e4) {
                if (obj == null) {
                    throw new HttpRequestException(e4);
                }
            }
            throw th;
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$b */
    protected static abstract class C4916b<V> extends C4915d<V> {
        /* renamed from: a */
        private final Closeable f17244a;
        /* renamed from: b */
        private final boolean f17245b;

        protected C4916b(Closeable closeable, boolean z) {
            this.f17244a = closeable;
            this.f17245b = z;
        }

        /* renamed from: c */
        protected void mo6256c() throws IOException {
            if (this.f17244a instanceof Flushable) {
                ((Flushable) this.f17244a).flush();
            }
            if (this.f17245b) {
                try {
                    this.f17244a.close();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
            this.f17244a.close();
        }
    }

    public static class HttpRequestException extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        protected HttpRequestException(IOException iOException) {
            super(iOException);
        }

        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$a */
    public static class C4918a {
        /* renamed from: a */
        private static final byte[] f17249a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};

        /* renamed from: a */
        private static byte[] m19291a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            int i4 = 0;
            byte[] bArr3 = f17249a;
            int i5 = (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
            if (i2 > 2) {
                i4 = (bArr[i + 2] << 24) >>> 24;
            }
            i4 |= i5;
            switch (i2) {
                case 1:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = (byte) 61;
                    bArr2[i3 + 3] = (byte) 61;
                    break;
                case 2:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
                    bArr2[i3 + 3] = (byte) 61;
                    break;
                case 3:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
                    bArr2[i3 + 3] = bArr3[i4 & 63];
                    break;
            }
            return bArr2;
        }

        /* renamed from: a */
        public static String m19288a(String str) {
            byte[] bytes;
            try {
                bytes = str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException e) {
                bytes = str.getBytes();
            }
            return C4918a.m19289a(bytes);
        }

        /* renamed from: a */
        public static String m19289a(byte[] bArr) {
            return C4918a.m19290a(bArr, 0, bArr.length);
        }

        /* renamed from: a */
        public static String m19290a(byte[] bArr, int i, int i2) {
            byte[] b = C4918a.m19292b(bArr, i, i2);
            try {
                return new String(b, "US-ASCII");
            } catch (UnsupportedEncodingException e) {
                return new String(b);
            }
        }

        /* renamed from: b */
        public static byte[] m19292b(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException("Cannot serialize a null array.");
            } else if (i < 0) {
                throw new IllegalArgumentException("Cannot have negative offset: " + i);
            } else if (i2 < 0) {
                throw new IllegalArgumentException("Cannot have length offset: " + i2);
            } else if (i + i2 > bArr.length) {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)}));
            } else {
                Object obj = new byte[((i2 % 3 > 0 ? 4 : 0) + ((i2 / 3) * 4))];
                int i3 = i2 - 2;
                int i4 = 0;
                int i5 = 0;
                while (i5 < i3) {
                    C4918a.m19291a(bArr, i5 + i, 3, obj, i4);
                    i5 += 3;
                    i4 += 4;
                }
                if (i5 < i2) {
                    C4918a.m19291a(bArr, i5 + i, i2 - i5, obj, i4);
                    i4 += 4;
                }
                if (i4 > obj.length - 1) {
                    return obj;
                }
                Object obj2 = new byte[i4];
                System.arraycopy(obj, 0, obj2, 0, i4);
                return obj2;
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$c */
    public interface C4919c {
        /* renamed from: a */
        public static final C4919c f17250a = new C49201();

        /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$c$1 */
        static class C49201 implements C4919c {
            C49201() {
            }

            /* renamed from: a */
            public HttpURLConnection mo6258a(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }

            /* renamed from: a */
            public HttpURLConnection mo6259a(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        }

        /* renamed from: a */
        HttpURLConnection mo6258a(URL url) throws IOException;

        /* renamed from: a */
        HttpURLConnection mo6259a(URL url, Proxy proxy) throws IOException;
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest$e */
    public static class C4921e extends BufferedOutputStream {
        /* renamed from: a */
        private final CharsetEncoder f17251a;

        public C4921e(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.f17251a = Charset.forName(HttpRequest.m19310f(str)).newEncoder();
        }

        /* renamed from: a */
        public C4921e m19297a(String str) throws IOException {
            ByteBuffer encode = this.f17251a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    /* renamed from: f */
    private static String m19310f(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    /* renamed from: a */
    private static StringBuilder m19302a(String str, StringBuilder stringBuilder) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            stringBuilder.append('/');
        }
        return stringBuilder;
    }

    /* renamed from: b */
    private static StringBuilder m19305b(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(63);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            stringBuilder.append('&');
        }
        return stringBuilder;
    }

    /* renamed from: a */
    public static String m19300a(CharSequence charSequence) throws HttpRequestException {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + CoreConstants.COLON_CHAR + Integer.toString(port);
            }
            try {
                String toASCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = toASCIIString.indexOf(63);
                if (indexOf > 0 && indexOf + 1 < toASCIIString.length()) {
                    toASCIIString = toASCIIString.substring(0, indexOf + 1) + toASCIIString.substring(indexOf + 1).replace(Marker.ANY_NON_NULL_MARKER, "%2B");
                }
                return toASCIIString;
            } catch (Throwable e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    /* renamed from: a */
    public static String m19301a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder stringBuilder = new StringBuilder(charSequence2);
        m19302a(charSequence2, stringBuilder);
        m19305b(charSequence2, stringBuilder);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append('&');
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static HttpRequest m19303b(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "GET");
    }

    /* renamed from: a */
    public static HttpRequest m19299a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = m19301a(charSequence, (Map) map);
        if (z) {
            a = m19300a(a);
        }
        return m19303b(a);
    }

    /* renamed from: c */
    public static HttpRequest m19306c(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "POST");
    }

    /* renamed from: b */
    public static HttpRequest m19304b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = m19301a(charSequence, (Map) map);
        if (z) {
            a = m19300a(a);
        }
        return m19306c(a);
    }

    /* renamed from: d */
    public static HttpRequest m19307d(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "PUT");
    }

    /* renamed from: e */
    public static HttpRequest m19308e(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "DELETE");
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequestException {
        try {
            this.f17254a = new URL(charSequence.toString());
            this.f17256e = str;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* renamed from: p */
    private Proxy m19311p() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.f17262k, this.f17263l));
    }

    /* renamed from: q */
    private HttpURLConnection m19312q() {
        try {
            HttpURLConnection a;
            if (this.f17262k != null) {
                a = f17253c.mo6259a(this.f17254a, m19311p());
            } else {
                a = f17253c.mo6258a(this.f17254a);
            }
            a.setRequestMethod(this.f17256e);
            return a;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        return m19348o() + ' ' + m19347n();
    }

    /* renamed from: a */
    public HttpURLConnection m19324a() {
        if (this.f17255d == null) {
            this.f17255d = m19312q();
        }
        return this.f17255d;
    }

    /* renamed from: b */
    public int m19325b() throws HttpRequestException {
        try {
            m19343j();
            return m19324a().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public boolean ok() throws HttpRequestException {
        return 200 == m19325b();
    }

    /* renamed from: c */
    protected ByteArrayOutputStream m19330c() {
        int i = m19342i();
        if (i > 0) {
            return new ByteArrayOutputStream(i);
        }
        return new ByteArrayOutputStream();
    }

    /* renamed from: a */
    public String m19323a(String str) throws HttpRequestException {
        OutputStream c = m19330c();
        try {
            m19315a(m19336e(), c);
            return c.toString(m19310f(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* renamed from: d */
    public String m19334d() throws HttpRequestException {
        return m19323a(m19340g());
    }

    /* renamed from: e */
    public BufferedInputStream m19336e() throws HttpRequestException {
        return new BufferedInputStream(m19339f(), this.f17261j);
    }

    /* renamed from: f */
    public InputStream m19339f() throws HttpRequestException {
        if (m19325b() < HttpStatus.SC_BAD_REQUEST) {
            try {
                InputStream inputStream = m19324a().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        }
        inputStream = m19324a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = m19324a().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequestException(e2);
            }
        }
        if (!this.f17260i || !AsyncHttpClient.ENCODING_GZIP.equals(m19341h())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e22) {
            throw new HttpRequestException(e22);
        }
    }

    /* renamed from: a */
    public HttpRequest m19314a(int i) {
        m19324a().setConnectTimeout(i);
        return this;
    }

    /* renamed from: a */
    public HttpRequest m19317a(String str, String str2) {
        m19324a().setRequestProperty(str, str2);
        return this;
    }

    /* renamed from: b */
    public String m19327b(String str) throws HttpRequestException {
        m19344k();
        return m19324a().getHeaderField(str);
    }

    /* renamed from: c */
    public int m19329c(String str) throws HttpRequestException {
        return m19313a(str, -1);
    }

    /* renamed from: a */
    public int m19313a(String str, int i) throws HttpRequestException {
        m19344k();
        return m19324a().getHeaderFieldInt(str, i);
    }

    /* renamed from: b */
    public String m19328b(String str, String str2) {
        return m19331c(m19327b(str), str2);
    }

    /* renamed from: c */
    protected String m19331c(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = indexOf;
            indexOf = length;
        } else {
            int i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        while (indexOf2 < indexOf) {
            int indexOf3 = str.indexOf(61, indexOf2);
            if (indexOf3 != -1 && indexOf3 < indexOf && str2.equals(str.substring(indexOf2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, indexOf).trim();
                indexOf3 = trim.length();
                if (indexOf3 != 0) {
                    if (indexOf3 > 2 && CoreConstants.DOUBLE_QUOTE_CHAR == trim.charAt(0) && CoreConstants.DOUBLE_QUOTE_CHAR == trim.charAt(indexOf3 - 1)) {
                        return trim.substring(1, indexOf3 - 1);
                    }
                    return trim;
                }
            }
            indexOf++;
            indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        return null;
    }

    /* renamed from: g */
    public String m19340g() {
        return m19328b("Content-Type", "charset");
    }

    /* renamed from: a */
    public HttpRequest m19322a(boolean z) {
        m19324a().setUseCaches(z);
        return this;
    }

    /* renamed from: h */
    public String m19341h() {
        return m19327b("Content-Encoding");
    }

    /* renamed from: d */
    public HttpRequest m19332d(String str) {
        return m19333d(str, null);
    }

    /* renamed from: d */
    public HttpRequest m19333d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return m19317a("Content-Type", str);
        }
        String str3 = HTTP.CHARSET_PARAM;
        return m19317a("Content-Type", str + HTTP.CHARSET_PARAM + str2);
    }

    /* renamed from: i */
    public int m19342i() {
        return m19329c("Content-Length");
    }

    /* renamed from: a */
    protected HttpRequest m19315a(InputStream inputStream, OutputStream outputStream) throws IOException {
        final InputStream inputStream2 = inputStream;
        final OutputStream outputStream2 = outputStream;
        return (HttpRequest) new C4916b<HttpRequest>(this, inputStream, this.f17259h) {
            /* renamed from: c */
            final /* synthetic */ HttpRequest f17248c;

            /* renamed from: b */
            public /* synthetic */ Object mo6257b() throws HttpRequestException, IOException {
                return m19286a();
            }

            /* renamed from: a */
            public HttpRequest m19286a() throws IOException {
                byte[] bArr = new byte[this.f17248c.f17261j];
                while (true) {
                    int read = inputStream2.read(bArr);
                    if (read == -1) {
                        return this.f17248c;
                    }
                    outputStream2.write(bArr, 0, read);
                }
            }
        }.call();
    }

    /* renamed from: j */
    protected HttpRequest m19343j() throws IOException {
        if (this.f17257f != null) {
            if (this.f17258g) {
                this.f17257f.m19297a("\r\n--00content0boundary00--\r\n");
            }
            if (this.f17259h) {
                try {
                    this.f17257f.close();
                } catch (IOException e) {
                }
            } else {
                this.f17257f.close();
            }
            this.f17257f = null;
        }
        return this;
    }

    /* renamed from: k */
    protected HttpRequest m19344k() throws HttpRequestException {
        try {
            return m19343j();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* renamed from: l */
    protected HttpRequest m19345l() throws IOException {
        if (this.f17257f == null) {
            m19324a().setDoOutput(true);
            this.f17257f = new C4921e(m19324a().getOutputStream(), m19331c(m19324a().getRequestProperty("Content-Type"), "charset"), this.f17261j);
        }
        return this;
    }

    /* renamed from: m */
    protected HttpRequest m19346m() throws IOException {
        if (this.f17258g) {
            this.f17257f.m19297a("\r\n--00content0boundary00\r\n");
        } else {
            this.f17258g = true;
            m19332d("multipart/form-data; boundary=00content0boundary00").m19345l();
            this.f17257f.m19297a("--00content0boundary00\r\n");
        }
        return this;
    }

    /* renamed from: a */
    protected HttpRequest m19319a(String str, String str2, String str3) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"").append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"").append(str2);
        }
        stringBuilder.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        m19338f("Content-Disposition", stringBuilder.toString());
        if (str3 != null) {
            m19338f("Content-Type", str3);
        }
        return m19337f((CharSequence) "\r\n");
    }

    /* renamed from: e */
    public HttpRequest m19335e(String str, String str2) {
        return m19326b(str, null, str2);
    }

    /* renamed from: b */
    public HttpRequest m19326b(String str, String str2, String str3) throws HttpRequestException {
        return m19321a(str, str2, null, str3);
    }

    /* renamed from: a */
    public HttpRequest m19321a(String str, String str2, String str3, String str4) throws HttpRequestException {
        try {
            m19346m();
            m19319a(str, str2, str3);
            this.f17257f.m19297a(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* renamed from: a */
    public HttpRequest m19316a(String str, Number number) throws HttpRequestException {
        return m19318a(str, null, number);
    }

    /* renamed from: a */
    public HttpRequest m19318a(String str, String str2, Number number) throws HttpRequestException {
        return m19326b(str, str2, number != null ? number.toString() : null);
    }

    /* renamed from: a */
    public HttpRequest m19320a(String str, String str2, String str3, InputStream inputStream) throws HttpRequestException {
        try {
            m19346m();
            m19319a(str, str2, str3);
            m19315a(inputStream, this.f17257f);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* renamed from: f */
    public HttpRequest m19338f(String str, String str2) throws HttpRequestException {
        return m19337f((CharSequence) str).m19337f((CharSequence) ": ").m19337f((CharSequence) str2).m19337f((CharSequence) "\r\n");
    }

    /* renamed from: f */
    public HttpRequest m19337f(CharSequence charSequence) throws HttpRequestException {
        try {
            m19345l();
            this.f17257f.m19297a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* renamed from: n */
    public URL m19347n() {
        return m19324a().getURL();
    }

    /* renamed from: o */
    public String m19348o() {
        return m19324a().getRequestMethod();
    }
}
