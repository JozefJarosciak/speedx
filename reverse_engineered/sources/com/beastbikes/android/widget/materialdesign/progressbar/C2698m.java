package com.beastbikes.android.widget.materialdesign.progressbar;

import android.content.Context;
import android.content.res.TypedArray;

/* compiled from: ThemeUtils */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.m */
public class C2698m {
    /* renamed from: a */
    public static int m13346a(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            int color = obtainStyledAttributes.getColor(0, 0);
            return color;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: b */
    public static float m13347b(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            float f = obtainStyledAttributes.getFloat(0, 0.0f);
            return f;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
