package com.digits.sdk.android;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4609d;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import java.util.List;

/* compiled from: DigitsGuestSessionProvider */
class ak extends C4609d {
    /* renamed from: a */
    final C4586l<ap> f13189a;
    /* renamed from: b */
    final OAuth2Service f13190b;

    /* compiled from: DigitsGuestSessionProvider */
    /* renamed from: com.digits.sdk.android.ak$a */
    static class C2894a extends C4580e<OAuth2Token> {
        /* renamed from: a */
        final C4586l<ap> f13187a;
        /* renamed from: b */
        final C4580e<C1469k> f13188b;

        C2894a(C4586l<ap> c4586l, C4580e<C1469k> c4580e) {
            this.f13187a = c4586l;
            this.f13188b = c4580e;
        }

        /* renamed from: a */
        public void m13970a(C4645j<OAuth2Token> c4645j) {
            C1469k apVar = new ap((OAuth2Token) c4645j.f16364a);
            this.f13187a.a(apVar.e(), apVar);
            if (this.f13188b != null) {
                this.f13188b.a(new C4645j(apVar, c4645j.f16365b));
            }
        }

        /* renamed from: a */
        public void m13969a(TwitterException twitterException) {
            if (this.f13188b != null) {
                this.f13188b.a(twitterException);
            }
        }
    }

    ak(C4586l<ap> c4586l, List<C4586l<? extends C1469k>> list) {
        this(c4586l, list, new OAuth2Service(C4655n.a(), C4655n.a().e(), new ad()));
    }

    ak(C4586l<ap> c4586l, List<C4586l<? extends C1469k>> list, OAuth2Service oAuth2Service) {
        super(list);
        this.f13189a = c4586l;
        this.f13190b = oAuth2Service;
    }

    /* renamed from: a */
    public void m13971a(C4580e<C1469k> c4580e) {
        this.f13190b.a(new C2894a(this.f13189a, c4580e));
    }
}
