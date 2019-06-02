package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;

public class ShareSDK {
    public static final String SDK_TAG = "SHARESDK";
    public static final int SDK_VERSION_INT = 68;
    /* renamed from: a */
    private static C0612f f1223a;
    /* renamed from: b */
    private static boolean f1224b = true;

    public static void initSDK(Context context) {
        initSDK(context, null, true);
    }

    public static void initSDK(Context context, boolean z) {
        initSDK(context, null, z);
    }

    public static void initSDK(Context context, String str) {
        initSDK(context, str, true);
    }

    public static void initSDK(Context context, String str, boolean z) {
        if (DeviceHelper.getInstance(context) == null) {
            throw new RuntimeException("The param of context is null which in ShareSDK.initSDK(context)!");
        } else if (f1223a == null) {
            C0612f c0612f = new C0612f(context, str);
            c0612f.m2238a(z);
            c0612f.startThread();
            f1223a = c0612f;
        }
    }

    public static void stopSDK(Context context) {
        stopSDK();
    }

    public static void stopSDK() {
    }

    /* renamed from: b */
    private static void m1986b() {
        if (f1223a == null) {
            throw new RuntimeException("Please call ShareSDK.initSDK(Context) in the main process before any action.");
        }
    }

    public static void registerService(Class<? extends Service> cls) {
        m1986b();
        f1223a.m2234a((Class) cls);
    }

    public static void unregisterService(Class<? extends Service> cls) {
        m1986b();
        f1223a.m2245b((Class) cls);
    }

    public static <T extends Service> T getService(Class<T> cls) {
        m1986b();
        return f1223a.m2248c((Class) cls);
    }

    public static void registerPlatform(Class<? extends CustomPlatform> cls) {
        m1986b();
        f1223a.m2252d(cls);
    }

    public static void unregisterPlatform(Class<? extends CustomPlatform> cls) {
        m1986b();
        f1223a.m2255e(cls);
    }

    public static String getSDKVersionName() {
        m1986b();
        return f1223a.m2242b();
    }

    public static int getSDKVersionCode() {
        return 68;
    }

    public static void setConnTimeout(int i) {
        m1986b();
        f1223a.m2231a(i);
    }

    public static void setReadTimeout(int i) {
        m1986b();
        f1223a.m2244b(i);
    }

    public static void removeCookieOnAuthorize(boolean z) {
        m1986b();
        f1223a.m2246b(z);
    }

    public static void deleteCache() {
        m1986b();
        f1223a.m2254e();
    }

    @Deprecated
    public static synchronized Platform[] getPlatformList(Context context) {
        Platform[] platformList;
        synchronized (ShareSDK.class) {
            platformList = getPlatformList();
        }
        return platformList;
    }

    public static synchronized Platform[] getPlatformList() {
        Platform[] a;
        synchronized (ShareSDK.class) {
            m1986b();
            a = f1223a.m2240a();
        }
        return a;
    }

    @Deprecated
    public static Platform getPlatform(Context context, String str) {
        m1986b();
        return f1223a.m2227a(str);
    }

    public static Platform getPlatform(String str) {
        m1986b();
        return f1223a.m2227a(str);
    }

    public static void logDemoEvent(int i, Platform platform) {
        m1986b();
        f1223a.m2233a(i, platform);
    }

    public static void logApiEvent(String str, int i) {
        m1986b();
        f1223a.m2235a(str, i);
    }

    public static void setPlatformDevInfo(String str, HashMap<String, Object> hashMap) {
        m1986b();
        f1223a.m2237a(str, (HashMap) hashMap);
    }

    public static String platformIdToName(int i) {
        m1986b();
        return f1223a.m2249c(i);
    }

    public static int platformNameToId(String str) {
        m1986b();
        return f1223a.m2241b(str);
    }

    public static boolean isRemoveCookieOnAuthorize() {
        m1986b();
        return f1223a.m2251c();
    }

    public static void closeDebug() {
        f1224b = false;
    }

    public static boolean isDebug() {
        return f1224b;
    }

    /* renamed from: a */
    static void m1982a(String str, String str2) {
        m1986b();
        f1223a.m2236a(str, str2);
    }

    /* renamed from: a */
    static void m1981a(int i, int i2) {
        m1986b();
        f1223a.m2232a(i, i2);
    }

    /* renamed from: b */
    static String m1985b(String str, String str2) {
        m1986b();
        return f1223a.m2243b(str, str2);
    }

    /* renamed from: a */
    static String m1977a(int i, String str) {
        m1986b();
        return f1223a.m2228a(i, str);
    }

    /* renamed from: a */
    static boolean m1983a() {
        m1986b();
        return f1223a.m2253d();
    }

    /* renamed from: a */
    static boolean m1984a(HashMap<String, Object> hashMap) {
        m1986b();
        return f1223a.m2239a((HashMap) hashMap);
    }

    /* renamed from: b */
    static boolean m1987b(HashMap<String, Object> hashMap) {
        m1986b();
        return f1223a.m2247b((HashMap) hashMap);
    }

    /* renamed from: a */
    static String m1980a(String str, boolean z, int i, String str2) {
        m1986b();
        return f1223a.m2230a(str, z, i, str2);
    }

    /* renamed from: a */
    static String m1979a(String str) {
        m1986b();
        return f1223a.m2250c(str);
    }

    /* renamed from: a */
    static String m1978a(Bitmap bitmap) {
        m1986b();
        return f1223a.m2229a(bitmap);
    }
}
