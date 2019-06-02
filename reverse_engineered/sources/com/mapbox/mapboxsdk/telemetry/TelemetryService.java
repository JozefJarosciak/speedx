package com.mapbox.mapboxsdk.telemetry;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TelemetryService extends Service {
    private static final String TAG = "TelemetryService";
    private TelemetryLocationReceiver telemetryLocationReceiver = null;

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate() called");
        IntentFilter intentFilter = new IntentFilter(TelemetryLocationReceiver.INTENT_STRING);
        this.telemetryLocationReceiver = new TelemetryLocationReceiver();
        registerReceiver(this.telemetryLocationReceiver, intentFilter);
    }

    public void onDestroy() {
        shutdownTelemetry();
        super.onDestroy();
    }

    public void onTaskRemoved(Intent intent) {
        shutdownTelemetry();
        super.onTaskRemoved(intent);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, "onStartCommand() called");
        return 2;
    }

    private void shutdownTelemetry() {
        MapboxEventManager.getMapboxEventManager().flushEventsQueueImmediately();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            Log.e(TAG, "Error while trying to sleep for 1 second: " + e);
        }
        try {
            unregisterReceiver(this.telemetryLocationReceiver);
        } catch (IllegalArgumentException e2) {
            Log.e(TAG, "Error when unregisterReceiver: " + e2);
        }
    }
}
