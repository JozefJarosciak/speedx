package com.beastbikes.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import org.json.JSONException;
import org.json.JSONObject;

public class PushReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Object string = extras.getString(JPushInterface.EXTRA_EXTRA);
            Log.d("JPush", "[PushReceiver]  " + string);
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject;
                try {
                    jSONObject = new JSONObject(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONObject = null;
                }
                C1592b.a().a(jSONObject, context);
            }
        }
    }
}
