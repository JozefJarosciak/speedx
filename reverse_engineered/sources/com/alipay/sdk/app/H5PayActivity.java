package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.util.C0885l;

public class H5PayActivity extends Activity {
    /* renamed from: a */
    private WebView f1904a;
    /* renamed from: b */
    private WebViewClient f1905b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("url");
            if (C0885l.m3473b(string)) {
                String string2 = extras.getString("cookie");
                super.requestWindowFeature(1);
                this.f1904a = C0885l.m3460a((Activity) this, string, string2);
                this.f1905b = new C0815b(this);
                this.f1904a.setWebViewClient(this.f1905b);
                return;
            }
            finish();
        } catch (Exception e) {
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.f1904a.canGoBack()) {
            C0821h.f1929a = C0821h.m3171a();
            finish();
        } else if (((C0815b) this.f1905b).f1917a) {
            C0822i a = C0822i.m3178a(C0822i.NETWORK_ERROR.f1938h);
            C0821h.f1929a = C0821h.m3172a(a.f1938h, a.f1939i, "");
            finish();
        }
    }

    public void finish() {
        mo2423a();
        super.finish();
    }

    /* renamed from: a */
    public void mo2423a() {
        Object obj = PayTask.f1909a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f1904a != null) {
            this.f1904a.removeAllViews();
            try {
                this.f1904a.destroy();
            } catch (Throwable th) {
            }
            this.f1904a = null;
        }
    }
}
