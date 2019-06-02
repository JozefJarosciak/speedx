package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message$ReceivedStatus;

class RongIMClient$37 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int val$messageId;
    final /* synthetic */ Message$ReceivedStatus val$receivedStatus;

    RongIMClient$37(RongIMClient rongIMClient, ResultCallback resultCallback, int i, Message$ReceivedStatus message$ReceivedStatus) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$messageId = i;
        this.val$receivedStatus = message$ReceivedStatus;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean messageReceivedStatus = RongIMClient.access$400(this.this$0).setMessageReceivedStatus(this.val$messageId, this.val$receivedStatus.getFlag());
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(messageReceivedStatus));
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
