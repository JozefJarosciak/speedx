package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class ActivityComplainActivity$1 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ActivityComplainActivity f11535a;

    ActivityComplainActivity$1(ActivityComplainActivity activityComplainActivity) {
        this.f11535a = activityComplainActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12357a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12358a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* renamed from: a */
    protected Boolean m12357a(String... strArr) {
        try {
            return Boolean.valueOf(ActivityComplainActivity.b(this.f11535a).a(strArr[0], strArr[1], ActivityComplainActivity.a(this.f11535a)));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m12358a(Boolean bool) {
        if (bool.booleanValue()) {
            this.f11535a.finish();
        } else {
            ActivityComplainActivity.a(this.f11535a, false);
        }
    }
}
