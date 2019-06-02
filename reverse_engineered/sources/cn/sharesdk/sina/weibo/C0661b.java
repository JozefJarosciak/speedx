package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

/* compiled from: SinaWeiboAuthorizeWebviewClient */
/* renamed from: cn.sharesdk.sina.weibo.b */
public class C0661b extends C0562b {
    /* renamed from: d */
    private boolean f1557d;

    public C0661b(C0584e c0584e) {
        super(c0584e);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!TextUtils.isEmpty(this.b) && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            m2529a(str);
            return true;
        } else if (!str.startsWith("sms:")) {
            return super.shouldOverrideUrlLoading(webView, str);
        } else {
            String substring = str.substring(4);
            try {
                Intent b = m2526b(substring);
                b.setPackage("com.android.mms");
                webView.getContext().startActivity(b);
                return true;
            } catch (Throwable th) {
                if (this.c == null) {
                    return true;
                }
                this.c.onError(th);
                return true;
            }
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(this.b) && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            m2529a(str);
        } else if (str.startsWith("sms:")) {
            String substring = str.substring(4);
            try {
                Intent b = m2526b(substring);
                b.setPackage("com.android.mms");
                webView.getContext().startActivity(b);
            } catch (Throwable th) {
                if (this.c != null) {
                    this.c.onError(th);
                }
            }
        } else {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    /* renamed from: a */
    protected void m2529a(String str) {
        if (!this.f1557d) {
            this.f1557d = true;
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
                m2525a(this.a.m2022a().getPlatform(), string3);
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
    private void m2525a(final Platform platform, final String str) {
        new Thread(this) {
            /* renamed from: c */
            final /* synthetic */ C0661b f1556c;

            public void run() {
                try {
                    String a = C0665d.m2538a(platform).m2546a(platform.getContext(), str);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    return;
                }
                if (a == null) {
                    this.f1556c.c.onError(new Throwable("Authorize token is empty"));
                    return;
                }
                HashMap fromJson = new Hashon().fromJson(a);
                Bundle bundle = new Bundle();
                bundle.putString("uid", String.valueOf(fromJson.get("uid")));
                bundle.putString("remind_in", String.valueOf(fromJson.get("remind_in")));
                bundle.putString("expires_in", String.valueOf(fromJson.get("expires_in")));
                bundle.putString("access_token", String.valueOf(fromJson.get("access_token")));
                this.f1556c.c.onComplete(bundle);
            }
        }.start();
    }

    /* renamed from: b */
    private Intent m2526b(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", "");
        intent.setFlags(268435456);
        return intent;
    }
}
