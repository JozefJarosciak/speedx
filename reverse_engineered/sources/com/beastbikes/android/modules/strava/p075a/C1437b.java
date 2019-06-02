package com.beastbikes.android.modules.strava.p075a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: StravaServiceStub */
/* renamed from: com.beastbikes.android.modules.strava.a.b */
public interface C1437b extends C1600d {
    @C1447d(a = "/oauth/token")
    /* renamed from: a */
    JSONObject m7381a(@C1452i(a = "client_id") int i, @C1452i(a = "client_secret") String str, @C1452i(a = "code") String str2);
}
