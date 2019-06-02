package com.digits.sdk.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class LogoImageView extends ImageView {
    public LogoImageView(Context context) {
        super(context);
        m13889a(context);
    }

    public LogoImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LogoImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13889a(context);
    }

    /* renamed from: a */
    void m13889a(Context context) {
        Drawable a = bu.m14176a(context.getTheme());
        if (a != null) {
            setVisibility(0);
            setImageDrawable(a);
            return;
        }
        setVisibility(8);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (getDrawable().getIntrinsicHeight() * size) / getDrawable().getIntrinsicWidth());
    }
}
