package com.mapzen.p091a.p092a.p187b;

import android.content.Context;
import android.location.Location;
import com.mapzen.p091a.p092a.p093a.C4209d;

/* compiled from: LocationEngine */
/* renamed from: com.mapzen.a.a.b.e */
public abstract class C4216e {
    /* renamed from: a */
    private final Context f14856a;
    /* renamed from: b */
    private final C4214a f14857b;
    /* renamed from: c */
    private C4209d f14858c;

    /* compiled from: LocationEngine */
    /* renamed from: com.mapzen.a.a.b.e$a */
    public interface C4214a {
        /* renamed from: a */
        void mo5987a(C4216e c4216e, Location location);
    }

    /* renamed from: a */
    public abstract Location mo5988a();

    /* renamed from: b */
    protected abstract void mo5989b();

    /* renamed from: c */
    protected abstract void mo5990c();

    public C4216e(Context context, C4214a c4214a) {
        this.f14856a = context;
        this.f14857b = c4214a;
    }

    /* renamed from: a */
    public void m16734a(C4209d c4209d) {
        this.f14858c = c4209d;
        if (c4209d != null) {
            mo5989b();
        } else {
            mo5990c();
        }
    }

    /* renamed from: d */
    protected Context m16737d() {
        return this.f14856a;
    }

    /* renamed from: e */
    protected C4214a m16738e() {
        return this.f14857b;
    }

    /* renamed from: f */
    protected C4209d m16739f() {
        return this.f14858c;
    }
}
