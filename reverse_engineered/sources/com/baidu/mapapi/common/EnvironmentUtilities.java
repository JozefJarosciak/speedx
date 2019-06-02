package com.baidu.mapapi.common;

import android.content.Context;
import com.baidu.platform.comapi.util.C1280d;
import java.io.File;

public class EnvironmentUtilities {
    /* renamed from: a */
    static String f2775a;
    /* renamed from: b */
    static String f2776b;
    /* renamed from: c */
    static String f2777c;
    /* renamed from: d */
    static int f2778d;
    /* renamed from: e */
    static int f2779e;
    /* renamed from: f */
    static int f2780f;
    /* renamed from: g */
    private static C1280d f2781g = null;

    public static String getAppCachePath() {
        return f2776b;
    }

    public static String getAppSDCardPath() {
        String str = f2775a + "/BaiduMapSDKNew";
        if (str.length() != 0) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return str;
    }

    public static String getAppSecondCachePath() {
        return f2777c;
    }

    public static int getDomTmpStgMax() {
        return f2779e;
    }

    public static int getItsTmpStgMax() {
        return f2780f;
    }

    public static int getMapTmpStgMax() {
        return f2778d;
    }

    public static String getSDCardPath() {
        return f2775a;
    }

    public static void initAppDirectory(Context context) {
        if (f2781g == null) {
            f2781g = C1280d.m4838a();
            f2781g.m4842a(context);
        }
        if (f2775a == null || f2775a.length() <= 0) {
            f2775a = f2781g.m4844b().m4834a();
            f2776b = f2781g.m4844b().m4836c();
        } else {
            f2776b = f2775a + File.separator + "BaiduMapSDKNew" + File.separator + "cache";
        }
        f2777c = f2781g.m4844b().m4837d();
        f2778d = 20971520;
        f2779e = 52428800;
        f2780f = 5242880;
    }

    public static void setSDCardPath(String str) {
        f2775a = str;
    }
}
