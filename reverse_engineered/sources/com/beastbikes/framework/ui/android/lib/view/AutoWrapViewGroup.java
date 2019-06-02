package com.beastbikes.framework.ui.android.lib.view;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class AutoWrapViewGroup extends ViewGroup {
    private int horInterval = 10;
    private int imgSize = 80;
    private int rowContentCount = 1;
    private int verInterval = 10;

    public AutoWrapViewGroup(Context context) {
        super(context);
    }

    public void setHorizontalInterval(int i) {
        this.horInterval = i;
    }

    public void setVerticalInterval(int i) {
        this.verInterval = i;
    }

    public void setChildSize(int i) {
        this.imgSize = i;
    }

    public void setChildRowCount(int i) {
        this.rowContentCount = i;
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            if (getChildAt(i4).getVisibility() == 0) {
                i3++;
            }
        }
        i3 = ((i3 + this.rowContentCount) - 1) / this.rowContentCount;
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(((i3 - 1) * this.verInterval) + (this.imgSize * i3), Integer.MIN_VALUE));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int childCount = getChildCount();
        int i6 = 0;
        int i7 = 0;
        while (i6 < childCount) {
            int i8;
            View childAt = getChildAt(i6);
            int i9 = this.imgSize;
            int i10 = this.imgSize;
            if (i6 == 0) {
                i8 = i5 + i9;
            } else {
                i8 = (this.horInterval + i9) + i5;
            }
            if (i7 == 0) {
                i5 = i10;
            } else {
                i5 = ((this.verInterval + i10) * i7) + i10;
            }
            if (i8 > i3) {
                i8 = i7 + 1;
                i5 = ((this.verInterval + i10) * i8) + i10;
                i7 = i8;
                i8 = i9;
            }
            childAt.layout(i8 - i9, i5 - i10, i8, i5);
            i6++;
            i5 = i8;
        }
    }
}
