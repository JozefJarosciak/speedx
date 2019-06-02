package com.mapbox.mapboxsdk.annotations;

import android.view.View;
import android.view.View.OnClickListener;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap$OnInfoWindowClickListener;

class InfoWindow$1 implements OnClickListener {
    final /* synthetic */ InfoWindow this$0;

    InfoWindow$1(InfoWindow infoWindow) {
        this.this$0 = infoWindow;
    }

    public void onClick(View view) {
        MapboxMap mapboxMap = (MapboxMap) InfoWindow.access$000(this.this$0).get();
        if (mapboxMap != null) {
            MapboxMap$OnInfoWindowClickListener onInfoWindowClickListener = mapboxMap.getOnInfoWindowClickListener();
            boolean z = false;
            if (onInfoWindowClickListener != null) {
                z = onInfoWindowClickListener.onInfoWindowClick(this.this$0.getBoundMarker());
            }
            if (!z) {
                this.this$0.close();
            }
        }
    }
}
