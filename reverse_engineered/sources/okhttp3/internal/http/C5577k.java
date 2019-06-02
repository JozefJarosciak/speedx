package okhttp3.internal.http;

import okhttp3.C5569z;
import okhttp3.C5607q;
import okhttp3.C5608s;
import okio.C5636e;

/* compiled from: RealResponseBody */
/* renamed from: okhttp3.internal.http.k */
public final class C5577k extends C5569z {
    /* renamed from: a */
    private final C5607q f17974a;
    /* renamed from: b */
    private final C5636e f17975b;

    public C5577k(C5607q c5607q, C5636e c5636e) {
        this.f17974a = c5607q;
        this.f17975b = c5636e;
    }

    public C5608s contentType() {
        String a = this.f17974a.m20413a("Content-Type");
        return a != null ? C5608s.m20418a(a) : null;
    }

    public long contentLength() {
        return C5576j.m20266a(this.f17974a);
    }

    public C5636e source() {
        return this.f17975b;
    }
}
