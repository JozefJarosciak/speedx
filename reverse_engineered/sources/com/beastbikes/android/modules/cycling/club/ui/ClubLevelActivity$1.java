package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.C2065d;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubLevelActivity$1 extends AsyncTask<Void, Void, List<C2065d>> {
    /* renamed from: a */
    final /* synthetic */ ClubLevelActivity f9716a;

    ClubLevelActivity$1(ClubLevelActivity clubLevelActivity) {
        this.f9716a = clubLevelActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10829a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10830a((List) obj);
    }

    /* renamed from: a */
    protected List<C2065d> m10829a(Void... voidArr) {
        try {
            return new ClubManager(this.f9716a.getApplicationContext()).m10548c();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10830a(List<C2065d> list) {
        if (list != null) {
            this.f9716a.f5152b.add((List) list);
        }
    }
}
