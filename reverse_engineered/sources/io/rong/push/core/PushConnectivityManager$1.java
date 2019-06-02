package io.rong.push.core;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import io.rong.push.PushConst;
import io.rong.push.common.RLog;
import io.rong.push.core.PushClient.ClientListener;
import io.rong.push.core.PushProtocalStack.PublishMessage;
import org.json.JSONException;

class PushConnectivityManager$1 implements ClientListener {
    final /* synthetic */ PushConnectivityManager this$0;
    final /* synthetic */ Context val$context;

    PushConnectivityManager$1(PushConnectivityManager pushConnectivityManager, Context context) {
        this.this$0 = pushConnectivityManager;
        this.val$context = context;
    }

    public void onMessageArrived(PublishMessage publishMessage) {
        if (publishMessage == null || publishMessage.getDataAsString() == null) {
            RLog.e("PushConnectivityManager", "sendNotification, msg = null");
            return;
        }
        RLog.i("PushConnectivityManager", publishMessage.getDataAsString());
        try {
            Bundle decode = PushUtils.decode(publishMessage.getDataAsString());
            String string = decode.getString("packageName");
            decode.remove("packageName");
            if (TextUtils.isEmpty(string)) {
                RLog.e("PushConnectivityManager", "messageArrived.packageName is null!!!!");
                return;
            }
            RLog.e("TAG", "new push message. packageName:" + string);
            Intent intent = new Intent();
            intent.setAction(PushConst.ACTION_RONG_PUSH_MESSAGE_ARRIVED);
            intent.setPackage(string);
            intent.putExtras(decode);
            if (VERSION.SDK_INT >= 12) {
                intent.setFlags(32);
            }
            this.val$context.sendBroadcast(intent);
        } catch (JSONException e) {
        }
    }

    public void onPingSuccess() {
        RLog.d("PushConnectivityManager", "onPingSuccess");
        this.this$0.getHandler().sendEmptyMessage(7);
    }

    public void onDisConnected() {
        RLog.d("PushConnectivityManager", "onDisConnected");
        this.this$0.getHandler().sendEmptyMessage(4);
    }

    public void onPingFailure() {
        RLog.d("PushConnectivityManager", "onPingFailure");
        this.this$0.getHandler().sendEmptyMessage(6);
    }
}
