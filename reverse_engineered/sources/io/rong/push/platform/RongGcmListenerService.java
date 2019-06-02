package io.rong.push.platform;

import android.content.Intent;
import android.os.Bundle;
import com.avos.avoscloud.AVStatus;
import com.google.android.gms.gcm.GcmListenerService;
import io.rong.push.PushConst;
import io.rong.push.common.RLog;
import io.rong.push.core.PushUtils;
import org.json.JSONException;

public class RongGcmListenerService extends GcmListenerService {
    private static final String TAG = "RongGcmListenerService";

    public void onMessageReceived(String str, Bundle bundle) {
        RLog.d(TAG, "onMessageReceived");
        if (bundle != null) {
            String string = bundle.getString(AVStatus.MESSAGE_TAG);
            Intent intent = new Intent();
            intent.setAction(PushConst.ACTION_RONG_PUSH_MESSAGE_ARRIVED);
            intent.setPackage(getPackageName());
            try {
                intent.putExtras(PushUtils.decode(string));
                sendBroadcast(intent);
            } catch (JSONException e) {
            }
        }
    }
}
