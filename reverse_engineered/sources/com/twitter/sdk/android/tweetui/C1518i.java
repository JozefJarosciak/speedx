package com.twitter.sdk.android.tweetui;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.internal.scribe.C1502c;
import com.twitter.sdk.android.core.internal.scribe.C1506j;
import com.twitter.sdk.android.core.internal.scribe.C4629a;
import com.twitter.sdk.android.tweetui.internal.C4723b;
import com.twitter.sdk.android.tweetui.internal.C4726e;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.concurrency.C1522b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@C1522b(a = {C4655n.class})
/* compiled from: TweetUi */
/* renamed from: com.twitter.sdk.android.tweetui.i */
public class C1518i extends C1468h<Boolean> {
    /* renamed from: a */
    List<C4586l<? extends C1469k>> f7139a;
    /* renamed from: b */
    List<C4586l<? extends C1469k>> f7140b;
    /* renamed from: c */
    C4726e f7141c;
    /* renamed from: d */
    C4723b f7142d;
    /* renamed from: e */
    String f7143e;
    /* renamed from: f */
    C4629a f7144f;
    /* renamed from: l */
    private final AtomicReference<Gson> f7145l = new AtomicReference();
    /* renamed from: m */
    private C4702f f7146m;
    /* renamed from: n */
    private C4728j f7147n;
    /* renamed from: o */
    private C4728j f7148o;
    /* renamed from: p */
    private Picasso f7149p;

    /* renamed from: n */
    protected /* synthetic */ Object mo2813n() {
        return m8327b();
    }

    /* renamed from: a */
    public static C1518i m8323a() {
        C1518i.m8324h();
        return (C1518i) C1520c.m8348a(C1518i.class);
    }

    /* renamed from: g */
    public String mo2812g() {
        return "com.twitter.sdk.android:tweet-ui";
    }

    /* renamed from: c */
    public String mo2810c() {
        return "1.10.1.111";
    }

    /* renamed from: d */
    protected boolean mo2811d() {
        super.mo2811d();
        C4655n a = C4655n.a();
        this.f7139a = new ArrayList(1);
        this.f7139a.add(a.h());
        this.f7141c = new C4726e(this.f7139a);
        this.f7147n = new C4728j(a, this.f7141c);
        this.f7140b = new ArrayList(2);
        this.f7140b.add(a.h());
        this.f7140b.add(a.i());
        this.f7142d = new C4723b(a, this.f7140b);
        this.f7148o = new C4728j(a, this.f7142d);
        this.f7146m = new C4702f(m8080r().m8368f(), this.f7147n, this.f7148o);
        return true;
    }

    /* renamed from: b */
    protected Boolean m8327b() {
        this.f7149p = Picasso.with(m8079q());
        this.f7147n.a(this.f7141c.a());
        this.f7148o.a(this.f7142d.a());
        m8330e();
        m8325i();
        this.f7143e = m8078p().i();
        return Boolean.valueOf(true);
    }

    /* renamed from: h */
    private static void m8324h() {
        if (C1520c.m8348a(C1518i.class) == null) {
            throw new IllegalStateException("Must start TweetUi Kit in Fabric.with().");
        }
    }

    /* renamed from: i */
    private void m8325i() {
        this.f7144f = new C4629a(this, "TweetUi", (Gson) this.f7145l.get(), this.f7140b, m8078p());
    }

    /* renamed from: a */
    void m8326a(C1502c c1502c, List<C1506j> list) {
        if (this.f7144f != null) {
            this.f7144f.a(c1502c, list);
        }
    }

    /* renamed from: e */
    void m8330e() {
        if (this.f7145l.get() == null) {
            this.f7145l.compareAndSet(null, new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create());
        }
    }

    /* renamed from: f */
    C4702f m8331f() {
        return this.f7146m;
    }
}
