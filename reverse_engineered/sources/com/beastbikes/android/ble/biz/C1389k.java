package com.beastbikes.android.ble.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: GeocodeStub */
/* renamed from: com.beastbikes.android.ble.biz.k */
public interface C1389k extends C1600d {
    @C1446c(a = "nearbysearch/json")
    /* renamed from: a */
    JSONObject m5437a(@C1452i(a = "location") String str, @C1452i(a = "radius") int i, @C1452i(a = "key") String str2);

    @C1446c(a = "autocomplete/json")
    /* renamed from: a */
    JSONObject m5438a(@C1452i(a = "input") String str, @C1452i(a = "key") String str2);

    @C1446c(a = "geocoder/v2/", b = "")
    /* renamed from: a */
    JSONObject m5439a(@C1452i(a = "location") String str, @C1452i(a = "output") String str2, @C1452i(a = "coordtype") String str3, @C1452i(a = "pois") int i, @C1452i(a = "ak") String str4, @C1452i(a = "mcode") String str5);

    @C1446c(a = "place/v2/suggestion", b = "")
    /* renamed from: a */
    JSONObject m5440a(@C1452i(a = "q") String str, @C1452i(a = "region") String str2, @C1452i(a = "output") String str3, @C1452i(a = "ak") String str4, @C1452i(a = "mcode") String str5);

    @C1446c(a = "details/json")
    /* renamed from: b */
    JSONObject m5441b(@C1452i(a = "placeid") String str, @C1452i(a = "key") String str2);
}
