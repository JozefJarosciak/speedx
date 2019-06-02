package com.baidu.mapapi;

import android.content.Context;
import com.baidu.platform.comapi.C1220c;

public class SDKInitializer {
    public static final String SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR = "network error";
    public static final String SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR = "permission check error";
    public static final String SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK = "permission check ok";
    public static final String SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE = "error_code";
    /* renamed from: a */
    private static CoordType f2758a = CoordType.BD09LL;

    private SDKInitializer() {
    }

    public static CoordType getCoordType() {
        return f2758a;
    }

    public static void initialize(Context context) {
        initialize(null, context);
    }

    public static void initialize(String str, Context context) {
        C1220c.m4569a(str, context);
    }

    public static void setCoordType(CoordType coordType) {
        f2758a = coordType;
    }
}
