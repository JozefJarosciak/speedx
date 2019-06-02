package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;

class RongIMClient$52 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int val$messageId;

    RongIMClient$52(RongIMClient rongIMClient, ResultCallback resultCallback, int i) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$messageId = i;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                Message message = RongIMClient.access$400(this.this$0).getMessage(this.val$messageId);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(message);
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
