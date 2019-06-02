package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.util.C0881i;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.data.a */
public final class C0847a {
    /* renamed from: a */
    public static final int f2084a = 3500;
    /* renamed from: b */
    public static final String f2085b = "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&";
    /* renamed from: c */
    public static final int f2086c = 1000;
    /* renamed from: d */
    public static final int f2087d = 20000;
    /* renamed from: e */
    public static final String f2088e = "alipay_cashier_dynamic_config";
    /* renamed from: f */
    public static final String f2089f = "timeout";
    /* renamed from: g */
    public static final String f2090g = "st_sdk_config";
    /* renamed from: h */
    public static final String f2091h = "tbreturl";
    /* renamed from: k */
    private static C0847a f2092k;
    /* renamed from: i */
    int f2093i = f2084a;
    /* renamed from: j */
    public String f2094j = f2085b;

    /* renamed from: a */
    private static /* synthetic */ void m3255a(C0847a c0847a) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f2089f, c0847a.m3263a());
            jSONObject.put(f2091h, c0847a.f2094j);
            C0881i.m3450a(C0870b.m3386a().f2177a, f2088e, jSONObject.toString());
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static /* synthetic */ void m3256a(C0847a c0847a, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject(f2090g);
                c0847a.f2093i = optJSONObject.optInt(f2089f, f2084a);
                c0847a.f2094j = optJSONObject.optString(f2091h, f2085b).trim();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public final int m3263a() {
        if (this.f2093i < 1000 || this.f2093i > 20000) {
            return f2084a;
        }
        new StringBuilder("DynamicConfig::getJumpTimeout >").append(this.f2093i);
        return this.f2093i;
    }

    /* renamed from: c */
    private String m3260c() {
        return this.f2094j;
    }

    /* renamed from: b */
    public static C0847a m3258b() {
        if (f2092k == null) {
            C0847a c0847a = new C0847a();
            f2092k = c0847a;
            Object b = C0881i.m3451b(C0870b.m3386a().f2177a, f2088e, null);
            if (!TextUtils.isEmpty(b)) {
                try {
                    JSONObject jSONObject = new JSONObject(b);
                    c0847a.f2093i = jSONObject.optInt(f2089f, f2084a);
                    c0847a.f2094j = jSONObject.optString(f2091h, f2085b).trim();
                } catch (Throwable th) {
                }
            }
        }
        return f2092k;
    }

    /* renamed from: d */
    private void m3261d() {
        Object b = C0881i.m3451b(C0870b.m3386a().f2177a, f2088e, null);
        if (!TextUtils.isEmpty(b)) {
            try {
                JSONObject jSONObject = new JSONObject(b);
                this.f2093i = jSONObject.optInt(f2089f, f2084a);
                this.f2094j = jSONObject.optString(f2091h, f2085b).trim();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    private void m3257a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f2093i = jSONObject.optInt(f2089f, f2084a);
                this.f2094j = jSONObject.optString(f2091h, f2085b).trim();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: e */
    private void m3262e() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f2089f, m3263a());
            jSONObject.put(f2091h, this.f2094j);
            C0881i.m3450a(C0870b.m3386a().f2177a, f2088e, jSONObject.toString());
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    private void m3259b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject(f2090g);
                this.f2093i = optJSONObject.optInt(f2089f, f2084a);
                this.f2094j = optJSONObject.optString(f2091h, f2085b).trim();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public final void m3264a(Context context) {
        new Thread(new C0848b(this, context)).start();
    }
}
