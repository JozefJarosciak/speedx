package com.tencent.wxop.stat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.sys.C0869a;
import com.tencent.p191a.p192a.p193a.p194a.C4399g;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4544p;
import com.tencent.wxop.stat.common.C4545q;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import java.net.URI;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class StatConfig {
    /* renamed from: A */
    private static String f15820A = null;
    /* renamed from: B */
    private static String f15821B;
    /* renamed from: C */
    private static String f15822C;
    /* renamed from: D */
    private static String f15823D = "mta_channel";
    /* renamed from: E */
    private static int f15824E = Opcodes.GETFIELD;
    /* renamed from: F */
    private static int f15825F = 1024;
    /* renamed from: G */
    private static long f15826G = 0;
    /* renamed from: H */
    private static long f15827H = 300000;
    /* renamed from: I */
    private static volatile String f15828I = StatConstants.MTA_REPORT_FULL_URL;
    /* renamed from: J */
    private static int f15829J = 0;
    /* renamed from: K */
    private static volatile int f15830K = 0;
    /* renamed from: L */
    private static int f15831L = 20;
    /* renamed from: M */
    private static int f15832M = 0;
    /* renamed from: N */
    private static boolean f15833N = false;
    /* renamed from: O */
    private static int f15834O = 4096;
    /* renamed from: P */
    private static boolean f15835P = false;
    /* renamed from: Q */
    private static String f15836Q = null;
    /* renamed from: R */
    private static boolean f15837R = false;
    /* renamed from: S */
    private static C4550g f15838S = null;
    /* renamed from: a */
    static C4549f f15839a = new C4549f(2);
    /* renamed from: b */
    static C4549f f15840b = new C4549f(1);
    /* renamed from: c */
    static String f15841c = "__HIBERNATE__";
    /* renamed from: d */
    static String f15842d = "__HIBERNATE__TIME";
    /* renamed from: e */
    static String f15843e = "__MTA_KILL__";
    /* renamed from: f */
    static String f15844f = "";
    /* renamed from: g */
    static boolean f15845g = false;
    /* renamed from: h */
    static int f15846h = 100;
    /* renamed from: i */
    static long f15847i = AbstractComponentTracker.LINGERING_TIMEOUT;
    public static boolean isAutoExceptionCaught = true;
    /* renamed from: j */
    static boolean f15848j = true;
    /* renamed from: k */
    static volatile String f15849k = StatConstants.MTA_SERVER;
    /* renamed from: l */
    static boolean f15850l = true;
    /* renamed from: m */
    static int f15851m = 0;
    /* renamed from: n */
    static long f15852n = AbstractComponentTracker.LINGERING_TIMEOUT;
    /* renamed from: o */
    static int f15853o = 512;
    /* renamed from: p */
    private static StatLogger f15854p = C4539k.m18052b();
    /* renamed from: q */
    private static StatReportStrategy f15855q = StatReportStrategy.APP_LAUNCH;
    /* renamed from: r */
    private static boolean f15856r = false;
    /* renamed from: s */
    private static boolean f15857s = true;
    /* renamed from: t */
    private static int f15858t = 30000;
    /* renamed from: u */
    private static int f15859u = 100000;
    /* renamed from: v */
    private static int f15860v = 30;
    /* renamed from: w */
    private static int f15861w = 10;
    /* renamed from: x */
    private static int f15862x = 100;
    /* renamed from: y */
    private static int f15863y = 30;
    /* renamed from: z */
    private static int f15864z = 1;

    /* renamed from: a */
    static int m17855a() {
        return f15860v;
    }

    /* renamed from: a */
    static String m17856a(Context context) {
        return C4545q.m18098a(C4544p.m18093a(context, "_mta_ky_tag_", null));
    }

    /* renamed from: a */
    static String m17857a(String str, String str2) {
        try {
            String string = f15840b.f16123b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            f15854p.m18014w("can't find custom key:" + str);
            return str2;
        }
    }

    /* renamed from: a */
    static synchronized void m17858a(int i) {
        synchronized (StatConfig.class) {
            f15830K = i;
        }
    }

    /* renamed from: a */
    static void m17859a(long j) {
        C4544p.m18095b(C4551i.m18119a(), f15841c, j);
        setEnableStatService(false);
        f15854p.warn("MTA is disable for current SDK version");
    }

    /* renamed from: a */
    static void m17860a(Context context, C4549f c4549f) {
        if (c4549f.f16122a == f15840b.f16122a) {
            f15840b = c4549f;
            m17864a(c4549f.f16123b);
            if (!f15840b.f16123b.isNull("iplist")) {
                C4525a.m17934a(context).m17942a(f15840b.f16123b.getString("iplist"));
            }
        } else if (c4549f.f16122a == f15839a.f16122a) {
            f15839a = c4549f;
        }
    }

    /* renamed from: a */
    static void m17861a(Context context, C4549f c4549f, JSONObject jSONObject) {
        Object obj = null;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase("v")) {
                    int i = jSONObject.getInt(str);
                    Object obj2 = c4549f.f16125d != i ? 1 : obj;
                    c4549f.f16125d = i;
                    obj = obj2;
                } else if (str.equalsIgnoreCase("c")) {
                    str = jSONObject.getString("c");
                    if (str.length() > 0) {
                        c4549f.f16123b = new JSONObject(str);
                    }
                } else if (str.equalsIgnoreCase(ANSIConstants.ESC_END)) {
                    c4549f.f16124c = jSONObject.getString(ANSIConstants.ESC_END);
                }
            }
            if (obj == 1) {
                au a = au.m17968a(C4551i.m18119a());
                if (a != null) {
                    a.m17998a(c4549f);
                }
                if (c4549f.f16122a == f15840b.f16122a) {
                    m17864a(c4549f.f16123b);
                    m17871b(c4549f.f16123b);
                }
            }
            m17860a(context, c4549f);
        } catch (Throwable e) {
            f15854p.m18011e(e);
        } catch (Throwable e2) {
            f15854p.m18011e(e2);
        }
    }

    /* renamed from: a */
    static void m17862a(Context context, String str) {
        if (str != null) {
            C4544p.m18096b(context, "_mta_ky_tag_", C4545q.m18103b(str));
        }
    }

    /* renamed from: a */
    static void m17863a(Context context, JSONObject jSONObject) {
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(Integer.toString(f15840b.f16122a))) {
                    m17861a(context, f15840b, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase(Integer.toString(f15839a.f16122a))) {
                    m17861a(context, f15839a, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase("rs")) {
                    StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt(str));
                    if (statReportStrategy != null) {
                        f15855q = statReportStrategy;
                        if (isDebugEnable()) {
                            f15854p.m18009d("Change to ReportStrategy:" + statReportStrategy.name());
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Throwable e) {
            f15854p.m18011e(e);
        }
    }

    /* renamed from: a */
    static void m17864a(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                setStatSendStrategy(statReportStrategy);
            }
        } catch (JSONException e) {
            if (isDebugEnable()) {
                f15854p.m18012i("rs not found.");
            }
        }
    }

    /* renamed from: a */
    static boolean m17865a(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    /* renamed from: a */
    private static boolean m17866a(String str) {
        if (str == null) {
            return false;
        }
        if (f15821B == null) {
            f15821B = str;
            return true;
        } else if (f15821B.contains(str)) {
            return false;
        } else {
            f15821B += "|" + str;
            return true;
        }
    }

    /* renamed from: a */
    static boolean m17867a(JSONObject jSONObject, String str, String str2) {
        if (!jSONObject.isNull(str)) {
            String optString = jSONObject.optString(str);
            if (C4539k.m18056c(str2) && C4539k.m18056c(optString) && str2.equalsIgnoreCase(optString)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    static void m17868b() {
        f15832M++;
    }

    /* renamed from: b */
    static void m17869b(int i) {
        if (i >= 0) {
            f15832M = i;
        }
    }

    /* renamed from: b */
    static void m17870b(Context context, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString(f15843e);
            if (C4539k.m18056c(optString)) {
                JSONObject jSONObject2 = new JSONObject(optString);
                if (jSONObject2.length() != 0) {
                    Object obj;
                    if (!jSONObject2.isNull("sm")) {
                        obj = jSONObject2.get("sm");
                        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : obj instanceof String ? Integer.valueOf((String) obj).intValue() : 0;
                        if (intValue > 0) {
                            if (isDebugEnable()) {
                                f15854p.m18012i("match sleepTime:" + intValue + " minutes");
                            }
                            C4544p.m18095b(context, f15842d, System.currentTimeMillis() + ((long) ((intValue * 60) * 1000)));
                            setEnableStatService(false);
                            f15854p.warn("MTA is disable for current SDK version");
                        }
                    }
                    if (m17867a(jSONObject2, C0869a.f2167h, StatConstants.VERSION)) {
                        f15854p.m18012i("match sdk version:2.0.3");
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (m17867a(jSONObject2, "md", Build.MODEL)) {
                        f15854p.m18012i("match MODEL:" + Build.MODEL);
                        obj = 1;
                    }
                    if (m17867a(jSONObject2, C0869a.f2170k, C4539k.m18068j(context))) {
                        f15854p.m18012i("match app version:" + C4539k.m18068j(context));
                        obj = 1;
                    }
                    if (m17867a(jSONObject2, "mf", Build.MANUFACTURER)) {
                        f15854p.m18012i("match MANUFACTURER:" + Build.MANUFACTURER);
                        obj = 1;
                    }
                    if (m17867a(jSONObject2, "osv", VERSION.SDK_INT)) {
                        f15854p.m18012i("match android SDK version:" + VERSION.SDK_INT);
                        obj = 1;
                    }
                    if (m17867a(jSONObject2, "ov", VERSION.SDK_INT)) {
                        f15854p.m18012i("match android SDK version:" + VERSION.SDK_INT);
                        obj = 1;
                    }
                    if (m17867a(jSONObject2, "ui", au.m17968a(context).m18001b(context).m18017b())) {
                        f15854p.m18012i("match imei:" + au.m17968a(context).m18001b(context).m18017b());
                        obj = 1;
                    }
                    if (m17867a(jSONObject2, "mid", getLocalMidOnly(context))) {
                        f15854p.m18012i("match mid:" + getLocalMidOnly(context));
                        obj = 1;
                    }
                    if (obj != null) {
                        m17859a(C4539k.m18051b(StatConstants.VERSION));
                    }
                }
            }
        } catch (Throwable e) {
            f15854p.m18011e(e);
        }
    }

    /* renamed from: b */
    static void m17871b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                m17870b(C4551i.m18119a(), jSONObject);
                String string = jSONObject.getString(f15841c);
                if (isDebugEnable()) {
                    f15854p.m18009d("hibernateVer:" + string + ", current version:2.0.3");
                }
                long b = C4539k.m18051b(string);
                if (C4539k.m18051b(StatConstants.VERSION) <= b) {
                    m17859a(b);
                }
            } catch (JSONException e) {
                f15854p.m18009d("__HIBERNATE__ not found.");
            }
        }
    }

    /* renamed from: c */
    static int m17872c() {
        return f15832M;
    }

    public static synchronized String getAppKey(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (f15821B != null) {
                str = f15821B;
            } else {
                if (context != null) {
                    if (f15821B == null) {
                        f15821B = C4539k.m18062f(context);
                    }
                }
                if (f15821B == null || f15821B.trim().length() == 0) {
                    f15854p.error((Object) "AppKey can not be null or empty, please read Developer's Guide first!");
                }
                str = f15821B;
            }
        }
        return str;
    }

    public static int getCurSessionStatReportCount() {
        return f15830K;
    }

    public static C4550g getCustomLogger() {
        return f15838S;
    }

    public static String getCustomProperty(String str) {
        try {
            return f15839a.f16123b.getString(str);
        } catch (Throwable th) {
            f15854p.m18011e(th);
            return null;
        }
    }

    public static String getCustomProperty(String str, String str2) {
        try {
            String string = f15839a.f16123b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            f15854p.m18011e(th);
            return str2;
        }
    }

    public static String getCustomUserId(Context context) {
        if (context == null) {
            f15854p.error((Object) "Context for getCustomUid is null.");
            return null;
        }
        if (f15836Q == null) {
            f15836Q = C4544p.m18093a(context, "MTA_CUSTOM_UID", "");
        }
        return f15836Q;
    }

    public static long getFlushDBSpaceMS() {
        return f15852n;
    }

    public static synchronized String getInstallChannel(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (f15822C != null) {
                str = f15822C;
            } else {
                str = C4544p.m18093a(context, f15823D, "");
                f15822C = str;
                if (str == null || f15822C.trim().length() == 0) {
                    f15822C = C4539k.m18063g(context);
                }
                if (f15822C == null || f15822C.trim().length() == 0) {
                    f15854p.m18014w("installChannel can not be null or empty, please read Developer's Guide first!");
                }
                str = f15822C;
            }
        }
        return str;
    }

    public static String getLocalMidOnly(Context context) {
        return context != null ? C4399g.m17233E(context).m17236p().m17226a() : "0";
    }

    public static int getMaxBatchReportCount() {
        return f15863y;
    }

    public static int getMaxDaySessionNumbers() {
        return f15831L;
    }

    public static int getMaxImportantDataSendRetryCount() {
        return f15862x;
    }

    public static int getMaxParallelTimmingEvents() {
        return f15825F;
    }

    public static int getMaxReportEventLength() {
        return f15834O;
    }

    public static int getMaxSendRetryCount() {
        return f15861w;
    }

    public static int getMaxSessionStatReportCount() {
        return f15829J;
    }

    public static int getMaxStoreEventCount() {
        return f15859u;
    }

    public static String getMid(Context context) {
        return getLocalMidOnly(context);
    }

    public static long getMsPeriodForMethodsCalledLimitClear() {
        return f15847i;
    }

    public static int getNumEventsCachedInMemory() {
        return f15851m;
    }

    public static int getNumEventsCommitPerSec() {
        return f15864z;
    }

    public static int getNumOfMethodsCalledLimit() {
        return f15846h;
    }

    public static String getQQ(Context context) {
        return C4544p.m18093a(context, "mta.acc.qq", f15844f);
    }

    public static int getReportCompressedSize() {
        return f15853o;
    }

    public static int getSendPeriodMinutes() {
        return f15824E;
    }

    public static int getSessionTimoutMillis() {
        return f15858t;
    }

    public static String getStatReportHost() {
        return f15849k;
    }

    public static String getStatReportUrl() {
        return f15828I;
    }

    public static StatReportStrategy getStatSendStrategy() {
        return f15855q;
    }

    public static boolean isAutoExceptionCaught() {
        return isAutoExceptionCaught;
    }

    public static boolean isDebugEnable() {
        return f15856r;
    }

    public static boolean isEnableConcurrentProcess() {
        return f15835P;
    }

    public static boolean isEnableSmartReporting() {
        return f15848j;
    }

    public static boolean isEnableStatService() {
        return f15857s;
    }

    public static boolean isReportEventsByOrder() {
        return f15850l;
    }

    public static boolean isXGProMode() {
        return f15837R;
    }

    public static void setAppKey(Context context, String str) {
        if (context == null) {
            f15854p.error((Object) "ctx in StatConfig.setAppKey() is null");
        } else if (str == null || str.length() > 256) {
            f15854p.error((Object) "appkey in StatConfig.setAppKey() is null or exceed 256 bytes");
        } else {
            if (f15821B == null) {
                f15821B = m17856a(context);
            }
            if ((m17866a(str) | m17866a(C4539k.m18062f(context))) != 0) {
                m17862a(context, f15821B);
            }
        }
    }

    public static void setAppKey(String str) {
        if (str == null) {
            f15854p.error((Object) "appkey in StatConfig.setAppKey() is null");
        } else if (str.length() > 256) {
            f15854p.error((Object) "The length of appkey cann't exceed 256 bytes.");
        } else {
            f15821B = str;
        }
    }

    public static void setAutoExceptionCaught(boolean z) {
        isAutoExceptionCaught = z;
    }

    public static void setCustomLogger(C4550g c4550g) {
        f15838S = c4550g;
    }

    public static void setCustomUserId(Context context, String str) {
        if (context == null) {
            f15854p.error((Object) "Context for setCustomUid is null.");
            return;
        }
        C4544p.m18096b(context, "MTA_CUSTOM_UID", str);
        f15836Q = str;
    }

    public static void setDebugEnable(boolean z) {
        f15856r = z;
        C4539k.m18052b().setDebugEnable(z);
    }

    public static void setEnableConcurrentProcess(boolean z) {
        f15835P = z;
    }

    public static void setEnableSmartReporting(boolean z) {
        f15848j = z;
    }

    public static void setEnableStatService(boolean z) {
        f15857s = z;
        if (!z) {
            f15854p.warn("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    public static void setFlushDBSpaceMS(long j) {
        if (j > 0) {
            f15852n = j;
        }
    }

    public static void setInstallChannel(Context context, String str) {
        if (str.length() > 128) {
            f15854p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
            return;
        }
        f15822C = str;
        C4544p.m18096b(context, f15823D, str);
    }

    public static void setInstallChannel(String str) {
        if (str.length() > 128) {
            f15854p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            f15822C = str;
        }
    }

    public static void setMaxBatchReportCount(int i) {
        if (m17865a(i, 2, 1000)) {
            f15863y = i;
        } else {
            f15854p.error((Object) "setMaxBatchReportCount can not exceed the range of [2,1000].");
        }
    }

    public static void setMaxDaySessionNumbers(int i) {
        if (i <= 0) {
            f15854p.m18010e((Object) "maxDaySessionNumbers must be greater than 0.");
        } else {
            f15831L = i;
        }
    }

    public static void setMaxImportantDataSendRetryCount(int i) {
        if (i > 100) {
            f15862x = i;
        }
    }

    public static void setMaxParallelTimmingEvents(int i) {
        if (m17865a(i, 1, 4096)) {
            f15825F = i;
        } else {
            f15854p.error((Object) "setMaxParallelTimmingEvents can not exceed the range of [1, 4096].");
        }
    }

    public static void setMaxReportEventLength(int i) {
        if (i <= 0) {
            f15854p.error((Object) "maxReportEventLength on setMaxReportEventLength() must greater than 0.");
        } else {
            f15834O = i;
        }
    }

    public static void setMaxSendRetryCount(int i) {
        if (m17865a(i, 1, 1000)) {
            f15861w = i;
        } else {
            f15854p.error((Object) "setMaxSendRetryCount can not exceed the range of [1,1000].");
        }
    }

    public static void setMaxSessionStatReportCount(int i) {
        if (i < 0) {
            f15854p.error((Object) "maxSessionStatReportCount cannot be less than 0.");
        } else {
            f15829J = i;
        }
    }

    public static void setMaxStoreEventCount(int i) {
        if (m17865a(i, 0, 500000)) {
            f15859u = i;
        } else {
            f15854p.error((Object) "setMaxStoreEventCount can not exceed the range of [0, 500000].");
        }
    }

    public static void setNumEventsCachedInMemory(int i) {
        if (i >= 0) {
            f15851m = i;
        }
    }

    public static void setNumEventsCommitPerSec(int i) {
        if (i > 0) {
            f15864z = i;
        }
    }

    public static void setNumOfMethodsCalledLimit(int i, long j) {
        f15846h = i;
        if (j >= 1000) {
            f15847i = j;
        }
    }

    public static void setQQ(Context context, String str) {
        C4544p.m18096b(context, "mta.acc.qq", str);
        f15844f = str;
    }

    public static void setReportCompressedSize(int i) {
        if (i > 0) {
            f15853o = i;
        }
    }

    public static void setReportEventsByOrder(boolean z) {
        f15850l = z;
    }

    public static void setSendPeriodMinutes(int i) {
        if (m17865a(i, 1, 10080)) {
            f15824E = i;
        } else {
            f15854p.error((Object) "setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        }
    }

    public static void setSessionTimoutMillis(int i) {
        if (m17865a(i, 1000, (int) CoreConstants.MILLIS_IN_ONE_DAY)) {
            f15858t = i;
        } else {
            f15854p.error((Object) "setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
        }
    }

    public static void setStatReportUrl(String str) {
        if (str == null || str.length() == 0) {
            f15854p.error((Object) "statReportUrl cannot be null or empty.");
            return;
        }
        f15828I = str;
        try {
            f15849k = new URI(f15828I).getHost();
        } catch (Exception e) {
            f15854p.m18014w(e);
        }
        if (isDebugEnable()) {
            f15854p.m18012i("url:" + f15828I + ", domain:" + f15849k);
        }
    }

    public static void setStatSendStrategy(StatReportStrategy statReportStrategy) {
        f15855q = statReportStrategy;
        if (statReportStrategy != StatReportStrategy.PERIOD) {
            StatServiceImpl.f15872c = 0;
        }
        if (isDebugEnable()) {
            f15854p.m18009d("Change to statSendStrategy: " + statReportStrategy);
        }
    }

    public static void setXGProMode(boolean z) {
        f15837R = z;
    }
}
