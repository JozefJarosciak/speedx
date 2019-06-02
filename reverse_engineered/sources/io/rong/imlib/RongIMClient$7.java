package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$7 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$portrait;
    final /* synthetic */ String val$targetId;
    final /* synthetic */ String val$title;

    RongIMClient$7(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str, String str2, String str3) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$title = str2;
        this.val$portrait = str3;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean updateConversationInfo = RongIMClient.access$400(this.this$0).updateConversationInfo(this.val$conversationType.getValue(), this.val$targetId, this.val$title, this.val$portrait);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(updateConversationInfo));
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
