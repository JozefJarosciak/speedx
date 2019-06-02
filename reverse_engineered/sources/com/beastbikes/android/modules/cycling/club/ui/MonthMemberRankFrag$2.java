package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class MonthMemberRankFrag$2 extends AsyncTask<Void, Void, List<C2173a>> {
    /* renamed from: a */
    final /* synthetic */ MonthMemberRankFrag f9813a;

    MonthMemberRankFrag$2(MonthMemberRankFrag monthMemberRankFrag) {
        this.f9813a = monthMemberRankFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10882a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10883a((List) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* renamed from: a */
    protected List<C2173a> m10882a(Void... voidArr) {
        try {
            return MonthMemberRankFrag.d(this.f9813a).m10537a(1, MonthMemberRankFrag.a(this.f9813a), MonthMemberRankFrag.b(this.f9813a), MonthMemberRankFrag.c(this.f9813a));
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10883a(List<C2173a> list) {
        MonthMemberRankFrag.e(this.f9813a).m12976a();
        if (list != null && !list.isEmpty()) {
            MonthMemberRankFrag.f(this.f9813a).clear();
            MonthMemberRankFrag.f(this.f9813a).addAll(list);
            MonthMemberRankFrag.h(this.f9813a).notifyDataSetChanged();
            MonthMemberRankFrag.g(this.f9813a).setVisibility(8);
        } else if (MonthMemberRankFrag.f(this.f9813a).size() > 0) {
            MonthMemberRankFrag.g(this.f9813a).setVisibility(8);
        } else {
            MonthMemberRankFrag.g(this.f9813a).setVisibility(0);
        }
    }
}
