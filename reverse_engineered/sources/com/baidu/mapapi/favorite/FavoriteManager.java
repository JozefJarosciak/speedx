package com.baidu.mapapi.favorite;

import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.favrite.C1227a;
import com.baidu.platform.comapi.favrite.FavSyncPoi;
import com.baidu.platform.comapi.map.C1253i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FavoriteManager {
    /* renamed from: a */
    private static FavoriteManager f2782a;
    /* renamed from: b */
    private static C1227a f2783b;

    private FavoriteManager() {
    }

    public static FavoriteManager getInstance() {
        if (f2782a == null) {
            f2782a = new FavoriteManager();
        }
        return f2782a;
    }

    public int add(FavoritePoiInfo favoritePoiInfo) {
        if (f2783b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return 0;
        } else if (favoritePoiInfo == null || favoritePoiInfo.f2786c == null) {
            Log.e("baidumapsdk", "object or pt can not be null!");
            return 0;
        } else if (favoritePoiInfo.f2785b == null || favoritePoiInfo.f2785b.equals("")) {
            Log.e("baidumapsdk", "poiName can not be null or empty!");
            return -1;
        } else {
            FavSyncPoi a = C1103a.m4039a(favoritePoiInfo);
            int a2 = f2783b.m4595a(a.f3618b, a);
            if (a2 != 1) {
                return a2;
            }
            favoritePoiInfo.f2784a = a.f3617a;
            favoritePoiInfo.f2790g = Long.parseLong(a.f3624h);
            return a2;
        }
    }

    public boolean clearAllFavPois() {
        if (f2783b != null) {
            return f2783b.m4600c();
        }
        Log.e("baidumapsdk", "you may have not call init method!");
        return false;
    }

    public boolean deleteFavPoi(String str) {
        if (f2783b != null) {
            return (str == null || str.equals("")) ? false : f2783b.m4596a(str);
        } else {
            Log.e("baidumapsdk", "you may have not call init method!");
            return false;
        }
    }

    public void destroy() {
        if (f2783b != null) {
            f2783b.m4598b();
            f2783b = null;
            BMapManager.destroy();
            C1253i.m4753b();
        }
    }

    public List<FavoritePoiInfo> getAllFavPois() {
        if (f2783b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        }
        String f = f2783b.m4604f();
        if (f == null || f.equals("")) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(f);
            if (jSONObject.optInt("favpoinum") == 0) {
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("favcontents");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    arrayList.add(C1103a.m4038a(jSONObject2));
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FavoritePoiInfo getFavPoi(String str) {
        if (f2783b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        } else if (str == null || str.equals("")) {
            return null;
        } else {
            FavSyncPoi b = f2783b.m4597b(str);
            return b != null ? C1103a.m4037a(b) : null;
        }
    }

    public void init() {
        if (f2783b == null) {
            C1253i.m4750a();
            BMapManager.init();
            f2783b = C1227a.m4590a();
        }
    }

    public boolean updateFavPoi(String str, FavoritePoiInfo favoritePoiInfo) {
        if (f2783b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return false;
        } else if (str == null || str.equals("") || favoritePoiInfo == null) {
            return false;
        } else {
            if (favoritePoiInfo == null || favoritePoiInfo.f2786c == null) {
                Log.e("baidumapsdk", "object or pt can not be null!");
                return false;
            } else if (favoritePoiInfo.f2785b == null || favoritePoiInfo.f2785b.equals("")) {
                Log.e("baidumapsdk", "poiName can not be null or empty!");
                return false;
            } else {
                favoritePoiInfo.f2784a = str;
                return f2783b.m4599b(str, C1103a.m4039a(favoritePoiInfo));
            }
        }
    }
}
