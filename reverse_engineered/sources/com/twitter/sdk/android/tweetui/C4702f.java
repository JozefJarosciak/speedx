package com.twitter.sdk.android.tweetui;

import android.os.Handler;
import android.support.v4.util.LruCache;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4589m;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.models.C1513g;
import io.fabric.sdk.android.C1520c;

/* compiled from: TweetRepository */
/* renamed from: com.twitter.sdk.android.tweetui.f */
class C4702f {
    /* renamed from: a */
    final LruCache<Long, C1513g> f16530a = new LruCache(20);
    /* renamed from: b */
    final LruCache<Long, Object> f16531b = new LruCache(20);
    /* renamed from: c */
    private final Handler f16532c;
    /* renamed from: d */
    private final C4728j f16533d;
    /* renamed from: e */
    private final C4728j f16534e;

    C4702f(Handler handler, C4728j c4728j, C4728j c4728j2) {
        this.f16532c = handler;
        this.f16534e = c4728j;
        this.f16533d = c4728j2;
    }

    /* renamed from: a */
    void m18513a(long j, C4580e<C1513g> c4580e) {
        final long j2 = j;
        final C4580e<C1513g> c4580e2 = c4580e;
        this.f16534e.m18607a(new C4694c<C4589m>(this, c4580e, C1520c.h()) {
            /* renamed from: c */
            final /* synthetic */ C4702f f16526c;

            /* renamed from: a */
            public void mo6128a(C4645j<C4589m> c4645j) {
                ((C4589m) c4645j.f16364a).m18183b().create(Long.valueOf(j2), Boolean.valueOf(true), c4580e2);
            }
        });
    }

    /* renamed from: b */
    void m18514b(long j, C4580e<C1513g> c4580e) {
        final long j2 = j;
        final C4580e<C1513g> c4580e2 = c4580e;
        this.f16534e.m18607a(new C4694c<C4589m>(this, c4580e, C1520c.h()) {
            /* renamed from: c */
            final /* synthetic */ C4702f f16529c;

            /* renamed from: a */
            public void mo6128a(C4645j<C4589m> c4645j) {
                ((C4589m) c4645j.f16364a).m18183b().destroy(Long.valueOf(j2), Boolean.valueOf(true), c4580e2);
            }
        });
    }
}
