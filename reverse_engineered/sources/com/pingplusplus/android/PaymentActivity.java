package com.pingplusplus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;
import ch.qos.logback.classic.spi.CallerData;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.sys.C0869a;
import com.baidu.paysdk.api.BaiduPay;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.data.pay.PayApi;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.unionpay.UPPayAssistEx;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends Activity {
    public static final String EXTRA_CHARGE = "com.pingplusplus.android.PaymentActivity.CHARGE";
    /* renamed from: i */
    private static final String[] f7008i = new String[]{"alipay", "wx", "upacp", "upmp", "bfb", "yeepay_wap", "jdpay_wap", "bfb_wap", "qpay", "mmdpay_wap", "fqlpay_wap", "qgbc_wap", "cmb_wallet"};
    /* renamed from: a */
    public int f7009a = 0;
    /* renamed from: b */
    protected String f7010b = "cancel";
    /* renamed from: c */
    int f7011c = 1;
    /* renamed from: d */
    private String f7012d = null;
    /* renamed from: e */
    private int f7013e = 0;
    /* renamed from: f */
    private JSONObject f7014f;
    /* renamed from: g */
    private C4307x f7015g = null;
    /* renamed from: h */
    private boolean f7016h = false;
    /* renamed from: j */
    private Handler f7017j = new C4288e(this);

    /* renamed from: a */
    private void m8265a(int i, String str) {
        if (str == null) {
            m8285a("cancel", "user_cancelled");
        } else if (i == -15) {
            m8285a("fail", "network is error");
        } else {
            String substring = str.substring("statecode={".length() + str.indexOf("statecode="), str.indexOf("};order_no="));
            if (substring.equals("0")) {
                m8284a("success");
            } else if (substring.equals(C0844a.f2048d)) {
                m8284a("in_process");
            } else if (substring.equals("2") || substring.equals("7")) {
                m8285a("cancel", "user_cancelled");
            } else if (substring.equals("3")) {
                m8285a("fail", "bfb_not_supported_method");
            } else if (substring.equals("4")) {
                m8285a("fail", "bfb_token_expired");
            } else {
                m8285a("fail", "unknown_error");
            }
        }
    }

    /* renamed from: a */
    private void m8267a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("alipay");
        PingppLog.d("PaymentActivity start alipay credential : " + jSONObject);
        new C4282b(this, jSONObject2.getString("orderInfo")).start();
    }

    @TargetApi(11)
    /* renamed from: a */
    private void m8268a(JSONObject jSONObject, String str) {
        String optString = jSONObject.optString(str);
        if ("fqlpay_wap".equals(str)) {
            new C4300q(this, this.f7014f, optString, null).a(true).a();
        } else {
            new C4300q(this, this.f7014f, optString, null).a();
        }
    }

    /* renamed from: a */
    private boolean m8269a(JSONObject jSONObject, String str, String str2) {
        return (str2 == null || str2.equals(str)) && jSONObject.has(str) && !jSONObject.getString(str).equals("[]") && !jSONObject.getString(str).equals("{}");
    }

    /* renamed from: b */
    private HttpsURLConnection m8271b(String str) {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        httpsURLConnection.setRequestMethod("GET");
        return httpsURLConnection;
    }

    @TargetApi(11)
    /* renamed from: b */
    private void m8272b(String str, String str2) {
        PingppLog.d("模拟支付页面: 支付渠道 " + str2);
        String format = String.format("http://sissi.pingxx.com/mock.php?ch_id=%s&channel=%s", new Object[]{str, str2});
        PingppLog.a(format);
        new C4300q(this, this.f7014f, format, null).a();
    }

    /* renamed from: b */
    private void m8273b(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("wx");
        PingppLog.d("PaymentActivity start wx credential : " + jSONObject);
        String string = jSONObject2.getString("appId");
        PingppObject.getInstance().wxAppId = string;
        try {
            this.f7015g = new C4307x(this, string);
            PingppObject.getInstance().pingppWxHandler = this.f7015g;
            if (this.f7015g.a()) {
                if ((this.f7015g.b() >= 570425345 ? 1 : 0) != 0) {
                    this.f7009a = 1;
                    this.f7015g.a(jSONObject2);
                    return;
                }
                m8285a("fail", "wx_app_not_support");
                return;
            }
            m8285a("invalid", "wx_app_not_installed");
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            string = PingppObject.getInstance().currentChannel;
            m8286a("fail", "channel_sdk_not_included:" + string, "不支持该渠道: " + string + "。缺少微信的 SDK。");
        }
    }

    /* renamed from: c */
    private void m8274c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("qpay");
            PingppLog.d("PaymentActivity start qpay credential : " + jSONObject);
            String string = jSONObject2.getString("app_id");
            PingppObject.getInstance().pingppQPayHandler = C4293j.a(this, string);
            IOpenApi a = PingppObject.getInstance().pingppQPayHandler.a();
            if (!a.isMobileQQInstalled()) {
                m8285a("invalid", "qq_app_not_installed");
            } else if (a.isMobileQQSupportApi("pay")) {
                PayApi payApi = new PayApi();
                StringBuilder append = new StringBuilder().append("");
                int i = this.f7011c;
                this.f7011c = i + 1;
                payApi.serialNumber = append.append(i).toString();
                if (PingppObject.getInstance().qpayScheme == null) {
                    payApi.callbackScheme = "qwallet" + string;
                } else {
                    payApi.callbackScheme = PingppObject.getInstance().qpayScheme;
                }
                payApi.pubAcc = "";
                payApi.pubAccHint = "";
                payApi.timeStamp = System.currentTimeMillis() / 1000;
                payApi.sigType = "HMAC-SHA1";
                payApi.bargainorId = jSONObject2.optString("bargainor_id");
                payApi.appId = string;
                payApi.nonce = jSONObject2.optString("nonce");
                payApi.sig = jSONObject2.optString("sign");
                payApi.tokenId = jSONObject2.optString("token_id");
                if (payApi.checkParams()) {
                    a.execApi(payApi);
                }
            } else {
                m8285a("fail", "qq_app_not_support");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    private void m8275d(JSONObject jSONObject) {
        if (VERSION.SDK_INT >= 23) {
            PingppLog.d("Checking permissions...");
            try {
                if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_PHONE_STATE")) {
                        Toast.makeText(this, "使用银联支付，请接受以下权限", 0).show();
                    }
                    ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE"}, 101);
                    return;
                }
            } catch (NoClassDefFoundError e) {
                e.printStackTrace();
                m8286a("fail", "client_error", "请使用最新的 Android v4 Support Library，Android 6.0 以上需要申请权限");
                return;
            }
        }
        m8276e(jSONObject);
    }

    /* renamed from: e */
    private void m8276e(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject.has("upacp")) {
            jSONObject2 = jSONObject.getJSONObject("upacp");
            PingppLog.d("PaymentActivity start upacp credential : " + jSONObject);
        } else {
            jSONObject2 = jSONObject.getJSONObject("upmp");
            PingppLog.d("PaymentActivity start upmp credential : " + jSONObject);
        }
        try {
            UPPayAssistEx.startPay(this, null, null, jSONObject2.getString("tn"), jSONObject2.getString("mode"));
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            String str = PingppObject.getInstance().currentChannel;
            m8286a("fail", "channel_sdk_not_included:" + str, "不支持该渠道: " + str + "。缺少银联的 SDK。");
        }
    }

    /* renamed from: f */
    private void m8277f(JSONObject jSONObject) {
        String str;
        JSONObject jSONObject2 = jSONObject.getJSONObject("bfb");
        PingppLog.d("PaymentActivity start bfb credential : " + jSONObject);
        try {
            BaiduPay.getInstance();
            Map hashMap = new HashMap();
            hashMap.put("cashier_type", "0");
            Iterable arrayList = new ArrayList();
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                Iterable arrayList2 = new ArrayList();
                arrayList2.add(str);
                arrayList2.add(jSONObject2.getString(str));
                arrayList.add(TextUtils.join(SimpleComparison.EQUAL_TO_OPERATION, arrayList2));
            }
            if (arrayList.size() == 0) {
                m8285a("fail", "invalid_credential");
                return;
            }
            str = TextUtils.join(C0869a.f2161b, arrayList);
            this.f7013e = 1;
            BaiduPay.getInstance().doPay(this, str, new C4283c(this), hashMap);
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            str = PingppObject.getInstance().currentChannel;
            m8286a("fail", "channel_sdk_not_included:" + str, "不支持该渠道: " + str + "。缺少百度钱包的 SDK。");
        }
    }

    @TargetApi(11)
    /* renamed from: g */
    private void m8278g(JSONObject jSONObject) {
        String encode;
        String encode2;
        byte[] bArr;
        UnsupportedEncodingException unsupportedEncodingException;
        JSONObject jSONObject2 = jSONObject.getJSONObject("yeepay_wap");
        PingppLog.d("PaymentActivity start yeepay_wap credential : " + jSONObject);
        String string = jSONObject2.getString("merchantaccount");
        try {
            encode = URLEncoder.encode(jSONObject2.getString("encryptkey"), "UTF-8");
            try {
                encode2 = URLEncoder.encode(jSONObject2.getString(C0861d.f2139k), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException unsupportedEncodingException2 = e;
                bArr = encode;
                unsupportedEncodingException = unsupportedEncodingException2;
                unsupportedEncodingException.printStackTrace();
                encode = bArr;
                encode2 = null;
                new C4300q(this, this.f7014f, String.format("live".equals(jSONObject2.getString("mode")) ? "https://ok.yeepay.com/paymobile/api/pay/request?merchantaccount=%s&encryptkey=%s&data=%s" : "http://mobiletest.yeepay.com/paymobile/api/pay/request?merchantaccount=%s&encryptkey=%s&data=%s", new Object[]{string, encode, encode2}), null).a();
            }
        } catch (UnsupportedEncodingException e2) {
            unsupportedEncodingException = e2;
            bArr = null;
            unsupportedEncodingException.printStackTrace();
            encode = bArr;
            encode2 = null;
            if ("live".equals(jSONObject2.getString("mode"))) {
            }
            new C4300q(this, this.f7014f, String.format("live".equals(jSONObject2.getString("mode")) ? "https://ok.yeepay.com/paymobile/api/pay/request?merchantaccount=%s&encryptkey=%s&data=%s" : "http://mobiletest.yeepay.com/paymobile/api/pay/request?merchantaccount=%s&encryptkey=%s&data=%s", new Object[]{string, encode, encode2}), null).a();
        }
        if ("live".equals(jSONObject2.getString("mode"))) {
        }
        new C4300q(this, this.f7014f, String.format("live".equals(jSONObject2.getString("mode")) ? "https://ok.yeepay.com/paymobile/api/pay/request?merchantaccount=%s&encryptkey=%s&data=%s" : "http://mobiletest.yeepay.com/paymobile/api/pay/request?merchantaccount=%s&encryptkey=%s&data=%s", new Object[]{string, encode, encode2}), null).a();
    }

    @Deprecated
    public static String getVersion() {
        return Pingpp.VERSION;
    }

    @TargetApi(11)
    /* renamed from: h */
    private void m8279h(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("bfb_wap");
        String optString = jSONObject2.optString("url");
        Iterable arrayList = new ArrayList();
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!"url".equals(str)) {
                Iterable arrayList2 = new ArrayList();
                arrayList2.add(str);
                arrayList2.add(jSONObject2.getString(str));
                arrayList.add(TextUtils.join(SimpleComparison.EQUAL_TO_OPERATION, arrayList2));
            }
        }
        if (arrayList.size() == 0) {
            m8285a("fail", "invalid_credential");
            return;
        }
        new C4300q(this, this.f7014f, optString + CallerData.NA + TextUtils.join(C0869a.f2161b, arrayList), null).a(true).a();
    }

    /* renamed from: i */
    private void m8280i(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("qgbc_wap");
        if (optJSONObject != null) {
            new Thread(new C4287d(this, optJSONObject.optString("url"))).start();
        }
    }

    @TargetApi(11)
    /* renamed from: j */
    private void m8281j(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("jdpay_wap");
        PingppLog.d("PaymentActivity start jdpay_wap credential : " + jSONObject);
        Iterable arrayList = new ArrayList();
        Iterator keys = jSONObject2.keys();
        String str = "https://m.jdpay.com/wepay/web/pay";
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if ("channelUrl".equals(str2)) {
                str = jSONObject2.getString(str2);
            } else {
                Iterable arrayList2 = new ArrayList();
                arrayList2.add(str2);
                try {
                    arrayList2.add(URLEncoder.encode(jSONObject2.getString(str2), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                arrayList.add(TextUtils.join(SimpleComparison.EQUAL_TO_OPERATION, arrayList2));
            }
        }
        if (arrayList.size() == 0) {
            m8285a("fail", "invalid_credential");
            return;
        }
        str2 = TextUtils.join(C0869a.f2161b, arrayList);
        PingppLog.d("jdPay orderInfo: " + str2);
        new C4300q(this, this.f7014f, str, str2.getBytes()).a(false).a();
    }

    /* renamed from: k */
    private void m8282k(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("cmb_wallet");
        Iterable arrayList = new ArrayList();
        Iterator keys = optJSONObject.keys();
        String str = null;
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if ("ChannelUrl".equalsIgnoreCase(str2)) {
                str = optJSONObject.optString(str2);
            } else {
                Iterable arrayList2 = new ArrayList();
                arrayList2.add(str2);
                try {
                    arrayList2.add(URLEncoder.encode(optJSONObject.getString(str2), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                arrayList.add(TextUtils.join(SimpleComparison.EQUAL_TO_OPERATION, arrayList2));
            }
        }
        if (arrayList.size() == 0) {
            m8285a("fail", "invalid_credential");
            return;
        }
        new C4300q(this, this.f7014f, str + CallerData.NA + TextUtils.join(C0869a.f2161b, arrayList), null).a(false).a();
    }

    /* renamed from: a */
    public void m8283a() {
        int i = PingppObject.getInstance().wxErrCode;
        if (i == 0) {
            m8284a("success");
        } else if (i == -2) {
            m8285a("cancel", "user_cancelled");
        } else {
            m8286a("fail", "channel_returns_fail", "wx_err_code:" + i);
        }
        PingppObject.getInstance().wxErrCode = -10;
    }

    /* renamed from: a */
    public void m8284a(String str) {
        m8285a(str, "");
    }

    /* renamed from: a */
    public void m8285a(String str, String str2) {
        m8286a(str, str2, "");
    }

    /* renamed from: a */
    public void m8286a(String str, String str2, String str3) {
        PingppLog.a("setResultAndFinish result=" + str + " isWXPayEntryActivity=" + this.f7016h);
        PingppObject.getInstance().currentChannel = null;
        PingppObject.getInstance().pingppWxHandler = null;
        Intent intent = new Intent();
        intent.putExtra("pay_result", str);
        intent.putExtra("error_msg", str2);
        intent.putExtra("extra_msg", str3);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: a */
    public void m8287a(boolean z, String str, int i) {
        if (z) {
            m8284a("success");
        } else if (i == -1) {
            m8285a("cancel", str);
        } else {
            m8285a("fail", str);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (C4300q.f14990a != null) {
                Object data = (intent == null || i2 != -1) ? null : intent.getData();
                C4300q.f14990a.onReceiveValue(data);
                C4300q.f14990a = null;
            } else {
                return;
            }
        } else if (i == 2) {
            if (C4300q.f14991b != null) {
                Uri data2 = (intent == null || i2 != -1) ? null : intent.getData();
                if (data2 != null) {
                    C4300q.f14991b.onReceiveValue(new Uri[]{data2});
                } else {
                    C4300q.f14991b.onReceiveValue(new Uri[0]);
                }
                C4300q.f14991b = null;
            } else {
                return;
            }
        }
        if (i == 16) {
            m8284a("success");
        }
        super.onActivityResult(i, i2, intent);
        if (PingppObject.getInstance().currentChannel != null && !PingppObject.getInstance().currentChannel.equalsIgnoreCase("upmp") && !PingppObject.getInstance().currentChannel.equalsIgnoreCase("upacp")) {
            return;
        }
        if (intent == null) {
            m8285a("fail", "");
            return;
        }
        String string = intent.getExtras().getString("pay_result");
        if (string == null) {
            m8284a("fail");
        } else if (string.equalsIgnoreCase("success")) {
            m8284a("success");
        } else if (string.equalsIgnoreCase("fail")) {
            m8285a("fail", "channel_returns_fail");
        } else if (string.equalsIgnoreCase("cancel")) {
            m8285a("cancel", "user_cancelled");
        } else {
            m8285a("fail", "unknown_error");
        }
    }

    public void onBackPressed() {
        PingppObject.getInstance().currentChannel = null;
        Intent intent = new Intent();
        intent.putExtra("pay_result", this.f7010b);
        setResult(-1, intent);
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PingppLog.a("onCreate");
        if (bundle == null) {
            JSONObject jSONObject;
            this.f7014f = null;
            String stringExtra = getIntent().getStringExtra(EXTRA_CHARGE);
            if (stringExtra != null) {
                PingppLog.d("PaymentActivity received charge: " + stringExtra);
                try {
                    this.f7014f = new JSONObject(stringExtra);
                    new Thread(new C4280a(this)).start();
                    if (this.f7014f.has("credential")) {
                        this.f7012d = this.f7014f.getString(OnlineConfigAgent.KEY_CHANNEL);
                        if (!this.f7014f.has("livemode") || this.f7014f.getBoolean("livemode")) {
                            jSONObject = this.f7014f.getJSONObject("credential");
                            if (jSONObject != null) {
                                PingppObject.getInstance().currentChannel = this.f7012d;
                                PingppLog.d("PaymentActivity received channel: " + this.f7012d);
                                if (Arrays.asList(f7008i).contains(this.f7012d)) {
                                    PingppObject.getInstance().currentChannel = null;
                                    m8285a("fail", "invalid_charge_no_such_channel");
                                }
                                try {
                                    if (!m8269a(jSONObject, "upmp", this.f7012d) || m8269a(jSONObject, "upacp", this.f7012d)) {
                                        m8275d(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "wx", this.f7012d)) {
                                        m8273b(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "alipay", this.f7012d)) {
                                        m8267a(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "bfb", this.f7012d)) {
                                        m8277f(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "bfb_wap", this.f7012d)) {
                                        m8279h(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "yeepay_wap", this.f7012d)) {
                                        m8278g(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "jdpay_wap", this.f7012d)) {
                                        m8281j(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "qpay", this.f7012d)) {
                                        m8274c(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "mmdpay_wap", this.f7012d) || m8269a(jSONObject, "fqlpay_wap", this.f7012d)) {
                                        m8268a(jSONObject, this.f7012d);
                                        return;
                                    } else if (m8269a(jSONObject, "qgbc_wap", this.f7012d)) {
                                        m8280i(jSONObject);
                                        return;
                                    } else if (m8269a(jSONObject, "cmb_wallet", this.f7012d)) {
                                        m8282k(jSONObject);
                                        return;
                                    } else {
                                        PingppObject.getInstance().currentChannel = null;
                                        m8285a("fail", "invalid_credential");
                                        return;
                                    }
                                } catch (JSONException e) {
                                    PingppObject.getInstance().currentChannel = null;
                                    m8285a("fail", "invalid_credential");
                                    return;
                                }
                            } else if (PingppObject.getInstance().wxAppId != null) {
                                PingppLog.a("isWXPayEntryActivity");
                                this.f7016h = true;
                                this.f7015g = PingppObject.getInstance().pingppWxHandler;
                                if (this.f7015g != null) {
                                    this.f7015g.a(this);
                                    this.f7015g.a(getIntent());
                                }
                            }
                        }
                        m8272b(this.f7014f.getString("id"), this.f7012d);
                        return;
                    }
                    m8285a("fail", "invalid_charge_no_credential");
                } catch (JSONException e2) {
                    m8285a("fail", "invalid_charge_json_decode_fail");
                }
            }
            jSONObject = null;
            if (jSONObject != null) {
                PingppObject.getInstance().currentChannel = this.f7012d;
                PingppLog.d("PaymentActivity received channel: " + this.f7012d);
                if (Arrays.asList(f7008i).contains(this.f7012d)) {
                    if (m8269a(jSONObject, "upmp", this.f7012d)) {
                    }
                    m8275d(jSONObject);
                    return;
                }
                PingppObject.getInstance().currentChannel = null;
                m8285a("fail", "invalid_charge_no_such_channel");
            } else if (PingppObject.getInstance().wxAppId != null) {
                PingppLog.a("isWXPayEntryActivity");
                this.f7016h = true;
                this.f7015g = PingppObject.getInstance().pingppWxHandler;
                if (this.f7015g != null) {
                    this.f7015g.a(this);
                    this.f7015g.a(getIntent());
                }
            }
        }
    }

    public void onDestroy() {
        if (PingppObject.getInstance().pingppQPayHandler != null) {
            PingppObject.getInstance().pingppQPayHandler.c();
            PingppObject.getInstance().qpayScheme = null;
        }
        super.onDestroy();
        PingppLog.a("onDestroy isWXPayEntryActivity=" + this.f7016h);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        PingppLog.a("onNewIntent isWXPayEntryActivity=" + this.f7016h);
        if (this.f7015g != null) {
            setIntent(intent);
            this.f7015g.a(getIntent());
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.f7009a == 1) {
            this.f7009a = 2;
        } else if (this.f7013e == 1 && PingppObject.getInstance().currentChannel != null && PingppObject.getInstance().currentChannel.equalsIgnoreCase("bfb")) {
            this.f7013e = 2;
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        switch (i) {
            case 101:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    m8286a("fail", "permission_denied", "银联渠道所需权限被用户拒绝");
                    return;
                }
                C4295l.a(this).b(this);
                try {
                    m8276e(this.f7014f.getJSONObject("credential"));
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    m8285a("fail", "invalid_charge_no_credential");
                    return;
                }
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        PingppLog.a("onResume wxPayStatus=" + this.f7009a + " isWXPayEntryActivity=" + this.f7016h);
        if (this.f7009a == 2 || this.f7013e == 2) {
            m8285a("cancel", "user_cancelled");
        } else if (this.f7009a == 0 && PingppObject.getInstance().wxErrCode != -10 && PingppObject.getInstance().currentChannel.equals("wx")) {
            m8283a();
        }
    }
}
