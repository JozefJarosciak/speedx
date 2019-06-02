package com.pingplusplus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.umeng.onlineconfig.OnlineConfigAgent;
import org.json.JSONObject;

/* renamed from: com.pingplusplus.android.q */
public class C4300q {
    /* renamed from: a */
    public static ValueCallback<Uri> f14990a;
    /* renamed from: b */
    public static ValueCallback<Uri[]> f14991b;
    /* renamed from: c */
    private WebViewEx f14992c;
    /* renamed from: d */
    private PaymentActivity f14993d;
    /* renamed from: e */
    private JSONObject f14994e;
    /* renamed from: f */
    private String f14995f;
    /* renamed from: g */
    private byte[] f14996g;
    /* renamed from: h */
    private ImageView f14997h;
    /* renamed from: i */
    private ProgressBar f14998i;
    /* renamed from: j */
    private FrameLayout f14999j;
    /* renamed from: k */
    private View f15000k;
    /* renamed from: l */
    private String f15001l;
    /* renamed from: m */
    private boolean f15002m;
    /* renamed from: n */
    private String f15003n;
    /* renamed from: o */
    private boolean f15004o = true;
    /* renamed from: p */
    private boolean f15005p;
    /* renamed from: q */
    private String f15006q;

    public C4300q(Activity activity, JSONObject jSONObject, String str, byte[] bArr) {
        this.f14992c = new WebViewEx(activity);
        this.f14993d = (PaymentActivity) activity;
        this.f14995f = str;
        this.f14996g = bArr;
        this.f14994e = jSONObject;
        this.f15000k = LayoutInflater.from(activity).inflate(activity.getResources().getIdentifier("activity_pingpp_payment", "layout", activity.getPackageName()), null);
        this.f14992c = (WebViewEx) this.f15000k.findViewById(activity.getResources().getIdentifier("pingpp_webView", "id", activity.getPackageName()));
        this.f14998i = (ProgressBar) this.f15000k.findViewById(activity.getResources().getIdentifier("pingpp_progressbar", "id", activity.getPackageName()));
        this.f14999j = (FrameLayout) this.f15000k.findViewById(activity.getResources().getIdentifier("pingpp_title", "id", activity.getPackageName()));
        this.f14997h = (ImageView) this.f15000k.findViewById(activity.getResources().getIdentifier("pingpp_back", "id", activity.getPackageName()));
        this.f14997h.setOnClickListener(new C4301s(this));
        m17026d();
        this.f15003n = jSONObject.optString(OnlineConfigAgent.KEY_CHANNEL);
        m17024c();
    }

    /* renamed from: a */
    private void m17015a(ValueCallback<Uri[]> valueCallback) {
        f14991b = valueCallback;
        Parcelable intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        intent2.putExtra("android.intent.extra.INTENT", intent);
        intent2.putExtra("android.intent.extra.TITLE", "Image Chooser");
        this.f14993d.startActivityForResult(intent2, 2);
    }

    /* renamed from: a */
    private void m17018a(String str) {
        try {
            Uri parse = Uri.parse(str);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(parse);
            this.f14993d.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m17024c() {
        if ("cmb_wallet".equals(this.f15003n)) {
            try {
                CookieSyncManager.createInstance(this.f14993d.getApplicationContext());
                CookieManager.getInstance().removeAllCookie();
                CookieSyncManager.getInstance().sync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        m17037b();
        WebViewEx webViewEx = this.f14992c;
        WebViewEx webViewEx2 = this.f14992c;
        webViewEx2.getClass();
        webViewEx.setWebViewClient(new C4303t(this, webViewEx2));
        webViewEx = this.f14992c;
        webViewEx2 = this.f14992c;
        webViewEx2.getClass();
        webViewEx.setWebChromeClient(new C4305u(this, webViewEx2));
    }

    @TargetApi(11)
    /* renamed from: d */
    private void m17026d() {
        WebSettings settings = this.f14992c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUserAgentString(String.format("%s; %s/%s", new Object[]{settings.getUserAgentString(), "PingppAndroidSDK", Pingpp.VERSION}));
        this.f14992c.addJavascriptInterface(new C1497w(this, this.f14993d), "PingppAndroidSDK");
        this.f14992c.addJavascriptInterface(new C1497w(this, this.f14993d), "PingppSDK");
        this.f14992c.addJavascriptInterface(new C1496v(this), "local_obj");
        this.f14992c.removeJavascriptInterface("searchBoxJavaBridge_");
    }

    /* renamed from: a */
    public C4300q m17035a(boolean z) {
        if (z) {
            this.f14997h.setVisibility(8);
        } else {
            this.f14997h.setVisibility(0);
        }
        return this;
    }

    /* renamed from: a */
    public void m17036a() {
        this.f14993d.setContentView(this.f15000k);
        if (this.f14996g != null) {
            this.f14992c.postUrl(this.f14995f, this.f14996g);
        } else if (this.f15003n.equals("jdpay_wap") || this.f15003n.equals("bfb_wap") || this.f15003n.equals("fqlpay_wap")) {
            this.f14992c.loadUrl("file:///android_asset/pingpp_web.html");
        } else {
            this.f14992c.loadUrl(this.f14995f);
        }
    }

    /* renamed from: b */
    public void m17037b() {
        if ("fqlpay_wap".equals(this.f15003n)) {
            this.f14999j.setBackgroundResource(this.f14993d.getResources().getIdentifier("fqlpay_wap_color", "color", this.f14993d.getPackageName()));
        } else if ("mmdpay_wap".equals(this.f15003n)) {
            this.f14999j.setBackgroundResource(this.f14993d.getResources().getIdentifier("mmdpay_wap_color", "color", this.f14993d.getPackageName()));
        } else if ("qgbc_wap".equals(this.f15003n)) {
            this.f14999j.setBackgroundResource(this.f14993d.getResources().getIdentifier("qgbc_wap_color", "color", this.f14993d.getPackageName()));
        }
    }
}
