package io.rong.push.platform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.avos.avoscloud.AVStatus;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import io.rong.push.PushConst;
import io.rong.push.PushService;
import io.rong.push.common.RLog;
import java.util.List;

public class MiMessageReceiver extends PushMessageReceiver {
    private static final String TAG = "MiMessageReceiver";
    private String mRegId;

    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        RLog.v(TAG, "onReceivePassThroughMessage is called. " + miPushMessage.toString());
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        RLog.v(TAG, "onNotificationMessageClicked is called. " + miPushMessage.toString());
        Intent intent = new Intent();
        intent.setAction(PushConst.ACTION_MI_PUSH_MESSAGE_CLICKED);
        intent.setPackage(context.getPackageName());
        intent.putExtra(AVStatus.MESSAGE_TAG, miPushMessage);
        context.sendBroadcast(intent);
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        RLog.v(TAG, "onNotificationMessageArrived is called. " + miPushMessage.toString());
        Intent intent = new Intent();
        intent.setAction(PushConst.ACTION_MI_PUSH_MESSAGE_ARRIVED);
        intent.setPackage(context.getPackageName());
        intent.putExtra(AVStatus.MESSAGE_TAG, miPushMessage);
        context.sendBroadcast(intent);
    }

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        RLog.v(TAG, "onCommandResult is called. " + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if (!"register".equals(command)) {
            return;
        }
        if (miPushCommandMessage.getResultCode() == 0) {
            this.mRegId = str;
            SharedPreferences sharedPreferences = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
            Editor edit = sharedPreferences.edit();
            if (!sharedPreferences.getString("pushType", "").equals("MI")) {
                RLog.d(TAG, "write to cache.");
                edit.putString("pushType", "MI");
                edit.putString("token", this.mRegId);
            } else if (!sharedPreferences.getString("token", "").equals(this.mRegId)) {
                edit.putString("token", this.mRegId);
            } else {
                return;
            }
            edit.apply();
            RLog.e(TAG, "send to pushService.");
            try {
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(PushConst.ACTION_SEND_REGISTRATION_INFO);
                intent.putExtra("regInfo", "MI|" + this.mRegId);
                context.startService(intent);
                return;
            } catch (SecurityException e) {
                RLog.e(TAG, "SecurityException. Failed to send token to PushService.");
                return;
            }
        }
        RLog.e(TAG, "Failed to get register id from MI." + miPushCommandMessage.getResultCode());
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        RLog.v(TAG, "onReceiveRegisterResult is called. " + miPushCommandMessage.toString());
    }
}
