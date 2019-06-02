package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

/* compiled from: TypefaceUtil */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.g */
public class C2673g {
    /* renamed from: a */
    private static final HashMap<String, Typeface> f12548a = new HashMap();

    /* renamed from: a */
    public static Typeface m13289a(Context context, String str, int i) {
        if (str == null || !str.startsWith("asset:")) {
            return Typeface.create(str, i);
        }
        synchronized (f12548a) {
            try {
                Typeface typeface;
                if (f12548a.containsKey(str)) {
                    typeface = (Typeface) f12548a.get(str);
                    return typeface;
                }
                typeface = Typeface.createFromAsset(context.getAssets(), str.substring("asset:".length()));
                f12548a.put(str, typeface);
                return typeface;
            } catch (Exception e) {
                return Typeface.DEFAULT;
            }
        }
    }
}
