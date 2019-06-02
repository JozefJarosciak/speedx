package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class MonthlyRankFragment$4 extends AsyncTask<Void, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ MonthlyRankFragment f10190a;

    MonthlyRankFragment$4(MonthlyRankFragment monthlyRankFragment) {
        this.f10190a = monthlyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11151a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11152a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m11151a(Void... voidArr) {
        try {
            return MonthlyRankFragment.g(this.f10190a).m11133a(MonthlyRankFragment.d(this.f10190a), MonthlyRankFragment.e(this.f10190a), MonthlyRankFragment.i(this.f10190a), MonthlyRankFragment.f(this.f10190a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11152a(List<RankDTO> list) {
        if (list == null || list.size() == 0) {
            MonthlyRankFragment.b(this.f10190a).setHasFooter(false);
            MonthlyRankFragment.b(this.f10190a).m13150a();
            return;
        }
        if (MonthlyRankFragment.b(this.f10190a).m13154c()) {
            MonthlyRankFragment.a(this.f10190a).clear();
        }
        MonthlyRankFragment.a(this.f10190a).addAll(list);
        if (MonthlyRankFragment.j(this.f10190a) && list.size() < MonthlyRankFragment.f(this.f10190a)) {
            MonthlyRankFragment.b(this.f10190a).setCanLoadMore(false);
        }
        MonthlyRankFragment.b(this.f10190a).setHasFooter(false);
        MonthlyRankFragment.b(this.f10190a).m13153b();
        MonthlyRankFragment.b(this.f10190a).m13150a();
        MonthlyRankFragment.a(this.f10190a, false);
    }
}
