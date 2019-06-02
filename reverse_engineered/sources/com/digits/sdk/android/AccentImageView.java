package com.digits.sdk.android;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AccentImageView extends ImageView {
    public AccentImageView(Context context) {
        super(context);
        m13833a(context);
    }

    public AccentImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AccentImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13833a(context);
    }

    /* renamed from: a */
    private void m13833a(Context context) {
        setColorFilter(bu.m14175a(getResources(), context.getTheme()), Mode.SRC_IN);
    }
}
