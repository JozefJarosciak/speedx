package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;
import java.util.List;

class RongIMClient$49 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ List val$userIdList;

    /* renamed from: io.rong.imlib.RongIMClient$49$1 */
    class C52841 extends Stub {
        C52841() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$49.this.val$callback != null) {
                RongIMClient$49.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$49.this.val$callback != null) {
                RongIMClient$49.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$49(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, List list) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$discussionId = str;
        this.val$userIdList = list;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).addMemberToDiscussion(this.val$discussionId, this.val$userIdList, new C52841());
            } catch (RemoteException e) {
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
