package com.digits.sdk.android;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: DigitsClient */
public class ag {
    /* renamed from: a */
    private final aa f13161a;
    /* renamed from: b */
    private final at f13162b;
    /* renamed from: c */
    private final C4586l<ap> f13163c;
    /* renamed from: d */
    private final C4655n f13164d;
    /* renamed from: e */
    private final ae f13165e;
    /* renamed from: f */
    private final ao f13166f;
    /* renamed from: g */
    private DigitsApiClient f13167g;

    /* compiled from: DigitsClient */
    /* renamed from: com.digits.sdk.android.ag$a */
    static abstract class C2886a<T> extends C4580e<DigitsApiClient> {
        /* renamed from: e */
        final C4580e<T> f13143e;

        public C2886a(C4580e<T> c4580e) {
            this.f13143e = c4580e;
        }

        /* renamed from: a */
        public void m13923a(TwitterException twitterException) {
            if (this.f13143e != null) {
                this.f13143e.a(twitterException);
            }
        }
    }

    ag() {
        this(aa.a(), new at(), C4655n.a(), aa.b(), null, new C2916h(aa.a().i()));
    }

    ag(aa aaVar, at atVar, C4655n c4655n, C4586l<ap> c4586l, ae aeVar, ao aoVar) {
        if (c4655n == null) {
            throw new IllegalArgumentException("twitter must not be null");
        } else if (aaVar == null) {
            throw new IllegalArgumentException("digits must not be null");
        } else if (atVar == null) {
            throw new IllegalArgumentException("userAgent must not be null");
        } else if (c4586l == null) {
            throw new IllegalArgumentException("sessionManager must not be null");
        } else {
            this.f13164d = c4655n;
            this.f13161a = aaVar;
            this.f13162b = atVar;
            this.f13163c = c4586l;
            if (aeVar == null) {
                this.f13165e = m13930a((C4586l) c4586l);
                this.f13165e.a(null);
            } else {
                this.f13165e = aeVar;
            }
            this.f13166f = aoVar;
        }
    }

    /* renamed from: a */
    protected ae m13930a(C4586l c4586l) {
        List arrayList = new ArrayList(1);
        arrayList.add(c4586l);
        return new ae(this, new ak(c4586l, arrayList));
    }

    /* renamed from: a */
    protected void m13933a(final String str, final Verification verification, C4580e<C1470g> c4580e) {
        this.f13165e.m13921a(new C2886a<C1470g>(this, c4580e) {
            /* renamed from: c */
            final /* synthetic */ ag f13146c;

            /* renamed from: a */
            public void m13924a(C4645j<DigitsApiClient> c4645j) {
                ((DigitsApiClient) c4645j.f16364a).m13868b().auth(str, verification.name(), Locale.getDefault().getLanguage(), this.e);
            }
        });
    }

    /* renamed from: a */
    protected void m13934a(final String str, final String str2, C4580e<as> c4580e) {
        this.f13165e.m13921a(new C2886a<as>(this, c4580e) {
            /* renamed from: c */
            final /* synthetic */ ag f13149c;

            /* renamed from: a */
            public void m13925a(C4645j<DigitsApiClient> c4645j) {
                ((DigitsApiClient) c4645j.f16364a).m13868b().account(str2, str, this.e);
            }
        });
    }

    /* renamed from: a */
    protected void m13932a(String str, long j, String str2, C4580e<aq> c4580e) {
        final String str3 = str;
        final long j2 = j;
        final String str4 = str2;
        this.f13165e.m13921a(new C2886a<aq>(this, c4580e) {
            /* renamed from: d */
            final /* synthetic */ ag f13153d;

            /* renamed from: a */
            public void m13926a(C4645j<DigitsApiClient> c4645j) {
                ((DigitsApiClient) c4645j.f16364a).m13868b().login(str3, j2, str4, this.e);
            }
        });
    }

    /* renamed from: b */
    protected void m13936b(final String str, final Verification verification, C4580e<C1471z> c4580e) {
        this.f13165e.m13921a(new C2886a<C1471z>(this, c4580e) {
            /* renamed from: c */
            final /* synthetic */ ag f13156c;

            /* renamed from: a */
            public void m13927a(C4645j<DigitsApiClient> c4645j) {
                ((DigitsApiClient) c4645j.f16364a).m13869c().register(str, "third_party_confirmation_code", Boolean.valueOf(true), Locale.getDefault().getLanguage(), "digits_sdk", verification.name(), this.e);
            }
        });
    }

    /* renamed from: b */
    protected void m13935b(String str, long j, String str2, C4580e<aq> c4580e) {
        final String str3 = str;
        final long j2 = j;
        final String str4 = str2;
        this.f13165e.m13921a(new C2886a<aq>(this, c4580e) {
            /* renamed from: d */
            final /* synthetic */ ag f13160d;

            /* renamed from: a */
            public void m13928a(C4645j<DigitsApiClient> c4645j) {
                ((DigitsApiClient) c4645j.f16364a).m13868b().verifyPin(str3, j2, str4, this.e);
            }
        });
    }

    /* renamed from: a */
    protected at m13931a() {
        return this.f13162b;
    }

    /* renamed from: a */
    DigitsApiClient m13929a(C1469k c1469k) {
        if (this.f13167g != null && this.f13167g.m13867a().equals(c1469k)) {
            return this.f13167g;
        }
        this.f13167g = new DigitsApiClient(c1469k, this.f13164d.b(), this.f13164d.e(), this.f13161a.k(), this.f13162b);
        return this.f13167g;
    }
}
