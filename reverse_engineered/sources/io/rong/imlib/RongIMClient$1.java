package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.IStringCallback.Stub;
import io.rong.imlib.RongIMClient.ConnectCallback;

class RongIMClient$1 extends Stub {
    final /* synthetic */ ConnectCallback val$callback;

    RongIMClient$1(ConnectCallback connectCallback) {
        this.val$callback = connectCallback;
    }

    public void onComplete(String str) throws RemoteException {
        RLog.m19419d("RongIMClient", "connect callback onComplete");
        RongIMClient.access$500(RongIMClient$SingletonHolder.sInstance).onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED);
        RongIMClient.access$1802(RongIMClient$SingletonHolder.sInstance, str);
        RongIMClient.access$102(RongIMClient$SingletonHolder.sInstance, 0);
        RongIMClient.access$902(RongIMClient$SingletonHolder.sInstance, true);
        RongIMClient.access$200(RongIMClient$SingletonHolder.sInstance).getSharedPreferences("Statistics", 0).edit().putString("userId", str).apply();
        if (RongIMClient.access$300(RongIMClient$SingletonHolder.sInstance) != null) {
            RongIMClient.access$1600().removeCallbacks(RongIMClient.access$300(RongIMClient$SingletonHolder.sInstance));
            RongIMClient.access$302(RongIMClient$SingletonHolder.sInstance, null);
        }
        if (RongIMClient.access$600(RongIMClient$SingletonHolder.sInstance) != null) {
            RongIMClient.access$1900(RongIMClient$SingletonHolder.sInstance).post(RongIMClient.access$600(RongIMClient$SingletonHolder.sInstance));
        }
        if (this.val$callback != null) {
            this.val$callback.onCallback(str);
        }
    }

    public void onFailure(int i) throws RemoteException {
        RLog.m19419d("RongIMClient", "connect callback : onFailure = " + i);
        RongIMClient.access$500(RongIMClient$SingletonHolder.sInstance).onStatusChange((RongIMClient$ConnectionStatusListener$ConnectionStatus) RongIMClient.access$2000().get(Integer.valueOf(i)));
        if (RongIMClient.access$600(RongIMClient$SingletonHolder.sInstance) != null) {
            RongIMClient.access$602(RongIMClient$SingletonHolder.sInstance, null);
        }
        if (i == RongIMClient$ErrorCode.RC_CONN_USER_OR_PASSWD_ERROR.getValue()) {
            if (this.val$callback != null) {
                this.val$callback.onTokenIncorrect();
            }
            RongIMClient.access$702(RongIMClient$SingletonHolder.sInstance, null);
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
        }
    }
}
