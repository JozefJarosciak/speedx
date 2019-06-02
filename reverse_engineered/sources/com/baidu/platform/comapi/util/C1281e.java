package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.sys.C0869a;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.mapapi.VersionInfo;
import com.baidu.platform.comjni.util.AppMD5;
import com.baidu.platform.comjni.util.C1292a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.umeng.onlineconfig.C4766a;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.baidu.platform.comapi.util.e */
public class C1281e {
    /* renamed from: A */
    private static final String f3888A = C1281e.class.getSimpleName();
    /* renamed from: B */
    private static C1292a f3889B = new C1292a();
    /* renamed from: C */
    private static boolean f3890C = true;
    /* renamed from: D */
    private static int f3891D = 0;
    /* renamed from: E */
    private static int f3892E = 0;
    /* renamed from: F */
    private static Map<String, String> f3893F = new HashMap();
    /* renamed from: a */
    static String f3894a = "02";
    /* renamed from: b */
    static String f3895b;
    /* renamed from: c */
    static String f3896c;
    /* renamed from: d */
    static String f3897d;
    /* renamed from: e */
    static String f3898e;
    /* renamed from: f */
    static int f3899f;
    /* renamed from: g */
    static int f3900g;
    /* renamed from: h */
    static int f3901h;
    /* renamed from: i */
    static int f3902i;
    /* renamed from: j */
    static int f3903j;
    /* renamed from: k */
    static int f3904k;
    /* renamed from: l */
    static String f3905l;
    /* renamed from: m */
    static String f3906m;
    /* renamed from: n */
    static String f3907n = "baidu";
    /* renamed from: o */
    static String f3908o = "baidu";
    /* renamed from: p */
    static String f3909p = "";
    /* renamed from: q */
    static String f3910q = "";
    /* renamed from: r */
    static String f3911r = "";
    /* renamed from: s */
    static String f3912s;
    /* renamed from: t */
    static String f3913t;
    /* renamed from: u */
    static String f3914u = "-1";
    /* renamed from: v */
    static String f3915v = "-1";
    /* renamed from: w */
    public static Context f3916w;
    /* renamed from: x */
    public static final int f3917x = Integer.parseInt(VERSION.SDK);
    /* renamed from: y */
    public static float f3918y = 1.0f;
    /* renamed from: z */
    public static String f3919z;

    /* renamed from: a */
    public static void m4846a() {
        C1281e.m4854d();
    }

    /* renamed from: a */
    public static void m4847a(String str) {
        f3905l = str;
        C1281e.m4858f();
    }

    /* renamed from: a */
    public static void m4848a(String str, String str2) {
        f3914u = str2;
        f3915v = str;
        C1281e.m4858f();
    }

    /* renamed from: a */
    public static byte[] m4849a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static Bundle m4850b() {
        Bundle bundle = new Bundle();
        bundle.putString("cpu", f3909p);
        bundle.putString("resid", f3894a);
        bundle.putString(OnlineConfigAgent.KEY_CHANNEL, f3907n);
        bundle.putString("glr", f3910q);
        bundle.putString("glv", f3911r);
        bundle.putString("mb", C1281e.m4860g());
        bundle.putString(C0869a.f2167h, C1281e.m4862i());
        bundle.putString("os", C1281e.m4864k());
        bundle.putInt("dpi_x", C1281e.m4865l());
        bundle.putInt("dpi_y", C1281e.m4865l());
        bundle.putString(C0825c.f1951a, f3905l);
        bundle.putString("cuid", C1281e.m4868o());
        bundle.putByteArray("signature", C1281e.m4849a(f3916w));
        bundle.putString("pcn", f3916w.getPackageName());
        bundle.putInt("screen_x", C1281e.m4861h());
        bundle.putInt("screen_y", C1281e.m4863j());
        if (f3889B != null) {
            f3889B.m4971a(bundle);
            Log.d("phoneInfo", "mAppSysOSAPI not null");
        }
        Log.d("phoneInfo", bundle.toString());
        return bundle;
    }

    /* renamed from: b */
    public static void m4851b(Context context) {
        f3916w = context;
        f3912s = context.getFilesDir().getAbsolutePath();
        f3913t = context.getCacheDir().getAbsolutePath();
        f3896c = Build.MODEL;
        f3897d = "Android" + VERSION.SDK;
        f3895b = context.getPackageName();
        C1281e.m4853c(context);
        C1281e.m4855d(context);
        C1281e.m4857e(context);
        C1281e.m4859f(context);
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(MapboxEvent.TYPE_LOCATION);
            f3891D = locationManager.isProviderEnabled("gps") ? 1 : 0;
            f3892E = locationManager.isProviderEnabled("network") ? 1 : 0;
            f3893F.put("resid", AppMD5.encodeUrlParamsValue(f3894a));
            f3893F.put(OnlineConfigAgent.KEY_CHANNEL, AppMD5.encodeUrlParamsValue(C1281e.m4866m()));
            f3893F.put("mb", AppMD5.encodeUrlParamsValue(C1281e.m4860g()));
            f3893F.put(C0869a.f2167h, AppMD5.encodeUrlParamsValue(C1281e.m4862i()));
            f3893F.put("os", AppMD5.encodeUrlParamsValue(C1281e.m4864k()));
            f3893F.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", new Object[]{Integer.valueOf(C1281e.m4865l()), Integer.valueOf(C1281e.m4865l())})));
            f3893F.put("cuid", AppMD5.encodeUrlParamsValue(C1281e.m4868o()));
            f3893F.put("pcn", AppMD5.encodeUrlParamsValue(f3916w.getPackageName()));
            f3893F.put("screen", AppMD5.encodeUrlParamsValue(String.format("%d,%d", new Object[]{Integer.valueOf(C1281e.m4861h()), Integer.valueOf(C1281e.m4863j())})));
        } catch (Exception e) {
            Log.w("baidumapsdk", "LocationManager error");
            f3893F.put("resid", AppMD5.encodeUrlParamsValue(f3894a));
            f3893F.put(OnlineConfigAgent.KEY_CHANNEL, AppMD5.encodeUrlParamsValue(C1281e.m4866m()));
            f3893F.put("mb", AppMD5.encodeUrlParamsValue(C1281e.m4860g()));
            f3893F.put(C0869a.f2167h, AppMD5.encodeUrlParamsValue(C1281e.m4862i()));
            f3893F.put("os", AppMD5.encodeUrlParamsValue(C1281e.m4864k()));
            f3893F.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", new Object[]{Integer.valueOf(C1281e.m4865l()), Integer.valueOf(C1281e.m4865l())})));
            f3893F.put("cuid", AppMD5.encodeUrlParamsValue(C1281e.m4868o()));
            f3893F.put("pcn", AppMD5.encodeUrlParamsValue(f3916w.getPackageName()));
            f3893F.put("screen", AppMD5.encodeUrlParamsValue(String.format("%d,%d", new Object[]{Integer.valueOf(C1281e.m4861h()), Integer.valueOf(C1281e.m4863j())})));
        } catch (Throwable th) {
            f3893F.put("resid", AppMD5.encodeUrlParamsValue(f3894a));
            f3893F.put(OnlineConfigAgent.KEY_CHANNEL, AppMD5.encodeUrlParamsValue(C1281e.m4866m()));
            f3893F.put("mb", AppMD5.encodeUrlParamsValue(C1281e.m4860g()));
            f3893F.put(C0869a.f2167h, AppMD5.encodeUrlParamsValue(C1281e.m4862i()));
            f3893F.put("os", AppMD5.encodeUrlParamsValue(C1281e.m4864k()));
            f3893F.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", new Object[]{Integer.valueOf(C1281e.m4865l()), Integer.valueOf(C1281e.m4865l())})));
            f3893F.put("cuid", AppMD5.encodeUrlParamsValue(C1281e.m4868o()));
            f3893F.put("pcn", AppMD5.encodeUrlParamsValue(f3916w.getPackageName()));
            f3893F.put("screen", AppMD5.encodeUrlParamsValue(String.format("%d,%d", new Object[]{Integer.valueOf(C1281e.m4861h()), Integer.valueOf(C1281e.m4863j())})));
        }
        if (f3889B != null) {
            f3889B.m4970a();
        }
    }

    /* renamed from: c */
    public static String m4852c() {
        if (f3893F == null) {
            return null;
        }
        Date date = new Date();
        long seconds = ((long) (date.getSeconds() * 1000)) + date.getTime();
        double d = (((double) (seconds % 1000)) / 1000.0d) + ((double) (seconds / 1000));
        f3893F.put("ctm", AppMD5.encodeUrlParamsValue(String.format("%f", new Object[]{Double.valueOf(d)})));
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : f3893F.entrySet()) {
            stringBuilder.append(C0869a.f2161b).append((String) entry.getKey()).append(SimpleComparison.EQUAL_TO_OPERATION).append((String) entry.getValue());
        }
        return stringBuilder.toString();
    }

    /* renamed from: c */
    private static void m4853c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            f3898e = VersionInfo.getApiVersion();
            if (!(f3898e == null || f3898e.equals(""))) {
                f3898e = f3898e.replace('_', CoreConstants.DOT);
            }
            f3899f = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            f3898e = C4766a.f16699b;
            f3899f = 1;
        }
    }

    /* renamed from: d */
    public static void m4854d() {
    }

    /* renamed from: d */
    private static void m4855d(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        if (defaultDisplay != null) {
            f3900g = defaultDisplay.getWidth();
            f3901h = defaultDisplay.getHeight();
            defaultDisplay.getMetrics(displayMetrics);
        }
        f3918y = displayMetrics.density;
        f3902i = (int) displayMetrics.xdpi;
        f3903j = (int) displayMetrics.ydpi;
        if (f3917x > 3) {
            f3904k = displayMetrics.densityDpi;
        } else {
            f3904k = 160;
        }
        if (f3904k == 0) {
            f3904k = 160;
        }
    }

    /* renamed from: e */
    public static String m4856e() {
        return f3905l;
    }

    /* renamed from: e */
    private static void m4857e(Context context) {
        f3906m = Secure.getString(context.getContentResolver(), "android_id");
    }

    /* renamed from: f */
    public static void m4858f() {
        f3893F.put(C0825c.f1951a, AppMD5.encodeUrlParamsValue(C1281e.m4856e()));
        f3893F.put("appid", AppMD5.encodeUrlParamsValue(f3914u));
        f3893F.put("bduid", "");
        if (f3889B != null) {
            Bundle bundle = new Bundle();
            bundle.putString("cpu", f3909p);
            bundle.putString("resid", f3894a);
            bundle.putString(OnlineConfigAgent.KEY_CHANNEL, f3907n);
            bundle.putString("glr", f3910q);
            bundle.putString("glv", f3911r);
            bundle.putString("mb", C1281e.m4860g());
            bundle.putString(C0869a.f2167h, C1281e.m4862i());
            bundle.putString("os", C1281e.m4864k());
            bundle.putInt("dpi_x", C1281e.m4865l());
            bundle.putInt("dpi_y", C1281e.m4865l());
            bundle.putString(C0825c.f1951a, f3905l);
            bundle.putString("cuid", C1281e.m4868o());
            bundle.putString("pcn", f3916w.getPackageName());
            bundle.putInt("screen_x", C1281e.m4861h());
            bundle.putInt("screen_y", C1281e.m4863j());
            bundle.putString("appid", f3914u);
            bundle.putString("duid", f3915v);
            if (!TextUtils.isEmpty(f3919z)) {
                bundle.putString("token", f3919z);
            }
            f3889B.m4971a(bundle);
            SysUpdateObservable.getInstance().updatePhoneInfo();
        }
    }

    /* renamed from: f */
    private static void m4859f(Context context) {
        f3905l = "0";
    }

    /* renamed from: g */
    public static String m4860g() {
        return f3896c;
    }

    /* renamed from: h */
    public static int m4861h() {
        return f3900g;
    }

    /* renamed from: i */
    public static String m4862i() {
        return f3898e;
    }

    /* renamed from: j */
    public static int m4863j() {
        return f3901h;
    }

    /* renamed from: k */
    public static String m4864k() {
        return f3897d;
    }

    /* renamed from: l */
    public static int m4865l() {
        return f3904k;
    }

    /* renamed from: m */
    public static String m4866m() {
        return f3907n;
    }

    /* renamed from: n */
    public static String m4867n() {
        return f3912s;
    }

    /* renamed from: o */
    public static String m4868o() {
        String a;
        try {
            a = CommonParam.m3534a(f3916w);
        } catch (Exception e) {
            a = "";
        }
        return a == null ? "" : a;
    }
}
