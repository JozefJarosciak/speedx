package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import org.json.JSONObject;

class CyclingFragment$6 extends AsyncTask<Double, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ C1802i f8660a;
    /* renamed from: b */
    final /* synthetic */ double f8661b;
    /* renamed from: c */
    final /* synthetic */ CyclingFragment f8662c;

    CyclingFragment$6(CyclingFragment cyclingFragment, C1802i c1802i, double d) {
        this.f8662c = cyclingFragment;
        this.f8660a = c1802i;
        this.f8661b = d;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9947a((Double[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9948a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (!this.f8660a.isShowing()) {
            this.f8660a.show();
        }
    }

    /* renamed from: a */
    protected Boolean m9947a(Double... dArr) {
        return Boolean.valueOf(CyclingFragment.a(this.f8662c).m9901a(this.f8661b * 1000.0d));
    }

    /* renamed from: a */
    protected void m9948a(Boolean bool) {
        if (this.f8660a.isShowing()) {
            this.f8660a.dismiss();
        }
        if (bool.booleanValue()) {
            try {
                JSONObject jSONObject = new JSONObject(CyclingFragment.d(this.f8662c).getString("cycling_my_goal", ""));
                jSONObject.put("myGoal", this.f8661b * 1000.0d);
                CyclingFragment.d(this.f8662c).edit().putString("cycling_my_goal", jSONObject.toString()).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
            CyclingFragment.c(this.f8662c);
        }
    }
}
