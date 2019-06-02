package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.ResizeLayout.OnResizeListener;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;

/* compiled from: WebAuthorizeActivity */
/* renamed from: cn.sharesdk.framework.authorize.e */
public class C0584e extends C0578a implements Callback, OnResizeListener {
    /* renamed from: b */
    protected AuthorizeListener f1253b;
    /* renamed from: c */
    private AuthorizeAdapter f1254c;
    /* renamed from: d */
    private RegisterView f1255d;
    /* renamed from: e */
    private WebView f1256e;

    /* compiled from: WebAuthorizeActivity */
    /* renamed from: cn.sharesdk.framework.authorize.e$1 */
    class C05811 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C0584e f1250a;

        /* compiled from: WebAuthorizeActivity */
        /* renamed from: cn.sharesdk.framework.authorize.e$1$1 */
        class C05801 extends Thread {
            /* renamed from: a */
            final /* synthetic */ C05811 f1249a;

            C05801(C05811 c05811) {
                this.f1249a = c05811;
            }

            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Throwable th) {
                    C0621d.m2279a().w(th);
                    AuthorizeListener authorizeListener = this.f1249a.f1250a.a.getAuthorizeListener();
                    if (authorizeListener != null) {
                        authorizeListener.onCancel();
                    }
                    this.f1249a.f1250a.finish();
                }
            }
        }

        C05811(C0584e c0584e) {
            this.f1250a = c0584e;
        }

        public void onClick(View view) {
            new C05801(this).start();
        }
    }

    /* compiled from: WebAuthorizeActivity */
    /* renamed from: cn.sharesdk.framework.authorize.e$2 */
    class C05822 extends Thread {
        /* renamed from: a */
        final /* synthetic */ C0584e f1251a;

        C05822(C0584e c0584e) {
            this.f1251a = c0584e;
        }

        public void run() {
            try {
                Message message = new Message();
                message.what = 2;
                if ("none".equals(DeviceHelper.getInstance(this.f1251a.activity).getDetailNetworkTypeForStatic())) {
                    message.arg1 = 1;
                    UIHandler.sendMessage(message, this.f1251a);
                    return;
                }
                if (ShareSDK.isRemoveCookieOnAuthorize()) {
                    CookieSyncManager.createInstance(this.f1251a.activity);
                    CookieManager.getInstance().removeAllCookie();
                }
                message.obj = this.f1251a.a.getAuthorizeUrl();
                UIHandler.sendMessage(message, this.f1251a);
            } catch (Throwable th) {
                C0621d.m2279a().w(th);
            }
        }
    }

    /* compiled from: WebAuthorizeActivity */
    /* renamed from: cn.sharesdk.framework.authorize.e$a */
    private static class C0583a implements Interpolator {
        /* renamed from: a */
        private float[] f1252a;

        private C0583a() {
            this.f1252a = new float[]{0.0f, 0.02692683f, 0.053847015f, 0.080753915f, 0.10764089f, 0.13450131f, 0.16132854f, 0.18811597f, 0.21485697f, 0.24154496f, 0.26817337f, 0.2947356f, 0.3212251f, 0.34763536f, 0.37395984f, 0.40019205f, 0.42632553f, 0.4523538f, 0.47827047f, 0.50406915f, 0.52974343f, 0.555287f, 0.5806936f, 0.60595685f, 0.6310707f, 0.65602875f, 0.68082494f, 0.70545316f, 0.72990733f, 0.75418144f, 0.7782694f, 0.8021654f, 0.8258634f, 0.8493577f, 0.8726424f, 0.89571184f, 0.9185602f, 0.94118196f, 0.9635715f, 0.9857233f, 1.0076319f, 1.0292919f, 1.0506978f, 1.0718446f, 1.0927268f, 1.1133395f, 1.1336775f, 1.1537358f, 1.1735094f, 1.1929934f, 1.1893399f, 1.1728106f, 1.1565471f, 1.1405534f, 1.1248333f, 1.1093911f, 1.0942302f, 1.0793544f, 1.0647675f, 1.050473f, 1.0364745f, 1.0227754f, 1.0093791f, 0.99628896f, 0.9835081f, 0.9710398f, 0.958887f, 0.9470527f, 0.93553996f, 0.9243516f, 0.91349024f, 0.90295863f, 0.90482706f, 0.9114033f, 0.91775465f, 0.9238795f, 0.9297765f, 0.93544406f, 0.9408808f, 0.94608533f, 0.95105654f, 0.955793f, 0.9602937f, 0.9645574f, 0.96858317f, 0.9723699f, 0.97591674f, 0.97922283f, 0.9822872f, 0.9851093f, 0.98768836f, 0.9900237f, 0.9921147f, 0.993961f, 0.99556196f, 0.9969173f, 0.9980267f, 0.99888986f, 0.99950653f, 0.9998766f, 1.0f};
        }

        public float getInterpolation(float f) {
            int i = 100;
            int i2 = (int) (100.0f * f);
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 <= 100) {
                i = i2;
            }
            return this.f1252a[i];
        }
    }

    /* renamed from: a */
    public void m2028a(AuthorizeListener authorizeListener) {
        this.f1253b = authorizeListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f1254c == null) {
            this.f1254c = m2027c();
            if (this.f1254c == null) {
                this.f1254c = new AuthorizeAdapter();
            }
        }
        this.f1254c.setActivity(activity);
    }

    /* renamed from: c */
    private AuthorizeAdapter m2027c() {
        try {
            ActivityInfo activityInfo = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128);
            if (activityInfo.metaData == null || activityInfo.metaData.isEmpty()) {
                return null;
            }
            String string = activityInfo.metaData.getString("AuthorizeAdapter");
            if (string == null || string.length() <= 0) {
                string = activityInfo.metaData.getString("Adapter");
                if (string == null || string.length() <= 0) {
                    return null;
                }
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof AuthorizeAdapter) {
                return (AuthorizeAdapter) newInstance;
            }
            return null;
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return null;
        }
    }

    public void onCreate() {
        if (this.f1255d == null) {
            this.f1255d = m2029b();
            this.f1255d.m2013a(this);
            this.f1255d.m2018a(this.f1254c.isNotitle());
            this.f1254c.setBodyView(this.f1255d.m2021d());
            this.f1254c.setWebView(this.f1255d.m2019b());
            TitleLayout c = this.f1255d.m2020c();
            this.f1254c.setTitleView(c);
            String name = this.a.getPlatform().getName();
            this.f1254c.setPlatformName(this.a.getPlatform().getName());
            try {
                c.getTvTitle().setText(C4275R.getStringRes(getContext(), "ssdk_" + name.toLowerCase()));
            } catch (Throwable th) {
                C0621d.m2279a().w(th);
            }
        }
        this.f1254c.onCreate();
        if (!(this.f1254c == null || this.f1254c.isPopUpAnimationDisable())) {
            Animation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(550);
            scaleAnimation.setInterpolator(new C0583a());
            this.f1255d.setAnimation(scaleAnimation);
        }
        this.activity.setContentView(this.f1255d);
    }

    /* renamed from: b */
    protected RegisterView m2029b() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m2017a().setOnClickListener(new C05811(this));
        this.f1256e = registerView.m2019b();
        WebSettings settings = this.f1256e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        this.f1256e.setVerticalScrollBarEnabled(false);
        this.f1256e.setHorizontalScrollBarEnabled(false);
        this.f1256e.setWebViewClient(this.a.getAuthorizeWebviewClient(this));
        new C05822(this).start();
        return registerView;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 2:
                AuthorizeListener authorizeListener;
                if (message.arg1 != 1) {
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        this.f1256e.loadUrl(str);
                        break;
                    }
                    finish();
                    authorizeListener = this.a.getAuthorizeListener();
                    if (authorizeListener != null) {
                        authorizeListener.onError(new Throwable("Authorize URL is empty (platform: " + this.a.getPlatform().getName() + ")"));
                        break;
                    }
                }
                authorizeListener = this.a.getAuthorizeListener();
                if (authorizeListener != null) {
                    authorizeListener.onError(new Throwable("Network error (platform: " + this.a.getPlatform().getName() + ")"));
                    break;
                }
                break;
        }
        return false;
    }

    public void OnResize(int i, int i2, int i3, int i4) {
        if (this.f1254c != null) {
            this.f1254c.onResize(i, i2, i3, i4);
        }
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.f1254c != null) {
            z = this.f1254c.onKeyEvent(i, keyEvent);
        }
        if (!z && i == 4 && keyEvent.getAction() == 0) {
            AuthorizeListener authorizeListener = this.a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onCancel();
            }
        }
        return z ? true : super.onKeyEvent(i, keyEvent);
    }

    public void onStart() {
        if (this.f1254c != null) {
            this.f1254c.onStart();
        }
    }

    public void onPause() {
        if (this.f1254c != null) {
            this.f1254c.onPause();
        }
    }

    public void onResume() {
        if (this.f1254c != null) {
            this.f1254c.onResume();
        }
    }

    public void onStop() {
        if (this.f1254c != null) {
            this.f1254c.onStop();
        }
    }

    public void onRestart() {
        if (this.f1254c != null) {
            this.f1254c.onRestart();
        }
    }

    public boolean onFinish() {
        if (this.f1254c != null) {
            return this.f1254c.onFinish();
        }
        if (this.f1256e != null) {
            this.f1256e.destroy();
            this.f1256e.removeAllViews();
        }
        if (this.activity != null) {
            ((ViewGroup) this.activity.getWindow().getDecorView()).removeAllViews();
        }
        return super.onFinish();
    }

    public void onDestroy() {
        if (this.f1254c != null) {
            this.f1254c.onDestroy();
        }
    }
}
