package com.beastbikes.android.modules.cycling.route.p068a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: RouteServiceStub */
/* renamed from: com.beastbikes.android.modules.cycling.route.a.b */
public interface C1418b extends C1600d {
    @C1447d(a = "/getRouteCities")
    /* renamed from: a */
    JSONObject m6732a();

    @C1447d(a = "/getMyRoutes")
    /* renamed from: a */
    JSONObject m6733a(@C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/getRoutesByCityId")
    /* renamed from: a */
    JSONObject m6734a(@C1444a(a = "cityId") String str);

    @C1447d(a = "/getRouteCommentsByRouteId")
    /* renamed from: a */
    JSONObject m6735a(@C1444a(a = "routeId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/updateRoute")
    /* renamed from: a */
    JSONObject m6736a(@C1444a(a = "routeId") String str, @C1444a(a = "name") String str2);

    @C1447d(a = "/postRouteComment")
    /* renamed from: a */
    JSONObject m6737a(@C1444a(a = "routeId") String str, @C1444a(a = "content") String str2, @C1444a(a = "parentId") String str3);

    @C1447d(a = "/uploadRoute")
    /* renamed from: a */
    JSONObject m6738a(@C1444a(a = "name") String str, @C1444a(a = "origin") String str2, @C1444a(a = "destination") String str3, @C1444a(a = "distance") double d, @C1444a(a = "mapUrl") String str4, @C1444a(a = "routeNodes") String str5);

    @C1447d(a = "/updateRoute")
    /* renamed from: a */
    JSONObject m6739a(@C1444a(a = "routeId") String str, @C1444a(a = "name") String str2, @C1444a(a = "origin") String str3, @C1444a(a = "destination") String str4, @C1444a(a = "distance") double d, @C1444a(a = "mapUrl") String str5, @C1444a(a = "routeNodes") String str6);

    @C1447d(a = "/postRouteFollower")
    /* renamed from: b */
    JSONObject m6740b(@C1444a(a = "routeId") String str);

    @C1447d(a = "/getRouteInfoByRouteId")
    /* renamed from: c */
    JSONObject m6741c(@C1444a(a = "routeId") String str);

    @C1447d(a = "/getRoutePhotosByRouteId")
    /* renamed from: d */
    JSONObject m6742d(@C1444a(a = "routeId") String str);

    @C1447d(a = "/deleteRouteByRouteId")
    /* renamed from: e */
    JSONObject m6743e(@C1444a(a = "routeId") String str);

    @C1447d(a = "/postFavoriteRoute")
    /* renamed from: f */
    JSONObject m6744f(@C1444a(a = "routeId") String str);
}
