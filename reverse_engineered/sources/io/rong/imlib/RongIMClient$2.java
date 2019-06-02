package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import java.util.List;

class RongIMClient$2 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;

    RongIMClient$2(RongIMClient rongIMClient, ResultCallback resultCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                List conversationList = RongIMClient.access$400(this.this$0).getConversationList();
                if (this.val$callback != null) {
                    this.val$callback.onCallback(conversationList);
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
