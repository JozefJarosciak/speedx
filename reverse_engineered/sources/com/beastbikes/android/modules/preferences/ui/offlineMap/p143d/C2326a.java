package com.beastbikes.android.modules.preferences.ui.offlineMap.p143d;

import android.support.v4.media.session.PlaybackStateCompat;

/* compiled from: OfflineMapUtil */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.d.a */
public class C2326a {
    /* renamed from: a */
    public static String m11884a(long j) {
        String str = "";
        if (j >= 1073741824) {
            try {
                return (((float) Math.round((float) ((10 * j) / 1073741824))) / 10.0f) + " GB";
            } catch (Exception e) {
                return "0 B";
            }
        } else if (j >= 1048576) {
            return (((float) Math.round(((double) (10 * j)) / 1048576.0d)) / 10.0f) + " MB";
        } else {
            if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                return (((float) Math.round((float) ((10 * j) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID))) / 10.0f) + " KB";
            }
            if (j >= 0) {
                return j + " B";
            }
            return "0 B";
        }
    }
}
