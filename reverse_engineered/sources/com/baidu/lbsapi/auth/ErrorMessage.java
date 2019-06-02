package com.baidu.lbsapi.auth;

import com.avos.avoscloud.AVStatus;
import org.json.JSONException;
import org.json.JSONObject;

class ErrorMessage {
    ErrorMessage() {
    }

    /* renamed from: a */
    static String m3568a(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
            jSONObject.put(AVStatus.MESSAGE_TAG, str);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    static String m3569a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", -1);
            jSONObject.put(AVStatus.MESSAGE_TAG, str);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }
}
