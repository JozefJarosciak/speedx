package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$96 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$uid;

    RongIMClient$96(RongIMClient rongIMClient, ResultCallback resultCallback, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$uid = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                if (this.val$callback != null) {
                    this.val$callback.onCallback(RongIMClient.access$400(this.this$0).getMessageByUid(this.val$uid));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
