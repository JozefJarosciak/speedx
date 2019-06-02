package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;

/* compiled from: ColorUtil */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.a */
public class C2661a {
    /* renamed from: b */
    private static int m13217b(int i, int i2, float f) {
        return Math.round(((float) i) + (((float) (i2 - i)) * f));
    }

    /* renamed from: a */
    public static int m13216a(int i, int i2, float f) {
        if (i == i2) {
            return i2;
        }
        if (f == 0.0f) {
            return i;
        }
        return f != 1.0f ? Color.argb(C2661a.m13217b(Color.alpha(i), Color.alpha(i2), f), C2661a.m13217b(Color.red(i), Color.red(i2), f), C2661a.m13217b(Color.green(i), Color.green(i2), f), C2661a.m13217b(Color.blue(i), Color.blue(i2), f)) : i2;
    }

    /* renamed from: a */
    public static int m13215a(int i, float f) {
        return (Math.round(((float) Color.alpha(i)) * f) << 24) | (ViewCompat.MEASURED_SIZE_MASK & i);
    }
}
