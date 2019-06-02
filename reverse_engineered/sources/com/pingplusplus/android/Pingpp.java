package com.pingplusplus.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import com.pingplusplus.android.crypto.CryptoUtils;
import com.umeng.onlineconfig.OnlineConfigAgent;
import org.json.JSONException;
import org.json.JSONObject;

public class Pingpp {
    public static int REQUEST_CODE_PAYMENT = 1010;
    public static final String VERSION = "2.1.5";

    public static void createPayment(Activity activity, String str) {
        try {
            if ("wx".equals(new JSONObject(str).optString(OnlineConfigAgent.KEY_CHANNEL))) {
                String packageName = activity.getPackageName();
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity"));
                intent.putExtra(PaymentActivity.EXTRA_CHARGE, str);
                activity.startActivityForResult(intent, REQUEST_CODE_PAYMENT);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent2 = new Intent(activity, PaymentActivity.class);
        intent2.putExtra(PaymentActivity.EXTRA_CHARGE, str);
        activity.startActivityForResult(intent2, REQUEST_CODE_PAYMENT);
    }

    public static void createPayment(Activity activity, String str, String str2) {
        PingppObject.getInstance().qpayScheme = str2;
        createPayment(activity, str);
    }

    public static String decryptData(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? null : CryptoUtils.decryptData(str, str2);
    }

    public static String decryptData(String str, String str2, String str3) {
        return TextUtils.isEmpty(str3) ? null : CryptoUtils.decryptData(str, str2, str3);
    }

    public static void enableDebugLog(boolean z) {
        PingppLog.DEBUG = z;
    }

    public static String encryptData(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? null : CryptoUtils.encryptData(str, str2);
    }

    public static String encryptData(String str, String str2, String str3) {
        return TextUtils.isEmpty(str3) ? null : CryptoUtils.encryptData(str, str2, str3);
    }
}
