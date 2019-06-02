package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dao.entity.BleDevice;

class SpeedForceActivity$6 extends AsyncTask<BleDevice, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7707a;

    SpeedForceActivity$6(SpeedForceActivity speedForceActivity) {
        this.f7707a = speedForceActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9192a((BleDevice[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9193a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9192a(BleDevice... bleDeviceArr) {
        return Boolean.valueOf(SpeedForceActivity.c(this.f7707a).m8890a(SpeedForceActivity.f(this.f7707a), SpeedForceActivity.m(this.f7707a)));
    }

    /* renamed from: a */
    protected void m9193a(Boolean bool) {
        if (bool.booleanValue()) {
            SpeedForceActivity.n(this.f7707a);
            this.f7707a.setResult(-1, this.f7707a.getIntent());
            this.f7707a.finish();
        }
    }
}
