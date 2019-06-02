package com.beastbikes.android.modules.user.p077a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: RegionServiceStub */
/* renamed from: com.beastbikes.android.modules.user.a.b */
public interface C1440b extends C1600d {
    @C1446c(a = "/places/autocomplete")
    /* renamed from: a */
    JSONObject m7463a(@C1452i(a = "query") String str, @C1452i(a = "location") String str2, @C1452i(a = "radius") String str3);
}
