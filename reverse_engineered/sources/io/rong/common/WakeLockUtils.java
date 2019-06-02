package io.rong.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import io.rong.imlib.HeartbeatReceiver;

public class WakeLockUtils {
    private static final int HEARTBEAT_SPAN = 150000;
    private static final String TAG = "WakeLockUtils";

    public static void startNextHeartbeat(Context context) {
        RLog.m19419d(TAG, "startNextHeartbeat " + context.getPackageName());
        Intent intent = new Intent(context, HeartbeatReceiver.class);
        intent.setPackage(context.getPackageName());
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        long elapsedRealtime = SystemClock.elapsedRealtime() + 150000;
        alarmManager.cancel(broadcast);
        alarmManager.set(2, elapsedRealtime, broadcast);
    }

    public static void cancelHeartbeat(Context context) {
        RLog.m19419d(TAG, "cancelHeartbeat " + context.getPackageName());
        Intent intent = new Intent(context, HeartbeatReceiver.class);
        intent.setPackage(context.getPackageName());
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
    }
}
