package com.beastbikes.android.modules.cycling.route.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;

/* compiled from: DragSortItemView */
/* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.b */
public class C2217b extends ViewGroup {
    /* renamed from: a */
    private int f10485a = 48;

    public C2217b(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(-1, -2));
    }

    public void setGravity(int i) {
        this.f10485a = i;
    }

    public int getGravity() {
        return this.f10485a;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            if (this.f10485a == 48) {
                childAt.layout(0, 0, getMeasuredWidth(), childAt.getMeasuredHeight());
            } else {
                childAt.layout(0, getMeasuredHeight() - childAt.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i2);
        View childAt = getChildAt(0);
        if (childAt == null) {
            setMeasuredDimension(0, size2);
            return;
        }
        if (childAt.isLayoutRequested()) {
            measureChild(childAt, i, MeasureSpec.makeMeasureSpec(0, 0));
        }
        if (mode == 0) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams.height > 0) {
                size = layoutParams.height;
            } else {
                size = childAt.getMeasuredHeight();
            }
        }
        setMeasuredDimension(size2, size);
    }
}
