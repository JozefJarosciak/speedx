package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;
import io.rong.imlib.model.Conversation.PublicServiceType;

class RongIMClient$86 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$publicServiceId;
    final /* synthetic */ PublicServiceType val$publicServiceType;

    /* renamed from: io.rong.imlib.RongIMClient$86$1 */
    class C53191 extends Stub {
        C53191() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$86.this.val$callback != null) {
                RongIMClient$86.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$86.this.val$callback != null) {
                RongIMClient$86.this.val$callback.onError(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$86(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, PublicServiceType publicServiceType) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$publicServiceId = str;
        this.val$publicServiceType = publicServiceType;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).subscribePublicService(this.val$publicServiceId, this.val$publicServiceType.getValue(), true, new C53191());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
