package com.wdullaer.materialdatetimepicker;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;

/* compiled from: Utils */
/* renamed from: com.wdullaer.materialdatetimepicker.c */
public class C4783c {
    /* renamed from: a */
    public static boolean m18769a() {
        return VERSION.SDK_INT >= 16;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m18768a(View view, CharSequence charSequence) {
        if (C4783c.m18769a() && view != null && charSequence != null) {
            view.announceForAccessibility(charSequence);
        }
    }

    /* renamed from: a */
    public static ObjectAnimator m18767a(View view, float f, float f2) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.275f, f);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.69f, f2);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe("scaleX", new Keyframe[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        PropertyValuesHolder ofKeyframe2 = PropertyValuesHolder.ofKeyframe("scaleY", new Keyframe[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{ofKeyframe, ofKeyframe2});
        ofPropertyValuesHolder.setDuration(544);
        return ofPropertyValuesHolder;
    }

    /* renamed from: a */
    public static int m18765a(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * 0.8f;
        return Color.HSVToColor(fArr);
    }

    /* renamed from: a */
    public static int m18766a(Context context) {
        TypedValue typedValue = new TypedValue();
        if (VERSION.SDK_INT >= 21) {
            context.getTheme().resolveAttribute(16843829, typedValue, true);
            return typedValue.data;
        }
        int identifier = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        if (identifier == 0 || !context.getTheme().resolveAttribute(identifier, typedValue, true)) {
            return ContextCompat.getColor(context, C4779R.color.mdtp_accent_color);
        }
        return typedValue.data;
    }

    /* renamed from: a */
    public static boolean m18771a(Context context, boolean z) {
        return C4783c.m18770a(context, C4779R.attr.mdtp_theme_dark, z);
    }

    /* renamed from: a */
    private static boolean m18770a(Context context, @AttrRes int i, boolean z) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            boolean z2 = obtainStyledAttributes.getBoolean(0, z);
            return z2;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
