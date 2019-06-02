package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;

class RongIMClient$41 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ Conversation val$conversation;

    RongIMClient$41(RongIMClient rongIMClient, ResultCallback resultCallback, Conversation conversation) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversation = conversation;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                String textMessageDraft = RongIMClient.access$400(this.this$0).getTextMessageDraft(this.val$conversation);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(textMessageDraft);
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
