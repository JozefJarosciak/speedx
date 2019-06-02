package io.rong.imlib;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.IConnectionStatusListener.Stub;

class RongIMClient$StatusListener extends Stub {
    final /* synthetic */ RongIMClient this$0;

    RongIMClient$StatusListener(RongIMClient rongIMClient) {
        this.this$0 = rongIMClient;
    }

    public void onChanged(int i) throws RemoteException {
        RLog.m19419d("RongIMClient", "onChanged cur = " + RongIMClient.access$800(this.this$0) + ", to = " + i);
        if (RongIMClient.access$800(this.this$0) != RongIMClient$ConnectionStatusListener$ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT && !RongIMClient.access$800(this.this$0).equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTING)) {
            onStatusChange((RongIMClient$ConnectionStatusListener$ConnectionStatus) RongIMClient.access$2000().get(Integer.valueOf(i)));
        }
    }

    void onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus) {
        RLog.m19419d("RongIMClient", "onStatusChange : cur = " + RongIMClient.access$800(this.this$0) + ", to = " + rongIMClient$ConnectionStatusListener$ConnectionStatus + ", retry = " + RongIMClient.access$100(this.this$0));
        if (RongIMClient.access$700(RongIMClient$SingletonHolder.sInstance) == null) {
            RLog.m19420e("RongIMClient", "onStatusChange Token is null!");
        } else if (rongIMClient$ConnectionStatusListener$ConnectionStatus == null) {
            RLog.m19420e("RongIMClient", "onStatusChange Unknown error!");
        } else {
            if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                RongIMClient.access$702(RongIMClient$SingletonHolder.sInstance, null);
            }
            if (RongIMClient.access$2400() != null && (!RongIMClient.access$800(this.this$0).equals(rongIMClient$ConnectionStatusListener$ConnectionStatus) || rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED))) {
                RongIMClient.access$2400().onChanged(rongIMClient$ConnectionStatusListener$ConnectionStatus);
            }
            if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                Object[] toArray = RongIMClient.access$2500(this.this$0).toArray();
                RLog.m19419d("RongIMClient", "reJoinChatRoom, size = " + RongIMClient.access$2500(this.this$0).size());
                for (int i = 0; i < RongIMClient.access$2500(this.this$0).size(); i++) {
                    RongIMClient.access$2600(RongIMClient$SingletonHolder.sInstance, (String) toArray[i], 0, null);
                }
                if (RongIMClient.access$300(this.this$0) != null) {
                    RongIMClient.access$1600().removeCallbacks(RongIMClient.access$300(this.this$0));
                    RongIMClient.access$302(this.this$0, null);
                }
            }
            if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE) && RongIMClient.access$100(this.this$0) < RongIMClient.access$2700(this.this$0).length && RongIMClient.access$800(this.this$0) != rongIMClient$ConnectionStatusListener$ConnectionStatus) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) RongIMClient.access$200(this.this$0).getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    RLog.m19420e("RongIMClient", "onStatusChange, network unavailable.");
                } else {
                    RLog.m19419d("RongIMClient", "onStatusChange, Will reconnect after " + (RongIMClient.access$2700(this.this$0)[RongIMClient.access$100(this.this$0)] * 1000));
                    RongIMClient.access$302(this.this$0, new RongIMClient$ReconnectRunnable(this.this$0));
                    RongIMClient.access$1600().postDelayed(RongIMClient.access$300(this.this$0), (long) (RongIMClient.access$2700(this.this$0)[RongIMClient.access$100(this.this$0)] * 1000));
                }
            }
            RongIMClient.access$802(this.this$0, rongIMClient$ConnectionStatusListener$ConnectionStatus);
        }
    }
}
