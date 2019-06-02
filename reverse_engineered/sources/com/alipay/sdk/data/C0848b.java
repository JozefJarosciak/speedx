package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0859b;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.packet.impl.C0864b;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.util.C0881i;
import com.alipay.sdk.util.C0883k;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.data.b */
final class C0848b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f2095a;
    /* renamed from: b */
    final /* synthetic */ C0847a f2096b;

    C0848b(C0847a c0847a, Context context) {
        this.f2096b = c0847a;
        this.f2095a = context;
    }

    public final void run() {
        try {
            C0861d c0864b = new C0864b();
            Context context = this.f2095a;
            C0859b a = c0864b.m3347a(context, "", C0883k.m3457a(context), true);
            if (a != null) {
                C0847a c0847a = this.f2096b;
                Object obj = a.f2126b;
                if (!TextUtils.isEmpty(obj)) {
                    try {
                        JSONObject optJSONObject = new JSONObject(obj).optJSONObject(C0847a.f2090g);
                        c0847a.f2093i = optJSONObject.optInt(C0847a.f2089f, C0847a.f2084a);
                        c0847a.f2094j = optJSONObject.optString(C0847a.f2091h, C0847a.f2085b).trim();
                    } catch (Throwable th) {
                    }
                }
                C0847a c0847a2 = this.f2096b;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(C0847a.f2089f, c0847a2.m3263a());
                    jSONObject.put(C0847a.f2091h, c0847a2.f2094j);
                    C0881i.m3450a(C0870b.m3386a().f2177a, C0847a.f2088e, jSONObject.toString());
                } catch (Exception e) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
