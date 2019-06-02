package com.beastbikes.framework.ui.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.beastbikes.framework.ui.android.C2824R;

public class ScrollableViewPager extends ViewPager {
    private final boolean scrollable;

    public ScrollableViewPager(Context context) {
        this(context, null);
    }

    public ScrollableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2824R.styleable.ScrollableViewPager);
        this.scrollable = obtainStyledAttributes.getBoolean(C2824R.styleable.ScrollableViewPager_scrollable, false);
        obtainStyledAttributes.recycle();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.scrollable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
