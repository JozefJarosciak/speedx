package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IDownloadMediaCallback.Stub;
import io.rong.imlib.model.Conversation;

class RongIMClient$65 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$DownloadMediaCallback val$callback;
    final /* synthetic */ Conversation val$conversation;
    final /* synthetic */ String val$imageUrl;
    final /* synthetic */ RongIMClient$MediaType val$mediaType;

    /* renamed from: io.rong.imlib.RongIMClient$65$1 */
    class C53011 extends Stub {
        C53011() {
        }

        public void onComplete(String str) throws RemoteException {
            if (RongIMClient$65.this.val$callback != null) {
                RongIMClient$65.this.val$callback.onCallback(str);
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$65.this.val$callback != null) {
                RongIMClient$65.this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
            }
        }

        public void onProgress(int i) throws RemoteException {
            if (RongIMClient$65.this.val$callback != null) {
                RongIMClient$65.this.val$callback.onProgressCallback(i);
            }
        }
    }

    RongIMClient$65(RongIMClient rongIMClient, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback, Conversation conversation, RongIMClient$MediaType rongIMClient$MediaType, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$DownloadMediaCallback;
        this.val$conversation = conversation;
        this.val$mediaType = rongIMClient$MediaType;
        this.val$imageUrl = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).downloadMedia(this.val$conversation, this.val$mediaType.getValue(), this.val$imageUrl, new C53011());
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
