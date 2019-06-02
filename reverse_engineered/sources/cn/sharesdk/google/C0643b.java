package cn.sharesdk.google;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.utils.C0621d;
import com.baidu.mapapi.SDKInitializer;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* compiled from: GooglePlusAuthorizeWebviewClient */
/* renamed from: cn.sharesdk.google.b */
public class C0643b extends C0562b {
    /* renamed from: d */
    private boolean f1494d;

    /* compiled from: GooglePlusAuthorizeWebviewClient */
    /* renamed from: cn.sharesdk.google.b$1 */
    class C06411 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0643b f1490a;

        C06411(C0643b c0643b) {
            this.f1490a = c0643b;
        }

        public void run() {
            this.f1490a.a.finish();
        }
    }

    public C0643b(C0584e c0584e) {
        super(c0584e);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith(this.b)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        webView.stopLoading();
        webView.postDelayed(new C06411(this), 500);
        m2416a(str);
        return true;
    }

    /* renamed from: a */
    protected void m2416a(String str) {
        if (!this.f1494d) {
            this.f1494d = true;
            Bundle urlToBundle = C4275R.urlToBundle(str);
            String string = urlToBundle.getString("error");
            String string2 = urlToBundle.getString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
            if (this.c == null) {
                return;
            }
            if (string == null && string2 == null) {
                Object string3 = urlToBundle.getString("code");
                if (TextUtils.isEmpty(string3)) {
                    this.c.onError(new Throwable("Authorize code is empty"));
                }
                m2412a(this.a.m2022a().getPlatform(), string3);
            } else if (string.equals("access_denied")) {
                this.c.onCancel();
            } else {
                int i = 0;
                try {
                    i = C4275R.parseInt(string2);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                }
                this.c.onError(new Throwable(string + " (" + i + ")"));
            }
        }
    }

    /* renamed from: a */
    private void m2412a(final Platform platform, final String str) {
        new Thread(this) {
            /* renamed from: c */
            final /* synthetic */ C0643b f1493c;

            public void run() {
                try {
                    String a = C0640a.m2397a(platform).m2398a(platform.getContext(), str);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    return;
                }
                if (a == null) {
                    this.f1493c.c.onError(new Throwable("Authorize token is empty"));
                    return;
                }
                HashMap fromJson = new Hashon().fromJson(a);
                Bundle bundle = new Bundle();
                bundle.putString("access_token", String.valueOf(fromJson.get("access_token")));
                bundle.putString("expires_in", String.valueOf(fromJson.get("expires_in")));
                bundle.putString("token_type", String.valueOf(fromJson.get("token_type")));
                this.f1493c.c.onComplete(bundle);
            }
        }.start();
    }
}
