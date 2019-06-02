package io.rong.push.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.rong.push.common.RLog;
import io.rong.push.core.MessageHandleService;
import io.rong.push.core.MessageHandleService.Job;

public abstract class PushMessageReceiver extends BroadcastReceiver {
    private static final String TAG = "PushMessageReceiver";

    public abstract boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage);

    public abstract boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage);

    public final void onReceive(Context context, Intent intent) {
        MessageHandleService.addJob(new Job(intent, this));
        Intent intent2 = new Intent(context, MessageHandleService.class);
        RLog.d(TAG, "onReceive.action:" + intent.getAction());
        try {
            context.startService(intent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
