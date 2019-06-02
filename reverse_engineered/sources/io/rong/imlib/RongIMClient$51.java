package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$51 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;

    /* renamed from: io.rong.imlib.RongIMClient$51$1 */
    class C52861 extends Stub {
        C52861() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$51.this.val$callback != null) {
                RongIMClient$51.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$51.this.val$callback != null) {
                RongIMClient$51.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$51(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$discussionId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).quitDiscussion(this.val$discussionId, new C52861());
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
