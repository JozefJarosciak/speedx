package com.pingplusplus.android;

import android.annotation.TargetApi;
import android.os.Message;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.http.HttpStatus;

/* renamed from: com.pingplusplus.android.d */
class C4287d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f14961a;
    /* renamed from: b */
    final /* synthetic */ PaymentActivity f14962b;

    C4287d(PaymentActivity paymentActivity, String str) {
        this.f14962b = paymentActivity;
        this.f14961a = str;
    }

    @TargetApi(11)
    public void run() {
        try {
            if (PaymentActivity.a(this.f14962b, this.f14961a).getResponseCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
                Message obtainMessage = PaymentActivity.b(this.f14962b).obtainMessage();
                obtainMessage.obj = this.f14961a;
                obtainMessage.what = 4;
                PaymentActivity.b(this.f14962b).sendMessage(obtainMessage);
                return;
            }
            this.f14962b.a("fail");
        } catch (MalformedURLException e) {
            this.f14962b.a("fail", "url is invalid");
            e.printStackTrace();
        } catch (IOException e2) {
            this.f14962b.a("fail", "url is invalid");
            e2.printStackTrace();
        } catch (Exception e3) {
            this.f14962b.a("fail", "url is invalid");
            e3.printStackTrace();
        }
    }
}
