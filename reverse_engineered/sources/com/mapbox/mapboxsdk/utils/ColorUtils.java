package com.mapbox.mapboxsdk.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.widget.ImageView;
import com.mapbox.mapboxsdk.R$attr;
import com.mapbox.mapboxsdk.exceptions.ConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
    @ColorInt
    public static int getPrimaryColor(@NonNull Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    @ColorInt
    public static int getPrimaryDarkColor(@NonNull Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    @ColorInt
    public static int getAccentColor(@NonNull Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.colorAccent, typedValue, true);
        return typedValue.data;
    }

    @NonNull
    public static ColorStateList getSelector(@ColorInt int i) {
        r1 = new int[2][];
        r1[0] = new int[]{16842919};
        r1[1] = new int[0];
        return new ColorStateList(r1, new int[]{i, i});
    }

    public static void setTintList(@NonNull ImageView imageView, @ColorInt int i) {
        DrawableCompat.setTintList(DrawableCompat.wrap(imageView.getDrawable()), getSelector(i));
    }

    static int normalizeColorComponent(String str) {
        return (int) (Float.parseFloat(str) * 255.0f);
    }

    @ColorInt
    public static int rgbaToColor(String str) {
        Matcher matcher = Pattern.compile("rgba?\\s*\\(\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*,?\\s*(\\d+\\.?\\d*)?\\s*\\)").matcher(str);
        if (matcher.matches() && matcher.groupCount() == 3) {
            return Color.rgb(normalizeColorComponent(matcher.group(1)), normalizeColorComponent(matcher.group(2)), normalizeColorComponent(matcher.group(3)));
        }
        if (matcher.matches() && matcher.groupCount() == 4) {
            return Color.argb(normalizeColorComponent(matcher.group(4)), normalizeColorComponent(matcher.group(1)), normalizeColorComponent(matcher.group(2)), normalizeColorComponent(matcher.group(3)));
        }
        throw new ConversionException("Not a valid rgb/rgba value");
    }
}
