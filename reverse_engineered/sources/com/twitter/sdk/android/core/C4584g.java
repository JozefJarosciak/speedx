package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;

/* compiled from: GuestAuthClient */
/* renamed from: com.twitter.sdk.android.core.g */
class C4584g {
    /* renamed from: a */
    private final OAuth2Service f16214a;

    /* compiled from: GuestAuthClient */
    /* renamed from: com.twitter.sdk.android.core.g$a */
    class C4583a extends C4580e<OAuth2Token> {
        /* renamed from: a */
        final /* synthetic */ C4584g f16211a;
        /* renamed from: b */
        private final C4586l<C4576a> f16212b;
        /* renamed from: c */
        private final C4580e<C4576a> f16213c;

        C4583a(C4584g c4584g, C4586l<C4576a> c4586l, C4580e<C4576a> c4580e) {
            this.f16211a = c4584g;
            this.f16212b = c4586l;
            this.f16213c = c4580e;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<OAuth2Token> c4645j) {
            C1469k c4576a = new C4576a((OAuth2Token) c4645j.f16364a);
            this.f16212b.mo6130a(c4576a.e(), c4576a);
            if (this.f16213c != null) {
                this.f16213c.mo6128a(new C4645j(c4576a, c4645j.f16365b));
            }
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            if (this.f16213c != null) {
                this.f16213c.mo6127a(twitterException);
            }
        }
    }

    C4584g(OAuth2Service oAuth2Service) {
        if (oAuth2Service == null) {
            throw new IllegalArgumentException("OAuth2Service must not be null");
        }
        this.f16214a = oAuth2Service;
    }

    /* renamed from: a */
    void m18155a(C4586l<C4576a> c4586l, C4580e<C4576a> c4580e) {
        if (c4586l == null) {
            throw new IllegalArgumentException("SessionManager must not be null");
        }
        this.f16214a.m18278a(new C4583a(this, c4586l, c4580e));
    }
}
