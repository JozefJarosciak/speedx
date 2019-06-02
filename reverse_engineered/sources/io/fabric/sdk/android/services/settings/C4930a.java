package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.C4847j;
import io.fabric.sdk.android.services.common.C4864a;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4890o;
import io.fabric.sdk.android.services.network.C4924c;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Locale;

/* compiled from: AbstractAppSpiCall */
/* renamed from: io.fabric.sdk.android.services.settings.a */
abstract class C4930a extends C4864a {
    public C4930a(C1468h c1468h, String str, String str2, C4924c c4924c, HttpMethod httpMethod) {
        super(c1468h, str, str2, c4924c, httpMethod);
    }

    /* renamed from: a */
    public boolean mo6261a(C4933d c4933d) {
        HttpRequest b = m19372b(m19371a(m19115b(), c4933d), c4933d);
        C1520c.h().mo6215a("Fabric", "Sending app info to " + m19114a());
        if (c4933d.f17297j != null) {
            C1520c.h().mo6215a("Fabric", "App icon hash is " + c4933d.f17297j.f17319a);
            C1520c.h().mo6215a("Fabric", "App icon size is " + c4933d.f17297j.f17321c + "x" + c4933d.f17297j.f17322d);
        }
        int b2 = b.m19325b();
        C1520c.h().mo6215a("Fabric", ("POST".equals(b.m19348o()) ? "Create" : "Update") + " app request ID: " + b.m19327b("X-REQUEST-ID"));
        C1520c.h().mo6215a("Fabric", "Result was " + b2);
        if (C4890o.m19223a(b2) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private HttpRequest m19371a(HttpRequest httpRequest, C4933d c4933d) {
        return httpRequest.m19317a("X-CRASHLYTICS-API-KEY", c4933d.f17288a).m19317a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").m19317a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.c());
    }

    /* renamed from: b */
    private HttpRequest m19372b(HttpRequest httpRequest, C4933d c4933d) {
        HttpRequest e = httpRequest.m19335e("app[identifier]", c4933d.f17289b).m19335e("app[name]", c4933d.f17293f).m19335e("app[display_version]", c4933d.f17290c).m19335e("app[build_version]", c4933d.f17291d).m19316a("app[source]", Integer.valueOf(c4933d.f17294g)).m19335e("app[minimum_sdk_version]", c4933d.f17295h).m19335e("app[built_sdk_version]", c4933d.f17296i);
        if (!C4877i.m19167b(c4933d.f17292e)) {
            e.m19335e("app[instance_identifier]", c4933d.f17292e);
        }
        if (c4933d.f17297j != null) {
            Closeable closeable = null;
            try {
                closeable = this.a.q().getResources().openRawResource(c4933d.f17297j.f17320b);
                e.m19335e("app[icon][hash]", c4933d.f17297j.f17319a).m19320a("app[icon][data]", "icon.png", "application/octet-stream", (InputStream) closeable).m19316a("app[icon][width]", Integer.valueOf(c4933d.f17297j.f17321c)).m19316a("app[icon][height]", Integer.valueOf(c4933d.f17297j.f17322d));
            } catch (Throwable e2) {
                C1520c.h().mo6222d("Fabric", "Failed to find app icon with resource ID: " + c4933d.f17297j.f17320b, e2);
            } finally {
                String str = "Failed to close app icon InputStream.";
                C4877i.m19160a(closeable, str);
            }
        }
        if (c4933d.f17298k != null) {
            for (C4847j c4847j : c4933d.f17298k) {
                e.m19335e(m19373a(c4847j), c4847j.m19057b());
                e.m19335e(m19375b(c4847j), c4847j.m19058c());
            }
        }
        return e;
    }

    /* renamed from: a */
    String m19373a(C4847j c4847j) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", new Object[]{c4847j.m19056a()});
    }

    /* renamed from: b */
    String m19375b(C4847j c4847j) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", new Object[]{c4847j.m19056a()});
    }
}
