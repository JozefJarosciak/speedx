package io.rong.imlib;

import android.content.Intent;
import io.rong.common.RLog;

class RongIMClient$ReconnectRunnable implements Runnable {
    final /* synthetic */ RongIMClient this$0;

    public RongIMClient$ReconnectRunnable(RongIMClient rongIMClient) {
        this.this$0 = rongIMClient;
    }

    public void run() {
        RLog.m19419d("RongIMClient", "ReconnectRunnable, count = " + RongIMClient.access$100(this.this$0));
        Intent intent = new Intent(RongIMClient.access$200(this.this$0), ConnectChangeReceiver.class);
        intent.setAction(ConnectChangeReceiver.RECONNECT_ACTION);
        RongIMClient.access$200(this.this$0).sendBroadcast(intent);
        RongIMClient.access$108(this.this$0);
        RongIMClient.access$302(this.this$0, null);
    }
}
