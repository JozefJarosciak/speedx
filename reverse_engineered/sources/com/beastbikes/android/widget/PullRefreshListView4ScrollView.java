package com.beastbikes.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class PullRefreshListView4ScrollView extends PullRefreshListView {
    public PullRefreshListView4ScrollView(Context context) {
        super(context);
    }

    public PullRefreshListView4ScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
