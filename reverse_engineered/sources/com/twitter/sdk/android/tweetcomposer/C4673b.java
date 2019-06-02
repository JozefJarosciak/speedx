package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.tweetcomposer.internal.C1516b;
import com.twitter.sdk.android.tweetcomposer.internal.b$a;

/* compiled from: CardDataFactory */
/* renamed from: com.twitter.sdk.android.tweetcomposer.b */
class C4673b {
    /* renamed from: a */
    static C1516b m18434a(Card card, Long l, String str) {
        return new b$a().m18470a(Card.APP_CARD_TYPE).m18472b(C4673b.m18435a(l)).m18476f(card.f16422e).m18477g(card.f16421d).m18478h(card.f16423f).m18473c("{}").m18474d("open").m18475e(str).m18471a();
    }

    /* renamed from: a */
    static String m18435a(Long l) {
        return "media://" + Long.toString(l.longValue());
    }
}
