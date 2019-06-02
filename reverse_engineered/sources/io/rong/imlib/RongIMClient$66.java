package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.ILongCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$66 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;

    /* renamed from: io.rong.imlib.RongIMClient$66$1 */
    class C53021 extends Stub {
        C53021() {
        }

        public void onComplete(long j) throws RemoteException {
            if (RongIMClient$66.this.val$callback != null) {
                RongIMClient$66.this.val$callback.onCallback(ConversationNotificationStatus.setValue((int) j));
            }
        }

        public void onFailure(int i) throws RemoteException {
            RLog.m19422i("RongIMClient", "getConversationNotificationStatus-----------------ipc  onFailure--------errorCode:" + i);
            if (RongIMClient$66.this.val$callback != null) {
                RongIMClient$66.this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$66(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getConversationNotificationStatus(this.val$conversationType.getValue(), this.val$targetId, new C53021());
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
