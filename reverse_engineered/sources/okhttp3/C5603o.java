package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.C5586l;
import okio.C5635d;
import okio.C5637c;

/* compiled from: FormBody */
/* renamed from: okhttp3.o */
public final class C5603o extends C5602x {
    /* renamed from: a */
    private static final C5608s f18050a = C5608s.m20418a("application/x-www-form-urlencoded");
    /* renamed from: b */
    private final List<String> f18051b;
    /* renamed from: c */
    private final List<String> f18052c;

    /* compiled from: FormBody */
    /* renamed from: okhttp3.o$a */
    public static final class C5601a {
        /* renamed from: a */
        private final List<String> f18048a = new ArrayList();
        /* renamed from: b */
        private final List<String> f18049b = new ArrayList();

        /* renamed from: a */
        public C5601a m20394a(String str, String str2) {
            this.f18048a.add(HttpUrl.m19656a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            this.f18049b.add(HttpUrl.m19656a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            return this;
        }

        /* renamed from: b */
        public C5601a m20396b(String str, String str2) {
            this.f18048a.add(HttpUrl.m19656a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            this.f18049b.add(HttpUrl.m19656a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            return this;
        }

        /* renamed from: a */
        public C5603o m20395a() {
            return new C5603o(this.f18048a, this.f18049b);
        }
    }

    private C5603o(List<String> list, List<String> list2) {
        this.f18051b = C5586l.m20321a((List) list);
        this.f18052c = C5586l.m20321a((List) list2);
    }

    public C5608s contentType() {
        return f18050a;
    }

    public long contentLength() {
        return m20397a(null, true);
    }

    public void writeTo(C5635d c5635d) throws IOException {
        m20397a(c5635d, false);
    }

    /* renamed from: a */
    private long m20397a(C5635d c5635d, boolean z) {
        C5637c c5637c;
        long j = 0;
        if (z) {
            c5637c = new C5637c();
        } else {
            c5637c = c5635d.mo6810b();
        }
        int size = this.f18051b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                c5637c.m20641b(38);
            }
            c5637c.m20631a((String) this.f18051b.get(i));
            c5637c.m20641b(61);
            c5637c.m20631a((String) this.f18052c.get(i));
        }
        if (z) {
            j = c5637c.m20623a();
            c5637c.m20685t();
        }
        return j;
    }
}
