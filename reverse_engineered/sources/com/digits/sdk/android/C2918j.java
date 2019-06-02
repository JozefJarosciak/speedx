package com.digits.sdk.android;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/* compiled from: ButtonThemer */
/* renamed from: com.digits.sdk.android.j */
class C2918j {
    /* renamed from: b */
    private static int[][] f13314b = new int[][]{new int[]{16842908, 16842910}, new int[]{16842919, 16842910}};
    /* renamed from: a */
    private final Resources f13315a;

    public C2918j(Resources resources) {
        this.f13315a = resources;
    }

    @TargetApi(16)
    /* renamed from: a */
    void m14208a(View view, int i) {
        Drawable stateListDrawable = new StateListDrawable();
        float applyDimension = TypedValue.applyDimension(1, 5.0f, this.f13315a.getDisplayMetrics());
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(applyDimension);
        gradientDrawable.setColor(m14204c(i));
        for (int[] addState : f13314b) {
            stateListDrawable.addState(addState, gradientDrawable);
        }
        Drawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(i);
        gradientDrawable2.setCornerRadius(applyDimension);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable2);
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    @TargetApi(16)
    /* renamed from: b */
    void m14211b(View view, int i) {
        Drawable stateListDrawable = new StateListDrawable();
        float applyDimension = TypedValue.applyDimension(1, 5.0f, this.f13315a.getDisplayMetrics());
        float applyDimension2 = TypedValue.applyDimension(1, 2.0f, this.f13315a.getDisplayMetrics());
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(applyDimension);
        gradientDrawable.setStroke((int) applyDimension2, m14204c(i));
        for (int[] addState : f13314b) {
            stateListDrawable.addState(addState, gradientDrawable);
        }
        Drawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(applyDimension);
        gradientDrawable2.setStroke((int) applyDimension2, m14205d(i));
        stateListDrawable.addState(new int[]{-16842910}, gradientDrawable2);
        gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(applyDimension);
        gradientDrawable2.setStroke((int) applyDimension2, i);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable2);
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    /* renamed from: c */
    private int m14204c(int i) {
        if (bu.m14178a(i)) {
            return bu.m14174a(0.2d, ViewCompat.MEASURED_STATE_MASK, i);
        }
        return bu.m14174a(0.2d, -1, i);
    }

    /* renamed from: d */
    private int m14205d(int i) {
        if (bu.m14178a(i)) {
            return bu.m14174a(0.6d, ViewCompat.MEASURED_STATE_MASK, i);
        }
        return bu.m14174a(0.6d, -1, i);
    }

    /* renamed from: a */
    void m14209a(TextView textView, int i) {
        int d = m14205d(m14206a(i));
        r2 = new int[2][];
        r2[0] = new int[]{-16842910};
        r2[1] = StateSet.WILD_CARD;
        textView.setTextColor(new ColorStateList(r2, new int[]{d, r0}));
    }

    /* renamed from: b */
    void m14212b(TextView textView, int i) {
        int c = m14204c(i);
        int d = m14205d(i);
        r2 = new int[4][];
        r2[2] = new int[]{-16842910};
        r2[3] = StateSet.WILD_CARD;
        textView.setTextColor(new ColorStateList(r2, new int[]{c, c, d, i}));
    }

    /* renamed from: a */
    int m14206a(int i) {
        return bu.m14178a(i) ? this.f13315a.getColor(C2876R.color.dgts__text_dark) : this.f13315a.getColor(C2876R.color.dgts__text_light);
    }

    /* renamed from: b */
    int m14210b(int i) {
        return i;
    }

    @TargetApi(21)
    /* renamed from: a */
    void m14207a(View view) {
        if (VERSION.SDK_INT >= 21) {
            view.setStateListAnimator(null);
            view.setElevation(0.0f);
        }
    }
}
