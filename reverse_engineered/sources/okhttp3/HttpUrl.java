package okhttp3;

import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.cons.C0845b;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.C5586l;
import okio.C5637c;
import org.apache.http.HttpHost;
import org.java_websocket.WebSocket;
import org.slf4j.Marker;

public final class HttpUrl {
    /* renamed from: a */
    private static final char[] f17570a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /* renamed from: b */
    private final String f17571b;
    /* renamed from: c */
    private final String f17572c;
    /* renamed from: d */
    private final String f17573d;
    /* renamed from: e */
    private final String f17574e;
    /* renamed from: f */
    private final int f17575f;
    /* renamed from: g */
    private final List<String> f17576g;
    /* renamed from: h */
    private final List<String> f17577h;
    /* renamed from: i */
    private final String f17578i;
    /* renamed from: j */
    private final String f17579j;

    /* renamed from: okhttp3.HttpUrl$1 */
    static /* synthetic */ class C54591 {
        /* renamed from: a */
        static final /* synthetic */ int[] f17561a = new int[ParseResult.values().length];

        static {
            try {
                f17561a[ParseResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f17561a[ParseResult.INVALID_HOST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f17561a[ParseResult.UNSUPPORTED_SCHEME.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f17561a[ParseResult.MISSING_SCHEME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f17561a[ParseResult.INVALID_PORT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static final class Builder {
        /* renamed from: a */
        String f17562a;
        /* renamed from: b */
        String f17563b = "";
        /* renamed from: c */
        String f17564c = "";
        /* renamed from: d */
        String f17565d;
        /* renamed from: e */
        int f17566e = -1;
        /* renamed from: f */
        final List<String> f17567f = new ArrayList();
        /* renamed from: g */
        List<String> f17568g;
        /* renamed from: h */
        String f17569h;

        enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public Builder() {
            this.f17567f.add("");
        }

        /* renamed from: a */
        public Builder m19644a(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase(HttpHost.DEFAULT_SCHEME_NAME)) {
                this.f17562a = HttpHost.DEFAULT_SCHEME_NAME;
            } else if (str.equalsIgnoreCase(C0845b.f2060a)) {
                this.f17562a = C0845b.f2060a;
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        /* renamed from: b */
        public Builder m19647b(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String e = m19636e(str, 0, str.length());
            if (e == null) {
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            this.f17565d = e;
            return this;
        }

        /* renamed from: a */
        public Builder m19643a(int i) {
            if (i <= 0 || i > 65535) {
                throw new IllegalArgumentException("unexpected port: " + i);
            }
            this.f17566e = i;
            return this;
        }

        /* renamed from: a */
        int m19641a() {
            return this.f17566e != -1 ? this.f17566e : HttpUrl.m19653a(this.f17562a);
        }

        /* renamed from: c */
        public Builder m19649c(String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            m19630a(str, 0, str.length(), false, false);
            return this;
        }

        /* renamed from: d */
        public Builder m19651d(String str) {
            List b;
            if (str != null) {
                b = HttpUrl.m19665b(HttpUrl.m19656a(str, " \"'<>#", true, false, true, true));
            } else {
                b = null;
            }
            this.f17568g = b;
            return this;
        }

        /* renamed from: a */
        public Builder m19645a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            Object a;
            if (this.f17568g == null) {
                this.f17568g = new ArrayList();
            }
            this.f17568g.add(HttpUrl.m19656a(str, " \"'<>#&=", false, false, true, true));
            List list = this.f17568g;
            if (str2 != null) {
                a = HttpUrl.m19656a(str2, " \"'<>#&=", false, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        /* renamed from: b */
        public Builder m19648b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            Object a;
            if (this.f17568g == null) {
                this.f17568g = new ArrayList();
            }
            this.f17568g.add(HttpUrl.m19656a(str, " \"'<>#&=", true, false, true, true));
            List list = this.f17568g;
            if (str2 != null) {
                a = HttpUrl.m19656a(str2, " \"'<>#&=", true, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        /* renamed from: b */
        Builder m19646b() {
            int size = this.f17567f.size();
            for (int i = 0; i < size; i++) {
                this.f17567f.set(i, HttpUrl.m19656a((String) this.f17567f.get(i), "[]", true, true, false, true));
            }
            if (this.f17568g != null) {
                int size2 = this.f17568g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = (String) this.f17568g.get(i2);
                    if (str != null) {
                        this.f17568g.set(i2, HttpUrl.m19656a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            if (this.f17569h != null) {
                this.f17569h = HttpUrl.m19656a(this.f17569h, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        /* renamed from: c */
        public HttpUrl m19650c() {
            if (this.f17562a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.f17565d != null) {
                return new HttpUrl();
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f17562a);
            stringBuilder.append("://");
            if (!(this.f17563b.isEmpty() && this.f17564c.isEmpty())) {
                stringBuilder.append(this.f17563b);
                if (!this.f17564c.isEmpty()) {
                    stringBuilder.append(CoreConstants.COLON_CHAR);
                    stringBuilder.append(this.f17564c);
                }
                stringBuilder.append('@');
            }
            if (this.f17565d.indexOf(58) != -1) {
                stringBuilder.append('[');
                stringBuilder.append(this.f17565d);
                stringBuilder.append(']');
            } else {
                stringBuilder.append(this.f17565d);
            }
            int a = m19641a();
            if (a != HttpUrl.m19653a(this.f17562a)) {
                stringBuilder.append(CoreConstants.COLON_CHAR);
                stringBuilder.append(a);
            }
            HttpUrl.m19660a(stringBuilder, this.f17567f);
            if (this.f17568g != null) {
                stringBuilder.append('?');
                HttpUrl.m19666b(stringBuilder, this.f17568g);
            }
            if (this.f17569h != null) {
                stringBuilder.append('#');
                stringBuilder.append(this.f17569h);
            }
            return stringBuilder.toString();
        }

        /* renamed from: a */
        ParseResult m19642a(HttpUrl httpUrl, String str) {
            int d;
            int a = C5586l.m20315a(str, 0, str.length());
            int b = C5586l.m20335b(str, a, str.length());
            if (m19632b(str, a, b) != -1) {
                if (str.regionMatches(true, a, "https:", 0, 6)) {
                    this.f17562a = C0845b.f2060a;
                    a += "https:".length();
                } else {
                    if (!str.regionMatches(true, a, "http:", 0, 5)) {
                        return ParseResult.UNSUPPORTED_SCHEME;
                    }
                    this.f17562a = HttpHost.DEFAULT_SCHEME_NAME;
                    a += "http:".length();
                }
            } else if (httpUrl == null) {
                return ParseResult.MISSING_SCHEME;
            } else {
                this.f17562a = httpUrl.f17571b;
            }
            int c = m19633c(str, a, b);
            if (c >= 2 || httpUrl == null || !httpUrl.f17571b.equals(this.f17562a)) {
                Object obj = null;
                Object obj2 = null;
                int i = a + c;
                while (true) {
                    Object obj3;
                    Object obj4;
                    int a2 = C5586l.m20317a(str, i, b, "@/\\?#");
                    switch (a2 != b ? str.charAt(a2) : '￿') {
                        case '￿':
                        case '#':
                        case '/':
                        case '?':
                        case '\\':
                            d = m19634d(str, i, a2);
                            if (d + 1 < a2) {
                                this.f17565d = m19636e(str, i, d);
                                this.f17566e = m19640g(str, d + 1, a2);
                                if (this.f17566e == -1) {
                                    return ParseResult.INVALID_PORT;
                                }
                            }
                            this.f17565d = m19636e(str, i, d);
                            this.f17566e = HttpUrl.m19653a(this.f17562a);
                            if (this.f17565d != null) {
                                a = a2;
                                break;
                            }
                            return ParseResult.INVALID_HOST;
                        case '@':
                            if (obj == null) {
                                a = C5586l.m20316a(str, i, a2, (char) CoreConstants.COLON_CHAR);
                                String a3 = HttpUrl.m19654a(str, i, a, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                if (obj2 != null) {
                                    a3 = this.f17563b + "%40" + a3;
                                }
                                this.f17563b = a3;
                                if (a != a2) {
                                    obj = 1;
                                    this.f17564c = HttpUrl.m19654a(str, a + 1, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                }
                                obj2 = 1;
                            } else {
                                this.f17564c += "%40" + HttpUrl.m19654a(str, i, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            }
                            a = a2 + 1;
                            obj3 = obj;
                            obj4 = obj2;
                            continue;
                        default:
                            obj3 = obj;
                            a = i;
                            obj4 = obj2;
                            continue;
                    }
                    obj = obj3;
                    obj2 = obj4;
                    i = a;
                }
            } else {
                this.f17563b = httpUrl.m19673d();
                this.f17564c = httpUrl.m19675e();
                this.f17565d = httpUrl.f17574e;
                this.f17566e = httpUrl.f17575f;
                this.f17567f.clear();
                this.f17567f.addAll(httpUrl.m19679i());
                if (a == b || str.charAt(a) == '#') {
                    m19651d(httpUrl.m19681k());
                }
            }
            d = C5586l.m20317a(str, a, b, "?#");
            m19629a(str, a, d);
            if (d >= b || str.charAt(d) != '?') {
                a = d;
            } else {
                a = C5586l.m20316a(str, d, b, '#');
                this.f17568g = HttpUrl.m19665b(HttpUrl.m19654a(str, d + 1, a, " \"'<>#", true, false, true, true));
            }
            if (a < b && str.charAt(a) == '#') {
                this.f17569h = HttpUrl.m19654a(str, a + 1, b, "", true, false, false, false);
            }
            return ParseResult.SUCCESS;
        }

        /* renamed from: a */
        private void m19629a(String str, int i, int i2) {
            if (i != i2) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == CoreConstants.ESCAPE_CHAR) {
                    this.f17567f.clear();
                    this.f17567f.add("");
                    i++;
                } else {
                    this.f17567f.set(this.f17567f.size() - 1, "");
                }
                int i3 = i;
                while (i3 < i2) {
                    int a = C5586l.m20317a(str, i3, i2, "/\\");
                    boolean z = a < i2;
                    m19630a(str, i3, a, z, true);
                    if (z) {
                        a++;
                    }
                    i3 = a;
                }
            }
        }

        /* renamed from: a */
        private void m19630a(String str, int i, int i2, boolean z, boolean z2) {
            String a = HttpUrl.m19654a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (!m19637e(a)) {
                if (m19639f(a)) {
                    m19635d();
                    return;
                }
                if (((String) this.f17567f.get(this.f17567f.size() - 1)).isEmpty()) {
                    this.f17567f.set(this.f17567f.size() - 1, a);
                } else {
                    this.f17567f.add(a);
                }
                if (z) {
                    this.f17567f.add("");
                }
            }
        }

        /* renamed from: e */
        private boolean m19637e(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        /* renamed from: f */
        private boolean m19639f(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        /* renamed from: d */
        private void m19635d() {
            if (!((String) this.f17567f.remove(this.f17567f.size() - 1)).isEmpty() || this.f17567f.isEmpty()) {
                this.f17567f.add("");
            } else {
                this.f17567f.set(this.f17567f.size() - 1, "");
            }
        }

        /* renamed from: b */
        private static int m19632b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                return -1;
            }
            int i3 = i + 1;
            while (i3 < i2) {
                char charAt2 = str.charAt(i3);
                if ((charAt2 >= 'a' && charAt2 <= 'z') || ((charAt2 >= 'A' && charAt2 <= 'Z') || ((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == CoreConstants.DASH_CHAR || charAt2 == CoreConstants.DOT))) {
                    i3++;
                } else if (charAt2 == CoreConstants.COLON_CHAR) {
                    return i3;
                } else {
                    return -1;
                }
            }
            return -1;
        }

        /* renamed from: c */
        private static int m19633c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != CoreConstants.ESCAPE_CHAR && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: d */
        private static int m19634d(java.lang.String r3, int r4, int r5) {
            /*
            r0 = r4;
        L_0x0001:
            if (r0 >= r5) goto L_0x001a;
        L_0x0003:
            r1 = r3.charAt(r0);
            switch(r1) {
                case 58: goto L_0x001b;
                case 91: goto L_0x000d;
                default: goto L_0x000a;
            };
        L_0x000a:
            r0 = r0 + 1;
            goto L_0x0001;
        L_0x000d:
            r0 = r0 + 1;
            if (r0 >= r5) goto L_0x000a;
        L_0x0011:
            r1 = r3.charAt(r0);
            r2 = 93;
            if (r1 != r2) goto L_0x000d;
        L_0x0019:
            goto L_0x000a;
        L_0x001a:
            r0 = r5;
        L_0x001b:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.d(java.lang.String, int, int):int");
        }

        /* renamed from: e */
        private static String m19636e(String str, int i, int i2) {
            String a = HttpUrl.m19655a(str, i, i2, false);
            if (!a.contains(":")) {
                return C5586l.m20336b(a);
            }
            InetAddress f;
            if (a.startsWith("[") && a.endsWith("]")) {
                f = m19638f(a, 1, a.length() - 1);
            } else {
                f = m19638f(a, 0, a.length());
            }
            if (f == null) {
                return null;
            }
            byte[] address = f.getAddress();
            if (address.length == 16) {
                return m19628a(address);
            }
            throw new AssertionError();
        }

        /* renamed from: f */
        private static InetAddress m19638f(String str, int i, int i2) {
            byte[] bArr = new byte[16];
            int i3 = i;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            while (i3 < i2) {
                if (i6 == bArr.length) {
                    return null;
                }
                int a;
                if (i3 + 2 > i2 || !str.regionMatches(i3, "::", 0, 2)) {
                    if (i6 != 0) {
                        if (str.regionMatches(i3, ":", 0, 1)) {
                            i3++;
                        } else if (!str.regionMatches(i3, ".", 0, 1)) {
                            return null;
                        } else {
                            if (!m19631a(str, i4, i2, bArr, i6 - 2)) {
                                return null;
                            }
                            i6 += 2;
                        }
                    }
                } else if (i5 != -1) {
                    return null;
                } else {
                    i3 += 2;
                    i5 = i6 + 2;
                    if (i3 == i2) {
                        i6 = i5;
                        break;
                    }
                    i6 = i5;
                }
                i4 = 0;
                int i7 = i3;
                while (i7 < i2) {
                    a = HttpUrl.m19652a(str.charAt(i7));
                    if (a == -1) {
                        break;
                    }
                    i4 = (i4 << 4) + a;
                    i7++;
                }
                a = i7 - i3;
                if (a == 0 || a > 4) {
                    return null;
                }
                a = i6 + 1;
                bArr[i6] = (byte) ((i4 >>> 8) & 255);
                i6 = a + 1;
                bArr[a] = (byte) (i4 & 255);
                i4 = i3;
                i3 = i7;
            }
            if (i6 != bArr.length) {
                if (i5 == -1) {
                    return null;
                }
                System.arraycopy(bArr, i5, bArr, bArr.length - (i6 - i5), i6 - i5);
                Arrays.fill(bArr, i5, (bArr.length - i6) + i5, (byte) 0);
            }
            try {
                return InetAddress.getByAddress(bArr);
            } catch (UnknownHostException e) {
                throw new AssertionError();
            }
        }

        /* renamed from: a */
        private static boolean m19631a(String str, int i, int i2, byte[] bArr, int i3) {
            int i4 = i;
            int i5 = i3;
            while (i4 < i2) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i3) {
                    if (str.charAt(i4) != CoreConstants.DOT) {
                        return false;
                    }
                    i4++;
                }
                int i6 = 0;
                int i7 = i4;
                while (i7 < i2) {
                    char charAt = str.charAt(i7);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if (i6 == 0 && i4 != i7) {
                        return false;
                    } else {
                        i6 = ((i6 * 10) + charAt) - 48;
                        if (i6 > 255) {
                            return false;
                        }
                        i7++;
                    }
                }
                if (i7 - i4 == 0) {
                    return false;
                }
                i4 = i5 + 1;
                bArr[i5] = (byte) i6;
                i5 = i4;
                i4 = i7;
            }
            if (i5 != i3 + 4) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        private static String m19628a(byte[] bArr) {
            int i = 0;
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            while (i4 < bArr.length) {
                int i5 = i4;
                while (i5 < 16 && bArr[i5] == (byte) 0 && bArr[i5 + 1] == (byte) 0) {
                    i5 += 2;
                }
                int i6 = i5 - i4;
                if (i6 > i2) {
                    i2 = i6;
                    i3 = i4;
                }
                i4 = i5 + 2;
            }
            C5637c c5637c = new C5637c();
            while (i < bArr.length) {
                if (i == i3) {
                    c5637c.m20641b(58);
                    i += i2;
                    if (i == 16) {
                        c5637c.m20641b(58);
                    }
                } else {
                    if (i > 0) {
                        c5637c.m20641b(58);
                    }
                    c5637c.m20670j((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
                    i += 2;
                }
            }
            return c5637c.m20681p();
        }

        /* renamed from: g */
        private static int m19640g(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(HttpUrl.m19654a(str, i, i2, "", false, false, false, true));
                return (parseInt <= 0 || parseInt > 65535) ? -1 : parseInt;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    private HttpUrl(Builder builder) {
        String str = null;
        this.f17571b = builder.f17562a;
        this.f17572c = m19657a(builder.f17563b, false);
        this.f17573d = m19657a(builder.f17564c, false);
        this.f17574e = builder.f17565d;
        this.f17575f = builder.m19641a();
        this.f17576g = m19659a(builder.f17567f, false);
        this.f17577h = builder.f17568g != null ? m19659a(builder.f17568g, true) : null;
        if (builder.f17569h != null) {
            str = m19657a(builder.f17569h, false);
        }
        this.f17578i = str;
        this.f17579j = builder.toString();
    }

    /* renamed from: a */
    public URI m19669a() {
        String builder = m19685o().m19646b().toString();
        try {
            return new URI(builder);
        } catch (Throwable e) {
            try {
                return URI.create(builder.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: b */
    public String m19670b() {
        return this.f17571b;
    }

    /* renamed from: c */
    public boolean m19672c() {
        return this.f17571b.equals(C0845b.f2060a);
    }

    /* renamed from: d */
    public String m19673d() {
        if (this.f17572c.isEmpty()) {
            return "";
        }
        int length = this.f17571b.length() + 3;
        return this.f17579j.substring(length, C5586l.m20317a(this.f17579j, length, this.f17579j.length(), ":@"));
    }

    /* renamed from: e */
    public String m19675e() {
        if (this.f17573d.isEmpty()) {
            return "";
        }
        return this.f17579j.substring(this.f17579j.indexOf(58, this.f17571b.length() + 3) + 1, this.f17579j.indexOf(64));
    }

    /* renamed from: f */
    public String m19676f() {
        return this.f17574e;
    }

    /* renamed from: g */
    public int m19677g() {
        return this.f17575f;
    }

    /* renamed from: a */
    public static int m19653a(String str) {
        if (str.equals(HttpHost.DEFAULT_SCHEME_NAME)) {
            return 80;
        }
        if (str.equals(C0845b.f2060a)) {
            return WebSocket.DEFAULT_WSS_PORT;
        }
        return -1;
    }

    /* renamed from: h */
    public String m19678h() {
        int indexOf = this.f17579j.indexOf(47, this.f17571b.length() + 3);
        return this.f17579j.substring(indexOf, C5586l.m20317a(this.f17579j, indexOf, this.f17579j.length(), "?#"));
    }

    /* renamed from: a */
    static void m19660a(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append('/');
            stringBuilder.append((String) list.get(i));
        }
    }

    /* renamed from: i */
    public List<String> m19679i() {
        int indexOf = this.f17579j.indexOf(47, this.f17571b.length() + 3);
        int a = C5586l.m20317a(this.f17579j, indexOf, this.f17579j.length(), "?#");
        List<String> arrayList = new ArrayList();
        while (indexOf < a) {
            int i = indexOf + 1;
            indexOf = C5586l.m20316a(this.f17579j, i, a, '/');
            arrayList.add(this.f17579j.substring(i, indexOf));
        }
        return arrayList;
    }

    /* renamed from: j */
    public List<String> m19680j() {
        return this.f17576g;
    }

    /* renamed from: k */
    public String m19681k() {
        if (this.f17577h == null) {
            return null;
        }
        int indexOf = this.f17579j.indexOf(63) + 1;
        return this.f17579j.substring(indexOf, C5586l.m20316a(this.f17579j, indexOf + 1, this.f17579j.length(), '#'));
    }

    /* renamed from: b */
    static void m19666b(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = (String) list.get(i);
            String str2 = (String) list.get(i + 1);
            if (i > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(str);
            if (str2 != null) {
                stringBuilder.append('=');
                stringBuilder.append(str2);
            }
        }
    }

    /* renamed from: b */
    static List<String> m19665b(String str) {
        List<String> arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            }
            i = indexOf + 1;
        }
        return arrayList;
    }

    /* renamed from: l */
    public String m19682l() {
        if (this.f17577h == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        m19666b(stringBuilder, this.f17577h);
        return stringBuilder.toString();
    }

    /* renamed from: m */
    public int m19683m() {
        return this.f17577h != null ? this.f17577h.size() / 2 : 0;
    }

    /* renamed from: n */
    public String m19684n() {
        if (this.f17578i == null) {
            return null;
        }
        return this.f17579j.substring(this.f17579j.indexOf(35) + 1);
    }

    /* renamed from: c */
    public HttpUrl m19671c(String str) {
        Builder d = m19674d(str);
        return d != null ? d.m19650c() : null;
    }

    /* renamed from: o */
    public Builder m19685o() {
        Builder builder = new Builder();
        builder.f17562a = this.f17571b;
        builder.f17563b = m19673d();
        builder.f17564c = m19675e();
        builder.f17565d = this.f17574e;
        builder.f17566e = this.f17575f != m19653a(this.f17571b) ? this.f17575f : -1;
        builder.f17567f.clear();
        builder.f17567f.addAll(m19679i());
        builder.m19651d(m19681k());
        builder.f17569h = m19684n();
        return builder;
    }

    /* renamed from: d */
    public Builder m19674d(String str) {
        Builder builder = new Builder();
        return builder.m19642a(this, str) == ParseResult.SUCCESS ? builder : null;
    }

    /* renamed from: e */
    public static HttpUrl m19668e(String str) {
        Builder builder = new Builder();
        if (builder.m19642a(null, str) == ParseResult.SUCCESS) {
            return builder.m19650c();
        }
        return null;
    }

    public boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).f17579j.equals(this.f17579j);
    }

    public int hashCode() {
        return this.f17579j.hashCode();
    }

    public String toString() {
        return this.f17579j;
    }

    /* renamed from: a */
    static String m19657a(String str, boolean z) {
        return m19655a(str, 0, str.length(), z);
    }

    /* renamed from: a */
    private List<String> m19659a(List<String> list, boolean z) {
        List arrayList = new ArrayList(list.size());
        for (String str : list) {
            arrayList.add(str != null ? m19657a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: a */
    static String m19655a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == CoreConstants.PERCENT_CHAR || (charAt == '+' && z)) {
                C5637c c5637c = new C5637c();
                c5637c.m20632a(str, i, i3);
                m19662a(c5637c, str, i3, i2, z);
                return c5637c.m20681p();
            }
        }
        return str.substring(i, i2);
    }

    /* renamed from: a */
    static void m19662a(C5637c c5637c, String str, int i, int i2, boolean z) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt != 37 || i3 + 2 >= i2) {
                if (codePointAt == 43 && z) {
                    c5637c.m20641b(32);
                }
                c5637c.m20630a(codePointAt);
            } else {
                int a = m19652a(str.charAt(i3 + 1));
                int a2 = m19652a(str.charAt(i3 + 2));
                if (!(a == -1 || a2 == -1)) {
                    c5637c.m20641b((a << 4) + a2);
                    i3 += 2;
                }
                c5637c.m20630a(codePointAt);
            }
            i3 += Character.charCount(codePointAt);
        }
    }

    /* renamed from: a */
    static boolean m19663a(String str, int i, int i2) {
        return i + 2 < i2 && str.charAt(i) == CoreConstants.PERCENT_CHAR && m19652a(str.charAt(i + 1)) != -1 && m19652a(str.charAt(i + 2)) != -1;
    }

    /* renamed from: a */
    static int m19652a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 65) + 10;
    }

    /* renamed from: a */
    static String m19654a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && (!z || (z2 && !m19663a(str, i3, i2)))) || (codePointAt == 43 && z3)))) {
                C5637c c5637c = new C5637c();
                c5637c.m20632a(str, i, i3);
                m19661a(c5637c, str, i3, i2, str2, z, z2, z3, z4);
                return c5637c.m20681p();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    /* renamed from: a */
    static void m19661a(C5637c c5637c, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        C5637c c5637c2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt == 43 && z3) {
                    c5637c.m20631a(z ? Marker.ANY_NON_NULL_MARKER : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !m19663a(str, i, i2)))))) {
                    if (c5637c2 == null) {
                        c5637c2 = new C5637c();
                    }
                    c5637c2.m20630a(codePointAt);
                    while (!c5637c2.mo6818f()) {
                        int i3 = c5637c2.mo6823i() & 255;
                        c5637c.m20641b(37);
                        c5637c.m20641b(f17570a[(i3 >> 4) & 15]);
                        c5637c.m20641b(f17570a[i3 & 15]);
                    }
                } else {
                    c5637c.m20630a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    /* renamed from: a */
    static String m19656a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return m19654a(str, 0, str.length(), str2, z, z2, z3, z4);
    }
}
