package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.aa;
import com.beastbikes.framework.business.BusinessException;
import java.util.Calendar;
import org.apache.commons.cli.HelpFormatter;

class SpeedXHeartRateSettingActivity$1 extends AsyncTask<String, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ SpeedXHeartRateSettingActivity f7779a;

    SpeedXHeartRateSettingActivity$1(SpeedXHeartRateSettingActivity speedXHeartRateSettingActivity) {
        this.f7779a = speedXHeartRateSettingActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9234a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9235a((ProfileDTO) obj);
    }

    /* renamed from: a */
    protected ProfileDTO m9234a(String... strArr) {
        try {
            return new C2389c(this.f7779a).m12136c(SpeedXHeartRateSettingActivity.a(this.f7779a));
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m9235a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            SpeedXHeartRateSettingActivity.a(this.f7779a, profileDTO.getMaxHeartRate());
            if (SpeedXHeartRateSettingActivity.b(this.f7779a) > 0) {
                SpeedXHeartRateSettingActivity.b(this.f7779a, SpeedXHeartRateSettingActivity.b(this.f7779a));
                return;
            }
            int a = aa.m12768a(profileDTO.getBirthday().split(HelpFormatter.DEFAULT_OPT_PREFIX)[0]);
            if (a > 0) {
                SpeedXHeartRateSettingActivity.a(this.f7779a, SpeedXHeartRateSettingActivity.c(this.f7779a, Calendar.getInstance().get(1) - a));
                SpeedXHeartRateSettingActivity.b(this.f7779a, SpeedXHeartRateSettingActivity.b(this.f7779a));
            }
        }
    }
}
