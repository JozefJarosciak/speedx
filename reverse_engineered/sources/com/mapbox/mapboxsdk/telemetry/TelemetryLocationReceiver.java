package com.mapbox.mapboxsdk.telemetry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

public class TelemetryLocationReceiver extends BroadcastReceiver {
    public static final String INTENT_STRING = "com.mapbox.mapboxsdk.telemetry.TelemetryLocationReceiver";
    private static final String TAG = "TelemLocationReceiver";

    public void onReceive(Context context, Intent intent) {
        Location location = (Location) intent.getExtras().get(MapboxEvent.TYPE_LOCATION);
        if (location != null) {
            MapboxEventManager.getMapboxEventManager().addLocationEvent(location);
        } else {
            Log.d(TAG, "location NOT received");
        }
    }
}
