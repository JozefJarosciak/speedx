package com.beastbikes.android.ble.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: S605RomUpdateStub */
/* renamed from: com.beastbikes.android.ble.biz.l */
public interface C1390l extends C1600d {
    @C1447d(a = "/ota/open/checkVersion")
    /* renamed from: a */
    JSONObject m5442a(@C1452i(a = "mid") String str, @C1452i(a = "version") String str2, @C1452i(a = "oem") String str3, @C1452i(a = "models") String str4, @C1452i(a = "token") String str5, @C1452i(a = "platform") String str6, @C1452i(a = "deviceType") String str7, @C1452i(a = "sdkVerCode") String str8);
}
