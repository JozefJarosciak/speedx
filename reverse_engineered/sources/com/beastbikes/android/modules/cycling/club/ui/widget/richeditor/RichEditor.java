package com.beastbikes.android.modules.cycling.club.ui.widget.richeditor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RichEditor extends WebView {
    /* renamed from: a */
    private boolean f10136a;
    /* renamed from: b */
    private String f10137b;
    /* renamed from: c */
    private C2072d f10138c;
    /* renamed from: d */
    private C2163c f10139d;
    /* renamed from: e */
    private C2161a f10140e;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditor$d */
    public interface C2072d {
        /* renamed from: a */
        void mo3370a(String str);
    }

    public enum Type {
        BOLD,
        ITALIC,
        SUBSCRIPT,
        SUPERSCRIPT,
        STRIKETHROUGH,
        UNDERLINE,
        H1,
        H2,
        H3,
        H4,
        H5,
        H6
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditor$a */
    public interface C2161a {
        /* renamed from: a */
        void m11085a(boolean z);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditor$b */
    protected class C2162b extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ RichEditor f10135a;

        protected C2162b(RichEditor richEditor) {
            this.f10135a = richEditor;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f10135a.f10136a = str.equalsIgnoreCase("file:///android_asset/editor.html");
            if (this.f10135a.f10140e != null) {
                this.f10135a.f10140e.m11085a(this.f10135a.f10136a);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                String decode = URLDecoder.decode(str, "UTF-8");
                if (TextUtils.indexOf(str, "re-callback://") == 0) {
                    this.f10135a.m11093b(decode);
                    return true;
                } else if (TextUtils.indexOf(str, "re-state://") != 0) {
                    return super.shouldOverrideUrlLoading(webView, str);
                } else {
                    this.f10135a.m11095c(decode);
                    return true;
                }
            } catch (UnsupportedEncodingException e) {
                return false;
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditor$c */
    public interface C2163c {
        /* renamed from: a */
        void m11086a(String str, List<Type> list);
    }

    public RichEditor(Context context) {
        this(context, null);
    }

    public RichEditor(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842885);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public RichEditor(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10136a = false;
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(m11098a());
        loadUrl("file:///android_asset/editor.html");
        m11089a(context, attributeSet);
    }

    /* renamed from: a */
    protected C2162b m11098a() {
        return new C2162b(this);
    }

    public void setOnTextChangeListener(C2072d c2072d) {
        this.f10138c = c2072d;
    }

    public void setOnDecorationChangeListener(C2163c c2163c) {
        this.f10139d = c2163c;
    }

    public void setOnInitialLoadListener(C2161a c2161a) {
        this.f10140e = c2161a;
    }

    /* renamed from: b */
    private void m11093b(String str) {
        this.f10137b = str.replaceFirst("re-callback://", "");
        if (this.f10138c != null) {
            this.f10138c.mo3370a(this.f10137b);
        }
    }

    /* renamed from: c */
    private void m11095c(String str) {
        Object toUpperCase = str.replaceFirst("re-state://", "").toUpperCase(Locale.ENGLISH);
        List arrayList = new ArrayList();
        for (Type type : Type.values()) {
            if (TextUtils.indexOf(toUpperCase, type.name()) != -1) {
                arrayList.add(type);
            }
        }
        if (this.f10139d != null) {
            this.f10139d.m11086a(toUpperCase, arrayList);
        }
    }

    /* renamed from: a */
    private void m11089a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842927});
        switch (obtainStyledAttributes.getInt(0, -1)) {
            case 1:
                m11099a("javascript:RE.setTextAlign(\"center\")");
                break;
            case 3:
                m11099a("javascript:RE.setTextAlign(\"left\")");
                break;
            case 5:
                m11099a("javascript:RE.setTextAlign(\"right\")");
                break;
            case 16:
                m11099a("javascript:RE.setVerticalAlign(\"middle\")");
                break;
            case 17:
                m11099a("javascript:RE.setVerticalAlign(\"middle\")");
                m11099a("javascript:RE.setTextAlign(\"center\")");
                break;
            case 48:
                m11099a("javascript:RE.setVerticalAlign(\"top\")");
                break;
            case 80:
                m11099a("javascript:RE.setVerticalAlign(\"bottom\")");
                break;
        }
        obtainStyledAttributes.recycle();
    }

    public void setHtml(String str) {
        if (str == null) {
            str = "";
        }
        try {
            m11099a("javascript:RE.setHtml('" + URLEncoder.encode(str, "UTF-8") + "');");
        } catch (UnsupportedEncodingException e) {
        }
        this.f10137b = str;
    }

    public String getHtml() {
        return this.f10137b;
    }

    public void setEditorFontColor(int i) {
        m11099a("javascript:RE.setBaseTextColor('" + m11088a(i) + "');");
    }

    public void setEditorFontSize(int i) {
        m11099a("javascript:RE.setBaseFontSize('" + i + "px');");
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        m11099a("javascript:RE.setPadding('" + i + "px', '" + i2 + "px', '" + i3 + "px', '" + i4 + "px');");
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        setPadding(i, i2, i3, i4);
    }

    public void setEditorBackgroundColor(int i) {
        setBackgroundColor(i);
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
    }

    public void setBackgroundResource(int i) {
        Bitmap a = C2169c.m11123a(getContext(), i);
        String a2 = C2169c.m11125a(a);
        a.recycle();
        m11099a("javascript:RE.setBackgroundImage('url(data:image/png;base64," + a2 + ")');");
    }

    public void setBackground(Drawable drawable) {
        Bitmap a = C2169c.m11124a(drawable);
        String a2 = C2169c.m11125a(a);
        a.recycle();
        m11099a("javascript:RE.setBackgroundImage('url(data:image/png;base64," + a2 + ")');");
    }

    public void setBackground(String str) {
        m11099a("javascript:RE.setBackgroundImage('url(" + str + ")');");
    }

    public void setEditorWidth(int i) {
        m11099a("javascript:RE.setWidth('" + i + "px');");
    }

    public void setEditorHeight(int i) {
        m11099a("javascript:RE.setHeight('" + i + "px');");
    }

    public void setPlaceholder(String str) {
        m11099a("javascript:RE.setPlaceholder('" + str + "');");
    }

    /* renamed from: b */
    public void m11101b() {
        m11099a("javascript:RE.setBold();");
    }

    /* renamed from: c */
    public void m11102c() {
        m11099a("javascript:RE.setItalic();");
    }

    /* renamed from: d */
    public void m11103d() {
        m11099a("javascript:RE.setUnderline();");
    }

    public void setTextColor(int i) {
        m11099a("javascript:RE.prepareInsert();");
        m11099a("javascript:RE.setTextColor('" + m11088a(i) + "');");
    }

    public void setTextBackgroundColor(int i) {
        m11099a("javascript:RE.prepareInsert();");
        m11099a("javascript:RE.setTextBackgroundColor('" + m11088a(i) + "');");
    }

    public void setFontSize(int i) {
        if (i > 7 || i < 1) {
            Log.e("RichEditor", "Font size should have a value between 1-7");
        }
        m11099a("javascript:RE.setFontSize('" + i + "');");
    }

    public void setHeading(int i) {
        m11099a("javascript:RE.setHeading('" + i + "');");
    }

    /* renamed from: e */
    public void m11104e() {
        m11099a("javascript:RE.setJustifyLeft();");
    }

    /* renamed from: f */
    public void m11105f() {
        m11099a("javascript:RE.setJustifyCenter();");
    }

    /* renamed from: g */
    public void m11106g() {
        m11099a("javascript:RE.setJustifyRight();");
    }

    /* renamed from: a */
    public void m11100a(String str, String str2) {
        m11097i();
        m11099a("javascript:RE.prepareInsert();");
        m11099a("javascript:RE.insertImage('" + str + "', '" + str2 + "');");
        m11097i();
        m11097i();
    }

    /* renamed from: i */
    private void m11097i() {
        try {
            Process exec = Runtime.getRuntime().exec("input keyevent 66");
            if (exec != null) {
                exec.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: h */
    public void m11107h() {
        this.f10137b = "";
        m11099a("javascript:testAlert()");
    }

    /* renamed from: a */
    private String m11088a(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i)});
    }

    /* renamed from: a */
    protected void m11099a(final String str) {
        if (this.f10136a) {
            m11096d(str);
        } else {
            postDelayed(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ RichEditor f10134b;

                public void run() {
                    this.f10134b.m11099a(str);
                }
            }, 100);
        }
    }

    /* renamed from: d */
    private void m11096d(String str) {
        if (VERSION.SDK_INT >= 19) {
            evaluateJavascript(str, null);
        } else {
            new C2165a().m11110a(this, str);
        }
    }
}
