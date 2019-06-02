package com.mapbox.mapboxsdk.maps.widgets;

import android.animation.ValueAnimator;
import android.location.Location;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.geometry.LatLng;

class MyLocationView$MyLocationShowBehavior extends MyLocationView$MyLocationBehavior {
    final /* synthetic */ MyLocationView this$0;

    private MyLocationView$MyLocationShowBehavior(MyLocationView myLocationView) {
        this.this$0 = myLocationView;
        super(myLocationView);
    }

    void updateLatLng(@NonNull Location location) {
        super.updateLatLng(location);
        if (MyLocationView.access$700(this.this$0) == null) {
            MyLocationView.access$702(this.this$0, new LatLng(location));
            MyLocationView.access$1102(this.this$0, SystemClock.elapsedRealtime());
        }
        LatLng latLng = new LatLng(location);
        if (MyLocationView.access$1200(this.this$0) == 8 && location.hasBearing()) {
            MyLocationView.access$100(this.this$0, location.getBearing() + MyLocationView.access$1600(this.this$0));
        }
        updateAccuracy(location);
        long access$1100 = MyLocationView.access$1100(this.this$0);
        MyLocationView.access$1102(this.this$0, SystemClock.elapsedRealtime());
        access$1100 = (long) (((float) (MyLocationView.access$1100(this.this$0) - access$1100)) * 1.2f);
        if (MyLocationView.access$1700(this.this$0) != null) {
            MyLocationView.access$1700(this.this$0).end();
            MyLocationView.access$1702(this.this$0, null);
        }
        MyLocationView.access$1702(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
        MyLocationView.access$1700(this.this$0).setDuration(access$1100);
        MyLocationView.access$1700(this.this$0).addUpdateListener(new MyLocationView$MarkerCoordinateAnimatorListener(this.this$0, this, MyLocationView.access$700(this.this$0), latLng));
        MyLocationView.access$1700(this.this$0).start();
        MyLocationView.access$702(this.this$0, latLng);
    }

    void invalidate() {
        if (MyLocationView.access$700(this.this$0) != null) {
            MyLocationView.access$1502(this.this$0, MyLocationView.access$1900(this.this$0).toScreenLocation(MyLocationView.access$700(this.this$0)));
        }
        this.this$0.invalidate();
    }
}
