package com.beastbikes.android.modules.user.filter.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class DrawableCenterTextView extends TextView {
    public DrawableCenterTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DrawableCenterTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableCenterTextView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (compoundDrawables != null) {
            Drawable drawable = compoundDrawables[0];
            Drawable drawable2 = compoundDrawables[2];
            if (!(drawable == null && drawable2 == null)) {
                float measureText = getPaint().measureText(getText().toString());
                int compoundDrawablePadding = getCompoundDrawablePadding();
                if (drawable != null) {
                    i = drawable.getIntrinsicWidth();
                } else if (drawable2 != null) {
                    i = drawable2.getIntrinsicWidth();
                }
                canvas.translate((((float) getWidth()) - ((((float) i) + measureText) + ((float) compoundDrawablePadding))) / 2.0f, 0.0f);
            }
        }
        super.onDraw(canvas);
    }
}
