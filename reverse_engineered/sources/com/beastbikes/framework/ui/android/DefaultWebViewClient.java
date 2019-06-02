package com.beastbikes.framework.ui.android;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebViewClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultWebViewClient extends WebViewClient {
    private static final String API_VERSION = "/api/1.0";
    private static final String DEFAULT_ERROR_PAGE_URL = "file:///android_asset/webkit/error.html";
    private static final String ERROR_HTML = "error.html";
    private static final String TAG = "DefaultWebViewClient";
    private static final String WEBKIT = "webkit";
    private static final Logger logger = LoggerFactory.getLogger(TAG);
    private final WebActivity webActivity;

    public DefaultWebViewClient(WebActivity webActivity) {
        this.webActivity = webActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        logger.debug("Override loadding " + str);
        return this.webActivity.handleURL(str) || super.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        logger.debug("Loading " + str);
    }

    public void onPageFinished(WebView webView, String str) {
        logger.debug("Loading " + str + " complete");
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        int i2 = 0;
        logger.error(String.format("Load %s failed, error %d (%s)", new Object[]{str2, Integer.valueOf(i), str}));
        try {
            String[] list = webView.getContext().getAssets().list(WEBKIT);
            if (list != null) {
                int length = list.length;
                while (i2 < length) {
                    if (ERROR_HTML.equalsIgnoreCase(list[i2])) {
                        webView.loadUrl(DEFAULT_ERROR_PAGE_URL);
                        return;
                    }
                    i2++;
                }
            }
        } catch (Throwable e) {
            logger.warn("Default error page not found", e);
        }
    }

    public void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
        HitTestResult hitTestResult = webView.getHitTestResult();
        if (hitTestResult != null) {
            switch (hitTestResult.getType()) {
                case 5:
                case 8:
                    logger.info(hitTestResult.getType() + "------" + str);
                    return;
                default:
                    return;
            }
        }
    }
}
