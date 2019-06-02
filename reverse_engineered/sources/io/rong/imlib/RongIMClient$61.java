package io.rong.imlib;

import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;

class RongIMClient$61 extends RongIMClient$UploadMediaCallback {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$SendImageMessageCallback val$callback;
    final /* synthetic */ String val$pushContent;
    final /* synthetic */ String val$pushData;

    /* renamed from: io.rong.imlib.RongIMClient$61$1 */
    class C52991 implements ISendMessageCallback {
        C52991() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
            if (RongIMClient$61.this.val$callback != null) {
                RongIMClient$61.this.val$callback.onSuccess(message);
            }
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            if (RongIMClient$61.this.val$callback != null) {
                RongIMClient$61.this.val$callback.onError(message, rongIMClient$ErrorCode);
            }
        }
    }

    RongIMClient$61(RongIMClient rongIMClient, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback, String str, String str2) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$SendImageMessageCallback;
        this.val$pushContent = str;
        this.val$pushData = str2;
    }

    public void onProgress(Message message, int i) {
        if (this.val$callback != null) {
            this.val$callback.onProgressCallback(message, i);
        }
    }

    public void onSuccess(Message message) {
        this.this$0.sendMessage(message, this.val$pushContent, this.val$pushData, new C52991());
    }

    public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        message.setSentStatus(Message$SentStatus.FAILED);
        this.this$0.setMessageSentStatus(message.getMessageId(), Message$SentStatus.FAILED, null);
        if (this.val$callback != null) {
            this.val$callback.onFail(message, rongIMClient$ErrorCode);
        }
    }
}
