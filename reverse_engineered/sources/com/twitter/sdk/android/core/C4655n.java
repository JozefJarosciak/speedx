package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.C4576a.C4575a;
import com.twitter.sdk.android.core.internal.C4603b;
import com.twitter.sdk.android.core.internal.C4608c;
import com.twitter.sdk.android.core.internal.C4611f;
import com.twitter.sdk.android.core.internal.C4614h;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.scribe.C4643m;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.network.C4926d;
import io.fabric.sdk.android.services.p094c.C1521c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: TwitterCore */
/* renamed from: com.twitter.sdk.android.core.n */
public class C4655n extends C1468h<Boolean> {
    /* renamed from: a */
    C4586l<C1514q> f16401a;
    /* renamed from: b */
    C4586l<C4576a> f16402b;
    /* renamed from: c */
    C4608c<C1514q> f16403c;
    /* renamed from: d */
    private final TwitterAuthConfig f16404d;
    /* renamed from: e */
    private final ConcurrentHashMap<C1469k, C4589m> f16405e = new ConcurrentHashMap();
    /* renamed from: f */
    private volatile SSLSocketFactory f16406f;

    /* renamed from: n */
    protected /* synthetic */ Object m18395n() {
        return m18391f();
    }

    public C4655n(TwitterAuthConfig twitterAuthConfig) {
        this.f16404d = twitterAuthConfig;
    }

    /* renamed from: a */
    public static C4655n m18381a() {
        C4655n.m18383k();
        return (C4655n) C1520c.a(C4655n.class);
    }

    /* renamed from: c */
    public String m18388c() {
        return "1.6.6.111";
    }

    /* renamed from: b */
    public TwitterAuthConfig m18387b() {
        return this.f16404d;
    }

    /* renamed from: e */
    public SSLSocketFactory m18390e() {
        C4655n.m18383k();
        if (this.f16406f == null) {
            m18382j();
        }
        return this.f16406f;
    }

    /* renamed from: j */
    private synchronized void m18382j() {
        if (this.f16406f == null) {
            try {
                this.f16406f = C4926d.m19356a(new C4657o(q()));
                C1520c.h().mo6215a("Twitter", "Custom SSL pinning enabled");
            } catch (Throwable e) {
                C1520c.h().mo6222d("Twitter", "Exception setting up custom SSL pinning", e);
            }
        }
    }

    /* renamed from: d */
    protected boolean m18389d() {
        new C4603b().m18229a(q(), m18392g(), m18392g() + ":" + "session_store" + ".xml");
        this.f16401a = new C4587i(new C1521c(q(), "session_store"), new q$a(), "active_twittersession", "twittersession");
        this.f16403c = new C4608c(this.f16401a, r().e(), new C4614h());
        this.f16402b = new C4587i(new C1521c(q(), "session_store"), new C4575a(), "active_appsession", "appsession");
        return true;
    }

    /* renamed from: f */
    protected Boolean m18391f() {
        this.f16401a.mo6132b();
        this.f16402b.mo6132b();
        m18390e();
        m18384l();
        this.f16403c.m18242a(r().d());
        return Boolean.valueOf(true);
    }

    /* renamed from: g */
    public String m18392g() {
        return "com.twitter.sdk.android:twitter-core";
    }

    /* renamed from: k */
    private static void m18383k() {
        if (C1520c.a(C4655n.class) == null) {
            throw new IllegalStateException("Must start Twitter Kit with Fabric.with() first");
        }
    }

    /* renamed from: l */
    private void m18384l() {
        List arrayList = new ArrayList();
        arrayList.add(this.f16401a);
        arrayList.add(this.f16402b);
        C4643m.m18374a(this, arrayList, p());
    }

    /* renamed from: a */
    public void m18386a(C4580e<C4576a> c4580e) {
        C4655n.m18383k();
        new C4584g(new OAuth2Service(this, m18390e(), new C4611f())).m18155a(this.f16402b, c4580e);
    }

    /* renamed from: h */
    public C4586l<C1514q> m18393h() {
        C4655n.m18383k();
        return this.f16401a;
    }

    /* renamed from: i */
    public C4586l<C4576a> m18394i() {
        C4655n.m18383k();
        return this.f16402b;
    }

    /* renamed from: a */
    public C4589m m18385a(C1469k c1469k) {
        C4655n.m18383k();
        if (!this.f16405e.containsKey(c1469k)) {
            this.f16405e.putIfAbsent(c1469k, new C4589m(c1469k));
        }
        return (C4589m) this.f16405e.get(c1469k);
    }
}
