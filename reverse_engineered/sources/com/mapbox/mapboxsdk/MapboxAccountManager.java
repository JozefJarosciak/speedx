package com.mapbox.mapboxsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.exceptions.InvalidAccessTokenException;
import com.mapbox.mapboxsdk.exceptions.MapboxAccountManagerNotStartedException;
import com.mapbox.mapboxsdk.net.ConnectivityReceiver;
import com.mapbox.mapboxsdk.telemetry.MapboxEventManager;

public class MapboxAccountManager {
    private static MapboxAccountManager mapboxAccountManager = null;
    private final String accessToken;
    private final Context applicationContext;
    private Boolean connected = null;

    private MapboxAccountManager(Context context, String str) {
        this.applicationContext = context.getApplicationContext();
        this.accessToken = str;
    }

    public static MapboxAccountManager start(Context context, String str) {
        if (mapboxAccountManager == null) {
            mapboxAccountManager = new MapboxAccountManager(context, str);
            MapboxEventManager.getMapboxEventManager().initialize(context, str);
            ConnectivityReceiver.instance(context);
        }
        return mapboxAccountManager;
    }

    public static MapboxAccountManager getInstance() {
        if (mapboxAccountManager != null) {
            return mapboxAccountManager;
        }
        throw new MapboxAccountManagerNotStartedException();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public static void validateAccessToken(String str) throws InvalidAccessTokenException {
        if (TextUtils.isEmpty(str) || !(str.toLowerCase(MapboxConstants.MAPBOX_LOCALE).startsWith("pk.") || str.toLowerCase(MapboxConstants.MAPBOX_LOCALE).startsWith("sk."))) {
            throw new InvalidAccessTokenException();
        }
    }

    public void setConnected(Boolean bool) {
        this.connected = bool;
    }

    public Boolean isConnected() {
        if (this.connected != null) {
            return this.connected;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.applicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return Boolean.valueOf(z);
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }
}
