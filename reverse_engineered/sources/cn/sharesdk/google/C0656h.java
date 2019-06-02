package cn.sharesdk.google;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.C0561d;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.RegisterView;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.DeviceHelper;

/* compiled from: WebShareActivity */
/* renamed from: cn.sharesdk.google.h */
public class C0656h extends FakeActivity {
    /* renamed from: a */
    private String f1535a;
    /* renamed from: b */
    private PlatformActionListener f1536b;
    /* renamed from: c */
    private C0649d f1537c;
    /* renamed from: d */
    private RegisterView f1538d;
    /* renamed from: e */
    private WebView f1539e;
    /* renamed from: f */
    private boolean f1540f;
    /* renamed from: g */
    private boolean f1541g;

    /* compiled from: WebShareActivity */
    /* renamed from: cn.sharesdk.google.h$1 */
    class C06541 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C0656h f1533a;

        /* compiled from: WebShareActivity */
        /* renamed from: cn.sharesdk.google.h$1$1 */
        class C06531 extends Thread {
            /* renamed from: a */
            final /* synthetic */ C06541 f1532a;

            C06531(C06541 c06541) {
                this.f1532a = c06541;
            }

            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    this.f1532a.f1533a.finish();
                    this.f1532a.f1533a.f1536b.onCancel(null, 0);
                }
            }
        }

        C06541(C0656h c0656h) {
            this.f1533a = c0656h;
        }

        public void onClick(View view) {
            new C06531(this).start();
        }
    }

    /* compiled from: WebShareActivity */
    /* renamed from: cn.sharesdk.google.h$2 */
    class C06552 extends C0561d {
        /* renamed from: a */
        final /* synthetic */ C0656h f1534a;

        C06552(C0656h c0656h) {
            this.f1534a = c0656h;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    /* renamed from: a */
    public void m2495a(String str) {
        this.f1535a = str;
    }

    /* renamed from: a */
    public void m2494a(PlatformActionListener platformActionListener) {
        this.f1536b = platformActionListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f1537c == null) {
            this.f1537c = m2492b();
            if (this.f1537c == null) {
                this.f1537c = new C0649d();
            }
        }
        this.f1537c.m2459a(activity);
    }

    /* renamed from: b */
    private C0649d m2492b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("GooglePlusWebShareAdapter");
            if (string == null || string.length() <= 0) {
                return null;
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof C0649d) {
                return (C0649d) newInstance;
            }
            return null;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return null;
        }
    }

    public void onCreate() {
        this.f1538d = m2493a();
        try {
            int stringRes = C4275R.getStringRes(getContext(), "ssdk_share_to_googleplus");
            if (stringRes > 0) {
                this.f1538d.m2020c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            this.f1538d.m2020c().setVisibility(8);
        }
        this.f1537c.m2461a(this.f1538d.m2021d());
        this.f1537c.m2460a(this.f1538d.m2019b());
        this.f1537c.m2462a(this.f1538d.m2020c());
        this.f1537c.m2458a();
        this.activity.setContentView(this.f1538d);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.f1540f = true;
            finish();
            this.f1536b.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.f1538d.m2019b().loadUrl(this.f1535a);
    }

    /* renamed from: a */
    protected RegisterView m2493a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m2020c().getChildAt(registerView.m2020c().getChildCount() - 1).setVisibility(8);
        registerView.m2017a().setOnClickListener(new C06541(this));
        this.f1539e = registerView.m2019b();
        WebSettings settings = this.f1539e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        this.f1539e.setVerticalScrollBarEnabled(false);
        this.f1539e.setHorizontalScrollBarEnabled(false);
        CookieSyncManager.createInstance(this.activity);
        CookieManager.getInstance().removeAllCookie();
        this.f1539e.setWebViewClient(new C06552(this));
        return registerView;
    }

    public void onStart() {
        if (this.f1537c != null) {
            this.f1537c.m2464c();
        }
    }

    public void onPause() {
        if (this.f1537c != null) {
            this.f1537c.m2465d();
        }
    }

    public void onResume() {
        if (this.f1537c != null) {
            this.f1537c.m2466e();
        }
    }

    public void onStop() {
        if (this.f1537c != null) {
            this.f1537c.m2467f();
        }
    }

    public void onRestart() {
        if (this.f1537c != null) {
            this.f1537c.m2468g();
        }
    }

    public void onDestroy() {
        if (!(this.f1540f || this.f1541g)) {
            this.f1536b.onComplete(null, 9, null);
        }
        if (this.f1537c != null) {
            this.f1537c.m2463b();
        }
    }

    public boolean onFinish() {
        if (this.f1537c != null) {
            return this.f1537c.m2469h();
        }
        return super.onFinish();
    }
}
