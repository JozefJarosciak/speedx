package com.twitter.sdk.android.tweetui.internal;

import java.util.Locale;

/* compiled from: MediaTimeUtils */
/* renamed from: com.twitter.sdk.android.tweetui.internal.c */
final class C4724c {
    /* renamed from: a */
    static String m18599a(long j) {
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        if (i / 3600 > 0) {
            return String.format(Locale.getDefault(), "%1$d:%2$02d:%3$02d", new Object[]{Integer.valueOf(i / 3600), Integer.valueOf(i3), Integer.valueOf(i2)});
        }
        return String.format(Locale.getDefault(), "%1$d:%2$02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
    }
}
