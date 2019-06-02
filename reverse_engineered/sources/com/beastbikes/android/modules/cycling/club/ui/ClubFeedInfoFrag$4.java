package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.framework.business.BusinessException;

class ClubFeedInfoFrag$4 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f9642a;
    /* renamed from: b */
    final /* synthetic */ ClubFeedInfoFrag f9643b;

    ClubFeedInfoFrag$4(ClubFeedInfoFrag clubFeedInfoFrag, String str) {
        this.f9643b = clubFeedInfoFrag;
        this.f9642a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10790a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10791a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10790a(Void... voidArr) {
        try {
            return Boolean.valueOf(new ClubManager(this.f9643b.getActivity()).m10541a(0, ClubFeedInfoFrag.c(this.f9643b).getObjectId(), this.f9642a, null));
        } catch (BusinessException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10791a(Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            ClubFeedInfoFrag.c(this.f9643b).setStatus(2);
            ClubFeedInfoFrag.j(this.f9643b);
            ClubFeedInfoFrag.d(this.f9643b);
        }
    }
}
