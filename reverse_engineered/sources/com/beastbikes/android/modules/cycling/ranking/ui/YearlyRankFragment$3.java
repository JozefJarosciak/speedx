package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;

class YearlyRankFragment$3 extends AsyncTask<Void, Void, RankDTO> {
    /* renamed from: a */
    final /* synthetic */ YearlyRankFragment f10213a;

    YearlyRankFragment$3(YearlyRankFragment yearlyRankFragment) {
        this.f10213a = yearlyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11175a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11176a((RankDTO) obj);
    }

    /* renamed from: a */
    protected RankDTO m11175a(Void... voidArr) {
        try {
            return YearlyRankFragment.g(this.f10213a).m11132a(YearlyRankFragment.d(this.f10213a), YearlyRankFragment.e(this.f10213a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11176a(RankDTO rankDTO) {
        if (rankDTO != null && YearlyRankFragment.h(this.f10213a) != null) {
            YearlyRankFragment.h(this.f10213a).setMilestone(rankDTO.getMilestone());
            YearlyRankFragment.h(this.f10213a).setOrdinal(rankDTO.getOrdinal());
            YearlyRankFragment.b(this.f10213a).setHeaderDate(YearlyRankFragment.h(this.f10213a));
        }
    }
}
