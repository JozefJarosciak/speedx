package okhttp3;

import ch.qos.logback.core.CoreConstants;
import okhttp3.C5607q.C5606a;
import okhttp3.internal.http.C5575h;

/* compiled from: Request */
/* renamed from: okhttp3.w */
public final class C5621w {
    /* renamed from: a */
    private final HttpUrl f18152a;
    /* renamed from: b */
    private final String f18153b;
    /* renamed from: c */
    private final C5607q f18154c;
    /* renamed from: d */
    private final C5602x f18155d;
    /* renamed from: e */
    private final Object f18156e;
    /* renamed from: f */
    private volatile C5466d f18157f;

    /* compiled from: Request */
    /* renamed from: okhttp3.w$a */
    public static class C5620a {
        /* renamed from: a */
        private HttpUrl f18147a;
        /* renamed from: b */
        private String f18148b;
        /* renamed from: c */
        private C5606a f18149c;
        /* renamed from: d */
        private C5602x f18150d;
        /* renamed from: e */
        private Object f18151e;

        public C5620a() {
            this.f18148b = "GET";
            this.f18149c = new C5606a();
        }

        private C5620a(C5621w c5621w) {
            this.f18147a = c5621w.f18152a;
            this.f18148b = c5621w.f18153b;
            this.f18150d = c5621w.f18155d;
            this.f18151e = c5621w.f18156e;
            this.f18149c = c5621w.f18154c.m20416b();
        }

        /* renamed from: a */
        public C5620a m20507a(HttpUrl httpUrl) {
            if (httpUrl == null) {
                throw new NullPointerException("url == null");
            }
            this.f18147a = httpUrl;
            return this;
        }

        /* renamed from: a */
        public C5620a m20504a(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else {
                if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    str = "https:" + str.substring(4);
                }
            }
            HttpUrl e = HttpUrl.m19668e(str);
            if (e != null) {
                return m20507a(e);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        /* renamed from: a */
        public C5620a m20505a(String str, String str2) {
            this.f18149c.m20408c(str, str2);
            return this;
        }

        /* renamed from: b */
        public C5620a m20512b(String str, String str2) {
            this.f18149c.m20404a(str, str2);
            return this;
        }

        /* renamed from: b */
        public C5620a m20511b(String str) {
            this.f18149c.m20406b(str);
            return this;
        }

        /* renamed from: a */
        public C5620a m20508a(C5607q c5607q) {
            this.f18149c = c5607q.m20416b();
            return this;
        }

        /* renamed from: a */
        public C5620a m20509a(C5602x c5602x) {
            return m20506a("POST", c5602x);
        }

        /* renamed from: a */
        public C5620a m20506a(String str, C5602x c5602x) {
            if (str == null) {
                throw new NullPointerException("method == null");
            } else if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            } else if (c5602x != null && !C5575h.m20264c(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (c5602x == null && C5575h.m20263b(str)) {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            } else {
                this.f18148b = str;
                this.f18150d = c5602x;
                return this;
            }
        }

        /* renamed from: a */
        public C5620a m20503a(Object obj) {
            this.f18151e = obj;
            return this;
        }

        /* renamed from: a */
        public C5621w m20510a() {
            if (this.f18147a != null) {
                return new C5621w();
            }
            throw new IllegalStateException("url == null");
        }
    }

    private C5621w(C5620a c5620a) {
        Object e;
        this.f18152a = c5620a.f18147a;
        this.f18153b = c5620a.f18148b;
        this.f18154c = c5620a.f18149c.m20405a();
        this.f18155d = c5620a.f18150d;
        if (c5620a.f18151e != null) {
            e = c5620a.f18151e;
        } else {
            C5621w c5621w = this;
        }
        this.f18156e = e;
    }

    /* renamed from: a */
    public HttpUrl m20519a() {
        return this.f18152a;
    }

    /* renamed from: b */
    public String m20520b() {
        return this.f18153b;
    }

    /* renamed from: c */
    public C5607q m20521c() {
        return this.f18154c;
    }

    /* renamed from: a */
    public String m20518a(String str) {
        return this.f18154c.m20413a(str);
    }

    /* renamed from: d */
    public C5602x m20522d() {
        return this.f18155d;
    }

    /* renamed from: e */
    public Object m20523e() {
        return this.f18156e;
    }

    /* renamed from: f */
    public C5620a m20524f() {
        return new C5620a();
    }

    /* renamed from: g */
    public C5466d m20525g() {
        C5466d c5466d = this.f18157f;
        if (c5466d != null) {
            return c5466d;
        }
        c5466d = C5466d.m19707a(this.f18154c);
        this.f18157f = c5466d;
        return c5466d;
    }

    /* renamed from: h */
    public boolean m20526h() {
        return this.f18152a.m19672c();
    }

    public String toString() {
        return "Request{method=" + this.f18153b + ", url=" + this.f18152a + ", tag=" + (this.f18156e != this ? this.f18156e : null) + CoreConstants.CURLY_RIGHT;
    }
}
