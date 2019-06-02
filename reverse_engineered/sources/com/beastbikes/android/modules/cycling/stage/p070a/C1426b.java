package com.beastbikes.android.modules.cycling.stage.p070a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1445b;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1451h;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: StageStub */
/* renamed from: com.beastbikes.android.modules.cycling.stage.a.b */
public interface C1426b extends C1600d {
    @C1446c(a = "/collect/legs/")
    /* renamed from: a */
    JSONObject m7012a();

    @C1447d(a = "/collect/")
    /* renamed from: a */
    JSONObject m7013a(@C1444a(a = "leg_id") int i);

    @C1446c(a = "/leg_record/rank/")
    /* renamed from: a */
    JSONObject m7014a(@C1452i(a = "leg_id") int i, @C1452i(a = "page") int i2);

    @C1446c(a = "/legs/{leg_id}/performances")
    /* renamed from: a */
    JSONObject m7015a(@C1451h(a = "leg_id") int i, @C1452i(a = "user_id") String str);

    @C1446c(a = "/leg/{id}/")
    /* renamed from: a */
    JSONObject m7016a(@C1451h(a = "id") long j);

    @C1446c(a = "/leg/nearest/")
    /* renamed from: a */
    JSONObject m7017a(@C1452i(a = "local_point") String str, @C1452i(a = "scope_distance") int i);

    @C1446c(a = "/routes/{route_id}/legs")
    /* renamed from: a */
    JSONObject m7018a(@C1452i(a = "user_id") String str, @C1451h(a = "route_id") String str2);

    @C1445b(a = "/collect/{id}/")
    /* renamed from: b */
    JSONObject m7019b(@C1451h(a = "id") int i);
}
