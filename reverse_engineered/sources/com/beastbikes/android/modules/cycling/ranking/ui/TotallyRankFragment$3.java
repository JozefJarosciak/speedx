package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;

class TotallyRankFragment$3 extends AsyncTask<Void, Void, RankDTO> {
    /* renamed from: a */
    final /* synthetic */ TotallyRankFragment f10203a;

    TotallyRankFragment$3(TotallyRankFragment totallyRankFragment) {
        this.f10203a = totallyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11161a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11162a((RankDTO) obj);
    }

    /* renamed from: a */
    protected RankDTO m11161a(Void... voidArr) {
        try {
            return TotallyRankFragment.g(this.f10203a).m11132a(TotallyRankFragment.d(this.f10203a), TotallyRankFragment.e(this.f10203a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11162a(RankDTO rankDTO) {
        if (TotallyRankFragment.h(this.f10203a) != null && rankDTO != null) {
            TotallyRankFragment.h(this.f10203a).setMilestone(rankDTO.getMilestone());
            TotallyRankFragment.h(this.f10203a).setOrdinal(rankDTO.getOrdinal());
            TotallyRankFragment.b(this.f10203a).setHeaderDate(TotallyRankFragment.h(this.f10203a));
        }
    }
}
