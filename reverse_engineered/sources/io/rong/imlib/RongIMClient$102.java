package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;
import io.rong.message.RecallNotificationMessage;

class RongIMClient$102 implements ISendMessageCallback {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ Message val$message;

    RongIMClient$102(RongIMClient rongIMClient, Message message, ResultCallback resultCallback) {
        this.this$0 = rongIMClient;
        this.val$message = message;
        this.val$callback = resultCallback;
    }

    public void onAttached(Message message) {
    }

    public void onSuccess(Message message) {
        RecallNotificationMessage recallNotificationMessage = new RecallNotificationMessage(this.val$message.getSenderUserId(), message.getSentTime(), this.val$message.getObjectName());
        try {
            RongIMClient.access$400(this.this$0).setMessageContent(this.val$message.getMessageId(), recallNotificationMessage.encode(), ((MessageTag) RecallNotificationMessage.class.getAnnotation(MessageTag.class)).value());
        } catch (RemoteException e) {
            e.printStackTrace();
            if (this.val$callback != null) {
                this.val$callback.onError(RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        }
        if (this.val$callback != null) {
            this.val$callback.onSuccess(recallNotificationMessage);
        }
    }

    public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
