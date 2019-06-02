package com.beastbikes.android.widget;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

class CircleIndicator$1 implements OnPageChangeListener {
    /* renamed from: a */
    final /* synthetic */ CircleIndicator f12064a;

    CircleIndicator$1(CircleIndicator circleIndicator) {
        this.f12064a = circleIndicator;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (CircleIndicator.a(this.f12064a).getAdapter() != null && CircleIndicator.a(this.f12064a).getAdapter().getCount() > 0) {
            View childAt;
            LayoutParams layoutParams;
            if (CircleIndicator.b(this.f12064a).isRunning()) {
                CircleIndicator.b(this.f12064a).end();
                CircleIndicator.b(this.f12064a).cancel();
            }
            if (CircleIndicator.c(this.f12064a).isRunning()) {
                CircleIndicator.c(this.f12064a).end();
                CircleIndicator.c(this.f12064a).cancel();
            }
            if (CircleIndicator.d(this.f12064a) >= 0) {
                childAt = this.f12064a.getChildAt(CircleIndicator.d(this.f12064a));
                if (childAt != null) {
                    childAt.setBackgroundResource(CircleIndicator.e(this.f12064a));
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    layoutParams.width = CircleIndicator.f(this.f12064a);
                    layoutParams.height = CircleIndicator.g(this.f12064a);
                    childAt.setLayoutParams(layoutParams);
                    CircleIndicator.b(this.f12064a).setTarget(childAt);
                    CircleIndicator.b(this.f12064a).start();
                }
            }
            childAt = this.f12064a.getChildAt(i);
            if (childAt != null) {
                childAt.setBackgroundResource(CircleIndicator.h(this.f12064a));
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.width = CircleIndicator.i(this.f12064a);
                layoutParams.height = CircleIndicator.j(this.f12064a);
                childAt.setLayoutParams(layoutParams);
                CircleIndicator.c(this.f12064a).setTarget(childAt);
                CircleIndicator.c(this.f12064a).start();
            }
            CircleIndicator.a(this.f12064a, i);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }
}
