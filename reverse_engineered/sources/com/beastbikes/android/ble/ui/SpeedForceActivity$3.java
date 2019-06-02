package com.beastbikes.android.ble.ui;

import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import java.util.List;

class SpeedForceActivity$3 extends AsyncTask<String, Void, List<LocalActivity>> {
    /* renamed from: a */
    final /* synthetic */ C1614a f7700a;
    /* renamed from: b */
    final /* synthetic */ BluetoothDevice f7701b;
    /* renamed from: c */
    final /* synthetic */ SpeedForceActivity f7702c;

    SpeedForceActivity$3(SpeedForceActivity speedForceActivity, C1614a c1614a, BluetoothDevice bluetoothDevice) {
        this.f7702c = speedForceActivity;
        this.f7700a = c1614a;
        this.f7701b = bluetoothDevice;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9190a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9191a((List) obj);
    }

    /* renamed from: a */
    protected List<LocalActivity> m9190a(String... strArr) {
        return SpeedForceActivity.c(this.f7702c).m8884a(SpeedForceActivity.i(this.f7702c), this.f7700a.m8728a());
    }

    /* renamed from: a */
    protected void m9191a(List<LocalActivity> list) {
        if (list == null || list.size() <= 0) {
            SpeedForceActivity.j(this.f7702c).setVisibility(8);
            return;
        }
        SpeedForceActivity.j(this.f7702c).setVisibility(0);
        SpeedForceActivity.k(this.f7702c).setText(String.format(this.f7702c.getString(C1373R.string.msg_ble_unsync), new Object[]{this.f7701b.getName()}));
    }
}
