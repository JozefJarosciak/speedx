package retrofit2;

import com.alipay.sdk.util.C0880h;
import java.io.IOException;
import okhttp3.C5602x;
import okhttp3.C5603o.C5601a;
import okhttp3.C5607q;
import okhttp3.C5608s;
import okhttp3.C5611t;
import okhttp3.C5611t.C5609a;
import okhttp3.C5611t.C5610b;
import okhttp3.C5621w;
import okhttp3.C5621w.C5620a;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okio.C5635d;
import okio.C5637c;

final class RequestBuilder {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final HttpUrl baseUrl;
    private C5602x body;
    private C5608s contentType;
    private C5601a formBuilder;
    private final boolean hasBody;
    private final String method;
    private C5609a multipartBuilder;
    private String relativeUrl;
    private final C5620a requestBuilder = new C5620a();
    private Builder urlBuilder;

    private static class ContentTypeOverridingRequestBody extends C5602x {
        private final C5608s contentType;
        private final C5602x delegate;

        ContentTypeOverridingRequestBody(C5602x c5602x, C5608s c5608s) {
            this.delegate = c5602x;
            this.contentType = c5608s;
        }

        public C5608s contentType() {
            return this.contentType;
        }

        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        public void writeTo(C5635d c5635d) throws IOException {
            this.delegate.writeTo(c5635d);
        }
    }

    RequestBuilder(String str, HttpUrl httpUrl, String str2, C5607q c5607q, C5608s c5608s, boolean z, boolean z2, boolean z3) {
        this.method = str;
        this.baseUrl = httpUrl;
        this.relativeUrl = str2;
        this.contentType = c5608s;
        this.hasBody = z;
        if (c5607q != null) {
            this.requestBuilder.m20508a(c5607q);
        }
        if (z2) {
            this.formBuilder = new C5601a();
        } else if (z3) {
            this.multipartBuilder = new C5609a();
            this.multipartBuilder.m20426a(C5611t.f18074e);
        }
    }

    void setRelativeUrl(Object obj) {
        if (obj == null) {
            throw new NullPointerException("@Url parameter is null.");
        }
        this.relativeUrl = obj.toString();
    }

    void addHeader(String str, String str2) {
        if ("Content-Type".equalsIgnoreCase(str)) {
            C5608s a = C5608s.m20418a(str2);
            if (a == null) {
                throw new IllegalArgumentException("Malformed content type: " + str2);
            }
            this.contentType = a;
            return;
        }
        this.requestBuilder.m20512b(str, str2);
    }

    void addPathParam(String str, String str2, boolean z) {
        if (this.relativeUrl == null) {
            throw new AssertionError();
        }
        this.relativeUrl = this.relativeUrl.replace("{" + str + C0880h.f2222d, canonicalizeForPath(str2, z));
    }

    private static String canonicalizeForPath(String str, boolean z) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt < 32 || codePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                C5637c c5637c = new C5637c();
                c5637c.m20632a(str, 0, i);
                canonicalizeForPath(c5637c, str, i, length, z);
                return c5637c.m20681p();
            }
            i += Character.charCount(codePointAt);
        }
        return str;
    }

    private static void canonicalizeForPath(C5637c c5637c, String str, int i, int i2, boolean z) {
        C5637c c5637c2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt < 32 || codePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                    if (c5637c2 == null) {
                        c5637c2 = new C5637c();
                    }
                    c5637c2.m20630a(codePointAt);
                    while (!c5637c2.mo6818f()) {
                        int i3 = c5637c2.mo6823i() & 255;
                        c5637c.m20641b(37);
                        c5637c.m20641b(HEX_DIGITS[(i3 >> 4) & 15]);
                        c5637c.m20641b(HEX_DIGITS[i3 & 15]);
                    }
                } else {
                    c5637c.m20630a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    void addQueryParam(String str, String str2, boolean z) {
        if (this.relativeUrl != null) {
            this.urlBuilder = this.baseUrl.m19674d(this.relativeUrl);
            if (this.urlBuilder == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
            this.relativeUrl = null;
        }
        if (z) {
            this.urlBuilder.m19648b(str, str2);
        } else {
            this.urlBuilder.m19645a(str, str2);
        }
    }

    void addFormField(String str, String str2, boolean z) {
        if (z) {
            this.formBuilder.m20396b(str, str2);
        } else {
            this.formBuilder.m20394a(str, str2);
        }
    }

    void addPart(C5607q c5607q, C5602x c5602x) {
        this.multipartBuilder.m20425a(c5607q, c5602x);
    }

    void addPart(C5610b c5610b) {
        this.multipartBuilder.m20427a(c5610b);
    }

    void setBody(C5602x c5602x) {
        this.body = c5602x;
    }

    C5621w build() {
        HttpUrl c;
        Builder builder = this.urlBuilder;
        if (builder != null) {
            c = builder.m19650c();
        } else {
            c = this.baseUrl.m19671c(this.relativeUrl);
            if (c == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
        }
        C5602x c5602x = this.body;
        if (c5602x == null) {
            if (this.formBuilder != null) {
                c5602x = this.formBuilder.m20395a();
            } else if (this.multipartBuilder != null) {
                c5602x = this.multipartBuilder.m20428a();
            } else if (this.hasBody) {
                c5602x = C5602x.create(null, new byte[0]);
            }
        }
        C5608s c5608s = this.contentType;
        if (c5608s != null) {
            if (c5602x != null) {
                c5602x = new ContentTypeOverridingRequestBody(c5602x, c5608s);
            } else {
                this.requestBuilder.m20512b("Content-Type", c5608s.toString());
            }
        }
        return this.requestBuilder.m20507a(c).m20506a(this.method, c5602x).m20510a();
    }
}
