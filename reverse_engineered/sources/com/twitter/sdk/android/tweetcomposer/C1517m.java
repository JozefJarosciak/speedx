package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.internal.scribe.C4629a;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.concurrency.C1522b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@C1522b(a = {C4655n.class})
/* compiled from: TweetComposer */
/* renamed from: com.twitter.sdk.android.tweetcomposer.m */
public class C1517m extends C1468h<Void> {
    /* renamed from: a */
    String f7135a;
    /* renamed from: b */
    C4586l<C1514q> f7136b;
    /* renamed from: c */
    private final ConcurrentHashMap<C1469k, C4675d> f7137c = new ConcurrentHashMap();
    /* renamed from: d */
    private C4686j f7138d = new C4687k(null);

    /* renamed from: n */
    protected /* synthetic */ Object mo2813n() {
        return m8316a();
    }

    /* renamed from: c */
    public String mo2810c() {
        return "1.0.3.99";
    }

    /* renamed from: d */
    protected boolean mo2811d() {
        this.f7136b = C4655n.a().h();
        return super.mo2811d();
    }

    /* renamed from: a */
    protected Void m8316a() {
        this.f7135a = m8078p().i();
        this.f7136b.b();
        List arrayList = new ArrayList();
        arrayList.add(this.f7136b);
        this.f7138d = new C4687k(new C4629a(this, "TweetComposer", arrayList, m8078p()));
        return null;
    }

    /* renamed from: g */
    public String mo2812g() {
        return "com.twitter.sdk.android:tweet-composer";
    }

    /* renamed from: a */
    public C4675d m8315a(C1514q c1514q) {
        C1517m.m8314h();
        if (!this.f7137c.containsKey(c1514q)) {
            this.f7137c.putIfAbsent(c1514q, new C4675d(c1514q));
        }
        return (C4675d) this.f7137c.get(c1514q);
    }

    /* renamed from: b */
    public static C1517m m8313b() {
        C1517m.m8314h();
        return (C1517m) C1520c.m8348a(C1517m.class);
    }

    /* renamed from: e */
    protected C4686j m8319e() {
        return this.f7138d;
    }

    /* renamed from: h */
    private static void m8314h() {
        if (C1520c.m8348a(C1517m.class) == null) {
            throw new IllegalStateException("Must start Twitter Kit with Fabric.with() first");
        }
    }

    /* renamed from: f */
    String m8320f() {
        return this.f7135a;
    }
}
