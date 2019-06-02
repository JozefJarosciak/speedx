package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class TotallyRankFragment$4 extends AsyncTask<Void, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ TotallyRankFragment f10204a;

    TotallyRankFragment$4(TotallyRankFragment totallyRankFragment) {
        this.f10204a = totallyRankFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11163a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11164a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m11163a(Void... voidArr) {
        try {
            return TotallyRankFragment.g(this.f10204a).m11133a(TotallyRankFragment.d(this.f10204a), TotallyRankFragment.e(this.f10204a), TotallyRankFragment.i(this.f10204a), TotallyRankFragment.f(this.f10204a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11164a(List<RankDTO> list) {
        if (list == null || list.size() == 0) {
            TotallyRankFragment.b(this.f10204a).setHasFooter(false);
            TotallyRankFragment.b(this.f10204a).m13150a();
            return;
        }
        if (TotallyRankFragment.b(this.f10204a).m13154c()) {
            TotallyRankFragment.a(this.f10204a).clear();
        }
        TotallyRankFragment.a(this.f10204a).addAll(list);
        if (TotallyRankFragment.j(this.f10204a) && list.size() < TotallyRankFragment.f(this.f10204a)) {
            TotallyRankFragment.b(this.f10204a).setCanLoadMore(false);
        }
        TotallyRankFragment.b(this.f10204a).setHasFooter(false);
        TotallyRankFragment.b(this.f10204a).m13153b();
        TotallyRankFragment.b(this.f10204a).m13150a();
        TotallyRankFragment.a(this.f10204a, false);
    }
}
