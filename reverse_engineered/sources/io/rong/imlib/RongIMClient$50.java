package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$50 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ String val$userId;

    /* renamed from: io.rong.imlib.RongIMClient$50$1 */
    class C52851 extends Stub {
        C52851() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$50.this.val$callback != null) {
                RongIMClient$50.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$50.this.val$callback != null) {
                RongIMClient$50.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$50(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, String str2) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$discussionId = str;
        this.val$userId = str2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).removeDiscussionMember(this.val$discussionId, this.val$userId, new C52851());
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
