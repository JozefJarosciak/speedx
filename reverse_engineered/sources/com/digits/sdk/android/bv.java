package com.digits.sdk.android;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.Spanned;

/* compiled from: TosFormatHelper */
public class bv {
    /* renamed from: a */
    private final Context f13310a;

    bv(Context context) {
        this.f13310a = context;
    }

    /* renamed from: a */
    protected Spanned m14181a(@StringRes int i) {
        String a = m14179a(this.f13310a);
        return Html.fromHtml(this.f13310a.getString(i, new Object[]{"\"", "<u>", "</u>", a, "</a>", m14180b(C2876R.string.dgts__digits_com_url), m14180b(C2876R.string.dgts__digits_com_settings_url), m14180b(C2876R.string.dgts__twitter_tos_url), m14180b(C2876R.string.dgts__twitter_privacy_url), m14180b(C2876R.string.dgts__twitter_cookies_policy_url)}));
    }

    /* renamed from: a */
    private String m14179a(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    /* renamed from: b */
    private String m14180b(@StringRes int i) {
        return String.format("<a href=%1$s>", new Object[]{this.f13310a.getResources().getString(i)});
    }
}
