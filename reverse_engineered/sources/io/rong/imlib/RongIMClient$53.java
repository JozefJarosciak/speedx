package io.rong.imlib;

import android.os.RemoteException;
import android.text.TextUtils;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

class RongIMClient$53 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ MessageContent val$content;
    final /* synthetic */ ResultCallback val$resultCallback;
    final /* synthetic */ String val$senderUserId;
    final /* synthetic */ String val$targetId;
    final /* synthetic */ ConversationType val$type;

    RongIMClient$53(RongIMClient rongIMClient, ResultCallback resultCallback, String str, ConversationType conversationType, MessageContent messageContent, String str2) {
        this.this$0 = rongIMClient;
        this.val$resultCallback = resultCallback;
        this.val$targetId = str;
        this.val$type = conversationType;
        this.val$content = messageContent;
        this.val$senderUserId = str2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            Message obtain = Message.obtain(this.val$targetId, this.val$type, this.val$content);
            if (TextUtils.isEmpty(this.val$senderUserId)) {
                obtain.setSenderUserId(RongIMClient.access$1800(this.this$0));
            } else {
                obtain.setSenderUserId(this.val$senderUserId);
            }
            try {
                obtain = RongIMClient.access$400(this.this$0).insertMessage(obtain);
                if (this.val$resultCallback != null) {
                    this.val$resultCallback.onCallback(obtain);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$resultCallback != null) {
                    this.val$resultCallback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$resultCallback != null) {
            this.val$resultCallback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
