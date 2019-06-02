package io.rong.push.platform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.huawei.android.pushagent.api.PushEventReceiver;
import io.rong.push.PushConst;
import io.rong.push.PushService;
import io.rong.push.common.RLog;
import io.rong.push.core.PushUtils;
import org.json.JSONException;

public class HWReceiver extends PushEventReceiver {
    private final String TAG = "HWReceiver";

    public void onToken(Context context, String str, Bundle bundle) {
        RLog.d("HWReceiver", "获取token成功， token = " + str);
        "获取token和belongId成功，token = " + str + ",belongId = " + bundle.getString("belongId");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
        Editor edit = sharedPreferences.edit();
        if (sharedPreferences.getString("pushType", "").equals("HW") && !sharedPreferences.getString("token", "").equals(str)) {
            edit.putString("token", str);
            edit.apply();
            RLog.e("HWReceiver", "send to pushService.");
            try {
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(PushConst.ACTION_SEND_REGISTRATION_INFO);
                intent.putExtra("regInfo", "HW|" + str);
                context.startService(intent);
            } catch (SecurityException e) {
                RLog.e("HWReceiver", "SecurityException. Failed to send token to PushService.");
            }
        }
    }

    public boolean onPushMsg(Context context, byte[] bArr, Bundle bundle) {
        String str;
        String str2 = "";
        RLog.d("HWReceiver", "onPushMsg");
        try {
            str = new String(bArr, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            str = str2;
        }
        RLog.d("HWReceiver", "onPushMsg.message content:" + str);
        Intent intent = new Intent();
        intent.setAction(PushConst.ACTION_RONG_PUSH_MESSAGE_ARRIVED);
        try {
            Bundle decode = PushUtils.decode(str);
            intent.setPackage(context.getPackageName());
            intent.putExtras(decode);
            context.sendBroadcast(intent);
        } catch (JSONException e2) {
        }
        return false;
    }
}
