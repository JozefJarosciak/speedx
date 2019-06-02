package com.beastbikes.android.embapi;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.beastbikes.framework.android.p088g.C2803i;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.android.p103h.C2804a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: DeviceInterceptor */
/* renamed from: com.beastbikes.android.embapi.b */
public class C1811b implements C1809c {
    /* renamed from: a */
    public WebResourceResponse mo3250a(WebView webView, String str, String str2, Map<String, String> map) {
        Map hashMap = new HashMap();
        hashMap.put("id", C2803i.m13764a(webView.getContext()));
        return new C2804a(hashMap);
    }
}
