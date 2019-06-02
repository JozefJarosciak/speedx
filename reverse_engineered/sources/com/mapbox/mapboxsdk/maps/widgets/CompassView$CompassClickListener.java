package com.mapbox.mapboxsdk.maps.widgets;

import android.view.View;
import android.view.View.OnClickListener;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import java.lang.ref.WeakReference;

class CompassView$CompassClickListener implements OnClickListener {
    private WeakReference<CompassView> compassView;
    private WeakReference<MapboxMap> mapboxMap;

    CompassView$CompassClickListener(MapboxMap mapboxMap, CompassView compassView) {
        this.mapboxMap = new WeakReference(mapboxMap);
        this.compassView = new WeakReference(compassView);
    }

    public void onClick(View view) {
        MapboxMap mapboxMap = (MapboxMap) this.mapboxMap.get();
        CompassView compassView = (CompassView) this.compassView.get();
        if (mapboxMap != null && compassView != null) {
            mapboxMap.resetNorth();
            compassView.postDelayed(compassView, 650);
        }
    }
}
