package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$31 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;

    RongIMClient$31(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            Conversation conversation = new Conversation();
            conversation.setConversationType(this.val$conversationType);
            conversation.setTargetId(this.val$targetId);
            try {
                boolean clearMessages = RongIMClient.access$400(this.this$0).clearMessages(conversation);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(clearMessages));
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
