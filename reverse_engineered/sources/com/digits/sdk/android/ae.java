package com.digits.sdk.android;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4600a;
import com.twitter.sdk.android.core.internal.C4609d;

/* compiled from: DigitsAuthRequestQueue */
class ae extends C4600a {
    /* renamed from: a */
    final ag f13140a;

    ae(ag agVar, C4609d c4609d) {
        super(c4609d);
        this.f13140a = agVar;
    }

    /* renamed from: a */
    protected synchronized boolean m13921a(final C4580e<DigitsApiClient> c4580e) {
        return b(new C4580e<C1469k>(this) {
            /* renamed from: b */
            final /* synthetic */ ae f13139b;

            /* renamed from: a */
            public void m13920a(C4645j<C1469k> c4645j) {
                c4580e.a(new C4645j(this.f13139b.f13140a.m13929a((C1469k) c4645j.f16364a), null));
            }

            /* renamed from: a */
            public void m13919a(TwitterException twitterException) {
                c4580e.a(twitterException);
            }
        });
    }
}
