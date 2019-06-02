package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;

class MonthlyRankFragment$3 extends AsyncTask<Void, Void, RankDTO> {
    /* renamed from: a */
    final /* synthetic */ MonthlyRankFragment f10189a;

    MonthlyRankFragment$3(MonthlyRankFragment monthlyRankFragment) {
        this.f10189a = monthlyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11149a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11150a((RankDTO) obj);
    }

    /* renamed from: a */
    protected RankDTO m11149a(Void... voidArr) {
        try {
            return MonthlyRankFragment.g(this.f10189a).m11132a(MonthlyRankFragment.d(this.f10189a), MonthlyRankFragment.e(this.f10189a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11150a(RankDTO rankDTO) {
        if (MonthlyRankFragment.h(this.f10189a) != null && rankDTO != null) {
            MonthlyRankFragment.h(this.f10189a).setMilestone(rankDTO.getMilestone());
            MonthlyRankFragment.h(this.f10189a).setOrdinal(rankDTO.getOrdinal());
            MonthlyRankFragment.b(this.f10189a).setHeaderDate(MonthlyRankFragment.h(this.f10189a));
        }
    }
}
