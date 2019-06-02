package io.rong.push;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.IBinder;
import android.text.TextUtils;
import io.rong.imlib.common.RongLibConst;
import io.rong.push.common.RLog;
import io.rong.push.core.PushConnectivityManager;
import io.rong.push.core.PushConnectivityManager.NetworkType;

public class PushService extends Service {
    private static final String TAG = "PushService";

    public void onCreate() {
        super.onCreate();
        RLog.m8380d(TAG, "OnCreate");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        State state = null;
        SharedPreferences sharedPreferences = getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0);
        Object string = sharedPreferences.getString(RongLibConst.KEY_APPKEY, "");
        Object string2 = sharedPreferences.getString("deviceId", "");
        String string3 = sharedPreferences.getString("enabledPushTypes", "");
        Object string4 = sharedPreferences.getString("pushDomain", "");
        if (!TextUtils.isEmpty(string4)) {
            PushConnectivityManager.getInstance().setServerDomain(string4);
        }
        if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || PushConnectivityManager.getInstance().isInitialized())) {
            PushConnectivityManager.getInstance().init(this, string2, string, string3);
            PushConnectivityManager.getInstance().connect();
        }
        if (!(intent == null || intent.getAction() == null)) {
            RLog.m8380d(TAG, "onStartCommand, action = " + intent.getAction());
            String stringExtra;
            if (intent.getAction().equals(PushConst.ACTION_INIT_PUSH) && !PushConnectivityManager.getInstance().isInitialized()) {
                String stringExtra2 = intent.getStringExtra("deviceId");
                stringExtra = intent.getStringExtra("enabledPushTypes");
                String stringExtra3 = intent.getStringExtra(RongLibConst.KEY_APPKEY);
                Object stringExtra4 = intent.getStringExtra("pushDomain");
                if (!TextUtils.isEmpty(stringExtra4)) {
                    PushConnectivityManager.getInstance().setServerDomain(stringExtra4);
                }
                if (TextUtils.isEmpty(stringExtra3) || TextUtils.isEmpty(stringExtra2)) {
                    RLog.m8381e(TAG, "appKey or deviceId is null.");
                } else {
                    sharedPreferences.edit().putString("deviceId", stringExtra2).apply();
                    sharedPreferences.edit().putString(RongLibConst.KEY_APPKEY, stringExtra3).apply();
                    sharedPreferences.edit().putString("enabledPushTypes", stringExtra).apply();
                    PushConnectivityManager.getInstance().init(this, stringExtra2, stringExtra3, stringExtra);
                    PushConnectivityManager.getInstance().connect();
                }
            } else if (intent.getAction().equals(PushConst.ACTION_SEND_REGISTRATION_INFO)) {
                r0 = intent.getStringExtra("regInfo");
                String[] split = r0.split("\\|");
                stringExtra = getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).getString("pushTypeUsed", "");
                RLog.m8382i(TAG, "received info:" + r0 + ",pushType cached:" + stringExtra);
                if (split[0].equals(stringExtra)) {
                    PushConnectivityManager.getInstance().sendRegistrationIDToServer(r0);
                } else {
                    RLog.m8381e(TAG, "Push type received is different from the one cached. So ignore this event.");
                }
            } else if (intent.getAction().equals(PushConst.ACTION_HEARTBEAT)) {
                r0 = intent.getStringExtra(PushConst.PING_STRING_EXTRA);
                if (r0 == null) {
                    PushConnectivityManager.getInstance().ping();
                } else if (r0.equals(PushConst.PING_STRING_EXTRA)) {
                    PushConnectivityManager.getInstance().onPingTimeout();
                }
            } else if (intent.getAction().equals(PushConst.ACTION_STOP_PUSH)) {
                PushConnectivityManager.getInstance().disconnect();
            } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                State state2;
                NetworkType networkType = PushConnectivityManager.getInstance().getNetworkType();
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
                if (connectivityManager.getNetworkInfo(1) == null) {
                    state2 = null;
                } else {
                    state2 = connectivityManager.getNetworkInfo(1).getState();
                }
                if (connectivityManager.getNetworkInfo(0) != null) {
                    state = connectivityManager.getNetworkInfo(0).getState();
                }
                if (state2 != null && state2 == State.CONNECTED) {
                    PushConnectivityManager.getInstance().setNetworkType(NetworkType.WIFI);
                } else if (state == null || state != State.CONNECTED) {
                    PushConnectivityManager.getInstance().setNetworkType(NetworkType.ERROR);
                } else {
                    PushConnectivityManager.getInstance().setNetworkType(NetworkType.MOBILE);
                }
                NetworkType networkType2 = PushConnectivityManager.getInstance().getNetworkType();
                RLog.m8380d(TAG, "wifi = " + state2 + ", mobile = " + state + ", last = " + networkType + ", current = " + networkType2);
                if (networkType2.equals(NetworkType.ERROR)) {
                    PushConnectivityManager.getInstance().disconnect();
                } else if (networkType.equals(NetworkType.ERROR)) {
                    PushConnectivityManager.getInstance().connect();
                } else {
                    PushConnectivityManager.getInstance().disconnect();
                    PushConnectivityManager.getInstance().connect();
                }
            } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction()) || "android.intent.action.ACTION_POWER_CONNECTED".equals(intent.getAction()) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(intent.getAction()) || "android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                PushConnectivityManager.getInstance().connect();
            }
        }
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        RLog.m8380d(TAG, "onDestroy");
        super.onDestroy();
    }
}
