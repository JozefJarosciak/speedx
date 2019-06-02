package com.beastbikes.android.modules.cycling.club.ui.widget.richeditor;

import android.os.Message;
import android.webkit.WebView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: PrivateApiBridgeMode */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.a */
public class C2165a {
    /* renamed from: a */
    Method f10147a;
    /* renamed from: b */
    Object f10148b;
    /* renamed from: c */
    boolean f10149c;

    /* renamed from: a */
    private void m11109a(WebView webView) {
        Class cls = WebView.class;
        try {
            Field declaredField = cls.getDeclaredField("mProvider");
            declaredField.setAccessible(true);
            webView = declaredField.get(webView);
            cls = webView.getClass();
        } catch (Throwable th) {
        }
        try {
            Field declaredField2 = cls.getDeclaredField("mWebViewCore");
            declaredField2.setAccessible(true);
            this.f10148b = declaredField2.get(webView);
            if (this.f10148b != null) {
                this.f10147a = this.f10148b.getClass().getDeclaredMethod("sendMessage", new Class[]{Message.class});
                this.f10147a.setAccessible(true);
            }
        } catch (Throwable th2) {
            this.f10149c = true;
        }
    }

    /* renamed from: a */
    public void m11110a(WebView webView, String str) {
        if (this.f10147a == null && !this.f10149c) {
            m11109a(webView);
        }
        if (this.f10147a != null) {
            Message obtain = Message.obtain(null, 194, str);
            try {
                this.f10147a.invoke(this.f10148b, new Object[]{obtain});
            } catch (Throwable th) {
            }
        }
    }
}
