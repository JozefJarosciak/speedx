package com.beastbikes.android.ble.biz;

import android.app.Activity;
import android.text.TextUtils;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.ble.dto.C1668c;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: DirectionManager */
/* renamed from: com.beastbikes.android.ble.biz.i */
public class C1662i {
    /* renamed from: a */
    private static final Logger f7527a = LoggerFactory.getLogger("DirectionManager");
    /* renamed from: b */
    private C1388j f7528b;

    public C1662i(Activity activity) {
        this.f7528b = (C1388j) new C1772d(activity).m9398a(C1388j.class, "https://maps.googleapis.com/maps/api/");
    }

    /* renamed from: a */
    public C1668c m9013a(String str, String str2, String str3, String str4, String str5) {
        JSONObject a = this.f7528b.a(str, str2, str3, str4, "AIzaSyC6rtQRpblQN3RWVE3OCK_c8q4QhWVSnfg", str5);
        f7527a.info("CalculateRouteByGoogle jsonObj: " + a);
        String str6 = "";
        if (a != null && a.has("status")) {
            str6 = a.optString("status");
            if (TextUtils.equals(str6, "OK")) {
                JSONArray optJSONArray = a.optJSONArray("routes");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    return new C1668c(str, str6, optJSONArray.optJSONObject(0));
                }
            }
        }
        return new C1668c(str, str6, null);
    }
}
