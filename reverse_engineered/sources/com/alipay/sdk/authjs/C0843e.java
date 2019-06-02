package com.alipay.sdk.authjs;

import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.e */
final class C0843e extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ C0840a f2043a;
    /* renamed from: b */
    final /* synthetic */ C0841c f2044b;

    C0843e(C0841c c0841c, C0840a c0840a) {
        this.f2044b = c0841c;
        this.f2043a = c0840a;
    }

    public final void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", "true");
        } catch (JSONException e) {
        }
        C0840a c0840a = new C0840a(C0840a.f2027c);
        c0840a.f2033i = this.f2043a.f2033i;
        c0840a.f2037m = jSONObject;
        this.f2044b.f2039a.mo2425a(c0840a);
    }
}
