package com.alipay.sdk.packet;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.packet.b */
public final class C0859b {
    /* renamed from: a */
    String f2125a;
    /* renamed from: b */
    public String f2126b;

    public C0859b(String str, String str2) {
        this.f2125a = str;
        this.f2126b = str2;
    }

    /* renamed from: b */
    private String m3329b() {
        return this.f2125a;
    }

    /* renamed from: a */
    private void m3328a(String str) {
        this.f2125a = str;
    }

    /* renamed from: c */
    private String m3331c() {
        return this.f2126b;
    }

    /* renamed from: b */
    private void m3330b(String str) {
        this.f2126b = str;
    }

    /* renamed from: a */
    public final JSONObject m3332a() {
        if (TextUtils.isEmpty(this.f2126b)) {
            return null;
        }
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(this.f2126b);
        } catch (Exception e) {
            jSONObject = null;
        }
        return jSONObject;
    }

    public final String toString() {
        return "\nenvelop:" + this.f2125a + "\nbody:" + this.f2126b;
    }
}
