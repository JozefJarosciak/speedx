package com.mapbox.mapboxsdk.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.mapboxsdk.telemetry.TelemetryLocationReceiver;
import com.mapzen.p091a.p092a.p093a.C1486c;
import com.mapzen.p091a.p092a.p093a.C4209d;
import com.mapzen.p091a.p092a.p093a.C4210e;
import com.mapzen.p091a.p092a.p093a.C4212f;
import com.mapzen.p091a.p092a.p093a.C4212f.C4211a;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocationServices implements C1486c {
    private static final String TAG = "LocationServices";
    private static LocationServices instance;
    private Context context;
    private boolean isGPSEnabled;
    private Location lastLocation;
    private C4212f locationClient;
    private CopyOnWriteArrayList<LocationListener> locationListeners = new CopyOnWriteArrayList();

    private LocationServices(Context context) {
        this.context = context;
        this.locationClient = new C4211a(context).a();
    }

    public static LocationServices getLocationServices(@NonNull Context context) {
        if (instance == null) {
            instance = new LocationServices(context.getApplicationContext());
        }
        return instance;
    }

    public void toggleGPS(boolean z) {
        if (areLocationPermissionsGranted()) {
            if (this.locationClient.c()) {
                C4210e.f14846a.a(this);
                this.locationClient.b();
            }
            this.locationClient.a();
            Location a = C4210e.f14846a.a();
            if (a != null) {
                this.lastLocation = a;
            }
            if (z) {
                C4210e.f14846a.a(C4209d.a().a(1000).a(3.0f).a(100), this);
            } else {
                C4210e.f14846a.a(C4209d.a().a(1000).a(3.0f).a(105), this);
            }
            this.isGPSEnabled = z;
            return;
        }
        Log.w(TAG, "Location Permissions Not Granted Yet.  Try again after requesting.");
    }

    public boolean isGPSEnabled() {
        return this.isGPSEnabled;
    }

    public void onLocationChanged(Location location) {
        this.lastLocation = location;
        Iterator it = this.locationListeners.iterator();
        while (it.hasNext()) {
            ((LocationListener) it.next()).onLocationChanged(location);
        }
        Intent intent = new Intent(TelemetryLocationReceiver.INTENT_STRING);
        intent.putExtra(MapboxEvent.TYPE_LOCATION, location);
        this.context.sendBroadcast(intent);
    }

    public Location getLastLocation() {
        return this.lastLocation;
    }

    public void addLocationListener(@NonNull LocationListener locationListener) {
        if (!this.locationListeners.contains(locationListener)) {
            this.locationListeners.add(locationListener);
        }
    }

    public boolean removeLocationListener(@NonNull LocationListener locationListener) {
        return this.locationListeners.remove(locationListener);
    }

    public boolean areLocationPermissionsGranted() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            return true;
        }
        Log.w(TAG, "Location Permissions Not Granted Yet.  Try again after requesting.");
        return false;
    }
}
