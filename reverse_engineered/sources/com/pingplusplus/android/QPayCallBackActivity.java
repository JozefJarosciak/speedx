package com.pingplusplus.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.api.IOpenApiListener;
import com.tencent.mobileqq.openpay.data.base.BaseResponse;
import com.tencent.mobileqq.openpay.data.pay.PayResponse;

public class QPayCallBackActivity extends Activity implements IOpenApiListener {
    private IOpenApi openApi;
    private C4293j qPayHandler;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.qPayHandler = PingppObject.getInstance().pingppQPayHandler;
        if (this.qPayHandler == null) {
            this.qPayHandler = C4293j.m16994a(this, C4293j.f14969a);
        }
        this.openApi = this.qPayHandler.m16995a();
        this.openApi.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.openApi.handleIntent(intent, this);
    }

    public void onOpenResponse(BaseResponse baseResponse) {
        boolean z = false;
        String str = "Callback from mqq";
        str = "";
        if (baseResponse == null) {
            String str2 = "response is null.";
            return;
        }
        String str3;
        int i;
        if (baseResponse instanceof PayResponse) {
            PayResponse payResponse = (PayResponse) baseResponse;
            String str4 = " apiName:" + payResponse.apiName + " serialnumber:" + payResponse.serialNumber + " isSucess:" + payResponse.isSuccess() + " retCode:" + payResponse.retCode + " retMsg:" + payResponse.retMsg;
            z = payResponse.isSuccess();
            str3 = payResponse.retMsg;
            i = payResponse.retCode;
            if (payResponse.isSuccess() && !payResponse.isPayByWeChat()) {
                str4 + " transactionId:" + payResponse.transactionId + " payTime:" + payResponse.payTime + " callbackUrl:" + payResponse.callbackUrl + " totalFee:" + payResponse.totalFee + " spData:" + payResponse.spData;
            }
        } else {
            str3 = "response is not PayResponse.";
            str3 = str;
            i = 0;
        }
        this.qPayHandler.m16996b().a(z, str3, i);
        finish();
    }
}
