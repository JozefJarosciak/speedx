package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.ui.p098a.C1711a;
import com.beastbikes.android.ble.ui.p098a.C1712b;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;

class SpeedForceActivity$18 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f7693a;
    /* renamed from: b */
    final /* synthetic */ C1802i f7694b;
    /* renamed from: c */
    final /* synthetic */ boolean f7695c;
    /* renamed from: d */
    final /* synthetic */ SpeedForceActivity f7696d;

    SpeedForceActivity$18(SpeedForceActivity speedForceActivity, String str, C1802i c1802i, boolean z) {
        this.f7696d = speedForceActivity;
        this.f7693a = str;
        this.f7694b = c1802i;
        this.f7695c = z;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9186a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9187a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9186a(String... strArr) {
        try {
            return Boolean.valueOf(new C1651c(this.f7696d).m8897c(this.f7693a));
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m9187a(Boolean bool) {
        if (this.f7694b.isShowing()) {
            this.f7694b.dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f7696d, (int) C1373R.string.dialog_ble_active_success);
            if (this.f7695c) {
                Intent intent = new Intent(this.f7696d, BrowserActivity.class);
                intent.setData(Uri.parse(a$c.f7204e));
                this.f7696d.startActivity(intent);
                return;
            }
            new C1712b(this.f7696d).show();
            return;
        }
        new C1711a(this.f7696d, this.f7696d.getString(C1373R.string.dialog_ble_active_failed_tip)).show();
    }
}
