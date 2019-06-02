package cn.sharesdk.facebook;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.C0561d;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.RegisterView;
import cn.sharesdk.framework.utils.C0621d;
import com.baidu.mapapi.SDKInitializer;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;

/* compiled from: WebShareActivity */
/* renamed from: cn.sharesdk.facebook.e */
public class C0570e extends FakeActivity {
    /* renamed from: a */
    private String f1208a;
    /* renamed from: b */
    private PlatformActionListener f1209b;
    /* renamed from: c */
    private C0557a f1210c;
    /* renamed from: d */
    private RegisterView f1211d;
    /* renamed from: e */
    private WebView f1212e;
    /* renamed from: f */
    private boolean f1213f;
    /* renamed from: g */
    private boolean f1214g;

    /* compiled from: WebShareActivity */
    /* renamed from: cn.sharesdk.facebook.e$1 */
    class C05681 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C0570e f1206a;

        /* compiled from: WebShareActivity */
        /* renamed from: cn.sharesdk.facebook.e$1$1 */
        class C05671 extends Thread {
            /* renamed from: a */
            final /* synthetic */ C05681 f1205a;

            C05671(C05681 c05681) {
                this.f1205a = c05681;
            }

            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    this.f1205a.f1206a.finish();
                    this.f1205a.f1206a.f1209b.onCancel(null, 0);
                }
            }
        }

        C05681(C0570e c0570e) {
            this.f1206a = c0570e;
        }

        public void onClick(View view) {
            new C05671(this).start();
        }
    }

    /* compiled from: WebShareActivity */
    /* renamed from: cn.sharesdk.facebook.e$2 */
    class C05692 extends C0561d {
        /* renamed from: a */
        final /* synthetic */ C0570e f1207a;

        C05692(C0570e c0570e) {
            this.f1207a = c0570e;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str != null && str.startsWith("fbconnect://success")) {
                this.f1207a.m1970b(str);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    /* renamed from: a */
    public void m1973a(String str) {
        this.f1208a = str;
    }

    /* renamed from: a */
    public void m1972a(PlatformActionListener platformActionListener) {
        this.f1209b = platformActionListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f1210c == null) {
            this.f1210c = m1969b();
            if (this.f1210c == null) {
                this.f1210c = new C0557a();
            }
        }
        this.f1210c.m1925a(activity);
    }

    /* renamed from: b */
    private C0557a m1969b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("FBWebShareAdapter");
            if (string == null || string.length() <= 0) {
                return null;
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof C0557a) {
                return (C0557a) newInstance;
            }
            return null;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return null;
        }
    }

    public void onCreate() {
        this.f1211d = m1971a();
        try {
            int stringRes = C4275R.getStringRes(getContext(), "ssdk_share_to_facebook");
            if (stringRes > 0) {
                this.f1211d.m2020c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            this.f1211d.m2020c().setVisibility(8);
        }
        this.f1210c.m1927a(this.f1211d.m2021d());
        this.f1210c.m1926a(this.f1211d.m2019b());
        this.f1210c.m1928a(this.f1211d.m2020c());
        this.f1210c.m1924a();
        this.activity.setContentView(this.f1211d);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.f1213f = true;
            finish();
            this.f1209b.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.f1211d.m2019b().loadUrl(this.f1208a);
    }

    /* renamed from: a */
    protected RegisterView m1971a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m2020c().getChildAt(registerView.m2020c().getChildCount() - 1).setVisibility(8);
        registerView.m2017a().setOnClickListener(new C05681(this));
        this.f1212e = registerView.m2019b();
        WebSettings settings = this.f1212e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        this.f1212e.setVerticalScrollBarEnabled(false);
        this.f1212e.setHorizontalScrollBarEnabled(false);
        this.f1212e.setWebViewClient(new C05692(this));
        return registerView;
    }

    /* renamed from: b */
    private void m1970b(String str) {
        String str2 = str == null ? "" : new String(str);
        Bundle urlToBundle = C4275R.urlToBundle(str);
        if (urlToBundle == null) {
            this.f1213f = true;
            finish();
            this.f1209b.onError(null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        CharSequence string = urlToBundle.getString("post_id");
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(string)) {
            hashMap.put("post_id", string);
        }
        if (urlToBundle.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || urlToBundle.containsKey("error")) {
            if (this.f1209b != null) {
                this.f1209b.onError(null, 9, new Throwable(C4275R.encodeUrl(urlToBundle)));
            }
            this.f1213f = true;
            finish();
            return;
        }
        this.f1214g = true;
        finish();
        this.f1209b.onComplete(null, 0, hashMap);
    }

    public void onStart() {
        if (this.f1210c != null) {
            this.f1210c.m1930c();
        }
    }

    public void onPause() {
        if (this.f1210c != null) {
            this.f1210c.m1931d();
        }
    }

    public void onResume() {
        if (this.f1210c != null) {
            this.f1210c.m1932e();
        }
    }

    public void onStop() {
        if (this.f1210c != null) {
            this.f1210c.m1933f();
        }
    }

    public void onRestart() {
        if (this.f1210c != null) {
            this.f1210c.m1934g();
        }
    }

    public void onDestroy() {
        if (!(this.f1213f || this.f1214g)) {
            this.f1209b.onCancel(null, 0);
        }
        if (this.f1210c != null) {
            this.f1210c.m1929b();
        }
    }

    public boolean onFinish() {
        if (this.f1210c != null) {
            return this.f1210c.m1935h();
        }
        return super.onFinish();
    }
}
