package com.tencent.wxop.stat;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import com.tencent.wxop.stat.common.C4530b;
import com.tencent.wxop.stat.common.C4533e;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4544p;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.p201a.C4514a;
import com.tencent.wxop.stat.p201a.C4516c;
import com.tencent.wxop.stat.p201a.C4521i;
import com.tencent.wxop.stat.p201a.C4524l;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONObject;

public class StatServiceImpl {
    /* renamed from: a */
    static volatile int f15870a = 0;
    /* renamed from: b */
    static volatile long f15871b = 0;
    /* renamed from: c */
    static volatile long f15872c = 0;
    /* renamed from: d */
    private static C4533e f15873d;
    /* renamed from: e */
    private static volatile Map<C4516c, Long> f15874e = new ConcurrentHashMap();
    /* renamed from: f */
    private static volatile Map<String, Properties> f15875f = new ConcurrentHashMap();
    /* renamed from: g */
    private static volatile Map<Integer, Integer> f15876g = new ConcurrentHashMap(10);
    /* renamed from: h */
    private static volatile long f15877h = 0;
    /* renamed from: i */
    private static volatile long f15878i = 0;
    /* renamed from: j */
    private static volatile long f15879j = 0;
    /* renamed from: k */
    private static String f15880k = "";
    /* renamed from: l */
    private static volatile int f15881l = 0;
    /* renamed from: m */
    private static volatile String f15882m = "";
    /* renamed from: n */
    private static volatile String f15883n = "";
    /* renamed from: o */
    private static Map<String, Long> f15884o = new ConcurrentHashMap();
    /* renamed from: p */
    private static Map<String, Long> f15885p = new ConcurrentHashMap();
    /* renamed from: q */
    private static StatLogger f15886q = C4539k.m18052b();
    /* renamed from: r */
    private static UncaughtExceptionHandler f15887r = null;
    /* renamed from: s */
    private static volatile boolean f15888s = true;
    /* renamed from: t */
    private static Context f15889t = null;

    /* renamed from: a */
    static int m17874a(Context context, boolean z, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        int i = 1;
        long currentTimeMillis = System.currentTimeMillis();
        if (!z || currentTimeMillis - f15878i < ((long) StatConfig.getSessionTimoutMillis())) {
            boolean z2 = false;
        } else {
            int i2 = 1;
        }
        f15878i = currentTimeMillis;
        if (f15879j == 0) {
            f15879j = C4539k.m18054c();
        }
        if (currentTimeMillis >= f15879j) {
            f15879j = C4539k.m18054c();
            if (au.m17968a(context).m18001b(context).m18019d() != 1) {
                au.m17968a(context).m18001b(context).m18016a(1);
            }
            StatConfig.m17869b(0);
            f15870a = 0;
            f15880k = C4539k.m18043a(0);
            i2 = 1;
        }
        Object obj = f15880k;
        if (C4539k.m18049a(statSpecifyReportedInfo)) {
            obj = statSpecifyReportedInfo.getAppKey() + f15880k;
        }
        if (f15885p.containsKey(obj)) {
            i = i2;
        }
        if (i != 0) {
            if (C4539k.m18049a(statSpecifyReportedInfo)) {
                m17879a(context, statSpecifyReportedInfo);
            } else if (StatConfig.m17872c() < StatConfig.getMaxDaySessionNumbers()) {
                C4539k.m18082x(context);
                m17879a(context, null);
            } else {
                f15886q.m18010e((Object) "Exceed StatConfig.getMaxDaySessionNumbers().");
            }
            f15885p.put(obj, Long.valueOf(1));
        }
        if (f15888s) {
            testSpeed(context);
            f15888s = false;
        }
        return f15881l;
    }

    /* renamed from: a */
    static synchronized void m17877a(Context context) {
        synchronized (StatServiceImpl.class) {
            if (context != null) {
                if (f15873d == null && m17886b(context)) {
                    Context applicationContext = context.getApplicationContext();
                    f15889t = applicationContext;
                    f15873d = new C4533e();
                    f15880k = C4539k.m18043a(0);
                    f15877h = System.currentTimeMillis() + StatConfig.f15847i;
                    f15873d.m18024a(new C4554l(applicationContext));
                }
            }
        }
    }

    /* renamed from: a */
    static void m17879a(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (m17887c(context) != null) {
            if (StatConfig.isDebugEnable()) {
                f15886q.m18009d("start new session.");
            }
            if (statSpecifyReportedInfo == null || f15881l == 0) {
                f15881l = C4539k.m18040a();
            }
            StatConfig.m17858a(0);
            StatConfig.m17868b();
            new aq(new C4524l(context, f15881l, m17884b(), statSpecifyReportedInfo)).m17959a();
        }
    }

    /* renamed from: a */
    static void m17880a(Context context, Throwable th) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.reportSdkSelfException() can not be null!");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new C4559q(context2, th));
            }
        }
    }

    /* renamed from: a */
    static boolean m17881a() {
        if (f15870a < 2) {
            return false;
        }
        f15871b = System.currentTimeMillis();
        return true;
    }

    /* renamed from: a */
    static boolean m17882a(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: b */
    static JSONObject m17884b() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (StatConfig.f15840b.f16125d != 0) {
                jSONObject2.put("v", StatConfig.f15840b.f16125d);
            }
            jSONObject.put(Integer.toString(StatConfig.f15840b.f16122a), jSONObject2);
            jSONObject2 = new JSONObject();
            if (StatConfig.f15839a.f16125d != 0) {
                jSONObject2.put("v", StatConfig.f15839a.f16125d);
            }
            jSONObject.put(Integer.toString(StatConfig.f15839a.f16122a), jSONObject2);
        } catch (Throwable e) {
            f15886q.m18011e(e);
        }
        return jSONObject;
    }

    /* renamed from: b */
    private static void m17885b(Context context, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        try {
            new aq(new C4514a(context, m17874a(context, false, statSpecifyReportedInfo), statAccount, statSpecifyReportedInfo)).m17959a();
        } catch (Throwable th) {
            f15886q.m18011e(th);
            m17880a(context, th);
        }
    }

    /* renamed from: b */
    static boolean m17886b(Context context) {
        boolean z = false;
        long a = C4544p.m18091a(context, StatConfig.f15841c, 0);
        long b = C4539k.m18051b(StatConstants.VERSION);
        boolean z2 = true;
        if (b <= a) {
            f15886q.error("MTA is disable for current version:" + b + ",wakeup version:" + a);
            z2 = false;
        }
        a = C4544p.m18091a(context, StatConfig.f15842d, 0);
        if (a > System.currentTimeMillis()) {
            f15886q.error("MTA is disable for current time:" + System.currentTimeMillis() + ",wakeup time:" + a);
        } else {
            z = z2;
        }
        StatConfig.setEnableStatService(z);
        return z;
    }

    /* renamed from: c */
    static C4533e m17887c(Context context) {
        if (f15873d == null) {
            synchronized (StatServiceImpl.class) {
                if (f15873d == null) {
                    try {
                        m17877a(context);
                    } catch (Throwable th) {
                        f15886q.error(th);
                        StatConfig.setEnableStatService(false);
                    }
                }
            }
        }
        return f15873d;
    }

    /* renamed from: c */
    static void m17889c() {
        f15870a = 0;
        f15871b = 0;
    }

    public static void commitEvents(Context context, int i) {
        if (StatConfig.isEnableStatService()) {
            if (StatConfig.isDebugEnable()) {
                f15886q.m18012i("commitEvents, maxNumber=" + i);
            }
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.commitEvents() can not be null!");
            } else if (i < -1 || i == 0) {
                f15886q.error((Object) "The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
            } else if (C4525a.m17934a(f15889t).m17947f() && m17887c(context2) != null) {
                f15873d.m18024a(new ad(context2, i));
            }
        }
    }

    /* renamed from: d */
    static void m17890d() {
        f15870a++;
        f15871b = System.currentTimeMillis();
        flushDataToDB(f15889t);
    }

    /* renamed from: d */
    static void m17891d(Context context) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.sendNetworkDetector() can not be null!");
                return;
            }
            try {
                C4551i.m18122b(context2).m18123a(new C4521i(context2), new C4562t());
            } catch (Throwable th) {
                f15886q.m18011e(th);
            }
        }
    }

    /* renamed from: e */
    static void m17893e(Context context) {
        f15872c = System.currentTimeMillis() + ((long) (CoreConstants.MILLIS_IN_ONE_MINUTE * StatConfig.getSendPeriodMinutes()));
        C4544p.m18095b(context, "last_period_ts", f15872c);
        commitEvents(context, -1);
    }

    public static void flushDataToDB(Context context) {
        if (StatConfig.isEnableStatService() && StatConfig.f15851m > 0) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.testSpeed() can not be null!");
            } else {
                au.m17968a(context2).m18002c();
            }
        }
    }

    public static Properties getCommonKeyValueForKVEvent(String str) {
        return (Properties) f15875f.get(str);
    }

    public static Context getContext(Context context) {
        return context != null ? context : f15889t;
    }

    public static void onLowMemory(Context context) {
        if (StatConfig.isEnableStatService() && m17887c(getContext(context)) != null) {
            f15873d.m18024a(new C4557o(context));
        }
    }

    public static void onPause(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService() && m17887c(context) != null) {
            f15873d.m18024a(new C4555m(context, statSpecifyReportedInfo));
        }
    }

    public static void onResume(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService() && m17887c(context) != null) {
            f15873d.m18024a(new aj(context, statSpecifyReportedInfo));
        }
    }

    public static void onStop(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new C4556n(context2));
            }
        }
    }

    public static void reportAccount(Context context, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.m18010e((Object) "context is null in reportAccount.");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new al(statAccount, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void reportAppMonitorStat(Context context, StatAppMonitor statAppMonitor, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.reportAppMonitorStat() can not be null!");
            } else if (statAppMonitor == null) {
                f15886q.error((Object) "The StatAppMonitor of StatService.reportAppMonitorStat() can not be null!");
            } else if (statAppMonitor.getInterfaceName() == null) {
                f15886q.error((Object) "The interfaceName of StatAppMonitor on StatService.reportAppMonitorStat() can not be null!");
            } else {
                StatAppMonitor clone = statAppMonitor.clone();
                if (m17887c(context2) != null) {
                    f15873d.m18024a(new aa(context2, statSpecifyReportedInfo, clone));
                }
            }
        }
    }

    public static void reportError(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.reportError() can not be null!");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new C4558p(str, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void reportException(Context context, Throwable th, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.reportException() can not be null!");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new C4560r(th, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void reportGameUser(Context context, StatGameUser statGameUser, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.reportGameUser() can not be null!");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new am(statGameUser, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void reportQQ(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "context is null in reportQQ()");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new ak(str, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void setCommonKeyValueForKVEvent(String str, Properties properties) {
        if (!C4539k.m18056c(str)) {
            f15886q.m18010e((Object) "event_id or commonProp for setCommonKeyValueForKVEvent is invalid.");
        } else if (properties == null || properties.size() <= 0) {
            f15875f.remove(str);
        } else {
            f15875f.put(str, (Properties) properties.clone());
        }
    }

    public static void setContext(Context context) {
        if (context != null) {
            f15889t = context.getApplicationContext();
        }
    }

    public static void setEnvAttributes(Context context, Map<String, String> map) {
        if (map == null || map.size() > 512) {
            f15886q.error((Object) "The map in setEnvAttributes can't be null or its size can't exceed 512.");
            return;
        }
        try {
            C4530b.m18021a(context, (Map) map);
        } catch (Throwable e) {
            f15886q.m18011e(e);
        }
    }

    public static void startNewSession(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.startNewSession() can not be null!");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new ai(context2, statSpecifyReportedInfo));
            }
        }
    }

    public static boolean startStatService(Context context, String str, String str2, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        try {
            if (StatConfig.isEnableStatService()) {
                String str3 = StatConstants.VERSION;
                if (StatConfig.isDebugEnable()) {
                    f15886q.m18009d("MTA SDK version, current: " + str3 + " ,required: " + str2);
                }
                if (context == null || str2 == null) {
                    f15886q.error((Object) "Context or mtaSdkVersion in StatService.startStatService() is null, please check it!");
                    StatConfig.setEnableStatService(false);
                    return false;
                } else if (C4539k.m18051b(str3) < C4539k.m18051b(str2)) {
                    f15886q.error(("MTA SDK version conflicted, current: " + str3 + ",required: " + str2) + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/");
                    StatConfig.setEnableStatService(false);
                    return false;
                } else {
                    str3 = StatConfig.getInstallChannel(context);
                    if (str3 == null || str3.length() == 0) {
                        StatConfig.setInstallChannel(HelpFormatter.DEFAULT_OPT_PREFIX);
                    }
                    if (str != null) {
                        StatConfig.setAppKey(context, str);
                    }
                    if (m17887c(context) != null) {
                        f15873d.m18024a(new an(context, statSpecifyReportedInfo));
                    }
                    return true;
                }
            }
            f15886q.error((Object) "MTA StatService is disable.");
            return false;
        } catch (Throwable th) {
            f15886q.m18011e(th);
            return false;
        }
    }

    public static void stopSession() {
        f15878i = 0;
    }

    public static void testSpeed(Context context) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.testSpeed() can not be null!");
            } else if (m17887c(context2) != null) {
                f15873d.m18024a(new ae(context2));
            }
        }
    }

    public static void testSpeed(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.testSpeed() can not be null!");
            } else if (map == null || map.size() == 0) {
                f15886q.error((Object) "The domainMap of StatService.testSpeed() can not be null or empty!");
            } else {
                Map hashMap = new HashMap(map);
                if (m17887c(context2) != null) {
                    f15873d.m18024a(new af(context2, hashMap, statSpecifyReportedInfo));
                }
            }
        }
    }

    public static void trackBeginPage(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null || str == null || str.length() == 0) {
                f15886q.error((Object) "The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new C4565w(str2, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void trackCustomBeginEvent(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo, String... strArr) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomBeginEvent() can not be null!");
                return;
            }
            C4516c c4516c = new C4516c(str, strArr, null);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new C4564v(str, c4516c, context2));
            }
        }
    }

    public static void trackCustomBeginKVEvent(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomBeginEvent() can not be null!");
                return;
            }
            C4516c c4516c = new C4516c(str, null, properties);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new C4567y(str, c4516c, context2));
            }
        }
    }

    public static void trackCustomEndEvent(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo, String... strArr) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
                return;
            }
            C4516c c4516c = new C4516c(str, strArr, null);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new C4566x(str, c4516c, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void trackCustomEndKVEvent(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
                return;
            }
            C4516c c4516c = new C4516c(str, null, properties);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new C4568z(str, c4516c, context2, statSpecifyReportedInfo));
            }
        }
    }

    public static void trackCustomEvent(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo, String... strArr) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
            } else if (m17882a(str)) {
                f15886q.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                C4516c c4516c = new C4516c(str, strArr, null);
                if (m17887c(context2) != null) {
                    f15873d.m18024a(new C4561s(context2, statSpecifyReportedInfo, c4516c));
                }
            }
        }
    }

    public static void trackCustomKVEvent(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
            } else if (m17882a(str)) {
                f15886q.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                C4516c c4516c = new C4516c(str, null, properties);
                if (m17887c(context2) != null) {
                    f15873d.m18024a(new C4563u(context2, statSpecifyReportedInfo, c4516c));
                }
            }
        }
    }

    public static void trackCustomKVTimeIntervalEvent(Context context, String str, Properties properties, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null) {
                f15886q.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
            } else if (m17882a(str)) {
                f15886q.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
            } else {
                C4516c c4516c = new C4516c(str, null, properties);
                if (m17887c(context2) != null) {
                    f15873d.m18024a(new ac(context2, statSpecifyReportedInfo, c4516c, i));
                }
            }
        }
    }

    public static void trackCustomTimeIntervalEvent(Context context, int i, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (i <= 0) {
            f15886q.error((Object) "The intervalSecond of StatService.trackCustomTimeIntervalEvent() can must bigger than 0!");
            return;
        }
        Context context2 = getContext(context);
        if (context2 == null) {
            f15886q.error((Object) "The Context of StatService.trackCustomTimeIntervalEvent() can not be null!");
        } else if (m17887c(context2) != null) {
            f15873d.m18024a(new ab());
        }
    }

    public static void trackEndPage(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.isEnableStatService()) {
            Context context2 = getContext(context);
            if (context2 == null || str == null || str.length() == 0) {
                f15886q.error((Object) "The Context or pageName of StatService.trackEndPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (m17887c(context2) != null) {
                f15873d.m18024a(new ah(context2, str2, statSpecifyReportedInfo));
            }
        }
    }
}
