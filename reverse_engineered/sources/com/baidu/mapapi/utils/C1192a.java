package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.packet.C0861d;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.common.AppTools;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.utils.poi.DispathcPoiData;
import com.baidu.mapapi.utils.poi.PoiParaOption;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.baidu.mapapi.utils.route.RouteParaOption.EBusStrategyType;
import com.baidu.mapframework.open.aidl.C1204a;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.platform.comapi.p045a.C1216a;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.services.directions.v5.DirectionsCriteria;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mapapi.utils.a */
public class C1192a {
    /* renamed from: a */
    public static int f3512a = -1;
    /* renamed from: b */
    static ServiceConnection f3513b = new C1196c();
    /* renamed from: c */
    private static final String f3514c = C1192a.class.getName();
    /* renamed from: d */
    private static C1204a f3515d;
    /* renamed from: e */
    private static IComOpenClient f3516e;
    /* renamed from: f */
    private static int f3517f;
    /* renamed from: g */
    private static String f3518g = null;
    /* renamed from: h */
    private static String f3519h = null;
    /* renamed from: i */
    private static String f3520i = null;
    /* renamed from: j */
    private static List<DispathcPoiData> f3521j = new ArrayList();
    /* renamed from: k */
    private static LatLng f3522k = null;
    /* renamed from: l */
    private static LatLng f3523l = null;
    /* renamed from: m */
    private static String f3524m = null;
    /* renamed from: n */
    private static String f3525n = null;
    /* renamed from: o */
    private static EBusStrategyType f3526o;
    /* renamed from: p */
    private static String f3527p = null;
    /* renamed from: q */
    private static String f3528q = null;
    /* renamed from: r */
    private static LatLng f3529r = null;
    /* renamed from: s */
    private static int f3530s = 0;
    /* renamed from: t */
    private static boolean f3531t = false;
    /* renamed from: u */
    private static boolean f3532u = false;
    /* renamed from: v */
    private static Thread f3533v;

    /* renamed from: a */
    public static String m4469a() {
        return AppTools.getBaiduMapToken();
    }

    /* renamed from: a */
    public static void m4470a(int i, Context context) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                C1192a.m4489c(context, i);
                return;
            case 3:
                C1192a.m4488c(context);
                return;
            case 4:
                C1192a.m4491d(context);
                return;
            case 5:
                C1192a.m4493e(context);
                return;
            case 7:
                C1192a.m4494f(context);
                return;
            case 8:
                C1192a.m4496g(context);
                return;
            case 9:
                C1192a.m4498h(context);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public static void m4471a(Context context) {
        if (f3532u) {
            context.unbindService(f3513b);
            f3532u = false;
        }
    }

    /* renamed from: a */
    private static void m4472a(List<DispathcPoiData> list, Context context) {
        f3518g = context.getPackageName();
        f3519h = C1192a.m4481b(context);
        f3520i = "";
        if (f3521j != null) {
            f3521j.clear();
        }
        for (DispathcPoiData add : list) {
            f3521j.add(add);
        }
    }

    /* renamed from: a */
    public static boolean m4473a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                return C1192a.m4497g();
            case 3:
                return C1192a.m4499h();
            case 4:
                return C1192a.m4504m();
            case 5:
                return C1192a.m4501j();
            case 6:
                return C1192a.m4500i();
            case 7:
                return C1192a.m4502k();
            case 8:
                return C1192a.m4503l();
            default:
                return false;
        }
    }

    /* renamed from: a */
    public static boolean m4474a(Context context, int i) {
        try {
            if (C1216a.m4555a(context)) {
                f3531t = false;
                switch (i) {
                    case 0:
                        f3512a = 0;
                        break;
                    case 1:
                        f3512a = 1;
                        break;
                    case 2:
                        f3512a = 2;
                        break;
                    case 3:
                        f3512a = 3;
                        break;
                    case 4:
                        f3512a = 4;
                        break;
                    case 5:
                        f3512a = 5;
                        break;
                    case 6:
                        f3512a = 6;
                        break;
                    case 7:
                        f3512a = 7;
                        break;
                    case 8:
                        f3512a = 8;
                        break;
                    case 9:
                        f3512a = 9;
                        break;
                }
                if (i == 9) {
                    f3532u = false;
                }
                if (f3515d == null || !f3532u) {
                    C1192a.m4482b(context, i);
                    return true;
                } else if (f3516e != null) {
                    f3531t = true;
                    return C1192a.m4473a(i);
                } else {
                    f3515d.mo2666a(new C1195b(i));
                    return true;
                }
            }
            Log.d(f3514c, "package sign verify failed");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m4475a(NaviParaOption naviParaOption, Context context, int i) {
        C1192a.m4483b(naviParaOption, context, i);
        return C1192a.m4474a(context, i);
    }

    /* renamed from: a */
    public static boolean m4476a(PoiParaOption poiParaOption, Context context, int i) {
        C1192a.m4484b(poiParaOption, context, i);
        return C1192a.m4474a(context, i);
    }

    /* renamed from: a */
    public static boolean m4477a(RouteParaOption routeParaOption, Context context, int i) {
        C1192a.m4485b(routeParaOption, context, i);
        return C1192a.m4474a(context, i);
    }

    /* renamed from: a */
    public static boolean m4478a(List<DispathcPoiData> list, Context context, int i) {
        C1192a.m4472a((List) list, context);
        return C1192a.m4474a(context, i);
    }

    /* renamed from: b */
    public static String m4481b(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (NameNotFoundException e) {
            }
        } catch (NameNotFoundException e2) {
            Object obj = applicationInfo;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /* renamed from: b */
    private static void m4482b(Context context, int i) {
        Intent intent = new Intent();
        String a = C1192a.m4469a();
        if (a != null) {
            intent.putExtra("api_token", a);
            intent.setAction("com.baidu.map.action.OPEN_SERVICE");
            intent.setPackage("com.baidu.BaiduMap");
            if (i != 9) {
                f3532u = context.bindService(intent, f3513b, 1);
            }
            if (f3532u) {
                f3533v = new Thread(new C1198e(context, i));
                f3533v.setDaemon(true);
                f3533v.start();
                return;
            }
            Log.e("baidumapsdk", "bind service failed，call openapi");
            C1192a.m4470a(i, context);
        }
    }

    /* renamed from: b */
    private static void m4483b(NaviParaOption naviParaOption, Context context, int i) {
        f3518g = context.getPackageName();
        f3524m = null;
        f3522k = null;
        f3525n = null;
        f3523l = null;
        if (naviParaOption.getStartPoint() != null) {
            f3522k = naviParaOption.getStartPoint();
        }
        if (naviParaOption.getEndPoint() != null) {
            f3523l = naviParaOption.getEndPoint();
        }
        if (naviParaOption.getStartName() != null) {
            f3524m = naviParaOption.getStartName();
        }
        if (naviParaOption.getEndName() != null) {
            f3525n = naviParaOption.getEndName();
        }
    }

    /* renamed from: b */
    private static void m4484b(PoiParaOption poiParaOption, Context context, int i) {
        f3527p = null;
        f3528q = null;
        f3529r = null;
        f3530s = 0;
        f3518g = context.getPackageName();
        if (poiParaOption.getUid() != null) {
            f3527p = poiParaOption.getUid();
        }
        if (poiParaOption.getKey() != null) {
            f3528q = poiParaOption.getKey();
        }
        if (poiParaOption.getCenter() != null) {
            f3529r = poiParaOption.getCenter();
        }
        if (poiParaOption.getRadius() != 0) {
            f3530s = poiParaOption.getRadius();
        }
    }

    /* renamed from: b */
    private static void m4485b(RouteParaOption routeParaOption, Context context, int i) {
        f3524m = null;
        f3522k = null;
        f3525n = null;
        f3523l = null;
        f3518g = context.getPackageName();
        if (routeParaOption.getStartPoint() != null) {
            f3522k = routeParaOption.getStartPoint();
        }
        if (routeParaOption.getEndPoint() != null) {
            f3523l = routeParaOption.getEndPoint();
        }
        if (routeParaOption.getStartName() != null) {
            f3524m = routeParaOption.getStartName();
        }
        if (routeParaOption.getEndName() != null) {
            f3525n = routeParaOption.getEndName();
        }
        if (routeParaOption.getBusStrategyType() != null) {
            f3526o = routeParaOption.getBusStrategyType();
        }
        switch (i) {
            case 0:
                f3517f = 0;
                return;
            case 1:
                f3517f = 1;
                return;
            case 2:
                f3517f = 2;
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    private static void m4488c(Context context) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/place/detail?");
        stringBuilder.append("uid=").append(f3527p);
        stringBuilder.append("&show_type=").append("detail_page");
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: c */
    private static void m4489c(Context context, int i) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        String[] strArr = new String[]{DirectionsCriteria.PROFILE_DRIVING, "transit", DirectionsCriteria.PROFILE_WALKING};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/direction?");
        stringBuilder.append("origin=");
        if (f3522k != null && SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3522k = CoordTrans.gcjToBaidu(f3522k);
        }
        if (!TextUtils.isEmpty(f3524m) && f3522k != null) {
            stringBuilder.append("name:").append(f3524m).append("|latlng:").append(f3522k.latitude).append(",").append(f3522k.longitude);
        } else if (!TextUtils.isEmpty(f3524m)) {
            stringBuilder.append(f3524m);
        } else if (f3522k != null) {
            stringBuilder.append(f3522k.latitude).append(",").append(f3522k.longitude);
        }
        stringBuilder.append("&destination=");
        if (f3523l != null && SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3523l = CoordTrans.gcjToBaidu(f3523l);
        }
        if (!TextUtils.isEmpty(f3525n) && f3523l != null) {
            stringBuilder.append("name:").append(f3525n).append("|latlng:").append(f3523l.latitude).append(",").append(f3523l.longitude);
        } else if (!TextUtils.isEmpty(f3525n)) {
            stringBuilder.append(f3525n);
        } else if (f3523l != null) {
            stringBuilder.append(f3523l.latitude).append(",").append(f3523l.longitude);
        }
        stringBuilder.append("&mode=").append(strArr[i]);
        stringBuilder.append("&target=").append(C0844a.f2048d);
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: d */
    private static void m4491d(Context context) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/nearbysearch?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3529r = CoordTrans.gcjToBaidu(f3529r);
        }
        stringBuilder.append("center=").append(f3529r.latitude).append(",").append(f3529r.longitude);
        stringBuilder.append("&query=").append(f3528q).append("&radius=").append(f3530s);
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: e */
    private static void m4493e(Context context) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/navi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3522k = CoordTrans.gcjToBaidu(f3522k);
            f3523l = CoordTrans.gcjToBaidu(f3523l);
        }
        stringBuilder.append("origin=").append(f3522k.latitude).append(",").append(f3522k.longitude);
        stringBuilder.append("&location=").append(f3523l.latitude).append(",").append(f3523l.longitude);
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: f */
    private static void m4494f(Context context) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/walknavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3522k = CoordTrans.gcjToBaidu(f3522k);
            f3523l = CoordTrans.gcjToBaidu(f3523l);
        }
        stringBuilder.append("origin=").append(f3522k.latitude).append(",").append(f3522k.longitude);
        stringBuilder.append("&destination=").append(f3523l.latitude).append(",").append(f3523l.longitude);
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: g */
    private static void m4496g(Context context) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/bikenavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3522k = CoordTrans.gcjToBaidu(f3522k);
            f3523l = CoordTrans.gcjToBaidu(f3523l);
        }
        stringBuilder.append("origin=").append(f3522k.latitude).append(",").append(f3522k.longitude);
        stringBuilder.append("&destination=").append(f3523l.latitude).append(",").append(f3523l.longitude);
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: g */
    private static boolean m4497g() {
        try {
            Log.d(f3514c, "callDispatchTakeOutRoute");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "route_search_page");
                Bundle bundle2 = new Bundle();
                bundle2.putInt("route_type", f3517f);
                bundle2.putInt("bus_strategy", f3526o.ordinal());
                bundle2.putInt("cross_city_bus_strategy", 5);
                if (f3522k != null) {
                    bundle2.putInt("start_type", 1);
                    bundle2.putInt("start_longitude", (int) CoordUtil.ll2mc(f3522k).getLongitudeE6());
                    bundle2.putInt("start_latitude", (int) CoordUtil.ll2mc(f3522k).getLatitudeE6());
                } else {
                    bundle2.putInt("start_type", 2);
                    bundle2.putInt("start_longitude", 0);
                    bundle2.putInt("start_latitude", 0);
                }
                if (f3524m != null) {
                    bundle2.putString("start_keyword", f3524m);
                } else {
                    bundle2.putString("start_keyword", "地图上的点");
                }
                bundle2.putString("start_uid", "");
                if (f3523l != null) {
                    bundle2.putInt("end_type", 1);
                    bundle2.putInt("end_longitude", (int) CoordUtil.ll2mc(f3523l).getLongitudeE6());
                    bundle2.putInt("end_latitude", (int) CoordUtil.ll2mc(f3523l).getLatitudeE6());
                } else {
                    bundle2.putInt("end_type", 2);
                    bundle2.putInt("end_longitude", 0);
                    bundle2.putInt("end_latitude", 0);
                }
                if (f3525n != null) {
                    bundle2.putString("end_keyword", f3525n);
                } else {
                    bundle2.putString("end_keyword", "地图上的点");
                }
                bundle2.putString("end_uid", "");
                bundle.putBundle("base_params", bundle2);
                bundle2 = new Bundle();
                bundle2.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle2);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchTakeOut com not found");
            return false;
        } catch (Throwable e) {
            Log.d(f3514c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* renamed from: h */
    private static void m4498h(Context context) {
        if (f3533v != null) {
            f3533v.interrupt();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("baidumap://map/walknavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f3522k = CoordTrans.gcjToBaidu(f3522k);
            f3523l = CoordTrans.gcjToBaidu(f3523l);
        }
        stringBuilder.append("origin=").append(f3522k.latitude).append(",").append(f3522k.longitude);
        stringBuilder.append("&destination=").append(f3523l.latitude).append(",").append(f3523l.longitude);
        stringBuilder.append("&mode=").append("walking_ar");
        stringBuilder.append("&src=").append("sdk_[" + f3518g + "]");
        Log.e("test", stringBuilder.toString());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: h */
    private static boolean m4499h() {
        try {
            Log.d(f3514c, "callDispatchTakeOutPoiDetials");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "request_poi_detail_page");
                Bundle bundle2 = new Bundle();
                if (f3527p != null) {
                    bundle2.putString("uid", f3527p);
                } else {
                    bundle2.putString("uid", "");
                }
                bundle.putBundle("base_params", bundle2);
                bundle2 = new Bundle();
                bundle2.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle2);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchTakeOut com not found");
            return false;
        } catch (Throwable e) {
            Log.d(f3514c, "callDispatchTakeOut exception", e);
        }
    }

    /* renamed from: i */
    private static boolean m4500i() {
        int i;
        JSONException e;
        if (f3521j == null || f3521j.size() <= 0) {
            return false;
        }
        try {
            Log.d(f3514c, "callDispatchPoiToBaiduMap");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "favorite_page");
                Bundle bundle2 = new Bundle();
                JSONArray jSONArray = new JSONArray();
                int i2 = 0;
                int i3 = 0;
                while (i2 < f3521j.size()) {
                    if (((DispathcPoiData) f3521j.get(i2)).name == null || ((DispathcPoiData) f3521j.get(i2)).name.equals("")) {
                        i = i3;
                    } else if (((DispathcPoiData) f3521j.get(i2)).pt == null) {
                        i = i3;
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("name", ((DispathcPoiData) f3521j.get(i2)).name);
                            GeoPoint ll2mc = CoordUtil.ll2mc(((DispathcPoiData) f3521j.get(i2)).pt);
                            jSONObject.put("ptx", ll2mc.getLongitudeE6());
                            jSONObject.put("pty", ll2mc.getLatitudeE6());
                            jSONObject.put("addr", ((DispathcPoiData) f3521j.get(i2)).addr);
                            jSONObject.put("uid", ((DispathcPoiData) f3521j.get(i2)).uid);
                            i = i3 + 1;
                            try {
                                jSONArray.put(jSONObject);
                            } catch (JSONException e2) {
                                e = e2;
                                e.printStackTrace();
                                i2++;
                                i3 = i;
                            }
                        } catch (JSONException e3) {
                            JSONException jSONException = e3;
                            i = i3;
                            e = jSONException;
                            e.printStackTrace();
                            i2++;
                            i3 = i;
                        }
                    }
                    i2++;
                    i3 = i;
                }
                if (i3 == 0) {
                    return false;
                }
                bundle2.putString(C0861d.f2139k, jSONArray.toString());
                bundle2.putString("from", f3519h);
                bundle2.putString("pkg", f3518g);
                bundle2.putString("cls", f3520i);
                bundle2.putInt("count", i3);
                bundle.putBundle("base_params", bundle2);
                Bundle bundle3 = new Bundle();
                bundle3.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle3);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchPoiToBaiduMap com not found");
            return false;
        } catch (Throwable e4) {
            Log.d(f3514c, "callDispatchPoiToBaiduMap exception", e4);
        }
    }

    /* renamed from: j */
    private static boolean m4501j() {
        try {
            Log.d(f3514c, "callDispatchTakeOutRouteNavi");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "navigation_page");
                Bundle bundle2 = new Bundle();
                bundle2.putString("coord_type", "bd09ll");
                bundle2.putString("type", "DIS");
                StringBuffer stringBuffer = new StringBuffer();
                if (f3524m != null) {
                    stringBuffer.append("name:" + f3524m + "|");
                }
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    f3522k = CoordTrans.gcjToBaidu(f3522k);
                }
                stringBuffer.append(String.format("latlng:%f,%f", new Object[]{Double.valueOf(f3522k.latitude), Double.valueOf(f3522k.longitude)}));
                StringBuffer stringBuffer2 = new StringBuffer();
                if (f3525n != null) {
                    stringBuffer2.append("name:" + f3525n + "|");
                }
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    f3523l = CoordTrans.gcjToBaidu(f3523l);
                }
                stringBuffer2.append(String.format("latlng:%f,%f", new Object[]{Double.valueOf(f3523l.latitude), Double.valueOf(f3523l.longitude)}));
                bundle2.putString("origin", stringBuffer.toString());
                bundle2.putString("destination", stringBuffer2.toString());
                bundle.putBundle("base_params", bundle2);
                bundle2 = new Bundle();
                bundle2.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle2);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchTakeOut com not found");
            return false;
        } catch (Throwable e) {
            Log.d(f3514c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* renamed from: k */
    private static boolean m4502k() {
        try {
            Log.d(f3514c, "callDispatchTakeOutRouteNavi");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "walknavi_page");
                Bundle bundle2 = new Bundle();
                bundle2.putString("coord_type", "bd09ll");
                StringBuffer stringBuffer = new StringBuffer();
                if (f3524m != null) {
                    stringBuffer.append("name:" + f3524m + "|");
                }
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    f3522k = CoordTrans.gcjToBaidu(f3522k);
                }
                stringBuffer.append(String.format("latlng:%f,%f", new Object[]{Double.valueOf(f3522k.latitude), Double.valueOf(f3522k.longitude)}));
                StringBuffer stringBuffer2 = new StringBuffer();
                if (f3525n != null) {
                    stringBuffer2.append("name:" + f3525n + "|");
                }
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    f3523l = CoordTrans.gcjToBaidu(f3523l);
                }
                stringBuffer2.append(String.format("latlng:%f,%f", new Object[]{Double.valueOf(f3523l.latitude), Double.valueOf(f3523l.longitude)}));
                bundle2.putString("origin", stringBuffer.toString());
                bundle2.putString("destination", stringBuffer2.toString());
                bundle.putBundle("base_params", bundle2);
                bundle2 = new Bundle();
                bundle2.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle2);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchTakeOut com not found");
            return false;
        } catch (Throwable e) {
            Log.d(f3514c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* renamed from: l */
    private static boolean m4503l() {
        try {
            Log.d(f3514c, "callDispatchTakeOutRouteRidingNavi");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "bikenavi_page");
                Bundle bundle2 = new Bundle();
                bundle2.putString("coord_type", "bd09ll");
                StringBuffer stringBuffer = new StringBuffer();
                if (f3524m != null) {
                    stringBuffer.append("name:" + f3524m + "|");
                }
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    f3522k = CoordTrans.gcjToBaidu(f3522k);
                }
                stringBuffer.append(String.format("latlng:%f,%f", new Object[]{Double.valueOf(f3522k.latitude), Double.valueOf(f3522k.longitude)}));
                StringBuffer stringBuffer2 = new StringBuffer();
                if (f3525n != null) {
                    stringBuffer2.append("name:" + f3525n + "|");
                }
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    f3523l = CoordTrans.gcjToBaidu(f3523l);
                }
                stringBuffer2.append(String.format("latlng:%f,%f", new Object[]{Double.valueOf(f3523l.latitude), Double.valueOf(f3523l.longitude)}));
                bundle2.putString("origin", stringBuffer.toString());
                bundle2.putString("destination", stringBuffer2.toString());
                bundle.putBundle("base_params", bundle2);
                bundle2 = new Bundle();
                bundle2.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle2);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchTakeOut com not found");
            return false;
        } catch (Throwable e) {
            Log.d(f3514c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* renamed from: m */
    private static boolean m4504m() {
        try {
            Log.d(f3514c, "callDispatchTakeOutPoiNearbySearch");
            String a = f3516e.mo2664a("map.android.baidu.mainmap");
            if (a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "poi_search_page");
                Bundle bundle2 = new Bundle();
                if (f3528q != null) {
                    bundle2.putString("search_key", f3528q);
                } else {
                    bundle2.putString("search_key", "");
                }
                if (f3529r != null) {
                    bundle2.putInt("center_pt_x", (int) CoordUtil.ll2mc(f3529r).getLongitudeE6());
                    bundle2.putInt("center_pt_y", (int) CoordUtil.ll2mc(f3529r).getLatitudeE6());
                } else {
                    bundle2.putString("search_key", "");
                }
                if (f3530s != 0) {
                    bundle2.putInt("search_radius", f3530s);
                } else {
                    bundle2.putInt("search_radius", 1000);
                }
                bundle2.putBoolean("is_direct_search", true);
                bundle2.putBoolean("is_direct_area_search", true);
                bundle.putBundle("base_params", bundle2);
                bundle2 = new Bundle();
                bundle2.putString("launch_from", "sdk_[" + f3518g + "]");
                bundle.putBundle("ext_params", bundle2);
                return f3516e.mo2665a("map.android.baidu.mainmap", a, bundle);
            }
            Log.d(f3514c, "callDispatchTakeOut com not found");
            return false;
        } catch (Throwable e) {
            Log.d(f3514c, "callDispatchTakeOut exception", e);
        }
    }
}
