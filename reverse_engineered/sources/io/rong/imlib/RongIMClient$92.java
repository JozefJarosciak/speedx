package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$92 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;
    final /* synthetic */ long val$timestamp;

    RongIMClient$92(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, ConversationType conversationType, long j) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$targetId = str;
        this.val$conversationType = conversationType;
        this.val$timestamp = j;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                if (RongIMClient.access$400(this.this$0).updateMessageReceiptStatus(this.val$targetId, this.val$conversationType.getValue(), this.val$timestamp)) {
                    if (this.val$callback != null) {
                        this.val$callback.onSuccess();
                    }
                } else if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.UNKNOWN);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
