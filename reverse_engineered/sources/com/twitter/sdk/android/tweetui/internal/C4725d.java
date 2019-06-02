package com.twitter.sdk.android.tweetui.internal;

import android.os.Build.VERSION;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.VideoInfo.Variant;

/* compiled from: TweetMediaUtils */
/* renamed from: com.twitter.sdk.android.tweetui.internal.d */
public final class C4725d {
    /* renamed from: a */
    public static Variant m18600a(MediaEntity mediaEntity) {
        for (Variant variant : mediaEntity.videoInfo.variants) {
            if (C4725d.m18601a(variant)) {
                return variant;
            }
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m18602b(MediaEntity mediaEntity) {
        if ("animated_gif".equals(mediaEntity.type)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    static boolean m18601a(Variant variant) {
        if ((VERSION.SDK_INT < 21 || !"application/x-mpegURL".equals(variant.contentType)) && !"video/mp4".equals(variant.contentType)) {
            return false;
        }
        return true;
    }
}
