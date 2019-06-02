package com.pingplusplus.android;

import com.baidu.android.pay.PayCallBack;

/* renamed from: com.pingplusplus.android.c */
class C4283c implements PayCallBack {
    /* renamed from: a */
    final /* synthetic */ PaymentActivity f14955a;

    C4283c(PaymentActivity paymentActivity) {
        this.f14955a = paymentActivity;
    }

    public boolean isHideLoadingDialog() {
        return false;
    }

    public void onPayResult(int i, String str) {
        PaymentActivity.a(this.f14955a, 0);
        PaymentActivity.a(this.f14955a, i, str);
    }
}
