package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.C4578f;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.internal.C4611f;
import javax.net.ssl.SSLSocketFactory;
import retrofit.RequestInterceptor;
import retrofit.RequestInterceptor.RequestFacade;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;

/* compiled from: OAuthService */
/* renamed from: com.twitter.sdk.android.core.internal.oauth.e */
abstract class C4616e {
    /* renamed from: a */
    private final C4655n f16273a;
    /* renamed from: b */
    private final C4611f f16274b;
    /* renamed from: c */
    private final String f16275c;
    /* renamed from: d */
    private final RestAdapter f16276d;

    /* compiled from: OAuthService */
    /* renamed from: com.twitter.sdk.android.core.internal.oauth.e$1 */
    class C46231 implements RequestInterceptor {
        /* renamed from: a */
        final /* synthetic */ C4616e f16293a;

        C46231(C4616e c4616e) {
            this.f16293a = c4616e;
        }

        public void intercept(RequestFacade requestFacade) {
            requestFacade.addHeader("User-Agent", this.f16293a.m18262e());
        }
    }

    public C4616e(C4655n c4655n, SSLSocketFactory sSLSocketFactory, C4611f c4611f) {
        this.f16273a = c4655n;
        this.f16274b = c4611f;
        this.f16275c = C4611f.m18247a("TwitterAndroidSDK", c4655n.m18388c());
        if (sSLSocketFactory == null) {
            throw new IllegalArgumentException("sslSocketFactory must not be null");
        }
        this.f16276d = new Builder().setEndpoint(m18261d().m18249a()).setClient(new C4578f(sSLSocketFactory)).setRequestInterceptor(new C46231(this)).build();
    }

    /* renamed from: c */
    protected C4655n m18260c() {
        return this.f16273a;
    }

    /* renamed from: d */
    protected C4611f m18261d() {
        return this.f16274b;
    }

    /* renamed from: e */
    protected String m18262e() {
        return this.f16275c;
    }

    /* renamed from: f */
    protected RestAdapter m18263f() {
        return this.f16276d;
    }
}
