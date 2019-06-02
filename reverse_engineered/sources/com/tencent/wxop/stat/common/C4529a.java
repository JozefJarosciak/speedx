package com.tencent.wxop.stat.common;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.a */
public class C4529a {
    /* renamed from: a */
    private String f16041a = null;
    /* renamed from: b */
    private String f16042b = null;
    /* renamed from: c */
    private String f16043c = null;
    /* renamed from: d */
    private String f16044d = "0";
    /* renamed from: e */
    private int f16045e;
    /* renamed from: f */
    private int f16046f = 0;
    /* renamed from: g */
    private long f16047g = 0;

    public C4529a(String str, String str2, int i) {
        this.f16041a = str;
        this.f16042b = str2;
        this.f16045e = i;
    }

    /* renamed from: a */
    JSONObject m18015a() {
        JSONObject jSONObject = new JSONObject();
        try {
            C4545q.m18100a(jSONObject, "ui", this.f16041a);
            C4545q.m18100a(jSONObject, "mc", this.f16042b);
            C4545q.m18100a(jSONObject, "mid", this.f16044d);
            C4545q.m18100a(jSONObject, "aid", this.f16043c);
            jSONObject.put("ts", this.f16047g);
            jSONObject.put("ver", this.f16046f);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void m18016a(int i) {
        this.f16045e = i;
    }

    /* renamed from: b */
    public String m18017b() {
        return this.f16041a;
    }

    /* renamed from: c */
    public String m18018c() {
        return this.f16042b;
    }

    /* renamed from: d */
    public int m18019d() {
        return this.f16045e;
    }

    public String toString() {
        return m18015a().toString();
    }
}
