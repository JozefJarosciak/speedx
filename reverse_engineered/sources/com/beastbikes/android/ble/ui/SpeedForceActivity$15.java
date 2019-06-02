package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.ui.p098a.C1715e;
import com.beastbikes.android.ble.ui.p098a.C1715e.C1696a;
import com.beastbikes.framework.business.BusinessException;
import java.util.HashMap;
import java.util.Map;

class SpeedForceActivity$15 extends AsyncTask<String, Void, Map<String, Boolean>> {
    /* renamed from: a */
    final /* synthetic */ C1651c f7682a;
    /* renamed from: b */
    final /* synthetic */ String f7683b;
    /* renamed from: c */
    final /* synthetic */ SpeedForceActivity f7684c;

    /* renamed from: com.beastbikes.android.ble.ui.SpeedForceActivity$15$1 */
    class C16971 implements C1696a {
        /* renamed from: a */
        final /* synthetic */ SpeedForceActivity$15 f7681a;

        C16971(SpeedForceActivity$15 speedForceActivity$15) {
            this.f7681a = speedForceActivity$15;
        }

        /* renamed from: a */
        public void mo3215a() {
            SpeedForceActivity.a(this.f7681a.f7684c, this.f7681a.f7683b);
        }
    }

    SpeedForceActivity$15(SpeedForceActivity speedForceActivity, C1651c c1651c, String str) {
        this.f7684c = speedForceActivity;
        this.f7682a = c1651c;
        this.f7683b = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9179a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9180a((Map) obj);
    }

    /* renamed from: a */
    protected Map<String, Boolean> m9179a(String... strArr) {
        boolean z = false;
        try {
            Map<String, Boolean> hashMap = new HashMap();
            Map b = this.f7682a.m8895b(this.f7683b);
            if (b != null) {
                z = ((Boolean) b.get("isActive")).booleanValue();
            }
            hashMap.put("isActive", Boolean.valueOf(z));
            return hashMap;
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m9180a(Map<String, Boolean> map) {
        super.onPostExecute(map);
        boolean booleanValue = ((Boolean) map.get("isActive")).booleanValue();
        C1614a b = C1661h.m8999a().m9005b(this.f7683b);
        int i = 0;
        if (b != null) {
            i = b.m8744i().m8746a();
        }
        if (!booleanValue && r0 > 0) {
            String str = "";
            BleDevice c = this.f7682a.m8896c(b.m8728a(), SpeedForceActivity.d(this.f7684c));
            if (c != null) {
                str = BleDevice.brandType2String(c.getBrandType());
            }
            new C1715e(this.f7684c, str, this.f7683b, new C16971(this)).show();
        }
    }
}
