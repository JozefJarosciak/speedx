package com.mapbox.mapboxsdk.maps.widgets;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class CompassView$1 extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ CompassView this$0;

    CompassView$1(CompassView compassView) {
        this.this$0 = compassView;
    }

    public void onAnimationEnd(View view) {
        this.this$0.setVisibility(4);
        CompassView.access$000(this.this$0);
    }
}
