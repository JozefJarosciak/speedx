package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.RemoteModelWrap;

class RongIMClient$85 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ int[] val$businessType;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$keywords;
    final /* synthetic */ RongIMClient$SearchType val$searchType;

    /* renamed from: io.rong.imlib.RongIMClient$85$1 */
    class C53181 extends Stub {
        C53181() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$85.this.val$callback != null && remoteModelWrap != null && remoteModelWrap.getContent() != null && (remoteModelWrap.getContent() instanceof PublicServiceProfileList)) {
                RongIMClient$85.this.val$callback.onCallback((PublicServiceProfileList) remoteModelWrap.getContent());
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$85.this.val$callback != null) {
                RongIMClient$85.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$85(RongIMClient rongIMClient, ResultCallback resultCallback, String str, int[] iArr, RongIMClient$SearchType rongIMClient$SearchType) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$keywords = str;
        this.val$businessType = iArr;
        this.val$searchType = rongIMClient$SearchType;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).searchPublicService(this.val$keywords, this.val$businessType[0], this.val$searchType.getValue(), new C53181());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
