package io.rong.push.core;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.gms.iid.InstanceID;
import com.huawei.android.pushagent.PushManager;
import com.xiaomi.mipush.sdk.MiPushClient;
import io.rong.push.PushConst;
import io.rong.push.PushService;
import io.rong.push.common.RLog;
import java.io.IOException;

public class PushRegistrationService extends IntentService {
    private final String TAG = "PushRegistrationService";

    public PushRegistrationService() {
        super("PushRegistration");
    }

    protected void onHandleIntent(Intent intent) {
        String str = "";
        SharedPreferences sharedPreferences = getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
        Editor edit = sharedPreferences.edit();
        RLog.d("PushRegistrationService", "intent is:" + intent);
        if (intent != null) {
            str = intent.getStringExtra("pushType");
            if (str == null) {
                return;
            }
        }
        RLog.d("PushRegistrationService", "pushType is:" + str);
        if (str.equals("GCM")) {
            InstanceID instance = InstanceID.getInstance(this);
            RLog.i("PushRegistrationService", "before GCM Registration.SendId:" + getResources().getString(getResources().getIdentifier("gcm_defaultSenderId", "string", getPackageName())));
            try {
                str = instance.getToken(getResources().getString(getResources().getIdentifier("gcm_defaultSenderId", "string", getPackageName())), "GCM", null);
                if (!TextUtils.isEmpty(str)) {
                    edit.putString("token", str);
                    Intent intent2 = new Intent(this, PushService.class);
                    intent2.setAction(PushConst.ACTION_SEND_REGISTRATION_INFO);
                    intent2.putExtra("regInfo", "GCM|" + str);
                    startService(intent2);
                }
            } catch (IOException e) {
                RLog.e("PushRegistrationService", "Failed to get token from google service." + e);
                edit.putString("token", "");
            }
            edit.putString("pushType", "GCM");
            edit.apply();
        } else if (str.equals("MI")) {
            str = sharedPreferences.getString("MiAppKey", "");
            String string = sharedPreferences.getString("MiAppId", "");
            RLog.d("PushRegistrationService", "MiAppKey:" + str + ",MiAppId:" + string);
            MiPushClient.registerPush(this, string, str);
            edit.putString("pushType", "MI");
            edit.apply();
        } else if (str.equals("HW")) {
            PushManager.requestToken(this);
            edit.putString("pushType", "HW");
            edit.apply();
        }
    }
}
