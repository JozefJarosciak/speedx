package com.mapbox.mapboxsdk.maps.widgets;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.mapbox.mapboxsdk.geometry.LatLng;

class MyLocationView$MarkerCoordinateAnimatorListener implements AnimatorUpdateListener {
    private MyLocationView$MyLocationBehavior behavior;
    private double fromLat;
    private double fromLng;
    final /* synthetic */ MyLocationView this$0;
    private double toLat;
    private double toLng;

    private MyLocationView$MarkerCoordinateAnimatorListener(MyLocationView myLocationView, MyLocationView$MyLocationBehavior myLocationView$MyLocationBehavior, LatLng latLng, LatLng latLng2) {
        this.this$0 = myLocationView;
        this.behavior = myLocationView$MyLocationBehavior;
        this.fromLat = latLng.getLatitude();
        this.fromLng = latLng.getLongitude();
        this.toLat = latLng2.getLatitude();
        this.toLng = latLng2.getLongitude();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.behavior.updateLatLng(this.fromLat + ((this.toLat - this.fromLat) * ((double) animatedFraction)), (((double) animatedFraction) * (this.toLng - this.fromLng)) + this.fromLng);
        this.this$0.update();
    }
}
