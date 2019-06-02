package com.beastbikes.android.modules.setting.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;

@C1459b(a = 2130903410)
public class KeepAliveHelperActivity extends BaseFragmentActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        WebView webView = (WebView) findViewById(C1373R.id.webView);
        webView.loadUrl("https://speedx.com/app/android/keepalivehelper.html");
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }
}
