package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.C2068g;
import com.beastbikes.framework.business.BusinessException;

class ClubLevelActivity$2 extends AsyncTask<Void, Void, C2068g> {
    /* renamed from: a */
    final /* synthetic */ ClubLevelActivity f9717a;

    ClubLevelActivity$2(ClubLevelActivity clubLevelActivity) {
        this.f9717a = clubLevelActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10831a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10832a((C2068g) obj);
    }

    /* renamed from: a */
    protected C2068g m10831a(Void... voidArr) {
        try {
            return new ClubManager(this.f9717a.getApplicationContext()).m10550d();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10832a(C2068g c2068g) {
        if (c2068g != null) {
            ClubLevelActivity.a(this.f9717a, c2068g);
        }
    }
}
