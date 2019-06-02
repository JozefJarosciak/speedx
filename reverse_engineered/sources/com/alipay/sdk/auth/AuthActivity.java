package com.alipay.sdk.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.alipay.sdk.authjs.C0840a;
import com.alipay.sdk.authjs.C0840a.C0839a;
import com.alipay.sdk.authjs.C0841c;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.util.C0885l;
import com.alipay.sdk.util.C0885l.C0884a;
import com.alipay.sdk.widget.C0889a;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public class AuthActivity extends Activity {
    /* renamed from: a */
    static final String f1993a = "params";
    /* renamed from: b */
    static final String f1994b = "redirectUri";
    /* renamed from: c */
    private WebView f1995c;
    /* renamed from: d */
    private String f1996d;
    /* renamed from: e */
    private C0889a f1997e;
    /* renamed from: f */
    private Handler f1998f;
    /* renamed from: g */
    private boolean f1999g;
    /* renamed from: h */
    private boolean f2000h;
    /* renamed from: i */
    private Runnable f2001i = new C0832d(this);

    /* renamed from: com.alipay.sdk.auth.AuthActivity$a */
    private class C0826a extends WebChromeClient {
        /* renamed from: a */
        final /* synthetic */ AuthActivity f1991a;

        private C0826a(AuthActivity authActivity) {
            this.f1991a = authActivity;
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (TextUtils.isEmpty(message)) {
                return super.onConsoleMessage(consoleMessage);
            }
            Object obj = null;
            if (message.startsWith("h5container.message: ")) {
                obj = message.replaceFirst("h5container.message: ", "");
            }
            if (TextUtils.isEmpty(obj)) {
                return super.onConsoleMessage(consoleMessage);
            }
            AuthActivity.m3211b(this.f1991a, obj);
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* renamed from: com.alipay.sdk.auth.AuthActivity$b */
    private class C0827b extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ AuthActivity f1992a;

        private C0827b(AuthActivity authActivity) {
            this.f1992a = authActivity;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            this.f1992a.f2000h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (this.f1992a.f1999g) {
                sslErrorHandler.proceed();
                this.f1992a.f1999g = false;
                return;
            }
            this.f1992a.runOnUiThread(new C0833e(this, sslErrorHandler));
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.toLowerCase().startsWith(C0844a.f2052h.toLowerCase()) || str.toLowerCase().startsWith(C0844a.f2053i.toLowerCase())) {
                try {
                    C0884a a = C0885l.m3461a(this.f1992a, C0885l.f2234b);
                    if (a == null || a.m3459a()) {
                        return true;
                    }
                    if (str.startsWith("intent://platformapi/startapp")) {
                        str = str.replaceFirst(C0844a.f2053i, C0844a.f2052h);
                    }
                    this.f1992a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (Throwable th) {
                    return true;
                }
            } else if (!AuthActivity.m3207a(this.f1992a, str)) {
                return super.shouldOverrideUrlLoading(webView, str);
            } else {
                webView.stopLoading();
                return true;
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthActivity.m3215d(this.f1992a);
            this.f1992a.f1998f.postDelayed(this.f1992a.f2001i, 30000);
            super.onPageStarted(webView, str, bitmap);
        }

        public final void onPageFinished(WebView webView, String str) {
            AuthActivity.m3218g(this.f1992a);
            this.f1992a.f1998f.removeCallbacks(this.f1992a.f2001i);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3204a(AuthActivity authActivity, C0840a c0840a) {
        if (authActivity.f1995c != null && c0840a != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(C0840a.f2029e, c0840a.f2033i);
                jSONObject.put(C0840a.f2031g, c0840a.f2035k);
                jSONObject.put(C0840a.f2030f, c0840a.f2037m);
                jSONObject.put(C0840a.f2032h, c0840a.f2036l);
                authActivity.runOnUiThread(new C0831c(authActivity, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{jSONObject.toString()})));
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ boolean m3207a(AuthActivity authActivity, String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, authActivity.f1996d)) {
                str = str + "?resultCode=150";
            }
            C0836h.m3225a((Activity) authActivity, str);
        }
        authActivity.finish();
        return true;
    }

    /* renamed from: b */
    static /* synthetic */ void m3211b(AuthActivity authActivity, String str) {
        String str2;
        C0841c c0841c = new C0841c(authActivity.getApplicationContext(), new C0830b(authActivity));
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(C0840a.f2029e);
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(C0840a.f2030f);
                    jSONObject2 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
                    String string2 = jSONObject.getString(C0840a.f2031g);
                    String string3 = jSONObject.getString(C0840a.f2028d);
                    C0840a c0840a = new C0840a("call");
                    c0840a.f2034j = string3;
                    c0840a.f2035k = string2;
                    c0840a.f2037m = jSONObject2;
                    c0840a.f2033i = string;
                    c0841c.m3253a(c0840a);
                }
            } catch (Exception e) {
                str2 = string;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        c0841c.m3254a(str2, C0839a.f2022d);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            str2 = null;
            if (!TextUtils.isEmpty(str2)) {
                c0841c.m3254a(str2, C0839a.f2022d);
            }
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m3215d(AuthActivity authActivity) {
        try {
            if (authActivity.f1997e == null) {
                authActivity.f1997e = new C0889a(authActivity, C0889a.f2240a);
            }
            authActivity.f1997e.m3498a();
        } catch (Exception e) {
            authActivity.f1997e = null;
        }
    }

    /* renamed from: g */
    static /* synthetic */ void m3218g(AuthActivity authActivity) {
        if (authActivity.f1997e != null) {
            authActivity.f1997e.m3499b();
        }
        authActivity.f1997e = null;
    }

    protected void onCreate(Bundle bundle) {
        Method method;
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            }
            try {
                this.f1996d = extras.getString(f1994b);
                String string = extras.getString("params");
                if (C0885l.m3473b(string)) {
                    super.requestWindowFeature(1);
                    this.f1998f = new Handler(getMainLooper());
                    View linearLayout = new LinearLayout(this);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                    linearLayout.setOrientation(1);
                    setContentView(linearLayout, layoutParams);
                    this.f1995c = new WebView(this);
                    layoutParams.weight = 1.0f;
                    this.f1995c.setVisibility(0);
                    linearLayout.addView(this.f1995c, layoutParams);
                    WebSettings settings = this.f1995c.getSettings();
                    settings.setUserAgentString(settings.getUserAgentString() + C0885l.m3479d(getApplicationContext()));
                    settings.setRenderPriority(RenderPriority.HIGH);
                    settings.setSupportMultipleWindows(true);
                    settings.setJavaScriptEnabled(true);
                    settings.setSavePassword(false);
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);
                    settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
                    settings.setAllowFileAccess(false);
                    settings.setTextSize(TextSize.NORMAL);
                    this.f1995c.setVerticalScrollbarOverlay(true);
                    this.f1995c.setWebViewClient(new C0827b());
                    this.f1995c.setWebChromeClient(new C0826a());
                    this.f1995c.setDownloadListener(new C0828a(this));
                    this.f1995c.loadUrl(string);
                    if (VERSION.SDK_INT >= 7) {
                        try {
                            method = this.f1995c.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                            if (method != null) {
                                method.invoke(this.f1995c.getSettings(), new Object[]{Boolean.valueOf(true)});
                            }
                        } catch (Exception e) {
                        }
                    }
                    try {
                        this.f1995c.removeJavascriptInterface("searchBoxJavaBridge_");
                        this.f1995c.removeJavascriptInterface("accessibility");
                        this.f1995c.removeJavascriptInterface("accessibilityTraversal");
                    } catch (Throwable th) {
                    }
                    if (VERSION.SDK_INT >= 19) {
                        this.f1995c.getSettings().setCacheMode(1);
                        return;
                    }
                    return;
                }
                finish();
            } catch (Exception e2) {
                finish();
            }
        } catch (Exception e3) {
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.f1995c.canGoBack()) {
            C0836h.m3225a((Activity) this, this.f1996d + "?resultCode=150");
            finish();
        } else if (this.f2000h) {
            C0836h.m3225a((Activity) this, this.f1996d + "?resultCode=150");
            finish();
        }
    }

    /* renamed from: a */
    private boolean m3209a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.f1996d)) {
                str = str + "?resultCode=150";
            }
            C0836h.m3225a((Activity) this, str);
        }
        finish();
        return true;
    }

    /* renamed from: b */
    private void m3212b(String str) {
        String str2;
        C0841c c0841c = new C0841c(getApplicationContext(), new C0830b(this));
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(C0840a.f2029e);
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(C0840a.f2030f);
                    jSONObject2 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
                    String string2 = jSONObject.getString(C0840a.f2031g);
                    String string3 = jSONObject.getString(C0840a.f2028d);
                    C0840a c0840a = new C0840a("call");
                    c0840a.f2034j = string3;
                    c0840a.f2035k = string2;
                    c0840a.f2037m = jSONObject2;
                    c0840a.f2033i = string;
                    c0841c.m3253a(c0840a);
                }
            } catch (Exception e) {
                str2 = string;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        c0841c.m3254a(str2, C0839a.f2022d);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            str2 = null;
            if (!TextUtils.isEmpty(str2)) {
                c0841c.m3254a(str2, C0839a.f2022d);
            }
        }
    }

    /* renamed from: a */
    private void m3205a(C0840a c0840a) {
        if (this.f1995c != null && c0840a != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(C0840a.f2029e, c0840a.f2033i);
                jSONObject.put(C0840a.f2031g, c0840a.f2035k);
                jSONObject.put(C0840a.f2030f, c0840a.f2037m);
                jSONObject.put(C0840a.f2032h, c0840a.f2036l);
                runOnUiThread(new C0831c(this, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{jSONObject.toString()})));
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: a */
    private void m3203a() {
        try {
            if (this.f1997e == null) {
                this.f1997e = new C0889a(this, C0889a.f2240a);
            }
            this.f1997e.m3498a();
        } catch (Exception e) {
            this.f1997e = null;
        }
    }

    /* renamed from: b */
    private void m3210b() {
        if (this.f1997e != null) {
            this.f1997e.m3499b();
        }
        this.f1997e = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f1995c != null) {
            this.f1995c.removeAllViews();
            try {
                this.f1995c.destroy();
            } catch (Throwable th) {
            }
            this.f1995c = null;
        }
    }
}
