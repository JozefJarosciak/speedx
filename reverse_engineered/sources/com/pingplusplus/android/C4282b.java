package com.pingplusplus.android;

import android.os.Message;
import com.alipay.sdk.app.PayTask;

/* renamed from: com.pingplusplus.android.b */
class C4282b extends Thread {
    /* renamed from: a */
    final /* synthetic */ String f14953a;
    /* renamed from: b */
    final /* synthetic */ PaymentActivity f14954b;

    C4282b(PaymentActivity paymentActivity, String str) {
        this.f14954b = paymentActivity;
        this.f14953a = str;
    }

    public void run() {
        String pay;
        try {
            PayTask payTask = new PayTask(this.f14954b);
            PingppLog.m16962d("alipaysdk version: " + payTask.getVersion());
            pay = payTask.pay(this.f14953a, true);
            PingppLog.m16962d("PaymentActivity alipay result: " + pay);
            Message message = new Message();
            message.what = 1;
            message.obj = pay;
            PaymentActivity.b(this.f14954b).sendMessage(message);
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            pay = PingppObject.getInstance().currentChannel;
            this.f14954b.a("fail", "channel_sdk_not_included:" + pay, "不支持该渠道: " + pay + "。缺少支付宝的 SDK。");
        }
    }
}
