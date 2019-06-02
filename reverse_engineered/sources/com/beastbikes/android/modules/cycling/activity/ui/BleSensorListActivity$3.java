package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import java.util.List;

class BleSensorListActivity$3 extends AsyncTask<Object, Object, List<BleDevice>> {
    /* renamed from: a */
    final /* synthetic */ int f8592a;
    /* renamed from: b */
    final /* synthetic */ BleSensorListActivity f8593b;

    BleSensorListActivity$3(BleSensorListActivity bleSensorListActivity, int i) {
        this.f8593b = bleSensorListActivity;
        this.f8592a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9916a(objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9917a((List) obj);
    }

    /* renamed from: a */
    protected List<BleDevice> m9916a(Object... objArr) {
        return BleSensorListActivity.b(this.f8593b).m8883a(this.f8592a);
    }

    /* renamed from: a */
    protected void m9917a(List<BleDevice> list) {
        if (list != null && !list.isEmpty()) {
            BleSensorListActivity.c(this.f8593b).clear();
            BleSensorListActivity.c(this.f8593b).addAll(list);
            BleSensorListActivity.d(this.f8593b).notifyDataSetChanged();
        }
    }
}
