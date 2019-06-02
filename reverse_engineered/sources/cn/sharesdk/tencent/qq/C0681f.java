package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import cn.sharesdk.framework.C0561d;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.RegisterView;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.util.C0882j;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;

/* compiled from: WebShareActivity */
/* renamed from: cn.sharesdk.tencent.qq.f */
public class C0681f extends FakeActivity {
    /* renamed from: a */
    private String f1617a;
    /* renamed from: b */
    private PlatformActionListener f1618b;
    /* renamed from: c */
    private String f1619c;
    /* renamed from: d */
    private QQWebShareAdapter f1620d;
    /* renamed from: e */
    private RegisterView f1621e;
    /* renamed from: f */
    private WebView f1622f;
    /* renamed from: g */
    private boolean f1623g;
    /* renamed from: h */
    private boolean f1624h;

    /* compiled from: WebShareActivity */
    /* renamed from: cn.sharesdk.tencent.qq.f$1 */
    class C06791 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C0681f f1615a;

        /* compiled from: WebShareActivity */
        /* renamed from: cn.sharesdk.tencent.qq.f$1$1 */
        class C06781 extends Thread {
            /* renamed from: a */
            final /* synthetic */ C06791 f1614a;

            C06781(C06791 c06791) {
                this.f1614a = c06791;
            }

            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    this.f1614a.f1615a.finish();
                    this.f1614a.f1615a.f1618b.onCancel(null, 0);
                }
            }
        }

        C06791(C0681f c0681f) {
            this.f1615a = c0681f;
        }

        public void onClick(View view) {
            new C06781(this).start();
        }
    }

    /* compiled from: WebShareActivity */
    /* renamed from: cn.sharesdk.tencent.qq.f$2 */
    class C06802 extends C0561d {
        /* renamed from: a */
        final /* synthetic */ C0681f f1616a;

        C06802(C0681f c0681f) {
            this.f1616a = c0681f;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str != null && str.startsWith(this.f1616a.f1619c)) {
                this.f1616a.m2629c(str);
            } else if (str != null && str.startsWith("http://www.myapp.com/down/")) {
                this.f1616a.f1624h = true;
            } else if (str != null && str.startsWith("wtloginmqq://")) {
                int stringRes = C4275R.getStringRes(this.f1616a.activity, "ssdk_use_login_button");
                if (stringRes <= 0) {
                    return true;
                }
                Toast.makeText(this.f1616a.activity, stringRes, 0).show();
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (str == null || !str.startsWith("wtloginmqq://")) {
                super.onPageStarted(webView, str, bitmap);
                return;
            }
            int stringRes = C4275R.getStringRes(this.f1616a.activity, "ssdk_use_login_button");
            if (stringRes > 0) {
                Toast.makeText(this.f1616a.activity, stringRes, 0).show();
            }
        }
    }

    /* renamed from: a */
    public void m2635a(String str) {
        this.f1617a = str;
    }

    /* renamed from: a */
    public void m2634a(PlatformActionListener platformActionListener) {
        this.f1618b = platformActionListener;
    }

    /* renamed from: b */
    public void m2636b(String str) {
        this.f1619c = "tencent" + str;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f1620d == null) {
            this.f1620d = m2626b();
            if (this.f1620d == null) {
                this.f1620d = new QQWebShareAdapter();
            }
        }
        this.f1620d.setActivity(activity);
    }

    /* renamed from: b */
    private QQWebShareAdapter m2626b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("QQWebShareAdapter");
            if (string == null || string.length() <= 0) {
                return null;
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof QQWebShareAdapter) {
                return (QQWebShareAdapter) newInstance;
            }
            return null;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return null;
        }
    }

    public void onCreate() {
        this.f1621e = m2633a();
        try {
            int stringRes = C4275R.getStringRes(getContext(), "ssdk_share_to_qq");
            if (stringRes > 0) {
                this.f1621e.m2020c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            this.f1621e.m2020c().setVisibility(8);
        }
        this.f1620d.setBodyView(this.f1621e.m2021d());
        this.f1620d.setWebView(this.f1621e.m2019b());
        this.f1620d.setTitleView(this.f1621e.m2020c());
        this.f1620d.onCreate();
        this.activity.setContentView(this.f1621e);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.f1623g = true;
            finish();
            this.f1618b.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.f1621e.m2019b().loadUrl(this.f1617a);
    }

    /* renamed from: a */
    protected RegisterView m2633a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m2020c().getChildAt(registerView.m2020c().getChildCount() - 1).setVisibility(8);
        registerView.m2017a().setOnClickListener(new C06791(this));
        this.f1622f = registerView.m2019b();
        WebSettings settings = this.f1622f.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        this.f1622f.setVerticalScrollBarEnabled(false);
        this.f1622f.setHorizontalScrollBarEnabled(false);
        this.f1622f.setWebViewClient(new C06802(this));
        return registerView;
    }

    /* renamed from: c */
    private void m2629c(String str) {
        String str2 = str == null ? "" : new String(str);
        Bundle urlToBundle = C4275R.urlToBundle(str);
        if (urlToBundle == null) {
            this.f1623g = true;
            finish();
            this.f1618b.onError(null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        String string = urlToBundle.getString(C0861d.f2143o);
        if ("share".equals(string) || "shareToQQ".equals(string)) {
            string = urlToBundle.getString(C0882j.f2229c);
            if ("cancel".equals(string)) {
                finish();
                this.f1618b.onCancel(null, 0);
                return;
            } else if ("complete".equals(string)) {
                Object string2 = urlToBundle.getString("response");
                if (TextUtils.isEmpty(string2)) {
                    this.f1623g = true;
                    finish();
                    this.f1618b.onError(null, 0, new Throwable("response empty" + str2));
                    return;
                }
                this.f1624h = true;
                finish();
                this.f1618b.onComplete(null, 0, new Hashon().fromJson(string2));
                return;
            } else {
                this.f1623g = true;
                finish();
                this.f1618b.onError(null, 0, new Throwable("operation failed: " + str2));
                return;
            }
        }
        this.f1623g = true;
        finish();
        this.f1618b.onError(null, 0, new Throwable("action error: " + str2));
    }

    public void onStart() {
        if (this.f1620d != null) {
            this.f1620d.onStart();
        }
    }

    public void onPause() {
        if (this.f1620d != null) {
            this.f1620d.onPause();
        }
    }

    public void onResume() {
        if (this.f1620d != null) {
            this.f1620d.onResume();
        }
    }

    public void onStop() {
        if (this.f1620d != null) {
            this.f1620d.onStop();
        }
    }

    public void onRestart() {
        if (this.f1620d != null) {
            this.f1620d.onRestart();
        }
    }

    public void onDestroy() {
        if (!(this.f1623g || this.f1624h)) {
            this.f1618b.onCancel(null, 0);
        }
        if (this.f1620d != null) {
            this.f1620d.onDestroy();
        }
    }

    public boolean onFinish() {
        if (this.f1620d != null) {
            return this.f1620d.onFinish();
        }
        return super.onFinish();
    }
}
