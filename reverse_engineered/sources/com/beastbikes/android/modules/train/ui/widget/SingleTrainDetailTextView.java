package com.beastbikes.android.modules.train.ui.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

public class SingleTrainDetailTextView extends AppCompatCheckedTextView {
    public SingleTrainDetailTextView(Context context) {
        super(context);
        m12114a();
    }

    public SingleTrainDetailTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m12114a();
    }

    public SingleTrainDetailTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12114a();
    }

    /* renamed from: a */
    private void m12114a() {
        r0 = new int[2][];
        r0[0] = new int[]{16842912};
        r0[1] = new int[0];
        setTextColor(new ColorStateList(r0, new int[]{-1, -6710887}));
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
        if (z) {
            setTextSize(1, 17.0f);
        } else {
            setTextSize(1, 14.0f);
        }
    }
}
