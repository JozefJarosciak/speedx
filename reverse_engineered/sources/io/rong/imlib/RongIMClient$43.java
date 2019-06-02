package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;

class RongIMClient$43 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$content;
    final /* synthetic */ Conversation val$conversation;

    RongIMClient$43(RongIMClient rongIMClient, ResultCallback resultCallback, Conversation conversation, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversation = conversation;
        this.val$content = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                boolean saveTextMessageDraft = RongIMClient.access$400(this.this$0).saveTextMessageDraft(this.val$conversation, this.val$content);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Boolean.valueOf(saveTextMessageDraft));
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
