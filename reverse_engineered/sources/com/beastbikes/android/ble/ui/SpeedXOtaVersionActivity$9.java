package com.beastbikes.android.ble.ui;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class SpeedXOtaVersionActivity$9 implements ErrorListener {
    /* renamed from: a */
    final /* synthetic */ SpeedXOtaVersionActivity f7805a;

    SpeedXOtaVersionActivity$9(SpeedXOtaVersionActivity speedXOtaVersionActivity) {
        this.f7805a = speedXOtaVersionActivity;
    }

    public void onErrorResponse(VolleyError volleyError) {
        Toasts.show(this.f7805a, (int) C1373R.string.label_ota_version_is_new_msg);
    }
}
