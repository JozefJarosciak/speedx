package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.avos.avoscloud.AVUser.AVThirdPartyUserAuth;
import com.avos.avoscloud.AnalyticsEvent;
import com.tencent.wxop.stat.StatAccount;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.a */
public class C4514a extends C4513e {
    /* renamed from: a */
    private StatAccount f15907a = null;

    public C4514a(Context context, int i, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15907a = statAccount;
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.ADDITION;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        C4545q.m18100a(jSONObject, AVThirdPartyUserAuth.SNS_TENCENT_WEIBO, this.f15907a.getAccount());
        jSONObject.put(AnalyticsEvent.accTag, this.f15907a.toJsonString());
        return true;
    }
}
