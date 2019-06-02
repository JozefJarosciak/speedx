package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* compiled from: Chart */
class c$1 implements AnimatorUpdateListener {
    /* renamed from: a */
    final /* synthetic */ C1475c f13878a;

    c$1(C1475c c1475c) {
        this.f13878a = c1475c;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f13878a.postInvalidate();
    }
}
