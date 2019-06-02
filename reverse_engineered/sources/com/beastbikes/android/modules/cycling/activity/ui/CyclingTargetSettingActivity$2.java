package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Intent;
import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.activity.dto.GoalConfigDTO;
import org.json.JSONObject;

class CyclingTargetSettingActivity$2 extends AsyncTask<Double, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ double f8694a;
    /* renamed from: b */
    final /* synthetic */ CyclingTargetSettingActivity f8695b;

    CyclingTargetSettingActivity$2(CyclingTargetSettingActivity cyclingTargetSettingActivity, double d) {
        this.f8695b = cyclingTargetSettingActivity;
        this.f8694a = d;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9964a((Double[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9965a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9964a(Double... dArr) {
        return Boolean.valueOf(CyclingTargetSettingActivity.a(this.f8695b).m9901a(this.f8694a * 1000.0d));
    }

    /* renamed from: a */
    protected void m9965a(Boolean bool) {
        if (bool.booleanValue()) {
            String valueOf = String.valueOf(this.f8694a);
            if (CyclingTargetSettingActivity.b(this.f8695b) != null && CyclingTargetSettingActivity.b(this.f8695b).size() > 0) {
                String str = valueOf;
                for (GoalConfigDTO goalConfigDTO : CyclingTargetSettingActivity.b(this.f8695b)) {
                    if (goalConfigDTO.isChecked()) {
                        valueOf = goalConfigDTO.getKey();
                    } else {
                        valueOf = str;
                    }
                    str = valueOf;
                }
                valueOf = str;
            }
            CyclingTargetSettingActivity.d(this.f8695b).edit().putString("cycling_target_setting", valueOf).commit();
            try {
                JSONObject jSONObject = new JSONObject(CyclingTargetSettingActivity.d(this.f8695b).getString("cycling_my_goal", ""));
                jSONObject.put("myGoal", this.f8694a * 1000.0d);
                CyclingTargetSettingActivity.d(this.f8695b).edit().putString("cycling_my_goal", jSONObject.toString()).commit();
            } catch (Exception e) {
            }
            this.f8695b.sendBroadcast(new Intent("action.target.distance"));
            this.f8695b.finish();
        }
    }
}
