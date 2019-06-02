package com.beastbikes.android.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: TypefaceUtils */
/* renamed from: com.beastbikes.android.utils.z */
public class C2583z {
    /* renamed from: a */
    private static final Map<String, Typeface> f12062a = new HashMap();

    @Nullable
    /* renamed from: a */
    public static Typeface m12912a(AssetManager assetManager, String str) {
        Typeface typeface;
        synchronized (f12062a) {
            try {
                if (f12062a.containsKey(str)) {
                    typeface = (Typeface) f12062a.get(str);
                } else {
                    typeface = Typeface.createFromAsset(assetManager, str);
                    f12062a.put(str, typeface);
                }
            } catch (Throwable e) {
                Log.w("Calligraphy", "Can't create asset from " + str + ". Make sure you have passed in the correct path and file name.", e);
                f12062a.put(str, Typeface.DEFAULT);
                typeface = Typeface.DEFAULT;
            }
        }
        return typeface;
    }
}
