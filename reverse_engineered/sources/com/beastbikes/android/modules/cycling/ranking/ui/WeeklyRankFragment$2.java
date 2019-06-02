package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;
import rx.p208a.C5694b;

class WeeklyRankFragment$2 implements C5694b<C1563a> {
    /* renamed from: a */
    final /* synthetic */ WeeklyRankFragment f10207a;

    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.WeeklyRankFragment$2$1 */
    class C21771 extends AsyncTask<Void, Void, List<RankDTO>> {
        /* renamed from: a */
        final /* synthetic */ WeeklyRankFragment$2 f10206a;

        C21771(WeeklyRankFragment$2 weeklyRankFragment$2) {
            this.f10206a = weeklyRankFragment$2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11165a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11166a((List) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* renamed from: a */
        protected List<RankDTO> m11165a(Void... voidArr) {
            try {
                return WeeklyRankFragment.g(this.f10206a.f10207a).m11133a(WeeklyRankFragment.d(this.f10206a.f10207a), WeeklyRankFragment.e(this.f10206a.f10207a), 1, WeeklyRankFragment.f(this.f10206a.f10207a));
            } catch (BusinessException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m11166a(List<RankDTO> list) {
            if (list != null) {
                WeeklyRankFragment.a(this.f10206a.f10207a).clear();
                WeeklyRankFragment.a(this.f10206a.f10207a).addAll(list);
                WeeklyRankFragment.b(this.f10206a.f10207a).m13153b();
            }
        }
    }

    WeeklyRankFragment$2(WeeklyRankFragment weeklyRankFragment) {
        this.f10207a = weeklyRankFragment;
    }

    public /* synthetic */ void call(Object obj) {
        m11167a((C1563a) obj);
    }

    /* renamed from: a */
    public void m11167a(C1563a c1563a) {
        WeeklyRankFragment.a(this.f10207a, c1563a.m8533a());
        WeeklyRankFragment.c(this.f10207a);
        this.f10207a.getAsyncTaskQueue().m13740a(new C21771(this), new Void[0]);
    }
}
