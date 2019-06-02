package com.mapbox.mapboxsdk.maps.widgets;

class MyLocationView$MyLocationBehaviorFactory {
    final /* synthetic */ MyLocationView this$0;

    private MyLocationView$MyLocationBehaviorFactory(MyLocationView myLocationView) {
        this.this$0 = myLocationView;
    }

    MyLocationView$MyLocationBehavior getBehavioralModel(int i) {
        if (i == 0) {
            return new MyLocationView$MyLocationShowBehavior(this.this$0, null);
        }
        return new MyLocationView$MyLocationTrackingBehavior(this.this$0, null);
    }
}
