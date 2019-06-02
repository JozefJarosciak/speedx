package com.mapbox.mapboxsdk.annotations;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap$OnInfoWindowLongClickListener;

class InfoWindow$2 implements OnLongClickListener {
    final /* synthetic */ InfoWindow this$0;

    InfoWindow$2(InfoWindow infoWindow) {
        this.this$0 = infoWindow;
    }

    public boolean onLongClick(View view) {
        MapboxMap mapboxMap = (MapboxMap) InfoWindow.access$000(this.this$0).get();
        if (mapboxMap != null) {
            MapboxMap$OnInfoWindowLongClickListener onInfoWindowLongClickListener = mapboxMap.getOnInfoWindowLongClickListener();
            if (onInfoWindowLongClickListener != null) {
                onInfoWindowLongClickListener.onInfoWindowLongClick(this.this$0.getBoundMarker());
            }
        }
        return true;
    }
}
