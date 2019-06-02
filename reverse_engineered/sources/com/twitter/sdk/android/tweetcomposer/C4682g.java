package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.internal.scribe.C1502c;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ComposerScribeClientImpl */
/* renamed from: com.twitter.sdk.android.tweetcomposer.g */
class C4682g implements C4681f {
    /* renamed from: a */
    private final C4686j f16468a;

    C4682g(C4686j c4686j) {
        if (c4686j == null) {
            throw new NullPointerException("scribeClient must not be null");
        }
        this.f16468a = c4686j;
    }

    /* renamed from: a */
    public void mo6160a(Card card) {
        C1502c a = C4688l.f16488a.m18343d("").m18344e("").m18345f("impression").m18340a();
        List arrayList = new ArrayList();
        arrayList.add(C4688l.m18482a(card));
        this.f16468a.mo6162a(a, arrayList);
    }

    /* renamed from: a */
    public void mo6161a(Card card, String str) {
        C1502c a = C4688l.f16488a.m18343d("").m18344e(str).m18345f("click").m18340a();
        List arrayList = new ArrayList();
        arrayList.add(C4688l.m18482a(card));
        this.f16468a.mo6162a(a, arrayList);
    }
}
