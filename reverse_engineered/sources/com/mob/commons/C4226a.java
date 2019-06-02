package com.mob.commons;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.alipay.sdk.cons.C0846c;
import com.mob.commons.authorize.C4236a;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CommonConfig */
/* renamed from: com.mob.commons.a */
public class C4226a {
    /* renamed from: a */
    private static HashMap<String, Object> f14874a;
    /* renamed from: b */
    private static long f14875b;
    /* renamed from: c */
    private static long f14876c;
    /* renamed from: d */
    private static boolean f14877d;

    /* renamed from: a */
    public static long m16764a(Context context) {
        long longValue;
        C4226a.m16783o(context);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            longValue = Long.valueOf(String.valueOf(f14874a.get("deviceTime"))).longValue();
        } catch (Throwable th) {
            longValue = 0;
        }
        return ((Long) C4275R.forceCast(f14874a.get("serverTime"), Long.valueOf(0))).longValue() + (elapsedRealtime - longValue);
    }

    /* renamed from: b */
    public static boolean m16770b(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("rt"), Integer.valueOf(1))).intValue();
    }

    /* renamed from: c */
    public static int m16771c(Context context) {
        C4226a.m16783o(context);
        return ((Integer) C4275R.forceCast(f14874a.get("rtsr"), Integer.valueOf(300000))).intValue();
    }

    /* renamed from: d */
    public static boolean m16772d(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("all"), Integer.valueOf(1))).intValue();
    }

    /* renamed from: e */
    public static long m16773e(Context context) {
        C4226a.m16783o(context);
        return ((Long) C4275R.forceCast(f14874a.get("aspa"), Long.valueOf(2592000))).longValue();
    }

    /* renamed from: f */
    public static boolean m16774f(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("di"), Integer.valueOf(1))).intValue();
    }

    /* renamed from: g */
    public static boolean m16775g(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("ext"), Integer.valueOf(1))).intValue();
    }

    /* renamed from: h */
    public static boolean m16776h(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("bs"), Integer.valueOf(1))).intValue();
    }

    /* renamed from: i */
    public static int m16777i(Context context) {
        C4226a.m16783o(context);
        return ((Integer) C4275R.forceCast(f14874a.get("bsgap"), Integer.valueOf(86400))).intValue();
    }

    /* renamed from: j */
    public static boolean m16778j(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("l"), Integer.valueOf(0))).intValue();
    }

    /* renamed from: k */
    public static int m16779k(Context context) {
        C4226a.m16783o(context);
        return ((Integer) C4275R.forceCast(f14874a.get("lgap"), Integer.valueOf(86400))).intValue();
    }

    /* renamed from: l */
    public static boolean m16780l(Context context) {
        C4226a.m16783o(context);
        return 1 == ((Integer) C4275R.forceCast(f14874a.get("wi"), Integer.valueOf(1))).intValue();
    }

    /* renamed from: m */
    public static long m16781m(Context context) {
        C4226a.m16783o(context);
        return (((long) ((Integer) C4275R.forceCast(f14874a.get("adle"), Integer.valueOf(172800))).intValue()) * 1000) + C4226a.m16764a(context);
    }

    /* renamed from: o */
    private static synchronized void m16783o(Context context) {
        synchronized (C4226a.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (f14874a == null) {
                if (C4226a.m16784p(context)) {
                    f14875b = elapsedRealtime;
                }
            } else if (elapsedRealtime - f14875b >= ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD && C4226a.m16785q(context)) {
                f14875b = elapsedRealtime;
            }
        }
    }

    /* renamed from: p */
    private static boolean m16784p(Context context) {
        String r = C4226a.m16786r(context);
        if (TextUtils.isEmpty(r)) {
            C4226a.m16768b();
            return false;
        }
        C4226a.m16769b(r);
        C4250f.m16888d(context, new Hashon().fromHashMap(f14874a));
        return true;
    }

    /* renamed from: q */
    private static boolean m16785q(Context context) {
        String e = C4250f.m16889e(context);
        if (TextUtils.isEmpty(e)) {
            return C4226a.m16784p(context);
        }
        C4226a.m16769b(e);
        if (((Long) C4275R.forceCast(f14874a.get("timestamp"), Long.valueOf(0))).longValue() - f14876c >= 86400000) {
            C4226a.m16787s(context);
        }
        return true;
    }

    /* renamed from: r */
    private static String m16786r(Context context) {
        try {
            C4237b a = C4237b.m16835a(context);
            ArrayList a2 = a.m16837a();
            if (a2.isEmpty()) {
                return null;
            }
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", context);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("appkey", ((MobProduct) a2.get(0)).getProductAppkey()));
            arrayList.add(new KVPair("plat", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPlatformCode", new Object[0]))));
            arrayList.add(new KVPair("apppkg", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPackageName", new Object[0]))));
            arrayList.add(new KVPair("appver", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getAppVersionName", new Object[0]))));
            arrayList.add(new KVPair("networktype", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getDetailNetworkTypeForStatic", new Object[0]))));
            CharSequence a3 = new C4236a().m16833a(context);
            if (!TextUtils.isEmpty(a3)) {
                arrayList.add(new KVPair("duid", a3));
            }
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Identity", a.m16836a(a2)));
            String httpGet = a.httpGet(C4226a.m16788t(context), arrayList, arrayList2, networkTimeOut);
            Hashon hashon = new Hashon();
            HashMap fromJson = hashon.fromJson(httpGet);
            if (fromJson == null) {
                return null;
            }
            if ("200".equals(String.valueOf(fromJson.get("status")))) {
                String str = (String) C4275R.forceCast(fromJson.get("sr"));
                if (str != null) {
                    HashMap fromJson2 = hashon.fromJson(Data.AES128Decode("FYsAXMqlWJLCDpnc", Base64.decode(str, 2)));
                    if (fromJson2 != null) {
                        String str2;
                        int intValue;
                        HashMap hashMap = (HashMap) C4275R.forceCast(fromJson2.get("cdata"));
                        if (hashMap != null) {
                            str2 = (String) C4275R.forceCast(hashMap.get(C0846c.f2075f));
                            intValue = ((Integer) C4275R.forceCast(hashMap.get("httpport"), Integer.valueOf(0))).intValue();
                            str = (String) C4275R.forceCast(hashMap.get("path"));
                            if (str2 == null || intValue == 0 || str == null) {
                                C4250f.m16890e(context, null);
                            } else {
                                C4250f.m16890e(context, "http://" + str2 + ":" + intValue + str);
                            }
                        } else {
                            C4250f.m16890e(context, null);
                        }
                        hashMap = (HashMap) C4275R.forceCast(fromJson2.get("cconf"));
                        if (hashMap != null) {
                            str2 = (String) C4275R.forceCast(hashMap.get(C0846c.f2075f));
                            intValue = ((Integer) C4275R.forceCast(hashMap.get("httpport"), Integer.valueOf(0))).intValue();
                            str = (String) C4275R.forceCast(hashMap.get("path"));
                            if (str2 == null || intValue == 0 || str == null) {
                                C4250f.m16892f(context, null);
                            } else {
                                C4250f.m16892f(context, "http://" + str2 + ":" + intValue + str);
                            }
                        } else {
                            C4250f.m16892f(context, null);
                        }
                    }
                }
                str = (String) C4275R.forceCast(fromJson.get("sc"));
                if (str == null) {
                    throw new Throwable("response is illegal: " + httpGet);
                }
                HashMap fromJson3 = hashon.fromJson(Data.AES128Decode("FYsAXMqlWJLCDpnc", Base64.decode(str, 2)));
                if (fromJson3 == null) {
                    throw new Throwable("response is illegal: " + httpGet);
                }
                long longValue = ((Long) C4275R.forceCast(fromJson.get("timestamp"), Long.valueOf(0))).longValue();
                fromJson3.put("deviceTime", Long.valueOf(SystemClock.elapsedRealtime()));
                fromJson3.put("serverTime", Long.valueOf(longValue));
                return hashon.fromHashMap(fromJson3);
            }
            C4250f.m16890e(context, null);
            C4250f.m16892f(context, null);
            throw new Throwable("response is illegal: " + httpGet);
        } catch (Throwable th) {
            C4250f.m16890e(context, null);
            C4250f.m16892f(context, null);
            MobLog.getInstance().m16946w(th);
            return null;
        }
    }

    /* renamed from: b */
    private static void m16768b() {
        f14874a = new HashMap();
        f14874a.put("in", Integer.valueOf(0));
        f14874a.put("all", Integer.valueOf(0));
        f14874a.put("aspa", Long.valueOf(2592000));
        f14874a.put("un", Integer.valueOf(0));
        f14874a.put("rt", Integer.valueOf(0));
        f14874a.put("rtsr", Integer.valueOf(300000));
        f14874a.put("mi", Integer.valueOf(0));
        f14874a.put("ext", Integer.valueOf(0));
        f14874a.put("bs", Integer.valueOf(0));
        f14874a.put("bsgap", Integer.valueOf(86400));
        f14874a.put("di", Integer.valueOf(0));
        f14874a.put("l", Integer.valueOf(0));
        f14874a.put("lgap", Integer.valueOf(86400));
        f14874a.put("wi", Integer.valueOf(0));
        f14874a.put("adle", Integer.valueOf(172800));
    }

    /* renamed from: b */
    private static void m16769b(String str) {
        try {
            f14874a = new Hashon().fromJson(str);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
    }

    /* renamed from: s */
    private static void m16787s(final Context context) {
        if (!f14877d) {
            f14877d = true;
            new Thread() {
                public void run() {
                    String n = C4226a.m16786r(context);
                    if (!TextUtils.isEmpty(n)) {
                        C4226a.m16769b(n);
                        C4250f.m16888d(context, new Hashon().fromHashMap(C4226a.f14874a));
                    }
                    C4226a.f14877d = false;
                }
            }.start();
        }
    }

    /* renamed from: t */
    private static String m16788t(Context context) {
        String str = null;
        try {
            str = C4250f.m16893g(context);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
        return TextUtils.isEmpty(str) ? "http://m.data.mob.com/v2/cconf" : str;
    }
}
