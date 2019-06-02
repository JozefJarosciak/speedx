package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.framework.business.BusinessException;

class ClubFeedInfoFrag$5 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubFeedInfoFrag f9644a;

    ClubFeedInfoFrag$5(ClubFeedInfoFrag clubFeedInfoFrag) {
        this.f9644a = clubFeedInfoFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10792a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10793a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10792a(Void... voidArr) {
        try {
            return Boolean.valueOf(new ClubManager(this.f9644a.getActivity()).m10541a(2, ClubFeedInfoFrag.c(this.f9644a).getObjectId(), "", null));
        } catch (BusinessException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10793a(Boolean bool) {
        if (bool != null && bool.booleanValue() && this.f9644a.getActivity() != null) {
            this.f9644a.getActivity().finish();
        }
    }
}
