package com.beastbikes.android.modules.cycling.ranking.p067a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: RankServiceStub */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.a.a */
public interface C1415a extends C1600d {
    @C1447d(a = "/getMyRank")
    /* renamed from: a */
    JSONObject m6646a(@C1444a(a = "rankType") int i, @C1444a(a = "geoCode") String str);

    @C1447d(a = "/getRankList")
    /* renamed from: a */
    JSONObject m6647a(@C1444a(a = "rankType") int i, @C1444a(a = "geoCode") String str, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3);

    @C1447d(a = "/getGeoCode")
    /* renamed from: a */
    JSONObject m6648a(@C1444a(a = "area") String str);
}
