package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.List;

class RongIMClient$26 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ int val$count;
    final /* synthetic */ int val$oldestMessageId;
    final /* synthetic */ String val$targetId;

    RongIMClient$26(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str, int i, int i2) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$oldestMessageId = i;
        this.val$count = i2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            Conversation conversation = new Conversation();
            conversation.setConversationType(this.val$conversationType);
            conversation.setTargetId(this.val$targetId);
            try {
                List olderMessages = RongIMClient.access$400(this.this$0).getOlderMessages(conversation, (long) this.val$oldestMessageId, this.val$count);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(olderMessages);
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
