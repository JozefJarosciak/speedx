package com.beastbikes.android.embapi;

import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.android.p103h.C2804a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NetworkInterceptor */
/* renamed from: com.beastbikes.android.embapi.d */
public class C1814d implements C1809c {

    /* compiled from: NetworkInterceptor */
    /* renamed from: com.beastbikes.android.embapi.d$1 */
    static /* synthetic */ class C18131 {
        /* renamed from: a */
        static final /* synthetic */ int[] f8222a = new int[State.values().length];

        static {
            try {
                f8222a[State.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8222a[State.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8222a[State.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8222a[State.SUSPENDED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8222a[State.DISCONNECTING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8222a[State.DISCONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* renamed from: a */
    public WebResourceResponse mo3250a(WebView webView, String str, String str2, Map<String, String> map) {
        NetworkInfo a = C2799c.m13753a(webView.getContext());
        if (a == null) {
            return new C2804a("");
        }
        Map hashMap = new HashMap();
        hashMap.put("available", Boolean.valueOf(a.isAvailable()));
        hashMap.put("name", a.getTypeName());
        hashMap.put("roaming", Boolean.valueOf(a.isRoaming()));
        switch (C18131.f8222a[a.getState().ordinal()]) {
            case 1:
                hashMap.put("state", Integer.valueOf(0));
                break;
            case 2:
                hashMap.put("state", Integer.valueOf(1));
                break;
            case 3:
                hashMap.put("state", Integer.valueOf(2));
                break;
            case 4:
                hashMap.put("state", Integer.valueOf(3));
                break;
            case 5:
                hashMap.put("state", Integer.valueOf(4));
                break;
            case 6:
                hashMap.put("state", Integer.valueOf(5));
                break;
        }
        switch (a.getType()) {
            case 0:
                hashMap.put("type", Integer.valueOf(3));
                break;
            case 1:
                hashMap.put("type", Integer.valueOf(2));
                break;
            case 9:
                hashMap.put("type", Integer.valueOf(1));
                break;
            default:
                hashMap.put("type", Integer.valueOf(0));
                break;
        }
        return new C2804a(hashMap);
    }
}
