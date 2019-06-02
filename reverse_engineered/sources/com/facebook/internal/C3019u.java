package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.AccessToken;
import com.facebook.C1472c;
import com.facebook.C2956R;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookServiceException;
import java.util.Locale;

/* compiled from: WebDialog */
/* renamed from: com.facebook.internal.u */
public class C3019u extends Dialog {
    /* renamed from: a */
    private String f13560a;
    /* renamed from: b */
    private String f13561b;
    /* renamed from: c */
    private C2993c f13562c;
    /* renamed from: d */
    private WebView f13563d;
    /* renamed from: e */
    private ProgressDialog f13564e;
    /* renamed from: f */
    private ImageView f13565f;
    /* renamed from: g */
    private FrameLayout f13566g;
    /* renamed from: h */
    private boolean f13567h;
    /* renamed from: i */
    private boolean f13568i;
    /* renamed from: j */
    private boolean f13569j;

    /* compiled from: WebDialog */
    /* renamed from: com.facebook.internal.u$c */
    public interface C2993c {
        /* renamed from: a */
        void mo3692a(Bundle bundle, FacebookException facebookException);
    }

    /* compiled from: WebDialog */
    /* renamed from: com.facebook.internal.u$1 */
    class C30501 implements OnCancelListener {
        /* renamed from: a */
        final /* synthetic */ C3019u f13643a;

        C30501(C3019u c3019u) {
            this.f13643a = c3019u;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f13643a.cancel();
        }
    }

    /* compiled from: WebDialog */
    /* renamed from: com.facebook.internal.u$2 */
    class C30512 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C3019u f13644a;

        C30512(C3019u c3019u) {
            this.f13644a = c3019u;
        }

        public void onClick(View view) {
            this.f13644a.cancel();
        }
    }

    /* compiled from: WebDialog */
    /* renamed from: com.facebook.internal.u$4 */
    class C30534 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C3019u f13646a;

        C30534(C3019u c3019u) {
            this.f13646a = c3019u;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!view.hasFocus()) {
                view.requestFocus();
            }
            return false;
        }
    }

    /* compiled from: WebDialog */
    /* renamed from: com.facebook.internal.u$a */
    public static class C3054a {
        /* renamed from: a */
        private Context f13647a;
        /* renamed from: b */
        private String f13648b;
        /* renamed from: c */
        private String f13649c;
        /* renamed from: d */
        private int f13650d;
        /* renamed from: e */
        private C2993c f13651e;
        /* renamed from: f */
        private Bundle f13652f;
        /* renamed from: g */
        private AccessToken f13653g;

        public C3054a(Context context, String str, Bundle bundle) {
            this.f13653g = AccessToken.m14270a();
            if (this.f13653g == null) {
                String a = C3048s.m14732a(context);
                if (a != null) {
                    this.f13648b = a;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            m14801a(context, str, bundle);
        }

        public C3054a(Context context, String str, String str2, Bundle bundle) {
            if (str == null) {
                str = C3048s.m14732a(context);
            }
            C3049t.m14791a(str, "applicationId");
            this.f13648b = str;
            m14801a(context, str2, bundle);
        }

        /* renamed from: a */
        public C3054a m14802a(C2993c c2993c) {
            this.f13651e = c2993c;
            return this;
        }

        /* renamed from: a */
        public C3019u mo3711a() {
            if (this.f13653g != null) {
                this.f13652f.putString("app_id", this.f13653g.m14283h());
                this.f13652f.putString("access_token", this.f13653g.m14277b());
            } else {
                this.f13652f.putString("app_id", this.f13648b);
            }
            return new C3019u(this.f13647a, this.f13649c, this.f13652f, this.f13650d, this.f13651e);
        }

        /* renamed from: b */
        public String m14804b() {
            return this.f13648b;
        }

        /* renamed from: c */
        public Context m14805c() {
            return this.f13647a;
        }

        /* renamed from: d */
        public int m14806d() {
            return this.f13650d;
        }

        /* renamed from: e */
        public Bundle m14807e() {
            return this.f13652f;
        }

        /* renamed from: f */
        public C2993c m14808f() {
            return this.f13651e;
        }

        /* renamed from: a */
        private void m14801a(Context context, String str, Bundle bundle) {
            this.f13647a = context;
            this.f13649c = str;
            if (bundle != null) {
                this.f13652f = bundle;
            } else {
                this.f13652f = new Bundle();
            }
        }
    }

    /* compiled from: WebDialog */
    /* renamed from: com.facebook.internal.u$b */
    private class C3055b extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ C3019u f13654a;

        private C3055b(C3019u c3019u) {
            this.f13654a = c3019u;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C3048s.m14754a("FacebookSDK.WebDialog", "Redirect URL: " + str);
            if (str.startsWith(this.f13654a.f13561b)) {
                int i;
                Bundle a = this.f13654a.mo3694a(str);
                String string = a.getString("error");
                if (string == null) {
                    string = a.getString("error_type");
                }
                String string2 = a.getString("error_msg");
                if (string2 == null) {
                    string2 = a.getString("error_message");
                }
                if (string2 == null) {
                    string2 = a.getString("error_description");
                }
                String string3 = a.getString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
                if (C3048s.m14761a(string3)) {
                    i = -1;
                } else {
                    try {
                        i = Integer.parseInt(string3);
                    } catch (NumberFormatException e) {
                        i = -1;
                    }
                }
                if (C3048s.m14761a(string) && C3048s.m14761a(string2) && i == -1) {
                    this.f13654a.m14601a(a);
                } else if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                    this.f13654a.cancel();
                } else if (i == 4201) {
                    this.f13654a.cancel();
                } else {
                    this.f13654a.m14603a(new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
                }
                return true;
            } else if (str.startsWith("fbconnect://cancel")) {
                this.f13654a.cancel();
                return true;
            } else if (str.contains("touch")) {
                return false;
            } else {
                try {
                    this.f13654a.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (ActivityNotFoundException e2) {
                    return false;
                }
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.f13654a.m14603a(new FacebookDialogException(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            this.f13654a.m14603a(new FacebookDialogException(null, -11, null));
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C3048s.m14754a("FacebookSDK.WebDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (!this.f13654a.f13568i) {
                this.f13654a.f13564e.show();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.f13654a.f13568i) {
                this.f13654a.f13564e.dismiss();
            }
            this.f13654a.f13566g.setBackgroundColor(0);
            this.f13654a.f13563d.setVisibility(0);
            this.f13654a.f13565f.setVisibility(0);
            this.f13654a.f13569j = true;
        }
    }

    public C3019u(Context context, String str) {
        this(context, str, C1472c.l());
    }

    public C3019u(Context context, String str, int i) {
        if (i == 0) {
            i = C1472c.l();
        }
        super(context, i);
        this.f13561b = "fbconnect://success";
        this.f13567h = false;
        this.f13568i = false;
        this.f13569j = false;
        this.f13560a = str;
    }

    public C3019u(Context context, String str, Bundle bundle, int i, C2993c c2993c) {
        if (i == 0) {
            i = C1472c.l();
        }
        super(context, i);
        this.f13561b = "fbconnect://success";
        this.f13567h = false;
        this.f13568i = false;
        this.f13569j = false;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("redirect_uri", "fbconnect://success");
        bundle.putString("display", "touch");
        bundle.putString("sdk", String.format(Locale.ROOT, "android-%s", new Object[]{C1472c.g()}));
        this.f13560a = C3048s.m14724a(C3040r.m14704a(), C3040r.m14707d() + "/" + "dialog/" + str, bundle).toString();
        this.f13562c = c2993c;
    }

    /* renamed from: a */
    public void m14602a(C2993c c2993c) {
        this.f13562c = c2993c;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        if (this.f13563d != null) {
            this.f13563d.stopLoading();
        }
        if (!(this.f13568i || this.f13564e == null || !this.f13564e.isShowing())) {
            this.f13564e.dismiss();
        }
        super.dismiss();
    }

    protected void onStart() {
        super.onStart();
        m14608d();
    }

    public void onDetachedFromWindow() {
        this.f13568i = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.f13568i = false;
        super.onAttachedToWindow();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f13564e = new ProgressDialog(getContext());
        this.f13564e.requestWindowFeature(1);
        this.f13564e.setMessage(getContext().getString(C2956R.string.com_facebook_loading));
        this.f13564e.setCanceledOnTouchOutside(false);
        this.f13564e.setOnCancelListener(new C30501(this));
        requestWindowFeature(1);
        this.f13566g = new FrameLayout(getContext());
        m14608d();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        m14598e();
        m14592a((this.f13565f.getDrawable().getIntrinsicWidth() / 2) + 1);
        this.f13566g.addView(this.f13565f, new LayoutParams(-2, -2));
        setContentView(this.f13566g);
    }

    /* renamed from: b */
    protected void m14605b(String str) {
        this.f13561b = str;
    }

    /* renamed from: a */
    protected Bundle mo3694a(String str) {
        Uri parse = Uri.parse(str);
        Bundle b = C3048s.m14764b(parse.getQuery());
        b.putAll(C3048s.m14764b(parse.getFragment()));
        return b;
    }

    /* renamed from: a */
    protected boolean m14604a() {
        return this.f13567h;
    }

    /* renamed from: b */
    protected boolean m14606b() {
        return this.f13569j;
    }

    /* renamed from: c */
    protected WebView m14607c() {
        return this.f13563d;
    }

    /* renamed from: d */
    public void m14608d() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        getWindow().setLayout(Math.min(m14590a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels, displayMetrics.density, 480, 800), displayMetrics.widthPixels), Math.min(m14590a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels, displayMetrics.density, 800, 1280), displayMetrics.heightPixels));
    }

    /* renamed from: a */
    private int m14590a(int i, float f, int i2, int i3) {
        double d = 0.5d;
        int i4 = (int) (((float) i) / f);
        if (i4 <= i2) {
            d = 1.0d;
        } else if (i4 < i3) {
            d = 0.5d + ((((double) (i3 - i4)) / ((double) (i3 - i2))) * 0.5d);
        }
        return (int) (d * ((double) i));
    }

    /* renamed from: a */
    protected void m14601a(Bundle bundle) {
        if (this.f13562c != null && !this.f13567h) {
            this.f13567h = true;
            this.f13562c.mo3692a(bundle, null);
            dismiss();
        }
    }

    /* renamed from: a */
    protected void m14603a(Throwable th) {
        if (this.f13562c != null && !this.f13567h) {
            this.f13567h = true;
            if (th instanceof FacebookException) {
                th = (FacebookException) th;
            } else {
                th = new FacebookException(th);
            }
            this.f13562c.mo3692a(null, th);
            dismiss();
        }
    }

    public void cancel() {
        if (this.f13562c != null && !this.f13567h) {
            m14603a(new FacebookOperationCanceledException());
        }
    }

    /* renamed from: e */
    private void m14598e() {
        this.f13565f = new ImageView(getContext());
        this.f13565f.setOnClickListener(new C30512(this));
        this.f13565f.setImageDrawable(getContext().getResources().getDrawable(C2956R.drawable.com_facebook_close));
        this.f13565f.setVisibility(4);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    /* renamed from: a */
    private void m14592a(int i) {
        View linearLayout = new LinearLayout(getContext());
        this.f13563d = new WebView(this, getContext().getApplicationContext()) {
            /* renamed from: a */
            final /* synthetic */ C3019u f13645a;

            public void onWindowFocusChanged(boolean z) {
                try {
                    super.onWindowFocusChanged(z);
                } catch (NullPointerException e) {
                }
            }
        };
        this.f13563d.setVerticalScrollBarEnabled(false);
        this.f13563d.setHorizontalScrollBarEnabled(false);
        this.f13563d.setWebViewClient(new C3055b());
        this.f13563d.getSettings().setJavaScriptEnabled(true);
        this.f13563d.loadUrl(this.f13560a);
        this.f13563d.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f13563d.setVisibility(4);
        this.f13563d.getSettings().setSavePassword(false);
        this.f13563d.getSettings().setSaveFormData(false);
        this.f13563d.setFocusable(true);
        this.f13563d.setFocusableInTouchMode(true);
        this.f13563d.setOnTouchListener(new C30534(this));
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.f13563d);
        linearLayout.setBackgroundColor(-872415232);
        this.f13566g.addView(linearLayout);
    }
}
