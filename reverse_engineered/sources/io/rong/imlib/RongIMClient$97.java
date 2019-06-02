package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$97 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;

    RongIMClient$97(RongIMClient rongIMClient, ConversationType conversationType, String str) {
        this.this$0 = rongIMClient;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
    }

    public void run() {
        try {
            RongIMClient.access$400(this.this$0).quitRealTimeLocation(this.val$conversationType.getValue(), this.val$targetId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
