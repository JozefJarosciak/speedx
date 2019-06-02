package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.List;

class RongIMClient$4 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType[] val$conversationTypes;

    RongIMClient$4(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType[] conversationTypeArr) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationTypes = conversationTypeArr;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) == null) {
            if (this.val$callback != null) {
                this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        } else if (this.val$conversationTypes.length == 0) {
            this.val$callback.onCallback(null);
        } else {
            try {
                int[] iArr = new int[this.val$conversationTypes.length];
                for (int i = 0; i < this.val$conversationTypes.length; i++) {
                    iArr[i] = this.val$conversationTypes[i].getValue();
                }
                List conversationListByType = RongIMClient.access$400(this.this$0).getConversationListByType(iArr);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(conversationListByType);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        }
    }
}
