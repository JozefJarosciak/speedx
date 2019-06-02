package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dao.entity.BleDevice;

class BleSensorListActivity$4 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ BleDevice f8594a;
    /* renamed from: b */
    final /* synthetic */ BleSensorListActivity f8595b;

    BleSensorListActivity$4(BleSensorListActivity bleSensorListActivity, BleDevice bleDevice) {
        this.f8595b = bleSensorListActivity;
        this.f8594a = bleDevice;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9918a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9919a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9918a(Void... voidArr) {
        return Boolean.valueOf(BleSensorListActivity.b(this.f8595b).m8889a(this.f8594a));
    }

    /* renamed from: a */
    protected void m9919a(Boolean bool) {
        if (bool.booleanValue()) {
            BleSensorListActivity.c(this.f8595b).remove(this.f8594a);
            BleSensorListActivity.d(this.f8595b).notifyDataSetChanged();
        }
    }
}
