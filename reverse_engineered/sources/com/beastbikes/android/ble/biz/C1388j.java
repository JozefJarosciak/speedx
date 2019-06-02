package com.beastbikes.android.ble.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: DirectionStub */
/* renamed from: com.beastbikes.android.ble.biz.j */
public interface C1388j extends C1600d {
    @C1446c(a = "directions/json")
    /* renamed from: a */
    JSONObject m5436a(@C1452i(a = "mode") String str, @C1452i(a = "origin") String str2, @C1452i(a = "destination") String str3, @C1452i(a = "waypoints") String str4, @C1452i(a = "key") String str5, @C1452i(a = "avoid") String str6);
}
