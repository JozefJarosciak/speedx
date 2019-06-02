package io.rong.imlib;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import io.rong.common.RLog;
import io.rong.common.WakeLockUtils;

public class HeartbeatReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        RLog.m19419d("HeartbeatReceiver", "onReceive : " + intent);
        if (NativeClient.nativeObj != null) {
            WakeLockUtils.startNextHeartbeat(context);
            NativeClient.nativeObj.ping();
        }
    }

    private void sendReconnect(Context context) {
        Intent intent = new Intent(context, ConnectChangeReceiver.class);
        intent.setAction(ConnectChangeReceiver.RECONNECT_ACTION);
        intent.setPackage(context.getPackageName());
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        long elapsedRealtime = SystemClock.elapsedRealtime() + 1000;
        alarmManager.cancel(broadcast);
        alarmManager.set(2, elapsedRealtime, broadcast);
    }
}
