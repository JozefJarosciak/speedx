package io.rong.imkit;

import android.graphics.Bitmap;
import android.view.View;
import io.rong.imageloader.core.assist.FailReason;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imlib.RongIMClient$DownloadMediaCallback;
import io.rong.imlib.RongIMClient$ErrorCode;

class RongIM$35 implements ImageLoadingListener {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$DownloadMediaCallback val$callback;

    RongIM$35(RongIM rongIM, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        this.this$0 = rongIM;
        this.val$callback = rongIMClient$DownloadMediaCallback;
    }

    public void onLoadingStarted(String str, View view) {
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
        if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.RC_NET_UNAVAILABLE);
        }
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (this.val$callback != null) {
            this.val$callback.onCallback(str);
        }
    }

    public void onLoadingCancelled(String str, View view) {
    }
}
