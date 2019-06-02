package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.k */
public class C4523k extends C4513e {
    /* renamed from: a */
    Long f15936a = null;
    /* renamed from: m */
    String f15937m;
    /* renamed from: n */
    String f15938n;

    public C4523k(Context context, String str, String str2, int i, Long l, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15938n = str;
        this.f15937m = str2;
        this.f15936a = l;
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.PAGE_VIEW;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        C4545q.m18100a(jSONObject, "pi", this.f15937m);
        C4545q.m18100a(jSONObject, "rf", this.f15938n);
        if (this.f15936a != null) {
            jSONObject.put("du", this.f15936a);
        }
        return true;
    }
}
