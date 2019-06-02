package com.beastbikes.android.widget.smoothprogressbar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;

/* compiled from: SmoothProgressBarUtils */
/* renamed from: com.beastbikes.android.widget.smoothprogressbar.b */
public final class C2742b {
    /* renamed from: a */
    public static Drawable m13556a(int[] iArr, float f) {
        if (iArr == null || iArr.length == 0) {
            return null;
        }
        return new ShapeDrawable(new C2741a(f, iArr));
    }

    /* renamed from: a */
    static void m13557a(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Speed must be >= 0");
        }
    }

    /* renamed from: a */
    static void m13561a(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("You must provide at least 1 color");
        }
    }

    /* renamed from: a */
    static void m13558a(float f, String str) {
        if (f < 0.0f) {
            throw new IllegalArgumentException(String.format("%s %d must be positive", new Object[]{str, Float.valueOf(f)}));
        }
    }

    /* renamed from: a */
    static void m13559a(int i, String str) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("%s must not be null", new Object[]{str}));
        }
    }

    /* renamed from: a */
    static void m13560a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format("%s must be not null", new Object[]{str}));
        }
    }
}
