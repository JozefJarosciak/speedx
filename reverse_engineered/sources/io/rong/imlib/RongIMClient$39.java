package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message$SentStatus;

class RongIMClient$39 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int val$messageId;
    final /* synthetic */ Message$SentStatus val$sentStatus;

    RongIMClient$39(RongIMClient rongIMClient, ResultCallback resultCallback, int i, Message$SentStatus message$SentStatus) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$messageId = i;
        this.val$sentStatus = message$SentStatus;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean messageSentStatus = RongIMClient.access$400(this.this$0).setMessageSentStatus(this.val$messageId, this.val$sentStatus.getValue());
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(messageSentStatus));
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
