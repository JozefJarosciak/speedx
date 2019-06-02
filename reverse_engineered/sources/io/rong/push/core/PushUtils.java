package io.rong.push.core;

import android.os.Bundle;
import android.text.TextUtils;
import com.beastbikes.framework.ui.android.WebActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class PushUtils {
    public static Bundle decode(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle = new Bundle();
            bundle.putLong("receivedTime", jSONObject.optLong("timestamp"));
            bundle.putString("objectName", jSONObject.optString("objectName"));
            bundle.putString("senderId", jSONObject.optString("fromUserId"));
            bundle.putString("senderName", jSONObject.optString("fromUserName"));
            bundle.putString("senderUri", jSONObject.optString("fromUserPo"));
            bundle.putString("pushTitle", jSONObject.optString(WebActivity.EXTRA_TITLE));
            bundle.putString("pushContent", jSONObject.optString("content"));
            bundle.putString("pushData", jSONObject.optString("appData"));
            Object optString = jSONObject.optString("channelType");
            int i = 0;
            if (!TextUtils.isEmpty(optString)) {
                try {
                    i = Integer.parseInt(optString);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            bundle.putInt("conversationType", i);
            if (i == 2 || i == 3 || i == 4) {
                bundle.putString("targetId", jSONObject.optString("channelId"));
                bundle.putString("targetUserName", jSONObject.optString("channelName"));
            } else {
                bundle.putString("targetId", jSONObject.optString("fromUserId"));
                bundle.putString("targetUserName", jSONObject.optString("fromUserName"));
            }
            bundle.putString("packageName", jSONObject.optString("packageName"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("rc");
            bundle.putString("toId", jSONObject2.optString("tId"));
            bundle.putString("pushId", jSONObject2.optString("id"));
            if (jSONObject2.has("ext") && jSONObject2.getJSONObject("ext") != null) {
                bundle.putString("extra", jSONObject2.getJSONObject("ext").toString());
            }
            return bundle;
        } catch (JSONException e2) {
            e2.printStackTrace();
            throw new JSONException("decode failed!");
        }
    }
}
