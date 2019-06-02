package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0441m;
import cn.jpush.android.p003b.p004a.C0421a;
import cn.jpush.android.p003b.p004a.C0422b;
import cn.jpush.android.p003b.p004a.C0425f;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;

public class FullScreenView extends LinearLayout {
    private static final String TAG;
    public static C0425f webViewHelper = null;
    /* renamed from: z */
    private static final String[] f902z;
    private OnClickListener btnBackClickListener = new C0482a(this);
    private ImageButton imgBtnBack;
    private final Context mContext;
    private WebView mWebView;
    private RelativeLayout rlTitleBar;
    private TextView tvTitle;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "R\u000f_\u000f&w\bV\u0006\u001bB\u0013V\u0014";
        r0 = 8;
        r4 = r3;
    L_0x000a:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0035;
    L_0x0013:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0018:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x008c;
            case 1: goto L_0x008f;
            case 2: goto L_0x0092;
            case 3: goto L_0x0095;
            default: goto L_0x001f;
        };
    L_0x001f:
        r9 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
    L_0x0021:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0033;
    L_0x0029:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0018;
    L_0x002d:
        TAG = r1;
        r1 = "x\u0015R\u0007 f\u0016\t";
        r0 = -1;
        goto L_0x000a;
    L_0x0033:
        r5 = r1;
        r1 = r7;
    L_0x0035:
        if (r5 > r6) goto L_0x0013;
    L_0x0037:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x004b;
            case 1: goto L_0x0053;
            case 2: goto L_0x005b;
            case 3: goto L_0x0063;
            case 4: goto L_0x006b;
            case 5: goto L_0x0073;
            case 6: goto L_0x007b;
            case 7: goto L_0x0084;
            case 8: goto L_0x002d;
            default: goto L_0x0043;
        };
    L_0x0043:
        r3[r2] = r1;
        r2 = 1;
        r1 = "}\u001e";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x004b:
        r3[r2] = r1;
        r2 = 2;
        r1 = "r\u000f_\u000f\"q\u0018e\n\u0010c";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0053:
        r3[r2] = r1;
        r2 = 3;
        r1 = "D\u0016V\u0002\u0006qZF\u0010\u00104\u001eV\u0005\u0014a\u0016GC\u0016{\u001eVC\u001czZY\u0013\u0000g\u0012l\u0014\u0010v\fZ\u0006\u0002K\u0016R\u001a\u001aa\u000e\u001d\u001b\u0018x[";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x005b:
        r3[r2] = r1;
        r2 = 4;
        r1 = "g\u001fR\u0011\u0016|8\\\u001b?u\fR!\u0007}\u001eT\u0006*";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x0063:
        r3[r2] = r1;
        r2 = 5;
        r1 = "f\u0016a\n\u0016|\nF\u0010\u001d@\u0013G\u000f\u0010V\u001bA";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x006b:
        r3[r2] = r1;
        r2 = 6;
        r1 = "}\u0017T1\u001cw\u0012C\u0016\u0006|8G\r7u\u0019X";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0073:
        r3[r2] = r1;
        r2 = 7;
        r1 = "^*F\u0010\u001dC\u001fQ";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x007b:
        r3[r2] = r1;
        r2 = 8;
        r1 = "`\fa\n\u0016|\nF\u0010\u001d@\u0013G\u000f\u0010";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0084:
        r3[r2] = r1;
        f902z = r4;
        r0 = 0;
        webViewHelper = r0;
        return;
    L_0x008c:
        r9 = 20;
        goto L_0x0021;
    L_0x008f:
        r9 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x0021;
    L_0x0092:
        r9 = 51;
        goto L_0x0021;
    L_0x0095:
        r9 = 99;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.FullScreenView.<clinit>():void");
    }

    public FullScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private void quitFullScreen() {
        try {
            LayoutParams attributes = ((Activity) this.mContext).getWindow().getAttributes();
            attributes.flags &= -1025;
            ((Activity) this.mContext).getWindow().setAttributes(attributes);
            ((Activity) this.mContext).getWindow().clearFlags(512);
        } catch (Exception e) {
            ac.m1586d();
        }
    }

    public void destory() {
        removeAllViews();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    public void initModule(Context context, C0429c c0429c) {
        C0441m c0441m = (C0441m) c0429c;
        CharSequence charSequence = c0441m.f708E;
        setFocusable(true);
        this.mWebView = (WebView) findViewById(getResources().getIdentifier(f902z[2], f902z[1], context.getPackageName()));
        this.rlTitleBar = (RelativeLayout) findViewById(getResources().getIdentifier(f902z[5], f902z[1], context.getPackageName()));
        this.tvTitle = (TextView) findViewById(getResources().getIdentifier(f902z[8], f902z[1], context.getPackageName()));
        this.imgBtnBack = (ImageButton) findViewById(getResources().getIdentifier(f902z[6], f902z[1], context.getPackageName()));
        if (this.mWebView == null || this.rlTitleBar == null || this.tvTitle == null || this.imgBtnBack == null) {
            ac.m1589e(TAG, f902z[3]);
            ((Activity) this.mContext).finish();
        }
        if (1 == c0441m.f710G) {
            this.rlTitleBar.setVisibility(8);
            ((Activity) context).getWindow().setFlags(1024, 1024);
        } else {
            this.tvTitle.setText(charSequence);
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
        }
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setScrollBarStyle(33554432);
        WebSettings settings = this.mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        C0490b.m1680a(settings);
        webViewHelper = new C0425f(context, c0429c);
        this.mWebView.removeJavascriptInterface(f902z[4]);
        this.mWebView.setWebChromeClient(new C0421a(f902z[7], C0422b.class));
        this.mWebView.setWebViewClient(new C0484c(c0429c));
        C0422b.setWebViewHelper(webViewHelper);
    }

    public void loadUrl(String str) {
        if (this.mWebView != null) {
            new StringBuilder(f902z[0]).append(str);
            ac.m1581b();
            this.mWebView.loadUrl(str);
        }
    }

    public void pause() {
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }

    public void resume() {
        if (this.mWebView != null) {
            this.mWebView.onResume();
            C0422b.setWebViewHelper(webViewHelper);
        }
    }

    public void showTitleBar() {
        if (this.rlTitleBar != null && this.rlTitleBar.getVisibility() == 8) {
            this.rlTitleBar.setVisibility(0);
            quitFullScreen();
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
            if (this.mWebView != null) {
                this.mWebView.postDelayed(new C0483b(this), 1000);
            }
        }
    }

    public boolean webviewCanGoBack() {
        return this.mWebView != null ? this.mWebView.canGoBack() : false;
    }

    public void webviewGoBack() {
        if (this.mWebView != null) {
            this.mWebView.goBack();
        }
    }
}
