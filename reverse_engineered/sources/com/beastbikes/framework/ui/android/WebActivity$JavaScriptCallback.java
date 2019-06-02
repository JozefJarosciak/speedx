package com.beastbikes.framework.ui.android;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

class WebActivity$JavaScriptCallback {
    Activity mActivity;
    final /* synthetic */ WebActivity this$0;

    public WebActivity$JavaScriptCallback(WebActivity webActivity, Activity activity) {
        this.this$0 = webActivity;
        this.mActivity = activity;
    }

    @JavascriptInterface
    public void speedxBridge(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.this$0.onJsMethodSpeedxBridge(str);
        }
    }

    @JavascriptInterface
    public int getCountryCode() {
        int[] iArr = new int[]{0};
        this.this$0.onJsMethodGetCountryCode(iArr);
        return iArr[0];
    }

    @JavascriptInterface
    public void finish() {
        this.this$0.finish();
    }

    @JavascriptInterface
    public void lightMedal(int i) {
        this.this$0.onJsMethodLightMedal(i);
    }

    @JavascriptInterface
    public void finishClubTransfer() {
        this.this$0.onJsMethodFinishClubTransfer();
    }

    @JavascriptInterface
    public void createPayment(String str) {
        this.this$0.onJsMethodCreatePayment(str);
    }

    @JavascriptInterface
    public void receiveAwardSuccess(int i) {
        this.this$0.receiveAwardSuccess(i);
    }
}
