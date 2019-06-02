package com.facebook.appevents;

import android.content.Context;
import com.facebook.C1472c;
import com.facebook.internal.C3000b;
import java.util.HashMap;
import java.util.Set;

/* compiled from: AppEventCollection */
/* renamed from: com.facebook.appevents.a */
class C2966a {
    /* renamed from: a */
    private final HashMap<AccessTokenAppIdPair, C2976e> f13447a = new HashMap();

    /* renamed from: a */
    public synchronized void m14425a(PersistedEvents persistedEvents) {
        if (persistedEvents != null) {
            for (AccessTokenAppIdPair accessTokenAppIdPair : persistedEvents.keySet()) {
                C2976e b = m14421b(accessTokenAppIdPair);
                for (AppEvent a : persistedEvents.get(accessTokenAppIdPair)) {
                    b.m14449a(a);
                }
            }
        }
    }

    /* renamed from: a */
    public synchronized void m14424a(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        m14421b(accessTokenAppIdPair).m14449a(appEvent);
    }

    /* renamed from: a */
    public synchronized Set<AccessTokenAppIdPair> m14423a() {
        return this.f13447a.keySet();
    }

    /* renamed from: a */
    public synchronized C2976e m14422a(AccessTokenAppIdPair accessTokenAppIdPair) {
        return (C2976e) this.f13447a.get(accessTokenAppIdPair);
    }

    /* renamed from: b */
    public synchronized int m14426b() {
        int i;
        i = 0;
        for (C2976e a : this.f13447a.values()) {
            i = a.m14447a() + i;
        }
        return i;
    }

    /* renamed from: b */
    private synchronized C2976e m14421b(AccessTokenAppIdPair accessTokenAppIdPair) {
        C2976e c2976e;
        c2976e = (C2976e) this.f13447a.get(accessTokenAppIdPair);
        if (c2976e == null) {
            Context f = C1472c.f();
            c2976e = new C2976e(C3000b.m14541a(f), AppEventsLogger.m14412b(f));
        }
        this.f13447a.put(accessTokenAppIdPair, c2976e);
        return c2976e;
    }
}
