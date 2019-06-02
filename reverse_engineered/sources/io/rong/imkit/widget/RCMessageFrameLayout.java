package io.rong.imkit.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RCMessageFrameLayout extends FrameLayout {
    private Drawable mOldDrawable;

    public RCMessageFrameLayout(Context context) {
        super(context);
    }

    public RCMessageFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RCMessageFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        this.mOldDrawable = getBackground();
        setBackgroundDrawable(null);
        setPadding(0, 0, 0, 0);
    }

    public Drawable getBackgroundDrawable() {
        return this.mOldDrawable;
    }
}
