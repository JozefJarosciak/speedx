package com.beastbikes.framework.ui.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class FlatListView extends ListView {
    public FlatListView(Context context) {
        this(context, null);
    }

    public FlatListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlatListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
