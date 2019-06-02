package com.mapbox.mapboxsdk.maps;

import android.os.SystemClock;
import com.mapbox.mapboxsdk.maps.MapView.OnMapChangedListener;

class MapboxMap$MapChangeCameraPositionListener implements OnMapChangedListener {
    private static final long UPDATE_RATE_MS = 400;
    private long previousUpdateTimestamp;
    final /* synthetic */ MapboxMap this$0;

    private MapboxMap$MapChangeCameraPositionListener(MapboxMap mapboxMap) {
        this.this$0 = mapboxMap;
        this.previousUpdateTimestamp = 0;
    }

    public void onMapChanged(int i) {
        if (i >= 0 && i <= 4) {
            MapboxMap.access$402(this.this$0, true);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime >= this.previousUpdateTimestamp) {
                MapboxMap.access$100(this.this$0);
                this.previousUpdateTimestamp = elapsedRealtime + UPDATE_RATE_MS;
            }
        }
    }
}
