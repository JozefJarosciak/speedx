package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$13 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;

    RongIMClient$13(RongIMClient rongIMClient, ResultCallback resultCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) == null) {
            if (this.val$callback != null) {
                this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        } else if (RongIMClient.access$900(this.this$0) || this.val$callback == null) {
            try {
                int totalUnreadCount = RongIMClient.access$400(this.this$0).getTotalUnreadCount();
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Integer.valueOf(totalUnreadCount));
                }
            } catch (RemoteException e) {
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else {
            RLog.m19419d("RongIMClient", "getTotalUnreadCount Has connect");
            this.val$callback.onCallback(Integer.valueOf(0));
        }
    }
}
