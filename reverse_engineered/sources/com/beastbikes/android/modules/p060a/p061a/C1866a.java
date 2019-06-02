package com.beastbikes.android.modules.p060a.p061a;

import android.content.Context;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.p060a.p107b.C1867a;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AdManager */
/* renamed from: com.beastbikes.android.modules.a.a.a */
public class C1866a extends C1397a {
    /* renamed from: a */
    private C1394b f8392a = null;

    public C1866a(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f8392a = (C1394b) new C1772d(context).m9399a(C1394b.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public C1867a m9711a() {
        try {
            JSONObject a = this.f8392a.a();
            if (a == null || a.optInt("code") != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            JSONObject jSONObject = optJSONArray.getJSONObject(0);
            if (jSONObject != null) {
                return new C1867a(jSONObject);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
