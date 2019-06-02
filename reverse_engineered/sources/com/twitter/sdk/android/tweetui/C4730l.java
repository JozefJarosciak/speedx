package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.internal.scribe.C1502c;
import com.twitter.sdk.android.core.internal.scribe.C1506j;
import com.twitter.sdk.android.core.internal.scribe.C1506j.C1505c;
import com.twitter.sdk.android.core.internal.scribe.c$a;
import com.twitter.sdk.android.core.internal.scribe.j$a;
import com.twitter.sdk.android.core.models.MediaEntity;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoScribeClientImpl */
/* renamed from: com.twitter.sdk.android.tweetui.l */
class C4730l implements C4729k {
    /* renamed from: a */
    final C1518i f16602a;

    C4730l(C1518i c1518i) {
        this.f16602a = c1518i;
    }

    /* renamed from: a */
    public void mo6176a(long j, MediaEntity mediaEntity) {
        List arrayList = new ArrayList();
        arrayList.add(C4730l.m18611b(j, mediaEntity));
        this.f16602a.a(C4730l.m18610a(), arrayList);
    }

    /* renamed from: b */
    static C1506j m18611b(long j, MediaEntity mediaEntity) {
        return new j$a().m18368a(0).m18369a(j).m18371a(C4730l.m18612c(j, mediaEntity)).m18372a();
    }

    /* renamed from: c */
    static C1505c m18612c(long j, MediaEntity mediaEntity) {
        return new C1505c(j, C4730l.m18609a(mediaEntity), mediaEntity.id);
    }

    /* renamed from: a */
    static int m18609a(MediaEntity mediaEntity) {
        if ("animated_gif".equals(mediaEntity.type)) {
            return 3;
        }
        return 1;
    }

    /* renamed from: a */
    static C1502c m18610a() {
        return new c$a().m18339a("tfw").m18341b("android").m18342c("video").m18345f("play").m18340a();
    }
}
