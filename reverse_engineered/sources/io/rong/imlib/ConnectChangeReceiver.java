package io.rong.imlib;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import io.rong.common.RLog;
import io.rong.common.WakefulRongReceiver;

public class ConnectChangeReceiver extends WakefulRongReceiver {
    public static final String RECONNECT_ACTION = "action_reconnect";
    private static final String TAG = "ConnectChangeReceiver";
    static int sLastChannel = -1;

    public static void setLastConnectNetworkChannel(int i) {
        sLastChannel = i;
    }

    public void onReceive(Context context, Intent intent) {
        Exception e;
        RongIMClient$ConnectionStatusListener$ConnectionStatus currentConnectionStatus;
        if (intent != null && intent.getAction() != null) {
            NetworkInfo activeNetworkInfo;
            try {
                activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                try {
                    RLog.m19419d(TAG, "onReceive.network:" + (activeNetworkInfo != null ? Boolean.valueOf(activeNetworkInfo.isAvailable()) : "null") + ", intent:" + intent.toString());
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        if (activeNetworkInfo != null) {
                        }
                        return;
                    } else if (intent.getAction().equals(RECONNECT_ACTION)) {
                        if (activeNetworkInfo != null) {
                        }
                        return;
                    } else if (!intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                        currentConnectionStatus = RongIMClient.getInstance().getCurrentConnectionStatus();
                        RLog.m19419d(TAG, "ACTION_USER_PRESENT state = " + currentConnectionStatus);
                        if (currentConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE)) {
                            return;
                        }
                        return;
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                activeNetworkInfo = null;
                e = exception;
                e.printStackTrace();
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    if (intent.getAction().equals(RECONNECT_ACTION)) {
                        if (!intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                            currentConnectionStatus = RongIMClient.getInstance().getCurrentConnectionStatus();
                            RLog.m19419d(TAG, "ACTION_USER_PRESENT state = " + currentConnectionStatus);
                            if (currentConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE)) {
                                return;
                            }
                            return;
                        }
                    } else if (activeNetworkInfo != null) {
                        return;
                    }
                } else if (activeNetworkInfo != null) {
                    return;
                }
            }
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    sendReconnect(context);
                }
            } else if (intent.getAction().equals(RECONNECT_ACTION)) {
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    WakefulRongReceiver.startWakefulService(context, new Intent(context, ReConnectService.class));
                }
            } else if (!intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                currentConnectionStatus = RongIMClient.getInstance().getCurrentConnectionStatus();
                RLog.m19419d(TAG, "ACTION_USER_PRESENT state = " + currentConnectionStatus);
                if (currentConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE) && activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    sendReconnect(context);
                }
            }
        }
    }

    private final void sendReconnect(Context context) {
        Intent intent = new Intent(context, ConnectChangeReceiver.class);
        intent.setAction(RECONNECT_ACTION);
        intent.setPackage(context.getPackageName());
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        alarmManager.cancel(broadcast);
        alarmManager.set(2, elapsedRealtime, broadcast);
    }
}
