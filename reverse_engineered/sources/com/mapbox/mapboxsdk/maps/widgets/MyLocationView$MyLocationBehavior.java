package com.mapbox.mapboxsdk.maps.widgets;

import android.animation.ValueAnimator;
import android.location.Location;
import android.support.annotation.NonNull;

abstract class MyLocationView$MyLocationBehavior {
    final /* synthetic */ MyLocationView this$0;

    abstract void invalidate();

    private MyLocationView$MyLocationBehavior(MyLocationView myLocationView) {
        this.this$0 = myLocationView;
    }

    void updateLatLng(@NonNull Location location) {
        MyLocationView.access$602(this.this$0, location);
    }

    void updateLatLng(double d, double d2) {
        if (MyLocationView.access$700(this.this$0) != null) {
            MyLocationView.access$700(this.this$0).setLatitude(d);
            MyLocationView.access$700(this.this$0).setLongitude(d2);
        }
    }

    void updateAccuracy(@NonNull Location location) {
        if (MyLocationView.access$800(this.this$0) != null && MyLocationView.access$800(this.this$0).isRunning()) {
            MyLocationView.access$902(this.this$0, ((Float) MyLocationView.access$800(this.this$0).getAnimatedValue()).floatValue());
            MyLocationView.access$800(this.this$0).end();
        }
        MyLocationView.access$802(this.this$0, ValueAnimator.ofFloat(new float[]{MyLocationView.access$900(this.this$0) * 10.0f, location.getAccuracy() * 10.0f}));
        MyLocationView.access$800(this.this$0).setDuration(750);
        MyLocationView.access$800(this.this$0).start();
        MyLocationView.access$902(this.this$0, location.getAccuracy());
    }
}
