package com.twitter.sdk.android.core.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import ch.qos.logback.core.CoreConstants;

/* compiled from: TwitterApi */
/* renamed from: com.twitter.sdk.android.core.internal.f */
public class C4611f {
    /* renamed from: a */
    private final String f16262a;

    public C4611f() {
        this("https://api.twitter.com");
    }

    public C4611f(String str) {
        this.f16262a = str;
    }

    /* renamed from: a */
    public String m18249a() {
        return this.f16262a;
    }

    /* renamed from: a */
    public Builder m18248a(String... strArr) {
        Builder buildUpon = Uri.parse(m18249a()).buildUpon();
        if (strArr != null) {
            for (String appendPath : strArr) {
                buildUpon.appendPath(appendPath);
            }
        }
        return buildUpon;
    }

    /* renamed from: a */
    public static String m18247a(String str, String str2) {
        return '/' + str2 + ' ' + Build.MODEL + '/' + VERSION.RELEASE + " (" + Build.MANUFACTURER + ';' + Build.MODEL + ';' + Build.BRAND + ';' + Build.PRODUCT + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
