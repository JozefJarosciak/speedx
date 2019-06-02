package io.rong.imkit.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import io.rong.common.RongWebView;
import io.rong.imkit.C4974R;

public class RongWebviewActivity extends Activity {
    private static final String TAG = "RongWebviewActivity";
    private String mPrevUrl;
    private ProgressBar mProgressBar;
    private RongWebView mWebView;

    private class RongWebChromeClient extends WebChromeClient {
        private RongWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                RongWebviewActivity.this.mProgressBar.setVisibility(8);
            } else {
                if (RongWebviewActivity.this.mProgressBar.getVisibility() == 8) {
                    RongWebviewActivity.this.mProgressBar.setVisibility(0);
                }
                RongWebviewActivity.this.mProgressBar.setProgress(i);
            }
            super.onProgressChanged(webView, i);
        }
    }

    private class RongWebviewClient extends WebViewClient {
        private RongWebviewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (RongWebviewActivity.this.mPrevUrl == null) {
                RongWebviewActivity.this.mPrevUrl = str;
                RongWebviewActivity.this.mWebView.loadUrl(str);
                return true;
            } else if (RongWebviewActivity.this.mPrevUrl.equals(str)) {
                return false;
            } else {
                RongWebviewActivity.this.mPrevUrl = str;
                RongWebviewActivity.this.mWebView.loadUrl(str);
                return true;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4974R.layout.rc_ac_webview);
        Intent intent = getIntent();
        this.mWebView = (RongWebView) findViewById(C4974R.id.rc_webview);
        this.mProgressBar = (ProgressBar) findViewById(C4974R.id.rc_web_progressbar);
        this.mWebView.setVerticalScrollbarOverlay(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setSupportZoom(false);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.setWebViewClient(new RongWebviewClient());
        this.mWebView.setWebChromeClient(new RongWebChromeClient());
        String stringExtra = intent.getStringExtra("url");
        Uri data = intent.getData();
        if (stringExtra != null) {
            this.mPrevUrl = stringExtra;
            this.mWebView.loadUrl(stringExtra);
        } else if (data != null) {
            this.mPrevUrl = data.toString();
            this.mWebView.loadUrl(data.toString());
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.mWebView.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.mWebView.goBack();
        return true;
    }
}
