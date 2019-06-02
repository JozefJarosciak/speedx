package com.twitter.sdk.android.tweetui.internal;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C1500b;
import com.twitter.sdk.android.core.C4576a;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4609d;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import java.util.List;

/* compiled from: GuestSessionProvider */
/* renamed from: com.twitter.sdk.android.tweetui.internal.b */
public class C4723b extends C4609d {
    /* renamed from: a */
    private final C4655n f16598a;

    /* compiled from: GuestSessionProvider */
    /* renamed from: com.twitter.sdk.android.tweetui.internal.b$a */
    class C4722a extends C4580e<C4576a> {
        /* renamed from: a */
        final /* synthetic */ C4723b f16596a;
        /* renamed from: b */
        private final C4580e<C1469k> f16597b;

        C4722a(C4723b c4723b, C4580e<C1469k> c4580e) {
            this.f16596a = c4723b;
            this.f16597b = c4580e;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<C4576a> c4645j) {
            this.f16597b.mo6128a(new C4645j(c4645j.f16364a, c4645j.f16365b));
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            this.f16597b.mo6127a(twitterException);
        }
    }

    public C4723b(C4655n c4655n, List<C4586l<? extends C1469k>> list) {
        super(list);
        this.f16598a = c4655n;
    }

    /* renamed from: a */
    public C1469k mo6174a() {
        C1469k a = super.mo6174a();
        if (a == null) {
            return null;
        }
        C1500b d = a.d();
        if ((d instanceof TwitterAuthToken) || (d instanceof GuestAuthToken)) {
            return a;
        }
        return null;
    }

    /* renamed from: a */
    public void mo6175a(C4580e<C1469k> c4580e) {
        this.f16598a.m18386a(new C4722a(this, c4580e));
    }
}
