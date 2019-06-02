package com.beastbikes.android.widget.materialdesign.progressbar;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Size;

/* compiled from: ObjectAnimatorCompatBase */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.h */
class C2695h {
    /* renamed from: a */
    public static ObjectAnimator m13337a(Object obj, String str, String str2, Path path) {
        float[] fArr = new float[201];
        float[] fArr2 = new float[201];
        C2695h.m13338a(path, fArr, fArr2);
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat(str, fArr);
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(str2, fArr2);
        return ObjectAnimator.ofPropertyValuesHolder(obj, new PropertyValuesHolder[]{ofFloat, ofFloat2});
    }

    /* renamed from: a */
    private static void m13338a(Path path, @Size(201) float[] fArr, @Size(201) float[] fArr2) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        float[] fArr3 = new float[2];
        for (int i = 0; i < 201; i++) {
            pathMeasure.getPosTan((((float) i) * length) / 200.0f, fArr3, null);
            fArr[i] = fArr3[0];
            fArr2[i] = fArr3[1];
        }
    }
}
