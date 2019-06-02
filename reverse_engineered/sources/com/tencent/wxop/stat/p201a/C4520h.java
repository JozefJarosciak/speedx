package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.alipay.sdk.sys.C0869a;
import com.tencent.wxop.stat.C4525a;
import com.tencent.wxop.stat.StatAppMonitor;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.h */
public class C4520h extends C4513e {
    /* renamed from: m */
    private static String f15929m = null;
    /* renamed from: n */
    private static String f15930n = null;
    /* renamed from: a */
    private StatAppMonitor f15931a = null;

    public C4520h(Context context, int i, StatAppMonitor statAppMonitor, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15931a = statAppMonitor.clone();
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.MONITOR_STAT;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        if (this.f15931a == null) {
            return false;
        }
        jSONObject.put("na", this.f15931a.getInterfaceName());
        jSONObject.put("rq", this.f15931a.getReqSize());
        jSONObject.put("rp", this.f15931a.getRespSize());
        jSONObject.put("rt", this.f15931a.getResultType());
        jSONObject.put("tm", this.f15931a.getMillisecondsConsume());
        jSONObject.put("rc", this.f15931a.getReturnCode());
        jSONObject.put("sp", this.f15931a.getSampling());
        if (f15930n == null) {
            f15930n = C4539k.m18072n(this.l);
        }
        C4545q.m18100a(jSONObject, C0869a.f2170k, f15930n);
        if (f15929m == null) {
            f15929m = C4539k.m18067i(this.l);
        }
        C4545q.m18100a(jSONObject, "op", f15929m);
        jSONObject.put("cn", C4525a.m17934a(this.l).m17943b());
        return true;
    }
}
