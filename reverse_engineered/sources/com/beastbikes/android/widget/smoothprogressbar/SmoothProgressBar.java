package com.beastbikes.android.widget.smoothprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.widget.smoothprogressbar.C2746c.C2744a;
import com.beastbikes.android.widget.smoothprogressbar.C2746c.C2745b;

public class SmoothProgressBar extends ProgressBar {
    public SmoothProgressBar(Context context) {
        this(context, null);
    }

    public SmoothProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1373R.attr.spbStyle);
    }

    public SmoothProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (isInEditMode()) {
            setIndeterminateDrawable(new C2744a(context, true).m13569a());
            return;
        }
        Interpolator accelerateInterpolator;
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SmoothProgressBar, i, 0);
        int color = obtainStyledAttributes.getColor(1, resources.getColor(C1373R.color.spb_default_color));
        int integer = obtainStyledAttributes.getInteger(4, resources.getInteger(C1373R.integer.spb_default_sections_count));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, resources.getDimensionPixelSize(C1373R.dimen.spb_default_stroke_separator_length));
        float dimension = obtainStyledAttributes.getDimension(2, resources.getDimension(C1373R.dimen.spb_default_stroke_width));
        float f = obtainStyledAttributes.getFloat(5, Float.parseFloat(resources.getString(C1373R.string.spb_default_speed)));
        float f2 = obtainStyledAttributes.getFloat(6, f);
        float f3 = obtainStyledAttributes.getFloat(7, f);
        int integer2 = obtainStyledAttributes.getInteger(8, -1);
        boolean z = obtainStyledAttributes.getBoolean(9, resources.getBoolean(C1373R.bool.spb_default_reversed));
        boolean z2 = obtainStyledAttributes.getBoolean(10, resources.getBoolean(C1373R.bool.spb_default_mirror_mode));
        int resourceId = obtainStyledAttributes.getResourceId(11, 0);
        boolean z3 = obtainStyledAttributes.getBoolean(12, resources.getBoolean(C1373R.bool.spb_default_progressiveStart_activated));
        Drawable drawable = obtainStyledAttributes.getDrawable(13);
        boolean z4 = obtainStyledAttributes.getBoolean(14, false);
        boolean z5 = obtainStyledAttributes.getBoolean(15, false);
        obtainStyledAttributes.recycle();
        Interpolator interpolator = null;
        if (integer2 == -1) {
            interpolator = getInterpolator();
        }
        if (interpolator == null) {
            Object linearInterpolator;
            switch (integer2) {
                case 1:
                    linearInterpolator = new LinearInterpolator();
                    break;
                case 2:
                    linearInterpolator = new AccelerateDecelerateInterpolator();
                    break;
                case 3:
                    linearInterpolator = new DecelerateInterpolator();
                    break;
                default:
                    accelerateInterpolator = new AccelerateInterpolator();
                    break;
            }
        }
        accelerateInterpolator = interpolator;
        int[] iArr = null;
        if (resourceId != 0) {
            iArr = resources.getIntArray(resourceId);
        }
        C2744a d = new C2744a(context).m13571b(f).m13574c(f2).m13577d(f3).m13566a(accelerateInterpolator).m13564a(integer).m13572b(dimensionPixelSize).m13563a(dimension).m13567a(z).m13573b(z2).m13576c(z3).m13578d(z5);
        if (drawable != null) {
            d.m13565a(drawable);
        }
        if (z4) {
            d.m13570b();
        }
        if (iArr == null || iArr.length <= 0) {
            d.m13575c(color);
        } else {
            d.m13568a(iArr);
        }
        setIndeterminateDrawable(d.m13569a());
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isIndeterminate() && (getIndeterminateDrawable() instanceof C2746c) && !((C2746c) getIndeterminateDrawable()).isRunning()) {
            getIndeterminateDrawable().draw(canvas);
        }
    }

    /* renamed from: c */
    private C2746c m13553c() {
        Drawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null && (indeterminateDrawable instanceof C2746c)) {
            return (C2746c) indeterminateDrawable;
        }
        throw new RuntimeException("The drawable is not a SmoothProgressDrawable");
    }

    public void setInterpolator(Interpolator interpolator) {
        super.setInterpolator(interpolator);
        Drawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null && (indeterminateDrawable instanceof C2746c)) {
            ((C2746c) indeterminateDrawable).m13604a(interpolator);
        }
    }

    public void setSmoothProgressDrawableInterpolator(Interpolator interpolator) {
        m13553c().m13604a(interpolator);
    }

    public void setSmoothProgressDrawableColors(int[] iArr) {
        m13553c().m13607a(iArr);
    }

    public void setSmoothProgressDrawableColor(int i) {
        m13553c().m13602a(i);
    }

    public void setSmoothProgressDrawableSpeed(float f) {
        m13553c().m13601a(f);
    }

    public void setSmoothProgressDrawableProgressiveStartSpeed(float f) {
        m13553c().m13609b(f);
    }

    public void setSmoothProgressDrawableProgressiveStopSpeed(float f) {
        m13553c().m13613c(f);
    }

    public void setSmoothProgressDrawableSectionsCount(int i) {
        m13553c().m13610b(i);
    }

    public void setSmoothProgressDrawableSeparatorLength(int i) {
        m13553c().m13614c(i);
    }

    public void setSmoothProgressDrawableStrokeWidth(float f) {
        m13553c().m13617d(f);
    }

    public void setSmoothProgressDrawableReversed(boolean z) {
        m13553c().m13606a(z);
    }

    public void setSmoothProgressDrawableMirrorMode(boolean z) {
        m13553c().m13611b(z);
    }

    public void setProgressiveStartActivated(boolean z) {
        m13553c().m13615c(z);
    }

    public void setSmoothProgressDrawableCallbacks(C2745b c2745b) {
        m13553c().m13605a(c2745b);
    }

    public void setSmoothProgressDrawableBackgroundDrawable(Drawable drawable) {
        m13553c().m13603a(drawable);
    }

    public void setSmoothProgressDrawableUseGradients(boolean z) {
        m13553c().m13619d(z);
    }

    /* renamed from: a */
    public void m13554a() {
        m13553c().m13612c();
    }

    /* renamed from: b */
    public void m13555b() {
        m13553c().m13616d();
    }
}
