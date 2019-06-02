package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4530b;
import com.tencent.wxop.stat.common.C4539k;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.l */
public class C4524l extends C4513e {
    /* renamed from: a */
    private C4530b f15939a;
    /* renamed from: m */
    private JSONObject f15940m = null;

    public C4524l(Context context, int i, JSONObject jSONObject, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15939a = new C4530b(context);
        this.f15940m = jSONObject;
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.SESSION_ENV;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        if (this.f15900e != null) {
            jSONObject.put("ut", this.f15900e.m18019d());
        }
        if (this.f15940m != null) {
            jSONObject.put("cfg", this.f15940m);
        }
        if (C4539k.m18083y(this.l)) {
            jSONObject.put("ncts", 1);
        }
        this.f15939a.m18022a(jSONObject, null);
        return true;
    }
}
