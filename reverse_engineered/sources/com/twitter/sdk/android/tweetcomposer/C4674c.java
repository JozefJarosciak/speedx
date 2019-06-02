package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.view.View;

/* compiled from: CardViewFactory */
/* renamed from: com.twitter.sdk.android.tweetcomposer.c */
class C4674c {
    C4674c() {
    }

    /* renamed from: a */
    View m18436a(Context context, Card card) {
        if (!card.f16418a.equals(Card.APP_CARD_TYPE)) {
            return null;
        }
        View c4672a = new C4672a(context);
        c4672a.setCard(card);
        return c4672a;
    }
}
