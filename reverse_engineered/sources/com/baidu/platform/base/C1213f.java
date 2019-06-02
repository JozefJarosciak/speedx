package com.baidu.platform.base;

import ch.qos.logback.classic.spi.CallerData;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.platform.comjni.util.AppMD5;
import com.baidu.platform.domain.C1361b;
import com.baidu.platform.domain.C1363c;
import com.baidu.platform.util.C1364a;

/* renamed from: com.baidu.platform.base.f */
public abstract class C1213f {
    /* renamed from: a */
    protected C1364a f3585a = new C1364a();
    /* renamed from: b */
    private boolean f3586b = true;
    /* renamed from: c */
    private boolean f3587c = true;

    /* renamed from: a */
    public String m4540a() {
        String a = mo2682a(C1363c.m5233a());
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            return null;
        }
        if (this.f3586b) {
            this.f3585a.m5234a("token", authToken);
        }
        authToken = this.f3585a.m5235a() + HttpClient.getPhoneInfo();
        if (this.f3587c) {
            authToken = authToken + "&sign=" + AppMD5.getSignMD5String(authToken);
        }
        return a + CallerData.NA + authToken;
    }

    /* renamed from: a */
    protected final String m4541a(PlanNode planNode) {
        if (planNode == null) {
            return null;
        }
        String str = new String("{");
        LatLng location = planNode.getLocation();
        if (location != null) {
            str = str + "\"type\":1,";
            Point ll2point = CoordUtil.ll2point(location);
            return str + "\"xy\":\"" + ll2point.f3293x + "," + ll2point.f3294y + "\"}";
        } else if (planNode.getName() == null) {
            return str;
        } else {
            return (str + "\"type\":2,") + "\"keyword\":\"" + planNode.getName() + "\"}";
        }
    }

    /* renamed from: a */
    public abstract String mo2682a(C1361b c1361b);

    /* renamed from: a */
    public void m4543a(boolean z) {
        this.f3587c = z;
    }

    /* renamed from: b */
    public void m4544b(boolean z) {
        this.f3586b = z;
    }
}
