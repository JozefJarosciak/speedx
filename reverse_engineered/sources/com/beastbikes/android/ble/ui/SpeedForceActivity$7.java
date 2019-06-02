package com.beastbikes.android.ble.ui;

import com.alipay.sdk.util.C0882j;
import com.android.volley.Response.Listener;
import com.beastbikes.android.ble.protocol.v1.OTAFirmwareInfoCharacteristic;
import org.json.JSONObject;

class SpeedForceActivity$7 implements Listener<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ OTAFirmwareInfoCharacteristic f7708a;
    /* renamed from: b */
    final /* synthetic */ SpeedForceActivity f7709b;

    SpeedForceActivity$7(SpeedForceActivity speedForceActivity, OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        this.f7709b = speedForceActivity;
        this.f7708a = oTAFirmwareInfoCharacteristic;
    }

    public /* synthetic */ void onResponse(Object obj) {
        m9194a((JSONObject) obj);
    }

    /* renamed from: a */
    public void m9194a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optInt("code") != 0) {
            SpeedForceActivity.a(this.f7709b, false);
            SpeedForceActivity.b(this.f7709b, false);
            return;
        }
        SpeedForceActivity.a(this.f7709b, SpeedForceActivity.a(this.f7709b, jSONObject.optJSONObject(C0882j.f2229c), this.f7708a));
        SpeedForceActivity.b(this.f7709b, SpeedForceActivity.o(this.f7709b));
    }
}
