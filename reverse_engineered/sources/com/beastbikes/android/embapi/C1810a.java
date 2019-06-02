package com.beastbikes.android.embapi;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.framework.android.p088g.C1465f;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.android.p103h.C2804a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AgentInterceptor */
/* renamed from: com.beastbikes.android.embapi.a */
public class C1810a implements C1809c {
    /* renamed from: a */
    public WebResourceResponse mo3250a(WebView webView, String str, String str2, Map<String, String> map) {
        Map hashMap = new HashMap();
        hashMap.put("name", "");
        hashMap.put("platform", "android");
        hashMap.put("versionCode", Integer.valueOf(C1465f.a(BeastBikes.j().getApplicationContext())));
        hashMap.put("versionName", C1465f.b(BeastBikes.j().getApplicationContext()));
        return new C2804a(hashMap);
    }
}
