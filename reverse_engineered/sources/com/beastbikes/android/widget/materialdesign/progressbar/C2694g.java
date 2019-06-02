package com.beastbikes.android.widget.materialdesign.progressbar;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build.VERSION;

/* compiled from: ObjectAnimatorCompat */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.g */
public class C2694g {
    /* renamed from: a */
    public static ObjectAnimator m13336a(Object obj, String str, String str2, Path path) {
        if (VERSION.SDK_INT >= 21) {
            return C2696i.m13339a(obj, str, str2, path);
        }
        return C2695h.m13337a(obj, str, str2, path);
    }
}
