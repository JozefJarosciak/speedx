package com.digits.sdk.android;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import com.digits.sdk.android.C2876R.attr;
import java.lang.reflect.Field;

/* compiled from: ThemeUtils */
class bu {
    /* renamed from: a */
    static TypedValue m14177a(Theme theme, int i) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        return (typedValue.type < 28 || typedValue.type > 31) ? null : typedValue;
    }

    @TargetApi(21)
    /* renamed from: a */
    static int m14175a(Resources resources, Theme theme) {
        TypedValue a = m14177a(theme, C2876R.attr.dgts__accentColor);
        if (a != null) {
            return a.data;
        }
        if (VERSION.SDK_INT >= 21) {
            a = m14177a(theme, 16843829);
            if (a != null) {
                return a.data;
            }
        }
        try {
            Field declaredField = attr.class.getDeclaredField("colorAccent");
            a = m14177a(theme, declaredField.getInt(declaredField.getType()));
            if (a != null) {
                return a.data;
            }
        } catch (Exception e) {
        }
        return resources.getColor(C2876R.color.dgts__default_accent);
    }

    /* renamed from: a */
    static boolean m14178a(int i) {
        return ((((double) Color.green(i)) * 0.72d) + (0.21d * ((double) Color.red(i)))) + (((double) Color.blue(i)) * 0.07d) > 170.0d;
    }

    /* renamed from: a */
    static int m14174a(double d, int i, int i2) {
        int red = Color.red(i2);
        int red2 = Color.red(i);
        int green = Color.green(i2);
        int green2 = Color.green(i);
        return Color.rgb((int) ((((double) red2) * d) + ((1.0d - d) * ((double) red))), (int) ((((double) green2) * d) + ((1.0d - d) * ((double) green))), (int) (((1.0d - d) * ((double) Color.blue(i2))) + (((double) Color.blue(i)) * d)));
    }

    /* renamed from: a */
    static Drawable m14176a(Theme theme) {
        return theme.obtainStyledAttributes(new TypedValue().data, new int[]{C2876R.attr.dgts__logoDrawable}).getDrawable(0);
    }
}
