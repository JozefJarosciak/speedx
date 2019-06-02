package io.fabric.sdk.android.services.settings;

import android.content.Context;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.common.C4875g;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.common.C4891p;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.network.C4924c;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Settings */
/* renamed from: io.fabric.sdk.android.services.settings.q */
public class C4951q {
    /* renamed from: a */
    private final AtomicReference<C4952s> f17337a;
    /* renamed from: b */
    private final CountDownLatch f17338b;
    /* renamed from: c */
    private C4939r f17339c;
    /* renamed from: d */
    private boolean f17340d;

    /* compiled from: Settings */
    /* renamed from: io.fabric.sdk.android.services.settings.q$a */
    static class C4950a {
        /* renamed from: a */
        private static final C4951q f17336a = new C4951q();
    }

    /* renamed from: a */
    public static C4951q m19411a() {
        return C4950a.f17336a;
    }

    private C4951q() {
        this.f17337a = new AtomicReference();
        this.f17338b = new CountDownLatch(1);
        this.f17340d = false;
    }

    /* renamed from: a */
    public synchronized C4951q m19413a(C1468h c1468h, C4883l c4883l, C4924c c4924c, String str, String str2, String str3) {
        C4951q c4951q;
        if (this.f17340d) {
            c4951q = this;
        } else {
            if (this.f17339c == null) {
                Context q = c1468h.q();
                String b = c4883l.m19185b();
                String a = new C4875g().m19139a(q);
                String g = c4883l.m19190g();
                C4891p c4891p = new C4891p();
                C4942k c4942k = new C4942k();
                C4938i c4938i = new C4938i(c1468h);
                String e = C4877i.m19171e(q);
                C1468h c1468h2 = c1468h;
                String str4 = str3;
                C4944l c4944l = new C4944l(c1468h2, str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{b}), c4924c);
                g = str2;
                String str5 = str;
                this.f17339c = new C4940j(c1468h, new C4954v(a, c4883l.m19188e(), c4883l.m19187d(), c4883l.m19186c(), c4883l.m19192i(), c4883l.m19184a(), c4883l.m19193j(), C4877i.m19152a(C4877i.m19173g(q)), g, str5, DeliveryMechanism.determineFrom(g).getId(), e), c4891p, c4942k, c4938i, c4944l);
            }
            this.f17340d = true;
            c4951q = this;
        }
        return c4951q;
    }

    /* renamed from: b */
    public C4952s m19414b() {
        try {
            this.f17338b.await();
            return (C4952s) this.f17337a.get();
        } catch (InterruptedException e) {
            C1520c.h().mo6221d("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    /* renamed from: c */
    public synchronized boolean m19415c() {
        C4952s a;
        a = this.f17339c.mo6264a();
        m19412a(a);
        return a != null;
    }

    /* renamed from: d */
    public synchronized boolean m19416d() {
        C4952s a;
        a = this.f17339c.mo6265a(SettingsCacheBehavior.SKIP_CACHE_LOOKUP);
        m19412a(a);
        if (a == null) {
            C1520c.h().mo6222d("Fabric", "Failed to force reload of settings from Crashlytics.", null);
        }
        return a != null;
    }

    /* renamed from: a */
    private void m19412a(C4952s c4952s) {
        this.f17337a.set(c4952s);
        this.f17338b.countDown();
    }
}
