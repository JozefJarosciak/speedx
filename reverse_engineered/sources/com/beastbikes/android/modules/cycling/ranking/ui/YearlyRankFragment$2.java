package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;
import rx.p208a.C5694b;

class YearlyRankFragment$2 implements C5694b<C1563a> {
    /* renamed from: a */
    final /* synthetic */ YearlyRankFragment f10212a;

    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.YearlyRankFragment$2$1 */
    class C21781 extends AsyncTask<Void, Void, List<RankDTO>> {
        /* renamed from: a */
        final /* synthetic */ YearlyRankFragment$2 f10211a;

        C21781(YearlyRankFragment$2 yearlyRankFragment$2) {
            this.f10211a = yearlyRankFragment$2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11172a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11173a((List) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* renamed from: a */
        protected List<RankDTO> m11172a(Void... voidArr) {
            try {
                return YearlyRankFragment.g(this.f10211a.f10212a).m11133a(YearlyRankFragment.d(this.f10211a.f10212a), YearlyRankFragment.e(this.f10211a.f10212a), 1, YearlyRankFragment.f(this.f10211a.f10212a));
            } catch (BusinessException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m11173a(List<RankDTO> list) {
            if (list != null) {
                YearlyRankFragment.a(this.f10211a.f10212a).clear();
                YearlyRankFragment.a(this.f10211a.f10212a).addAll(list);
                YearlyRankFragment.b(this.f10211a.f10212a).m13153b();
            }
        }
    }

    YearlyRankFragment$2(YearlyRankFragment yearlyRankFragment) {
        this.f10212a = yearlyRankFragment;
    }

    public /* synthetic */ void call(Object obj) {
        m11174a((C1563a) obj);
    }

    /* renamed from: a */
    public void m11174a(C1563a c1563a) {
        YearlyRankFragment.a(this.f10212a, c1563a.m8533a());
        YearlyRankFragment.c(this.f10212a);
        this.f10212a.getAsyncTaskQueue().m13740a(new C21781(this), new Void[0]);
    }
}
