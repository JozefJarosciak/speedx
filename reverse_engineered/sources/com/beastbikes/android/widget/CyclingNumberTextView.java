package com.beastbikes.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.beastbikes.android.utils.C2583z;

public class CyclingNumberTextView extends TextView {
    public CyclingNumberTextView(Context context) {
        super(context);
        m12919a(context);
    }

    public CyclingNumberTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12919a(context);
    }

    public CyclingNumberTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12919a(context);
    }

    /* renamed from: a */
    private void m12919a(Context context) {
        setTypeface(C2583z.m12912a(context.getAssets(), "fonts/YS11.otf"));
    }
}
