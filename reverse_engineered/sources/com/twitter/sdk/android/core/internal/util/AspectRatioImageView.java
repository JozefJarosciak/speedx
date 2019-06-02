package com.twitter.sdk.android.core.internal.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.twitter.sdk.android.core.C4573R;

public class AspectRatioImageView extends ImageView {
    /* renamed from: a */
    private double f16361a;
    /* renamed from: b */
    private int f16362b;

    public AspectRatioImageView(Context context) {
        this(context, null);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4573R.styleable.tw__AspectRatioImageView);
        try {
            this.f16361a = (double) obtainStyledAttributes.getFloat(C4573R.styleable.tw__AspectRatioImageView_tw__image_aspect_ratio, 1.0f);
            this.f16362b = obtainStyledAttributes.getInt(C4573R.styleable.tw__AspectRatioImageView_tw__image_dimension_to_adjust, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public double getAspectRatio() {
        return this.f16361a;
    }

    public int getDimensionToAdjust() {
        return this.f16362b;
    }

    public void setAspectRatio(double d) {
        this.f16361a = d;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.f16362b == 0) {
            measuredHeight = m18375a(measuredWidth, this.f16361a);
        } else {
            measuredWidth = m18376b(measuredHeight, this.f16361a);
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    /* renamed from: a */
    int m18375a(int i, double d) {
        if (d == 0.0d) {
            return 0;
        }
        return (int) Math.round(((double) i) / d);
    }

    /* renamed from: b */
    int m18376b(int i, double d) {
        return (int) Math.round(((double) i) * d);
    }
}
