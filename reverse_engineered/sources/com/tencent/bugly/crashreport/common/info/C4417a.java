package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.support.v4.os.EnvironmentCompat;
import com.alipay.sdk.util.C0880h;
import com.tencent.bugly.C4402b;
import com.tencent.bugly.crashreport.C4404a;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.common.info.a */
public final class C4417a {
    /* renamed from: Z */
    private static C4417a f15260Z = null;
    /* renamed from: A */
    public HashMap<String, String> f15261A = new HashMap();
    /* renamed from: B */
    public List<String> f15262B = new ArrayList();
    /* renamed from: C */
    public C4404a f15263C = null;
    /* renamed from: D */
    private final Context f15264D;
    /* renamed from: E */
    private String f15265E;
    /* renamed from: F */
    private String f15266F;
    /* renamed from: G */
    private String f15267G = EnvironmentCompat.MEDIA_UNKNOWN;
    /* renamed from: H */
    private String f15268H = EnvironmentCompat.MEDIA_UNKNOWN;
    /* renamed from: I */
    private String f15269I = "";
    /* renamed from: J */
    private String f15270J = null;
    /* renamed from: K */
    private String f15271K = null;
    /* renamed from: L */
    private String f15272L = null;
    /* renamed from: M */
    private String f15273M = null;
    /* renamed from: N */
    private long f15274N = -1;
    /* renamed from: O */
    private long f15275O = -1;
    /* renamed from: P */
    private long f15276P = -1;
    /* renamed from: Q */
    private String f15277Q = null;
    /* renamed from: R */
    private String f15278R = null;
    /* renamed from: S */
    private Map<String, PlugInBean> f15279S = null;
    /* renamed from: T */
    private boolean f15280T = true;
    /* renamed from: U */
    private String f15281U = null;
    /* renamed from: V */
    private String f15282V = null;
    /* renamed from: W */
    private Boolean f15283W = null;
    /* renamed from: X */
    private String f15284X = null;
    /* renamed from: Y */
    private Map<String, PlugInBean> f15285Y = null;
    /* renamed from: a */
    public final long f15286a = System.currentTimeMillis();
    private int aa = -1;
    private int ab = -1;
    private Map<String, String> ac = new HashMap();
    private Map<String, String> ad = new HashMap();
    private Map<String, String> ae = new HashMap();
    private boolean af;
    private String ag = null;
    private String ah = null;
    private String ai = null;
    private String aj = null;
    private String ak = null;
    private final Object al = new Object();
    private final Object am = new Object();
    private final Object an = new Object();
    private final Object ao = new Object();
    private final Object ap = new Object();
    private final Object aq = new Object();
    private final Object ar = new Object();
    /* renamed from: b */
    public final byte f15287b;
    /* renamed from: c */
    public String f15288c;
    /* renamed from: d */
    public final String f15289d;
    /* renamed from: e */
    public boolean f15290e = true;
    /* renamed from: f */
    public final String f15291f;
    /* renamed from: g */
    public final String f15292g;
    /* renamed from: h */
    public final String f15293h;
    /* renamed from: i */
    public long f15294i;
    /* renamed from: j */
    public String f15295j = null;
    /* renamed from: k */
    public String f15296k = null;
    /* renamed from: l */
    public String f15297l = null;
    /* renamed from: m */
    public String f15298m = null;
    /* renamed from: n */
    public String f15299n = null;
    /* renamed from: o */
    public List<String> f15300o = null;
    /* renamed from: p */
    public String f15301p = EnvironmentCompat.MEDIA_UNKNOWN;
    /* renamed from: q */
    public long f15302q = 0;
    /* renamed from: r */
    public long f15303r = 0;
    /* renamed from: s */
    public long f15304s = 0;
    /* renamed from: t */
    public long f15305t = 0;
    /* renamed from: u */
    public boolean f15306u = false;
    /* renamed from: v */
    public String f15307v = null;
    /* renamed from: w */
    public String f15308w = null;
    /* renamed from: x */
    public String f15309x = null;
    /* renamed from: y */
    public boolean f15310y = false;
    /* renamed from: z */
    public boolean f15311z = false;

    private C4417a(Context context) {
        this.f15264D = C4479y.m17772a(context);
        this.f15287b = (byte) 1;
        PackageInfo b = AppInfo.m17297b(context);
        if (b != null) {
            try {
                this.f15295j = b.versionName;
                this.f15307v = this.f15295j;
                this.f15308w = Integer.toString(b.versionCode);
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.f15288c = AppInfo.m17293a(context);
        this.f15289d = AppInfo.m17292a(Process.myPid());
        this.f15291f = C4418b.m17379k();
        this.f15292g = C4418b.m17358a();
        this.f15296k = AppInfo.m17298c(context);
        this.f15293h = "Android " + C4418b.m17361b() + ",level " + C4418b.m17363c();
        this.f15292g + C0880h.f2220b + this.f15293h;
        Map d = AppInfo.m17299d(context);
        if (d != null) {
            try {
                this.f15300o = AppInfo.m17295a(d);
                String str = (String) d.get("BUGLY_APPID");
                if (str != null) {
                    this.f15282V = str;
                }
                str = (String) d.get("BUGLY_APP_VERSION");
                if (str != null) {
                    this.f15295j = str;
                }
                str = (String) d.get("BUGLY_APP_CHANNEL");
                if (str != null) {
                    this.f15297l = str;
                }
                str = (String) d.get("BUGLY_ENABLE_DEBUG");
                if (str != null) {
                    this.f15306u = str.equalsIgnoreCase("true");
                }
                str = (String) d.get("com.tencent.rdm.uuid");
                if (str != null) {
                    this.f15309x = str;
                }
            } catch (Throwable th2) {
                if (!C4475w.m17748a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.f15311z = true;
                C4475w.m17751c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th22) {
            if (C4402b.f15204c) {
                th22.printStackTrace();
            }
        }
        C4475w.m17751c("com info create end", new Object[0]);
    }

    /* renamed from: a */
    public final boolean m17325a() {
        return this.af;
    }

    /* renamed from: a */
    public final void m17324a(boolean z) {
        this.af = z;
        if (this.f15263C != null) {
            this.f15263C.setNativeIsAppForeground(z);
        }
    }

    /* renamed from: a */
    public static synchronized C4417a m17303a(Context context) {
        C4417a c4417a;
        synchronized (C4417a.class) {
            if (f15260Z == null) {
                f15260Z = new C4417a(context);
            }
            c4417a = f15260Z;
        }
        return c4417a;
    }

    /* renamed from: b */
    public static synchronized C4417a m17304b() {
        C4417a c4417a;
        synchronized (C4417a.class) {
            c4417a = f15260Z;
        }
        return c4417a;
    }

    /* renamed from: c */
    public static String m17305c() {
        return "2.4.0";
    }

    /* renamed from: d */
    public final void m17331d() {
        synchronized (this.al) {
            this.f15265E = UUID.randomUUID().toString();
        }
    }

    /* renamed from: e */
    public final String m17333e() {
        if (this.f15265E == null) {
            synchronized (this.al) {
                if (this.f15265E == null) {
                    this.f15265E = UUID.randomUUID().toString();
                }
            }
        }
        return this.f15265E;
    }

    /* renamed from: f */
    public final String m17335f() {
        if (C4479y.m17792a(null)) {
            return this.f15282V;
        }
        return null;
    }

    /* renamed from: a */
    public final void m17322a(String str) {
        this.f15282V = str;
    }

    /* renamed from: g */
    public final String m17337g() {
        String str;
        synchronized (this.aq) {
            str = this.f15267G;
        }
        return str;
    }

    /* renamed from: b */
    public final void m17327b(String str) {
        synchronized (this.aq) {
            if (str == null) {
                str = "10000";
            }
            this.f15267G = str;
        }
    }

    /* renamed from: h */
    public final String m17339h() {
        if (this.f15266F != null) {
            return this.f15266F;
        }
        this.f15266F = m17342k() + "|" + m17344m() + "|" + m17345n();
        return this.f15266F;
    }

    /* renamed from: c */
    public final void m17329c(String str) {
        this.f15266F = str;
    }

    /* renamed from: i */
    public final synchronized String m17340i() {
        return this.f15268H;
    }

    /* renamed from: d */
    public final synchronized void m17332d(String str) {
        this.f15268H = str;
    }

    /* renamed from: j */
    public final synchronized String m17341j() {
        return this.f15269I;
    }

    /* renamed from: e */
    public final synchronized void m17334e(String str) {
        this.f15269I = str;
    }

    /* renamed from: k */
    public final String m17342k() {
        if (!this.f15280T) {
            return "";
        }
        if (this.f15270J == null) {
            this.f15270J = C4418b.m17359a(this.f15264D);
        }
        return this.f15270J;
    }

    /* renamed from: l */
    public final String m17343l() {
        if (!this.f15280T) {
            return "";
        }
        if (this.f15271K == null) {
            this.f15271K = C4418b.m17366d(this.f15264D);
        }
        return this.f15271K;
    }

    /* renamed from: m */
    public final String m17344m() {
        if (!this.f15280T) {
            return "";
        }
        if (this.f15272L == null) {
            this.f15272L = C4418b.m17362b(this.f15264D);
        }
        return this.f15272L;
    }

    /* renamed from: n */
    public final String m17345n() {
        if (!this.f15280T) {
            return "";
        }
        if (this.f15273M == null) {
            this.f15273M = C4418b.m17364c(this.f15264D);
        }
        return this.f15273M;
    }

    /* renamed from: o */
    public final long m17346o() {
        if (this.f15274N <= 0) {
            this.f15274N = C4418b.m17365d();
        }
        return this.f15274N;
    }

    /* renamed from: p */
    public final long m17347p() {
        if (this.f15275O <= 0) {
            this.f15275O = C4418b.m17369f();
        }
        return this.f15275O;
    }

    /* renamed from: q */
    public final long m17348q() {
        if (this.f15276P <= 0) {
            this.f15276P = C4418b.m17373h();
        }
        return this.f15276P;
    }

    /* renamed from: r */
    public final String m17349r() {
        if (this.f15277Q == null) {
            this.f15277Q = C4418b.m17360a(true);
        }
        return this.f15277Q;
    }

    /* renamed from: s */
    public final String m17350s() {
        if (this.f15278R == null) {
            this.f15278R = C4418b.m17372g(this.f15264D);
        }
        return this.f15278R;
    }

    /* renamed from: a */
    public final void m17323a(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (this.am) {
                this.f15261A.put(str, str2);
            }
        }
    }

    /* renamed from: t */
    public final String m17351t() {
        try {
            Map all = this.f15264D.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.am) {
                    for (Entry entry : all.entrySet()) {
                        try {
                            this.f15261A.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            C4475w.m17748a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C4475w.m17748a(th2);
        }
        if (this.f15261A.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry2 : this.f15261A.entrySet()) {
            stringBuilder.append("[");
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append(",");
            stringBuilder.append((String) entry2.getValue());
            stringBuilder.append("] ");
        }
        m17330c("SDK_INFO", stringBuilder.toString());
        return stringBuilder.toString();
    }

    /* renamed from: u */
    public final String m17352u() {
        if (this.ak == null) {
            this.ak = AppInfo.m17300e(this.f15264D);
        }
        return this.ak;
    }

    /* renamed from: v */
    public final synchronized Map<String, PlugInBean> m17353v() {
        return null;
    }

    /* renamed from: w */
    public final String m17354w() {
        if (this.f15281U == null) {
            this.f15281U = C4418b.m17377j();
        }
        return this.f15281U;
    }

    /* renamed from: x */
    public final Boolean m17355x() {
        if (this.f15283W == null) {
            this.f15283W = Boolean.valueOf(C4418b.m17374h(this.f15264D));
        }
        return this.f15283W;
    }

    /* renamed from: y */
    public final String m17356y() {
        if (this.f15284X == null) {
            this.f15284X = C4418b.m17370f(this.f15264D);
            C4475w.m17747a("rom:%s", this.f15284X);
        }
        return this.f15284X;
    }

    /* renamed from: z */
    public final Map<String, String> m17357z() {
        Map<String, String> map;
        synchronized (this.an) {
            if (this.ac.size() <= 0) {
                map = null;
            } else {
                map = new HashMap(this.ac);
            }
        }
        return map;
    }

    /* renamed from: f */
    public final String m17336f(String str) {
        if (C4479y.m17792a(str)) {
            C4475w.m17752d("key should not be empty %s", str);
            return null;
        }
        String str2;
        synchronized (this.an) {
            str2 = (String) this.ac.remove(str);
        }
        return str2;
    }

    /* renamed from: A */
    public final void m17306A() {
        synchronized (this.an) {
            this.ac.clear();
        }
    }

    /* renamed from: g */
    public final String m17338g(String str) {
        if (C4479y.m17792a(str)) {
            C4475w.m17752d("key should not be empty %s", str);
            return null;
        }
        String str2;
        synchronized (this.an) {
            str2 = (String) this.ac.get(str);
        }
        return str2;
    }

    /* renamed from: b */
    public final void m17328b(String str, String str2) {
        if (C4479y.m17792a(str) || C4479y.m17792a(str2)) {
            C4475w.m17752d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.an) {
            this.ac.put(str, str2);
        }
    }

    /* renamed from: B */
    public final int m17307B() {
        int size;
        synchronized (this.an) {
            size = this.ac.size();
        }
        return size;
    }

    /* renamed from: C */
    public final Set<String> m17308C() {
        Set<String> keySet;
        synchronized (this.an) {
            keySet = this.ac.keySet();
        }
        return keySet;
    }

    /* renamed from: D */
    public final Map<String, String> m17309D() {
        Map<String, String> map;
        synchronized (this.ar) {
            if (this.ad.size() <= 0) {
                map = null;
            } else {
                map = new HashMap(this.ad);
            }
        }
        return map;
    }

    /* renamed from: c */
    public final void m17330c(String str, String str2) {
        if (C4479y.m17792a(str) || C4479y.m17792a(str2)) {
            C4475w.m17752d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.ao) {
            this.ae.put(str, str2);
        }
    }

    /* renamed from: E */
    public final Map<String, String> m17310E() {
        Map<String, String> map;
        synchronized (this.ao) {
            if (this.ae.size() <= 0) {
                map = null;
            } else {
                map = new HashMap(this.ae);
            }
        }
        return map;
    }

    /* renamed from: a */
    public final void m17321a(int i) {
        synchronized (this.ap) {
            if (this.aa != i) {
                this.aa = i;
                C4475w.m17747a("user scene tag %d changed to tag %d", Integer.valueOf(r0), Integer.valueOf(this.aa));
            }
        }
    }

    /* renamed from: F */
    public final int m17311F() {
        int i;
        synchronized (this.ap) {
            i = this.aa;
        }
        return i;
    }

    /* renamed from: b */
    public final void m17326b(int i) {
        if (this.ab != 24096) {
            this.ab = 24096;
            C4475w.m17747a("server scene tag %d changed to tag %d", Integer.valueOf(r0), Integer.valueOf(this.ab));
        }
    }

    /* renamed from: G */
    public final int m17312G() {
        return this.ab;
    }

    /* renamed from: H */
    public final boolean m17313H() {
        return AppInfo.m17301f(this.f15264D);
    }

    /* renamed from: I */
    public final synchronized Map<String, PlugInBean> m17314I() {
        return null;
    }

    /* renamed from: J */
    public static int m17302J() {
        return C4418b.m17363c();
    }

    /* renamed from: K */
    public final String m17315K() {
        if (this.ag == null) {
            this.ag = C4418b.m17381l();
        }
        return this.ag;
    }

    /* renamed from: L */
    public final String m17316L() {
        if (this.ah == null) {
            this.ah = C4418b.m17376i(this.f15264D);
        }
        return this.ah;
    }

    /* renamed from: M */
    public final String m17317M() {
        if (this.ai == null) {
            this.ai = C4418b.m17378j(this.f15264D);
        }
        return this.ai;
    }

    /* renamed from: N */
    public final String m17318N() {
        Context context = this.f15264D;
        return C4418b.m17382m();
    }

    /* renamed from: O */
    public final String m17319O() {
        if (this.aj == null) {
            this.aj = C4418b.m17380k(this.f15264D);
        }
        return this.aj;
    }

    /* renamed from: P */
    public final long m17320P() {
        Context context = this.f15264D;
        return C4418b.m17383n();
    }
}
