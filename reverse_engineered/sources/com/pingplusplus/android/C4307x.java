package com.pingplusplus.android;

import android.content.Intent;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import org.json.JSONObject;

/* renamed from: com.pingplusplus.android.x */
public class C4307x implements IWXAPIEventHandler {
    /* renamed from: a */
    private PaymentActivity f15012a;
    /* renamed from: b */
    private PaymentActivity f15013b;
    /* renamed from: c */
    private IWXAPI f15014c = null;

    public C4307x(PaymentActivity paymentActivity, String str) {
        this.f15012a = paymentActivity;
        this.f15014c = WXAPIFactory.createWXAPI(this.f15012a.getApplicationContext(), str);
    }

    /* renamed from: a */
    public void m17038a(Intent intent) {
        this.f15014c.handleIntent(intent, this);
    }

    /* renamed from: a */
    public void m17039a(PaymentActivity paymentActivity) {
        if (!paymentActivity.equals(this.f15012a)) {
            PingppLog.m16961a("wxPayEnActivity not equals paymentActivity");
            this.f15013b = paymentActivity;
        }
    }

    /* renamed from: a */
    public void m17040a(JSONObject jSONObject) {
        String string = jSONObject.getString("appId");
        this.f15014c.registerApp(string);
        this.f15014c.handleIntent(this.f15012a.getIntent(), this);
        BaseReq payReq = new PayReq();
        payReq.appId = string;
        payReq.partnerId = jSONObject.getString("partnerId");
        payReq.prepayId = jSONObject.getString("prepayId");
        payReq.nonceStr = jSONObject.getString("nonceStr");
        if (jSONObject.get("timeStamp") instanceof String) {
            payReq.timeStamp = jSONObject.getString("timeStamp");
        } else {
            payReq.timeStamp = jSONObject.getInt("timeStamp") + "";
        }
        payReq.packageValue = jSONObject.getString("packageValue");
        payReq.sign = jSONObject.getString("sign");
        this.f15014c.sendReq(payReq);
    }

    /* renamed from: a */
    public boolean m17041a() {
        return this.f15014c.isWXAppInstalled();
    }

    /* renamed from: b */
    public int m17042b() {
        return this.f15014c.getWXAppSupportAPI();
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        PingppLog.m16961a("onResp");
        if (baseResp.getType() == 5) {
            PingppLog.m16962d("PaymentActivity wx result errCode : " + baseResp.errCode + " , errStr:" + baseResp.errStr);
            this.f15012a.f7009a = 0;
            PingppLog.m16961a("onResp wxPayStatus=" + this.f15012a.f7009a);
            PingppObject.getInstance().wxErrCode = baseResp.errCode;
            if (this.f15013b != null) {
                this.f15013b.finish();
                this.f15013b = null;
                return;
            }
            this.f15012a.a();
        }
    }
}
