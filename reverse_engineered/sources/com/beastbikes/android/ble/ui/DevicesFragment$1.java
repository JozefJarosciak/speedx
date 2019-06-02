package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class DevicesFragment$1 extends AsyncTask<String, Void, List<BleDevice>> {
    /* renamed from: a */
    final /* synthetic */ DevicesFragment f7569a;

    DevicesFragment$1(DevicesFragment devicesFragment) {
        this.f7569a = devicesFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9073a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9074a((List) obj);
    }

    /* renamed from: a */
    protected List<BleDevice> m9073a(String... strArr) {
        List<BleDevice> arrayList = new ArrayList();
        Collection b = DevicesFragment.a(this.f7569a).m8893b();
        if (b != null && b.size() > 0) {
            arrayList.addAll(b);
        }
        List<BleDevice> a = DevicesFragment.a(this.f7569a).m8882a();
        if (a != null && a.size() > 0) {
            for (BleDevice bleDevice : a) {
                if (!arrayList.contains(bleDevice)) {
                    arrayList.add(bleDevice);
                }
            }
        }
        Collections.sort(arrayList, DevicesFragment.b(this.f7569a));
        return arrayList;
    }

    /* renamed from: a */
    protected void m9074a(List<BleDevice> list) {
        if (list == null || list.size() < 1) {
            DevicesFragment.a().error("getBleDevices(), onPostExecute response list is null");
            return;
        }
        DevicesFragment.a().info("Devices size: " + list.size());
        if (DevicesFragment.c(this.f7569a) != null) {
            if (DevicesFragment.d(this.f7569a) == null) {
                DevicesFragment.a(this.f7569a, new ArrayList());
            }
            DevicesFragment.d(this.f7569a).clear();
            DevicesFragment.d(this.f7569a).addAll(list);
            DevicesFragment.c(this.f7569a).m9079a(DevicesFragment.d(this.f7569a));
        }
    }
}
