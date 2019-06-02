package com.beastbikes.android.modules.cycling.achievement.p063a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1451h;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: AchievementServiceStub */
/* renamed from: com.beastbikes.android.modules.cycling.achievement.a.b */
public interface C1396b extends C1600d {
    @C1446c(a = "/legs/{leg_id}/achievements")
    /* renamed from: a */
    JSONObject m5846a(@C1451h(a = "leg_id") long j, @C1452i(a = "user_id") String str);

    @C1446c(a = "/achievements/summary")
    /* renamed from: a */
    JSONObject m5847a(@C1452i(a = "user_id") String str);
}
