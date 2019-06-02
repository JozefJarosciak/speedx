package okhttp3.internal;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.aa;

/* compiled from: RouteDatabase */
/* renamed from: okhttp3.internal.k */
public final class C5584k {
    /* renamed from: a */
    private final Set<aa> f18003a = new LinkedHashSet();

    /* renamed from: a */
    public synchronized void m20312a(aa aaVar) {
        this.f18003a.add(aaVar);
    }

    /* renamed from: b */
    public synchronized void m20313b(aa aaVar) {
        this.f18003a.remove(aaVar);
    }

    /* renamed from: c */
    public synchronized boolean m20314c(aa aaVar) {
        return this.f18003a.contains(aaVar);
    }
}
