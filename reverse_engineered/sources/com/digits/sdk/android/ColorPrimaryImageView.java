package com.digits.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ColorPrimaryImageView extends ImageView {
    public ColorPrimaryImageView(Context context) {
        super(context);
        m13836a(context);
    }

    public ColorPrimaryImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPrimaryImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13836a(context);
    }

    /* renamed from: a */
    private void m13836a(Context context) {
        setColorFilter(m13837b(context), Mode.SRC_IN);
    }

    /* renamed from: b */
    private int m13837b(Context context) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16842806});
        int color = obtainStyledAttributes.getColor(0, getResources().getColor(C2876R.color.dgts__default_logo_name));
        obtainStyledAttributes.recycle();
        return color;
    }
}
