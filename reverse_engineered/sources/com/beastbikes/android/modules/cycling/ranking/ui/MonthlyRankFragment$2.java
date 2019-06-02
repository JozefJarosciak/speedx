package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;
import rx.p208a.C5694b;

class MonthlyRankFragment$2 implements C5694b<C1563a> {
    /* renamed from: a */
    final /* synthetic */ MonthlyRankFragment f10188a;

    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.MonthlyRankFragment$2$1 */
    class C21741 extends AsyncTask<Void, Void, List<RankDTO>> {
        /* renamed from: a */
        final /* synthetic */ MonthlyRankFragment$2 f10187a;

        C21741(MonthlyRankFragment$2 monthlyRankFragment$2) {
            this.f10187a = monthlyRankFragment$2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11146a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11147a((List) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* renamed from: a */
        protected List<RankDTO> m11146a(Void... voidArr) {
            try {
                return MonthlyRankFragment.g(this.f10187a.f10188a).m11133a(MonthlyRankFragment.d(this.f10187a.f10188a), MonthlyRankFragment.e(this.f10187a.f10188a), 1, MonthlyRankFragment.f(this.f10187a.f10188a));
            } catch (BusinessException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m11147a(List<RankDTO> list) {
            if (list != null) {
                MonthlyRankFragment.a(this.f10187a.f10188a).clear();
                MonthlyRankFragment.a(this.f10187a.f10188a).addAll(list);
                MonthlyRankFragment.b(this.f10187a.f10188a).m13153b();
            }
        }
    }

    MonthlyRankFragment$2(MonthlyRankFragment monthlyRankFragment) {
        this.f10188a = monthlyRankFragment;
    }

    public /* synthetic */ void call(Object obj) {
        m11148a((C1563a) obj);
    }

    /* renamed from: a */
    public void m11148a(C1563a c1563a) {
        MonthlyRankFragment.a(this.f10188a, c1563a.m8533a());
        MonthlyRankFragment.c(this.f10188a);
        this.f10188a.getAsyncTaskQueue().m13740a(new C21741(this), new Void[0]);
    }
}
