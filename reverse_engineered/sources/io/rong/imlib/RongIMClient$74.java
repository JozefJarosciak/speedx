package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$74 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$chatroomId;
    final /* synthetic */ int val$defMessageCount;

    /* renamed from: io.rong.imlib.RongIMClient$74$1 */
    class C53091 extends Stub {
        C53091() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$74.this.val$callback != null) {
                RongIMClient$74.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$74.this.val$callback != null) {
                RongIMClient$74.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$74(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, int i) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$chatroomId = str;
        this.val$defMessageCount = i;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).joinChatRoom(this.val$chatroomId, this.val$defMessageCount, new C53091());
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
