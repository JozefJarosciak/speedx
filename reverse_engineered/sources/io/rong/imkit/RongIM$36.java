package io.rong.imkit;

import android.view.View;
import io.rong.imageloader.core.listener.ImageLoadingProgressListener;
import io.rong.imlib.RongIMClient$DownloadMediaCallback;

class RongIM$36 implements ImageLoadingProgressListener {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$DownloadMediaCallback val$callback;

    RongIM$36(RongIM rongIM, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        this.this$0 = rongIM;
        this.val$callback = rongIMClient$DownloadMediaCallback;
    }

    public void onProgressUpdate(String str, View view, int i, int i2) {
        if (this.val$callback != null) {
            this.val$callback.onProgress((i * 100) / i2);
        }
    }
}
