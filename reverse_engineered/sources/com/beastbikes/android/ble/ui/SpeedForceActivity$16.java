package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.ui.p098a.C1712b;
import com.beastbikes.android.ble.ui.p098a.C1715e;
import com.beastbikes.android.ble.ui.p098a.C1715e.C1696a;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SpeedForceActivity$16 extends AsyncTask<String, Void, Map<String, Object>> {
    /* renamed from: a */
    final /* synthetic */ C1651c f7687a;
    /* renamed from: b */
    final /* synthetic */ String f7688b;
    /* renamed from: c */
    final /* synthetic */ SpeedForceActivity f7689c;

    SpeedForceActivity$16(SpeedForceActivity speedForceActivity, C1651c c1651c, String str) {
        this.f7689c = speedForceActivity;
        this.f7687a = c1651c;
        this.f7688b = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9182a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9183a((Map) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (!SpeedForceActivity.e(this.f7689c).isShowing()) {
            SpeedForceActivity.e(this.f7689c).show();
        }
    }

    /* renamed from: a */
    protected Map<String, Object> m9182a(String... strArr) {
        try {
            boolean z;
            Map<String, Object> hashMap = new HashMap();
            Map b = this.f7687a.m8895b(this.f7688b);
            hashMap.put("isActive", b.get("isActive"));
            hashMap.put("owner", b.get("owner"));
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser == null) {
                z = false;
            } else if (currentUser.getSignType() == 16) {
                List c = new C1536a(this.f7689c).m8471c();
                int i = 0;
                boolean z2 = false;
                while (i < c.size()) {
                    AccountDTO accountDTO = (AccountDTO) c.get(i);
                    if (accountDTO.getAuthType() == 2 || accountDTO.getAuthType() == 1) {
                        z = accountDTO.getStatus() == 1;
                        if (z) {
                            break;
                        }
                    } else {
                        z = z2;
                    }
                    i++;
                    z2 = z;
                }
                z = z2;
            } else {
                z = true;
            }
            hashMap.put("isBind", Boolean.valueOf(z));
            return hashMap;
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m9183a(Map<String, Object> map) {
        super.onPostExecute(map);
        if (SpeedForceActivity.e(this.f7689c) != null && SpeedForceActivity.e(this.f7689c).isShowing()) {
            SpeedForceActivity.e(this.f7689c).dismiss();
        }
        boolean booleanValue = ((Boolean) map.get("isActive")).booleanValue();
        final boolean booleanValue2 = ((Boolean) map.get("isBind")).booleanValue();
        int i = 0;
        if (map.get("owner") != null) {
            i = Integer.valueOf(map.get("owner").toString()).intValue();
        }
        if (booleanValue) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser == null) {
                return;
            }
            if (i != currentUser.getSpeedxId()) {
                Toasts.show(this.f7689c, (int) C1373R.string.dialog_ble_active_switch_tip);
            } else if (booleanValue2) {
                Intent intent = new Intent(this.f7689c, BrowserActivity.class);
                intent.setData(Uri.parse(a$c.f7204e));
                this.f7689c.startActivity(intent);
            } else {
                new C1712b(this.f7689c).show();
            }
        } else if (SpeedForceActivity.a(this.f7689c, SpeedForceActivity.f(this.f7689c))) {
            String str = "";
            C1614a b = C1661h.m8999a().m9005b(this.f7688b);
            if (b != null) {
                BleDevice c = this.f7687a.m8896c(b.m8728a(), SpeedForceActivity.g(this.f7689c));
                if (c != null) {
                    str = BleDevice.brandType2String(c.getBrandType());
                }
            }
            new C1715e(this.f7689c, str, this.f7688b, new C1696a(this) {
                /* renamed from: b */
                final /* synthetic */ SpeedForceActivity$16 f7686b;

                /* renamed from: a */
                public void mo3215a() {
                    SpeedForceActivity.a(this.f7686b.f7689c, this.f7686b.f7688b, booleanValue2);
                }
            }).show();
        } else {
            Toasts.show(this.f7689c, (int) C1373R.string.str_disconnect);
        }
    }
}
