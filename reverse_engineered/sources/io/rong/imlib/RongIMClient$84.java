package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.RemoteModelWrap;

class RongIMClient$84 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$keywords;
    final /* synthetic */ RongIMClient$SearchType val$searchType;

    /* renamed from: io.rong.imlib.RongIMClient$84$1 */
    class C53171 extends Stub {
        C53171() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$84.this.val$callback != null && remoteModelWrap != null && remoteModelWrap.getContent() != null && (remoteModelWrap.getContent() instanceof PublicServiceProfileList)) {
                RongIMClient$84.this.val$callback.onCallback((PublicServiceProfileList) remoteModelWrap.getContent());
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$84.this.val$callback != null) {
                RongIMClient$84.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$84(RongIMClient rongIMClient, ResultCallback resultCallback, String str, RongIMClient$SearchType rongIMClient$SearchType) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$keywords = str;
        this.val$searchType = rongIMClient$SearchType;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).searchPublicService(this.val$keywords, 0, this.val$searchType.getValue(), new C53171());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
