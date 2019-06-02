package com.digits.sdk.android;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4610e;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: DigitsSessionVerifier */
class ar implements C4610e {
    /* renamed from: a */
    private final C2895a f13196a;

    /* compiled from: DigitsSessionVerifier */
    /* renamed from: com.digits.sdk.android.ar$a */
    static class C2895a extends C4580e<bz> {
        /* renamed from: a */
        private final ConcurrentHashMap<bs, Boolean> f13194a;
        /* renamed from: b */
        private final C4586l<ap> f13195b;

        C2895a(ConcurrentHashMap<bs, Boolean> concurrentHashMap, C4586l<ap> c4586l) {
            this.f13194a = concurrentHashMap;
            this.f13195b = c4586l;
        }

        /* renamed from: a */
        public void m13984a(C4645j<bz> c4645j) {
            if (c4645j.f16364a != null) {
                C1469k a = ap.a((bz) c4645j.f16364a);
                if (a.b() && !a.equals(this.f13195b.a(a.e()))) {
                    this.f13195b.a(a.e(), a);
                    for (bs bsVar : this.f13194a.keySet()) {
                        if (bsVar != null) {
                            bsVar.m14167a(a);
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        public void m13983a(TwitterException twitterException) {
        }
    }

    ar() {
        this(new C2895a(new ConcurrentHashMap(), aa.b()));
    }

    ar(C2895a c2895a) {
        this.f13196a = c2895a;
    }

    /* renamed from: a */
    public void m13985a(C1469k c1469k) {
        if ((c1469k instanceof ap) && !((ap) c1469k).a()) {
            m13986b(c1469k).verifyAccount(this.f13196a);
        }
    }

    /* renamed from: b */
    DigitsApiClient$AccountService m13986b(C1469k c1469k) {
        return new DigitsApiClient(c1469k).m13870d();
    }
}
