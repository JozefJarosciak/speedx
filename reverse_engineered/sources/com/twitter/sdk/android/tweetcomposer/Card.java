package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import java.io.Serializable;

public class Card implements Serializable {
    public static final String APP_CARD_TYPE = "promo_image_app";
    /* renamed from: a */
    final String f16418a;
    /* renamed from: b */
    final String f16419b;
    /* renamed from: c */
    final String f16420c;
    /* renamed from: d */
    final String f16421d;
    /* renamed from: e */
    final String f16422e;
    /* renamed from: f */
    final String f16423f;

    public String getCardType() {
        return this.f16418a;
    }

    /* renamed from: a */
    static boolean m18408a(Card card) {
        return (card == null || card.getCardType() == null || !card.getCardType().equals(APP_CARD_TYPE)) ? false : true;
    }

    private static String getApplicationName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    private static String getPackageName(Context context) {
        return context.getPackageName();
    }
}
