package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$28 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int[] val$messageIds;

    RongIMClient$28(RongIMClient rongIMClient, ResultCallback resultCallback, int[] iArr) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$messageIds = iArr;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean deleteMessage = RongIMClient.access$400(this.this$0).deleteMessage(this.val$messageIds);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(deleteMessage));
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
