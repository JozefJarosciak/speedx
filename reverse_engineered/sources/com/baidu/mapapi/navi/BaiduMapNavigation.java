package com.baidu.mapapi.navi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.utils.C1192a;
import com.baidu.mapapi.utils.OpenClientUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaiduMapNavigation {
    /* renamed from: a */
    private static boolean f3299a = true;

    /* renamed from: a */
    private static String m4365a(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (NameNotFoundException e) {
            }
        } catch (NameNotFoundException e2) {
            Object obj = applicationInfo;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /* renamed from: a */
    private static void m4366a(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        } else if (naviParaOption.f3300a == null || naviParaOption.f3302c == null) {
            throw new IllegalNaviArgumentException("you must set start and end point.");
        } else {
            GeoPoint ll2mc = CoordUtil.ll2mc(naviParaOption.f3300a);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(naviParaOption.f3302c);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://app.navi.baidu.com/mobile/#navi/naving/");
            stringBuilder.append("&sy=0");
            stringBuilder.append("&endp=");
            stringBuilder.append("&start=");
            stringBuilder.append("&startwd=");
            stringBuilder.append("&endwd=");
            stringBuilder.append("&fromprod=map_sdk");
            stringBuilder.append("&app_version=");
            stringBuilder.append(VersionInfo.VERSION_INFO);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("type", C0844a.f2048d);
            if (naviParaOption.f3301b == null || naviParaOption.f3301b.equals("")) {
                try {
                    jSONObject.put("keyword", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                jSONObject.put("keyword", naviParaOption.f3301b);
            }
            jSONObject.put("xy", String.valueOf(ll2mc.getLongitudeE6()) + "," + String.valueOf(ll2mc.getLatitudeE6()));
            jSONArray.put(jSONObject);
            jSONObject2.put("type", C0844a.f2048d);
            if (naviParaOption.f3303d == null || naviParaOption.f3303d.equals("")) {
                jSONObject.put("keyword", "");
            } else {
                jSONObject.put("keyword", naviParaOption.f3303d);
            }
            jSONObject2.put("xy", String.valueOf(ll2mc2.getLongitudeE6()) + "," + String.valueOf(ll2mc2.getLatitudeE6()));
            jSONArray.put(jSONObject2);
            if (jSONArray.length() > 0) {
                stringBuilder.append("&positions=");
                stringBuilder.append(jSONArray.toString());
            }
            stringBuilder.append("&ctrl_type=");
            stringBuilder.append("&mrsl=");
            stringBuilder.append("/vt=map&state=entry");
            Uri parse = Uri.parse(stringBuilder.toString());
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(parse);
            context.startActivity(intent);
        }
    }

    public static void finish(Context context) {
        if (context != null) {
            C1192a.m4471a(context);
        }
    }

    public static boolean openBaiduMapBikeNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        } else if (naviParaOption.f3302c == null || naviParaOption.f3300a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        } else {
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                return false;
            } else if (baiduMapVersion >= 869) {
                return C1192a.m4475a(naviParaOption, context, 8);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
                return false;
            }
        }
    }

    public static boolean openBaiduMapNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        } else if (naviParaOption.f3302c == null || naviParaOption.f3300a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        } else {
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                if (f3299a) {
                    m4366a(naviParaOption, context);
                    return true;
                }
                throw new BaiduMapAppNotSupportNaviException("BaiduMap app is not installed.");
            } else if (baiduMapVersion >= 830) {
                return C1192a.m4475a(naviParaOption, context, 5);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.2");
                if (f3299a) {
                    m4366a(naviParaOption, context);
                    return true;
                }
                throw new BaiduMapAppNotSupportNaviException("Baidumap app version is too lowl.Version is greater than 8.2");
            }
        }
    }

    public static boolean openBaiduMapWalkNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        } else if (naviParaOption.f3302c == null || naviParaOption.f3300a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        } else {
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                return false;
            } else if (baiduMapVersion >= 869) {
                return C1192a.m4475a(naviParaOption, context, 7);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
                return false;
            }
        }
    }

    public static boolean openBaiduMapWalkNaviAR(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        } else if (naviParaOption.f3302c == null || naviParaOption.f3300a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        } else {
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                return false;
            } else if (baiduMapVersion >= 869) {
                return C1192a.m4475a(naviParaOption, context, 9);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
                return false;
            }
        }
    }

    @Deprecated
    public static void openWebBaiduMapNavi(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        } else if (naviParaOption.f3300a != null && naviParaOption.f3302c != null) {
            GeoPoint ll2mc = CoordUtil.ll2mc(naviParaOption.f3300a);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(naviParaOption.f3302c);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://daohang.map.baidu.com/mobile/#navi/naving/start=");
            stringBuilder.append(ll2mc.getLongitudeE6());
            stringBuilder.append(",");
            stringBuilder.append(ll2mc.getLatitudeE6());
            stringBuilder.append("&endp=");
            stringBuilder.append(ll2mc2.getLongitudeE6());
            stringBuilder.append(",");
            stringBuilder.append(ll2mc2.getLatitudeE6());
            stringBuilder.append("&fromprod=");
            stringBuilder.append(m4365a(context));
            stringBuilder.append("/vt=map&state=entry");
            r0 = Uri.parse(stringBuilder.toString());
            r1 = new Intent();
            r1.setAction("android.intent.action.VIEW");
            r1.setFlags(268435456);
            r1.setData(r0);
            context.startActivity(r1);
        } else if (naviParaOption.f3301b == null || naviParaOption.f3301b.equals("") || naviParaOption.f3303d == null || naviParaOption.f3303d.equals("")) {
            throw new IllegalNaviArgumentException("you must set start and end point or set the start and end name.");
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("http://daohang.map.baidu.com/mobile/#search/search/qt=nav&sn=2$$$$$$");
            stringBuilder2.append(naviParaOption.f3301b);
            stringBuilder2.append("$$$$$$&en=2$$$$$$");
            stringBuilder2.append(naviParaOption.f3303d);
            stringBuilder2.append("$$$$$$&fromprod=");
            stringBuilder2.append(m4365a(context));
            r0 = Uri.parse(stringBuilder2.toString());
            r1 = new Intent();
            r1.setAction("android.intent.action.VIEW");
            r1.setData(r0);
            context.startActivity(r1);
        }
    }

    public static void setSupportWebNavi(boolean z) {
        f3299a = z;
    }
}
