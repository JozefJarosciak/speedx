package com.mapbox.mapboxsdk.maps.widgets;

import android.location.Location;
import com.mapbox.mapboxsdk.location.LocationListener;
import java.lang.ref.WeakReference;

class MyLocationView$GpsLocationListener implements LocationListener {
    private WeakReference<MyLocationView> userLocationView;

    MyLocationView$GpsLocationListener(MyLocationView myLocationView) {
        this.userLocationView = new WeakReference(myLocationView);
    }

    public void onLocationChanged(Location location) {
        MyLocationView myLocationView = (MyLocationView) this.userLocationView.get();
        if (myLocationView != null) {
            myLocationView.setLocation(location);
        }
    }
}
