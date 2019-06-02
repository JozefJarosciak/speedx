package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import java.util.List;

class DiscoveryResultActivity$1 extends AsyncTask<String, Void, List<BleDevice>> {
    /* renamed from: a */
    final /* synthetic */ DiscoveryResultActivity f7591a;

    DiscoveryResultActivity$1(DiscoveryResultActivity discoveryResultActivity) {
        this.f7591a = discoveryResultActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9088a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9089a((List) obj);
    }

    /* renamed from: a */
    protected List<BleDevice> m9088a(String... strArr) {
        return DiscoveryResultActivity.a(this.f7591a).m8882a();
    }

    /* renamed from: a */
    protected void m9089a(List<BleDevice> list) {
        super.onPostExecute(list);
        if (list != null && list.size() > 0) {
            DiscoveryResultActivity.a(this.f7591a, ((BleDevice) list.get(0)).getMacAddress());
            DiscoveryResultActivity.b(this.f7591a).notifyDataSetChanged();
        }
    }
}
