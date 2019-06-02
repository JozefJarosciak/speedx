package com.mapbox.mapboxsdk.telemetry;

import android.os.Handler;
import android.util.Log;
import com.mapbox.mapboxsdk.location.LocationServices;

class MapboxEventManager$1 implements Runnable {
    final /* synthetic */ MapboxEventManager this$0;
    final /* synthetic */ Handler val$permsHandler;

    MapboxEventManager$1(MapboxEventManager mapboxEventManager, Handler handler) {
        this.this$0 = mapboxEventManager;
        this.val$permsHandler = handler;
    }

    public void run() {
        if (LocationServices.getLocationServices(MapboxEventManager.access$000(this.this$0)).areLocationPermissionsGranted()) {
            Log.i("MapboxEventManager", "Permissions finally granted, so starting Ambient if GPS isn't already enabled");
            if (LocationServices.getLocationServices(MapboxEventManager.access$000(this.this$0)).isGPSEnabled()) {
                LocationServices.getLocationServices(MapboxEventManager.access$000(this.this$0)).toggleGPS(false);
                return;
            }
            return;
        }
        Log.i("MapboxEventManager", "Permissions not granted yet... let's try again in 30 seconds");
        this.val$permsHandler.postDelayed(this, 30000);
    }
}
