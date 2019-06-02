package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.ui.p098a.C1711a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.ui.android.utils.Toasts;

class SpeedForceActivity$17 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f7690a;
    /* renamed from: b */
    final /* synthetic */ C1802i f7691b;
    /* renamed from: c */
    final /* synthetic */ SpeedForceActivity f7692c;

    SpeedForceActivity$17(SpeedForceActivity speedForceActivity, String str, C1802i c1802i) {
        this.f7692c = speedForceActivity;
        this.f7690a = str;
        this.f7691b = c1802i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9184a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9185a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9184a(String... strArr) {
        try {
            return Boolean.valueOf(new C1651c(this.f7692c).m8897c(this.f7690a));
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m9185a(Boolean bool) {
        if (this.f7691b.isShowing()) {
            this.f7691b.dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f7692c, (int) C1373R.string.dialog_ble_active_success);
        } else {
            new C1711a(this.f7692c, this.f7692c.getString(C1373R.string.dialog_ble_active_failed_tip)).show();
        }
    }
}
