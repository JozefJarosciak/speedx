package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$68 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ RongIMClient$DiscussionInviteStatus val$status;

    /* renamed from: io.rong.imlib.RongIMClient$68$1 */
    class C53041 extends Stub {
        C53041() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$68.this.val$callback != null) {
                RongIMClient$68.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$68.this.val$callback != null) {
                RongIMClient$68.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$68(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$discussionId = str;
        this.val$status = rongIMClient$DiscussionInviteStatus;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).setDiscussionInviteStatus(this.val$discussionId, this.val$status.getValue(), new C53041());
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
