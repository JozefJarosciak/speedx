package com.pingplusplus.android;

/* renamed from: com.pingplusplus.android.a */
class C4280a implements Runnable {
    /* renamed from: a */
    final /* synthetic */ PaymentActivity f14952a;

    C4280a(PaymentActivity paymentActivity) {
        this.f14952a = paymentActivity;
    }

    public void run() {
        if (PingppObject.getInstance().isOne) {
            PingppObject.getInstance().dataCollection.m16955a(C4289f.ONE);
            PingppObject.getInstance().dataCollection.m16956a(PaymentActivity.a(this.f14952a));
            return;
        }
        PingppDataCollection pingppDataCollection = new PingppDataCollection(this.f14952a);
        pingppDataCollection.m16956a(PaymentActivity.a(this.f14952a));
        pingppDataCollection.m16955a(C4289f.SDK);
        pingppDataCollection.sendToServer();
    }
}
