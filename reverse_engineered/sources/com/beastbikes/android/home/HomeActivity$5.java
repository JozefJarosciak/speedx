package com.beastbikes.android.home;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.business.BusinessException;

class HomeActivity$5 extends AsyncTask<Void, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ C1802i f8232a;
    /* renamed from: b */
    final /* synthetic */ AVUser f8233b;
    /* renamed from: c */
    final /* synthetic */ HomeActivity f8234c;

    HomeActivity$5(HomeActivity homeActivity, C1802i c1802i, AVUser aVUser) {
        this.f8234c = homeActivity;
        this.f8232a = c1802i;
        this.f8233b = aVUser;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9527a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9528a((ProfileDTO) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (!this.f8232a.isShowing()) {
            this.f8232a.show();
        }
    }

    /* renamed from: a */
    protected ProfileDTO m9527a(Void... voidArr) {
        try {
            return HomeActivity.e(this.f8234c).m12136c(this.f8233b.getObjectId());
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m9528a(ProfileDTO profileDTO) {
        if (!this.f8234c.isFinishing() && this.f8232a.isShowing()) {
            this.f8232a.dismiss();
        }
        HomeActivity.f(this.f8234c);
    }
}
