package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.RemoteModelWrap;
import io.rong.imlib.model.RongListWrap;

class RongIMClient$25 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ int val$count;
    final /* synthetic */ long val$dateTime;
    final /* synthetic */ String val$targetId;

    /* renamed from: io.rong.imlib.RongIMClient$25$1 */
    class C52801 extends Stub {
        C52801() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$25.this.val$callback == null) {
                return;
            }
            if (remoteModelWrap == null || remoteModelWrap.getContent() == null || !(remoteModelWrap.getContent() instanceof RongListWrap)) {
                RongIMClient$25.this.val$callback.onCallback(null);
                return;
            }
            RongIMClient$25.this.val$callback.onCallback(((RongListWrap) remoteModelWrap.getContent()).getList());
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$25.this.val$callback != null) {
                RongIMClient$25.this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$25(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType conversationType, String str, long j, int i) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$dateTime = j;
        this.val$count = i;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            Conversation conversation = new Conversation();
            conversation.setConversationType(this.val$conversationType);
            conversation.setTargetId(this.val$targetId);
            try {
                RongIMClient.access$400(this.this$0).getRemoteHistoryMessages(conversation, this.val$dateTime, this.val$count, new C52801());
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
