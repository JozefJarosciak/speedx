package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongCommonDefine.GetMessageDirection;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.List;

class RongIMClient$24 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ int val$baseMessageId;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ int val$count;
    final /* synthetic */ GetMessageDirection val$direction;
    final /* synthetic */ String val$objectName;
    final /* synthetic */ String val$targetId;

    RongIMClient$24(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str, String str2, int i, int i2, GetMessageDirection getMessageDirection) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$objectName = str2;
        this.val$baseMessageId = i;
        this.val$count = i2;
        this.val$direction = getMessageDirection;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            Conversation conversation = new Conversation();
            conversation.setConversationType(this.val$conversationType);
            conversation.setTargetId(this.val$targetId);
            try {
                List olderMessagesByObjectName = RongIMClient.access$400(this.this$0).getOlderMessagesByObjectName(conversation, this.val$objectName, (long) this.val$baseMessageId, this.val$count, this.val$direction.equals(GetMessageDirection.FRONT));
                if (this.val$callback != null) {
                    this.val$callback.onCallback(olderMessagesByObjectName);
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
