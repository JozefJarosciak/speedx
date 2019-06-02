package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubRankBean;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubRankFrag$2 extends AsyncTask<Void, Void, List<ClubRankBean>> {
    /* renamed from: a */
    final /* synthetic */ ClubRankFrag f9796a;

    ClubRankFrag$2(ClubRankFrag clubRankFrag) {
        this.f9796a = clubRankFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10866a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10867a((List) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* renamed from: a */
    protected List<ClubRankBean> m10866a(Void... voidArr) {
        try {
            return ClubRankFrag.d(this.f9796a).m10536a(ClubRankFrag.a(this.f9796a), ClubRankFrag.b(this.f9796a), ClubRankFrag.c(this.f9796a));
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10867a(List<ClubRankBean> list) {
        ClubRankFrag.e(this.f9796a).m12976a();
        if (list != null && !list.isEmpty()) {
            ClubRankFrag.g(this.f9796a).setVisibility(8);
            ClubRankFrag.f(this.f9796a).clear();
            ClubRankFrag.f(this.f9796a).addAll(list);
            ClubRankFrag.h(this.f9796a).notifyDataSetChanged();
        } else if (ClubRankFrag.f(this.f9796a).size() > 0) {
            ClubRankFrag.g(this.f9796a).setVisibility(8);
        } else {
            ClubRankFrag.g(this.f9796a).setVisibility(0);
        }
    }
}
