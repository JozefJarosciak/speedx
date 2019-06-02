package com.beastbikes.framework.ui.android;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.framework.android.p088g.C1465f;
import com.beastbikes.framework.android.p088g.C2803i;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebActivity extends BaseFragmentActivity {
    public static final String EXTRA_CAN_GOBACK = "can_goback";
    public static final String EXTRA_ENTER_ANIMATION = "enter_animation";
    public static final String EXTRA_EXIT_ANIMATION = "exit_animation";
    public static final String EXTRA_HTTP_HEADERS = "additional_http_headers";
    public static final String EXTRA_NONE_ANIMATION = "none_animation";
    public static final String EXTRA_TITLE = "title";
    private WebView browser;
    protected boolean canGoBack = false;
    private FrameLayout container;
    private WebChromeClient defaultWebChromeClient = new DefaultWebChromeClient(this);
    private WebViewClient defaultWebViewClient = new DefaultWebViewClient(this);
    private int enterAnim;
    private int exitAnim;
    private int noneAnim;
    private String targetUrl;
    private String userAgent;

    /* renamed from: com.beastbikes.framework.ui.android.WebActivity$1 */
    class C28251 implements DownloadListener {
        C28251() {
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            DownloadManager downloadManager = (DownloadManager) WebActivity.this.getSystemService("download");
            Request request = new Request(Uri.parse(str));
            if (!TextUtils.isEmpty(str2)) {
                request.addRequestHeader("User-Agent", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                request.addRequestHeader("Content-Disposition", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                request.setMimeType(str4);
            }
            request.allowScanningByMediaScanner();
            request.setAllowedNetworkTypes(2);
            downloadManager.enqueue(request);
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.WebActivity$2 */
    class C28262 implements Runnable {
        C28262() {
        }

        public void run() {
            WebActivity.this.browser.loadUrl(WebActivity.this.targetUrl, WebActivity.this.getRequestHeaders());
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.canGoBack = intent.getBooleanExtra(EXTRA_CAN_GOBACK, this.canGoBack);
        if (VERSION.SDK_INT >= 19) {
            try {
                WebView.setWebContentsDebuggingEnabled(true);
            } catch (Exception e) {
            }
        }
        this.browser = new WebView(this);
        this.container = new FrameLayout(this);
        this.container.addView(this.browser, new LayoutParams(-1, -1));
        setContentView(this.container, new FrameLayout.LayoutParams(-1, -1));
        setupBrowser();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected WebView getBrowser() {
        return this.browser;
    }

    public void finish() {
        super.overridePendingTransition(0, this.exitAnim);
        super.finish();
    }

    protected WebViewClient getWebViewClient() {
        return this.defaultWebViewClient;
    }

    protected WebChromeClient getWebChromeClient() {
        return this.defaultWebChromeClient;
    }

    protected void setWebViewClient(WebViewClient webViewClient) {
        this.defaultWebViewClient = webViewClient;
    }

    protected void setWebChromeClient(WebChromeClient webChromeClient) {
        this.defaultWebChromeClient = webChromeClient;
    }

    protected boolean handleURL(String str) {
        return false;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void setupBrowser() {
        Intent intent = getIntent();
        getPackageName() + "/" + C1465f.b(this);
        WebSettings settings = this.browser.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath());
        settings.setUserAgentString(buildUserAgent(settings.getUserAgentString(), this));
        settings.setBuiltInZoomControls(false);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(true);
        String path = getDir("database", 0).getPath();
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(path);
        settings.setDomStorageEnabled(true);
        this.browser.addJavascriptInterface(new WebActivity$JavaScriptCallback(this, this), "speedx");
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        this.userAgent = settings.getUserAgentString();
        this.browser.setDownloadListener(new C28251());
        this.browser.setWebChromeClient(getWebChromeClient());
        this.browser.setWebViewClient(getWebViewClient());
        this.browser.setBackgroundColor(-14540254);
        this.enterAnim = intent.getIntExtra(EXTRA_ENTER_ANIMATION, 0);
        this.exitAnim = intent.getIntExtra(EXTRA_EXIT_ANIMATION, 0);
        this.noneAnim = intent.getIntExtra(EXTRA_NONE_ANIMATION, 0);
        super.overridePendingTransition(this.enterAnim, this.noneAnim);
        CharSequence stringExtra = intent.getStringExtra(EXTRA_TITLE);
        if (!TextUtils.isEmpty(stringExtra)) {
            setTitle(stringExtra);
        }
        this.targetUrl = intent.getDataString();
        if (!TextUtils.isEmpty(this.targetUrl)) {
            this.browser.post(new C28262());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.canGoBack && !this.browser.getUrl().equals(this.targetUrl)) {
                    this.browser.goBack();
                    return true;
                }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static String buildUserAgent(String str, Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String str2 = Build.FINGERPRINT;
        String appMetaData = getAppMetaData(context, "Channel ID");
        String a = C2803i.m13764a(context);
        try {
            str = str + C0880h.f2220b + str2 + ";Beast/" + packageManager.getPackageInfo(packageName, 0).versionName + "_" + appMetaData + C0880h.f2220b + a;
        } catch (Exception e) {
        }
        return str;
    }

    private static String getAppMetaData(Context context, String str) {
        String str2 = null;
        if (!(context == null || TextUtils.isEmpty(str))) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
                    if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                        str2 = applicationInfo.metaData.getString(str);
                    }
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public Map<String, String> getRequestHeaders() {
        Map hashMap = new HashMap();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundleExtra = intent.getBundleExtra(EXTRA_HTTP_HEADERS);
            if (bundleExtra != null && bundleExtra.size() > 0) {
                for (String str : bundleExtra.keySet()) {
                    CharSequence string = bundleExtra.getString(str);
                    if (!TextUtils.isEmpty(string)) {
                        hashMap.put(str, string);
                    }
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.canGoBack || keyEvent.getKeyCode() != 4 || this.browser.getUrl().equals(this.targetUrl)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.browser.goBack();
        return true;
    }

    protected void receiveAwardSuccess(int i) {
    }

    public void onJsMethodSpeedxBridge(String str) {
    }

    public void onJsMethodFinishClubTransfer() {
    }

    public int onJsMethodGetCountryCode(int[] iArr) {
        return onJsMethodGetCountryCode(iArr);
    }

    public void onJsMethodLightMedal(int i) {
    }

    public void onJsMethodCreatePayment(String str) {
    }
}
