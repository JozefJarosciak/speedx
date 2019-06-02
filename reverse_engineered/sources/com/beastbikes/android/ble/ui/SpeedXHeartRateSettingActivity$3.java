package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import android.os.Handler;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class SpeedXHeartRateSettingActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ int f7782a;
    /* renamed from: b */
    final /* synthetic */ SpeedXHeartRateSettingActivity f7783b;

    /* renamed from: com.beastbikes.android.ble.ui.SpeedXHeartRateSettingActivity$3$1 */
    class C17041 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ SpeedXHeartRateSettingActivity$3 f7781a;

        C17041(SpeedXHeartRateSettingActivity$3 speedXHeartRateSettingActivity$3) {
            this.f7781a = speedXHeartRateSettingActivity$3;
        }

        public void run() {
            SpeedXHeartRateSettingActivity.e(this.f7781a.f7783b).mo3170d(this.f7781a.f7782a);
            SpeedXHeartRateSettingActivity.f(this.f7781a.f7783b).info("B09设备写入心率，heartRate = " + this.f7781a.f7782a);
        }
    }

    SpeedXHeartRateSettingActivity$3(SpeedXHeartRateSettingActivity speedXHeartRateSettingActivity, int i) {
        this.f7783b = speedXHeartRateSettingActivity;
        this.f7782a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9238a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9239a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9238a(String... strArr) {
        return Boolean.valueOf(SpeedXHeartRateSettingActivity.d(this.f7783b).m12135b(SpeedXHeartRateSettingActivity.c(this.f7783b), this.f7782a));
    }

    /* renamed from: a */
    protected void m9239a(Boolean bool) {
        if (!bool.booleanValue()) {
            Toasts.show(this.f7783b, this.f7783b.getString(C1373R.string.network_not_awesome));
        } else if (SpeedXHeartRateSettingActivity.e(this.f7783b) != null) {
            SpeedXHeartRateSettingActivity.f(this.f7783b).info("写入心率信息: 心率值 = " + this.f7782a + ", 结果:" + SpeedXHeartRateSettingActivity.e(this.f7783b).mo3168c(this.f7782a));
            if (SpeedXHeartRateSettingActivity.g(this.f7783b) == 2 || SpeedXHeartRateSettingActivity.g(this.f7783b) == 3) {
                new Handler(this.f7783b.getMainLooper()).postDelayed(new C17041(this), 500);
            }
        }
    }
}
