package io.rong.imlib;

import android.app.IntentService;
import android.content.Intent;
import io.rong.common.RLog;
import io.rong.common.WakefulRongReceiver;
import io.rong.imlib.RongIMClient.ConnectCallback;

public class ReConnectService extends IntentService {
    private static final String TAG = "ReConnectService";

    public ReConnectService() {
        super("RONG_ReConnect");
    }

    protected void onHandleIntent(final Intent intent) {
        if (intent == null) {
            return;
        }
        if (RongIMClient.getInstance() == null) {
            WakefulRongReceiver.completeWakefulIntent(intent);
            return;
        }
        RLog.m19419d(TAG, "RECONNECT " + intent.toString());
        RongIMClient.getInstance().reconnect(new ConnectCallback() {
            public void onSuccess(String str) {
                WakefulRongReceiver.completeWakefulIntent(intent);
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                WakefulRongReceiver.completeWakefulIntent(intent);
            }

            public void onTokenIncorrect() {
                WakefulRongReceiver.completeWakefulIntent(intent);
            }
        });
    }
}
