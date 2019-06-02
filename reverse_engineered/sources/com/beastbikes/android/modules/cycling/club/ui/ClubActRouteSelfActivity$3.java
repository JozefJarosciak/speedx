package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class ClubActRouteSelfActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubActRouteSelfActivity f9432a;

    ClubActRouteSelfActivity$3(ClubActRouteSelfActivity clubActRouteSelfActivity) {
        this.f9432a = clubActRouteSelfActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10670a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10671a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10670a(String... strArr) {
        try {
            return Boolean.valueOf(ClubActRouteSelfActivity.b(this.f9432a).m11199a(ClubActRouteSelfActivity.h(this.f9432a).getId(), strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10671a(Boolean bool) {
        if (bool.booleanValue()) {
            ClubActRouteSelfActivity.e(this.f9432a).remove(ClubActRouteSelfActivity.i(this.f9432a));
            ClubActRouteSelfActivity.e(this.f9432a).add(ClubActRouteSelfActivity.i(this.f9432a), ClubActRouteSelfActivity.h(this.f9432a));
            ClubActRouteSelfActivity.g(this.f9432a).notifyDataSetChanged();
        }
    }
}
