package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.C4525a;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.j */
public class C4522j extends C4513e {
    /* renamed from: a */
    private static String f15933a = null;
    /* renamed from: m */
    private String f15934m = null;
    /* renamed from: n */
    private String f15935n = null;

    public C4522j(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15934m = C4525a.m17934a(context).m17943b();
        if (f15933a == null) {
            f15933a = C4539k.m18067i(context);
        }
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.NETWORK_MONITOR;
    }

    /* renamed from: a */
    public void m17928a(String str) {
        this.f15935n = str;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        C4545q.m18100a(jSONObject, "op", f15933a);
        C4545q.m18100a(jSONObject, "cn", this.f15934m);
        jSONObject.put("sp", this.f15935n);
        return true;
    }
}
