package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class YearlyRankFragment$4 extends AsyncTask<Void, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ YearlyRankFragment f10214a;

    YearlyRankFragment$4(YearlyRankFragment yearlyRankFragment) {
        this.f10214a = yearlyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11177a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11178a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m11177a(Void... voidArr) {
        try {
            return YearlyRankFragment.g(this.f10214a).m11133a(YearlyRankFragment.d(this.f10214a), YearlyRankFragment.e(this.f10214a), YearlyRankFragment.i(this.f10214a), YearlyRankFragment.f(this.f10214a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11178a(List<RankDTO> list) {
        if (list == null || list.size() == 0) {
            YearlyRankFragment.b(this.f10214a).setHasFooter(false);
            YearlyRankFragment.b(this.f10214a).m13150a();
            return;
        }
        if (YearlyRankFragment.b(this.f10214a).m13154c()) {
            YearlyRankFragment.a(this.f10214a).clear();
        }
        YearlyRankFragment.a(this.f10214a).addAll(list);
        if (YearlyRankFragment.j(this.f10214a) && list.size() < YearlyRankFragment.f(this.f10214a)) {
            YearlyRankFragment.b(this.f10214a).setCanLoadMore(false);
        }
        YearlyRankFragment.b(this.f10214a).setHasFooter(false);
        YearlyRankFragment.b(this.f10214a).m13153b();
        YearlyRankFragment.b(this.f10214a).m13150a();
        YearlyRankFragment.a(this.f10214a, false);
    }
}
