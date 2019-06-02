package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.C4275R;
import java.lang.reflect.Method;

public class RegisterView extends ResizeLayout {
    /* renamed from: a */
    private TitleLayout f1242a;
    /* renamed from: b */
    private RelativeLayout f1243b;
    /* renamed from: c */
    private WebView f1244c;
    /* renamed from: d */
    private TextView f1245d;

    /* renamed from: cn.sharesdk.framework.authorize.RegisterView$1 */
    class C05761 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ RegisterView f1238a;

        C05761(RegisterView registerView) {
            this.f1238a = registerView;
        }

        public void onClick(View view) {
            try {
                int stringRes = C4275R.getStringRes(view.getContext(), "ssdk_website");
                Object obj = null;
                if (stringRes > 0) {
                    obj = view.getResources().getString(stringRes);
                }
                if (!TextUtils.isEmpty(obj)) {
                    view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(obj)));
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
    }

    public RegisterView(Context context) {
        super(context);
        m2015a(context);
    }

    public RegisterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m2015a(context);
    }

    /* renamed from: a */
    private void m2015a(Context context) {
        int bitmapRes;
        setBackgroundColor(-1);
        setOrientation(1);
        final int b = m2016b(context);
        this.f1242a = new TitleLayout(context);
        try {
            bitmapRes = C4275R.getBitmapRes(context, "ssdk_auth_title_back");
            if (bitmapRes > 0) {
                this.f1242a.setBackgroundResource(bitmapRes);
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        this.f1242a.getBtnRight().setVisibility(8);
        bitmapRes = C4275R.getStringRes(getContext(), "ssdk_weibo_oauth_regiseter");
        if (bitmapRes > 0) {
            this.f1242a.getTvTitle().setText(bitmapRes);
        }
        addView(this.f1242a);
        View imageView = new ImageView(context);
        int bitmapRes2 = C4275R.getBitmapRes(context, "ssdk_logo");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        imageView.setScaleType(ScaleType.CENTER_INSIDE);
        imageView.setPadding(0, 0, C4275R.dipToPx(context, 10), 0);
        imageView.setLayoutParams(new LayoutParams(-2, -1));
        imageView.setOnClickListener(new C05761(this));
        this.f1242a.addView(imageView);
        this.f1243b = new RelativeLayout(context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        this.f1243b.setLayoutParams(layoutParams);
        addView(this.f1243b);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f1243b.addView(linearLayout);
        this.f1245d = new TextView(context);
        this.f1245d.setLayoutParams(new LayoutParams(-1, 5));
        this.f1245d.setBackgroundColor(-12929302);
        linearLayout.addView(this.f1245d);
        this.f1245d.setVisibility(8);
        this.f1244c = new WebView(context);
        layoutParams = new LayoutParams(-1, -1);
        layoutParams.weight = 1.0f;
        this.f1244c.setLayoutParams(layoutParams);
        WebChromeClient c05772 = new WebChromeClient(this) {
            /* renamed from: b */
            final /* synthetic */ RegisterView f1240b;

            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                LayoutParams layoutParams = (LayoutParams) this.f1240b.f1245d.getLayoutParams();
                layoutParams.width = (b * i) / 100;
                this.f1240b.f1245d.setLayoutParams(layoutParams);
                if (i <= 0 || i >= 100) {
                    this.f1240b.f1245d.setVisibility(8);
                } else {
                    this.f1240b.f1245d.setVisibility(0);
                }
            }
        };
        if (VERSION.SDK_INT > 10 && VERSION.SDK_INT < 17) {
            try {
                Method method = this.f1244c.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.f1244c, new Object[]{"searchBoxJavaBridge_"});
            } catch (Throwable th2) {
                C0621d.m2279a().d(th2);
            }
        }
        this.f1244c.setWebChromeClient(c05772);
        linearLayout.addView(this.f1244c);
        this.f1244c.requestFocus();
    }

    /* renamed from: b */
    private int m2016b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!(context instanceof Activity)) {
            return 0;
        }
        WindowManager windowManager = ((Activity) context).getWindowManager();
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: a */
    public View m2017a() {
        return this.f1242a.getBtnBack();
    }

    /* renamed from: a */
    public void m2018a(boolean z) {
        this.f1242a.setVisibility(z ? 8 : 0);
    }

    /* renamed from: b */
    public WebView m2019b() {
        return this.f1244c;
    }

    /* renamed from: c */
    public TitleLayout m2020c() {
        return this.f1242a;
    }

    /* renamed from: d */
    public RelativeLayout m2021d() {
        return this.f1243b;
    }
}
