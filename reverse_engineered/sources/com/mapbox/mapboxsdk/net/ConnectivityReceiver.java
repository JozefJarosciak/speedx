package com.mapbox.mapboxsdk.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static ConnectivityReceiver INSTANCE;
    private static final String TAG = ConnectivityReceiver.class.getSimpleName();
    private List<ConnectivityListener> listeners = new CopyOnWriteArrayList();

    public static synchronized ConnectivityReceiver instance(Context context) {
        ConnectivityReceiver connectivityReceiver;
        synchronized (ConnectivityReceiver.class) {
            if (INSTANCE == null) {
                INSTANCE = new ConnectivityReceiver();
                context.registerReceiver(INSTANCE, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                INSTANCE.addListener(new NativeConnectivityListener());
            }
            connectivityReceiver = INSTANCE;
        }
        return connectivityReceiver;
    }

    private ConnectivityReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        boolean isConnected = isConnected(context);
        Log.v(TAG, "Connected: " + isConnected);
        for (ConnectivityListener onNetworkStateChanged : this.listeners) {
            onNetworkStateChanged.onNetworkStateChanged(isConnected);
        }
    }

    public void addListener(@NonNull ConnectivityListener connectivityListener) {
        this.listeners.add(connectivityListener);
    }

    public void removeListener(@NonNull ConnectivityListener connectivityListener) {
        this.listeners.remove(connectivityListener);
    }

    public boolean isConnected(Context context) {
        Boolean isConnected = MapboxAccountManager.getInstance().isConnected();
        if (isConnected != null) {
            return isConnected.booleanValue();
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
