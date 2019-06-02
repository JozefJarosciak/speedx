package io.rong.imlib;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.common.SystemUtils;
import io.rong.imlib.IHandler.Stub;

class RongIMClient$AidlConnection implements ServiceConnection {
    final /* synthetic */ RongIMClient this$0;

    RongIMClient$AidlConnection(RongIMClient rongIMClient) {
        this.this$0 = rongIMClient;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        RLog.m19419d("RongIMClient", "onServiceConnected mConnectionStatus = " + RongIMClient.access$800(this.this$0));
        RongIMClient.access$102(this.this$0, 0);
        RongIMClient.access$902(this.this$0, false);
        RongIMClient.access$402(this.this$0, Stub.asInterface(iBinder));
        try {
            if (!TextUtils.isEmpty(RongIMClient.access$1000())) {
                RongIMClient.access$400(this.this$0).setServerInfo(RongIMClient.access$1000(), RongIMClient.access$1100());
            }
            RongIMClient.access$400(this.this$0).setConnectionStatusListener(RongIMClient.access$500(this.this$0));
            ModuleManager.init(RongIMClient.access$200(this.this$0), RongIMClient.access$400(this.this$0));
            RongIMClient.access$1200(this.this$0);
            synchronized (RongIMClient.access$1300(this.this$0)) {
                for (String registerMessageType : RongIMClient.access$1300(this.this$0)) {
                    RongIMClient.access$400(this.this$0).registerMessageType(registerMessageType);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(ConnectChangeReceiver.RECONNECT_ACTION);
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        RongIMClient.access$200(this.this$0).registerReceiver(RongIMClient.access$1400(this.this$0), intentFilter);
        if (RongIMClient.access$1500(this.this$0) != null) {
            RongIMClient.access$1600().post(RongIMClient.access$1500(this.this$0));
        } else if (RongIMClient.access$700(this.this$0) != null) {
            this.this$0.reconnect(null);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        RongIMClient.access$402(this.this$0, null);
        RongIMClient.access$902(this.this$0, false);
        RLog.m19419d("RongIMClient", "onServiceDisconnected " + RongIMClient.access$800(this.this$0) + " -> DISCONNECTED");
        RongIMClient.access$500(this.this$0).onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        try {
            if (RongIMClient.access$1400(this.this$0) != null) {
                RongIMClient.access$200(this.this$0).unregisterReceiver(RongIMClient.access$1400(this.this$0));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (SystemUtils.isAppRunning(RongIMClient.access$200(this.this$0), RongIMClient.access$200(this.this$0).getPackageName())) {
            RLog.m19419d("RongIMClient", "onServiceDisconnected Main process is running.");
            RongIMClient.access$1700(this.this$0);
        }
    }
}
