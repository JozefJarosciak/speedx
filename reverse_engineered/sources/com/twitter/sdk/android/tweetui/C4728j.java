package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4589m;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4600a;
import com.twitter.sdk.android.core.internal.C4609d;

/* compiled from: TweetUiAuthRequestQueue */
/* renamed from: com.twitter.sdk.android.tweetui.j */
class C4728j extends C4600a {
    /* renamed from: a */
    private final C4655n f16601a;

    C4728j(C4655n c4655n, C4609d c4609d) {
        super(c4609d);
        this.f16601a = c4655n;
    }

    /* renamed from: a */
    protected synchronized boolean m18607a(final C4580e<C4589m> c4580e) {
        return m18225b(new C4580e<C1469k>(this) {
            /* renamed from: b */
            final /* synthetic */ C4728j f16600b;

            /* renamed from: a */
            public void mo6128a(C4645j<C1469k> c4645j) {
                c4580e.mo6128a(new C4645j(this.f16600b.f16601a.m18385a((C1469k) c4645j.f16364a), null));
            }

            /* renamed from: a */
            public void mo6127a(TwitterException twitterException) {
                c4580e.mo6127a(twitterException);
            }
        });
    }
}
