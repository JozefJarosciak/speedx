package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.internal.scribe.C1502c;
import com.twitter.sdk.android.core.internal.scribe.C1506j;
import com.twitter.sdk.android.core.internal.scribe.c$a;
import com.twitter.sdk.android.core.models.C1513g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TweetScribeClientImpl */
/* renamed from: com.twitter.sdk.android.tweetui.h */
class C4704h implements C4703g {
    /* renamed from: a */
    final C1518i f16535a;

    C4704h(C1518i c1518i) {
        this.f16535a = c1518i;
    }

    /* renamed from: a */
    public void mo6163a(C1513g c1513g) {
        List arrayList = new ArrayList();
        arrayList.add(C1506j.a(c1513g));
        this.f16535a.a(C4704h.m18520c(), arrayList);
    }

    /* renamed from: b */
    public void mo6164b(C1513g c1513g) {
        List arrayList = new ArrayList();
        arrayList.add(C1506j.a(c1513g));
        this.f16535a.a(C4704h.m18519b(), arrayList);
    }

    /* renamed from: c */
    public void mo6165c(C1513g c1513g) {
        List arrayList = new ArrayList();
        arrayList.add(C1506j.a(c1513g));
        this.f16535a.a(C4704h.m18518a(), arrayList);
    }

    /* renamed from: a */
    static C1502c m18518a() {
        return new c$a().m18339a("tfw").m18341b("android").m18342c("tweet").m18344e("actions").m18345f("unfavorite").m18340a();
    }

    /* renamed from: b */
    static C1502c m18519b() {
        return new c$a().m18339a("tfw").m18341b("android").m18342c("tweet").m18344e("actions").m18345f("favorite").m18340a();
    }

    /* renamed from: c */
    static C1502c m18520c() {
        return new c$a().m18339a("tfw").m18341b("android").m18342c("tweet").m18344e("actions").m18345f("share").m18340a();
    }
}
