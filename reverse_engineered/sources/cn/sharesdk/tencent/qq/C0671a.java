package cn.sharesdk.tencent.qq;

import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;

/* compiled from: QQAuthorizeWebviewClient */
/* renamed from: cn.sharesdk.tencent.qq.a */
public class C0671a extends C0562b {
    public C0671a(C0584e c0584e) {
        super(c0584e);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, final String str) {
        if (str.startsWith(this.b)) {
            webView.setVisibility(4);
            webView.stopLoading();
            this.a.finish();
            new Thread(this) {
                /* renamed from: b */
                final /* synthetic */ C0671a f1588b;

                public void run() {
                    try {
                        this.f1588b.m2596a(str);
                    } catch (Throwable th) {
                        C0621d.m2279a().d(th);
                    }
                }
            }.start();
        } else {
            webView.loadUrl(str);
        }
        return true;
    }

    /* renamed from: a */
    protected void m2596a(String str) {
        if (str.startsWith(this.b)) {
            str = str.substring(str.indexOf(35) + 1);
        }
        String[] split = str.split(C0869a.f2161b);
        HashMap hashMap = new HashMap();
        for (String split2 : split) {
            String[] split3 = split2.split(SimpleComparison.EQUAL_TO_OPERATION);
            if (split3.length < 2) {
                hashMap.put(URLDecoder.decode(split3[0]), "");
            } else {
                hashMap.put(URLDecoder.decode(split3[0]), URLDecoder.decode(split3[1] == null ? "" : split3[1]));
            }
        }
        m2595a(hashMap);
    }

    /* renamed from: a */
    private void m2595a(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("access_token");
        String str2 = (String) hashMap.get("expires_in");
        String str3 = (String) hashMap.get("error");
        String str4 = (String) hashMap.get("error_description");
        String str5 = (String) hashMap.get("pf");
        String str6 = (String) hashMap.get("pfkey");
        String str7 = (String) hashMap.get("pay_token");
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = C0673b.m2597a(this.a.m2022a().getPlatform()).m2608c(str);
                if (c == null || c.size() <= 0) {
                    if (this.c != null) {
                        this.c.onError(new Throwable());
                    }
                } else if (c.containsKey("openid")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", str);
                    bundle.putString("open_id", String.valueOf(c.get("openid")));
                    bundle.putString("expires_in", str2);
                    bundle.putString("pf", str5);
                    bundle.putString("pfkey", str6);
                    bundle.putString("pay_token", str7);
                    if (this.c != null) {
                        this.c.onComplete(bundle);
                    }
                } else if (this.c != null) {
                    this.c.onError(new Throwable());
                }
            } catch (Throwable th) {
                if (this.c != null) {
                    this.c.onError(th);
                }
            }
        } else if (TextUtils.isEmpty(str3)) {
            this.c.onError(new Throwable());
        } else {
            str = str4 + " (" + str3 + ")";
            if (this.c != null) {
                this.c.onError(new Throwable(str));
            }
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        try {
            Method method = sslErrorHandler.getClass().getMethod("proceed", new Class[0]);
            method.setAccessible(true);
            method.invoke(sslErrorHandler, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
