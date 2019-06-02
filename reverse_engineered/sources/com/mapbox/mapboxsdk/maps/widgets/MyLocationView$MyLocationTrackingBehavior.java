package com.mapbox.mapboxsdk.maps.widgets;

import android.graphics.PointF;
import android.location.Location;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;

class MyLocationView$MyLocationTrackingBehavior extends MyLocationView$MyLocationBehavior {
    final /* synthetic */ MyLocationView this$0;

    private MyLocationView$MyLocationTrackingBehavior(MyLocationView myLocationView) {
        this.this$0 = myLocationView;
        super(myLocationView);
    }

    void updateLatLng(@NonNull Location location) {
        float f;
        super.updateLatLng(location);
        if (MyLocationView.access$700(this.this$0) == null) {
            MyLocationView.access$702(this.this$0, new LatLng(location));
            MyLocationView.access$1102(this.this$0, SystemClock.elapsedRealtime());
        }
        float access$1100 = (float) MyLocationView.access$1100(this.this$0);
        MyLocationView.access$1102(this.this$0, SystemClock.elapsedRealtime());
        if (access$1100 == 0.0f) {
            f = 0.0f;
        } else {
            f = (((float) MyLocationView.access$1100(this.this$0)) - access$1100) * 1.1f;
        }
        MyLocationView.access$702(this.this$0, new LatLng(location));
        Builder target = new Builder().target(MyLocationView.access$700(this.this$0));
        if (MyLocationView.access$1200(this.this$0) == 8) {
            if (location.hasBearing()) {
                target.bearing((double) location.getBearing());
            }
            MyLocationView.access$100(this.this$0, 0.0f);
        }
        updateAccuracy(location);
        MyLocationView.access$300(this.this$0).easeCamera(CameraUpdateFactory.newCameraPosition(target.build()), (int) f, false, false, null);
    }

    void invalidate() {
        int[] padding = MyLocationView.access$300(this.this$0).getPadding();
        MyLocationView.access$1502(this.this$0, new PointF(((float) (((this.this$0.getWidth() + padding[0]) - padding[2]) / 2)) + MyLocationView.access$1300(this.this$0), ((float) ((padding[1] + (this.this$0.getHeight() - padding[3])) / 2)) + MyLocationView.access$1400(this.this$0)));
        this.this$0.invalidate();
    }
}
