package com.beastbikes.android.modules.shop.p073a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: BikeShopStub */
/* renamed from: com.beastbikes.android.modules.shop.a.b */
public interface C1431b extends C1600d {
    @C1447d(a = "/getBikeShopList")
    /* renamed from: a */
    JSONObject m7233a(@C1444a(a = "longitude") double d, @C1444a(a = "latitude") double d2, @C1444a(a = "range") float f, @C1444a(a = "keyWord") String str, @C1444a(a = "uLatitude") double d3, @C1444a(a = "uLongitude") double d4, @C1444a(a = "type") String str2);

    @C1446c(a = "/bike_shops/")
    /* renamed from: a */
    JSONObject m7234a(@C1452i(a = "lng") double d, @C1452i(a = "lat") double d2, @C1452i(a = "name") String str, @C1452i(a = "search_type") int i);

    @C1446c(a = "/bike_shops/")
    /* renamed from: a */
    JSONObject m7235a(@C1452i(a = "lng") double d, @C1452i(a = "lat") double d2, @C1452i(a = "name") String str, @C1452i(a = "search_type") int i, @C1452i(a = "page") int i2, @C1452i(a = "page_size") int i3);

    @C1447d(a = "/deleteBikeShop")
    /* renamed from: a */
    JSONObject m7236a(@C1444a(a = "shopId") long j);

    @C1447d(a = "/getBikeShopInfo")
    /* renamed from: a */
    JSONObject m7237a(@C1444a(a = "shopId") long j, @C1444a(a = "longitude") float f, @C1444a(a = "latitude") float f2);
}
