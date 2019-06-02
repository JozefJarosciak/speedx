package com.pingplusplus.android;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.util.C0880h;
import com.alipay.sdk.util.C0882j;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: com.pingplusplus.android.e */
class C4288e extends Handler {
    /* renamed from: a */
    WeakReference<PaymentActivity> f14963a;

    C4288e(PaymentActivity paymentActivity) {
        this.f14963a = new WeakReference(paymentActivity);
    }

    /* renamed from: a */
    private int m16990a(String str) {
        return Integer.parseInt(m16991a(str.replace("{", "").replace(C0880h.f2222d, ""), "resultStatus=", ";memo"));
    }

    /* renamed from: a */
    private String m16991a(String str, String str2, String str3) {
        int indexOf = str.indexOf(str2) + str2.length();
        return str3 != null ? str.substring(indexOf, str.indexOf(str3)) : str.substring(indexOf);
    }

    @TargetApi(11)
    public void handleMessage(Message message) {
        PaymentActivity paymentActivity = (PaymentActivity) this.f14963a.get();
        switch (message.what) {
            case 1:
                String str = (String) message.obj;
                try {
                    int a = m16990a(str);
                    if (a == 9000) {
                        paymentActivity.a("success");
                        return;
                    } else if (a == 6001) {
                        paymentActivity.a("cancel", "user_cancelled");
                        return;
                    } else {
                        Iterable arrayList = new ArrayList();
                        arrayList.add(C0882j.f2227a);
                        arrayList.add(String.valueOf(a));
                        paymentActivity.a("fail", "channel_returns_fail", TextUtils.join(SimpleComparison.EQUAL_TO_OPERATION, arrayList));
                        return;
                    }
                } catch (Exception e) {
                    paymentActivity.a("fail", "channel_returns_fail", str);
                    return;
                }
            case 2:
                paymentActivity.a("success");
                return;
            case 3:
                return;
            case 4:
                new C4300q(paymentActivity, PaymentActivity.a(paymentActivity), (String) message.obj, null).m17035a(true).m17036a();
                return;
            default:
                paymentActivity.a("fail", "testmode_notify_failed");
                return;
        }
    }
}
