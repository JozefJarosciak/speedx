package com.baidu.mapapi.utils.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.IllegalNaviArgumentException;
import com.baidu.mapapi.utils.C1192a;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.poi.IllegalPoiSearchArgumentException;
import com.baidu.mapapi.utils.route.RouteParaOption.EBusStrategyType;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.services.directions.v5.DirectionsCriteria;

public class BaiduMapRoutePlan {
    /* renamed from: a */
    private static boolean f3546a = true;

    /* renamed from: a */
    private static void m4518a(RouteParaOption routeParaOption, Context context, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://api.map.baidu.com/direction?");
        stringBuilder.append("origin=");
        LatLng latLng = routeParaOption.f3548a;
        if (SDKInitializer.getCoordType() == CoordType.GCJ02 && latLng != null) {
            latLng = CoordTrans.gcjToBaidu(latLng);
        }
        if (routeParaOption.f3548a != null && routeParaOption.f3550c != null && !routeParaOption.f3550c.equals("")) {
            stringBuilder.append("latlng:");
            stringBuilder.append(latLng.latitude);
            stringBuilder.append(",");
            stringBuilder.append(latLng.longitude);
            stringBuilder.append("|");
            stringBuilder.append("name:");
            stringBuilder.append(routeParaOption.f3550c);
        } else if (routeParaOption.f3548a != null) {
            stringBuilder.append(latLng.latitude);
            stringBuilder.append(",");
            stringBuilder.append(latLng.longitude);
        } else {
            stringBuilder.append(routeParaOption.f3550c);
        }
        latLng = routeParaOption.f3549b;
        if (SDKInitializer.getCoordType() == CoordType.GCJ02 && latLng != null) {
            latLng = CoordTrans.gcjToBaidu(latLng);
        }
        stringBuilder.append("&destination=");
        if (routeParaOption.f3549b != null && routeParaOption.f3551d != null && !routeParaOption.f3551d.equals("")) {
            stringBuilder.append("latlng:");
            stringBuilder.append(latLng.latitude);
            stringBuilder.append(",");
            stringBuilder.append(latLng.longitude);
            stringBuilder.append("|");
            stringBuilder.append("name:");
            stringBuilder.append(routeParaOption.f3551d);
        } else if (routeParaOption.f3549b != null) {
            stringBuilder.append(latLng.latitude);
            stringBuilder.append(",");
            stringBuilder.append(latLng.longitude);
        } else {
            stringBuilder.append(routeParaOption.f3551d);
        }
        String str = "";
        switch (i) {
            case 0:
                str = DirectionsCriteria.PROFILE_DRIVING;
                break;
            case 1:
                str = "transit";
                break;
            case 2:
                str = DirectionsCriteria.PROFILE_WALKING;
                break;
        }
        stringBuilder.append("&mode=");
        stringBuilder.append(str);
        stringBuilder.append("&region=");
        if (routeParaOption.getCityName() == null || routeParaOption.getCityName().equals("")) {
            stringBuilder.append("全国");
        } else {
            stringBuilder.append(routeParaOption.getCityName());
        }
        stringBuilder.append("&output=html");
        stringBuilder.append("&src=");
        stringBuilder.append(context.getPackageName());
        Uri parse = Uri.parse(stringBuilder.toString());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(parse);
        context.startActivity(intent);
    }

    public static void finish(Context context) {
        if (context != null) {
            C1192a.m4471a(context);
        }
    }

    public static boolean openBaiduMapDrivingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        } else if (routeParaOption.f3549b == null && routeParaOption.f3548a == null && routeParaOption.f3551d == null && routeParaOption.f3550c == null) {
            throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
        } else if (routeParaOption.f3550c == null && routeParaOption.f3548a == null) {
            throw new IllegalNaviArgumentException("startPoint and startName not all null.");
        } else if (routeParaOption.f3551d == null && routeParaOption.f3549b == null) {
            throw new IllegalNaviArgumentException("endPoint and endName not all null.");
        } else if (((routeParaOption.f3550c == null || routeParaOption.f3550c.equals("")) && routeParaOption.f3548a == null) || ((routeParaOption.f3551d == null || routeParaOption.f3551d.equals("")) && routeParaOption.f3549b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        } else {
            if (routeParaOption.f3553f == null) {
                routeParaOption.f3553f = EBusStrategyType.bus_recommend_way;
            }
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                if (f3546a) {
                    m4518a(routeParaOption, context, 0);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            } else if (baiduMapVersion >= 810) {
                return C1192a.m4477a(routeParaOption, context, 0);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
                if (f3546a) {
                    m4518a(routeParaOption, context, 0);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
            }
        }
    }

    public static boolean openBaiduMapTransitRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        } else if (routeParaOption.f3549b == null && routeParaOption.f3548a == null && routeParaOption.f3551d == null && routeParaOption.f3550c == null) {
            throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
        } else if (routeParaOption.f3550c == null && routeParaOption.f3548a == null) {
            throw new IllegalNaviArgumentException("startPoint and startName not all null.");
        } else if (routeParaOption.f3551d == null && routeParaOption.f3549b == null) {
            throw new IllegalNaviArgumentException("endPoint and endName not all null.");
        } else if (((routeParaOption.f3550c == null || routeParaOption.f3550c.equals("")) && routeParaOption.f3548a == null) || ((routeParaOption.f3551d == null || routeParaOption.f3551d.equals("")) && routeParaOption.f3549b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        } else {
            if (routeParaOption.f3553f == null) {
                routeParaOption.f3553f = EBusStrategyType.bus_recommend_way;
            }
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                if (f3546a) {
                    m4518a(routeParaOption, context, 1);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            } else if (baiduMapVersion >= 810) {
                return C1192a.m4477a(routeParaOption, context, 1);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
                if (f3546a) {
                    m4518a(routeParaOption, context, 1);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
            }
        }
    }

    public static boolean openBaiduMapWalkingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        } else if (routeParaOption.f3549b == null && routeParaOption.f3548a == null && routeParaOption.f3551d == null && routeParaOption.f3550c == null) {
            throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
        } else if (routeParaOption.f3550c == null && routeParaOption.f3548a == null) {
            throw new IllegalNaviArgumentException("startPoint and startName not all null.");
        } else if (routeParaOption.f3551d == null && routeParaOption.f3549b == null) {
            throw new IllegalNaviArgumentException("endPoint and endName not all null.");
        } else if (((routeParaOption.f3550c == null || routeParaOption.f3550c.equals("")) && routeParaOption.f3548a == null) || ((routeParaOption.f3551d == null || routeParaOption.f3551d.equals("")) && routeParaOption.f3549b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        } else {
            if (routeParaOption.f3553f == null) {
                routeParaOption.f3553f = EBusStrategyType.bus_recommend_way;
            }
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                if (f3546a) {
                    m4518a(routeParaOption, context, 2);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            } else if (baiduMapVersion >= 810) {
                return C1192a.m4477a(routeParaOption, context, 2);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
                if (f3546a) {
                    m4518a(routeParaOption, context, 2);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
            }
        }
    }

    public static void setSupportWebRoute(boolean z) {
        f3546a = z;
    }
}
