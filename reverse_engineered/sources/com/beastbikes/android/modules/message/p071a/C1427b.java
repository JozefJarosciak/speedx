package com.beastbikes.android.modules.message.p071a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: SpeedyServiceStub */
/* renamed from: com.beastbikes.android.modules.message.a.b */
public interface C1427b extends C1600d {
    @C1447d(a = "/getBroadcasts")
    /* renamed from: a */
    JSONObject m7119a();

    @C1447d(a = "/getBroadcastCount")
    /* renamed from: a */
    JSONObject m7120a(@C1444a(a = "lastDate") float f);
}
