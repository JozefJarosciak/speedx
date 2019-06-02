package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.ILongCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$67 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ ConversationNotificationStatus val$notificationStatus;
    final /* synthetic */ String val$targetId;

    /* renamed from: io.rong.imlib.RongIMClient$67$1 */
    class C53031 extends Stub {
        C53031() {
        }

        public void onComplete(long j) throws RemoteException {
            if (RongIMClient$67.this.val$callback != null) {
                RongIMClient$67.this.val$callback.onCallback(ConversationNotificationStatus.setValue((int) j));
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$67.this.val$callback != null) {
                RongIMClient$67.this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$67(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str, ConversationNotificationStatus conversationNotificationStatus) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$notificationStatus = conversationNotificationStatus;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).setConversationNotificationStatus(this.val$conversationType.getValue(), this.val$targetId, this.val$notificationStatus.getValue(), new C53031());
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
