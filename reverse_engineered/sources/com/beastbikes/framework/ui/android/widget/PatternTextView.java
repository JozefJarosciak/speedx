package com.beastbikes.framework.ui.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import com.beastbikes.framework.ui.android.C2824R;

public class PatternTextView extends TextView {
    public PatternTextView(Context context) {
        this(context, null);
    }

    public PatternTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PatternTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2824R.styleable.PatternTextView);
        setText(String.format(String.valueOf(obtainStyledAttributes.getText(C2824R.styleable.PatternTextView_pattern)), new Object[]{getText()}));
        obtainStyledAttributes.recycle();
    }
}
