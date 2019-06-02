package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.RemoteModelWrap;

class RongIMClient$89 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;

    /* renamed from: io.rong.imlib.RongIMClient$89$1 */
    class C53221 extends Stub {
        C53221() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$89.this.val$callback != null) {
                RongIMClient$89.this.val$callback.onCallback((PublicServiceProfileList) remoteModelWrap.getContent());
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$89.this.val$callback != null) {
                RongIMClient$89.this.val$callback.onError(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$89(RongIMClient rongIMClient, ResultCallback resultCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getPublicServiceList(new C53221());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
