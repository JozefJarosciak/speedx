package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$35 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int val$messageId;
    final /* synthetic */ String val$value;

    RongIMClient$35(RongIMClient rongIMClient, ResultCallback resultCallback, int i, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$messageId = i;
        this.val$value = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean messageExtra = RongIMClient.access$400(this.this$0).setMessageExtra(this.val$messageId, this.val$value);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(messageExtra));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
