package com.loopj.android.http;

import android.content.Context;
import java.util.List;

class AsyncHttpClient$4 implements Runnable {
    final /* synthetic */ AsyncHttpClient this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ boolean val$mayInterruptIfRunning;

    AsyncHttpClient$4(AsyncHttpClient asyncHttpClient, Context context, boolean z) {
        this.this$0 = asyncHttpClient;
        this.val$context = context;
        this.val$mayInterruptIfRunning = z;
    }

    public void run() {
        List<RequestHandle> list = (List) AsyncHttpClient.access$100(this.this$0).get(this.val$context);
        if (list != null) {
            for (RequestHandle cancel : list) {
                cancel.cancel(this.val$mayInterruptIfRunning);
            }
            AsyncHttpClient.access$100(this.this$0).remove(this.val$context);
        }
    }
}
