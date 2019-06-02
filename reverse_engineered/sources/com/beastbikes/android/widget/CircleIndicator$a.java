package com.beastbikes.android.widget;

import android.view.animation.Interpolator;

class CircleIndicator$a implements Interpolator {
    /* renamed from: a */
    final /* synthetic */ CircleIndicator f12066a;

    private CircleIndicator$a(CircleIndicator circleIndicator) {
        this.f12066a = circleIndicator;
    }

    public float getInterpolation(float f) {
        return Math.abs(1.0f - f);
    }
}
