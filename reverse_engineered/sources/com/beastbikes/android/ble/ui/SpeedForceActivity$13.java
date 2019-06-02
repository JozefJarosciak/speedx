package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dto.S605FirmwareInfo;
import com.beastbikes.android.ble.p095a.C1599a;

class SpeedForceActivity$13 extends AsyncTask<Void, Void, S605FirmwareInfo> {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7677a;

    SpeedForceActivity$13(SpeedForceActivity speedForceActivity) {
        this.f7677a = speedForceActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9174a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9175a((S605FirmwareInfo) obj);
    }

    /* renamed from: a */
    protected S605FirmwareInfo m9174a(Void... voidArr) {
        return C1599a.m8586a().m8587a(SpeedForceActivity.b(this.f7677a));
    }

    /* renamed from: a */
    protected void m9175a(S605FirmwareInfo s605FirmwareInfo) {
        if (s605FirmwareInfo != null) {
            SpeedForceActivity.a(this.f7677a, s605FirmwareInfo);
        }
    }
}
