package com.mapbox.mapboxsdk.maps.widgets;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class MyLocationView$1 implements AnimatorUpdateListener {
    final /* synthetic */ MyLocationView this$0;

    MyLocationView$1(MyLocationView myLocationView) {
        this.this$0 = myLocationView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.this$0.invalidate();
    }
}
