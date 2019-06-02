package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4611f;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.network.C4929h;
import io.fabric.sdk.android.services.network.HttpRequest.C4918a;
import javax.net.ssl.SSLSocketFactory;

public class OAuth2Service extends C4616e {
    /* renamed from: a */
    OAuth2Service$OAuth2Api f16282a = ((OAuth2Service$OAuth2Api) m18263f().create(OAuth2Service$OAuth2Api.class));

    public OAuth2Service(C4655n c4655n, SSLSocketFactory sSLSocketFactory, C4611f c4611f) {
        super(c4655n, sSLSocketFactory, c4611f);
    }

    /* renamed from: a */
    public void m18278a(final C4580e<OAuth2Token> c4580e) {
        m18280b(new C4580e<AppAuthToken>(this) {
            /* renamed from: b */
            final /* synthetic */ OAuth2Service f16281b;

            /* renamed from: a */
            public void mo6128a(C4645j<AppAuthToken> c4645j) {
                final OAuth2Token oAuth2Token = (OAuth2Token) c4645j.f16364a;
                this.f16281b.m18279a(new C4580e<C1501b>(this) {
                    /* renamed from: b */
                    final /* synthetic */ C46181 f16279b;

                    /* renamed from: a */
                    public void mo6128a(C4645j<C1501b> c4645j) {
                        c4580e.mo6128a(new C4645j(new GuestAuthToken(oAuth2Token.c(), oAuth2Token.d(), ((C1501b) c4645j.f16364a).f7056a), null));
                    }

                    /* renamed from: a */
                    public void mo6127a(TwitterException twitterException) {
                        C1520c.h().mo6222d("Twitter", "Your app may not allow guest auth. Please talk to us regarding upgrading your consumer key.", twitterException);
                        c4580e.mo6127a(twitterException);
                    }
                }, oAuth2Token);
            }

            /* renamed from: a */
            public void mo6127a(TwitterException twitterException) {
                C1520c.h().mo6222d("Twitter", "Failed to get app auth token", twitterException);
                if (c4580e != null) {
                    c4580e.mo6127a(twitterException);
                }
            }
        });
    }

    /* renamed from: b */
    public void m18280b(C4580e<AppAuthToken> c4580e) {
        this.f16282a.getAppAuthToken(m18276a(), "client_credentials", c4580e);
    }

    /* renamed from: a */
    public void m18279a(C4580e<C1501b> c4580e, OAuth2Token oAuth2Token) {
        this.f16282a.getGuestToken(m18277a(oAuth2Token), "", c4580e);
    }

    /* renamed from: a */
    public static String m18277a(OAuth2Token oAuth2Token) {
        return "Bearer " + oAuth2Token.d();
    }

    /* renamed from: a */
    private String m18276a() {
        TwitterAuthConfig b = m18260c().m18387b();
        return "Basic " + C4918a.m19288a(C4929h.m19370c(b.a()) + ":" + C4929h.m19370c(b.b()));
    }
}
