package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.apmobilesecuritysdk.p022a.C0764a;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.sdk.cons.C0845b;
import java.util.HashMap;
import java.util.Map;

public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String a;
        synchronized (SecurityClientMobile.class) {
            Map hashMap = new HashMap();
            hashMap.put(C0845b.f2066g, C0789a.m3019a(map, C0845b.f2066g, ""));
            hashMap.put(C0845b.f2062c, C0789a.m3019a(map, C0845b.f2062c, ""));
            hashMap.put("userId", C0789a.m3019a(map, "userId", ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            a = C0764a.m2914a(context);
        }
        return a;
    }
}
