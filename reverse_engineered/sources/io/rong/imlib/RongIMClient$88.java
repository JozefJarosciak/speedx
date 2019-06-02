package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.RemoteModelWrap;

class RongIMClient$88 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$publicServiceId;
    final /* synthetic */ PublicServiceType val$publicServiceType;

    /* renamed from: io.rong.imlib.RongIMClient$88$1 */
    class C53211 extends Stub {
        C53211() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$88.this.val$callback != null) {
                Object obj = null;
                if (remoteModelWrap != null) {
                    obj = (PublicServiceProfile) remoteModelWrap.getContent();
                }
                RongIMClient$88.this.val$callback.onCallback(obj);
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$88.this.val$callback != null) {
                RongIMClient$88.this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$88(RongIMClient rongIMClient, ResultCallback resultCallback, String str, PublicServiceType publicServiceType) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$publicServiceId = str;
        this.val$publicServiceType = publicServiceType;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getPublicServiceProfile(this.val$publicServiceId, this.val$publicServiceType.getValue(), new C53211());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
