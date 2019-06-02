package com.baidu.mapapi.favorite;

import ch.qos.logback.core.joran.action.Action;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.favrite.FavSyncPoi;
import org.json.JSONObject;

/* renamed from: com.baidu.mapapi.favorite.a */
class C1103a {
    /* renamed from: a */
    static FavoritePoiInfo m4037a(FavSyncPoi favSyncPoi) {
        if (favSyncPoi == null || favSyncPoi.f3619c == null || favSyncPoi.f3618b.equals("")) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        favoritePoiInfo.f2784a = favSyncPoi.f3617a;
        favoritePoiInfo.f2785b = favSyncPoi.f3618b;
        favoritePoiInfo.f2786c = new LatLng(((double) favSyncPoi.f3619c.f3294y) / 1000000.0d, ((double) favSyncPoi.f3619c.f3293x) / 1000000.0d);
        favoritePoiInfo.f2788e = favSyncPoi.f3621e;
        favoritePoiInfo.f2789f = favSyncPoi.f3622f;
        favoritePoiInfo.f2787d = favSyncPoi.f3620d;
        favoritePoiInfo.f2790g = Long.parseLong(favSyncPoi.f3624h);
        return favoritePoiInfo;
    }

    /* renamed from: a */
    static FavoritePoiInfo m4038a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        JSONObject optJSONObject = jSONObject.optJSONObject("pt");
        if (optJSONObject != null) {
            favoritePoiInfo.f2786c = new LatLng(((double) optJSONObject.optInt("y")) / 1000000.0d, ((double) optJSONObject.optInt("x")) / 1000000.0d);
        }
        favoritePoiInfo.f2785b = jSONObject.optString("uspoiname");
        favoritePoiInfo.f2790g = Long.parseLong(jSONObject.optString("addtimesec"));
        favoritePoiInfo.f2787d = jSONObject.optString("addr");
        favoritePoiInfo.f2789f = jSONObject.optString("uspoiuid");
        favoritePoiInfo.f2788e = jSONObject.optString("ncityid");
        favoritePoiInfo.f2784a = jSONObject.optString(Action.KEY_ATTRIBUTE);
        return favoritePoiInfo;
    }

    /* renamed from: a */
    static FavSyncPoi m4039a(FavoritePoiInfo favoritePoiInfo) {
        if (favoritePoiInfo == null || favoritePoiInfo.f2786c == null || favoritePoiInfo.f2785b == null || favoritePoiInfo.f2785b.equals("")) {
            return null;
        }
        FavSyncPoi favSyncPoi = new FavSyncPoi();
        favSyncPoi.f3618b = favoritePoiInfo.f2785b;
        favSyncPoi.f3619c = new Point((int) (favoritePoiInfo.f2786c.longitude * 1000000.0d), (int) (favoritePoiInfo.f2786c.latitude * 1000000.0d));
        favSyncPoi.f3620d = favoritePoiInfo.f2787d;
        favSyncPoi.f3621e = favoritePoiInfo.f2788e;
        favSyncPoi.f3622f = favoritePoiInfo.f2789f;
        favSyncPoi.f3625i = false;
        return favSyncPoi;
    }
}
