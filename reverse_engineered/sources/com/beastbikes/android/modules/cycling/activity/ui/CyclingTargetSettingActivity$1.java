package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.activity.dto.GoalConfigDTO;
import java.util.ArrayList;
import java.util.List;

class CyclingTargetSettingActivity$1 extends AsyncTask<Void, Void, List<GoalConfigDTO>> {
    /* renamed from: a */
    final /* synthetic */ CyclingTargetSettingActivity f8693a;

    CyclingTargetSettingActivity$1(CyclingTargetSettingActivity cyclingTargetSettingActivity) {
        this.f8693a = cyclingTargetSettingActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9962a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9963a((List) obj);
    }

    /* renamed from: a */
    protected List<GoalConfigDTO> m9962a(Void... voidArr) {
        return CyclingTargetSettingActivity.a(this.f8693a).m9900a();
    }

    /* renamed from: a */
    protected void m9963a(List<GoalConfigDTO> list) {
        if (list != null && list.size() > 0) {
            if (CyclingTargetSettingActivity.b(this.f8693a) == null) {
                CyclingTargetSettingActivity.a(this.f8693a, new ArrayList());
            }
            CyclingTargetSettingActivity.b(this.f8693a).clear();
            CyclingTargetSettingActivity.b(this.f8693a).addAll(list);
            CyclingTargetSettingActivity.c(this.f8693a).notifyDataSetChanged();
        }
    }
}
