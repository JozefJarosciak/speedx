package okhttp3;

import ch.qos.logback.core.CoreConstants;
import java.io.Closeable;
import okhttp3.C5607q.C5606a;

/* compiled from: Response */
/* renamed from: okhttp3.y */
public final class C5627y implements Closeable {
    /* renamed from: a */
    private final C5621w f18178a;
    /* renamed from: b */
    private final Protocol f18179b;
    /* renamed from: c */
    private final int f18180c;
    /* renamed from: d */
    private final String f18181d;
    /* renamed from: e */
    private final C5604p f18182e;
    /* renamed from: f */
    private final C5607q f18183f;
    /* renamed from: g */
    private final C5569z f18184g;
    /* renamed from: h */
    private final C5627y f18185h;
    /* renamed from: i */
    private final C5627y f18186i;
    /* renamed from: j */
    private final C5627y f18187j;
    /* renamed from: k */
    private final long f18188k;
    /* renamed from: l */
    private final long f18189l;
    /* renamed from: m */
    private volatile C5466d f18190m;

    /* compiled from: Response */
    /* renamed from: okhttp3.y$a */
    public static class C5626a {
        /* renamed from: a */
        private C5621w f18166a;
        /* renamed from: b */
        private Protocol f18167b;
        /* renamed from: c */
        private int f18168c;
        /* renamed from: d */
        private String f18169d;
        /* renamed from: e */
        private C5604p f18170e;
        /* renamed from: f */
        private C5606a f18171f;
        /* renamed from: g */
        private C5569z f18172g;
        /* renamed from: h */
        private C5627y f18173h;
        /* renamed from: i */
        private C5627y f18174i;
        /* renamed from: j */
        private C5627y f18175j;
        /* renamed from: k */
        private long f18176k;
        /* renamed from: l */
        private long f18177l;

        public C5626a() {
            this.f18168c = -1;
            this.f18171f = new C5606a();
        }

        private C5626a(C5627y c5627y) {
            this.f18168c = -1;
            this.f18166a = c5627y.f18178a;
            this.f18167b = c5627y.f18179b;
            this.f18168c = c5627y.f18180c;
            this.f18169d = c5627y.f18181d;
            this.f18170e = c5627y.f18182e;
            this.f18171f = c5627y.f18183f.m20416b();
            this.f18172g = c5627y.f18184g;
            this.f18173h = c5627y.f18185h;
            this.f18174i = c5627y.f18186i;
            this.f18175j = c5627y.f18187j;
            this.f18176k = c5627y.f18188k;
            this.f18177l = c5627y.f18189l;
        }

        /* renamed from: a */
        public C5626a m20548a(C5621w c5621w) {
            this.f18166a = c5621w;
            return this;
        }

        /* renamed from: a */
        public C5626a m20545a(Protocol protocol) {
            this.f18167b = protocol;
            return this;
        }

        /* renamed from: a */
        public C5626a m20541a(int i) {
            this.f18168c = i;
            return this;
        }

        /* renamed from: a */
        public C5626a m20543a(String str) {
            this.f18169d = str;
            return this;
        }

        /* renamed from: a */
        public C5626a m20546a(C5604p c5604p) {
            this.f18170e = c5604p;
            return this;
        }

        /* renamed from: a */
        public C5626a m20544a(String str, String str2) {
            this.f18171f.m20404a(str, str2);
            return this;
        }

        /* renamed from: a */
        public C5626a m20547a(C5607q c5607q) {
            this.f18171f = c5607q.m20416b();
            return this;
        }

        /* renamed from: a */
        public C5626a m20550a(C5569z c5569z) {
            this.f18172g = c5569z;
            return this;
        }

        /* renamed from: a */
        public C5626a m20549a(C5627y c5627y) {
            if (c5627y != null) {
                m20528a("networkResponse", c5627y);
            }
            this.f18173h = c5627y;
            return this;
        }

        /* renamed from: b */
        public C5626a m20553b(C5627y c5627y) {
            if (c5627y != null) {
                m20528a("cacheResponse", c5627y);
            }
            this.f18174i = c5627y;
            return this;
        }

        /* renamed from: a */
        private void m20528a(String str, C5627y c5627y) {
            if (c5627y.f18184g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (c5627y.f18185h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (c5627y.f18186i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (c5627y.f18187j != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        /* renamed from: c */
        public C5626a m20554c(C5627y c5627y) {
            if (c5627y != null) {
                m20532d(c5627y);
            }
            this.f18175j = c5627y;
            return this;
        }

        /* renamed from: d */
        private void m20532d(C5627y c5627y) {
            if (c5627y.f18184g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        /* renamed from: a */
        public C5626a m20542a(long j) {
            this.f18176k = j;
            return this;
        }

        /* renamed from: b */
        public C5626a m20552b(long j) {
            this.f18177l = j;
            return this;
        }

        /* renamed from: a */
        public C5627y m20551a() {
            if (this.f18166a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.f18167b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.f18168c >= 0) {
                return new C5627y();
            } else {
                throw new IllegalStateException("code < 0: " + this.f18168c);
            }
        }
    }

    private C5627y(C5626a c5626a) {
        this.f18178a = c5626a.f18166a;
        this.f18179b = c5626a.f18167b;
        this.f18180c = c5626a.f18168c;
        this.f18181d = c5626a.f18169d;
        this.f18182e = c5626a.f18170e;
        this.f18183f = c5626a.f18171f.m20405a();
        this.f18184g = c5626a.f18172g;
        this.f18185h = c5626a.f18173h;
        this.f18186i = c5626a.f18174i;
        this.f18187j = c5626a.f18175j;
        this.f18188k = c5626a.f18176k;
        this.f18189l = c5626a.f18177l;
    }

    /* renamed from: a */
    public C5621w m20569a() {
        return this.f18178a;
    }

    /* renamed from: b */
    public int m20570b() {
        return this.f18180c;
    }

    /* renamed from: c */
    public boolean m20571c() {
        return this.f18180c >= 200 && this.f18180c < 300;
    }

    /* renamed from: d */
    public String m20572d() {
        return this.f18181d;
    }

    /* renamed from: e */
    public C5604p m20573e() {
        return this.f18182e;
    }

    /* renamed from: a */
    public String m20567a(String str) {
        return m20568a(str, null);
    }

    /* renamed from: a */
    public String m20568a(String str, String str2) {
        String a = this.f18183f.m20413a(str);
        return a != null ? a : str2;
    }

    /* renamed from: f */
    public C5607q m20574f() {
        return this.f18183f;
    }

    /* renamed from: g */
    public C5569z m20575g() {
        return this.f18184g;
    }

    /* renamed from: h */
    public C5626a m20576h() {
        return new C5626a();
    }

    /* renamed from: i */
    public C5466d m20577i() {
        C5466d c5466d = this.f18190m;
        if (c5466d != null) {
            return c5466d;
        }
        c5466d = C5466d.m19707a(this.f18183f);
        this.f18190m = c5466d;
        return c5466d;
    }

    /* renamed from: j */
    public long m20578j() {
        return this.f18188k;
    }

    /* renamed from: k */
    public long m20579k() {
        return this.f18189l;
    }

    public void close() {
        this.f18184g.close();
    }

    public String toString() {
        return "Response{protocol=" + this.f18179b + ", code=" + this.f18180c + ", message=" + this.f18181d + ", url=" + this.f18178a.m20519a() + CoreConstants.CURLY_RIGHT;
    }
}
