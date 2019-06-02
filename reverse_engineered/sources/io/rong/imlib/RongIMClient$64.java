package io.rong.imlib;

import android.net.Uri;
import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.IUploadCallback.Stub;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;

class RongIMClient$64 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$UploadMediaCallback val$callback;
    final /* synthetic */ Conversation val$conversation;
    final /* synthetic */ Uri val$finalUri;
    final /* synthetic */ RongIMClient$MediaType val$mediaType;
    final /* synthetic */ Message val$message;

    /* renamed from: io.rong.imlib.RongIMClient$64$1 */
    class C53001 extends Stub {
        C53001() {
        }

        public void onComplete(String str) throws RemoteException {
            RLog.m19422i("RongIMClient", "uploadMedia onComplete url = " + str);
            ((ImageMessage) RongIMClient$64.this.val$message.getContent()).setRemoteUri(Uri.parse(str));
            if (RongIMClient$64.this.val$callback != null) {
                RongIMClient$64.this.val$callback.onCallback(RongIMClient$64.this.val$message);
            }
        }

        public void onFailure(int i) throws RemoteException {
            RLog.m19420e("RongIMClient", "uploadMedia onFailure: " + i);
            if (RongIMClient$64.this.val$callback != null) {
                RongIMClient$64.this.val$callback.onFail(RongIMClient$64.this.val$message, RongIMClient$ErrorCode.valueOf(i));
            }
        }

        public void onProgress(int i) throws RemoteException {
            if (RongIMClient$64.this.val$callback != null) {
                RongIMClient$64.this.val$callback.onProgressCallback(RongIMClient$64.this.val$message, i);
            }
        }
    }

    RongIMClient$64(RongIMClient rongIMClient, RongIMClient$UploadMediaCallback rongIMClient$UploadMediaCallback, Conversation conversation, Uri uri, RongIMClient$MediaType rongIMClient$MediaType, Message message) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$UploadMediaCallback;
        this.val$conversation = conversation;
        this.val$finalUri = uri;
        this.val$mediaType = rongIMClient$MediaType;
        this.val$message = message;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).uploadMedia(this.val$conversation, this.val$finalUri.getPath(), this.val$mediaType.getValue(), new C53001());
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$callback != null) {
                    this.val$callback.onFail(this.val$message, RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
