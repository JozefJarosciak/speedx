package com.beastbikes.android.modules.strava.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.strava.p075a.C2345a;
import com.beastbikes.framework.ui.android.DefaultWebChromeClient;
import com.beastbikes.framework.ui.android.DefaultWebViewClient;
import com.beastbikes.framework.ui.android.WebActivity;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StravaAuthWebActivity extends WebActivity {
    /* renamed from: b */
    private static final Logger f11199b = LoggerFactory.getLogger("StravaAuthWebActivity");
    /* renamed from: a */
    String f11200a = "StravaAuthWebActivity";
    /* renamed from: c */
    private C1802i f11201c;

    protected void onCreate(Bundle bundle) {
        setTitle(C1373R.string.label_service_manager);
        if (getIntent().getBooleanExtra("clear_cookie", false)) {
            CookieManager.getInstance().removeAllCookie();
        }
        super.setWebViewClient(new DefaultWebViewClient(this, this) {
            /* renamed from: a */
            final /* synthetic */ StravaAuthWebActivity f11195a;

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Uri parse = Uri.parse(str);
                if (parse != null) {
                    String host = parse.getHost();
                    if (parse.getScheme().equals("speedx") && host.equals("strava_callback_url_for_speedx")) {
                        this.f11195a.m11998a(parse.getQueryParameter("code"));
                    }
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                try {
                    webView.loadUrl("file:///android_asset/webkit/empty.html");
                } catch (Exception e) {
                }
            }
        });
        super.setWebChromeClient(new DefaultWebChromeClient(this, this) {
            /* renamed from: a */
            final /* synthetic */ StravaAuthWebActivity f11196a;

            public void onReceivedTitle(WebView webView, String str) {
            }
        });
        super.onCreate(bundle);
    }

    /* renamed from: a */
    public void m11998a(final String str) {
        if (TextUtils.isEmpty(str)) {
            finish();
            return;
        }
        if (this.f11201c == null) {
            this.f11201c = new C1802i((Context) this, null, true);
        }
        this.f11201c.show();
        getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, JSONObject>(this) {
            /* renamed from: b */
            final /* synthetic */ StravaAuthWebActivity f11198b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11994a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11995a((JSONObject) obj);
            }

            /* renamed from: a */
            protected JSONObject m11994a(Void... voidArr) {
                return new C2345a(this.f11198b).m11993a(str);
            }

            /* renamed from: a */
            protected void m11995a(JSONObject jSONObject) {
                Intent intent = this.f11198b.getIntent();
                if (this.f11198b.f11201c != null && this.f11198b.f11201c.isShowing()) {
                    this.f11198b.f11201c.dismiss();
                }
                StravaAuthWebActivity.f11199b.info("return from strava" + jSONObject.toString());
                String optString = jSONObject.optString("access_token");
                if (TextUtils.isEmpty(optString)) {
                    optString = jSONObject.toString();
                }
                String optString2 = jSONObject.optJSONObject("athlete").optString("id");
                if (TextUtils.isEmpty(optString)) {
                    intent.putExtra("error_msg", optString);
                    this.f11198b.setResult(0, intent);
                } else {
                    intent.putExtra("token", optString);
                    intent.putExtra("authkey", optString2);
                    this.f11198b.setResult(-1, intent);
                }
                this.f11198b.finish();
            }
        }, new Void[0]);
    }
}
