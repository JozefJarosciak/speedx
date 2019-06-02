package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class WeeklyRankFragment$4 extends AsyncTask<Void, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ WeeklyRankFragment f10209a;

    WeeklyRankFragment$4(WeeklyRankFragment weeklyRankFragment) {
        this.f10209a = weeklyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11170a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11171a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m11170a(Void... voidArr) {
        try {
            return WeeklyRankFragment.g(this.f10209a).m11133a(WeeklyRankFragment.d(this.f10209a), WeeklyRankFragment.e(this.f10209a), WeeklyRankFragment.i(this.f10209a), WeeklyRankFragment.f(this.f10209a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11171a(List<RankDTO> list) {
        if (list == null || list.size() == 0) {
            WeeklyRankFragment.b(this.f10209a).setHasFooter(false);
            WeeklyRankFragment.b(this.f10209a).m13150a();
            return;
        }
        if (WeeklyRankFragment.b(this.f10209a).m13154c()) {
            WeeklyRankFragment.a(this.f10209a).clear();
        }
        WeeklyRankFragment.a(this.f10209a).addAll(list);
        if (WeeklyRankFragment.j(this.f10209a) && list.size() < WeeklyRankFragment.f(this.f10209a)) {
            WeeklyRankFragment.b(this.f10209a).setCanLoadMore(false);
        }
        WeeklyRankFragment.b(this.f10209a).setHasFooter(false);
        WeeklyRankFragment.b(this.f10209a).m13153b();
        WeeklyRankFragment.b(this.f10209a).m13150a();
        WeeklyRankFragment.a(this.f10209a, false);
    }
}
