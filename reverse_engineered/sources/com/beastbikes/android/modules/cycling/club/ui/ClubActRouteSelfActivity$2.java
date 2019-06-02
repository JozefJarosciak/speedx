package com.beastbikes.android.modules.cycling.club.ui;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubActRouteSelfActivity$2 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ int f9430a;
    /* renamed from: b */
    final /* synthetic */ ClubActRouteSelfActivity f9431b;

    ClubActRouteSelfActivity$2(ClubActRouteSelfActivity clubActRouteSelfActivity, int i) {
        this.f9431b = clubActRouteSelfActivity;
        this.f9430a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10668a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10669a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (ClubActRouteSelfActivity.a(this.f9431b) != null) {
            ClubActRouteSelfActivity.a(this.f9431b).show();
        }
    }

    /* renamed from: a */
    protected Boolean m10668a(String... strArr) {
        try {
            return Boolean.valueOf(ClubActRouteSelfActivity.b(this.f9431b).m11205e(strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10669a(Boolean bool) {
        if (ClubActRouteSelfActivity.a(this.f9431b) != null) {
            ClubActRouteSelfActivity.a(this.f9431b).dismiss();
        }
        if (bool.booleanValue()) {
            ClubActRouteSelfActivity.e(this.f9431b).remove(this.f9430a);
            ClubActRouteSelfActivity.g(this.f9431b).notifyDataSetChanged();
            if (ClubActRouteSelfActivity.e(this.f9431b).size() == 0) {
                ClubActRouteSelfActivity.c(this.f9431b).setVisibility(8);
                ClubActRouteSelfActivity.d(this.f9431b).setVisibility(0);
                return;
            }
            return;
        }
        NetworkInfo a = C2799c.m13753a(this.f9431b);
        if (a == null || !a.isConnected()) {
            Toasts.show(this.f9431b, (int) C1373R.string.network_not_awesome);
        } else {
            Toasts.show(this.f9431b, (int) C1373R.string.delete_err);
        }
    }
}
