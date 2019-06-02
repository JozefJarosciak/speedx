package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

public abstract class RongIMClient$DownloadMediaCallback extends ResultCallback<String> {
    public abstract void onProgress(int i);

    void onProgressCallback(final int i) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$DownloadMediaCallback.this.onProgress(i);
            }
        });
    }
}
