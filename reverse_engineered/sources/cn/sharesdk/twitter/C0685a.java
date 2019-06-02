package cn.sharesdk.twitter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mob.tools.utils.C4275R;

/* compiled from: TwitterAuthorizeWebviewClient */
/* renamed from: cn.sharesdk.twitter.a */
public class C0685a extends C0562b {
    /* renamed from: d */
    private boolean f1634d;

    public C0685a(C0584e c0584e) {
        super(c0584e);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b == null || !str.startsWith(this.b)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        webView.stopLoading();
        this.a.finish();
        final String valueOf = String.valueOf(C4275R.urlToBundle(str).get("oauth_verifier"));
        new Thread(this) {
            /* renamed from: b */
            final /* synthetic */ C0685a f1631b;

            public void run() {
                try {
                    this.f1631b.m2646a(valueOf);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                }
            }
        }.start();
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.b != null && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            final String valueOf = String.valueOf(C4275R.urlToBundle(str).get("oauth_verifier"));
            new Thread(this) {
                /* renamed from: b */
                final /* synthetic */ C0685a f1633b;

                public void run() {
                    try {
                        this.f1633b.m2646a(valueOf);
                    } catch (Throwable th) {
                        C0621d.m2279a().d(th);
                    }
                }
            }.start();
        }
        super.onPageStarted(webView, str, bitmap);
    }

    /* renamed from: a */
    public void m2646a(String str) {
        if (!this.f1634d) {
            this.f1634d = true;
            String a = C0686b.m2647a(this.a.m2022a().getPlatform()).m2648a(str);
            if (a != null && a.length() > 0) {
                String[] split = a.split(C0869a.f2161b);
                Bundle bundle = new Bundle();
                for (String str2 : split) {
                    if (str2 != null) {
                        String[] split2 = str2.split(SimpleComparison.EQUAL_TO_OPERATION);
                        if (split2.length >= 2) {
                            bundle.putString(split2[0], split2[1]);
                        }
                    }
                }
                if (bundle == null || bundle.size() <= 0) {
                    if (this.c != null) {
                        this.c.onError(new Throwable());
                    }
                } else if (this.c != null) {
                    this.c.onComplete(bundle);
                }
            } else if (this.c != null) {
                this.c.onError(new Throwable());
            }
        }
    }
}
