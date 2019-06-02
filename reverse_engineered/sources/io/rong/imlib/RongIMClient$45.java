package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;

class RongIMClient$45 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ Conversation val$conversation;

    RongIMClient$45(RongIMClient rongIMClient, ResultCallback resultCallback, Conversation conversation) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversation = conversation;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean clearTextMessageDraft = RongIMClient.access$400(this.this$0).clearTextMessageDraft(this.val$conversation);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(clearTextMessageDraft));
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
