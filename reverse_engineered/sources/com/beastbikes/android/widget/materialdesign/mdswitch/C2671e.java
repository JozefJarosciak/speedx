package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.TypedValue;
import com.beastbikes.android.C1373R;

/* compiled from: ThemeUtil */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.e */
public class C2671e {
    /* renamed from: a */
    private static TypedValue f12522a;

    /* renamed from: a */
    public static int m13275a(Context context, int i) {
        return (int) (TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    /* renamed from: a */
    private static int m13276a(Context context, int i, int i2) {
        if (f12522a == null) {
            f12522a = new TypedValue();
        }
        try {
            Theme theme = context.getTheme();
            if (theme == null || !theme.resolveAttribute(i, f12522a, true)) {
                return i2;
            }
            if (f12522a.type >= 16 && f12522a.type <= 31) {
                return f12522a.data;
            }
            if (f12522a.type == 3) {
                return context.getResources().getColor(f12522a.resourceId);
            }
            return i2;
        } catch (Exception e) {
            return i2;
        }
    }

    @TargetApi(21)
    /* renamed from: b */
    public static int m13278b(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return C2671e.m13276a(context, 16843817, i);
        }
        return C2671e.m13276a(context, C1373R.attr.colorControlNormal, i);
    }

    @TargetApi(21)
    /* renamed from: c */
    public static int m13279c(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return C2671e.m13276a(context, 16843818, i);
        }
        return C2671e.m13276a(context, C1373R.attr.colorControlActivated, i);
    }

    @TargetApi(21)
    /* renamed from: d */
    public static int m13280d(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return C2671e.m13276a(context, 16843820, i);
        }
        return C2671e.m13276a(context, C1373R.attr.colorControlHighlight, i);
    }

    /* renamed from: a */
    public static int m13277a(TypedArray typedArray, int i) {
        if (VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue peekValue = typedArray.peekValue(i);
        return peekValue == null ? 0 : peekValue.type;
    }
}
