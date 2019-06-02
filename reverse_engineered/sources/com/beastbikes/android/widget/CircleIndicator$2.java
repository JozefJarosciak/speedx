package com.beastbikes.android.widget;

import android.database.DataSetObserver;

class CircleIndicator$2 extends DataSetObserver {
    /* renamed from: a */
    final /* synthetic */ CircleIndicator f12065a;

    CircleIndicator$2(CircleIndicator circleIndicator) {
        this.f12065a = circleIndicator;
    }

    public void onChanged() {
        super.onChanged();
        if (CircleIndicator.a(this.f12065a) != null) {
            int count = CircleIndicator.a(this.f12065a).getAdapter().getCount();
            if (count != this.f12065a.getChildCount()) {
                if (CircleIndicator.d(this.f12065a) < count) {
                    CircleIndicator.a(this.f12065a, CircleIndicator.a(this.f12065a).getCurrentItem());
                } else {
                    CircleIndicator.a(this.f12065a, -1);
                }
                CircleIndicator.k(this.f12065a);
            }
        }
    }
}
