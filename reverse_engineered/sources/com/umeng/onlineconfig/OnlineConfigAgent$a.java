package com.umeng.onlineconfig;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.onlineconfig.proguard.C4763d;
import com.umeng.onlineconfig.proguard.C4777g;
import com.umeng.onlineconfig.proguard.C4778h;
import org.json.JSONObject;

class OnlineConfigAgent$a extends C4763d {
    /* renamed from: a */
    final /* synthetic */ OnlineConfigAgent f16686a;
    /* renamed from: e */
    private final String f16687e;
    /* renamed from: f */
    private JSONObject f16688f;

    public OnlineConfigAgent$a(OnlineConfigAgent onlineConfigAgent, Context context) {
        this.f16686a = onlineConfigAgent;
        super(null);
        this.f16687e = "http://oc.umeng.com/v2/check_config_update";
        this.d = "http://oc.umeng.com/v2/check_config_update";
        this.f16688f = m18710a(context);
    }

    /* renamed from: a */
    public JSONObject mo6182a() {
        return this.f16688f;
    }

    /* renamed from: b */
    public String mo6183b() {
        return this.d;
    }

    /* renamed from: a */
    private JSONObject m18710a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "online_config");
            jSONObject.put("appkey", TextUtils.isEmpty(OnlineConfigAgent.a(this.f16686a)) ? C4777g.m18746a(context) : OnlineConfigAgent.a(this.f16686a));
            jSONObject.put(OnlineConfigAgent.KEY_VERSION_CODE, C4777g.m18748b(context));
            jSONObject.put(OnlineConfigAgent.KEY_PACKAGE, C4777g.m18752f(context));
            jSONObject.put(OnlineConfigAgent.KEY_SDK_VERSION, C4777g.m18745a());
            jSONObject.put(OnlineConfigAgent.KEY_ID, C4778h.m18755b(C4777g.m18750d(context)));
            jSONObject.put(OnlineConfigAgent.KEY_CHANNEL, TextUtils.isEmpty(OnlineConfigAgent.b(this.f16686a)) ? C4777g.m18749c(context) : OnlineConfigAgent.b(this.f16686a));
            jSONObject.put("last_config_time", OnlineConfigAgent.a(this.f16686a, context));
            return jSONObject;
        } catch (Exception e) {
            OnlineConfigLog.m18730e(C4766a.f16698a, "exception in onlineConfigInternal");
            return null;
        }
    }
}
