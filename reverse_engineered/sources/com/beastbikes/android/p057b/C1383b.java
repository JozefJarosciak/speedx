package com.beastbikes.android.p057b;

import android.content.Context;
import java.io.File;

/* compiled from: BeastStore */
/* renamed from: com.beastbikes.android.b.b */
public final class C1383b {

    /* compiled from: BeastStore */
    /* renamed from: com.beastbikes.android.b.b$a */
    public static final class C1382a {
        /* renamed from: a */
        public static void m5409a(Context context) {
            int i = 0;
            File externalCacheDir = context.getExternalCacheDir();
            String[] strArr = new String[]{"avatars", "activities", "apks"};
            int length = strArr.length;
            while (i < length) {
                new File(externalCacheDir, strArr[i]).mkdirs();
                i++;
            }
        }
    }
}
