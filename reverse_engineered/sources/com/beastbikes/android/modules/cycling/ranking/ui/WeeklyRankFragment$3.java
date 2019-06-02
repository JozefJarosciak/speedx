package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;

class WeeklyRankFragment$3 extends AsyncTask<Void, Void, RankDTO> {
    /* renamed from: a */
    final /* synthetic */ WeeklyRankFragment f10208a;

    WeeklyRankFragment$3(WeeklyRankFragment weeklyRankFragment) {
        this.f10208a = weeklyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11168a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11169a((RankDTO) obj);
    }

    /* renamed from: a */
    protected RankDTO m11168a(Void... voidArr) {
        try {
            return WeeklyRankFragment.g(this.f10208a).m11132a(WeeklyRankFragment.d(this.f10208a), WeeklyRankFragment.e(this.f10208a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11169a(RankDTO rankDTO) {
        if (rankDTO != null && WeeklyRankFragment.h(this.f10208a) != null) {
            WeeklyRankFragment.h(this.f10208a).setMilestone(rankDTO.getMilestone());
            WeeklyRankFragment.h(this.f10208a).setOrdinal(rankDTO.getOrdinal());
            WeeklyRankFragment.b(this.f10208a).setHeaderDate(WeeklyRankFragment.h(this.f10208a));
        }
    }
}
