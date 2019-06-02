package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.alipay.sdk.sys.C0869a;
import com.tencent.p191a.p192a.p193a.p194a.C4400h;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.au;
import com.tencent.wxop.stat.common.C4529a;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.e */
public abstract class C4513e {
    /* renamed from: j */
    protected static String f15895j = null;
    /* renamed from: a */
    private StatSpecifyReportedInfo f15896a = null;
    /* renamed from: b */
    protected String f15897b = null;
    /* renamed from: c */
    protected long f15898c;
    /* renamed from: d */
    protected int f15899d;
    /* renamed from: e */
    protected C4529a f15900e = null;
    /* renamed from: f */
    protected int f15901f;
    /* renamed from: g */
    protected String f15902g = null;
    /* renamed from: h */
    protected String f15903h = null;
    /* renamed from: i */
    protected String f15904i = null;
    /* renamed from: k */
    protected boolean f15905k = false;
    /* renamed from: l */
    protected Context f15906l;

    C4513e(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15906l = context;
        this.f15898c = System.currentTimeMillis() / 1000;
        this.f15899d = i;
        this.f15903h = StatConfig.getInstallChannel(context);
        this.f15904i = C4539k.m18068j(context);
        this.f15897b = StatConfig.getAppKey(context);
        if (statSpecifyReportedInfo != null) {
            this.f15896a = statSpecifyReportedInfo;
            if (C4539k.m18056c(statSpecifyReportedInfo.getAppKey())) {
                this.f15897b = statSpecifyReportedInfo.getAppKey();
            }
            if (C4539k.m18056c(statSpecifyReportedInfo.getInstallChannel())) {
                this.f15903h = statSpecifyReportedInfo.getInstallChannel();
            }
            if (C4539k.m18056c(statSpecifyReportedInfo.getVersion())) {
                this.f15904i = statSpecifyReportedInfo.getVersion();
            }
            this.f15905k = statSpecifyReportedInfo.isImportant();
        }
        this.f15902g = StatConfig.getCustomUserId(context);
        this.f15900e = au.m17968a(context).m18001b(context);
        if (mo6117a() != C4518f.NETWORK_DETECTOR) {
            this.f15901f = C4539k.m18077s(context).intValue();
        } else {
            this.f15901f = -C4518f.NETWORK_DETECTOR.m17920a();
        }
        if (!C4400h.m17243c(f15895j)) {
            String localMidOnly = StatConfig.getLocalMidOnly(context);
            f15895j = localMidOnly;
            if (!C4539k.m18056c(localMidOnly)) {
                f15895j = "0";
            }
        }
    }

    /* renamed from: a */
    public abstract C4518f mo6117a();

    /* renamed from: a */
    public abstract boolean mo6118a(JSONObject jSONObject);

    /* renamed from: b */
    public boolean m17904b(JSONObject jSONObject) {
        boolean z = false;
        try {
            C4545q.m18100a(jSONObject, "ky", this.f15897b);
            jSONObject.put("et", mo6117a().m17920a());
            if (this.f15900e != null) {
                jSONObject.put("ui", this.f15900e.m18017b());
                C4545q.m18100a(jSONObject, "mc", this.f15900e.m18018c());
                int d = this.f15900e.m18019d();
                jSONObject.put("ut", d);
                if (d == 0 && C4539k.m18081w(this.f15906l) == 1) {
                    jSONObject.put("ia", 1);
                }
            }
            C4545q.m18100a(jSONObject, "cui", this.f15902g);
            if (mo6117a() != C4518f.SESSION_ENV) {
                C4545q.m18100a(jSONObject, C0869a.f2170k, this.f15904i);
                C4545q.m18100a(jSONObject, "ch", this.f15903h);
            }
            if (this.f15905k) {
                jSONObject.put("impt", 1);
            }
            C4545q.m18100a(jSONObject, "mid", f15895j);
            jSONObject.put("idx", this.f15901f);
            jSONObject.put("si", this.f15899d);
            jSONObject.put("ts", this.f15898c);
            jSONObject.put("dts", C4539k.m18041a(this.f15906l, false));
            z = mo6118a(jSONObject);
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: c */
    public long m17905c() {
        return this.f15898c;
    }

    /* renamed from: d */
    public StatSpecifyReportedInfo m17906d() {
        return this.f15896a;
    }

    /* renamed from: e */
    public Context m17907e() {
        return this.f15906l;
    }

    /* renamed from: f */
    public boolean m17908f() {
        return this.f15905k;
    }

    /* renamed from: g */
    public String m17909g() {
        try {
            JSONObject jSONObject = new JSONObject();
            m17904b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
