package com.beastbikes.android.ble.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: BleStub */
/* renamed from: com.beastbikes.android.ble.biz.d */
public interface C1387d extends C1600d {
    @C1446c(a = "/device/")
    /* renamed from: a */
    JSONObject m5430a();

    @C1447d(a = "/checkCControlActive")
    /* renamed from: a */
    JSONObject m5431a(@C1444a(a = "ccontrolNo") String str);

    @C1447d(a = "/syncDeviceTotalDistance")
    /* renamed from: a */
    JSONObject m5432a(@C1444a(a = "ccontrolNo") String str, @C1444a(a = "totalDistance") float f);

    @C1447d(a = "/device/")
    /* renamed from: a */
    JSONObject m5433a(@C1444a(a = "central_id") String str, @C1444a(a = "central_name") String str2, @C1444a(a = "hardware") int i, @C1444a(a = "bike_type") int i2);

    @C1447d(a = "/activeCControl")
    /* renamed from: b */
    JSONObject m5434b(@C1444a(a = "ccontrolNo") String str);

    @C1447d(a = "/device/del_device/")
    /* renamed from: c */
    JSONObject m5435c(@C1444a(a = "central_id") String str);
}
