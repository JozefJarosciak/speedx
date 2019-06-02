package com.beastbikes.android.ble.ui;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

class SpeedForceActivity$8 implements ErrorListener {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7710a;

    SpeedForceActivity$8(SpeedForceActivity speedForceActivity) {
        this.f7710a = speedForceActivity;
    }

    public void onErrorResponse(VolleyError volleyError) {
        SpeedForceActivity.b(this.f7710a, false);
    }
}
