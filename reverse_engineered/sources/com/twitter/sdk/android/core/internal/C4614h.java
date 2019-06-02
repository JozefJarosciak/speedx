package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4589m;
import com.twitter.sdk.android.core.internal.scribe.C1502c;
import com.twitter.sdk.android.core.internal.scribe.C4629a;
import com.twitter.sdk.android.core.internal.scribe.C4643m;
import com.twitter.sdk.android.core.internal.scribe.c$a;
import com.twitter.sdk.android.core.services.AccountService;
import retrofit.RetrofitError;

/* compiled from: TwitterSessionVerifier */
/* renamed from: com.twitter.sdk.android.core.internal.h */
public class C4614h implements C4610e {
    /* renamed from: a */
    private final C4613a f16269a = new C4613a();
    /* renamed from: b */
    private final C4629a f16270b = C4643m.m18373a();

    /* compiled from: TwitterSessionVerifier */
    /* renamed from: com.twitter.sdk.android.core.internal.h$a */
    protected static class C4613a {
        protected C4613a() {
        }

        /* renamed from: a */
        public AccountService m18255a(C1469k c1469k) {
            return new C4589m(c1469k).m18180a();
        }
    }

    /* renamed from: a */
    public void mo6139a(C1469k c1469k) {
        AccountService a = this.f16269a.m18255a(c1469k);
        try {
            m18256a();
            a.verifyCredentials(Boolean.valueOf(true), Boolean.valueOf(false));
        } catch (RetrofitError e) {
        }
    }

    /* renamed from: a */
    private void m18256a() {
        if (this.f16270b != null) {
            C1502c a = new c$a().m18339a("android").m18341b("credentials").m18342c("").m18343d("").m18344e("").m18345f("impression").m18340a();
            this.f16270b.m18324a(a);
        }
    }
}
