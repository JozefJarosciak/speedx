package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$11 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$id;
    final /* synthetic */ boolean val$isTop;

    RongIMClient$11(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str, boolean z) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$id = str;
        this.val$isTop = z;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean conversationTopStatus = RongIMClient.access$400(this.this$0).setConversationTopStatus(this.val$conversationType.getValue(), this.val$id, this.val$isTop);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(conversationTopStatus));
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
