package io.rong.imlib;

import android.net.Uri;
import io.rong.common.RLog;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;

public class RongIMClient$UploadImageStatusListener {
    private RongIMClient$SendImageMessageWithUploadListenerCallback callback;
    private Message message;
    private String pushContent;
    private String pushData;
    final /* synthetic */ RongIMClient this$0;

    /* renamed from: io.rong.imlib.RongIMClient$UploadImageStatusListener$1 */
    class C53551 implements ISendMessageCallback {
        C53551() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
            if (RongIMClient$UploadImageStatusListener.this.callback != null) {
                RongIMClient$UploadImageStatusListener.this.callback.onSuccess(message);
            }
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            if (RongIMClient$UploadImageStatusListener.this.callback != null) {
                RongIMClient$UploadImageStatusListener.this.callback.onError(message, rongIMClient$ErrorCode);
            }
        }
    }

    public RongIMClient$UploadImageStatusListener(RongIMClient rongIMClient, Message message, String str, String str2, RongIMClient$SendImageMessageWithUploadListenerCallback rongIMClient$SendImageMessageWithUploadListenerCallback) {
        this.this$0 = rongIMClient;
        this.callback = rongIMClient$SendImageMessageWithUploadListenerCallback;
        this.message = message;
        this.pushContent = str;
        this.pushData = str2;
    }

    public void update(int i) {
        if (this.callback != null) {
            this.callback.onProgress(this.message, i);
        }
    }

    public void error() {
        if (this.callback != null) {
            this.callback.onFail(this.message, RongIMClient$ErrorCode.RC_MSG_SEND_FAIL);
        }
    }

    public void success(Uri uri) {
        if (uri == null) {
            RLog.m19420e("RongIMClient", "UploadImageStatusListener uri is null.");
            if (this.callback != null) {
                this.callback.onFail(this.message, RongIMClient$ErrorCode.RC_MSG_SEND_FAIL);
                return;
            }
            return;
        }
        MessageContent content = this.message.getContent();
        if (content instanceof ImageMessage) {
            ((ImageMessage) content).setRemoteUri(uri);
        }
        this.this$0.sendMessage(this.message, this.pushContent, this.pushData, new C53551());
    }
}
