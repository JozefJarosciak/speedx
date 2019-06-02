package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.lbsapi.auth.c */
class C1021c {
    /* renamed from: a */
    private Context f2275a;
    /* renamed from: b */
    private HashMap<String, String> f2276b = null;
    /* renamed from: c */
    private C1020a<String> f2277c = null;

    /* renamed from: com.baidu.lbsapi.auth.c$a */
    interface C1020a<Result> {
        /* renamed from: a */
        void mo2594a(Result result);
    }

    protected C1021c(Context context) {
        this.f2275a = context;
    }

    /* renamed from: a */
    private HashMap<String, String> m3605a(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            String str2 = str2.toString();
            hashMap2.put(str2, hashMap.get(str2));
        }
        return hashMap2;
    }

    /* renamed from: a */
    private void m3607a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
        } catch (JSONException e) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("status", -1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (this.f2277c != null) {
            this.f2277c.mo2594a(jSONObject != null ? jSONObject.toString() : new JSONObject().toString());
        }
    }

    /* renamed from: a */
    protected void m3609a(HashMap<String, String> hashMap, C1020a<String> c1020a) {
        this.f2276b = m3605a((HashMap) hashMap);
        this.f2277c = c1020a;
        new Thread(new C1022d(this)).start();
    }
}
