package com.beastbikes.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ScrollView4CheckBottom extends ScrollView {
    /* renamed from: a */
    private C2601a f12177a;

    /* renamed from: com.beastbikes.android.widget.ScrollView4CheckBottom$a */
    public interface C2601a {
        /* renamed from: a */
        void m12986a();
    }

    public ScrollView4CheckBottom(Context context) {
        super(context);
    }

    public ScrollView4CheckBottom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setScrollViewLoadMoreListener(C2601a c2601a) {
        this.f12177a = c2601a;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (getHeight() + i2 >= computeVerticalScrollRange() && this.f12177a != null) {
            this.f12177a.m12986a();
        }
    }
}
