package cn.sharesdk.facebook;

import android.os.Bundle;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.utils.C0621d;
import com.baidu.mapapi.SDKInitializer;
import com.mob.tools.utils.C4275R;

/* compiled from: FacebookWebViewClient */
/* renamed from: cn.sharesdk.facebook.c */
public class C0563c extends C0562b {

    /* compiled from: FacebookWebViewClient */
    /* renamed from: cn.sharesdk.facebook.c$1 */
    class C05601 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0563c f1187a;

        C05601(C0563c c0563c) {
            this.f1187a = c0563c;
        }

        public void run() {
            this.f1187a.a.finish();
        }
    }

    public C0563c(C0584e c0584e) {
        super(c0584e);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith(this.b)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        webView.stopLoading();
        webView.postDelayed(new C05601(this), 500);
        m1948a(str);
        return true;
    }

    /* renamed from: a */
    protected void m1948a(String str) {
        Bundle urlToBundle = C4275R.urlToBundle(str);
        String string = urlToBundle.getString("error_message");
        if (!(string == null || this.c == null)) {
            string = "error_message ==>>" + string + "\n" + "error_code ==>>" + urlToBundle.getString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
            this.c.onError(new Throwable(str));
        }
        if (string == null) {
            String string2 = urlToBundle.getString("access_token");
            string = urlToBundle.containsKey("expires_in") ? urlToBundle.getString("expires_in") : "-1";
            if (this.c != null) {
                int parseInt;
                urlToBundle = new Bundle();
                urlToBundle.putString("oauth_token", string2);
                urlToBundle.putString("oauth_token_secret", "");
                try {
                    parseInt = C4275R.parseInt(string);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    parseInt = -1;
                }
                urlToBundle.putInt("oauth_token_expires", parseInt);
                this.c.onComplete(urlToBundle);
            }
        }
    }
}
