package io.fabric.sdk.android.services.settings;

import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.common.C4864a;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.network.C4924c;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: DefaultSettingsSpiCall */
/* renamed from: io.fabric.sdk.android.services.settings.l */
class C4944l extends C4864a implements C4943w {
    public C4944l(C1468h c1468h, String str, String str2, C4924c c4924c) {
        this(c1468h, str, str2, c4924c, HttpMethod.GET);
    }

    C4944l(C1468h c1468h, String str, String str2, C4924c c4924c, HttpMethod httpMethod) {
        super(c1468h, str, str2, c4924c, httpMethod);
    }

    /* renamed from: a */
    public JSONObject mo6267a(C4954v c4954v) {
        HttpRequest httpRequest = null;
        try {
            Map b = m19405b(c4954v);
            httpRequest = m19402a(m19113a(b), c4954v);
            C1520c.h().mo6215a("Fabric", "Requesting settings from " + m19114a());
            C1520c.h().mo6215a("Fabric", "Settings query params were: " + b);
            JSONObject a = m19406a(httpRequest);
            return a;
        } finally {
            if (httpRequest != null) {
                C1520c.h().mo6215a("Fabric", "Settings request ID: " + httpRequest.m19327b("X-REQUEST-ID"));
            }
        }
    }

    /* renamed from: a */
    JSONObject m19406a(HttpRequest httpRequest) {
        int b = httpRequest.m19325b();
        C1520c.h().mo6215a("Fabric", "Settings result was: " + b);
        if (m19408a(b)) {
            return m19403a(httpRequest.m19334d());
        }
        C1520c.h().mo6221d("Fabric", "Failed to retrieve settings from " + m19114a());
        return null;
    }

    /* renamed from: a */
    boolean m19408a(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    /* renamed from: a */
    private JSONObject m19403a(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable e) {
            C1520c.h().mo6216a("Fabric", "Failed to parse settings JSON from " + m19114a(), e);
            C1520c.h().mo6215a("Fabric", "Settings response " + str);
            return null;
        }
    }

    /* renamed from: b */
    private Map<String, String> m19405b(C4954v c4954v) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("build_version", c4954v.f17360j);
        hashMap.put("display_version", c4954v.f17359i);
        hashMap.put(MapboxEvent.ATTRIBUTE_SOURCE, Integer.toString(c4954v.f17361k));
        if (c4954v.f17362l != null) {
            hashMap.put("icon_hash", c4954v.f17362l);
        }
        String str = c4954v.f17358h;
        if (!C4877i.m19167b(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    /* renamed from: a */
    private HttpRequest m19402a(HttpRequest httpRequest, C4954v c4954v) {
        m19404a(httpRequest, "X-CRASHLYTICS-API-KEY", c4954v.f17351a);
        m19404a(httpRequest, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        m19404a(httpRequest, "X-CRASHLYTICS-API-CLIENT-VERSION", this.a.c());
        m19404a(httpRequest, "Accept", "application/json");
        m19404a(httpRequest, "X-CRASHLYTICS-DEVICE-MODEL", c4954v.f17352b);
        m19404a(httpRequest, "X-CRASHLYTICS-OS-BUILD-VERSION", c4954v.f17353c);
        m19404a(httpRequest, "X-CRASHLYTICS-OS-DISPLAY-VERSION", c4954v.f17354d);
        m19404a(httpRequest, "X-CRASHLYTICS-ADVERTISING-TOKEN", c4954v.f17355e);
        m19404a(httpRequest, "X-CRASHLYTICS-INSTALLATION-ID", c4954v.f17356f);
        m19404a(httpRequest, "X-CRASHLYTICS-ANDROID-ID", c4954v.f17357g);
        return httpRequest;
    }

    /* renamed from: a */
    private void m19404a(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.m19317a(str, str2);
        }
    }
}
