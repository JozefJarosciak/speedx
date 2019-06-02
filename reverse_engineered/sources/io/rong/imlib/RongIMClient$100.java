package io.rong.imlib;

import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.message.InformationNotificationMessage;

class RongIMClient$100 extends RongIMClient$SendMessageCallback {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ String val$kefuId;

    RongIMClient$100(RongIMClient rongIMClient, String str) {
        this.this$0 = rongIMClient;
        this.val$kefuId = str;
    }

    public void onError(Integer num, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        this.this$0.insertMessage(ConversationType.CUSTOMER_SERVICE, this.val$kefuId, "rong", InformationNotificationMessage.obtain("无人工在线"), null);
    }

    public void onSuccess(Integer num) {
    }
}
