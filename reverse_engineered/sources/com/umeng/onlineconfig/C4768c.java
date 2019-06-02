package com.umeng.onlineconfig;

import com.umeng.onlineconfig.proguard.C4765e;
import java.util.Locale;
import org.json.JSONObject;

/* compiled from: OnlineConfigResponse */
/* renamed from: com.umeng.onlineconfig.c */
public class C4768c extends C4765e {
    /* renamed from: a */
    public JSONObject f16708a = null;
    /* renamed from: b */
    boolean f16709b = false;
    /* renamed from: c */
    int f16710c = -1;
    /* renamed from: d */
    int f16711d = -1;
    /* renamed from: e */
    private final String f16712e = "config_update";
    /* renamed from: f */
    private final String f16713f = "report_policy";
    /* renamed from: g */
    private final String f16714g = "online_params";
    /* renamed from: h */
    private final String f16715h = "report_interval";

    public C4768c(JSONObject jSONObject) {
        super(jSONObject);
        if (jSONObject != null) {
            m18740a(jSONObject);
            m18739a();
        }
    }

    /* renamed from: a */
    private void m18740a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("config_update") && !jSONObject.getString("config_update").toLowerCase(Locale.US).equals("no")) {
                if (jSONObject.has("report_policy")) {
                    this.f16710c = jSONObject.getInt("report_policy");
                    this.f16711d = jSONObject.optInt("report_interval") * 1000;
                } else {
                    OnlineConfigLog.m18736w(C4766a.f16698a, " online config fetch no report policy");
                }
                this.f16708a = jSONObject.optJSONObject("online_params");
                this.f16709b = true;
            }
        } catch (Exception e) {
            OnlineConfigLog.m18737w(C4766a.f16698a, "fail to parce online config response", e);
        }
    }

    /* renamed from: a */
    private void m18739a() {
        if (!C4767b.m18738a(this.f16710c)) {
            this.f16710c = 1;
        }
    }
}
