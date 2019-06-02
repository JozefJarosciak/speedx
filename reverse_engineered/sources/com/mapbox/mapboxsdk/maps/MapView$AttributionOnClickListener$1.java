package com.mapbox.mapboxsdk.maps;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.mapbox.mapboxsdk.maps.MapView.AttributionOnClickListener;
import com.mapbox.mapboxsdk.telemetry.MapboxEventManager;

class MapView$AttributionOnClickListener$1 implements OnClickListener {
    final /* synthetic */ AttributionOnClickListener this$0;

    MapView$AttributionOnClickListener$1(AttributionOnClickListener attributionOnClickListener) {
        this.this$0 = attributionOnClickListener;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        MapboxEventManager.getMapboxEventManager().setTelemetryEnabled(true);
        dialogInterface.cancel();
    }
}
