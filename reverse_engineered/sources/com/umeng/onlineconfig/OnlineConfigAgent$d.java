package com.umeng.onlineconfig;

import com.umeng.onlineconfig.proguard.C4765e;
import org.json.JSONObject;

public class OnlineConfigAgent$d extends C4765e {
    /* renamed from: a */
    public long f16696a = -1;
    /* renamed from: b */
    public long f16697b = -1;

    public OnlineConfigAgent$d(JSONObject jSONObject) {
        super(jSONObject);
        m18727a(jSONObject);
    }

    /* renamed from: a */
    private void m18727a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.f16696a = jSONObject.optLong("last_config_time", -1);
                this.f16697b = (jSONObject.optLong("oc_interval", -1) * 60) * 1000;
            } catch (Exception e) {
                OnlineConfigLog.m18737w(C4766a.f16698a, "fail to parce online config response", e);
            }
        }
    }
}
