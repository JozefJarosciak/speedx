package io.rong.imlib;

import android.os.RemoteException;
import java.util.concurrent.CountDownLatch;

class RongIMClient$72 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ CountDownLatch val$countDownLatch;
    final /* synthetic */ long[] val$deltaTime;

    RongIMClient$72(RongIMClient rongIMClient, long[] jArr, CountDownLatch countDownLatch) {
        this.this$0 = rongIMClient;
        this.val$deltaTime = jArr;
        this.val$countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            this.val$deltaTime[0] = RongIMClient.access$400(this.this$0).getDeltaTime();
            this.val$countDownLatch.countDown();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
