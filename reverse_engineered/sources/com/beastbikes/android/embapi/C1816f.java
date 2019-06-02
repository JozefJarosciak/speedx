package com.beastbikes.android.embapi;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.android.p103h.C2804a;
import io.rong.imlib.statistics.UserData;
import java.util.HashMap;
import java.util.Map;

/* compiled from: UserInterceptor */
/* renamed from: com.beastbikes.android.embapi.f */
public class C1816f implements C1809c {
    /* renamed from: a */
    public WebResourceResponse mo3250a(WebView webView, String str, String str2, Map<String, String> map) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            return new C2804a("");
        }
        Map hashMap = new HashMap();
        hashMap.put("id", currentUser.getObjectId());
        hashMap.put(UserData.USERNAME_KEY, currentUser.getUsername());
        hashMap.put("email", currentUser.getEmail());
        return new C2804a(hashMap);
    }
}
