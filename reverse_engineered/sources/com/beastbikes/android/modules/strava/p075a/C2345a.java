package com.beastbikes.android.modules.strava.p075a;

import android.content.Context;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import org.json.JSONObject;

/* compiled from: StravaManager */
/* renamed from: com.beastbikes.android.modules.strava.a.a */
public class C2345a extends C1397a {
    /* renamed from: a */
    private C1437b f11194a;

    public C2345a(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f11194a = (C1437b) new C1772d(context).m9399a(C1437b.class, "https://www.strava.com", C1768c.m9391a(context));
    }

    /* renamed from: a */
    public JSONObject m11993a(String str) {
        return this.f11194a.a(13697, "ac9ad6125c73f55e68800a4982a380eea4c153a2", str);
    }
}
