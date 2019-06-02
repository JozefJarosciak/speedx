package com.qiniu.android.p110b;

import ch.qos.logback.core.joran.action.Action;
import com.qiniu.android.p189c.C4339e;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpToken */
/* renamed from: com.qiniu.android.b.i */
public final class C4327i {
    /* renamed from: a */
    public final String f15084a;
    /* renamed from: b */
    private String f15085b = null;

    private C4327i(JSONObject jSONObject, String str) {
        this.f15085b = jSONObject.optString("returnUrl");
        this.f15084a = str;
    }

    /* renamed from: a */
    public static C4327i m17107a(String str) {
        try {
            String[] split = str.split(":");
            if (split.length != 3) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(C4339e.m17131b(split[2])));
                if (jSONObject.optString(Action.SCOPE_ATTRIBUTE).equals("") || jSONObject.optInt("deadline") == 0) {
                    return null;
                }
                return new C4327i(jSONObject, str);
            } catch (JSONException e) {
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public String toString() {
        return this.f15084a;
    }

    /* renamed from: a */
    public boolean m17108a() {
        return !this.f15085b.equals("");
    }
}
