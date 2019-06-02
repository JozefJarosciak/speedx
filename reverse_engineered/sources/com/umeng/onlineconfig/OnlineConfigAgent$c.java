package com.umeng.onlineconfig;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.onlineconfig.proguard.C4763d;
import com.umeng.onlineconfig.proguard.C4777g;
import org.json.JSONObject;

class OnlineConfigAgent$c extends C4763d {
    /* renamed from: a */
    final /* synthetic */ OnlineConfigAgent f16693a;
    /* renamed from: e */
    private final String f16694e;
    /* renamed from: f */
    private JSONObject f16695f;

    public OnlineConfigAgent$c(OnlineConfigAgent onlineConfigAgent, Context context) {
        this.f16693a = onlineConfigAgent;
        super(null);
        this.f16694e = "http://oc.umeng.com/v2/get_update_time";
        this.d = "http://oc.umeng.com/v2/get_update_time";
        this.f16695f = m18724a(context);
    }

    /* renamed from: a */
    public JSONObject mo6182a() {
        return this.f16695f;
    }

    /* renamed from: a */
    private JSONObject m18724a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appkey", TextUtils.isEmpty(OnlineConfigAgent.a(this.f16693a)) ? C4777g.m18746a(context) : OnlineConfigAgent.a(this.f16693a));
            jSONObject.put(OnlineConfigAgent.KEY_VERSION_CODE, C4777g.m18748b(context));
            return jSONObject;
        } catch (Exception e) {
            OnlineConfigLog.m18730e(C4766a.f16698a, "exception in onlineConfigInternal");
            return null;
        }
    }

    /* renamed from: b */
    public String mo6183b() {
        return this.d;
    }
}
