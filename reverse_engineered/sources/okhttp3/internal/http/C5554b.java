package okhttp3.internal.http;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.C5466d;
import okhttp3.C5607q;
import okhttp3.C5621w;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import okhttp3.C5627y.C5626a;
import org.apache.http.HttpStatus;

/* compiled from: CacheStrategy */
/* renamed from: okhttp3.internal.http.b */
public final class C5554b {
    /* renamed from: a */
    public final C5621w f17896a;
    /* renamed from: b */
    public final C5627y f17897b;

    /* compiled from: CacheStrategy */
    /* renamed from: okhttp3.internal.http.b$a */
    public static class C5553a {
        /* renamed from: a */
        final long f17884a;
        /* renamed from: b */
        final C5621w f17885b;
        /* renamed from: c */
        final C5627y f17886c;
        /* renamed from: d */
        private Date f17887d;
        /* renamed from: e */
        private String f17888e;
        /* renamed from: f */
        private Date f17889f;
        /* renamed from: g */
        private String f17890g;
        /* renamed from: h */
        private Date f17891h;
        /* renamed from: i */
        private long f17892i;
        /* renamed from: j */
        private long f17893j;
        /* renamed from: k */
        private String f17894k;
        /* renamed from: l */
        private int f17895l = -1;

        public C5553a(long j, C5621w c5621w, C5627y c5627y) {
            this.f17884a = j;
            this.f17885b = c5621w;
            this.f17886c = c5627y;
            if (c5627y != null) {
                this.f17892i = c5627y.m20578j();
                this.f17893j = c5627y.m20579k();
                C5607q f = c5627y.m20574f();
                int a = f.m20411a();
                for (int i = 0; i < a; i++) {
                    String a2 = f.m20412a(i);
                    String b = f.m20414b(i);
                    if ("Date".equalsIgnoreCase(a2)) {
                        this.f17887d = C5568f.m20225a(b);
                        this.f17888e = b;
                    } else if ("Expires".equalsIgnoreCase(a2)) {
                        this.f17891h = C5568f.m20225a(b);
                    } else if ("Last-Modified".equalsIgnoreCase(a2)) {
                        this.f17889f = C5568f.m20225a(b);
                        this.f17890g = b;
                    } else if ("ETag".equalsIgnoreCase(a2)) {
                        this.f17894k = b;
                    } else if ("Age".equalsIgnoreCase(a2)) {
                        this.f17895l = C5555c.m20172b(b, -1);
                    }
                }
            }
        }

        /* renamed from: a */
        public C5554b m20168a() {
            C5554b b = m20164b();
            if (b.f17896a == null || !this.f17885b.m20525g().m19717i()) {
                return b;
            }
            return new C5554b(null, null);
        }

        /* renamed from: b */
        private C5554b m20164b() {
            long j = 0;
            if (this.f17886c == null) {
                return new C5554b(this.f17885b, null);
            }
            if (this.f17885b.m20526h() && this.f17886c.m20573e() == null) {
                return new C5554b(this.f17885b, null);
            }
            if (!C5554b.m20169a(this.f17886c, this.f17885b)) {
                return new C5554b(this.f17885b, null);
            }
            C5466d g = this.f17885b.m20525g();
            if (g.m19709a() || C5553a.m20163a(this.f17885b)) {
                return new C5554b(this.f17885b, null);
            }
            long toMillis;
            long d = m20166d();
            long c = m20165c();
            if (g.m19711c() != -1) {
                c = Math.min(c, TimeUnit.SECONDS.toMillis((long) g.m19711c()));
            }
            if (g.m19716h() != -1) {
                toMillis = TimeUnit.SECONDS.toMillis((long) g.m19716h());
            } else {
                toMillis = 0;
            }
            C5466d i = this.f17886c.m20577i();
            if (!(i.m19714f() || g.m19715g() == -1)) {
                j = TimeUnit.SECONDS.toMillis((long) g.m19715g());
            }
            if (i.m19709a() || d + toMillis >= r4 + c) {
                C5620a f = this.f17885b.m20524f();
                if (this.f17894k != null) {
                    f.m20505a("If-None-Match", this.f17894k);
                } else if (this.f17889f != null) {
                    f.m20505a("If-Modified-Since", this.f17890g);
                } else if (this.f17887d != null) {
                    f.m20505a("If-Modified-Since", this.f17888e);
                }
                C5621w a = f.m20510a();
                if (C5553a.m20163a(a)) {
                    return new C5554b(a, this.f17886c);
                }
                return new C5554b(a, null);
            }
            C5626a h = this.f17886c.m20576h();
            if (toMillis + d >= c) {
                h.m20544a("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if (d > 86400000 && m20167e()) {
                h.m20544a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new C5554b(null, h.m20551a());
        }

        /* renamed from: c */
        private long m20165c() {
            C5466d i = this.f17886c.m20577i();
            if (i.m19711c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) i.m19711c());
            }
            long time;
            if (this.f17891h != null) {
                time = this.f17891h.getTime() - (this.f17887d != null ? this.f17887d.getTime() : this.f17893j);
                if (time <= 0) {
                    time = 0;
                }
                return time;
            } else if (this.f17889f == null || this.f17886c.m20569a().m20519a().m19682l() != null) {
                return 0;
            } else {
                time = (this.f17887d != null ? this.f17887d.getTime() : this.f17892i) - this.f17889f.getTime();
                if (time > 0) {
                    return time / 10;
                }
                return 0;
            }
        }

        /* renamed from: d */
        private long m20166d() {
            long j = 0;
            if (this.f17887d != null) {
                j = Math.max(0, this.f17893j - this.f17887d.getTime());
            }
            if (this.f17895l != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.f17895l));
            }
            return (j + (this.f17893j - this.f17892i)) + (this.f17884a - this.f17893j);
        }

        /* renamed from: e */
        private boolean m20167e() {
            return this.f17886c.m20577i().m19711c() == -1 && this.f17891h == null;
        }

        /* renamed from: a */
        private static boolean m20163a(C5621w c5621w) {
            return (c5621w.m20518a("If-Modified-Since") == null && c5621w.m20518a("If-None-Match") == null) ? false : true;
        }
    }

    private C5554b(C5621w c5621w, C5627y c5627y) {
        this.f17896a = c5621w;
        this.f17897b = c5627y;
    }

    /* renamed from: a */
    public static boolean m20169a(C5627y c5627y, C5621w c5621w) {
        switch (c5627y.m20570b()) {
            case 200:
            case 203:
            case 204:
            case 300:
            case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
            case 308:
            case 404:
            case HttpStatus.SC_METHOD_NOT_ALLOWED /*405*/:
            case HttpStatus.SC_GONE /*410*/:
            case HttpStatus.SC_REQUEST_URI_TOO_LONG /*414*/:
            case 501:
                break;
            case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
            case HttpStatus.SC_TEMPORARY_REDIRECT /*307*/:
                if (c5627y.m20567a("Expires") == null && c5627y.m20577i().m19711c() == -1 && !c5627y.m20577i().m19713e() && !c5627y.m20577i().m19712d()) {
                    return false;
                }
            default:
                return false;
        }
        return (c5627y.m20577i().m19710b() || c5621w.m20525g().m19710b()) ? false : true;
    }
}
