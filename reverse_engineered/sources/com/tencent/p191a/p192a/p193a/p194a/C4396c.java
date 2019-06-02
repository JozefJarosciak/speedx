package com.tencent.p191a.p192a.p193a.p194a;

import android.util.Log;
import org.json.JSONObject;

/* renamed from: com.tencent.a.a.a.a.c */
public final class C4396c {
    /* renamed from: T */
    long f15186T = 0;
    /* renamed from: a */
    String f15187a = null;
    /* renamed from: b */
    String f15188b = null;
    /* renamed from: c */
    String f15189c = "0";

    /* renamed from: e */
    static C4396c m17224e(String str) {
        C4396c c4396c = new C4396c();
        if (C4400h.m17242b(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull("ui")) {
                    c4396c.f15187a = jSONObject.getString("ui");
                }
                if (!jSONObject.isNull("mc")) {
                    c4396c.f15188b = jSONObject.getString("mc");
                }
                if (!jSONObject.isNull("mid")) {
                    c4396c.f15189c = jSONObject.getString("mid");
                }
                if (!jSONObject.isNull("ts")) {
                    c4396c.f15186T = jSONObject.getLong("ts");
                }
            } catch (Throwable e) {
                Log.w("MID", e);
            }
        }
        return c4396c;
    }

    /* renamed from: n */
    private JSONObject m17225n() {
        JSONObject jSONObject = new JSONObject();
        try {
            C4400h.m17239a(jSONObject, "ui", this.f15187a);
            C4400h.m17239a(jSONObject, "mc", this.f15188b);
            C4400h.m17239a(jSONObject, "mid", this.f15189c);
            jSONObject.put("ts", this.f15186T);
        } catch (Throwable e) {
            Log.w("MID", e);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public final String m17226a() {
        return this.f15189c;
    }

    public final String toString() {
        return m17225n().toString();
    }
}
