package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$17 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType[] val$conversationTypes;

    RongIMClient$17(RongIMClient rongIMClient, ResultCallback resultCallback, ConversationType[] conversationTypeArr) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$conversationTypes = conversationTypeArr;
    }

    public void run() {
        int i = 0;
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                int[] iArr = new int[this.val$conversationTypes.length];
                ConversationType[] conversationTypeArr = this.val$conversationTypes;
                int length = conversationTypeArr.length;
                int i2 = 0;
                while (i < length) {
                    iArr[i2] = conversationTypeArr[i].getValue();
                    i2++;
                    i++;
                }
                i = RongIMClient.access$400(this.this$0).getUnreadCount(iArr);
                if (this.val$callback != null) {
                    this.val$callback.onCallback(Integer.valueOf(i));
                }
            } catch (RemoteException e) {
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
