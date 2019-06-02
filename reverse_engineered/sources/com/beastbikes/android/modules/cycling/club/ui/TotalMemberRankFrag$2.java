package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class TotalMemberRankFrag$2 extends AsyncTask<Void, Void, List<C2173a>> {
    /* renamed from: a */
    final /* synthetic */ TotalMemberRankFrag f9839a;

    TotalMemberRankFrag$2(TotalMemberRankFrag totalMemberRankFrag) {
        this.f9839a = totalMemberRankFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10918a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10919a((List) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* renamed from: a */
    protected List<C2173a> m10918a(Void... voidArr) {
        try {
            return TotalMemberRankFrag.d(this.f9839a).m10537a(0, TotalMemberRankFrag.a(this.f9839a), TotalMemberRankFrag.b(this.f9839a), TotalMemberRankFrag.c(this.f9839a));
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10919a(List<C2173a> list) {
        TotalMemberRankFrag.e(this.f9839a).m12976a();
        if (list != null && !list.isEmpty()) {
            TotalMemberRankFrag.f(this.f9839a).clear();
            TotalMemberRankFrag.f(this.f9839a).addAll(list);
            TotalMemberRankFrag.h(this.f9839a).notifyDataSetChanged();
            TotalMemberRankFrag.g(this.f9839a).setVisibility(8);
        } else if (TotalMemberRankFrag.f(this.f9839a).size() > 0) {
            TotalMemberRankFrag.g(this.f9839a).setVisibility(8);
        } else {
            TotalMemberRankFrag.g(this.f9839a).setVisibility(0);
        }
    }
}
