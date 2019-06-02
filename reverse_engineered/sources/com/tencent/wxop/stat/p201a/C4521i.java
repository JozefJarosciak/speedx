package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.i */
public class C4521i extends C4513e {
    /* renamed from: a */
    public static final StatSpecifyReportedInfo f15932a;

    static {
        StatSpecifyReportedInfo statSpecifyReportedInfo = new StatSpecifyReportedInfo();
        f15932a = statSpecifyReportedInfo;
        statSpecifyReportedInfo.setAppKey("A9VH9B8L4GX4");
    }

    public C4521i(Context context) {
        super(context, 0, f15932a);
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.NETWORK_DETECTOR;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        C4545q.m18100a(jSONObject, "actky", StatConfig.getAppKey(this.l));
        return true;
    }
}
