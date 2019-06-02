package okhttp3;

import com.avos.avoscloud.AVStatus;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.http.C5555c;

/* compiled from: CacheControl */
/* renamed from: okhttp3.d */
public final class C5466d {
    /* renamed from: a */
    public static final C5466d f17605a = new C5465a().m19703a().m19706c();
    /* renamed from: b */
    public static final C5466d f17606b = new C5465a().m19705b().m19704a(Integer.MAX_VALUE, TimeUnit.SECONDS).m19706c();
    /* renamed from: c */
    String f17607c;
    /* renamed from: d */
    private final boolean f17608d;
    /* renamed from: e */
    private final boolean f17609e;
    /* renamed from: f */
    private final int f17610f;
    /* renamed from: g */
    private final int f17611g;
    /* renamed from: h */
    private final boolean f17612h;
    /* renamed from: i */
    private final boolean f17613i;
    /* renamed from: j */
    private final boolean f17614j;
    /* renamed from: k */
    private final int f17615k;
    /* renamed from: l */
    private final int f17616l;
    /* renamed from: m */
    private final boolean f17617m;
    /* renamed from: n */
    private final boolean f17618n;

    /* compiled from: CacheControl */
    /* renamed from: okhttp3.d$a */
    public static final class C5465a {
        /* renamed from: a */
        boolean f17598a;
        /* renamed from: b */
        boolean f17599b;
        /* renamed from: c */
        int f17600c = -1;
        /* renamed from: d */
        int f17601d = -1;
        /* renamed from: e */
        int f17602e = -1;
        /* renamed from: f */
        boolean f17603f;
        /* renamed from: g */
        boolean f17604g;

        /* renamed from: a */
        public C5465a m19703a() {
            this.f17598a = true;
            return this;
        }

        /* renamed from: a */
        public C5465a m19704a(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxStale < 0: " + i);
            }
            long toSeconds = timeUnit.toSeconds((long) i);
            this.f17601d = toSeconds > 2147483647L ? Integer.MAX_VALUE : (int) toSeconds;
            return this;
        }

        /* renamed from: b */
        public C5465a m19705b() {
            this.f17603f = true;
            return this;
        }

        /* renamed from: c */
        public C5466d m19706c() {
            return new C5466d();
        }
    }

    private C5466d(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.f17608d = z;
        this.f17609e = z2;
        this.f17610f = i;
        this.f17611g = i2;
        this.f17612h = z3;
        this.f17613i = z4;
        this.f17614j = z5;
        this.f17615k = i3;
        this.f17616l = i4;
        this.f17617m = z6;
        this.f17618n = z7;
        this.f17607c = str;
    }

    private C5466d(C5465a c5465a) {
        this.f17608d = c5465a.f17598a;
        this.f17609e = c5465a.f17599b;
        this.f17610f = c5465a.f17600c;
        this.f17611g = -1;
        this.f17612h = false;
        this.f17613i = false;
        this.f17614j = false;
        this.f17615k = c5465a.f17601d;
        this.f17616l = c5465a.f17602e;
        this.f17617m = c5465a.f17603f;
        this.f17618n = c5465a.f17604g;
    }

    /* renamed from: a */
    public boolean m19709a() {
        return this.f17608d;
    }

    /* renamed from: b */
    public boolean m19710b() {
        return this.f17609e;
    }

    /* renamed from: c */
    public int m19711c() {
        return this.f17610f;
    }

    /* renamed from: d */
    public boolean m19712d() {
        return this.f17612h;
    }

    /* renamed from: e */
    public boolean m19713e() {
        return this.f17613i;
    }

    /* renamed from: f */
    public boolean m19714f() {
        return this.f17614j;
    }

    /* renamed from: g */
    public int m19715g() {
        return this.f17615k;
    }

    /* renamed from: h */
    public int m19716h() {
        return this.f17616l;
    }

    /* renamed from: i */
    public boolean m19717i() {
        return this.f17617m;
    }

    /* renamed from: a */
    public static C5466d m19707a(C5607q c5607q) {
        String b;
        boolean z = false;
        int i = -1;
        int i2 = -1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z5 = false;
        boolean z6 = false;
        Object obj = 1;
        int a = c5607q.m20411a();
        int i5 = 0;
        String str = null;
        boolean z7 = false;
        while (i5 < a) {
            boolean z8;
            String a2 = c5607q.m20412a(i5);
            b = c5607q.m20414b(i5);
            if (a2.equalsIgnoreCase("Cache-Control")) {
                if (str != null) {
                    obj = null;
                } else {
                    str = b;
                }
            } else if (a2.equalsIgnoreCase("Pragma")) {
                obj = null;
            } else {
                z8 = z7;
                i5++;
                z7 = z8;
            }
            z8 = z7;
            int i6 = 0;
            while (i6 < b.length()) {
                String str2;
                int a3 = C5555c.m20171a(b, i6, "=,;");
                String trim = b.substring(i6, a3).trim();
                if (a3 == b.length() || b.charAt(a3) == ',' || b.charAt(a3) == ';') {
                    i6 = a3 + 1;
                    str2 = null;
                } else {
                    i6 = C5555c.m20170a(b, a3 + 1);
                    String trim2;
                    if (i6 >= b.length() || b.charAt(i6) != '\"') {
                        a3 = C5555c.m20171a(b, i6, ",;");
                        trim2 = b.substring(i6, a3).trim();
                        i6 = a3;
                        str2 = trim2;
                    } else {
                        i6++;
                        a3 = C5555c.m20171a(b, i6, "\"");
                        trim2 = b.substring(i6, a3);
                        i6 = a3 + 1;
                        str2 = trim2;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    z8 = true;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    z = true;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i = C5555c.m20172b(str2, -1);
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i2 = C5555c.m20172b(str2, -1);
                } else if (AVStatus.INBOX_PRIVATE.equalsIgnoreCase(trim)) {
                    z2 = true;
                } else if ("public".equalsIgnoreCase(trim)) {
                    z3 = true;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    z4 = true;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i3 = C5555c.m20172b(str2, Integer.MAX_VALUE);
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i4 = C5555c.m20172b(str2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z5 = true;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z6 = true;
                }
            }
            i5++;
            z7 = z8;
        }
        if (obj == null) {
            b = null;
        } else {
            b = str;
        }
        return new C5466d(z7, z, i, i2, z2, z3, z4, i3, i4, z5, z6, b);
    }

    public String toString() {
        String str = this.f17607c;
        if (str != null) {
            return str;
        }
        str = m19708j();
        this.f17607c = str;
        return str;
    }

    /* renamed from: j */
    private String m19708j() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f17608d) {
            stringBuilder.append("no-cache, ");
        }
        if (this.f17609e) {
            stringBuilder.append("no-store, ");
        }
        if (this.f17610f != -1) {
            stringBuilder.append("max-age=").append(this.f17610f).append(", ");
        }
        if (this.f17611g != -1) {
            stringBuilder.append("s-maxage=").append(this.f17611g).append(", ");
        }
        if (this.f17612h) {
            stringBuilder.append("private, ");
        }
        if (this.f17613i) {
            stringBuilder.append("public, ");
        }
        if (this.f17614j) {
            stringBuilder.append("must-revalidate, ");
        }
        if (this.f17615k != -1) {
            stringBuilder.append("max-stale=").append(this.f17615k).append(", ");
        }
        if (this.f17616l != -1) {
            stringBuilder.append("min-fresh=").append(this.f17616l).append(", ");
        }
        if (this.f17617m) {
            stringBuilder.append("only-if-cached, ");
        }
        if (this.f17618n) {
            stringBuilder.append("no-transform, ");
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }
}
