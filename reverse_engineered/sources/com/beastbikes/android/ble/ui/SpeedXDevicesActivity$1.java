package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class SpeedXDevicesActivity$1 extends AsyncTask<String, Void, List<BleDevice>> {
    /* renamed from: a */
    final /* synthetic */ SpeedXDevicesActivity f7718a;

    SpeedXDevicesActivity$1(SpeedXDevicesActivity speedXDevicesActivity) {
        this.f7718a = speedXDevicesActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9195a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9196a((List) obj);
    }

    /* renamed from: a */
    protected List<BleDevice> m9195a(String... strArr) {
        Collection b = SpeedXDevicesActivity.a(this.f7718a).m8893b();
        if (b != null && b.size() > 0) {
            SpeedXDevicesActivity.b(this.f7718a).addAll(b);
        }
        List<BleDevice> a = SpeedXDevicesActivity.a(this.f7718a).m8882a();
        if (a != null && a.size() > 0) {
            for (BleDevice bleDevice : a) {
                if (!SpeedXDevicesActivity.b(this.f7718a).contains(bleDevice)) {
                    SpeedXDevicesActivity.b(this.f7718a).add(bleDevice);
                }
            }
        }
        Collections.sort(SpeedXDevicesActivity.b(this.f7718a), SpeedXDevicesActivity.c(this.f7718a));
        return SpeedXDevicesActivity.b(this.f7718a);
    }

    /* renamed from: a */
    protected void m9196a(List<BleDevice> list) {
        if (list == null || list.size() < 1) {
            SpeedXDevicesActivity.a().error("getBleDevices(), onPostExecute response list is null");
        } else {
            SpeedXDevicesActivity.d(this.f7718a).notifyDataSetChanged();
        }
    }
}
