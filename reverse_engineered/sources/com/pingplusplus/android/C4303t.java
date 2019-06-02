package com.pingplusplus.android;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.webkit.WebView;
import cmb.pb.util.CMBKeyboardFunc;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.pingplusplus.android.t */
class C4303t extends C4302z {
    /* renamed from: a */
    final /* synthetic */ C4300q f15009a;

    C4303t(C4300q c4300q, WebViewEx webViewEx) {
        this.f15009a = c4300q;
        webViewEx.getClass();
        super(webViewEx);
    }

    public void onPageFinished(WebView webView, String str) {
        if ("fqlpay_wap".equals(this.f15009a.f15003n) && str.contains("http://m.mall.fenqile.com/app/order/result/")) {
            this.f15009a.f14997h.setVisibility(0);
            webView.loadUrl("javascript:window.local_obj.showSource('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        }
        if (str.equals("file:///android_asset/pingpp_web.html") && this.f15009a.f15004o) {
            this.f15009a.f15004o = false;
            this.f15009a.f14992c.loadUrl(this.f15009a.f14995f);
        } else if (str.equals("file:///android_asset/pingpp_web.html") && !this.f15009a.f15004o) {
            this.f15009a.f14993d.onBackPressed();
        } else if (!str.equals("file:///android_asset/pingpp_web.html")) {
            this.f15009a.f14998i.setVisibility(8);
        }
        super.onPageFinished(webView, str);
    }

    @TargetApi(11)
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        if ("https://m.baifubao.com/".equals(str)) {
            this.f15009a.f14993d.onBackPressed();
        }
        if ("mmdpay_wap".equals(this.f15009a.f15003n) && str.contains("https://wap.memedai.cn/merchantApp/sdk/web/ending?")) {
            this.f15009a.f15005p = true;
            this.f15009a.f14993d.f7010b = "processing";
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f15009a.f14993d.f7010b = "fail";
        if (this.f15009a.f15006q != null) {
            webView.loadUrl(this.f15009a.f15006q);
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Object obj = null;
        if (str.contains(this.f15009a.f14994e.optJSONObject("credential").optString("page_url")) && str.contains("pay_result=1")) {
            this.f15009a.f14993d.f7010b = "success";
            this.f15009a.f14993d.a("success");
        } else if (str.contains("pay_result=-1")) {
            this.f15009a.f14993d.f7010b = "fail";
            this.f15009a.f14993d.a("fail");
        } else {
            if (!"https://www.baifubao.com/wap/0/wallet/0/transaction/0".equals(str)) {
                this.f15009a.f14993d;
                if (!str.contains("https://ok.yeepay.com/paymobile/query/pay/success?")) {
                    this.f15009a.f14993d;
                    if (!str.contains("http://mobiletest.yeepay.com/paymobile/query/pay/success?")) {
                        if ("https://m.baifubao.com/".equals(str) || (this.f15009a.f15002m && str.contains(this.f15009a.f15001l))) {
                            this.f15009a.f14993d.onBackPressed();
                        } else if (str.contains("http://wappass.baidu.com/passport/reg?") || str.contains("http://wappass.baidu.com/passport/getpass?") || str.contains("http://wappass.baidu.com/passport/agreement?") || "https://www.jdpay.com/".equals(str) || "http://wapzt.189.cn/pages/login/login_userLogin.html#/pages/password/passwordNoLogin.html".equals(str) || "https://uac.10010.com/cust/resetpwd/inputName".equals(str) || "http://bj.ac.10086.cn/login".equals(str) || str.startsWith("tel:")) {
                            this.f15009a.m17018a(str);
                            return true;
                        } else if (str.startsWith("intent://")) {
                            return true;
                        } else {
                            if ("http://pingxx/?payResult=success".equals(str)) {
                                this.f15009a.f14993d.a("success");
                                return true;
                            } else if (("http://m.mall.fenqile.com/".equals(str) || "http://m.fenqile.com/".equals(str)) && !str.contains("http://m.mall.fenqile.com/app/order/result/")) {
                                this.f15009a.f14993d.onBackPressed();
                                return true;
                            } else if ("fqlpay_wap".equals(this.f15009a.f15003n) && this.f15009a.f15005p && !str.contains("http://m.mall.fenqile.com/app/order/result/")) {
                                this.f15009a.m17018a(str);
                                return true;
                            } else if ("mmdpay_wap".equals(this.f15009a.f15003n) && str.contains("https://wap.memedai.cn/merchantApp/sdk/web/ending?")) {
                                this.f15009a.f15005p = true;
                                this.f15009a.f14993d.f7010b = "processing";
                            }
                        }
                    }
                }
            }
            this.f15009a.f14993d.f7010b = "success";
        }
        try {
            JSONObject jSONObject = this.f15009a.f14994e.getJSONObject("extra");
            if ("jdpay_wap".equals(this.f15009a.f15003n)) {
                CharSequence string = jSONObject.getString("success_url");
                String string2 = jSONObject.getString("fail_url");
                PingppLog.m16962d("jdPay url: " + str);
                PingppLog.m16962d("jdPay successUrl: " + string);
                PingppLog.m16962d("jdPay fail_url: " + string2);
                this.f15009a.f15006q = string2;
                if (str.contains(string)) {
                    this.f15009a.f14993d.a("success");
                    this.f15009a.f14992c.destroy();
                    this.f15009a.f14992c = null;
                    PingppLog.m16962d("jdPay success");
                    return true;
                } else if (str.contains(string2)) {
                    this.f15009a.f14993d.a("fail");
                    this.f15009a.f14992c.destroy();
                    this.f15009a.f14992c = null;
                    PingppLog.m16962d("jdPay fail");
                    return true;
                }
            } else if ("yeepay_wap".equals(this.f15009a.f15003n) && str.contains(jSONObject.optString("result_url"))) {
                this.f15009a.f14993d.a("success");
                this.f15009a.f14992c.destroy();
                this.f15009a.f14992c = null;
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.f15009a.f14993d.f7010b = "fail";
        }
        if ("cmb_wallet".equals(this.f15009a.f15003n)) {
            if (new CMBKeyboardFunc(this.f15009a.f14993d).HandleUrlCall(webView, str)) {
                return true;
            }
            try {
                obj = this.f15009a.f14994e.optJSONObject("credential").optJSONObject("cmb_wallet").optString("MerchantRetUrl");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (str.equals(obj)) {
                this.f15009a.f15005p = true;
                this.f15009a.f14993d.f7010b = "success";
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }
}
