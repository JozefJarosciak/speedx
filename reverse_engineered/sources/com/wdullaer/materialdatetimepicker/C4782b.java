package com.wdullaer.materialdatetimepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.SimpleArrayMap;

/* compiled from: TypefaceHelper */
/* renamed from: com.wdullaer.materialdatetimepicker.b */
public class C4782b {
    /* renamed from: a */
    private static final SimpleArrayMap<String, Typeface> f16736a = new SimpleArrayMap();

    /* renamed from: a */
    public static Typeface m18764a(Context context, String str) {
        Typeface typeface;
        synchronized (f16736a) {
            if (f16736a.containsKey(str)) {
                typeface = (Typeface) f16736a.get(str);
            } else {
                typeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s.ttf", new Object[]{str}));
                f16736a.put(str, typeface);
            }
        }
        return typeface;
    }
}
