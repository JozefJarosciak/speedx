package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dto.BleCyclingDTO;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.user.p077a.C2389c;
import java.util.List;

class SpeedForceActivity$2 extends AsyncTask<String, Void, BleCyclingDTO> {
    /* renamed from: a */
    final /* synthetic */ String f7698a;
    /* renamed from: b */
    final /* synthetic */ SpeedForceActivity f7699b;

    SpeedForceActivity$2(SpeedForceActivity speedForceActivity, String str) {
        this.f7699b = speedForceActivity;
        this.f7698a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9188a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9189a((BleCyclingDTO) obj);
    }

    /* renamed from: a */
    protected BleCyclingDTO m9188a(String... strArr) {
        BleCyclingDTO bleCyclingDTO;
        BleCyclingDTO h = new C2389c(this.f7699b).m12142h(this.f7698a);
        List<LocalActivity> b = SpeedForceActivity.c(this.f7699b).m8894b(SpeedForceActivity.h(this.f7699b), this.f7698a);
        if (h == null) {
            bleCyclingDTO = new BleCyclingDTO();
        } else {
            bleCyclingDTO = h;
        }
        if (b != null && b.size() > 0) {
            for (LocalActivity localActivity : b) {
                bleCyclingDTO.setTotalCount(bleCyclingDTO.getTotalCount() + 1);
                bleCyclingDTO.setTotalDistance(bleCyclingDTO.getTotalDistance() + localActivity.getTotalDistance());
                bleCyclingDTO.setTotalTime((long) (((double) bleCyclingDTO.getTotalTime()) + localActivity.getTotalElapsedTime()));
            }
        }
        return bleCyclingDTO;
    }

    /* renamed from: a */
    protected void m9189a(BleCyclingDTO bleCyclingDTO) {
        SpeedForceActivity.a(this.f7699b, bleCyclingDTO);
    }
}
