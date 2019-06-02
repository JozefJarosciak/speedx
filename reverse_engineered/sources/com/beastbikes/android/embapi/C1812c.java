package com.beastbikes.android.embapi;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.android.p103h.C2805b;
import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: HybridInterceptor */
/* renamed from: com.beastbikes.android.embapi.c */
public class C1812c implements C1809c {
    /* renamed from: a */
    private static final String f8220a = ("webkit" + File.separator + "hybrid.js");
    /* renamed from: b */
    private static final Logger f8221b = LoggerFactory.getLogger("HybridInterceptor");

    /* renamed from: a */
    public WebResourceResponse mo3250a(WebView webView, String str, String str2, Map<String, String> map) {
        try {
            return new C2805b(webView.getResources().getAssets().open(f8220a));
        } catch (Throwable e) {
            f8221b.debug(e.getMessage(), e);
            return null;
        }
    }
}
