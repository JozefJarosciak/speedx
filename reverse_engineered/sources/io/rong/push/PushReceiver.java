package io.rong.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.rong.push.common.RLog;

public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "PushReceiver";

    public void onReceive(Context context, Intent intent) {
        RLog.m8380d(TAG, "onReceive intent = " + intent);
        if (intent == null || intent.getAction() == null) {
            RLog.m8381e(TAG, "intent or action is null.");
            return;
        }
        String string = context.getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).getString("pushTypeUsing", "");
        if (string.equals("GCM") || string.equals("MI") || string.equals("HW")) {
            RLog.m8380d(TAG, string + " is 3rd push type, doesn't handle");
            return;
        }
        try {
            Intent intent2 = new Intent(context, PushService.class);
            intent2.setAction(intent.getAction());
            intent2.putExtra(PushConst.PING_STRING_EXTRA, intent.getStringExtra(PushConst.PING_STRING_EXTRA));
            context.startService(intent2);
        } catch (SecurityException e) {
            RLog.m8381e(TAG, "SecurityException. Failed to start PushService.");
        }
    }
}
