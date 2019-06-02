package com.beastbikes.android.ble.ui;

import com.alipay.sdk.util.C0882j;
import com.android.volley.Response.Listener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.protocol.v1.OTAFirmwareInfoCharacteristic;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class SpeedXOtaVersionActivity$8 implements Listener<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ OTAFirmwareInfoCharacteristic f7803a;
    /* renamed from: b */
    final /* synthetic */ SpeedXOtaVersionActivity f7804b;

    SpeedXOtaVersionActivity$8(SpeedXOtaVersionActivity speedXOtaVersionActivity, OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        this.f7804b = speedXOtaVersionActivity;
        this.f7803a = oTAFirmwareInfoCharacteristic;
    }

    public /* synthetic */ void onResponse(Object obj) {
        m9240a((JSONObject) obj);
    }

    /* renamed from: a */
    public void m9240a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optInt("code") != 0) {
            Toasts.show(this.f7804b, (int) C1373R.string.label_ota_version_is_new_msg);
            return;
        }
        SpeedXOtaVersionActivity.a(this.f7804b, jSONObject.optJSONObject(C0882j.f2229c), this.f7803a);
    }
}
