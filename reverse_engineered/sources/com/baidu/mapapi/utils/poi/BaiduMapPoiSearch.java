package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.C1192a;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.platform.comapi.pano.C1269a;
import com.baidu.platform.comapi.pano.PanoStateError;
import com.baidu.platform.comapi.util.CoordTrans;
import java.util.List;

public class BaiduMapPoiSearch {
    /* renamed from: a */
    private static boolean f3540a = true;

    /* renamed from: com.baidu.mapapi.utils.poi.BaiduMapPoiSearch$1 */
    static /* synthetic */ class C11991 {
        /* renamed from: a */
        static final /* synthetic */ int[] f3538a = new int[PanoStateError.values().length];
        /* renamed from: b */
        static final /* synthetic */ int[] f3539b = new int[HttpStateError.values().length];

        static {
            try {
                f3539b[HttpStateError.NETWORK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3539b[HttpStateError.INNER_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3538a[PanoStateError.PANO_UID_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3538a[PanoStateError.PANO_NOT_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3538a[PanoStateError.PANO_NO_TOKEN.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3538a[PanoStateError.PANO_NO_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* renamed from: a */
    private static void m4509a(PoiParaOption poiParaOption, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://api.map.baidu.com/place/detail?");
        stringBuilder.append("uid=");
        stringBuilder.append(poiParaOption.f3541a);
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

    /* renamed from: b */
    private static void m4511b(PoiParaOption poiParaOption, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://api.map.baidu.com/place/search?");
        stringBuilder.append("query=");
        stringBuilder.append(poiParaOption.f3542b);
        stringBuilder.append("&location=");
        LatLng latLng = poiParaOption.f3543c;
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            latLng = CoordTrans.gcjToBaidu(latLng);
        }
        stringBuilder.append(latLng.latitude);
        stringBuilder.append(",");
        stringBuilder.append(latLng.longitude);
        stringBuilder.append("&radius=");
        stringBuilder.append(poiParaOption.f3544d);
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

    /* renamed from: b */
    private static void m4512b(String str, Context context) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("pano id can not be null.");
        } else if (context == null) {
            throw new RuntimeException("context cannot be null.");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("baidumap://map/streetscape?");
            stringBuilder.append("panoid=").append(str);
            stringBuilder.append("&pid=").append(str);
            stringBuilder.append("&panotype=").append("street");
            stringBuilder.append("&src=").append("sdk_[" + context.getPackageName() + "]");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
            intent.setFlags(268435456);
            if (intent == null || intent.resolveActivity(context.getPackageManager()) == null) {
                throw new RuntimeException("BaiduMap app is not installed.");
            }
            context.startActivity(intent);
        }
    }

    public static boolean dispatchPoiToBaiduMap(List<DispathcPoiData> list, Context context) throws Exception {
        if (list.isEmpty() || list.size() <= 0) {
            throw new NullPointerException("dispatch poidata is null");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 840) {
            return C1192a.m4478a((List) list, context, 6);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.4");
        }
        return false;
    }

    public static void finish(Context context) {
        if (context != null) {
            C1192a.m4471a(context);
        }
    }

    public static void openBaiduMapPanoShow(String str, Context context) {
        new C1269a().m4810a(str, new C1201a(context));
    }

    public static boolean openBaiduMapPoiDetialsPage(PoiParaOption poiParaOption, Context context) {
        if (poiParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        } else if (poiParaOption.f3541a == null) {
            throw new IllegalPoiSearchArgumentException("poi uid can not be null.");
        } else if (poiParaOption.f3541a.equals("")) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi uid can not be empty string");
            return false;
        } else {
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                if (f3540a) {
                    m4509a(poiParaOption, context);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            } else if (baiduMapVersion >= 810) {
                return C1192a.m4476a(poiParaOption, context, 3);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
                if (f3540a) {
                    m4509a(poiParaOption, context);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
            }
        }
    }

    public static boolean openBaiduMapPoiNearbySearch(PoiParaOption poiParaOption, Context context) {
        if (poiParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        } else if (poiParaOption.f3542b == null) {
            throw new IllegalPoiSearchArgumentException("poi search key can not be null.");
        } else if (poiParaOption.f3543c == null) {
            throw new IllegalPoiSearchArgumentException("poi search center can not be null.");
        } else if (poiParaOption.f3543c.longitude == 0.0d || poiParaOption.f3543c.latitude == 0.0d) {
            throw new IllegalPoiSearchArgumentException("poi search center longitude or latitude can not be 0.");
        } else if (poiParaOption.f3544d == 0) {
            throw new IllegalPoiSearchArgumentException("poi search radius larger than 0.");
        } else if (poiParaOption.f3542b.equals("")) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi key can not be empty string");
            return false;
        } else {
            int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
            if (baiduMapVersion == 0) {
                Log.e("baidumapsdk", "BaiduMap app is not installed.");
                if (f3540a) {
                    m4511b(poiParaOption, context);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            } else if (baiduMapVersion >= 810) {
                return C1192a.m4476a(poiParaOption, context, 4);
            } else {
                Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
                if (f3540a) {
                    m4511b(poiParaOption, context);
                    return true;
                }
                throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
            }
        }
    }

    public static void setSupportWebPoi(boolean z) {
        f3540a = z;
    }
}
